package com.cxjd.action.user;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cxjd.action.CrudAction;
import com.cxjd.domain.organizer.Organizer;
import com.cxjd.domain.role.Role;
import com.cxjd.domain.user.User;
import com.cxjd.service.organizer.OrganizerService;
import com.cxjd.service.role.RoleService;
import com.cxjd.service.user.IUserService;
import com.cxjd.service.user.UserService;

/**
 * user control²ã
 * 
 *@author ÀîÌÚ³¬
 *@since 2013-05-22
 *
 */
public class UserAction extends CrudAction<User, IUserService> {


	private static final long serialVersionUID = 260831697957624842L;

	Map<String, String> roleMap = new HashMap<String, String>();
	Map<String, String> orgMap = new HashMap<String, String>();
	private Map<Boolean, String> stateMap = new HashMap<Boolean, String>();
	
	public Map<Boolean, String> getStateMap() {
		return stateMap;
	}

	public void setStateMap(Map<Boolean, String> stateMap) {
		this.stateMap = stateMap;
	}

	public Map<String, String> getRoleMap() {
		return roleMap;
	}

	public void setRoleMap(Map<String, String> roleMap) {
		this.roleMap = roleMap;
	}
	
	public Map<String, String> getOrgMap() {
		return orgMap;
	}

	public void setOrgMap(Map<String, String> orgMap) {
		this.orgMap = orgMap;
	}

	public UserAction() {
		RoleService roleService = new RoleService();
		List<Role> roles =  roleService.read(null);
		for (Role role : roles) {
			roleMap.put(role.getId(),role.getRoleName());
		}
		OrganizerService organizerService = new OrganizerService();
		List<Organizer> organizers = organizerService.readAll();
		
		orgMap.put("", "Î´Ñ¡Ôñ");
		for(Organizer organizer : organizers){
			orgMap.put(organizer.getId(), organizer.getOrgName());
		}
		
		stateMap.put(true, "ÒÑÉóºË");
		stateMap.put(false, "Î´ÉóºË");
		
	}
	
	@Override
	public User getModel() {
		if(model == null){
			model = new User();
		}
		return model;
	}
	
	@Override
	public IUserService getService() {
		if(service == null){
			service = new UserService();
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
		return"add";
	}

	@Override
	public String update() throws Exception {
		User oldUser = new User();
		oldUser = getService().readUnique(getModel().getId());
		if(getModel().getUserPass() == null
				|| getModel().getUserPass().isEmpty()) {
			//Ã»ÓÐ¸ü¸ÄÃÜÂë
			getModel().setUserPass(oldUser.getUserPass());
		}
		getService().update(getModel());
		
		return "update";
	}

	@Override
	public String delete() throws Exception {
		getService().deleteUnique(getKey());
		return list();
	}

	
}
