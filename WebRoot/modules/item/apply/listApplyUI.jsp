<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String actionName = "listApply.action";			
%>
<html>
	<head>
		<title>
			<s:if test="#session.role==1">项目审批</s:if>
			<s:else>我的项目</s:else>
		</title>
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
			<s:if test="#session.role==1 || #session.role==3 || #session.role==4">
			function editMark(entityKey) {
				var editUrl = "url:<%=basePath%>markApply.action?key=" + entityKey;
				var vDialog = $.dialog({
					id: 'editWin',
					title: '入录成绩',
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
			
			function uploadFile(entityKey) {
				var addUrl = "url:<%=basePath%>add2Sfile.action?key=" + entityKey;
				var vDialog = $.dialog({
					id: 'addWin',
					title: '资料库管理',
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
				var editUrl = "url:<%=basePath%>showSfile.action?key=" + entityKey;
				var vDialog = $.dialog({
					id: 'editWin',
					title: '资料库管理',
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
				document.forms[0].action = "<%=basePath%>listApply.action";			   
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
							<h2>
								<s:if test="#session.role==1 || #session.role==3">项目审批</s:if>
								<s:else>我的项目</s:else>
								
							</h2>
							<div id="arr_02"></div>
						</div>
						<div class="bd" id="hidden_box">
							<div class="condition">
							<s:if test="#session.role==1 || #session.role==3 || #session.role==4">
								<table width="100%">
									<tr>
										<th>
											项目名称：
										</th>
										<td>
											<s:select name="model.Item.Id" list="itemMap" 
									 			listKey="key" listValue="value"  value="model.Item.Id" cssClass="txt_01"  />
										</td>
										<th>
											审核状态：
										</th>
										<td>
											<s:select name="model.applyStatus" list="applyStatusMap" 
									 			listKey="key" listValue="value"  value="model.applyStatus" />
										</td>
									</tr>
									
								</table>
							</s:if>
							</div>
						</div>
						<s:if test="#session.role==1 || #session.role==3 || #session.role==4">
							<div class="h20"></div>
							<div class="btn_list">
								<input type="button" class="I_btn_02" value="查询" onclick="listEntity();" />
								<s:if test='model.Item.ItemName != ""'>
									<a href='<%=basePath %>servlet/DownExcel?in=<s:property value="model.Item.Id" />&as=<s:property value="model.applyStatus" />'>导出excel</a>
								</s:if>
								<p>只有选择项目才能导出excel</p>
								</div>
						<div class="h20"></div>
						</s:if>
						
						<div class="bd">
							<div class="info_list">
								<s:if test="%{entities != null && entities.size > 0}">
									<table width="100%">
										<thead>
											<tr>
												<td>项目名称</td>
												<td>参赛作品题目</td>
									            <td>操作</td>
											</tr>
										</thead>
										<tbody>											
											<s:iterator value="entities">
												<tr >
													<td>
														<s:property value="item.itemName" />
													</td>
													<td><s:property value="applyName" /></td>
													<td>
													
													<s:if test="#session.role==1 || #session.role==3|| #session.role==4">
														<s:if test="applyStatus==0">
															<a href='showApply.action?key=<s:property value="id" />'>审核</a>
														</s:if>
														<s:if test="#session.role==1 ">
															<s:if test="applyStatus==4">
																<a href='#' onclick="editMark('<s:property value="id" />');">入录成绩</a>
															</s:if>
														</s:if>
														<s:if test="applyStatus==1">
															<a href='#' onclick="uploadFile('<s:property value="id" />');">上传资料</a>
														</s:if>
														<a href='showApply.action?key=<s:property value="id" />'>查看</a>
														<s:if test="#session.role==1 ">
															<a href='deleteApply.action?key=<s:property value="id" />'>删除</a>
														</s:if>
													</s:if>
													<s:else>
														<a href='showApply.action?key=<s:property value="id" />'>查看</a>
														<s:if test="applyStatus == 0">审核中</s:if>
													</s:else>
													
													<s:if test="applyStatus == 1">审核通过</s:if>
													<s:if test="applyStatus == 2">审核未通过</s:if>
													
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