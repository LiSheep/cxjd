package com.cxjd.finshtest.dao;

import java.sql.SQLException;
import java.util.List;

import com.cxjd.dao.JdbcDao;
import com.cxjd.dao.Pager;
import com.cxjd.domain.user.User;
import com.cxjd.finshtest.dao.domain.UserRowMapper;
import com.cxjd.service.user.UserService;

public class testMain {

	public static void main(String[] args) throws SQLException {
		JdbcDao<User> dao = new JdbcDao<User>();
		//String sql = "select * from c_user where userName = ? ";
		//Object[] obj = {"123"};
		//Pager<User> pager = new Pager<User>(2);
		//pager.setPageNo(2);//µ±Ç°Ò³Êý
//		for (User user : users) {
//			System.out.println(user.getId());
//		}
		
		//JdbcDao<User> dao2 = new JdbcDao<User>();
		//System.out.println(dao2.countSize("", args));
		
		Object [][]arg = { {1,2,3}, {1,2,3} };
		for (Object[] objects : arg) {
			for (Object object : objects) {
				System.out.println(object);
			}
		}
		
	}
	
	
}
