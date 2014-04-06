package com.cxjd.service;

import com.cxjd.dao.IJdbcDao;
import com.cxjd.dao.JdbcDao;
import com.cxjd.entity.EntityObject;

/**
 * Service ����
 * @author ���ڳ�
 *
 *@since 2013-05-10
 * @param <T>  java bean
 */
public abstract class AbstractBaseService<T extends EntityObject>  {
	
	private  IJdbcDao<T> entityDao;


	public IJdbcDao<T> getEntityDao() {
		if(entityDao == null)
			entityDao = new JdbcDao<T>();  //Ӧ����IOC
		return entityDao;
	}

	
}
