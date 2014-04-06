package com.cxjd.service.item;

import java.util.List;

import com.cxjd.dao.Pager;
import com.cxjd.domain.item.Apply;
import com.cxjd.domain.item.Claimer;
import com.cxjd.service.IBaseService;

public interface IApplyService extends IBaseService<Apply> {
	
	//通过外键读取报名表
	public Apply readApplyByItemFK(Object itemKey);
	
	//通过用户key读取报名信息
	public List<Apply> readApplyByUserKeyWithPage(Pager<Apply> pager, Object userKey);
	
	//更新报名表状态
	public boolean UpdateApplyStatus(int applyStatus, Object key);
	
	//简单读取apply用于成绩录入
	public Apply readSingleApplySimple(Object key);
	
	//更新成绩
	public boolean updateApplyMark(String mark, Object key);
	
	//查询成绩列表
	public List<Apply> readSimpleApply4MarkWithPage(Pager<Apply> pager);
	
	//根据状态和项目名查询（超级管理员查询全部）
	public List<Apply> readWithPageByArgs(Pager<Apply> pager, int applyStatus, String itemName);
	
	//根据状态和项目名查询（非超级管理员查询部分）
	public List<Apply> readWithPageByArgsOth(Pager<Apply> pager, int applyStatus, String itemName, String orgId);
		
	//查询以导出excel
	public List<Apply> readToExcel(int applyStatus, String itemName);
	
	//更新上传附件
	public boolean updateSfileKey(Object sfileKey, Object key);
}
