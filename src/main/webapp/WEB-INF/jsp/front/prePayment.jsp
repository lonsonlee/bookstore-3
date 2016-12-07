<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>prePayment.jsp</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="stylesheet" type="text/css" href="<%=basePath%>resources/css/front/prePayment.css">
	<script type="text/javascript" src="<%=basePath%>resources/js/jquery-3.1.1.min.js"></script>

  </head>
  
  <body>
<div class="orderMsgDiv">
	<span class="payTextSpan">支付金额：</span><span class="price_t">&yen;${total }</span>
	<span class="oidSpan">编号：${oid }</span>
</div>
<form action="<%=basePath%>order/pay.html" method="post" id="payForm" target="_top">
<input type="hidden" name="oid" value="${oid }"/>
<div class="bankDiv">
	<div class="chooseTextDiv">选择网上银行</div>
	<div id="bankListDiv">
		<c:forEach items="${bankList }" var="bankItem" varStatus="sta">
		  	<div class="bankItemDiv">
			<input id="${bankItem.payId }" type="radio" name="bank" value="${bankItem.payId }"/>
			<label for="${bankItem.payId}">
			<img src="<%=basePath%>resources/images/bank-img/${bankItem.picName}"/>
			</label>
			</div>
			<c:if test="${sta.count%4==0 }">
				<div style="clear:both"></div>
			</c:if>
		</c:forEach>
	</div>
	
	<div style="margin: 40px;">
		<a href="javascript:void $('#payForm').submit();" class="linkNext">下一步</a>
	</div>
</div>
</form>
  </body>
</html>
