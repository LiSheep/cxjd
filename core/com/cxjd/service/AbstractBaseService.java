package com.cxjd.service;

import com.cxjd.dao.IJdbcDao;
import com.cxjd.dao.JdbcDao;
import com.cxjd.entity.EntityObject;

/**
 * Service 基类
 * @author 李腾超
 *
 *@since 2013-05-10
 * @param <T>  java bean
 */
public abstract class AbstractBaseService<T extends EntityObject>  {
	
	private  IJdbcDao<T> entityDao;


	public IJdbcDao<T> getEntityDao() {
		if(entityDao == null)
			entityDao = new JdbcDao<T>();  //应该用IOC
		return entityDao;
	}

	
}
