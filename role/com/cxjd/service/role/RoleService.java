package com.cxjd.service.role;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

import com.cxjd.common.GuidGenerator;
import com.cxjd.dao.Pager;
import com.cxjd.domain.role.Role;
import com.cxjd.rowmapper.role.RoleRowmapper;
import com.cxjd.service.AbstractBaseService;

/**
 * role's service 
 * @author ÀîÌÚ³¬
 * @since 2013-05-30
 *
 */
public class RoleService extends AbstractBaseService<Role> implements IRoleService {

	@Override
	public boolean create(Role param) {
		String sql = "INSERT INTO c_role (roleKey, roleName, roleAuth) VALUES(?,?,?)";
		Object [] args = {GuidGenerator.getGuid(), param.getRoleName(), param.getRoleAuth() };
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
	public boolean update(Role param) {
		String sql = "UPDATE c_role SET roleName =?, roleAuth=? WHERE roleKey=?";
		Object [] args = {param.getRoleName(), param.getRoleAuth(),param.getId()};
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
	public boolean delete(Object[] keys) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteUnique(Object key) {
		String sql = "DELETE FROM c_role WHERE roleKey =?";
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
	public Role readUnique(Object key) {
		String sql = "SELECT * FROM c_role WHERE roleKey =? ";
		Object[]args = {key};
		Role role = new Role();
		try {
			role = getEntityDao().exeQuery(sql, new RoleRowmapper(), args).get(0);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return role;
	}

	@Override
	public List<Role> read(Object[] args) {
		String sql = "SELECT * FROM c_role ";
		List<Role> roles = null;
		try {
			roles = getEntityDao().exeQuery(sql, new RoleRowmapper(), args);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return roles;
	}

	@Override
	public List<Role> readAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Role> readWithPage(Pager<Role> pager, Object[] args) {
		String sql = "select * from c_role ";
		String countSql = "select count(*) from c_role ";
		List<Role> list = Collections.emptyList();
		try {
			list = getEntityDao().exeQueryPager(sql, new RoleRowmapper(), null, pager,countSql);
		} catch (SQLException e) {
			// TODO Auto-generated method stub
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public Role getRoleTypeById(String id) {
		String sql = "SELECT * FROM c_role WHERE roleKey = ?";
		Object[]args = {id};
		Role role = new Role();
		try {
			role = getEntityDao().exeQuery(sql, new RoleRowmapper(), args).get(0);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return role;
	}

}
