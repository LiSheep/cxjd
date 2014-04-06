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
		<title></title>
		<script type="text/javascript" src="<%=basePath%>js/jquery-1.9.1.js"></script>
		<script type="text/javascript" src="<%=basePath%>js/lhgdialog.min.js"></script>
		<script type="text/javascript" src="<%=basePath%>js/sideBar_menu.js"></script>
		<script type="text/javascript" src="<%=basePath%>js/My97DatePicker/WdatePicker.js"></script>
		<link type="text/css" rel="stylesheet" href="<%=basePath%>css/tm/style/backstage.css" />
        <link type="text/css" rel="stylesheet" href="<%=basePath%>css/tm/style/colorbox.css" />
       	<link rel="stylesheet" href="<%=basePath%>css/cxjd/main.css" type="text/css"></link>
		
		<link rel="stylesheet" href="<%=basePath%>css/jquery-ui-1.10.3.custom.min.css" type="text/css"></link>
		<script type="text/javascript" src="<%=basePath%>js/jquery-ui-1.10.3.custom.min.js"></script>
		
		<script type="text/javascript" src="<%=basePath%>js/common.js"></script>	
        
		<script type="text/javascript">
			
			function isDateGen(elementId) {
			trim(elementId);
			var eleValue = $("#"+elementId).val();
			if(!eleValue.match(/^(\d{1,4})(-|\/)(\d{1,2})\2(\d{1,2})$/)) {
				$("#"+elementId).focus();
				$("#"+elementId).select();
				return false;
		    }
		    return true;
		}
		
			$(function(){
				$("form").submit(function(){
					if(isEmpty("itemname")){
						$("#err_itemname").html("*项目名不能为空");
						return false;
					}else{ 
						$("#err_itemname").html("");
					}
					if(isEmpty("itemTime")){
						$("#err_itemTime").html("*报名截止时间不能为空");
						return false;
					}else if(!isDateGen("itemTime")){
						$("#err_itemTime").html("*报名截止时间格式不正确");
						return false;
					}
					else{ 
						$("#err_itemTime").html("");
					}
					if(isEmpty("itemCollege")){
						$("#err_itemCollege").html("*举办学院不能为空");
						return false;
					}else{ 
						$("#err_itemCollege").html("");
					}
					
					alert("添加成功！");
					parent.listEntity(); 
				});
				
			});
		 	$(function() {
    $( "#itemTime" ).datepicker({
      showOtherMonths: true,
      selectOtherMonths: true,
      dateFormat: "yy-mm-dd"
    });
  });
		</script>
	</head>
	<body>

		<div class="project_pop">
			<div class="hd">修改项目</div>
			<div class="exp">请输入项目信息：</div>
			<div class="bd">
				<s:form id="addItem_form" theme="simple" action="updateItem">
				<s:hidden name="model.id" />
					<table>
						<tbody>	
							<tr>
								<th>项目名：</th>
								<td>
									<s:textfield id="itemname" name="model.itemName" />
								</td>
								<td><span id="err_itemname" class="error"></span></td>
							</tr>
							<tr>
								<th>报名截止时间：</th>
								<td>
									<input type="text" id="itemTime" name="itemTime" value='<s:property value="transDateTime(model.itemTime)" />' />
								</td>
								<td><span id="err_itemTime" class="error"></span></td>
							</tr>
							<tr>
								<th>举办学院：</th>
								<td>
									<s:textfield id="itemCollege" name="model.itemCollege" />
								</td>
								<td><span id="err_itemCollege" class="error"></span></td>
							</tr>
							<tr>
								<td></td>
								<td><s:submit value="保存"/></td>
								<td></td>
							</tr>
						</tbody>
					</table>
				</s:form>
			</div>
		</div>
	</body>
</html>