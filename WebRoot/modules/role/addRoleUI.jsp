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
		<title>添加角色</title>
		<script type="text/javascript" src="<%=basePath%>js/jquery-1.7.1.min.js"></script>
		<script type="text/javascript" src="<%=basePath%>js/lhgdialog.min.js"></script>
		<script type="text/javascript" src="<%=basePath%>js/sideBar_menu.js"></script>
		<script type="text/javascript" src="<%=basePath%>js/My97DatePicker/WdatePicker.js"></script>
		<link type="text/css" rel="stylesheet" href="<%=basePath%>css/tm/style/backstage.css" />
        <link type="text/css" rel="stylesheet" href="<%=basePath%>css/tm/style/colorbox.css" />
       	<link rel="stylesheet" href="<%=basePath%>css/cxjd/main.css" type="text/css"></link>
       	<script type="text/javascript" src="<%=basePath%>js/jquery-1.7.1.min.js"></script>	
		<script type="text/javascript" src="<%=basePath%>js/common.js"></script>	
        
		<script type="text/javascript">
			$(function(){
				$("#addRole_form").submit(function(){
					if(isEmpty("rolename")){
						$("#err_rolename").html("*角色名不能为空");
						return false;
					}else{ 
						$("#err_rolename").html("");
					}
				});
			});
		</script>
	</head>
	<body>

		<div class="project_pop">
			<div class="hd">添加角色</div>
			<div class="exp">请输入角色信息：</div>
			<div class="bd">
				<s:form id="addRole_form" theme="simple" action="addRole">
					<table>
						<tbody>	
							<tr>
								<th>角色名：</th>
								<td>
									<s:textfield id="rolename" name="model.roleName" />
								</td>
								<td><span id="err_rolename" class="error"></span></td>
							</tr>
							<tr>
								<th>角色权限：</th>
								<td>
									<s:select name="model.roleAuth" list="roleType" 
									 listKey="key" listValue="value"  value="model.roleAuth"  />
								</td>
								<td></td>
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