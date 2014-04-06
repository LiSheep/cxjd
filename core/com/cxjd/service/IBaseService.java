package com.cxjd.service;

import java.util.List;

import com.cxjd.dao.Pager;
import com.cxjd.entity.EntityObject;

/**
 * service 操作接口
 * @author 李腾超
 * 
 * @since 2013-05-10
 * @param <T>
 */
public interface IBaseService<T extends EntityObject> {
	
	/**
	 * 插入一条记录
	 * @param model实体
	 * @return 成功返回true，失败返回false
	 */
	public abstract boolean create(T param);

	/**
	 * 更新一条记录
	 * @param model实体
	 * @return 成功返回true，失败返回false
	 */
	public abstract boolean update(T param);

	/**
	 * 批量删除
	 * @param 要删除的主键集合
	 * @return 成功返回true，失败返回false
	 */
	public abstract boolean delete(Object []keys);

	/**
	 * 删除单条记录
	 * @param 要删除的主键
	 * @return 成功返回true，失败返回false
	 */
	public abstract boolean deleteUnique(Object key);

	/**
	 * 读取一条记录
	 * @param 要读取的主键
	 * @return 成功返回该实体，否则返回null
	 */
	public abstract T readUnique(Object key);

	/**
	 * 批量读取数据
	 * @param SQL参数
	 * @return 成功返回该实体集合，否则返回null
	 */
	public abstract List<T> read(Object [] args);

	/**
	 * 读取全部的数据
	 * @return 成功返回该实体集合，否则返回null
	 */
	public abstract List<T> readAll();
	
	/**
	 * 动态分页读取数据
	 * @param pager类实现
	 * @param SQL参数
	 * @return
	 */
	public abstract List<T> readWithPage(Pager<T> pager, Object [] args);

}
