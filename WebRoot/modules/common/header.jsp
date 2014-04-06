<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
			%>
<div id="header" class="ks-clear">
	<div class="hd_sideBar"></div>
	<div class="hd_container">
		<div class="logo">
			<img src="css/tm/images/logo.png" />
		</div>
		<p class="version">v1.1.0</p>
		<div class="infoBox">
			你好，<s:property value="#session.name"/> <s:property value="#session.roleName" />
			<input type="button" value="退出" class="btn_01" onclick="javascript:window.location.href='<%=basePath%>logout.action'" />
			<input type="button" value="修改密码" class="btn_01" onclick="changepass()" />
			
			<script type="text/javascript">
				function changepass() {
				var addUrl = "url:<%=basePath%>changepass.action";
				var vDialog = $.dialog({
					id: 'addWin',
					title: '密码修改',
					lock: true,
					content: addUrl,
					width: '600px',
					height: '500px',
					min: false,
					max: false,
					close: function() {
						listEntity();
				    }
				});
			}
			</script>
			
		</div>
	</div>
</div>