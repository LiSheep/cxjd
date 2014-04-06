<html xmlns=http://www.w3.org/1999/xhtml>
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
		<title>添加用户</title>
		<meta http-equiv=Content-Type content="text/html;charset=utf-8">
		<script type="text/javascript" src="<%=basePath%>js/jquery-1.7.1.min.js"></script>
		<script type="text/javascript" src="<%=basePath%>js/lhgdialog.min.js"></script>
		<script type="text/javascript" src="<%=basePath%>js/sideBar_menu.js"></script>
		<script type="text/javascript" src="<%=basePath%>js/My97DatePicker/WdatePicker.js"></script>
		<link type="text/css" rel="stylesheet" href="<%=basePath%>css/tm/style/backstage.css" />
        <link type="text/css" rel="stylesheet" href="<%=basePath%>css/tm/style/colorbox.css" />
		<link rel="stylesheet" href="<%=basePath%>css/cxjd/main.css" type="text/css"></link>
        <script type="text/javascript" src="<%=basePath%>js/common.js"></script>
        <script type="text/javascript" src="<%=basePath%>js/usercheck.js"></script>
	</head>
	<body>
		
		<div class="project_pop">
			<div class="hd">添加用户</div>
			<div class="exp">请输入用户信息：</div>
			<div class="bd">
				<s:form theme="simple" action="updateUser">
					<s:hidden name="model.id"></s:hidden>
					<table>
						<tbody>	
							<tr>
								<th>用户名：</th>
								<td>
									<s:textfield id="username" name="model.userName" />
								</td>
								<td><span id="err_username" class="error"><s:property value="errors['model.userName']"/></span></td>
							</tr>
							<tr>
								<th>密码：</th>
								<td>
									<s:password id="password" name="model.userPass"/>
								</td>
								<td><span id="err_password" class="error"><s:property value="errors['model.userPass']"/></span></td>
							</tr>
							<tr>
								<th>确认密码：</th>
								<td>
									<s:password id="confirm" name="confirm"/>
								</td>
								<td><span id="err_confirm" class="error"><s:property value="errors['confirm']"/></span></td>
							</tr>
							<tr>
								<th>性别：</th>
								<td>
								<s:radio name="model.gender" list="%{#{true:'男',false:'女'}}" theme="simple"></s:radio>
								</td>
							</tr>
							<tr>
								<th>电子邮件：</th>
								<td><s:textfield id="email" name="model.userMail"/></td>
								<td>
								<span id="err_email" class="error"></span>
								</td>
							</tr>
							<tr>
								<th>移动电话：</th>
								<td><s:textfield id="phone" name="model.phone"/></td>
								<td>
								<span id="err_phone" class="error"></span>
								</td>
							</tr>
							<tr>
								<th>注册身份：</th>
								<td>
								<s:select name="model.roleKey" list="roleMap" 
									 listKey="key" listValue="value"  value="model.roleKey"   />
								</td>
								</td>
							</tr>	
							<tr>
								<th>所在承办单位：</th>
								<td>
								<s:select name="model.orgId" list="orgMap" 
									 listKey="key" listValue="value"  value="model.orgId"   />
								</td>
								</td>
							</tr>			
							<tr>
								<th>审核状态：</th>
								<td>
								<s:select name="model.state" list="stateMap" 
									 listKey="key" listValue="value"  value="model.state"   />
								</td>
								</td>
							</tr>		
							<tr>
								<td></td>
								<td>													
								<s:submit value="保存"/>
								</td>
								<td></td>
							</tr>
						</tbody>
					</table>
				</s:form>
			</div>
		</div>
	</body>
</html>