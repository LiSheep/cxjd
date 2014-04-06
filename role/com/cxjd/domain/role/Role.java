package com.cxjd.domain.role;

import com.cxjd.entity.EntityObject;

/**
 * role's java bean
 * @author ÀîÌÚ³¬
 * @since 2013-07-20
 *
 */
public class Role  extends EntityObject{

	private String roleName;
	private int roleAuth;

	public int getRoleAuth() {
		return roleAuth;
	}

	public void setRoleAuth(int roleAuth) {
		this.roleAuth = roleAuth;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	
}
