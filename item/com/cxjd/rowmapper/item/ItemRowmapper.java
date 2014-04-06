package com.cxjd.rowmapper.item;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.cxjd.dao.RowMapper;
import com.cxjd.domain.item.Item;

/**
 * Item rowmapper
 * 
 *@author ÀîÌÚ³¬
 *@since 2013-08-29
 *
 */
public class ItemRowmapper implements RowMapper<Item>  {

	@Override
	public Item mapRow(ResultSet rs, int num) {
		Item item = new Item();
		try {
			item.setId(rs.getString("itemKey"));
			item.setItemName(rs.getString("itemName"));
			item.setItemCollege(rs.getString("itemCollege"));
			item.setItemTime(rs.getDate("itemTime"));
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return item;
	}

}
