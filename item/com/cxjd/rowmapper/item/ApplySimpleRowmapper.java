package com.cxjd.rowmapper.item;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.cxjd.dao.RowMapper;
import com.cxjd.domain.item.Apply;
import com.cxjd.domain.item.Item;

/**
 * Apply rowmapper for 成绩管理
 * 
 *@author 李腾超
 *@since 2013-08-29
 *
 */
public class ApplySimpleRowmapper implements RowMapper<Apply> {

	@Override
	public Apply mapRow(ResultSet rs, int num) {
		
		Apply apply = new Apply();
		apply.setItem(new Item());
		
		try {
			apply.getItem().setItemName(rs.getString("itemName"));
			
			apply.setId(rs.getString("applyKey"));
			apply.setApplyName(rs.getString("applyName"));
			apply.setApplyMark(rs.getString("applyMark"));
			apply.setApplyStatus(rs.getInt("applyStatus"));
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return apply;
	}

}
