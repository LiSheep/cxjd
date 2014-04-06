package com.cxjd.service.tool;

import java.util.List;

import com.cxjd.dao.Pager;
import com.cxjd.domain.tool.BorrowTool;
import com.cxjd.service.IBaseService;

/**
 * borrowTool service��
 * 
 *@author ���ڳ�
 *@since 2013-07-29
 *
 */
public interface IBorrowToolService extends IBaseService<BorrowTool> {

	public BorrowTool getToolInfoByToolKey(Object key);
	
	public BorrowTool getUnCheckInfo();
	
	public List<BorrowTool> readAllWithPager(Pager<BorrowTool> pager, Object[] args);
	
	//�������
	public boolean outBorrowTool(BorrowTool key);
	
	//�黹����
	public boolean backBorrowTool(Object key);
	
	//���벻ͨ��
	public boolean refuseBorrowTool(Object key);
	
	//���¹�������
	public boolean updateOutToolCount(String key, int count);
	
}
