$(function(){
	
	
	
	/*
	 * 输入框得到焦点时，隐藏错误信息
	 */
	$(".input").focus(function(){
		var labelId=$(this).attr("id")+"Error";
		$("#"+labelId).text("");
	});
	
	/*
	 * 输入框失去焦点时，校验输入框信息
	 */
	$(".input").blur(function(){
		var id=$(this).attr("id");
		var fname="validate"+id.substring(0,1).toUpperCase()+id.substring(1)+"()";
		eval(fname);
	});
	
	$("#submitBtn").click(function(){
		var flag=true;
		if(!validateOldPassword()){
			flag=false;
		}
		if(!validateNewPassword())
			flag=false;
		if(!validateCnewPassword())
			flag=false;
		return flag;
	});
	
});

/**
 * 1.旧密码前端校验
 */
function validateOldPassword(){
	/*
	 * 旧密码不能为空
	 */
	var id="oldPassword";
	var value=$("#"+id).val();
	if(!value){
		$("#"+id+"Error").text("请填写此项");
		return false;
	}
	return true;
}

/**
 * 新密码前端校验
 */
function validateNewPassword(){
	/*
	 * 新密码不能为空
	 */
	var id="newPassword";
	var value=$("#"+id).val();
	if(!value){
		$("#"+id+"Error").text("请填写此项");
		return false;
	}
	
	/*
	 * 新密码长度必须在6到20之间
	 */
	if(value.length<6||value.length>20){
		$("#"+id+"Error").text("密码长度须在6到20位之间");
		return false;
	}
	return true;
}

/**
 * 确认密码前端校验
 */
function validateCnewPassword(){
	/*
	 * 确认密码不能为空
	 */
	var id="cnewPassword";
	var value=$("#"+id).val();
	if(!value){
		$("#"+id+"Error").text("请填写此项");
		return false;
	}
	
	/*
	 * 两次密码输入是否相等
	 */
	if(value!=$("#newPassword").val()){
		$("#"+id+"Error").text("两次输入不一致");
		return false;
	}
	
	return true;
}
