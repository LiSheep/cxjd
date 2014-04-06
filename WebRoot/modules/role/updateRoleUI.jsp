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
		<title>修改角色</title>
		<script type="text/javascript" src="<%=basePath%>js/jquery-1.7.1.min.js"></script>
		<script type="text/javascript" src="<%=basePath%>js/lhgdialog.min.js"></script>
		<script type="text/javascript" src="<%=basePath%>js/sideBar_menu.js"></script>
		<script type="text/javascript" src="<%=basePath%>js/My97DatePicker/WdatePicker.js"></script>
		<link type="text/css" rel="stylesheet" href="<%=basePath%>css/tm/style/backstage.css" />
        <link type="text/css" rel="stylesheet" href="<%=basePath%>css/tm/style/colorbox.css" />
		<script type="text/javascript">
			$(function(){
				$("#addRole_form").submit(function(){
					if(isEmpty("rolename")){
						$("#err_rolename").html("用户名不能为空");
						return;
					}else{
						$("#err_rolename").html("");
					}
					
				});
			});
		</script>
	</head>
	<body>
		<s:hidden name="model.id" />
		<div class="project_pop">
			<div class="hd">修改角色</div>
			<div class="exp">请输入角色信息：</div>
			<div class="bd">
				<s:form theme="simple" action="updateRole">
				<s:hidden name="model.id"/>
					<table>
						<tbody>	
							<tr>
								<th>角色名：</th>
								<td>
									<s:textfield name="model.roleName" />
								</td>
								<td><s:property value="errors['model.roleName']"/></td>
							</tr>
							<tr>
								<th>角色权限：</th>
								<td>
									<s:select name="model.roleAuth" list="roleType" 
									 listKey="key" listValue="value"  value="model.roleAuth"  />
								</td>
							</tr>		
							<tr>
								<td></td>
								<td><s:submit value="保存"/></td>
								<td></td>
							</tr>
						</tbody>
					</table>
				</s:form>
			</div>
		</div>
	</body>
</html>