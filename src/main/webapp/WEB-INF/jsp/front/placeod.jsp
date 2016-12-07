<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>placeod.jsp</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="stylesheet" type="text/css" href="<%=basePath%>resources/css/front/placeod.css">
	<script src="<%=basePath%>resources/js/jquery-3.1.1.min.js"></script>

  </head>
  
  <body>
  <c:choose>
  	<c:when test="${empty cartItemList }">嘻嘻~</c:when>
  	<c:otherwise>
<form id="orderForm" action="<%=basePath%>order/generateod.html" method="post">
	
	<input type="hidden" name="ids" value="${ids }"/>
	<input type="hidden" name="total" value="${total }"/>
	
<table>
	<tr bgcolor="#efeae5">
		<td width="400px" colspan="5"><span style="font-weight: 900;">生成订单</span></td>
	</tr>
	<tr align="center">
		<td width="10%"></td>
		<td width="50%" align="left">图书名称</td>
		<td>单价</td>
		<td>数量</td>
		<td>小计</td>
	</tr>


<c:forEach items="${cartItemList }" var="cartItem">
	<tr align="center">
		<td align="right">
			<a class="linkImage" href="<%=basePath%>book/detail.html?bid=${cartItem.book.bid}">
			<img src="<%=basePath%>resources/images/book-img/${cartItem.book.image_b}"/></a>
		</td>
		<td align="left">
			<a href="<%=basePath%>book/detail.html?bid=${cartItem.book.bid}">
			<span>${cartItem.book.bname }</span></a>
		</td>
		<td>&yen;${cartItem.book.currPrice }</td>
		<td>${cartItem.quantity }</td>
		<td>
			<span class="price_st">&yen;<span class="subtotal">${cartItem.subtotal }</span></span>
		</td>
	</tr>
</c:forEach>
	



	<tr>
		<td colspan="6" align="right">
			<span>总计：</span><span class="price_t">&yen;<span id="total">${total }</span></span>
		</td>
	</tr>
	<tr>
		<td colspan="5" id="infoTitleTd"><span>收货信息</span></td>
	</tr>
	<tr>
		<td colspan="6">
			姓名:
			<input type="text" name="consignee"/><br>
			电话:
			<input type="text" name="phone"/><br>
			地址:
			<input id="address" type="text" name="address"/>
		</td>
	</tr>
	<tr>
		<td id="submitTd" colspan="5">
			<a id="linkSubmit" href="javascript:$('#orderForm').submit();">提交订单</a>
		</td>
	</tr>
</table>
</form>
  	</c:otherwise>
  </c:choose>
  </body>
</html>
