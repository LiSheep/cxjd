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
			$("form").submit(function(){
				
				if(isEmpty("oldpass")){
					$("#err_oldpass").html("*密码不能为空");
					return false;
				}else{
					$("#err_oldpass").html("");
					if(isBiggerThan("oldpass", 5)){
						$("#err_oldpass").html("*密码不能少于5个字符");
						return false;
					}else{
						$("#err_oldpass").html("");
					}
				}
				if(isEmpty("newpass")){
					$("#err_newpass").html("*密码不能为空");
					return false;
				}else{
					$("#err_newpass").html("");
					if(isBiggerThan("newpass", 5)){
						$("#err_newpass").html("*密码不能少于5个字符");
						return false;
					}else{
						$("#err_newpass").html("");
					}
				}
				if(isEmpty("confirm")){
					$("#err_confirm").html("*请再次输入密码");
					return false;
				}else{
					$("#err_confirm").html("");
					var password = $("#newpass").val();
					var confirm = $("#confirm").val();
					if(password != confirm){
						$("#err_confirm").html("*两次输入密码不一致");
						return false;
					}else{
						$("#err_confirm").html("");
					}
				}
			});
		});
		</script>
	</head>
	<body>

		<div class="project_pop">
			<div class="hd">密码修改</div>
			<div class="exp"></div>
			<div class="bd">
				<s:form id="addRole_form" theme="simple" action="dochangepass">
					<table>
						<tbody>	
							<tr>
								<td></td>
								<th><s:fielderror name="err_oldpass" cssClass="error" /></th>
							</tr>
							<tr>
								<th>请输入原密码：</th>
								<td>
									<s:password id="oldpass" name="oldpass" />
								</td>
								<td><span id="err_oldpass" class="error"></span></td>
							</tr>
							<tr>
								<th>请输入新密码：</th>
								<td>
									<s:password id="newpass" name="newpass" />
								</td>
								<td><span id="err_newpass" class="error"></span></td>
							</tr>
							<tr>
								<th>请确认新密码：</th>
								<td>
									<s:password id="confirm" name="confirm" />
								</td>
								<td><span id="err_confirm" class="error"></span></td>
							</tr>
							<tr>
								<td></td>
								<td><s:submit value="修改"/></td>
								<td></td>
							</tr>
						</tbody>
					</table>
				</s:form>
			</div>
		</div>
	</body>
</html>