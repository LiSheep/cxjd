package com.cxjd.rowmapper.sfile;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.cxjd.dao.RowMapper;
import com.cxjd.domain.sfile.Sfile;

/**
 * share file row mapper²ã
 * 
 *@author ÀîÌÚ³¬
 *@since 2013-10-05
 *
 */
public class SfileRowmapper implements RowMapper<Sfile> {

	@Override
	public Sfile mapRow(ResultSet rs, int num) {
		Sfile item = new Sfile();
		try {
			item.setId(rs.getString("SfileKey"));
			item.setSfileTitle(rs.getString("SfileTitle"));
			item.setSfileComment(rs.getString("SfileComment"));
			item.setSfileOldName(rs.getString("SfileOldName"));
			item.setSfileNewName(rs.getString("SfileNewName"));
			item.setSfileDate(rs.getDate("SfileDate"));
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		
		return item;
	}

}
