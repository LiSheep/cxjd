package com.cxjd.rowmapper.news;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.cxjd.dao.RowMapper;
import com.cxjd.domain.news.News;

/**
 * news' rowmapper
 * @author 李腾超
 * @since 2013-07-20
 *
 */
public class NewsRowmapper implements RowMapper<News> {

	@Override
	public News mapRow(ResultSet rs, int num) {
		News item = new News();	//需要修改成我们所创建的JAVA BEAN 实体类
		
		try {
			item.setId(rs.getString("newsKey"));		//注意需要添加实体类的主键
			
			item.setNewsTitle(rs.getString("newsTitle"));
			item.setNewsContent(rs.getString("newsContent"));
			item.setNewsDate(rs.getDate("newsDate"));
			item.setNewsType(rs.getInt("newsType"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return item;
	}

}
