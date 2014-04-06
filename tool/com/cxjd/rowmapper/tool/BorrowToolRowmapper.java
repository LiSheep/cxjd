package com.cxjd.rowmapper.tool;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.cxjd.dao.RowMapper;
import com.cxjd.domain.tool.BorrowTool;

/**
 * borrowTool row mapper
 * 
 *@author 李腾超
 *@since 2013-07-29
 *
 */
public class BorrowToolRowmapper implements RowMapper<BorrowTool> {

	@Override
	public BorrowTool mapRow(ResultSet rs, int num) {
		BorrowTool item = new BorrowTool();
		
		try {
			item.setId(rs.getString("broKey"));
			item.setToolKey(rs.getString("toolKey"));
			item.setBro_userKey(rs.getString("bro_userKey"));	//借阅者
			item.setBroDay(rs.getInt("broDay"));
			item.setBroTime(rs.getDate("broTime"));
			item.setBroNote(rs.getString("broNote"));
			item.setBroNum(rs.getInt("broNum"));
			item.setReturnTime(rs.getDate("returnTime"));
			item.setBroStatus(rs.getInt("broStatus"));
			
			item.setToolName(rs.getString("toolName"));
			item.setToolNum(rs.getInt("toolNum"));
			item.setToolNo(rs.getString("toolNo"));
			item.setToolPlace(rs.getInt("toolPlace"));
			item.setToolOut(rs.getInt("toolOut"));
			item.setUserKey(rs.getString("userKey")); 	//管理者
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return item;
	}

}
