package com.cxjd.action.item;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.cxjd.action.CrudAction;
import com.cxjd.common.DataTransfer;
import com.cxjd.common.Dictionary;
import com.cxjd.domain.item.Apply;
import com.cxjd.domain.item.Claimer;
import com.cxjd.domain.item.Item;
import com.cxjd.domain.organizer.Organizer;
import com.cxjd.service.item.ApplyService;
import com.cxjd.service.item.IApplyService;
import com.cxjd.service.item.IItemService;
import com.cxjd.service.item.ItemService;
import com.cxjd.service.organizer.OrganizerService;
import com.cxjd.service.user.UserService;

/**
 * item control层
 * 用于 发布 项目
 * 
 *@author 李腾超
 *@since 2013-07-22
 *
 */
public class ItemAction extends CrudAction<Item, IItemService> implements SessionAware {

	private static final long serialVersionUID = -1572420313368284223L;

	
	private Map<String, Object> sessionMap;	//存储用户登录信息，key为user, value 为userKey
	private Map<String, String> orgMap = new HashMap<String, String>();
	
	private UserService userService = new UserService();
	
	public Map<String, String> getOrgMap() {
		return orgMap;
	}

	public void setOrgMap(Map<String, String> orgMap) {
		this.orgMap = orgMap;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public void setSession(Map<String, Object> arg0) {
		this.sessionMap = arg0;
	}
	
	@Override
	public Item getModel() {
		if(null == model){
			model = new Item();
		}
		return model;
	}

	@Override
	public IItemService getService() {
		if(null == service){
			service = new ItemService();
		}
		return service;
	}
	
	private IApplyService applyService;

	public IApplyService getApplyService() {
		if(null == applyService){
			applyService = new ApplyService();
		}
		return applyService;
	}


	@Override
	public String list() throws Exception {
		pager.setPageSize(10);
		
		if(this.sessionMap.get(Dictionary.role).equals(Dictionary.role_admin) || 
				this.sessionMap.get(Dictionary.role).equals(Dictionary.role_stu)){	//超级管理员和学生
			setEntities(getService().readWithPage(pager, null));
		}else{		
			
			String orgId = userService.getOrgIdByKey(this.sessionMap.get("userKey"));
			Object []args = {orgId};
			setEntities(getService().readWithPage(pager, args));
			
		}
		return "list";
	}

	@Override
	public String input() throws Exception {
		
		if(this.sessionMap.get(Dictionary.role).equals(Dictionary.role_admin)){
			OrganizerService organizerService = new OrganizerService();
			List<Organizer> organizers = organizerService.readAll();
			
			orgMap.put("", "未选择");
			for(Organizer organizer : organizers){
				orgMap.put(organizer.getId(), organizer.getOrgName());
			}
		}else {
			OrganizerService organizerService = new OrganizerService();
			String orgId = userService.getOrgIdByKey(this.sessionMap.get("userKey"));
			Organizer organizer =  organizerService.readUnique(orgId);
			getModel().setOrgId(orgId);
			orgMap.put(orgId, organizer.getOrgName());
		}
		
		String nav = "addinput";
		if( getKey()!=null && !getKey().isEmpty()){
			nav = "updateinput";
			model = getService().readUnique(getKey());
		}
		return nav;
	}

	String itemTime;
	
	public String getItemTime() {
		return itemTime;
	}

	public void setItemTime(String itemTime) {
		this.itemTime = itemTime;
	}

	@Override
	public String add() throws Exception {
		getModel().setItemTime(DataTransfer.stringtoDate(itemTime));
		getService().create(getModel());
		return "add";
	}

	@Override
	public String update() throws Exception {
		getModel().setItemTime(DataTransfer.stringtoDate(itemTime));
		getService().update(getModel());
		return "update";
	}

	@Override
	public String delete() throws Exception {
		getService().deleteUnique(getKey());
		return "delete";
	}
	
	//检测项目是否过期，过期返回true，不过期返回false
	public boolean ifOutNotDate(Date date){
		Date curr = new Date();
		/* 
		 if(date.getYear() > curr.getYear()){
			 return false;
		 }else if(date.getYear() < curr.getYear()){
			 return true;
		 }else{
			 if(date.getMonth() > curr.getMonth()){
				 return false;
			 }else if(date.getMonth() < curr.getMonth()){
				 return true;
			 }else if(date.getMonth() == curr.getMonth()){
				 if(date.getDay() > curr.getDay()){
					 return true;
				 }else if(date.getDay() < curr.getDay()){
					 return false;
				 }else{
					 return false;
				 }
			 }
			 return false;
		 }*/
		return date.before(curr);
		 
	}
	Apply apply;
	public Apply getApply() {
		if(apply == null){
			apply = new Apply();
			apply.setApplyStatus(Dictionary.applyStatus_uncheck);
			if(apply.getMainClaimer() == null)
			{
				apply.setMainClaimer(new Claimer());
				apply.getMainClaimer().setClaimerType(Dictionary.claimerType_main);
			}
		}
		
		return apply;
	}
	public void setApply(Apply apply) {
		this.apply = apply;
	}
	
	private List otherClaimer;
	public List getOtherClaimer() {
		return otherClaimer;
	}
	public void setOtherClaimer(List otherClaimer) {
		this.otherClaimer = otherClaimer;
	}

	public String apply() {
		this.setApply(getApplyService().readApplyByItemFK(getKey()));
		this.getApply().setApplyName("");
		return "apply";
	}
	
	//提交报名表
	public String doApply() {
		if(otherClaimer.size() > 0){
			int length = otherClaimer.size();
			apply.setOtherCliamers(new ArrayList<Claimer>());
			for(int i=0; i<length ;i++ ){
				Claimer cl = (Claimer)otherClaimer.get(i);	//其他组员
				cl.setClaimerType(Dictionary.claimerType_other);
				apply.getOtherCliamers().add(cl);
			}
			getApplyService().create(apply);
		}
		return "applyok";
	}
	
	public String transDateTime(Date time){
		
		return  DataTransfer.dateToString(time);
	}

	
}
