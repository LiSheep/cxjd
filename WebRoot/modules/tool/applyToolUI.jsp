<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<html>
<head>
	<title>添加工具</title>
	<script type="text/javascript" src="<%=basePath%>js/jquery-1.7.1.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>js/lhgdialog.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>js/sideBar_menu.js"></script>
	<script type="text/javascript" src="<%=basePath%>js/My97DatePicker/WdatePicker.js"></script>
	<link type="text/css" rel="stylesheet" href="<%=basePath%>css/tm/style/backstage.css" />
	<link type="text/css" rel="stylesheet" href="<%=basePath%>css/tm/style/colorbox.css" />
	 <link rel="stylesheet" href="<%=basePath%>css/cxjd/main.css" type="text/css"></link>
    <script type="text/javascript" src="<%=basePath%>js/common.js"></script>
    <script type="text/javascript" src="<%=basePath%>js/toolcheck.js"></script>
    <script>
    	
    </script>
</head>
<body>

	<div class="project_pop">
		<div class="hd">工具申请</div>
		<div class="exp">请填写申请表：</div>
		<div class="bd">
			<s:form id="addBorrow_form" theme="simple" action="addBorrow">
				<s:hidden name="model.toolKey" />
				<s:hidden name="model.broStatus" value="0" />
				<input type="hidden" name="model.bro_userKey"
					value="${sessionScope.userKey }" />
				<table>
					<tr>
						<td>申请工具:</td>
						<td><s:textfield disabled="true" name="model.toolName" /></td>
					</tr>
					<tr>
						<td>编号与型号:</td>
						<td><s:textfield disabled="true" name="model.toolNo" /></td>
					</tr>
					<tr>
						<td>需要租借时间:</td>
						<td><s:textfield id="broDay" name="model.broDay" /></td>
						<td>单位（天）</td>
						<td><span id="err_broDay" class="error"></span></td>
					</tr>
					<tr>
						<td>需要租借数量:</td>
						<td><s:textfield id="broNum" name="model.broNum" /></td>
						<td><span id="err_broNum" class="error"><s:fielderror name="err_broNum" /></span></td>
					</tr>
					<tr>
						<td>租借原因:</td>
						<td><s:textarea name="model.broNote" /></td>
					</tr>
					<tr>
						<td><s:submit value="申请" /></td>
					</tr>
				</table>
			</s:form>
		</div>
	</div>
</body>
</html>