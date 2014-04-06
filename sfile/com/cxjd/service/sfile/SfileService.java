package com.cxjd.service.sfile;

import java.sql.SQLException;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import com.cxjd.common.GuidGenerator;
import com.cxjd.dao.Pager;
import com.cxjd.domain.role.Role;
import com.cxjd.domain.sfile.Sfile;
import com.cxjd.rowmapper.role.RoleRowmapper;
import com.cxjd.rowmapper.sfile.SfileRowmapper;
import com.cxjd.service.AbstractBaseService;

/**
 * share file service²ã
 * 
 *@author ÀîÌÚ³¬
 *@since 2013-10-05
 *
 */
public class SfileService extends AbstractBaseService<Sfile> implements ISfileService {

	@Override
	public boolean create(Sfile param) {
		param.setId(GuidGenerator.getGuid());
		String sql = "INSERT INTO t_sfile(SfileKey, SfileTitle, SfileComment, SfileOldName, SfileNewName, SfileType, SfileDate) "+
					"VALUES(?, ?, ?, ?, ?, ?, ?)";
		
		Object[] args = {param.getId(), param.getSfileTitle(), param.getSfileComment(), param.getSfileOldName(), param.getSfileNewName(), param.getSfileType(), new Date()};
		int result = 0;
		try {
			result = getEntityDao().exeUpdate(sql, args);
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return result > 0;
	}
	
	@Override
	public boolean update(Sfile param) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Object[] keys) {
		return false;
	}

	@Override
	public boolean deleteUnique(Object key) {
		String sql = " DELETE FROM t_sfile WHERE SfileKey = ? ";
		Object [] args = {key};
		try {
			return getEntityDao().exeUpdate(sql, args) > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Sfile readUnique(Object key) {
		String sql = "SELECT * FROM t_sfile WHERE SfileKey = ?";
		Object []args = {key};
		Sfile item = null;
		try {
			List<Sfile> items = getEntityDao().exeQuery(sql, new SfileRowmapper(), args);
			if(!items.isEmpty())
				item = items.get(0);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return item;
			
	}

	@Override
	public List<Sfile> read(Object[] args) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Sfile> readAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Sfile> readWithPage(Pager<Sfile> pager, Object[] args) {
		String sql = "select * from t_sfile ";
		String countSql = "select count(*) from t_sfile ";
		List<Sfile> list = Collections.emptyList();
		try {
			list = getEntityDao().exeQueryPager(sql, new SfileRowmapper(), null, pager,countSql);
		} catch (SQLException e) {
			// TODO Auto-generated method stub
			e.printStackTrace();
		}
		return list;
	}

}
