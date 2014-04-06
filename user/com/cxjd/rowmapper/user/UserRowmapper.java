package com.cxjd.rowmapper.user;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.cxjd.dao.RowMapper;
import com.cxjd.domain.user.User;

/**
 * user's row mapper
 * @author ÀîÌÚ³¬
 * @since 2013-05-22
 *
 */
public class UserRowmapper implements RowMapper<User> {

	@Override
	public User mapRow(ResultSet rs, int num) {
		User item = new User();
		
		try {
			item.setId(rs.getString("userKey"));
			item.setUserName(rs.getString("userName"));
			item.setUserMail(rs.getString("userMail"));
			item.setUserPass(rs.getString("userPassword"));
			item.setGender(rs.getBoolean("gender"));
			item.setPhone(rs.getString("phone"));
			item.setState(rs.getBoolean("state"));
			item.setRoleKey(rs.getString("roleKey"));
			item.setOrgId(rs.getString("orgId"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return item;
	}

}
