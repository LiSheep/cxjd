package com.cxjd.finshtest.dao.domain;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.cxjd.dao.RowMapper;
import com.cxjd.domain.user.User;

public class UserRowMapper  implements RowMapper<User>{

	@Override
	public User mapRow(ResultSet rs, int num) {
		User item = new User();
		try {
			item.setId(rs.getString("userKey"));
			item.setUserName(rs.getString("userName"));
			item.setUserPass(rs.getString("userPassword"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return item;
	}

}
