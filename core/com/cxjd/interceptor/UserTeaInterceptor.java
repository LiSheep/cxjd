package com.cxjd.interceptor;

import java.util.Map;

import com.cxjd.common.Dictionary;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

/**
 * ������-Ȩ����֤-��ʦȨ��
 * @author ���ڳ�
 *
 * @since 2013-10-05
 */
public class UserTeaInterceptor extends MethodFilterInterceptor{


	private static final long serialVersionUID = -7958243055840363041L;

	@Override
	protected String doIntercept(ActionInvocation invocation) throws Exception {
		ActionContext ctx = invocation.getInvocationContext();  
        Map<String, Object> session = ctx.getSession();  
        int role = (Integer) session.get(Dictionary.role);
        if(role == Dictionary.role_tea || role == Dictionary.role_admin || role == Dictionary.role_collAdmin)
        	return invocation.invoke();
        else {
        	return Dictionary.noaccess;
        }
	}

}
