package com.cxjd.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cxjd.entity.EntityObject;
import com.cxjd.jdbc.JdbcConnection;

/**
 * DAO²ã
 * @author ÀîÌÚ³¬
 *@since 2013-05-10
 *
 * @param <T> java bean
 */
public class JdbcDao<T extends EntityObject> implements IJdbcDao<T> {

	private PreparedStatement preparedStatement;
	
	private void addPreparedStatement(Object [] args) throws SQLException{
		if(preparedStatement != null){
			preparedStatement.clearParameters();
		}
		int i = 1;
		if( args != null && args.length > 0){
			for (Object object : args) {
				preparedStatement.setObject(i++, object);
			}
		}
	}
	

	@Override
	public int exeUpdate(String sql, Object[] args) throws SQLException {

		this.preparedStatement = JdbcConnection.getConnection().prepareStatement(sql);
		this.addPreparedStatement(args);
		return preparedStatement.executeUpdate();
	}

	@Override
	public List<T > exeQuery(String sql, RowMapper<T> rm, Object[] args)
			throws SQLException {
		List<T > list = new ArrayList<T>();
		preparedStatement = JdbcConnection.getConnection().prepareStatement(sql);
		
		addPreparedStatement(args);
		ResultSet set = preparedStatement.executeQuery();
		
		int num=1;
		while(set.next()){
			list.add((T) rm.mapRow(set, num++));
		}
		return list;
	}
	
	@Override
	public List<T> exeQueryPager(String sql, RowMapper<T> rm, Object[] args, Pager<T> pager, String countSql) 
			throws SQLException {
		
		pager.setTotalCount(countSize(countSql, args));
		
		List<T> list = new ArrayList<T>();
		sql += "  limit "+(pager.getPageNo()-1)*pager.getPageSize()+","+pager.getPageSize();
		preparedStatement = JdbcConnection.getConnection().prepareStatement(sql);
		addPreparedStatement(args);
		ResultSet set = preparedStatement.executeQuery();
		int num=1;
		while(set.next()){
			list.add((T) rm.mapRow(set, num++));
		}
		return list;
	}


	@Override
	public int countSize(String sql, Object[] args) throws SQLException {
		preparedStatement = JdbcConnection.getConnection().prepareStatement(sql);
		addPreparedStatement(args);
		ResultSet set = preparedStatement.executeQuery();
		set.next();
		return set.getInt(1);
	}


	@Override
	public void updateWithBatch(String sql, Object[][] args)
			throws SQLException {
		preparedStatement = JdbcConnection.getConnection().prepareStatement(sql);
		for (Object[] objects : args) {
			addPreparedStatement(objects);
			
		}
		
	}
}
