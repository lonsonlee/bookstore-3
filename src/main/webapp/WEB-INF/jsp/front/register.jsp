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
    
    <title>My JSP 'register.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="stylesheet" type="text/css" href="<%=basePath%>resources/css/front/register.css">
	<script type="text/javascript" src="<%=basePath%>resources/js/jquery-3.1.1.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>resources/js/register.js"></script>
  </head>
  
  <body>
  
  <div id="divForm">
  
  <div id="divFirstLine">
  <div id="divTitle">
    用户注册
  </div>
  
  <div id="divLogin">
  	已有账户？<a href="<%=basePath%>login.html">马上登录</a>
  </div>
  <div style="clear:both"></div>  
  </div>
  
<form action="<%=basePath%>user/doRegister.html" method="post" id="registerForm">
	<input type="hidden" id="basePath" value="<%=basePath%>"/> 
    <table id="tableForm">
      <tr>
        <td class="tdText">用户名</td>
        <td class="tdInput">
          <input class="inputClass" type="text" name="loginname" id="loginname" value="${form.loginname }"/>
        </td>
        <td class="tdError">
          <label class="errorClass" id="loginnameError"></label>
        </td>
      </tr>
      <tr>
        <td class="tdText">登录密码</td>
        <td class="tdInput">
          <input class="inputClass" type="password" name="loginpass" id="loginpass" value="${form.loginpass }"/>
        </td>
        <td>
          <label class="errorClass" id="loginpassError">${errors.loginpass }</label>
        </td>
      </tr>
      <tr>
        <td class="tdText">确认密码</td>
        <td class="tdInput">
          <input class="inputClass" type="password" name="reloginpass" id="reloginpass" value="${form.reloginpass }"/>
        </td>
        <td>
          <label class="errorClass" id="reloginpassError">${errors.reloginpass}</label>
        </td>
      </tr>
      <tr>
        <td class="tdText">Email</td>
        <td class="tdInput">
          <input class="inputClass" type="text" name="email" id="email" value="${form.email }"/>
        </td>
        <td>
          <label class="errorClass" id="emailError">${errors.email}</label>
        </td>
      </tr>
      <tr>
        <td class="tdText">验证码</td>
        <td class="tdInput">
          <input class="inputClass" type="text" name="captcha" id="captcha" value="${form.verifyCode }"/>
        </td>
        <td>
          <label class="errorClass" id="captchaError">${errors.verifyCode}</label>
        </td>
      </tr>
      <tr>
        <td></td>
        <td class="tdInput">
          <div id="divCaptcha"><img id="imgCaptcha" src="<c:url value='/kaptcha/getCaptcha.html'/>"/></div>
        </td>
        <td>
          <label><a href="javascript:changeCode()">看不清?换一张</a></label>
        </td>
      </tr>
      <tr>
        <td></td>
        <td class="tdInput">
          <input type="submit" value="立即注册" id="submitBtn" class="inputClass"/>
        </td>
        <td>
          <label></label>
        </td>
      </tr>
    </table>
</form>    
  </div>

  </body>
</html>
