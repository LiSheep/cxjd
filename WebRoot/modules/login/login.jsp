<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>创新基地管理系统</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<script type="text/javascript" src="<%=basePath%>js/jquery-1.7.1.min.js"></script>	
	<script type="text/javascript" src="<%=basePath%>js/common.js"></script>	
	<script type="text/javascript">
		$(function(){
			$("#login_form").submit(function(){
				if(isEmpty("account")){
					$("#err_account").html("*用户名不能为空");
					return false;
				}else{
					$("#err_account").html("");
				}
				if(isBiggerThan("account",3)){
					$("#err_account").html("*用户名不能少于3个字符");
					return false;
				}else{
					$("#err_account").html("");
				}
				
				if(isEmpty("password")){
					$("#err_password").html("*密码不能为空");
					return false;
				}else{
					$("#err_password").html("");
				}
				if(isBiggerThan("password", 5)){
					$("#err_password").html("*密码不能少于5个字符");
					return false;
				}else{
					$("#err_password").html("");
				}
			});
		});
	</script>
	
	<style type="text/css">
		*{
			margin:0;
			padding:0;
		}
		#login_form{
			margin-left:auto;
			margin-right:auto;
		}
		#bk{
			background:url(<%=basePath%>css/images/mainbk.jpg );
			background-position:center;
			height:770px;
		}
		#title{
			margin-left:auto;
			margin-right:auto;
			text-align:center;
			font-size:50px;
			padding:100px 0 0 36px;
			font-family:Arial,Helvetica,sans-serif;
			position:relative;
			letter-spacing:15px;
			font-weight:bold;
			text-shadow: 0 -1px rgba(0, 0, 0, 0.4);
		}
		
		
		
		#login_form{
			padding:200px 0 0 580px;
			height:380px;
		}
		
		.inp{
			height:27px;
		}
		
		#loginform{
			font-size:27px;
		}
		#login_form p{
			padding-top:10px;
		}
		#btn{
			height:30px;
			width:80px;
		}
		
		.error{
			font-color:red;
		}
		
		#foot p{
			text-align:center;
			font-weight:bold;
		}
		#zhuce{
			padding-left:27px;
		}
		#btnhold{
			padding-left:120px;
		}
	</style>
  </head>
  
  <body>
  	<div id="bk">
  		<h1 id="title">创               新               基               地               管               理               系               统</h1>
		<form id="login_form" action="login.action" method="post">
			
			<table id="loginform">
				<tr>
					<td>用户名：</td>
					<td>
						<input type="text" class="inp" id="account" name="account" placeholder="请输入用户名或邮箱" >
						<span id="err_account" class="error"></span>
					</td>
				</tr>
				<tr>
					<td>密     码：</td>
					<td>
						<input type="password" class="inp" id="password" name="password" placeholder="请输入用户密码" >
						<span id="err_password" class="error"></span>
					</td>
				</tr>
			</table>
			<p>
				<span id="btnhold"><input id="btn" type="submit" value="登录"/></span> 
				<a id="zhuce" href="register.action">注册</a>
				<span class="error"><s:property value="errors['account']"/></span>
			</p>
		</form>
		<div id="foot">
			<p>2012年大学生创新创业训练计划项目 作品</p>			
		</div>
		</div>
  </body>
</html>
