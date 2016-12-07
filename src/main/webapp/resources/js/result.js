$(function(){
	$("#resend").click(function(){
		
		var countdown=60;
		var _this=$(this);
		
		//设置button效果
		_this.attr("disabled","true");
		_this.val(countdown+"秒后重新发送");
		
		//启动计时器，开始计时
		
		var timer=setInterval(function(){
			if(countdown==0){
				clearInterval(timer);//停止计时器
				_this.removeAttr("disabled");//启动按钮
				_this.val("重新发送");
			}else{
				countdown--;
				_this.val(countdown+"秒后重新发送");
			}
		},1000);
		
		//请求服务器重新发送邮件
		$.ajax({
			url:$("#basePath").val()+"user/resend.html",
			data:{email:$("#email").val(),activationCode:$("#activationCode").val()},
			type:"POST",
			dataType:"json",
			cache:false,
			success:function(data){
				if(data){
					alert("邮件已重新发送");
				}
			}
		});
		
	});
	
	
	
	
});