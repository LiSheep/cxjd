package com.cxjd.action.tool;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import sun.java2d.pipe.SpanShapeRenderer.Simple;

import com.cxjd.action.CrudAction;
import com.cxjd.common.CommonUtil;
import com.cxjd.common.Dictionary;
import com.cxjd.domain.role.Role;
import com.cxjd.domain.tool.BorrowTool;
import com.cxjd.domain.tool.Tool;
import com.cxjd.service.tool.BorrowToolService;
import com.cxjd.service.tool.IBorrowToolService;
import com.cxjd.service.tool.IToolService;
import com.cxjd.service.tool.ToolService;

/**
 * borrowTool action层
 * 
 *@author 李腾超
 *@since 2013-07-29
 *
 */
public class BorrowToolAction extends CrudAction<BorrowTool, IBorrowToolService> 
							implements SessionAware{

	private static final long serialVersionUID = -7050753033985058461L;
	private Map<String, Object> sessionMap;
	
	
	@Override
	public BorrowTool getModel() {
		if(this.model == null){
			this.model = new BorrowTool();
		}
		return this.model;
	}

	@Override
	public IBorrowToolService getService() {
		if(this.service == null){
			this.service =new BorrowToolService();
		}
		return this.service;
	}

	@Override
	public String list() throws Exception {
		pager.setPageSize(10);
		//为学生
		int a =   (Integer) sessionMap.get(Dictionary.role);
		if(Integer.valueOf(this.sessionMap.get(Dictionary.role).toString()) == Dictionary.role_stu ){
			Object[] args = {this.sessionMap.get("userKey")};
			setEntities(getService().readWithPage(pager, args));
		}else if(Integer.valueOf(this.sessionMap.get(Dictionary.role).toString()) == Dictionary.role_admin
				||Integer.valueOf(this.sessionMap.get(Dictionary.role).toString()) == Dictionary.role_tea) {
			setEntities(getService().readAllWithPager(pager, null));
		}
		else {
			// TODO Auto-generated method stub
			return "no.access"; //错误
		}
		return "list";
	}

	@Override
	public String input() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String add() throws Exception {
		IToolService toolService =  new ToolService();
		Tool tool = toolService.readUnique(getModel().getToolKey());
		if(tool.getToolNum() < getModel().getBroNum()){
			this.addFieldError("err_broNum", "需要租借数量不能超过总数量");	//添加错误：需要租借数量不能超过总数量
		}
		else {
			getService().create(getModel());
		}
		return "apply";
	}

	@Override
	public String update() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String delete() throws Exception {
		getService().deleteUnique(getKey());
		return "delete";
	}
	
	public String apply(){
		if( getKey()!=null && !getKey().isEmpty()){
			this.model = getService().getToolInfoByToolKey(getKey());
		}
		return "apply";
	}

	@Override
	public void setSession(Map<String, Object> arg0) {
		this.sessionMap = arg0;
	}
	
	public String check(){
		if( getKey()!=null && !getKey().isEmpty()){
			this.model = getService().readUnique(getKey());
		}
		return "check";
	}
	
	public String checkOK(){
		IToolService toolService =  new ToolService();
		Tool tool = toolService.readUnique(getModel().getToolKey());
		if(tool.getToolNum() < getModel().getBroNum()){
			this.addFieldError("err_broNum", "需要租借数量不能超过总数量");	//添加错误：需要租借数量不能超过总数量
			return "check";
		}
		if(getService().outBorrowTool(getModel())){
			
		}else {
			
		}
		
		return "check";
	}
	
	public String checkNO(){
		if(getService().refuseBorrowTool(getModel().getId())){
			
		}else {
			
		}
		return "check";
	}
	
	public String checkBack(){
		if(getService().backBorrowTool(getKey())){
			
		}else {
			
		}
		
		return "delete";
	}
	
	//获取当前状态
	public int getStatus(Object key){
		BorrowTool item = getService().readUnique(key);
		return item.getBroStatus();
	}
	
	//计算应归还时间
	public String getBackTime(Date broTime, int day){
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		return sf.format(CommonUtil.ChangeTime(broTime, day));
	}
	
	//获取是否超过应归还时间
	//为true则为已超过应归还时间
	public boolean needReturn(Date broTime){
		return (broTime.compareTo(new Date()) > 0);
	}
}
