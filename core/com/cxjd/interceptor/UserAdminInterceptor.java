package com.cxjd.interceptor;

import java.util.Map;

import com.cxjd.common.Dictionary;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

/**
 * 拦截器-权限验证-管理员权限
 * @author 李腾超
 *
 * @since 2013-06-25
 */
public class UserAdminInterceptor extends MethodFilterInterceptor{

	private static final long serialVersionUID = -6076118636695186803L;


	@Override
	protected String doIntercept(ActionInvocation invocation) throws Exception {
		ActionContext ctx = invocation.getInvocationContext();  
        Map<String, Object> session = ctx.getSession();  
        int role = (Integer) session.get(Dictionary.role);
        if(role == Dictionary.role_admin || role == Dictionary.role_collAdmin)
        	return invocation.invoke();
        else {
        	return Dictionary.noaccess;
        }
	}

}
