<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<div id="itemsTitleDiv">
新书推荐
<hr>
</div>
  
 <div class="bookListDiv">

<c:forEach items="${homeList}" var="book" varStatus="bookStatus">
  
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