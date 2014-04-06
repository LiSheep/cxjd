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
		<title>设备申请</title>
		<script type="text/javascript" src="<%=basePath%>js/jquery-1.7.1.min.js"></script>
		<script type="text/javascript" src="<%=basePath%>js/lhgdialog.min.js"></script>
		<script type="text/javascript" src="<%=basePath%>js/sideBar_menu.js"></script>
		<script type="text/javascript" src="<%=basePath%>js/My97DatePicker/WdatePicker.js"></script>
		<link type="text/css" rel="stylesheet" href="<%=basePath%>css/tm/style/backstage.css" />
        <link type="text/css" rel="stylesheet" href="<%=basePath%>css/tm/style/colorbox.css" />
		<script type="text/javascript">
			function checkOK() {
				document.forms[0].action = "<%=basePath%>checkOKBorrow.action";
				document.forms[0].submit();
				
			}
			function checkNO() {
				document.forms[0].action = "<%=basePath%>checkNOBorrow.action";
				document.forms[0].submit();
				
			}
			
		</script>
	</head>
	<body>

		<div class="project_pop">
			<div class="hd">申请工具表</div>
			<div class="exp">请完整填写申请工具表：</div>
			<div class="bd">
			<p style="color:red">注意：通过审核即开始计算时间，请管理员借出后再通过审核。管理员可直接修改申请表内容。</p>
				<s:form theme="simple">
				<s:hidden name="model.id" />
				<s:hidden name="model.toolKey" />
				<s:hidden name="model.broStatus" value="0"/>
				<input type="hidden" name="model.bro_userKey" value="${sessionScope.userKey }"  />
					<table>
						<tr>
							<td><s:fielderror name="err_broNum" /> </td>
						</tr>
						<tr>
							<td>申请工具:</td>
							<td><s:textfield disabled="true" name="model.toolName"/></td>
						</tr>
						<tr>
							<td>编号与型号:</td>
							<td><s:textfield disabled="true" name="model.toolNo" /></td>
						</tr>
						<tr>
							<td>需要租借时间:</td>
							<td><s:textfield name="model.broDay" /></td>
							<td>单位（天）</td>
						</tr>
						<tr>
							<td>需要租借数量:</td>
							<td><s:textfield name="model.broNum" /></td>
						</tr>
						<tr>
							<td>租借原因:</td>
							<td><s:textarea name="model.broNote" /></td>
						</tr>
						<tr>
							<td></td>
							<td><input type="button" value="审核通过"  onclick="checkOK();" /><input type="button" value="审核不通过" onclick="checkNO();" /></td>
						</tr>
					</table>
				</s:form>
			</div>
		</div>
	</body>
</html>