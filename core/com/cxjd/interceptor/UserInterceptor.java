package com.cxjd.interceptor;

import java.util.Map;

import com.cxjd.common.Dictionary;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

/**
 * �û�Ȩ����֤������
 * @author ���ڳ�
 *
 *@since 2013-05-10
 */
public class UserInterceptor extends MethodFilterInterceptor {

	private static final long serialVersionUID = -6076118636695186803L;

	@Override
	public String doIntercept(ActionInvocation invocation) throws Exception {
		ActionContext ctx = invocation.getInvocationContext();  
        Map<String, Object> session = ctx.getSession();  
        String user = (String) session.get(Dictionary.name);  
  
        // ����ѵ�½
        if (user != null ) {  
            	return invocation.invoke();
        }  
        return Dictionary.noaccess;
	}
}
