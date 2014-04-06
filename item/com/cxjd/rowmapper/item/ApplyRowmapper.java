package com.cxjd.rowmapper.item;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.cxjd.dao.RowMapper;
import com.cxjd.domain.item.Apply;
import com.cxjd.domain.item.Claimer;
import com.cxjd.domain.item.Item;

/**
 * Apply rowmapper
 * 
 *@author ÀîÌÚ³¬
 *@since 2013-08-29
 *
 */
public class ApplyRowmapper implements RowMapper<Apply> {

	@Override
	public Apply mapRow(ResultSet rs, int num) {
		
		Apply apply = new Apply();
		apply.setItem(new Item());
		apply.setMainClaimer(new Claimer());
		apply.setOtherCliamers(new ArrayList<Claimer>());
		
		try {
			apply.getItem().setId(rs.getString("itemKey"));
			apply.getItem().setItemName(rs.getString("itemName"));
			apply.getItem().setItemCollege(rs.getString("itemCollege"));
			apply.getItem().setItemTime(rs.getDate("itemTime"));
			
			apply.setId(rs.getString("applyKey"));
			apply.setApplyName(rs.getString("applyName"));
			apply.setApplyStatus(rs.getInt("applyStatus"));
			
			apply.setApplyUserKey(rs.getString("userKey"));
			
			apply.setSfileKey(rs.getString("SfileKey"));
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return apply;
	}

}
