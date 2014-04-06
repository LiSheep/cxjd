package com.cxjd.service.news;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

import com.cxjd.common.GuidGenerator;
import com.cxjd.dao.Pager;
import com.cxjd.domain.news.News;
import com.cxjd.rowmapper.news.NewsRowmapper;
import com.cxjd.service.AbstractBaseService;

/**
 * news' service 
 * @author ÀîÌÚ³¬
 * @since 2013-07-20
 *
 */
public class NewsService  extends AbstractBaseService<News> implements INewsService {

	@Override
	public boolean create(News param) {
		String sql = "INSERT INTO c_news(newsKey, newsTitle, newsContent, newsDate, newsType) VALUES(?,?,?,?,?)";
		Object[] args = { GuidGenerator.getGuid(), param.getNewsTitle(), param.getNewsContent(),
				param.getNewsDate(),param.isNewsType() };
		int result = 0;
		try {
			result = getEntityDao().exeUpdate(sql, args);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result >0;
	}

	@Override
	public boolean update(News param) {
		String sql = "UPDATE c_news SET newsTitle =?, newsContent = ?, newsDate=?,  newsType =? WHERE newsKey=?";
		Object [] args = {param.getNewsTitle(),param.getNewsContent(),param.getNewsDate(),
				param.isNewsType(), param.getId()};
		int result = 0;
		try {
			result  = getEntityDao().exeUpdate(sql, args);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result > 0;
	}

	@Override
	public boolean delete(Object[] keys) {
		return false;
	}

	@Override
	public boolean deleteUnique(Object key) {
		String sql = "DELETE FROM c_news WHERE newsKey =?";
		Object[] args ={key};
		int result = 0;
		try {
			result = getEntityDao().exeUpdate(sql, args);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result > 0;
	}

	@Override
	public News readUnique(Object key) {
		String sql = "SELECT * FROM c_news WHERE newsKey =? ";
		Object[]args = {key};
		News news = new News();
		try {
			news = getEntityDao().exeQuery(sql, new NewsRowmapper(), args).get(0);
		} catch (Exception e) {
			return null;
		}
		
		return news;
	}

	@Override
	public List<News> read(Object[] args) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<News> readAll() {
		return null;
	}

	@Override
	public List<News> readWithPage(Pager<News> pager, Object[] args) {
		String sql = "select * from c_news where newsType=? order by newsDate DESC ";
		String countSql = "select count(*) from c_news where newsType=?";
		List<News> list = Collections.emptyList();
		try {
			list = getEntityDao().exeQueryPager(sql, new NewsRowmapper(), args, pager,countSql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
}
