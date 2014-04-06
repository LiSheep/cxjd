$(function(){
			$("form").submit(function(){
				if(isEmpty("broDay")){
					$("#err_broDay").html("*需要租借时间不能为空");
					return false;
				}else{
					$("#err_broDay").html();
				}
				if(!isNumber("broDay")){
					$("#err_broDay").html("*需要租借时间应为数字");
					return false;
				}else{
					$("#err_broDay").html();
				}
				
				if(isEmpty("broNum")){
					$("#err_broNum").html("*需要租借数量不能为空");
					return false;
				}else{
					$("#err_broNum").html();
				}
				if(!isNumber("broNum")){
					$("#err_broNum").html("*需要租借数量应为数字");
					return false;
				}else{
					$("#err_broNum").html();
				}
				
			});
		});