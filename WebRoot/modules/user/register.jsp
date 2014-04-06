<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'register.jsp' starting page</title>

<meta http-equiv=Content-Type content="text/html;charset=utf-8">
<script type="text/javascript" src="<%=basePath%>js/jquery-1.7.1.min.js"></script>
<link rel="stylesheet" href="<%=basePath%>css/cxjd/main.css"
	type="text/css"></link>
<script type="text/javascript" src="<%=basePath%>js/common.js"></script>
<script type="text/javascript" src="<%=basePath%>js/usercheck.js"></script>
</head>
<body>
<s:hidden id="mode" value="add"></s:hidden>
	<s:form id="addUser_form" theme="simple" action="doregister">
		<table>
			<tbody>
				<tr>
					<th>用户名：</th>
					<td><s:textfield id="username" name="model.userName" />
					</td>
					<td>
						<span id="err_username" class="error">
							<s:property value="errors['model.userName']" /> 
						</span>
					</td>
				</tr>
				<tr>
					<th>密码：</th>
					<td><s:password id="password" name="model.userPass" />
					</td>
					<td><span id="err_password" class="error"></span></td>
				</tr>
				<tr>
					<th>确认密码：</th>
					<td><s:password id="confirm" name="confirm" />
					</td>
					<td><span id="err_confirm" class="error"></span></td>
				</tr>
				<tr>
					<th>性别：</th>
					<td><s:radio name="model.gender"
							list="%{#{true:'男',false:'女'}}" theme="simple"></s:radio>
					</td>
				</tr>
				<tr>
					<th>电子邮件：</th>
					<td><s:textfield id="email" name="model.userMail" /></td>
					<td>
						<span id="err_email" class="error"><s:property value="errors['model.userMail']" /></span>
					</td>
				</tr>
				<tr>
					<th>移动电话：</th>
					<td><s:textfield id="phone" name="model.phone" /></td>
					<td><span id="err_phone" class="error"></span>
					</td>
				</tr>
				<tr>
					<td></td>
					<td><s:submit value="注册" />
					</td>
					<td></td>
				</tr>
			</tbody>
		</table>
	</s:form>


</body>
</html>
