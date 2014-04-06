package com.cxjd.rowmapper.organizer;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.cxjd.dao.RowMapper;
import com.cxjd.domain.organizer.Organizer;

/**
 * organizer's rowmapper
 * @author ÀîÌÚ³¬
 * @since 2014-01-17
 *
 */
public class OrganizerRowmapper implements RowMapper<Organizer> {

	@Override
	public Organizer mapRow(ResultSet rs, int num) {
		Organizer item = new Organizer();
		try {
			item.setId(rs.getString("orgId"));
			item.setOrgName(rs.getString("orgName"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return item;
	}

}
