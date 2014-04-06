package com.cxjd.dao;

import java.util.Collections;
import java.util.List;

import com.cxjd.entity.EntityObject;

/**
 * 分页
 * @author 李腾超
 * @since 2013-05-15
 *
 * @param <T> java bean
 */
public class Pager<T extends EntityObject> {
	
	protected int pageNo = 1;		//当前页数
	protected int pageSize = 10;	//每页大小，默认为10
	
	protected List<T> result = Collections.emptyList();
	protected int totalCount = 0;	//记录条数
	
	public Pager() {
		
	}
	
	public Pager(int pageSize){
		this.pageSize = pageSize;
	}

	public int getPageNo() {
		if(getTotalPages() < this.pageNo){
			this.pageNo = getTotalPages();
		}
		return pageNo;
	}

	public void setPageNo(int pageNo){
		this.pageNo = pageNo;
	}
	
	public int getPageSize() {
		if(this.pageSize < 1){
			this.pageSize = 1;
		}
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public List<T> getResult() {
		return result;
	}

	public void setResult(List<T> result) {
		if(result != null){
			this.result = result;
		}
		else {
			result = Collections.emptyList();
		}
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	
	public int getTotalPages(){
		if(getTotalCount() <= getPageSize()){
			return 1;
		}
		int count = getTotalCount() / getPageSize();
		if(getTotalCount()% getPageSize() >0){
			count++;
		}
		return count;
	}
	
	public boolean hasNext(){
		return this.pageNo + 1<= getTotalPages();
	}
	
	public int getNextPages(){
		if(hasNext()){
			return this.pageNo + 1;
		}
		else {
			return this.pageNo;
		}
	}
	
	public boolean hasPre(){
		return this.pageNo - 1 >= 1;
	}
	
	public int getPrePages(){
		if(hasPre()){
			return this.pageNo - 1;
		}
		else {
			return this.pageNo;
		}
	}
	
	public int getFirstPage(){
		return 1;
	}
	
	public int getLastPage(){
		return getTotalPages();
	}
}
