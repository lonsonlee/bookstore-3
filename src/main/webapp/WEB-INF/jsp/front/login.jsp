<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'login.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="stylesheet" type="text/css" href="<%=basePath %>resources/css/front/login.css">
	<script type="text/javascript" src="<%=basePath %>resources/js/jquery-3.1.1.min.js"></script>
	<script type="text/javascript" src="<%=basePath %>resources/js/login.js"></script>
	
	<!-- 判断输入框的内容：是显示回显的错误的输入信息 还是 cookie中的信息 -->
	<script type="text/javascript">
		$(function(){
			var username="${cookie.loginname.value}";
			var password="${cookie.loginpass.value}"
			
			//如果回显中存在数据，则显示 回显的数据，不存在则显示cookie中的数据
			if("${requestScope.username}"||"${requestScope.password}"){
				username="${requestScope.username}";
				password="${requestScope.password}";
			}
			$("#loginname").val(username);
			$("#password").val(password);
		});
	</script>

  </head>
  
  <body>
  	   <div id="titleDiv">
  	     	用户登录
  	   </div>
  	   
  	   <c:if test="${!empty error}">
  	   <div id="errorDiv">
  	   ${error}
  	   </div>
  	   </c:if>
  	   
	   <div id="formDiv">
	   		
	   		
	   	   <form action="<%=basePath%>user/doLogin.html" method="post">
	   	   	
	   	   		
		   	      <span class="text1">用户名/邮箱</span><br>
		   	      <input type="text" id="loginname" class="input" name="username"/><br>
		   	      
		   	      <div id="oneLineDiv">
		   	      <div id="passwordTextDiv"><span class="text1">密码</span></div>
		   	      
		   	      <div id="forgetTextDiv"><a href="">忘记密码？</a></div>
		   	      <div style="clear:both"></div>  
		   	      </div>
		   	      
		   	      <input type="password" id="password" class="input" name="password"/><br>
		   	      
		   	      <input type="checkbox" id="rememberMe" name="rememberMe"/>10天内记住我
	   	      		
	   	      	  <input type="submit" id="submit" class="input" value="登录"/>
	   	   </form>
	   	   <div id="registerTextDiv">没有账户？<a href="<%=basePath%>register.html">免费注册</a></div>
	   </div>
	   
  </body>
</html>
