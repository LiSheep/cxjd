<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String actionName = "listReportNews.action";	
%>
<html>
	<head>
		<title>通知文件<s:if test="#session.role==1 || #session.role==3">管理</s:if></title>
		<script type="text/javascript" src="<%=basePath%>js/jquery-1.7.1.min.js"></script>
		<script type="text/javascript" src="<%=basePath%>js/lhgdialog.min.js"></script>
		<script type="text/javascript" src="<%=basePath%>js/sideBar_menu.js"></script>
		<link href="<%=basePath%>css/tm/style/backstage.css" type="text/css" rel="stylesheet" />
        <link href="<%=basePath%>css/tm/style/colorbox.css" type="text/css" rel="stylesheet" />
        <link href="<%=basePath%>css/cxjd/main.css" type="text/css" rel="stylesheet" />
		<script type="text/javascript">	
			window.onload = function() {
				sideBar_menu();
				$("#arr_02").click(function() {
					if($("#hidden_box").css("display")=="none") {
						$("#hidden_box").show();
					} else {
						$("#hidden_box").hide();
					}
				});
			};
			
			function listEntity() {			  
				document.forms[0].action = "<%=basePath%>listReportNews.action";			   
				document.forms[0].submit();	
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
						<div class="hd">
							<h2>通知文件
							<s:if test="#session.role==1 || #session.role==3">管理</s:if></h2>
						</div>
						
						<div class="h20"></div>
						<s:if test="#session.role==1 || #session.role==3">
						<div class="btn_list">
							<input type="button" class="I_btn_02" onclick="window.location.href='<%=basePath%>inputReportNews.action'" value="添加"/>
						</div>
						</s:if>
						<div class="bd">
							<div class="info_list">
								<s:if test="%{entities != null && entities.size > 0}">
									<table width="100%">
										<thead>
											<tr>
												<td>标题</td>
									            <td>更新时间</td>
									            <s:if test="#session.role==1 || #session.role==3"><td>操作</td></s:if>
											</tr>
										</thead>
										<tbody>											
											<s:iterator value="entities">
												<tr>
													<td>
														<a href='detailReportNews.action?key=<s:property value="id" />'>
														<s:property value="newsTitle" /></a>
													</td>
													<td width="100"><s:date name="newsDate" format="yyyy-MM-dd" /></td>
													<s:if test="#session.role==1 || #session.role==3">
													<td width="120">
														<input type="button" class="I_btn_01" onclick="window.location.href='<%=basePath%>inputReportNews.action?key=<s:property value="id" />'" value="修改"/>
														<a href='deleteReportNews.action?key=<s:property value="id" />'>删除</a>
													</td>
													</s:if>
												</tr>
											</s:iterator>
										</tbody>
									</table>
										<div class="pager">
											<ul>
												<s:if test="pager.getPageNo() !=1 ">
												<li><a href="<%=basePath%><%=actionName%>?page=<s:property value="pager.getFirstPage()" />">首页</a></li>
												</s:if>
												<s:if test="pager.hasPre()">
												<li ><a href="<%=basePath%><%=actionName%>?page=<s:property value="pager.getPrePages()" />">上一页</a></li>
												</s:if>
												<s:if test="pager.hasNext()">
												<li><a href="<%=basePath%><%=actionName%>?page=<s:property value="pager.getNextPages()" />">下一页</a></li>
												</s:if>
												<s:if test="pager.getPageNo() != pager.getTotalPages()">
												<li ><a href="<%=basePath%>/<%=actionName%>?page=<s:property value="pager.getLastPage()" />">末页</a></li>
												</s:if>
												<li style="clear:both">
													<p>第 <s:property value="pager.getPageNo()"/> 页  共<s:property value="pager.getTotalPages()" /> 页</p>
													<p>共    <s:property value="pager.getTotalCount()"/> 条记录</p>
												</li>
											</ul>
										</div>									
								</s:if>
							</div>
						</div>
					</div>
				</div>
			</div>
		</s:form>
		<jsp:include page="/modules/common/footer.jsp"></jsp:include>
	</body>
</html>