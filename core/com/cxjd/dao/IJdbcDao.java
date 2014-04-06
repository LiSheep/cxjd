package com.cxjd.dao;

import java.sql.SQLException;
import java.util.List;

import com.cxjd.entity.EntityObject;

/**
 * JDBC接口，实现数据库连接需要实现该接口
 * @author 李腾超
 * @since 2013-05-10
 *
 */
public interface IJdbcDao<T extends EntityObject> {

	/**
	 * 执行增加、修改、删除
	 * @param sql sql语句
	 * @param args 所需参数
	 * @return 返回影响记录的条数，0为执行失败
	 * @throws SQLException 
	 */
	public abstract int exeUpdate(String sql, Object [] args) throws SQLException;
	
	
	/**
	 * 执行查询
	 * @param sql sql语句
	 * @param rm RowMapper
	 * @param args 所需参数
	 * @return a ResultSet object that contains the data produced by the query; never null
	 * @throws SQLException 
	 */
	public List<T> exeQuery(String sql, RowMapper<T> rm, Object [] args) throws SQLException;
	
	/**
	 * 实行分页查询
	 * @param sql
	 * @param rm
	 * @param args
	 * @param toPage
	 * @return JAVA BEAN 对象list
	 * @throws SQLException
	 */
	public List<T> exeQueryPager(String sql, RowMapper<T>rm, Object [] args, Pager<T> pager, String countSql) throws SQLException;
	
	/**
	 * 查询记录数量
	 * @param sql
	 * @param args
	 * @return 记录条数
	 * @throws SQLException
	 */
	public int countSize(String sql, Object[] args) throws SQLException;
	
	
	/**
	 * 批处理执行 增删改 语句
	 * @param sql
	 * @param args
	 * @return 影响记录条数
	 * @throws SQLException
	 */
	public void updateWithBatch(String sql, Object[][] args ) throws SQLException;
}
