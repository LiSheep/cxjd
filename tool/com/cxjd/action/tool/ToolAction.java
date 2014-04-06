package com.cxjd.action.tool;


import com.cxjd.action.CrudAction;
import com.cxjd.common.Dictionary;
import com.cxjd.domain.tool.BorrowTool;
import com.cxjd.domain.tool.Tool;
import com.cxjd.service.tool.BorrowToolService;
import com.cxjd.service.tool.IToolService;
import com.cxjd.service.tool.ToolService;
import com.cxjd.service.user.IUserService;
import com.cxjd.service.user.UserService;

/**
 * tool action��
 * 
 *@author ���ڳ�
 *@since 2013-07-23
 *
 */
public class ToolAction extends CrudAction<Tool, IToolService> {

	private static final long serialVersionUID = -8386233090475373479L;
	@Override
	public Tool getModel() {
		if(model == null){
			model = new Tool();
		}
		return model;
	}

	@Override
	public IToolService getService() {
		if(service == null){
			service = new ToolService(); //newһ��service
		}
		return service;
	}

	@Override
	public String list() throws Exception {
		pager.setPageSize(10);
		setEntities(getService().readWithPage(pager, null));
		
		return "list";
	}

	@Override
	public String input() throws Exception {
		String nav = "addinput";
		if( getKey()!=null && !getKey().isEmpty()){
			nav = "updateinput";
			model = getService().readUnique(getKey());
		}
		return nav;
	}

	@Override
	public String add() throws Exception {
		getService().create(getModel());
		return "add";
	}

	@Override
	public String update() throws Exception {
		getService().update(getModel());
		return "update";
	}

	@Override
	public String delete() throws Exception {
		getService().deleteUnique(getKey());
		return "delete";
	}

	public String getUserByKey(String userKey){
		IUserService userService = new UserService();
		return userService.getUserNameByUserKey(userKey);
	}
	
	public String getPlace(int place) {
		if(place == Dictionary.schBenBu)
			return "����";
		if(place == Dictionary.schLvShun)
			return"��˳";
		
		return "error";
	}
	
	public int getToolLeft(int num, int out){
		return  num-out;
	}
	
	
}
