package com.cxjd.service.item;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

import com.cxjd.common.GuidGenerator;
import com.cxjd.dao.Pager;
import com.cxjd.domain.item.Item;
import com.cxjd.domain.role.Role;
import com.cxjd.rowmapper.item.ItemRowmapper;
import com.cxjd.rowmapper.role.RoleRowmapper;
import com.cxjd.service.AbstractBaseService;
import com.cxjd.service.organizer.OrganizerService;

/**
 * Item serveice
 * 
 *@author ÀîÌÚ³¬
 *@since 2013-08-29
 *
 */
public class ItemService extends AbstractBaseService<Item> implements IItemService {

	@Override
	public boolean create(Item param) {
		String sql = "INSERT INTO c_item(itemKey, itemName, itemTime, orgId, itemCollege) VALUES(?,?,?,?,?)";
		OrganizerService organizerService = new OrganizerService();
		param.setItemCollege(organizerService.readUnique(param.getOrgId()).getOrgName());
		Object[] args = { GuidGenerator.getGuid(),  param.getItemName(),
				param.getItemTime(),param.getOrgId(), param.getItemCollege() };
		int result = 0;
		try {
			result = getEntityDao().exeUpdate(sql, args);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result >0;
	}

	@Override
	public boolean update(Item param) {
		String sql = "UPDATE c_item SET itemName =?, itemTime=?, itemCollege=? WHERE itemKey=?";
		Object [] args = {param.getItemName(), param.getItemTime(),param.getItemCollege(),param.getId()};
		int result = 0;
		try {
			result  = getEntityDao().exeUpdate(sql, args);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result > 0;
	}

	@Override
	public boolean delete(Object[] keys) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteUnique(Object key) {
		String sql = "DELETE FROM c_item WHERE itemKey =?";
		Object[] args ={key};
		int result = 0;
		try {
			result = getEntityDao().exeUpdate(sql, args);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result > 0;
	}

	@Override
	public Item readUnique(Object key) {
		String sql = "SELECT * FROM c_item WHERE itemKey =? ";
		Object[]args = {key};
		Item item = new Item();
		try {
			List<Item> tmp = getEntityDao().exeQuery(sql, new ItemRowmapper(), args);
			if(tmp != null && tmp.size()>0)
			item = tmp.get(0);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return item;
	}

	@Override
	public List<Item> read(Object[] args) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Item> readAll() {
		String sql = "select * from c_item order by itemTime DESC ";
		List<Item> list = null;
		try {
			list = getEntityDao().exeQuery(sql, new ItemRowmapper(), null);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<Item> readWithPage(Pager<Item> pager, Object[] args) {
		List<Item> list = null;
		if(args == null){
			String sql = "select * from c_item order by itemTime DESC ";
			String countSql = "select count(*) from c_item";
			try {
				list = getEntityDao().exeQueryPager(sql, new ItemRowmapper(), args, pager,countSql);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else {
			String sql = "select * from c_item where orgId = ? order by itemTime DESC  ";
			String countSql = "select count(*) from c_item where orgId = ?";
			try {
				list = getEntityDao().exeQueryPager(sql, new ItemRowmapper(), args, pager,countSql);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return list;
	}

	@Override
	public Item ReadByName(String itemName) {
		String sql = "SELECT * FROM c_item WHERE itemName = ?";
		Object[] args ={itemName};
		Item item = null;
		try {
			List<Item> rItems = getEntityDao().exeQuery(sql, new ItemRowmapper(), args);
			if(rItems != null && rItems.size() != 0){
				item = getEntityDao().exeQuery(sql, new ItemRowmapper(), args).get(0);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return item;
	}
	

}
