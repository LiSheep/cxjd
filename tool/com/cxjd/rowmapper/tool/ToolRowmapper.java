package com.cxjd.rowmapper.tool;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.cxjd.dao.RowMapper;
import com.cxjd.domain.tool.Tool;


/**
 * tool row mapper
 * 
 *@author ÀîÌÚ³¬
 *@since 2013-07-23
 *
 */
public class ToolRowmapper implements RowMapper<Tool>{

	@Override
	public Tool mapRow(ResultSet rs, int num) {
		Tool item = new Tool();
		
		try {
			item.setId(rs.getString("toolKey"));
			item.setToolName(rs.getString("toolName"));
			item.setToolNum(rs.getInt("toolNum"));
			item.setToolNo(rs.getString("toolNo"));
			item.setToolPlace(rs.getInt("toolPlace"));
			item.setToolOut(rs.getInt("toolOut"));
			item.setUserKey(rs.getString("userKey"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return item;
	}

}
