package com.cxjd.service.user;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

import com.cxjd.common.GuidGenerator;
import com.cxjd.dao.Pager;
import com.cxjd.domain.user.User;
import com.cxjd.rowmapper.user.UserRowmapper;
import com.cxjd.service.AbstractBaseService;
import com.cxjd.service.role.RoleService;

/**
 * user's service 
 * @author ÀîÌÚ³¬
 * @since 2013-05-22
 *
 */
public class UserService extends AbstractBaseService<User> implements IUserService {

	private RoleService roleService = new RoleService();
	
	@Override
	public boolean create(User param) {
		String sql = "INSERT INTO c_user(userKey, userName, userMail, userPassword, gender, phone, roleKey, orgId, state) VALUES(?,?,?,?,?,?,?,?,?)";
		Object[] args = { GuidGenerator.getGuid(), param.getUserName(), param.getUserMail(), 
				param.getUserPass(), param.isGender(), param.getPhone(), param.getRoleKey(),
				param.getOrgId(), param.isState() };
		int result = 0;
		try {
			result = getEntityDao().exeUpdate(sql, args);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result >0;
	}

	@Override
	public boolean update(User param) {
		String sql = "UPDATE c_user SET userName =?, userMail = ?, userPassword=?,  gender =?, phone=?, roleKey=?, orgId=?, state=? WHERE userKey=?";
		Object [] args = {param.getUserName(), param.getUserMail(),  param.getUserPass(), param.isGender() , param.getPhone() ,param.getRoleKey(), param.getOrgId(), param.isState(), param.getId()};
		int result = 0;
		try {
			result  = getEntityDao().exeUpdate(sql, args);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result > 0;
	}

	@Override
	public List<User> readAll() {
		List<User> list = Collections.emptyList();
		String sql = "select * from c_user";
		try {
			list = getEntityDao().exeQuery(sql, new UserRowmapper(), null);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<User> readWithPage(Pager<User> pager, Object[] args) {
		String sql = "select * from c_user ";
		String countSql = "select count(*) from c_user ";
		List<User> list = Collections.emptyList();
		try {
			list = getEntityDao().exeQueryPager(sql, new UserRowmapper(), null, pager,countSql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public boolean delete(Object[] keys) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteUnique(Object key) {
		String sql = "DELETE FROM c_user WHERE userKey =?";
		Object[] args ={key};
		int result = 0;
		try {
			result = getEntityDao().exeUpdate(sql, args);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result > 0;
	}

	@Override
	public User readUnique(Object key) {
		String sql = "SELECT * FROM c_user WHERE userKey =? ";
		Object[]args = {key};
		User user = new User();
		try {
			user = getEntityDao().exeQuery(sql, new UserRowmapper(), args).get(0);
		} catch (Exception e) {
			return null;
		}
		
		return user;
	}

	@Override
	public List<User> read(Object[] args) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User loginAuth(String account, String password) {
		String sql;
		if(account.matches("^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$")){//Ê¹ÓÃÓÊÏäµÇÂ¼
			sql = "SELECT * FROM c_user WHERE userMail = ? AND userPassword = ? ";
		}
		else {
			sql = "SELECT * FROM c_user WHERE userName = ? AND userPassword = ? ";
		}
		Object[]args = { account, password };
		User user = new User();
		try {
			user = getEntityDao().exeQuery(sql, new UserRowmapper(), args).get(0);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return null;	//µÇÂ¼´íÎó£¬»á±¨´í
			//e.printStackTrace();
		}
		return user;
	}

	@Override
	public String getRoleNameByUserKey(String key) {
		String roleKey = this.readUnique(key).getRoleKey();	
		roleService.readUnique(roleKey);
		return roleService.readUnique(roleKey).getRoleName();
	}

	@Override
	public String getUserNameByUserKey(String key) {
		return this.readUnique(key).getUserName();
	}

	@Override
	public boolean checkUserName(String userName) {
		String sql = "SELECT COUNT(*) FROM c_user WHERE userName = ?";
		Object []args = {userName};
		try {
			return (getEntityDao().countSize(sql, args) > 0);
		} catch (SQLException e) {
			return true;
		}
	}

	@Override
	public boolean checkUserMail(String userMail) {
		String sql = "SELECT COUNT(*) FROM c_user WHERE userMail = ?";
		Object []args = {userMail};
		try {
			return (getEntityDao().countSize(sql, args) > 0);
		} catch (SQLException e) {
			return true;
		}
	}

	@Override
	public boolean changepass(Object key, String oldpass, String newpass) {
		String sql = "UPDATE c_user SET userPassword = ? WHERE userPassword = ? AND userKey = ?";
		Object []args ={newpass, oldpass, key};
		int result = 0;
		try {
			result = getEntityDao().exeUpdate(sql, args);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result > 0;
	}

	@Override
	public String getOrgIdByKey(Object key) {
		return this.readUnique(key).getOrgId();
	}
	
	
}
