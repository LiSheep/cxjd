package com.cxjd.rowmapper.role;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.cxjd.dao.RowMapper;
import com.cxjd.domain.role.Role;

/**
 * role's rowmapper
 * @author ÀîÌÚ³¬
 * @since 2013-05-30
 *
 */
public class RoleRowmapper implements RowMapper<Role> {

	@Override
	public Role mapRow(ResultSet rs, int num) {
		Role item = new Role();
		try {
			item.setId(rs.getString("roleKey"));
			item.setRoleName(rs.getString("roleName"));
			item.setRoleAuth(rs.getInt("roleAuth"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return item;
	}

}
