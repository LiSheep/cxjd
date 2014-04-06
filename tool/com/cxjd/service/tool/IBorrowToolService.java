package com.cxjd.service.tool;

import java.util.List;

import com.cxjd.dao.Pager;
import com.cxjd.domain.tool.BorrowTool;
import com.cxjd.service.IBaseService;

/**
 * borrowTool service层
 * 
 *@author 李腾超
 *@since 2013-07-29
 *
 */
public interface IBorrowToolService extends IBaseService<BorrowTool> {

	public BorrowTool getToolInfoByToolKey(Object key);
	
	public BorrowTool getUnCheckInfo();
	
	public List<BorrowTool> readAllWithPager(Pager<BorrowTool> pager, Object[] args);
	
	//借出工具
	public boolean outBorrowTool(BorrowTool key);
	
	//归还工具
	public boolean backBorrowTool(Object key);
	
	//申请不通过
	public boolean refuseBorrowTool(Object key);
	
	//更新工具数量
	public boolean updateOutToolCount(String key, int count);
	
}
