package com.cxjd.action.news;

import java.util.Date;

import com.cxjd.action.CrudAction;
import com.cxjd.domain.news.News;
import com.cxjd.service.news.INewsService;
import com.cxjd.service.news.NewsService;
import com.opensymphony.xwork2.ActionContext;

/**
 * news' control  层
 * @author 李腾超
 * @since 2013-07-20
 *
 */
public class NewsAction extends CrudAction<News, INewsService> {

	private static final long serialVersionUID = -6592322508416096384L;

	@Override
	public News getModel() {
		if(model == null){
			model = new News();	//new出一个java bean即可
		}
		return model;
	}

	@Override
	public INewsService getService() {
		if(service == null){
			service = new NewsService(); //new一个service
		}
		return service;
	}

	@Override
	public String list() throws Exception {
		pager.setPageSize(10);
		Object args[] = new Object[1];
		String actionName = ActionContext.getContext().getName();
		if(actionName.contains("listSchoolNews")){
			args[0] = 1;
		}
		else if(actionName.contains("listReportNews")) {
			args[0] = 2;
		}
		else if(actionName.contains("listItemNews")){
			args[0] = 3;
		}
		setEntities(getService().readWithPage(pager, args));
		return "list";
	}

	@Override
	public String input() throws Exception {
		String nav = "addinput";
		if( getKey()!=null && !getKey().isEmpty()){
			nav = "updateinput";
			model = getService().readUnique(getKey());
		}
		return nav;
	}

	@Override
	public String add() throws Exception {
		getModel().setNewsDate(new Date());
		getService().create(getModel());
		return "add";
	}

	@Override
	public String update() throws Exception {
		getModel().setNewsDate(new Date());
		getService().update(getModel());
		return "update";
	}

	@Override
	public String delete() throws Exception {
		getService().deleteUnique(getKey());
		return "delete";
	}
	
	public String detail(){
		model = getService().readUnique(getKey());
		return "detail";
	}
	
}
