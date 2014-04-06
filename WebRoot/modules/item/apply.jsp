<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ page import="com.cxjd.common.*" language="java" %>
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
		<title>项目申请表</title>
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
			var a = 1;
			function addTr(){
				var str = '<tr><td><input type="text" name="otherClaimer["+a+"].claimerName" /></td><td><select name="otherClaimer["+a+"].claimerSex"><option value="true">男</option><option value="false">女</option></select></td><td><input type="text" name="otherClaimer["+a+"].claimerNo" style="width:140px"/></td><td><input type="text" name="otherClaimer["+a+"].claimerCollege"/></td><td><input type="text" name="otherClaimer["+a+"].claimerPro" /></td><td><input type="text" name="otherClaimer["+a+"].claimerClass" style="width:120px"/></td></tr>';			
				$("#apply_table").append(str);a++;}
		
			$(function(){
				$("#error").hide();
				$("form").submit(function(){
					$("input").each(function(){
						if($(this).val() == ""){
							$("#error").show();
							return false;
						}
					});
				
					return true;
				});
			});
		</script>
	</head>
	<body>
		<jsp:include page="/modules/common/header.jsp" />
		<s:form theme="simple" action="doApplyItem">
			<input type="hidden" id="key" name="key"/>
			<input type="hidden" id="" name="apply.item.Id" value='<s:property value="apply.Item.Id" />' />
			<input type="hidden" id="" name="apply.ApplyUserKey" value='<s:property value="#session.userKey" />' />
			<div id="main" class="ks-clear">
				<jsp:include page="/modules/common/menu.jsp" />
				<div id="mi_container">
					<div class="module_01">
						<div class="hd">
							<h2>项目申请</h2>
						</div>
						<p id="error"><font color="red">*请组长完整填写该项目申请表</font></p>
						<div class="bd">
							<div class="info_list">
								<table id="apply_table">
									<tr><td colspan="6" class="bktd2"><b>项目信息</b></td></tr>
									<tr>
										<td style="width:60px" class="bktd">项目名称</td>
										<td><s:property value="apply.Item.ItemName" /></td>
										<td style="width:400px" class="bktd">报名截至时间</td><td><s:date name="apply.Item.itemTime" format="yyyy-MM-dd" /></td>
										<td class="bktd">举办学院</td><td colspan="5"><s:property value="apply.Item.itemCollege"/></td>
									</tr>
									<tr>
										<td class="bktd">参赛作品题目</td>
										<td colspan="5" style="margin-left:auto"><s:textfield name="apply.ApplyName" cssStyle="width:620px" /></td>
									</tr>
									<tr><td colspan="6" class="bktd2"><b>项目组长信息</b></td></tr>
									<tr>
										<td class="bktd">姓名</td><td><s:textfield name="apply.MainClaimer.ClaimerName" /></td>
										<td class="bktd">性别</td>
										<td>
											<s:select name="apply.MainClaimer.ClaimerSex" list="%{#{true:'男',false:'女'}}" 
											 listKey="key" listValue="value"  value="apply.mainClaimer.ClaimerSex" />
										</td>
										<td style="width:100px" class="bktd">学号</td>
										<td><s:textfield name="apply.MainClaimer.ClaimerNo" cssStyle="width:120px" /></td>
									</tr>
									<tr>
										<td class="bktd">学院</td>
										<td><s:textfield name="apply.MainClaimer.ClaimerCollege" /></td>
										<td class="bktd">专业</td>
										<td><s:textfield name="apply.MainClaimer.ClaimerPro" /></td>
										<td class="bktd">班级</td>
										<td><s:textfield name="apply.MainClaimer.ClaimerClass"  cssStyle="width:120px"  /></td>
									</tr>
									<tr><td colspan="6" class="bktd2"><b>其他组员信息</b></td></tr>
									<tr>
										<td class="bktd">姓名</td>
										<td class="bktd">性别</td>
										<td class="bktd">学号</td>
										<td class="bktd">学院</td>
										<td class="bktd">专业</td>
										<td class="bktd">班级</td>
									</tr>
									<tr>
										<td><input type="text" name="otherClaimer[0].claimerName" /></td>
										<td>
											<select name="otherClaimer[0].claimerSex">
												<option value="true">男</option>
												<option value="false">女</option>
											</select>
										</td>
										<td><input type="text" name="otherClaimer[0].claimerNo" style="width:140px"/></td>
										<td><input type="text" name="otherClaimer[0].claimerCollege"/></td>
										<td><input type="text" name="otherClaimer[0].claimerPro" /></td>
										<td><input type="text" name="otherClaimer[0].claimerClass" style="width:120px"/></td>
										
									</tr>
								</table>
								<span>
									<input type="button" class="I_btn_01" onclick="addTr()" value="增加一个组员" /> <br /> <br />
									<s:submit  value="提交申请" cssClass="I_btn_01"/>
								
								</span>
							</div>
						</div>
					</div>
				</div>
			</div>
		</s:form>
		<jsp:include page="/modules/common/footer.jsp"></jsp:include>
	</body>
</html>