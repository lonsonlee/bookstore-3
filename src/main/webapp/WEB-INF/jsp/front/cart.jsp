<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
 
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  <base href="<%=basePath%>">
    <title>cart.jsp</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	
	<link rel="stylesheet" type="text/css" href="<%=basePath%>resources/css/front/cart.css">
	<script type="text/javascript" src="<%=basePath %>resources/js/jquery-3.1.1.min.js"></script>
	<script type="text/javascript" src="<%=basePath %>resources/js/cart.js"></script>
	
	
  </head>
  <body>
  <input type="hidden" id="basePath" value="<%=basePath %>"/>
  <div id="backToMain">
  <a href="<%=basePath %>main.html">回主页</a>
	</div>
<c:choose>
	<c:when test="${empty cartItemList }">
	<table width="95%" align="center" cellpadding="0" cellspacing="0">
		
		<tr>
			<td align="right">
				<img align="top" src="<%=basePath %>resources/images/common/icon_empty.png"/>
			</td>
			<td>
				<span class="emptySpan">您的购物车中暂时没有商品...</span><a href="<%=basePath %>main.html">去购物</a>
			</td>
			
		</tr>
	</table>  
	</c:when>
	<c:otherwise>
<table width="95%" align="center" cellpadding="0" cellspacing="0">
	<tr align="center" bgcolor="#efeae5">
		<td align="left" width="50px">
			<input type="checkbox" id="selectAll" checked="checked"/><label for="selectAll">全选</label>
		</td>
		<td colspan="2">商品名称</td>
		<td>单价</td>
		<td>数量</td>
		<td>小计</td>
		<td>操作</td>
	</tr>



<c:forEach items="${cartItemList }" var="cartItem">
	<tr align="center">
		<td align="left">
			<input value="${cartItem.cartItemId }" type="checkbox" name="checkboxBtn" checked="checked"/>
		</td>
		<td align="left" width="70px">
			<a class="linkImage" href="">
			<img class="image_b" src="<%=basePath %>resources/images/book-img/${cartItem.book.image_b}"/>
			</a>
		</td>
		<td align="left" width="400px">
		    <a href=""><span>${cartItem.book.bname }</span></a>
		</td>
		<td><span>&yen;<span class="currPrice" id="${cartItem.cartItemId }CurrPrice">${cartItem.book.currPrice }</span></span></td>
		<td>
			<a class="minus" id="${cartItem.cartItemId }f_Minus"></a>
			<input class="quantity" readonly="readonly" id="${cartItem.cartItemId }Quantity" type="text" value="${cartItem.quantity }"/>
			<a class="plus" id="${cartItem.cartItemId }f_Plus"></a>
		</td>
		<td width="100px">
			<span class="price_st">&yen;<span id="${cartItem.cartItemId }Subtotal">${cartItem.subtotal }</span></span>
		</td>
		<td>
			<a id="${cartItem.cartItemId }delete" href="javascript:deleteItem()" class="deleteItem">删除</a>
		</td>
	</tr>
</c:forEach>




	
	<tr>
		<td colspan="4" class="deleteItemsTd">
			<a href="javascript:deleteItems()">批量删除</a>
		</td>
		<td colspan="3" align="right" class="totalTd">
			<span>总计：</span><span class="price_t">&yen;<span id="total"></span></span>
		</td>
	</tr>
	<tr>
		<td colspan="7" align="right">
			<a href="javascript:clearing()" id="clearingBtn" class="clearingBtn"></a>
		</td>
	</tr>
</table>
	<form id="clearingForm" action="<%=basePath %>cart/placeod.html" method="post">
		<input type="hidden" name="ids" id="cartItemIds"/>
		<input type="hidden" name="total" id="hiddenTotal"/>
	</form>

	</c:otherwise>
</c:choose>
  </body>
</html>
