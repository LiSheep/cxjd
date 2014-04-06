package com.cxjd.dao;

import java.sql.SQLException;
import java.util.List;

import com.cxjd.entity.EntityObject;

/**
 * JDBC�ӿڣ�ʵ�����ݿ�������Ҫʵ�ָýӿ�
 * @author ���ڳ�
 * @since 2013-05-10
 *
 */
public interface IJdbcDao<T extends EntityObject> {

	/**
	 * ִ�����ӡ��޸ġ�ɾ��
	 * @param sql sql���
	 * @param args �������
	 * @return ����Ӱ���¼��������0Ϊִ��ʧ��
	 * @throws SQLException 
	 */
	public abstract int exeUpdate(String sql, Object [] args) throws SQLException;
	
	
	/**
	 * ִ�в�ѯ
	 * @param sql sql���
	 * @param rm RowMapper
	 * @param args �������
	 * @return a ResultSet object that contains the data produced by the query; never null
	 * @throws SQLException 
	 */
	public List<T> exeQuery(String sql, RowMapper<T> rm, Object [] args) throws SQLException;
	
	/**
	 * ʵ�з�ҳ��ѯ
	 * @param sql
	 * @param rm
	 * @param args
	 * @param toPage
	 * @return JAVA BEAN ����list
	 * @throws SQLException
	 */
	public List<T> exeQueryPager(String sql, RowMapper<T>rm, Object [] args, Pager<T> pager, String countSql) throws SQLException;
	
	/**
	 * ��ѯ��¼����
	 * @param sql
	 * @param args
	 * @return ��¼����
	 * @throws SQLException
	 */
	public int countSize(String sql, Object[] args) throws SQLException;
	
	
	/**
	 * ������ִ�� ��ɾ�� ���
	 * @param sql
	 * @param args
	 * @return Ӱ���¼����
	 * @throws SQLException
	 */
	public void updateWithBatch(String sql, Object[][] args ) throws SQLException;
}
