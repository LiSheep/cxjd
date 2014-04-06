package com.cxjd.service.user;


import java.sql.SQLException;

import com.cxjd.domain.user.User;
import com.cxjd.service.IBaseService;

/**
 * role's service 层接口，返回NULL则为验证失败
 * @author 李腾超
 * @since 2013-05-22
 *
 */
public interface IUserService extends IBaseService<User> {
	
	public User loginAuth(String account, String password);
	
	public String getRoleNameByUserKey(String key);
	
	public String getUserNameByUserKey(String key);
	
	/**
	 * 查找是否有重复的用户名
	 * @param  用户名
	 * @return 返回true为有重复的用户名，false为没有重复用户名
	 * @throws SQLException 
	 */
	public boolean checkUserName(String userName);
	
	/**
	 * 查找是否有重复的邮箱地址
	 * @param  邮箱地址
	 * @return 返回true为有重复的邮箱地址，false为没有重复邮箱地址
	 * @throws SQLException 
	 */
	public boolean checkUserMail(String userMail);
	
	/**
	 * 查找是否有重复的邮箱地址
	 * @param  邮箱地址
	 * @return 返回true为有重复的邮箱地址，false为没有重复邮箱地址
	 * @throws SQLException 
	 */
	public boolean changepass(Object key, String oldpass, String newpass);
	
	/**
	 * 更具用户名查找承办方Id
	 * @param  UserKey
	 * @return OrgId
	 * @throws SQLException 
	 */
	public String getOrgIdByKey(Object key);
	
}
