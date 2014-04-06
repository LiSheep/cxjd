package com.cxjd.service.user;


import java.sql.SQLException;

import com.cxjd.domain.user.User;
import com.cxjd.service.IBaseService;

/**
 * role's service ��ӿڣ�����NULL��Ϊ��֤ʧ��
 * @author ���ڳ�
 * @since 2013-05-22
 *
 */
public interface IUserService extends IBaseService<User> {
	
	public User loginAuth(String account, String password);
	
	public String getRoleNameByUserKey(String key);
	
	public String getUserNameByUserKey(String key);
	
	/**
	 * �����Ƿ����ظ����û���
	 * @param  �û���
	 * @return ����trueΪ���ظ����û�����falseΪû���ظ��û���
	 * @throws SQLException 
	 */
	public boolean checkUserName(String userName);
	
	/**
	 * �����Ƿ����ظ��������ַ
	 * @param  �����ַ
	 * @return ����trueΪ���ظ��������ַ��falseΪû���ظ������ַ
	 * @throws SQLException 
	 */
	public boolean checkUserMail(String userMail);
	
	/**
	 * �����Ƿ����ظ��������ַ
	 * @param  �����ַ
	 * @return ����trueΪ���ظ��������ַ��falseΪû���ظ������ַ
	 * @throws SQLException 
	 */
	public boolean changepass(Object key, String oldpass, String newpass);
	
	/**
	 * �����û������ҳа췽Id
	 * @param  UserKey
	 * @return OrgId
	 * @throws SQLException 
	 */
	public String getOrgIdByKey(Object key);
	
}
