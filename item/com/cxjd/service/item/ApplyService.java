package com.cxjd.service.item;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.cxjd.common.Dictionary;
import com.cxjd.common.GuidGenerator;
import com.cxjd.dao.Pager;
import com.cxjd.domain.item.Apply;
import com.cxjd.domain.item.Claimer;
import com.cxjd.rowmapper.item.ApplyRowMapperToXls;
import com.cxjd.rowmapper.item.ApplyRowmapper;
import com.cxjd.rowmapper.item.ApplySimpleRowmapper;
import com.cxjd.rowmapper.item.ClaimerRowmapper;
import com.cxjd.service.AbstractBaseService;

/**
 * Apply service
 * 
 *@author 李腾超
 *@since 2013-08-29
 *
 */
public class ApplyService  extends AbstractBaseService<Apply> implements IApplyService  {

	@Override
	public boolean create(Apply param) {
		param.setId(GuidGenerator.getGuid());
		String sql = "INSERT INTO c_apply(applyKey, itemKey, userKey, applyName, applyStatus) VALUES(?,?,?,?,?)";
		Object[] args = { param.getId(), param.getItem().getId(), param.getApplyUserKey(), param.getApplyName(),
				0 };
		int result = 0;
		
		String sql2 = "INSERT INTO c_claimer(claimerKey, claimerName, claimerSex, claimerCollege, claimerPro, claimerClass, claimerNo, claimerType, applyKey) VALUES(?,?,?,?,?,?,?,?,?)";
		Object[] args2 = { param.getId(), param.getMainClaimer().getClaimerName(), param.getMainClaimer().isClaimerSex(), param.getMainClaimer().getClaimerCollege(),
		param.getMainClaimer().getClaimerPro(), param.getMainClaimer().getClaimerClass(), param.getMainClaimer().getClaimerNo(), param.getMainClaimer().getClaimerType(), param.getId()};
		
		try {
			result = getEntityDao().exeUpdate(sql, args);
			if(result > 0){
				result = getEntityDao().exeUpdate(sql2, args2);
			}else{
				return false;
			}
			if(result > 0){
				result = insertOtherClaimers(param.getOtherCliamers(), param.getId());
			}else {
				return false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return result >0;
	}
	
	private int insertOtherClaimers(List<Claimer> otherClaimers, String applyKey){
		int result = 0;
		for (Claimer claimer : otherClaimers) {
			String sql2 = "INSERT INTO c_claimer(claimerKey, claimerName, claimerSex, claimerCollege, claimerPro, claimerClass, claimerNo, claimerType, applyKey) VALUES(?,?,?,?,?,?,?,?,?)";
			Object[] args2 = { GuidGenerator.getGuid(), claimer.getClaimerName(), claimer.isClaimerSex(), claimer.getClaimerCollege(),
					claimer.getClaimerPro(), claimer.getClaimerClass(), claimer.getClaimerNo(), claimer.getClaimerType(), applyKey};
			try {
				result = getEntityDao().exeUpdate(sql2, args2);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return 0;
			}
		}
		
		return result;
	}

	@Override
	public boolean update(Apply param) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Object[] keys) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteUnique(Object key) {
		String sql = "DELETE FROM c_apply WHERE applyKey =?";
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
	public Apply readUnique(Object key) {
		String sql = "SELECT i.itemkey, i.itemName, i.itemTime, i.itemCollege, a.applyKey, a.userKey, a.applyName, a.applyStatus FROM c_apply AS a  INNER JOIN c_item AS i ON i.itemkey = a.itemKey  WHERE a.applyKey = ?";
		Object[]args = {key};
		Apply apply = null;
		try {
			List<Apply> list = getEntityDao().exeQuery(sql, new ApplyRowmapper(), args);
			if(list.size() > 0)
				apply = list.get(0);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		return apply;
	}

	@Override
	public List<Apply> read(Object[] args) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Apply> readAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Apply> readWithPage(Pager<Apply> pager, Object[] args) {
		String sql = "SELECT i.itemkey, i.itemName, i.itemTime, i.itemCollege, a.applyKey, a.userKey, a.applyName, a.applyStatus FROM c_item  AS i INNER JOIN c_apply AS a ON i.itemkey = a.itemKey";
		String countSql = "select count(*) FROM c_item  AS i INNER JOIN c_apply AS a ON i.itemkey = a.itemKey";
		List<Apply> list = Collections.emptyList();
		try {
			list = getEntityDao().exeQueryPager(sql, new ApplyRowmapper(), args, pager,countSql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	@Override
	public Apply readApplyByItemFK(Object itemKey){
		String sql = "SELECT i.itemkey, i.itemName, i.itemTime, i.itemCollege, a.applyKey, a.userKey, a.applyName, a.applyStatus, a.SFileKey FROM c_item AS i LEFT OUTER JOIN c_apply AS a ON i.itemkey = a.itemKey  WHERE i.itemKey = ?";
		Object[]args = {itemKey};
		Apply apply = new Apply();
		try {
			apply = getEntityDao().exeQuery(sql, new ApplyRowmapper(), args).get(0);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return apply;
	}
	
	public List<Apply> readApplyByUserKeyWithPage(Pager<Apply> pager, Object userKey){
		String sql = "SELECT i.itemkey, i.itemName, i.itemTime, i.itemCollege, a.applyKey, a.userKey, a.applyName, a.applyStatus, a.SfileKey FROM c_item  AS i INNER JOIN c_apply AS a  ON i.itemkey = a.itemKey  WHERE a.userKey = ?";
		String countSql = "select count(*) FROM c_item  AS i INNER JOIN c_apply AS a  ON i.itemkey = a.itemKey WHERE a.userKey = ?";
		Object[]args = {userKey};
		List<Apply> list = Collections.emptyList();
		try {
			list = getEntityDao().exeQueryPager(sql, new ApplyRowmapper(), args, pager,countSql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	@Override
	public boolean UpdateApplyStatus(int applyStatus, Object key) {
		String sql = "UPDATE c_apply SET applyStatus = ? WHERE applyKey=? ";
		Object [] args = { applyStatus, key};
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
	public Apply readSingleApplySimple(Object key) {
		String sql ="SELECT a.applyKey, a.applyName, a.applyMark, i.itemName, a.applyStatus FROM c_apply AS a INNER JOIN c_item AS i ON a.itemKey = i.itemkey WHERE a.applyKey = ?";
		Object []args = {key};
		Apply apply = null;
		try {
			List<Apply> list = getEntityDao().exeQuery(sql, new ApplySimpleRowmapper(), args);
			if(list.size() > 0)
				apply = list.get(0);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		return apply;
	}

	@Override
	public boolean updateApplyMark(String mark, Object key) {
		String sql = "UPDATE c_apply SET applyMark = ? WHERE applyKey=? ";
		Object [] args = { mark, key};
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
	public List<Apply> readSimpleApply4MarkWithPage(Pager<Apply> pager) {
		String sql = "SELECT a.applyKey, a.applyName, a.applyMark, i.itemName, a.applyStatus FROM c_apply AS a INNER JOIN c_item AS i ON i.itemkey = a.itemKey WHERE a.applyStatus = ? OR a.applyStatus = ?";
		String countSql = "select count(*) FROM c_apply AS a INNER JOIN c_item AS i ON i.itemkey = a.itemKey WHERE a.applyStatus = ? OR a.applyStatus = ?";
		Object[]args = {Dictionary.applyStatus_mark, Dictionary.applyStatus_pass};
		List<Apply> list = Collections.emptyList();
		try {
			list = getEntityDao().exeQueryPager(sql, new ApplySimpleRowmapper(), args, pager,countSql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<Apply> readWithPageByArgs(Pager<Apply> pager, int applyStatus,
			String itemName) {
		
		List<String> argsList = new ArrayList<String>();
		List<Apply> results = new ArrayList<Apply>();
		
		StringBuilder sql = new StringBuilder("SELECT i.itemkey, i.itemName, i.itemTime, i.itemCollege, a.applyKey, a.userKey, a.applyName, a.applyStatus,a.SfileKey FROM c_item  AS i INNER JOIN c_apply AS a ON i.itemkey = a.itemKey  WHERE 1=1 ");
		StringBuilder countSql = new StringBuilder("select count(*) FROM c_item  AS i INNER JOIN c_apply AS a ON i.itemkey = a.itemKey WHERE 1=1 ");
		if(applyStatus != -1){
			argsList.add(String.valueOf(applyStatus));
			countSql.append(" AND applyStatus = ?");
			sql.append(" AND applyStatus = ?");
		}
		if(itemName != null && !itemName.equals("")){
			argsList.add(itemName);
			sql.append(" AND i.itemKey = ?");
			countSql.append(" AND i.itemKey = ?");
		}
		
		Object [] args;
 		if(argsList.size() == 0)
			args = null;
		else {
			args = argsList.toArray();
		}
		
		try {
			results = getEntityDao().exeQueryPager(sql.toString(), new ApplyRowmapper(), args, pager,countSql.toString());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return results;
	}

	@Override
	public List<Apply> readToExcel(int applyStatus, String itemName) {
		List<String> argsList = new ArrayList<String>();
		List<Apply> results = new ArrayList<Apply>();
		
		StringBuilder sql = new StringBuilder("SELECT i.itemkey, i.itemName, i.itemTime, i.itemCollege, a.applyKey,  a.applyName, a.applyStatus FROM c_item  AS i INNER JOIN c_apply AS a ON i.itemkey = a.itemKey  WHERE 1=1 ");
		if(applyStatus != -1){
			argsList.add(String.valueOf(applyStatus));
			sql.append(" AND a.applyStatus = ?");
		}
		if(itemName != null && !itemName.equals("")){
			argsList.add(itemName);
			sql.append(" AND a.itemKey = ?");
		}else {
			return null;//只能按项目名称导出
		}
		
		Object [] args;
 		if(argsList.size() == 0)
			args = null;
		else {
			args = argsList.toArray();
		}
		
 		try {
			results = getEntityDao().exeQuery(sql.toString(),  new ApplyRowMapperToXls(), args);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 		return results;
	}

	@Override
	public boolean updateSfileKey(Object sfileKey, Object key) {
		String sql = "UPDATE c_apply SET SfileKey = ?, applyStatus = ? WHERE applyKey=? ";
		Object [] args = { sfileKey, Dictionary.applyStatus_uploaded, key};
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
	public List<Apply> readWithPageByArgsOth(Pager<Apply> pager,
			int applyStatus, String itemName, String orgId) {	//orgid不能为NULL
		if(orgId == null || orgId == ""){
			return null;
		}else{
			List<String> argsList = new ArrayList<String>();
			List<Apply> results = new ArrayList<Apply>();
			
			StringBuilder sql = new StringBuilder("SELECT i.itemkey, i.itemName, i.itemTime, i.itemCollege, a.applyKey, a.userKey, a.applyName, a.applyStatus,a.SfileKey FROM c_item  AS i INNER JOIN c_apply AS a ON i.itemkey = a.itemKey  WHERE 1=1 ");
			StringBuilder countSql = new StringBuilder("select count(*) FROM c_item  AS i INNER JOIN c_apply AS a ON i.itemkey = a.itemKey WHERE 1=1 ");
			if(applyStatus != -1){
				argsList.add(String.valueOf(applyStatus));
				countSql.append(" AND applyStatus = ?");
				sql.append(" AND applyStatus = ?");
			}
			if(itemName != null && !itemName.equals("")){
				argsList.add(itemName);
				sql.append(" AND i.itemKey = ?");
				countSql.append(" AND i.itemKey = ?");
			}
			
			argsList.add(orgId);
			sql.append(" AND i.orgId = ? ");
			countSql.append(" AND i.orgId = ? ");
			
			Object [] args;
			args = argsList.toArray();
			
			try {
				results = getEntityDao().exeQueryPager(sql.toString(), new ApplyRowmapper(), args, pager,countSql.toString());
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return results;
		}
	}

}
