package com.cxjd.service.tool;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

import com.cxjd.common.GuidGenerator;
import com.cxjd.dao.Pager;
import com.cxjd.domain.tool.Tool;
import com.cxjd.rowmapper.tool.ToolRowmapper;
import com.cxjd.service.AbstractBaseService;

/**
 * tool service²ã
 * 
 *@author ÀîÌÚ³¬
 *@since 2013-07-23
 *
 */
public class ToolService extends AbstractBaseService<Tool> implements IToolService {

	@Override
	public boolean create(Tool param) {
		String sql = "INSERT INTO c_tool(toolKey, toolName, toolNum, toolNo, toolPlace, toolOut, userKey) VALUES(?,?,?,?,?,0,?)";
		Object[] args = { GuidGenerator.getGuid(),  param.getToolName(),
				param.getToolNum(),param.getToolNo(), param.getToolPlace(), param.getUserKey() };
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
	public boolean update(Tool param) {
		String sql = "UPDATE c_tool SET toolName = ?, " +
				"toolNum=?, toolNo =?, toolPlace=?,toolOut = ?, userKey =? WHERE toolKey=? ";
		Object [] args = { param.getToolName(), param.getToolNum(),
				param.getToolNo(), param.getToolPlace(),param.getToolOut(), param.getUserKey(), param.getId()};
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
		String sql = "DELETE FROM c_tool WHERE toolKey =?";
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
	public Tool readUnique(Object key) {
		String sql = "SELECT * FROM c_tool WHERE toolKey =? ";
		Object[]args = {key};
		Tool item = new Tool();
		try {
			item = getEntityDao().exeQuery(sql, new ToolRowmapper(), args).get(0);
		} catch (Exception e) {
			return null;
		}
		
		return item;
	}

	@Override
	public List<Tool> read(Object[] args) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Tool> readAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Tool> readWithPage(Pager<Tool> pager, Object[] args) {
		String sql = "select * from c_tool ";
		String countSql = "select count(*) from c_tool " ;
		List<Tool> list = Collections.emptyList();
		try {
			list = getEntityDao().exeQueryPager(sql, new ToolRowmapper(), args, pager,countSql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

}
