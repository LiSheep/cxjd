package com.cxjd.action.role;

import java.util.Map;

import com.cxjd.action.CrudAction;
import com.cxjd.domain.role.Role;
import com.cxjd.service.role.IRoleService;
import com.cxjd.service.role.RoleService;
import com.cxjd.util.RoleType;

/**
 * role's control  层
 * @author 李腾超
 * @since 2013-06-23
 *
 */
public class RoleAction extends CrudAction<Role, IRoleService>{

	private static final long serialVersionUID = 332008553200395661L;

	private  Map<Integer, String> roleType = RoleType.getRoleType();
	
	public Map<Integer, String> getRoleType() {
		return roleType;
	}

	public void setRoleType(Map<Integer, String> roleType) {
		this.roleType = roleType;
	}

	@Override
	public Role getModel() {
		if(model == null){
			model = new Role();
		}
		return model;
	}

	@Override
	public IRoleService getService() {
		if(service == null){
			service = new RoleService();
		}
		return service;
	}

	@Override
	public String list() throws Exception {
		pager.setPageSize(5);
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
		Role oldUser = new Role();
		oldUser = getService().readUnique(getModel().getId());
		if(oldUser.getRoleName() == getModel().getRoleName() 
				&&oldUser.getRoleAuth() == getModel().getRoleAuth()){
			//没有更改
			return "update";
		}
		getService().update(getModel());
		return "update";
	}

	@Override
	public String delete() throws Exception {
		getService().deleteUnique(getKey());
		return list();
	}

	public String getRoleByType(int type){
		return RoleType.getTypeByKey(type);
	}
}
