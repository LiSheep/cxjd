package com.cxjd.action.item;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.cxjd.action.CrudAction;
import com.cxjd.common.Dictionary;
import com.cxjd.domain.item.Apply;
import com.cxjd.domain.item.Claimer;
import com.cxjd.domain.item.Item;
import com.cxjd.service.item.ApplyService;
import com.cxjd.service.item.ClaimerService;
import com.cxjd.service.item.IApplyService;
import com.cxjd.service.item.IClaimerService;
import com.cxjd.service.item.ItemService;

/**
 * Apply action层
 * 
 *@author 李腾超
 *@since 2013-08-29
 *
 */
public class ApplyAction extends CrudAction<Apply, IApplyService> implements SessionAware {

	private static final long serialVersionUID = -2050193911568113970L;

	private Map<String, Object> sessionMap;
	Map<String, String> applyStatusMap = new HashMap<String, String>();
	private IClaimerService claimerService;
	private Map<String, String> itemMap = new HashMap<String, String>();
	
	public Map<String, String> getItemMap() {
		return itemMap;
	}

	public IClaimerService getClaimerService() {
		if(null == claimerService)
			claimerService = new ClaimerService();
		return claimerService;
	}

	public Map<String, String> getApplyStatusMap() {
		return applyStatusMap;
	}


	public void setClaimerService(IClaimerService claimerService) {
		this.claimerService = claimerService;
	}

	public ApplyAction(){
		getModel().setApplyStatus(-1);
		
	}
	@Override
	public Apply getModel() {
		if(model == null)
			model = new Apply();
		return model;
	}

	@Override
	public IApplyService getService() {
		if(service == null)
			service = new ApplyService();
		return service;
	}

	@Override
	public String list() throws Exception {
		List<Apply> list =  Collections.emptyList();
		if(sessionMap.isEmpty())
			return Dictionary.noaccess;
		if(sessionMap.get(Dictionary.role).equals(Dictionary.role_stu)){
			Object userKey = sessionMap.get("userKey");
			pager.setPageSize(10);
			list = getService().readApplyByUserKeyWithPage(pager, userKey);
		}else{	//管理员/老师/
				
			//按条件查询
			ItemService itemService = new ItemService();
			List<Item> isItems = itemService.readAll();
			itemMap.put("", "查询全部");
			for(Item it : isItems ){
				itemMap.put(it.getId(), it.getItemName());
			}
			applyStatusMap.put(String.valueOf(Dictionary.applyStatus_nopass), "审核未通过");
			applyStatusMap.put(String.valueOf(Dictionary.applyStatus_pass), "审核通过");
			applyStatusMap.put(String.valueOf(Dictionary.applyStatus_uncheck), "未审核");
			applyStatusMap.put("-1", "查询全部");
			
			if(sessionMap.get(Dictionary.role).equals(Dictionary.role_admin)){	//超级管理员查询全部
			
				list = getService().readWithPageByArgs(pager, getModel().getApplyStatus(), getModel().getItem().getId());
				if(getModel().getItem().getId() != null && !getModel().getItem().getId().equals("")){
					
					getModel().setItem(itemService.readUnique(getModel().getItem().getId()));
				}
			}else if(sessionMap.get(Dictionary.role).equals(Dictionary.role_tea) 
					|| sessionMap.get(Dictionary.role).equals(Dictionary.role_collAdmin)){//其他查询本学院
				list = getService().readWithPageByArgsOth(pager, getModel().getApplyStatus(), getModel().getItem().getId(), sessionMap.get("orgId").toString());
				if(getModel().getItem().getId() != null && !getModel().getItem().getId().equals("")){
					
					getModel().setItem(itemService.readUnique(getModel().getItem().getId()));
				}
			}
		}
		
		setEntities(list);
		return "list";
	}

	@Override
	public String input() throws Exception {
		return null;
	}

	@Override
	public String add() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String update() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String delete() throws Exception {
		getService().deleteUnique(getKey());
		getClaimerService().deleteClaimerByApplyKey(getKey());
		return "delete";
	}

	@Override
	public void setSession(Map<String, Object> arg0) {
		this.sessionMap = arg0;
	}

	public String show() {
		model = getService().readUnique(getKey());
		List<Claimer> cls = getClaimerService().readClaimerByApplyKey(model.getId(), Dictionary.claimerType_main);
		if(cls == null || cls.size() == 0)
			return "check";
		
		model.setMainClaimer(cls.get(0));
		model.setOtherCliamers(getClaimerService().readClaimerByApplyKey(model.getId(), Dictionary.claimerType_other));
		
		return "show";
	}
	
	public String checkOK(){
		getService().UpdateApplyStatus(Dictionary.applyStatus_pass, getKey());
		return "check";
	}
	
	public String checkNO(){
		getService().UpdateApplyStatus(Dictionary.applyStatus_nopass, getKey());
		return "check";
	}
	
	public String mark(){
		model = getService().readSingleApplySimple(getKey());
		return "mark";
	}
	
	public String gomark(){
		getService().updateApplyMark(getModel().getApplyMark(), getModel().getId());
		getService().UpdateApplyStatus(Dictionary.applyStatus_mark, getModel().getId());
		model = getService().readSingleApplySimple(getModel().getId());
		return "mark";
	}
	
	public String listmark(){
		getPager().setPageSize(10);
		
		setEntities(getService().readSimpleApply4MarkWithPage(getPager()));
		return "listmark";
	}
	
}
