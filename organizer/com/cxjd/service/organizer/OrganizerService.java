package com.cxjd.service.organizer;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

import com.cxjd.common.GuidGenerator;
import com.cxjd.dao.Pager;
import com.cxjd.domain.organizer.Organizer;
import com.cxjd.domain.role.Role;
import com.cxjd.rowmapper.organizer.OrganizerRowmapper;
import com.cxjd.rowmapper.role.RoleRowmapper;
import com.cxjd.service.AbstractBaseService;

public class OrganizerService extends AbstractBaseService<Organizer> implements IOrganizerService{

	@Override
	public boolean create(Organizer param) {
		String sql = "INSERT INTO c_organizer (orgId, orgName) VALUES(?,?)";
		Object [] args = {GuidGenerator.getGuid(), param.getOrgName() };
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
	public boolean update(Organizer param) {
		String sql = "UPDATE c_organizer SET orgName =? WHERE orgId=?";
		Object [] args = {param.getOrgName(), param.getId()};
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
		String sql = "DELETE FROM c_organizer WHERE orgId =?";
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
	public Organizer readUnique(Object key) {
		String sql = "SELECT * FROM c_organizer WHERE orgId =? ";
		Object[]args = {key};
		Organizer item = new Organizer();
		try {
			item = getEntityDao().exeQuery(sql, new OrganizerRowmapper(), args).get(0);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return item;
	}

	@Override
	public List<Organizer> read(Object[] args) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Organizer> readAll() {
		String sql = "SELECT * FROM c_organizer ";
		List<Organizer> item = null;
		try {
			item = getEntityDao().exeQuery(sql, new OrganizerRowmapper(), null);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return item;
	}

	@Override
	public List<Organizer> readWithPage(Pager<Organizer> pager, Object[] args) {
		String sql = "select * from c_organizer ";
		String countSql = "select count(*) from c_organizer ";
		List<Organizer> list = Collections.emptyList();
		try {
			list = getEntityDao().exeQueryPager(sql, new OrganizerRowmapper(), null, pager,countSql);
		} catch (SQLException e) {
			// TODO Auto-generated method stub
			e.printStackTrace();
		}
		return list;
	}

}
