package com.cxjd.dao;

import java.sql.ResultSet;

import com.cxjd.entity.EntityObject;

/**
 * ��ӳ�䣬ʵ�����ѯ��Ҫ�÷���ӳ�������ֶ�
 * @author ���ڳ�
 * @since 2013-05-10
 *
 * @param <T> java bean
 */
public interface RowMapper<T extends EntityObject> {

	public T mapRow(ResultSet rs, int num);
}
