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
    
    <title>My JSP 'updatepw.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="stylesheet" type="text/css" href="<%=basePath %>resources/css/front/updatepw.css">
	<script type="text/javascript" src="<%=basePath %>resources/js/jquery-3.1.1.min.js"></script>
	<script type="text/javascript" src="<%=basePath %>resources/js/updatepw.js"></script>
	<script type="text/javascript">
		$(function(){
			//切换信息板的背景颜色
			if("${successMsg}"){
				$("#errorDiv").css("background-color","#E2EEF9");
				$("#errorDiv").css("color","#6188AE");
			}else{
				$("#errorDiv").css("background-color","#FCDEDE");
				$("#errorDiv").css("color","#AC3737");
			}
		});
		
	
	</script>
	
  </head>
  
  <body>
  
   <div id="logoDiv">
   		<a href="<%=basePath%>main.html"><img src="<%=basePath%>resources/images/common/logo.png"></a>
   </div>
  
  	<c:if test="${!empty oldPasswordError || !empty successMsg }">
  	<div id="errorDiv">
  		<span id="oldPasswordErrorSpan">${oldPasswordError }</span>
  		<span id="successMsgSpan">${successMsg}</span>
  	</div>
    </c:if>
  
  	<div id="titleDiv">
  	修改密码
  	</div>
  	
  	
    <div id="formDiv">
    	<form action="<%=basePath%>user/doUpdatepw.html" method="post">
    		<div class="inputTitleDiv">旧密码</div>
    		<input type="password" name="oldPassword" class="input" id="oldPassword"/>
    		<label class="error" id="oldPasswordError"></label><br>
    		
    		<div class="inputTitleDiv">新密码</div>
    		<input type="password" name="newPassword" class="input" id="newPassword"/>
    		<label class="error" id="newPasswordError"></label><br>
    		
    		<div class="inputTitleDiv">确认新密码</div>
    		<input type="password" name="cnewPassword" class="input" id="cnewPassword"/>
    		<label class="error" id="cnewPasswordError"></label><br>
    		
    		<div id="submitDiv"><input type="submit" value="修改密码" id="submitBtn"> <label><a href="">忘记密码？</a></label></div>
    	</form>
    </div>
  </body>
</html>
