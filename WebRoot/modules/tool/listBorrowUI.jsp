<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String actionName = "listBorrow.action";			
%>
<html>
	<head>
		<title>工具<s:if test="#session.role==1 || #session.role==3">管理</s:if></title>
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
			<s:if test="#session.role==1 || #session.role==3">
			function addEntity() {
				var addUrl = "url:<%=basePath%>inputTool.action";
				var vDialog = $.dialog({
					id: 'addWin',
					title: '工具管理',
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
			};
			
			
			function checkBorrow(entityKey) {
				var editUrl = "url:<%=basePath%>checkBorrow.action?key=" + entityKey;
				var vDialog = $.dialog({
					id: 'editWin',
					title: '工具借出审核',
					lock: true,
					content: editUrl,
					width: '600px',
					height: '500px',
					min: false,
					max: false,
					close: function() {
						listEntity();
				    }
				});
			};
			
			function backBorrow(entityKey) {
				var editUrl = "url:<%=basePath%>checkBackBorrow.action?key=" + entityKey;
				var vDialog = $.dialog({
					id: 'editWin',
					title: '工具归还审核',
					lock: true,
					content: editUrl,
					width: '600px',
					height: '500px',
					min: false,
					max: false,
					close: function() {
						listEntity();
				    }
				});
			};
			
			function listEntity() {			  
				document.forms[0].action = "<%=basePath%>listBorrow.action";			   
				document.forms[0].submit();	
			}
			</s:if>
			
			
		</script>
	</head>
	<body>
		<jsp:include page="/modules/common/header.jsp" />
		<s:form theme="simple">
			<div id="main" class="ks-clear">
				<jsp:include page="/modules/common/menu.jsp" />
				<div id="mi_container">
					<div class="module_01">
						<div class="hd">
							<h2>工具信息</h2>	
						</div>
						<div class="h20"></div>
						<div class="bd">
							<div class="info_list">
								<s:if test="%{entities != null && entities.size > 0}">
									<table width="100%">
										<thead>
											<tr>
									            <td>工具名字</td>
									            <td>编号与型号</td>
									            <td>申请数量</td>
									            <td>借出时间</td>
									            <td>应归还时间</td>
									            <td>状态</td>
									            <td>操作</td>
											</tr>
										</thead>
										<tbody>											
											<s:iterator value="entities">
												<tr>
													<td><s:property value="toolName" /></td>
													<td><s:property value="toolNo" /></td>
													<td><s:property value="broNum" /></td>
													<td>
														<s:if test="broTime != null">
															<s:date name="broTime" format="yyyy-MM-dd" />
														
														</s:if>
														<s:else>
															<font color="red">*</font>
														</s:else>
													</td>
													<td>
														<s:if test="broTime != null">
															<s:property value="getBackTime(broTime,broDay)" />
														</s:if>
														<s:else>
															<font color="red">*</font>
														</s:else>
													</td>
													<td>
														<s:if test="getStatus(id) == 1">已借出</s:if>
														<s:if test="getStatus(id) == 3">审核不通过</s:if>
														<s:if test="getStatus(id) ==2">已归还</s:if>
														
														<s:if test= '#session.role==2'><!-- 学生 -->
															<s:if test="broStatus==0">
																<label>未审核</label> 
															</s:if>
														</s:if>
													</td>
													
													<td width="120">
														
														<s:if test='#session.role==1 || #session.role==3'>  <!-- 管理员 -->
															<s:if test="getStatus(id) == 0"><input type="button" class="I_btn_01" onclick="checkBorrow('<s:property value="id" />');" value="审核"/></s:if>
															<s:if test="getStatus(id) != 1"><a href='deleteBorrow.action?key=<s:property value="id" />'>删除</a></s:if>
															<s:if test="getStatus(id) == 1"><a href='checkBackBorrow.action?key=<s:property value="id" />'>归还</a></s:if>
														</s:if>
													</td>
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
													<p>第 <s:property value="pager.getPageNo()"/> 页  共  <s:property value="pager.getTotalPages()" /> 页</p>
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