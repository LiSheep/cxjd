package com.cxjd.service;

import java.util.List;

import com.cxjd.dao.Pager;
import com.cxjd.entity.EntityObject;

/**
 * service �����ӿ�
 * @author ���ڳ�
 * 
 * @since 2013-05-10
 * @param <T>
 */
public interface IBaseService<T extends EntityObject> {
	
	/**
	 * ����һ����¼
	 * @param modelʵ��
	 * @return �ɹ�����true��ʧ�ܷ���false
	 */
	public abstract boolean create(T param);

	/**
	 * ����һ����¼
	 * @param modelʵ��
	 * @return �ɹ�����true��ʧ�ܷ���false
	 */
	public abstract boolean update(T param);

	/**
	 * ����ɾ��
	 * @param Ҫɾ������������
	 * @return �ɹ�����true��ʧ�ܷ���false
	 */
	public abstract boolean delete(Object []keys);

	/**
	 * ɾ��������¼
	 * @param Ҫɾ��������
	 * @return �ɹ�����true��ʧ�ܷ���false
	 */
	public abstract boolean deleteUnique(Object key);

	/**
	 * ��ȡһ����¼
	 * @param Ҫ��ȡ������
	 * @return �ɹ����ظ�ʵ�壬���򷵻�null
	 */
	public abstract T readUnique(Object key);

	/**
	 * ������ȡ����
	 * @param SQL����
	 * @return �ɹ����ظ�ʵ�弯�ϣ����򷵻�null
	 */
	public abstract List<T> read(Object [] args);

	/**
	 * ��ȡȫ��������
	 * @return �ɹ����ظ�ʵ�弯�ϣ����򷵻�null
	 */
	public abstract List<T> readAll();
	
	/**
	 * ��̬��ҳ��ȡ����
	 * @param pager��ʵ��
	 * @param SQL����
	 * @return
	 */
	public abstract List<T> readWithPage(Pager<T> pager, Object [] args);

}
