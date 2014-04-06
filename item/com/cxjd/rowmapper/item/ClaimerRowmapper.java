package com.cxjd.rowmapper.item;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.cxjd.dao.RowMapper;
import com.cxjd.domain.item.Claimer;


/**
 * Claimer rowmapper
 * 
 *@author ÀîÌÚ³¬
 *@since 2013-08-29
 *
 */
public class ClaimerRowmapper implements RowMapper<Claimer>{

	@Override
	public Claimer mapRow(ResultSet rs, int num) {
		Claimer item = new Claimer();
		try {
			item.setId(rs.getString("claimerKey"));
			item.setClaimerName(rs.getString("claimerName"));
			item.setClaimerSex(rs.getBoolean("claimerSex"));
			item.setClaimerCollege(rs.getString("claimerCollege"));
			item.setClaimerPro(rs.getString("claimerPro"));
			item.setClaimerClass(rs.getString("claimerClass"));
			item.setClaimerNo(rs.getString("claimerNo"));
			item.setClaimerType(rs.getInt("claimerType"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return item;
	}

	
}
