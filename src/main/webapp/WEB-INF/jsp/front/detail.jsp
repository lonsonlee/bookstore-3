<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'detail.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	
	<link rel="stylesheet" type="text/css" href="<%=basePath%>resources/css/front/detail.css">
	<script type="text/javascript" src="<%=basePath %>resources/js/jquery-3.1.1.min.js"></script>
  </head>
  
  <body>
   <div class="divBookName">${book.bname }</div>
  <div>
    <img align="top" src="<%=basePath%>resources/images/book-img/${book.image_w }" class="img_image_w"/>
    <div class="divBookDesc">
	    <ul>
	    	<li>商品编号：${book.bid }</li>
	    	<li>现价：<span class="price_n">&yen;${book.currPrice }</span></li>
	    	<li>原价：<span class="spanPrice">&yen;${book.pricing }</span>　折扣：<span style="color: #c30;">${book.discount }</span>折</li>
	    </ul>
		<hr class="hr1"/>
		<table>
			<tr>
				<td colspan="3">
					作者：${book.author } 著
				</td>
			</tr>
			<tr>
				<td colspan="3">
					出版社：${book.press }
				</td>
			</tr>
			<tr>
				<td colspan="3">出版时间：${book.publicationTime }</td>
			</tr>
			<tr>
				<td>版次：${book.edition }</td>
				<td>页数：${book.pageNum }</td>
				<td>字数：${book.wordNum }</td>
			</tr>
			<tr>
				<td width="180">印刷时间：${book.printTime }</td>
				<td>开本：${book.bookSize }开</td>
				<td>纸张：${book.paperCharacter }</td>
			</tr>
		</table>
		<div class="formDiv">
			<form id="addForm" action="<%=basePath%>cart/addToCart.html" method="post">
				<input type="hidden" name="bid" value="${book.bid }"/>
  				数量:<input id="quantity" type="text" name="quantity" value="1"/>
  			</form>
  			<a id="btn" href="javascript:$('#addForm').submit();"></a>
  		</div>
	</div>
  </div>
  </body>
</html>
