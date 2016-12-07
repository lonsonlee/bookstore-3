<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<div class="wrapDiv">
  <div class="contentDiv">
    <%--上一页 --%>
<c:choose>
	<c:when test="${data.paging.currentPage eq 1 }"><span class="disabledBtnSpan">上一页</span></c:when>
	<c:otherwise><a href="${data.paging.url }&currentPage=${data.paging.currentPage-1}" class="btnA bold">上一页</a></c:otherwise>
</c:choose>
        
        

<%--我们需要计算页码列表的开始和结束位置，即两个变量begin和end
计算它们需要通过当前页码！
1. 总页数不足6页--> begin=1, end=最大页
2. 通过公式设置begin和end，begin=当前页-1，end=当前页+3
3. 如果begin<1，那么让begin=1，end=6
4. 如果end>tp, 让begin=tp-5, end=tp
 --%>
 <c:choose>
 	<c:when test="${data.paging.totalPages <= 6 }">
 		<c:set var="begin" value="1"/>
 		<c:set var="end" value="${data.paging.totalPages }"/>
 	</c:when>
 	<c:otherwise>
 		<c:set var="begin" value="${data.paging.currentPage-2 }"/>
 		<c:set var="end" value="${data.paging.currentPage + 3}"/>
 		<c:if test="${begin < 1 }">
 		  <c:set var="begin" value="1"/>
 		  <c:set var="end" value="6"/>
 		</c:if>
 		<c:if test="${end > data.paging.totalPages }">
 		  <c:set var="begin" value="${data.paging.totalPages-5 }"/>
 		  <c:set var="end" value="${data.paging.totalPages }"/>
 		</c:if> 		
 	</c:otherwise>
 </c:choose>
 
 <c:forEach begin="${begin }" end="${end }" var="i">
   <c:choose>
   	  <c:when test="${i eq data.paging.currentPage }">
   	    <span class="currentPageBtnSpan">${i }</span>
   	  </c:when>
   	  <c:otherwise>
   	    <a href="${data.paging.url }&currentPage=${i}" class="btnA">${i }</a>
   	  </c:otherwise>
   </c:choose>
           
          	
 </c:forEach>
    

    
    <%-- 显示省略号 --%>
    <c:if test="${end < data.paging.totalPages }">
      <span class="ellipsisSpan">...</span>
    </c:if> 

    
     <%--下一页 --%>
<c:choose>
	<c:when test="${data.paging.currentPage eq data.paging.totalPages }"><span class="disabledBtnSpan">下一页</span></c:when>
	<c:otherwise><a href="${data.paging.url }&currentPage=${data.paging.currentPage+1}" class="btnA bold">下一页</a></c:otherwise>
</c:choose>
        
        
    
    
    <%-- 共N页 到M页 --%>
    <span>共${data.paging.totalPages }页</span>
    <span>到</span>
    <input type="text" class="input" id="input" value="${data.paging.currentPage }"/>
    <span>页</span>
    <a href="javascript:_go();" class="submitA">Go</a>
  </div>
</div>