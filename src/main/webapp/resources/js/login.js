$(function(){
	/*
	 * 切换登录按钮的背景颜色
	 */
	$("#submit").hover(
		function(){
		$(this).css("background-color","#6CB74B");
		},
		function(){
		$(this).css("background-color","#72C44E");
	});
});