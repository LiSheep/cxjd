<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String actionName = "showItem.action";			
%>
<html>
	<head>
		<title>项目申请表 <s:if test="#session.role==1 || #session.role==3"> 审核</s:if></title>
		<script type="text/javascript" src="<%=basePath%>js/jquery-1.7.1.min.js"></script>
		<script type="text/javascript" src="<%=basePath%>js/lhgdialog.min.js"></script>
		<script type="text/javascript" src="<%=basePath%>js/sideBar_menu.js"></script>
		<link href="<%=basePath%>css/tm/style/backstage.css" type="text/css" rel="stylesheet" />
        <link href="<%=basePath%>css/tm/style/colorbox.css" type="text/css" rel="stylesheet" />
        <style type="text/css">/*style-css-table*/
           #apply_table{
			font-size: 12px;			
		  }
		  /*哥哥从这里开始改起*/
		  #apply_table input{
		  	width:110px;
			height:20px;
		  }
		    input{background:#f4f4f4;}
			.bktd{background:#d8dfea;}
		   .bktd2{background:#3c6cb7;color:#ffffff;}
		   /*哥哥从这里开始改起*/
		</style>
		
		<script type="text/javascript">
			function checkOK(key){
				window.location.href="checkOKApply.action?key="+key;
			}
			function checkNO(key){
				window.location.href="checkNOApply.action?key="+key;
			}
			function back(){
				window.location.href="listApply.action";
			}
		</script>
		
	</head>
	<body>
		<jsp:include page="/modules/common/header.jsp" />
		<s:form theme="simple" action="">
			<input type="hidden" id="key" name="key"/>
			<input type="hidden" id="" name="model.item.Id" value='<s:property value="apply.Item.Id" />' />
			<div id="main" class="ks-clear">
				<jsp:include page="/modules/common/menu.jsp" />
				<div id="mi_container">
					<div class="module_01">
						<div class="hd">
							<h2>项目申请<s:if test="#session.role==1 || #session.role==3"> 审核</s:if></h2>
						</div>
						<div class="bd">
							<div class="info_list">
								<table id="apply_table" width="735px">
									<tr><td colspan="6" class="bktd">项目信息</td></tr>
									<tr>
										<td width="107px" class="bktd">项目名称</td>
										<td><s:property value="model.Item.ItemName" /></td>
										<td class="bktd">报名截至时间</td><td><s:date name="model.Item.itemTime" format="yyyy-MM-dd" /></td>
										<td class="bktd">举办学院</td><td><s:property value="model.Item.itemCollege" /></td>
									</tr>
									<tr>
										<td class="bktd">参赛作品题目</td>
										<td colspan="5"><s:property value="model.ApplyName" /></td>
									</tr>
									<tr><td colspan="6" class="bktd">项目组长信息</td></tr>
									<tr>
										<td class="bktd">姓名</td><td><s:property value="model.MainClaimer.ClaimerName" /></td>
										<td class="bktd">性别</td>
										<td>
											<s:if test="model.MainClaimer.ClaimerSex">
												男
											</s:if>
											<s:else>
												女
											</s:else>
										</td>
										<td style="width:100px" class="bktd">学号</td>
										<td><s:property value="model.MainClaimer.ClaimerNo" /></td>
									</tr>
									<tr>
										<td class="bktd">学院</td>
										<td><s:property value="model.MainClaimer.ClaimerCollege" /></td>
										<td class="bktd">专业</td>
										<td><s:property value="model.MainClaimer.ClaimerPro" /></td>
										<td class="bktd">班级</td>
										<td><s:property value="model.MainClaimer.ClaimerClass"  /></td>
									</tr>
									
									<s:if test="%{model.otherCliamers != null && model.otherCliamers.size() > 0}">
									<tr><td colspan="6" class="bktd">其他组员信息</td></tr>
									<tr>
										<td class="bktd">姓名</td>
										<td class="bktd">性别</td>
										<td class="bktd">学号</td>
										<td class="bktd">学院</td>
										<td class="bktd">专业</td>
										<td class="bktd">班级</td>
									</tr>
									
									<s:iterator value="model.otherCliamers">
									<tr>
										<td><s:property value="claimerName" /></td>
										<td>
											<s:if test="ClaimerSex">
												男
											</s:if>
											<s:else>
												女
											</s:else>
										</td>
										<td><s:property value="claimerNo" /></td>
										<td><s:property value="claimerCollege"/></td>
										<td><s:property value="claimerPro"/></td>
										<td><s:property value="claimerClass" /></td>
									</tr>
									</s:iterator>
									</s:if>
								</table>
									<div class="btn_group">
										<s:if test='#session.role==1 || #session.role==3'>
											<span><input type="button" class="I_btn_01" value="审核通过" onclick="checkOK('<s:property value="model.id" />');" /></span>
											<span><input type="button" class="I_btn_01" value="审核不通过" onclick="checkNO('<s:property value="model.id" />');" /></span>
										</s:if>
										<span><input type="button" class="I_btn_01" value="返回" onclick="back();" /></span>
									</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</s:form>
		<jsp:include page="/modules/common/footer.jsp"></jsp:include>
	</body>
</html>