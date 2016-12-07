<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<div id="sidebar">

<ul id="nav">

<c:forEach items="${parentList }" var="parent">

<li><a href="">${parent.cname }</a>
<div>
<c:forEach items="${parent.subCategory }" var="sub">
 <a href="<%=basePath%>book/list.html?cid=${sub.cid}">${sub.cname }</a>
 </c:forEach>
 </div>
</li>

</c:forEach>

</ul>

</div>
