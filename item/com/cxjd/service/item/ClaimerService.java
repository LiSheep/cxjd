package com.cxjd.service.item;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

import com.cxjd.dao.Pager;
import com.cxjd.domain.item.Claimer;
import com.cxjd.rowmapper.item.ClaimerRowmapper;
import com.cxjd.service.AbstractBaseService;

/**
 * Cliamer service
 * 
 *@author ÀîÌÚ³¬
 *@since 2013-08-29
 *
 */
public class ClaimerService extends AbstractBaseService<Claimer> implements IClaimerService{

	@Override
	public boolean create(Claimer param) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Claimer param) {
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
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Claimer readUnique(Object key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Claimer> read(Object[] args) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Claimer> readAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Claimer> readWithPage(Pager<Claimer> pager, Object[] args) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Claimer> readClaimerByApplyKey(Object applyKey, int claimerType) {
		String sql = "SELECT * FROM c_claimer WHERE applyKey = ? AND claimerType = ?";
		Object[]args = {applyKey, claimerType};
		List<Claimer> list = Collections.emptyList();
		try {
			list = getEntityDao().exeQuery(sql, new ClaimerRowmapper(), args);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public boolean deleteClaimerByApplyKey(Object applyKey) {
		String sql = "DELETE FROM c_claimer WHERE applyKey =?";
		Object[] args ={applyKey};
		int result = 0;
		try {
			result = getEntityDao().exeUpdate(sql, args);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result > 0;
	}

}
