package com.cxjd.dao;

import java.sql.ResultSet;

import com.cxjd.entity.EntityObject;

/**
 * 表映射，实体类查询需要该方法映射数据字段
 * @author 李腾超
 * @since 2013-05-10
 *
 * @param <T> java bean
 */
public interface RowMapper<T extends EntityObject> {

	public T mapRow(ResultSet rs, int num);
}
