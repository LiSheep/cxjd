$(function(){
			$("form").submit(function(){
				if(isEmpty("username")){
					$("#err_username").html("*用户名不能为空");
					return false;
				}else{
					$("#err_username").html("");
				}
				if(isBiggerThan("username",3)){
					$("#err_username").html("*用户名不能少于3个字符");
					return false;
				}else{
					$("#err_username").html("");
				}
				var isadd;//判断是否是添加，如果不是添加则密码可以为空
				if($("#mode").val() == "add")
					isadd = true;
				else
					isadd = false;
				
				if(isEmpty("password")){
					if(isadd){
						$("#err_password").html("*密码不能为空");
						return false;
					}
				}else{
					$("#err_password").html("");
					if(isBiggerThan("password", 5)){
						$("#err_password").html("*密码不能少于5个字符");
						return false;
					}else{
						$("#err_password").html("");
					}
				}
				if(isEmpty("confirm")){
					if(isadd){
						$("#err_confirm").html("*请再次输入密码");
						return false;
					}
				}else{
					$("#err_confirm").html("");
					var password = $("#password").val();
					var confirm = $("#confirm").val();
					
					if(password != confirm){
						$("#err_confirm").html("*两次输入密码不一致");
						return false;
					}else{
						$("#err_confirm").html("");
					}
				}
				if(isEmpty("email")){
					$("#err_email").html("*请填写电子邮箱地址");
					return false;
				}else{
					$("#err_email").html("");
				}
				if(!isEmail("email")){
					$("#err_email").html("*请正确填写电子邮箱号码");
					return false;
				}else{
					$("#err_email").html("");
				}
				
				if(isEmpty("phone")){
					$("#err_phone").html("*请填写电话号码");
					return false;
				}else{
					$("#err_phone").html("");
				}
				if(!isMobile("phone")){
					$("#err_phone").html("*请正确填写电话号码");
					return false;
				}else{
					$("#err_phone").html("");
				}
				
			});
		});