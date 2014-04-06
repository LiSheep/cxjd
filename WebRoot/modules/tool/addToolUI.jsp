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
	<script type="text/javascript"
		src="<%=basePath%>js/My97DatePicker/WdatePicker.js"></script>
	<link type="text/css" rel="stylesheet"
		href="<%=basePath%>css/tm/style/backstage.css" />
	<link type="text/css" rel="stylesheet"
		href="<%=basePath%>css/tm/style/colorbox.css" />
	<script type="text/javascript">
			$("form").submit(function(){
				alert("添加成功!");
				parent.listEntity();
			});
	</script>
</head>
<body>

	<div class="project_pop">
		<div class="hd">添加工具</div>
		<div class="exp">请输入工具信息：</div>
		<div class="bd">
			<s:form theme="simple" action="addTool">
				<table>
					<tbody>
						<tr>
							<th>工具名字：</th>
							<td><s:textfield name="model.toolName" /></td>
						</tr>
						<tr>
							<th>编号与型号：</th>
							<td><s:textfield name="model.toolNo" /></td>
						</tr>
						<tr>
							<th>数量：</th>
							<td><s:textfield name="model.toolNum" /></td>
						</tr>
						<tr>
							<th>归属地区：</th>
							<td><s:select list="#{1:'旅顺',2:'本部'}" listKey="key"
									listValue="value" headerKey="0" name="model.toolPlace" /></td>
						</tr>
						<tr>
							<td colspan="4" style="text-align:center;"><s:submit
									value="保存" /></td>
						</tr>
					</tbody>
				</table>
				<input type="hidden" name="model.userKey"
					value="${sessionScope.userKey }" />
			</s:form>
		</div>
	</div>
</body>
</html>