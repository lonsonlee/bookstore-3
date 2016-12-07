$(function(){
	
	
	/*
	 * 1.切换 register.jsp 注册按钮的背景颜色
	 */
	$("#submitBtn").hover(
			function(){
				$(this).css("background-color","#6CB74B");
				},
				function(){
				$(this).css("background-color","#72C44E");
				}
	);
	/*
	 * 2.输入框得到焦点，隐藏错误提示
	 */
	$(".inputClass").focus(function(){
		var labelId=$(this).attr("id")+"Error";
		$("#"+labelId).text("");
	});
	
	/*
	 * 3.输入框失去焦点，进行校验并提示错误信息
	 */
	$(".inputClass").blur(function(){
		var id=$(this).attr("id");
		var fName="validate"+id.substring(0,1).toUpperCase()+id.substring(1)+"()";
		eval(fName);
	});
	
	/*
	 * 4.生成验证码
	 */
	 $("#imgCaptcha").click(function () {
	        $(this).hide().attr('src', './kaptcha/getKaptchaImage.html?' + Math.floor(Math.random()*100) ).fadeIn();
	        event.cancelBubble=true;
	    });
	 
	
		 /*
		  * 提交按钮校验
		  */
	$("#registerForm").submit(function(){
			 var flag=true;
			 	if(!validateLoginname()){
			 		flag=false;
			 	}
			 		
			 	if(!validateLoginpass()){
			 		flag=false;
			 	}
			 	if(!validateReloginpass()){
			 		flag=false;
			 	}
			 	if(!validateEmail()){
			 		flag=false;
			 	}
			 	if(!validateCaptcha()){
			 		flag=false;
			 	}
			 	return flag;
		 });
		 
	
});




window.onbeforeunload = function(){
    //关闭窗口时自动退出
    if(event.clientX>360&&event.clientY<0||event.altKey){   
        alert(parent.document.location);
    }
};

/*
 * 切换验证码
 */
function changeCode() {
	$("#captcha").val("");
    $("#imgCaptcha").hide().attr('src', './kaptcha/getKaptchaImage.html?' + Math.floor(Math.random()*100) ).fadeIn();
    event.cancelBubble=true;
}




/*
 * 3.1 用户名校验
 */
function validateLoginname(){
	/*
	 * 1. 用户名不能为空
	 */
	var id="loginname";
	var value=$("#"+id).val();
	if(!value){
		$("#"+id+"Error").text("用户名不能为空");
		return false;
	}
	/*
	 * 2. 用户名长度必须在3~20之间
	 */
	if(value.length<4||value.length>20){
		$("#"+id+"Error").text("用户名长度必须在4到20之间");
		return false;
	}
	
	/*
	 * 3.检验用户名是否已被注册
	 */
	$.ajax({
		url:$("#basePath").val()+"user/loginname.html",
		data:{loginname:value},
		type:"POST",
		dataType:"json",
		async:false,
		cache:false,
		success:function(data){
			if(!data){
				$("#"+id+"Error").text("用户名已被注册");
				return false;
			}
		}
	});
	return true;
}

/*
 * 3.2 密码校验
 */
function validateLoginpass(){
	/*
	 * 1. 密码不能为空
	 */
	var id="loginpass";
	var value=$("#"+id).val();
	if(!value){
		$("#"+id+"Error").text("密码不能为空");
		return false;
	}
	/*
	 * 2. 密码长度必须在3~20之间
	 */
	if(value.length<6||value.length>20){
		$("#"+id+"Error").text("密码长度必须在6到20之间");
		return false;
	}
	return true;
}
/*
 * 3.3 确认密码校验
 */
function validateReloginpass(){
	/*
	 * 1. 确认密码不能为空
	 */
	var id="reloginpass";
	var value=$("#"+id).val();
	if(!value){
		$("#"+id+"Error").text("确认密码不能为空");
		return false;
	}
	/*
	 * 2. 两次密码是否一致
	 */
	if(value!=$("#loginpass").val()){
		$("#"+id+"Error").text("两次密码输入不一致");
		return false;
	}
	
	return true;
}

/*
 * 3.4 email输入框校验
 */
function validateEmail(){
	/*
	 * 1. email不能为空
	 */
	var id="email";
	var value=$("#"+id).val();
	if(!value){
		$("#"+id+"Error").text("Email不能为空");
		return false;
	}
	/*
	 * 2. email格式校验
	 */
	if(!/^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\.[a-zA-Z0-9_-]{2,3}){1,2})$/.test(value)){
		$("#"+id+"Error").text("Email格式错误");
		return false;
	}
	/*
	 * 3. email是否已被注册
	 */
	$.ajax({
		url:$("#basePath").val()+"user/email.html",
		data:{email:value},
		type:"POST",
		dataType:"json",
		cache:false,
		async:false,
		success:function(data){
			if(!data){
				$("#"+id+"Error").text("Email已被注册");
				return false;
			}
			
		}
	});
	return true;
}

/* 
 * 3.5 验证码输入框校验
 */
function validateCaptcha(){
	/*
	 * 1. 验证码不能为空
	 */
	var id="captcha";
	var value=$("#"+id).val();
	if(!value){
		$("#"+id+"Error").text("验证码不能为空");
		return false;
	}
	/*
	 * 2. 验证码长度只能等于4
	 */
	if(value.length!=4){
		$("#"+id+"Error").text("验证码不正确");
		return false;
	}
	
	/*
	 * 3. 验证码是否正确
	 */
	$.ajax({
		url:$("#basePath").val()+"user/captcha.html",
		data:{captcha:value},
		type:"POST",
		dataType:"json",
		async:false,
		cache:false,
		success:function(data){
			if(!data){
				$("#"+id+"Error").text("验证码不正确");
				return false;
			}
		}
	});
	return true;
}