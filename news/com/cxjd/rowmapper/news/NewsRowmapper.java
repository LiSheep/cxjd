package com.cxjd.rowmapper.news;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.cxjd.dao.RowMapper;
import com.cxjd.domain.news.News;

/**
 * news' rowmapper
 * @author ���ڳ�
 * @since 2013-07-20
 *
 */
public class NewsRowmapper implements RowMapper<News> {

	@Override
	public News mapRow(ResultSet rs, int num) {
		News item = new News();	//��Ҫ�޸ĳ�������������JAVA BEAN ʵ����
		
		try {
			item.setId(rs.getString("newsKey"));		//ע����Ҫ���ʵ���������
			
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
