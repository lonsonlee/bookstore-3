<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>订单列表</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta http-equiv="content-type" content="text/html;charset=utf-8">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="stylesheet" type="text/css" href="<%=basePath%>resources/css/front/order.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>resources/css/common/paging.css">
    <script type="text/javascript" src="<%=basePath%>resources/js/jquery-3.1.1.min.js"></script>
    <script type="text/javascript" src="<%=basePath%>resources/js/jquery.table.rowspan.js"></script>  
    <script type="text/javascript" src="<%=basePath%>resources/js/order.js"></script>
    
    
  </head>
  
  <body>
  	<input type="hidden" id="basePath" value="<%=basePath%>"/>
  	<div id="backToMain">
  		<a href="<%=basePath%>main.html">回主页</a>
	</div>
  	<c:choose>
  		<c:when test="${empty data.orderList }">
  		您的订单空空如也....
  		
  		</c:when>
  		
  	
  		<c:otherwise>
  			<table width="95%" align="center" id="titleTable">
  				<tr>
  				<td colspan="2" width="600"></td>
  				<td width="100">数量</td>
	  			<td width="100">收货人</td>
	  			<td width="100">金额</td>
	  			<td width="100">订单状态</td>
	  			<td width="100">操作</td>
  				</tr>
  			</table>
  		
  			<!-- 循环orderList,每一个order用一个table来表示 -->
  			<c:forEach items="${data.orderList }" var="order">
  			<table class="table" width="95%" align="center">
  			
  			<!-- order表的表头 -->
  			<tr class="orderTitleTr">
  			<td>${order.orderTime }</td>
  			<td>订单号: ${order.oid }</td>
  			<td colspan="5" width="500" class="deleteTd">
  			<a id="deleteA" href="javascript:deleteOrder('${order.oid }')">删除</a>
  			</td>
  			</tr>
  			
  				<!-- 循环order的条目 -->
	  			<c:forEach items="${order.orderItemList }" var="orderItem">
	  			<!-- 条目内容 -->
	  			<tr>
	  			<td colspan="2">
	  			<a href="<%=basePath%>book/detail.html?bid=${orderItem.book.bid}">
	  			<img src="<%=basePath%>resources/images/book-img/${orderItem.book.image_b}">
	  			</a>
	  			<div class="bnameDiv"><a href="<%=basePath%>book/detail.html?bid=${orderItem.book.bid}">${orderItem.book.bname }</a></div>
	  			</td>
	  			<td width="100">${orderItem.quantity }</td>
	  			<td width="100">${order.consignee }</td>
	  			<td width="100">${order.total }</td>
	  			<td width="100">
				<c:choose>
					<c:when test="${order.status eq 0 }">(等待付款)</c:when>
					<c:when test="${order.status eq 1 }">(准备发货)</c:when>
					<c:when test="${order.status eq 2 }">(等待确认)</c:when>
					<c:when test="${order.status eq 3 }">(交易成功)</c:when>
					<c:when test="${order.status eq 4 }">(已取消)</c:when>
				</c:choose>	
				</td>
	  			<td width="100">
				<c:if test="${order.status eq 0 }">
					<a href="<%=basePath%>order/toPrePayment.html?oid=${order.oid}&total=${order.total}">支付</a><br/>
					<a href="javascript:cancelOrder('${order.oid }')">取消</a><br/>						
				</c:if>
				<c:if test="${order.status eq 2 }">
					<a href="">确认收货</a><br/>
				</c:if>
				<c:if test="${order.status eq 3 }">
					<a href="">查看</a><br/>
				</c:if>
				<c:if test="${order.status eq 4 }">
					<a href="">立即购买</a>
				</c:if>
				</td>
	  			
	  			</tr>
	  			</c:forEach>
	  			
  			</table>
  			</c:forEach>
  		
  		
  		
  		 <jsp:include page="../common/paging.jsp"></jsp:include>
  		</c:otherwise>
  	</c:choose>
	
	
  </body>
</html>
