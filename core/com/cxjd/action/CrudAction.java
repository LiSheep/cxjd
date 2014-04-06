package com.cxjd.action;

import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.cxjd.dao.Pager;
import com.cxjd.entity.EntityObject;
import com.cxjd.service.IBaseService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;

/**
 * Action ����
 * @author ���ڳ�
 * @since 2013-05-10
 *
 * @param <T> java bean
 * @param <S> Service
 */
public abstract class CrudAction<T extends EntityObject, S extends IBaseService<T>>
		extends ActionSupport implements Preparable,  ServletRequestAware {

	private static final long serialVersionUID = 8307918526933076782L;

	private String key;
	protected T model;
	protected  S service;
	private List<T > entities = Collections.emptyList();
	protected Pager<T> pager = new Pager<T>();
	
	public Pager<T> getPager() {
		return pager;
	}

	/**
	 * ����ÿҳ�Ĵ�С��PagerĬ��Ϊ10
	 * @param pageSize ÿҳ��С
	 */
	public void setPager(int pageSize) {
		this.pager =  new Pager<T>(pageSize);
	}

	public List<T> getEntities() {
		return entities;
	}

	public void setEntities(List<T> entities) {
		this.entities = entities;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	@Override
	public void prepare() throws Exception {
	}

	@Override
	public void setServletRequest(HttpServletRequest arg0) {
		if(arg0.getParameter("page") == null && pager != null){
			pager.setPageNo(1);
		}
		else if(arg0.getParameterValues("page") != null) {
			pager.setPageNo(Integer.parseInt(arg0.getParameter("page")));
		}
	}
	
	/**
	 * �Ե���ģʽ����model
	 * @return EntityObject
	 * 
	 */
	public abstract T getModel();
	
	public abstract S getService();
	
	public abstract String list() throws Exception;
	
	public abstract String input() throws Exception;

	public abstract String add() throws Exception;

	public abstract String update() throws Exception;

	public abstract String delete() throws Exception;

}
