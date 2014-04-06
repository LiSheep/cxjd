<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<html>
	<head>
		<title>创新基地管理系统</title>
		<script type="text/javascript" src="<%=basePath%>js/jquery-1.7.1.min.js"></script>
		<script type="text/javascript" src="<%=basePath%>js/lhgdialog.min.js"></script>
		<script type="text/javascript" src="<%=basePath%>js/sideBar_menu.js"></script>
		<link href="<%=basePath%>css/tm/style/backstage.css" type="text/css" rel="stylesheet" />
        <link href="<%=basePath%>css/tm/style/colorbox.css" type="text/css" rel="stylesheet" />
		<script type="text/javascript">	
			window.onload = function() {
				sideBar_menu();
				$("#arr_02").click(function() {
					if($("#hidden_box").css("display")=="none") {
						$("#hidden_box").show();
					} else {
						$("#hidden_box").hide();
					}
				})
			}
			
			function listEntity() {			  
				window.location.href = "main.action";
			}
		</script>
	</head>
	<body>
		<jsp:include page="/modules/common/header.jsp" />
		<s:form theme="simple">
			<input type="hidden" id="key" name="key"/>
			<div id="main" class="ks-clear">
				<jsp:include page="/modules/common/menu.jsp" />
				<div id="mi_container">
					<div class="module_01">
						
						<div class="bd">
							<div class="info_list">
								
							</div>
						</div>
					</div>
				</div>
			</div>
		</s:form>
		<jsp:include page="/modules/common/footer.jsp"></jsp:include>
	</body>
</html>