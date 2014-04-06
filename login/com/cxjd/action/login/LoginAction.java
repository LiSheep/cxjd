package com.cxjd.action.login;

import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.ParameterAware;
import org.apache.struts2.interceptor.SessionAware;

import com.cxjd.common.Dictionary;
import com.cxjd.common.SessionHelper;
import com.cxjd.dao.Pager;
import com.cxjd.domain.news.News;
import com.cxjd.domain.role.Role;
import com.cxjd.domain.user.User;
import com.cxjd.service.news.INewsService;
import com.cxjd.service.news.NewsService;
import com.cxjd.service.role.RoleService;
import com.cxjd.service.user.UserService;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 登陆页面 action层
 * 
 *@author 李腾超
 *@since 2013-08-29
 *
 */
public class LoginAction extends ActionSupport implements 
						SessionAware, ParameterAware  {

	private static final long serialVersionUID = -980708965739112059L;
	private UserService userService = new UserService();
	
	private Map<String, Object> sessionMap;	//存储用户登录信息，key为user, value 为userKey
	private Map<String, String[]> paramMap;
	
	protected User model;
	
	public User getModel() {
		if(model == null){
			model = new User();
		}
		return model;
	}
	
	public String login(){
		if(SessionHelper.authorizating(sessionMap)){
			return Dictionary.access;
		}
		User user = this.loginAuth();
		String nav = Dictionary.noaccess;
		if(user != null){
			if( user.isState() ) {		//未被允许登录
				Role role = this.getRoleByUser(user);
				if(role != null){
					sessionMap.put(Dictionary.role, role.getRoleAuth());	
					sessionMap.put(Dictionary.name, user.getUserName() );
					sessionMap.put("userKey", user.getId());
					sessionMap.put(Dictionary.roleName, 
							userService.getRoleNameByUserKey(user.getId()));
					sessionMap.put("orgId", user.getOrgId());
					nav = Dictionary.access;
				}
			}
		}
		else {
			nav = Dictionary.noaccess;
		}
		
		//设置新闻
		setNews();
		
		
		return nav;
	}	
	
	List<News> entities;
	INewsService newsService;
	public INewsService getNewsService() {
		if(null == newsService){
			newsService = new NewsService();
		}
		return newsService;
	}


	public List<News> getEntities() {
		return entities;
	}

	public void setEntities(List<News> entities) {
		this.entities = entities;
	}
	
	private void setNews(){
		Pager<News> pager = new Pager<News>();
		pager.setPageNo(1);
		pager.setPageSize(8);
		Object []args = {1};
		setEntities(getNewsService().readWithPage(pager, args));
	}
	
	public String logout(){
		org.apache.struts2.dispatcher.SessionMap<String, Object> smMap =
				(org.apache.struts2.dispatcher.SessionMap<String, Object>)sessionMap;
		smMap.invalidate();
		return login();
	}
	
	//用户登陆验证
	private User loginAuth() {
		String[] account = paramMap.get("account");
		String[] password = paramMap.get("password");
		
		if(account == null || password == null)
			return null;
		
		if(account.length == 0 || password.length == 0)	
			return null;
		User user = userService.loginAuth(account[0], password[0]);
		if( user != null){
			return user;
		}else{
			this.addFieldError("account", "用户名或密码错误");
			return null;
		}
		
	}
	
	private Role getRoleByUser(User user){
		RoleService roleService = new RoleService();
		return roleService.getRoleTypeById(user.getRoleKey());
	}
	
	public String register(){
		if(this.loginAuth() == null)
			return "register";
		else
			return "access";
	}
	
	public String doregister(){
		getModel().setState(true);
		getModel().setRoleKey("");
		
		if(userService.checkUserName(getModel().getUserName())){
			this.addFieldError("model.userName", "用户名已存在");
			return "register";
		}
		if(userService.checkUserMail(getModel().getUserMail())){
			this.addFieldError("model.userMail", "该email已被注册");
			return "register";
		}
		getModel().setRoleKey("c19b1a6c-aee0-454b-b4eb-e7eb71256cfd");
		getModel().setState(true);
		if(userService.create(getModel()))
			return "access";
		else
			return "faile";
		
	}
	
	
	@Override
	public void setSession(Map<String, Object> arg0) {
		this.sessionMap = arg0;
	}
	@Override
	public void setParameters(Map<String, String[]> arg0) {
		this.paramMap = arg0;
	}
	
	private String oldpass;
	private String newpass;
	private String confirm;
	
	
	
	public String getConfirm() {
		return confirm;
	}

	public void setConfirm(String confirm) {
		this.confirm = confirm;
	}

	public String getOldpass() {
		return oldpass;
	}

	public void setOldpass(String oldpass) {
		this.oldpass = oldpass;
	}

	public String getNewpass() {
		return newpass;
	}

	public void setNewpass(String newpass) {
		this.newpass = newpass;
	}

	public String changepass(){
		return "changepass";
	}
	
	public String dochangepass(){
		if( !userService.changepass(sessionMap.get("userKey"), oldpass, newpass))
			this.addFieldError("err_oldpass", "原密码错误,请重新输入");
		else {
			this.addFieldError("msg", "密码修改成功");
		}
		return "changepass";
	}
	
}
