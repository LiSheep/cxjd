package com.cxjd.service.item;

import java.util.List;

import com.cxjd.dao.Pager;
import com.cxjd.domain.item.Apply;
import com.cxjd.domain.item.Claimer;
import com.cxjd.service.IBaseService;

public interface IApplyService extends IBaseService<Apply> {
	
	//ͨ�������ȡ������
	public Apply readApplyByItemFK(Object itemKey);
	
	//ͨ���û�key��ȡ������Ϣ
	public List<Apply> readApplyByUserKeyWithPage(Pager<Apply> pager, Object userKey);
	
	//���±�����״̬
	public boolean UpdateApplyStatus(int applyStatus, Object key);
	
	//�򵥶�ȡapply���ڳɼ�¼��
	public Apply readSingleApplySimple(Object key);
	
	//���³ɼ�
	public boolean updateApplyMark(String mark, Object key);
	
	//��ѯ�ɼ��б�
	public List<Apply> readSimpleApply4MarkWithPage(Pager<Apply> pager);
	
	//����״̬����Ŀ����ѯ����������Ա��ѯȫ����
	public List<Apply> readWithPageByArgs(Pager<Apply> pager, int applyStatus, String itemName);
	
	//����״̬����Ŀ����ѯ���ǳ�������Ա��ѯ���֣�
	public List<Apply> readWithPageByArgsOth(Pager<Apply> pager, int applyStatus, String itemName, String orgId);
		
	//��ѯ�Ե���excel
	public List<Apply> readToExcel(int applyStatus, String itemName);
	
	//�����ϴ�����
	public boolean updateSfileKey(Object sfileKey, Object key);
}
