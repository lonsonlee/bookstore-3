<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'result.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript" src="<%=basePath%>resources/js/jquery-3.1.1.min.js"></script>
	<script type="text/javascript" src="<%=basePath %>resources/js/result.js"></script>
  </head>
  
  <body>
  <input type="hidden" value="${resultUser.email }" id="email"/>
  <input type="hidden" value="${resultUser.activationCode }" id="activationCode"/>
  <input type="hidden" value="<%=basePath%>" id="basePath"/>
  	<c:choose>
  		<c:when test="${flag eq 'registerSuccess' }">
  		 您已经成功注册，请到您的邮箱完成账户激活。<br><br>
           没收到？<input id="resend" type="button" value="重新发送"/><br>
  		</c:when>
  		<c:when test="${flag eq 'invalid' }">
  		您的激活码无效。
  		</c:when>
  	</c:choose>
          
          
  </body>
</html>
