package com.cxjd.rowmapper.item;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.cxjd.dao.RowMapper;
import com.cxjd.domain.item.Apply;
import com.cxjd.domain.item.Claimer;
import com.cxjd.domain.item.Item;

public class ApplyRowMapperToXls implements RowMapper<Apply> {

	@Override
	public Apply mapRow(ResultSet rs, int num) {
		Apply apply = new Apply();
		apply.setItem(new Item());
		apply.setMainClaimer(new Claimer());
		apply.setOtherCliamers(new ArrayList<Claimer>());
		
		try {
			apply.getItem().setItemName(rs.getString("itemName"));
			apply.getItem().setItemCollege(rs.getString("itemCollege"));
			
			apply.setId(rs.getString("applyKey"));
			apply.setApplyName(rs.getString("applyName"));
			apply.setApplyStatus(rs.getInt("applyStatus"));
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return apply;
	}

}
