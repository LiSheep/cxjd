package com.cxjd.service.tool;

import java.sql.SQLException;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import com.cxjd.common.Dictionary;
import com.cxjd.common.GuidGenerator;
import com.cxjd.dao.Pager;
import com.cxjd.domain.tool.BorrowTool;
import com.cxjd.domain.tool.Tool;
import com.cxjd.rowmapper.tool.BorrowToolRowmapper;
import com.cxjd.service.AbstractBaseService;
import com.sun.faces.renderkit.html_basic.HtmlBasicRenderer.Param;


/**
 * borrowTool service层
 * 
 *@author 李腾超
 *@since 2013-07-29
 *
 */
public class BorrowToolService extends AbstractBaseService<BorrowTool> implements IBorrowToolService {

	@Override
	public boolean create(BorrowTool param) {
		String sql = "INSERT INTO c_borrow(broKey, toolKey, userKey, broTime, returnTime, broDay, broNote, broNum, broStatus) VALUES(?,?,?,?,?,?,?,?,?)";
		Object[] args = { GuidGenerator.getGuid(), param.getToolKey(), param.getBro_userKey(),
				param.getBroTime(), param.getReturnTime(), param.getBroDay(), 
				param.getBroNote(), param.getBroNum(), param.getBroStatus() };
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
	public boolean update(BorrowTool param) {
		String sql = "UPDATE c_borrow SET " +
				"toolKey = ?, userKey =?, broTime = ?, returnTime = ?, broDay =?, " +
				"broNote =?, broNum = ?, broStatus = ?  WHERE broKey=? ";
		Object [] args = { param.getToolKey(), param.getBro_userKey(), param.getBroTime(),
						param.getReturnTime(), param.getBroDay(), param.getBroNote(), param.getBroNum(), 
						param.getBroStatus(), param.getId()};
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
		String sql = "DELETE FROM c_borrow WHERE broKey =?";
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
	public BorrowTool readUnique(Object key) {
		String sql = "SELECT b.broKey, b.toolKey, b.userKey bro_userKey, b.broTime, b.returnTime, b.broDay, b.broNote, b.broNum, b.broStatus, t.toolName, t.toolNum, t.toolNo, t.toolPlace, t.toolOut, t.userKey  FROM c_borrow b, c_tool t WHERE b.toolKey = t.toolKey and broKey =? ";
		Object[]args = {key};
		BorrowTool item = new BorrowTool();
		try {
			item = getEntityDao().exeQuery(sql, new BorrowToolRowmapper(), args).get(0);
		} catch (Exception e) {
			return null;
		}
		
		return item;
	}

	@Override
	public List<BorrowTool> read(Object[] args) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BorrowTool> readAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BorrowTool> readWithPage(Pager<BorrowTool> pager, Object[] args) {
		String appendSql = "";
		if(args != null){
			appendSql = "and b.userKey = ?";
		}
		
		String sql = "SELECT b.broKey, b.toolKey, b.userKey bro_userKey, b.broTime, b.returnTime, b.broDay, b.broNote, b.broNum, b.broStatus, t.toolName, t.toolNum, t.toolNo, t.toolPlace, t.toolOut, t.userKey  FROM c_borrow b, c_tool t WHERE b.toolKey = t.toolKey ORDER BY broTime " + appendSql;
		String countSql = "SELECT COUNT(*) FROM c_borrow b, c_tool t WHERE b.toolKey = t.toolKey " + appendSql;
		List<BorrowTool> list = Collections.emptyList();
		try {
			list = getEntityDao().exeQueryPager(sql, new BorrowToolRowmapper(), args, pager,countSql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public BorrowTool getToolInfoByToolKey(Object key) {
		IToolService toolService = new ToolService();
		BorrowTool borrowTool = new BorrowTool();
		Tool tool = toolService.readUnique(key);
		
		borrowTool.setToolKey(tool.getId());
		borrowTool.setToolName(tool.getToolName());
		borrowTool.setToolNum(tool.getToolNum());
		borrowTool.setToolNo(tool.getToolNo());
		borrowTool.setToolOut(tool.getToolOut());
		borrowTool.setToolPlace(tool.getToolPlace());
		borrowTool.setUserKey(tool.getUserKey());
		
		return borrowTool;
	}

	
	
	@Override
	public BorrowTool getUnCheckInfo() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BorrowTool> readAllWithPager(Pager<BorrowTool> pager, Object[] args) {
		
		String sql = "SELECT b.broKey, b.toolKey, b.userKey bro_userKey, b.broTime, b.returnTime, b.broDay, b.broNote, b.broNum, b.broStatus, t.toolName, t.toolNum, t.toolNo, t.toolPlace, t.toolOut, t.userKey  FROM c_borrow b, c_tool t  WHERE b.toolKey = t.toolKey ORDER BY broTime  ";
		String countSql = "SELECT COUNT(*) FROM c_borrow b, c_tool t WHERE b.toolKey = t.toolKey ";
		List<BorrowTool> list = Collections.emptyList();
		try {
			list = getEntityDao().exeQueryPager(sql, new BorrowToolRowmapper(), args, pager,countSql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public boolean outBorrowTool(BorrowTool item) {
		BorrowTool readItem = this.readUnique(item.getId());
		if(item.getBroNum() > readItem.getToolNum()){	//借出数量超过库存
			return false;
		}else {
			readItem.setToolOut(readItem.getToolOut() + item.getBroNum() );
			readItem.setBroStatus(Dictionary.check);
			readItem.setBroTime(new Date());
			readItem.setBroDay(item.getBroDay());
			readItem.setBroNote(item.getBroNote());
			readItem.setBroNum(item.getBroNum());
			this.updateOutToolCount(readItem.getToolKey(), readItem.getToolOut());
			return this.update(readItem);
		}
	}

	@Override
	public boolean backBorrowTool(Object key) {
		BorrowTool item = this.readUnique(key);
		item.setToolOut(item.getToolOut() - item.getBroNum() );
		item.setBroStatus(Dictionary.isReturn);
		item.setReturnTime(new Date());
		this.updateOutToolCount(item.getToolKey(), item.getToolOut());
		return this.update(item);
	}

	@Override
	public boolean refuseBorrowTool(Object key) {
		BorrowTool item = this.readUnique(key);
		item.setBroStatus(Dictionary.refuse);
		return this.update(item);
	}
	
	@Override
	public boolean updateOutToolCount(String key, int count) {
		String sql = "UPDATE c_tool SET toolOut = ? WHERE toolKey=? ";
		Object[]args = {count, key};
		boolean res = false;
		try {
			res = (getEntityDao().exeUpdate(sql, args) > 0);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
	}
}
