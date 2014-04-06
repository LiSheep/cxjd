<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<s:if test='#session.role==1'>
<jsp:include page="/modules/common/adm_menu.jsp" />
</s:if>
<s:elseif test= '#session.role==2'>
<jsp:include page="/modules/common/stu_menu.jsp" />
</s:elseif>
<s:elseif test= '#session.role==3||#session.role==4'>
<jsp:include page="/modules/common/tea_menu.jsp" />
</s:elseif>
<s:else>
<div id="mi_sideBar">
	<ul id="sideBar_menu">
	<li>&nbsp</li>
</ul>
</div>
</s:else>
