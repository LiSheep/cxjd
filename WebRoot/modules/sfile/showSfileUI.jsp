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
		<script type="text/javascript" src="<%=basePath%>js/jquery-1.7.1.min.js"></script>
		<script type="text/javascript" src="<%=basePath%>js/lhgdialog.min.js"></script>
		<script type="text/javascript" src="<%=basePath%>js/sideBar_menu.js"></script>
		<script type="text/javascript" src="<%=basePath%>js/My97DatePicker/WdatePicker.js"></script>
		<link type="text/css" rel="stylesheet" href="<%=basePath%>css/tm/style/backstage.css" />
        <link type="text/css" rel="stylesheet" href="<%=basePath%>css/tm/style/colorbox.css" />
       	<link rel="stylesheet" href="<%=basePath%>css/cxjd/main.css" type="text/css"></link>
		<script type="text/javascript" src="<%=basePath%>js/common.js"></script>	
		<link rel="stylesheet" href="<%=basePath%>css/jquery-ui-1.10.3.custom.min.css" type="text/css"></link>
		<style type="text/css">
			#addSfile_form{
				padding:20px 0 0 0;
			}
			table tr{
				padding-top:10px;
			}
		</style>
		<script type="text/javascript" src="<%=basePath%>js/jquery-ui-1.10.3.custom.min.js"></script>
		
		<script type="text/javascript">
		
			$(function(){
				$("form").submit(function(){
					if(isEmpty("SfileTitle")){
						$("#err_SfileTitle").html("*资料标题不能为空");
						return false;
					}else{ 
						$("#err_SfileTitle").html("");
					}
					if(isEmpty("资料说明")){
						$("#err_SfileComment").html("*资料说明不能为空");
						return false;
					}else{ 
						$("#err_SfileComment").html("");
					}
					alert("添加成功！");
					parent.listEntity(); 
				});
				
			});
			
		</script>
	</head>
	<body>

		<div class="project_pop">
			<div class="hd">下载资料</div>
			<div class="bd">
				<s:form id="addSfile_form" theme="simple" >
					<table>
						<tbody>	
							<tr>
								<th>资料标题：</th>
								<td>
									<s:property value="model.SfileTitle" />
								</td>
							</tr>
							<tr>
								<th>资料说明：</th>
								<td>
									<s:property value="model.SfileComment" />
								</td>
							</tr>
							<tr>
								<th>附件：</th>
								<td>
								<p style="font-size:15px">
									<a href='
									<s:url action="downSfile">
										<s:param name="fileName" value="model.SfileNewName" />
										<s:param name="fileOldName" value="model.SfileOldName" />
										<s:param name="fileOldName" value="model.SfileOldName" />
										<s:param name="fileType" value="model.SfileOldName" />
										<s:param name="model" value="model" />
									</s:url>
									'>
										<s:property value="model.SfileOldName" />
									</a>
								</p>
								</td>							
							</tr>
						</tbody>
					</table>
				</s:form>
			</div>
		</div>
	</body>
</html>