package com.cxjd.service.role;

import com.cxjd.domain.role.Role;
import com.cxjd.service.IBaseService;

/**
 * role's service ��
 * @author ���ڳ�
 * @since 2013-05-30
 */
public interface IRoleService extends IBaseService<Role> {

	public Role getRoleTypeById(String id);
}
