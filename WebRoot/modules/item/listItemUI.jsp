<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String actionName = "listItem.action";			
%>
<html>
	<head>
		<title>项目管理</title>
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
		<s:if test="#session.role==1 || #session.role==4">	
			function addEntity() {
				var addUrl = "url:<%=basePath%>inputItem.action";
				var vDialog = $.dialog({
					id: 'addWin',
					title: '项目管理',
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
			
			
			function editEntity(entityKey) {
				var editUrl = "url:<%=basePath%>inputItem.action?key=" + entityKey;
				var vDialog = $.dialog({
					id: 'editWin',
					title: '项目管理',
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
			}
			</s:if>
			function listEntity() {			  
				document.forms[0].action = "<%=basePath%>listItem.action";			   
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
							<h2>项目  
								<s:if test="#session.role==1 || #session.role==4">管理</s:if>
								<s:else>申报</s:else>
							</h2>
						</div>
						
						<div class="h20"></div>
						<div class="btn_list">
						<s:if test="#session.role==1 || #session.role==4">
							<input type="button" class="I_btn_02" onclick="addEntity();" value="添加"/>
						</s:if>
						</div>
						<div class="bd">
							<div class="info_list">
								<s:if test="%{entities != null && entities.size > 0}">
									<table width="100%">
										<thead>
											<tr>
												<td>项目名称</td>
									            <td>举办学院</td>
									            <td>截止时间</td>
									            <td>操作</td>
											</tr>
										</thead>
										<tbody>											
											<s:iterator value="entities">
												<tr>
													<td>
														<s:property value="itemName" />
													</td>
													<td><s:property value="ItemCollege" /></td>
													<td width="100"><s:date name="ItemTime" format="yyyy-MM-dd" /></td>
													
													<td width="120">
													<s:if test="#session.role==1 || #session.role==4">
														<input type="button" class="I_btn_01" onclick="editEntity('<s:property value="id" />')" value="修改"/>
														<a href='deleteItem.action?key=<s:property value="id" />'>删除</a>
													</s:if>
													<s:else>
														<s:if test="!ifOutNotDate(ItemTime)">
															<a href='applyItem.action?key=<s:property value="id" />'>申报</a>
														</s:if>
														<s:else>
															<a href="#">项目已过期</a>
														</s:else>
													</s:else>
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