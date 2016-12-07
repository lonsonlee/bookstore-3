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
    
    <title>My JSP 'main.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="stylesheet" type="text/css" href="<%=basePath%>resources/css/front/main.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>resources/css/common/paging.css">
	
</head>
<body>


<div id="wrap">

<div id="header">

</div>

<div id="userNavDiv">

<c:choose>

<c:when test="${empty sessionScope.loginname}">
<ul id="LogAndRegNav">
	<li><a href="<%=basePath%>login.html">登录</a></li>
	<li><a href="<%=basePath%>register.html">注册</a></li>
</ul>
</c:when>

<c:otherwise>
<ul id="userNav">
<li><a href="">Hi,${sessionScope.loginname }</a></li>
<li><a href="<%=basePath%>user/doLogout.html">退出</a></li>
<li><a href="<%=basePath%>cart/cart.html">我的购物车</a></li>
<li><a href="<%=basePath%>order/order.html">我的订单</a></li>
<li><a href="<%=basePath%>updatepw.html">修改密码</a></li>
</ul>
</c:otherwise>
</c:choose>


</div>
<div style="clear:both"></div>




<jsp:include page="../../../category.html"></jsp:include>



<div id="main">

<div id="searchDiv">
<form method="get" action="<%=basePath%>book/list.html">
<input type="text" name="bname" id="searchInput"/>
<input type="submit" id="searchSubmit" value="搜索"/>
</form>
</div>



<c:choose>
<c:when test="${!empty data.bookList }">
<div class="bookListDiv">

<c:forEach items="${data.bookList}" var="book" varStatus="bookStatus">
  
  <div class="itemDiv">
    <a class="pic" href="<%=basePath%>book/detail.html?bid=${book.bid}">
    <img src="<%=basePath%>resources/images/book-img/${book.image_b}" border="0"/></a>
    <p class="price">
		<span class="price_c">&yen;${book.currPrice }</span>
		<span class="price_p">&yen;${book.pricing }</span>
		(<span class="price_d">${book.discount }折</span>)
	</p>
	<p><a id="bookname" title="${book.bname }" href="<%=basePath%>book/detail.html?bid=${book.bid}">${book.bname }</a></p>
	
	<%-- url标签会自动对参数进行url编码 --%>
	<c:url value="/<%=path%>book/list.html" var="authorUrl">
		<c:param name="author" value="${book.author }"/>
	</c:url>
	<c:url value="/<%=path%>book/list.html" var="pressUrl">
		<c:param name="press" value="${book.press }"/>
	</c:url>
	
	<p><a href="${authorUrl }" name='P_zz' title='${book.author }'>${book.author }</a></p>
	<p><span>出 版 社：</span><a href="${pressUrl }">${book.press }</a></p>
	<p><span>出版时间：</span>${book.publicationTime }</p>
  </div>
 	<c:if test="${bookStatus.count%4 == 0 }">
 	<div style="clear:both"></div>
 	</c:if>
</c:forEach>

  </div>
  
  <%-- <%@ include file="../common/paging.jsp" %> --%>
  <jsp:include page="../common/paging.jsp"></jsp:include>
  </c:when>
  <c:otherwise>
  		<jsp:include page="../../../book/home.html"></jsp:include>
  
  </c:otherwise>
  </c:choose>
  
</div>



</div>





</body>
</html>
