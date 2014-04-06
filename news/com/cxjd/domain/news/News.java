package com.cxjd.domain.news;

import java.util.Date;

import com.cxjd.entity.EntityObject;

/**
 * news' java bean
 * @author ÀîÌÚ³¬
 * @since 2013-05-30
 *
 */
public class News extends EntityObject {
	
	private String newsTitle;
	private String newsContent;
	private Date newsDate;
	private int newsType;
	
	public String getNewsTitle() {
		return newsTitle;
	}
	public void setNewsTitle(String newsTitle) {
		this.newsTitle = newsTitle;
	}
	public String getNewsContent() {
		return newsContent;
	}
	public void setNewsContent(String newsContent) {
		this.newsContent = newsContent;
	}
	public Date getNewsDate() {
		return newsDate;
	}
	public void setNewsDate(Date newsDate) {
		this.newsDate = newsDate;
	}
	public int isNewsType() {
		return newsType;
	}
	public void setNewsType(int newsType) {
		this.newsType = newsType;
	}
}
