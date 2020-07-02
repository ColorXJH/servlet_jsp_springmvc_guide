<%@page import="java.util.Date"%>
<%@page import="java.util.Arrays"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
</head>
<body>
	<c:set var="mydate" value="2019-08-30"/>
	<c:set var="myvar" value="${requestScope.color }" scope="page"/>
	<c:set var="myname" scope="request">xjh</c:set>
	<c:set target="${requestScope }" property="xyq" value="dearbear"/>
	<c:set target="${requestScope }" property="dazhong">tuguan</c:set>
	
	<c:set var="lname" value="${myname }" scope="request"/>
	<c:remove var="myname" scope="request"/>
	<%
	request.setAttribute("name", "<h1><a href='http://www.baidu.com'>xjh</a></h1>");
	request.setAttribute("age",24);
	pageContext.setAttribute("jack", "jack666",pageContext.PAGE_SCOPE);
	pageContext.setAttribute("color", "jack666",pageContext.REQUEST_SCOPE);
	%>
	<c:if test="${5==5 }" var="mydear" scope="page"/>
	<c:out value="${mydear }" escapeXml="true" default="xixi"/>
	${pageScope }
	<br/>
	${requestScope.xyq}
	<br/>
	${requestScope.dazhong }
	<br/>
	${requestScope.myname }
	<br/>
	${requestScope.lname }
	<br/>
	<c:out value="${name1 }" escapeXml="false" default="${age }"/>
	<br/>
	<c:out escapeXml="false" value="${name }">
		defaultValue
	</c:out>
	<br/>
	111
	<c:if test="${5==5 }">
	dear-bear
	</c:if>
	<br/>
	${!mydear? "you are my bear":"you are not my bear" }
	<br/>
	<c:choose>
		<c:when test="${5==5 }">this is 5</c:when>
		<c:otherwise> this is not 5</c:otherwise>
	</c:choose>
	<br/>
	<c:forEach var="xt" begin="1" end="5" step="2">
				<c:out value="${xt}"/><!-- 1 3 5 -->
	</c:forEach>
	<br/>
	<%
		ArrayList<String> list=new ArrayList();
		HashMap<String,String> map=new HashMap();
		list.add("tom1");
		list.add("tom2");
		list.add("tom3");
		list.add("tom4");
		list.add("tom5");
		map.put("tom1","hahah1");
		map.put("tom2","hahah2");
		map.put("tom3","hahah3");
		map.put("tom4","hahah4");
		map.put("tom5","hahah5");
		request.setAttribute("list", list);
		request.setAttribute("map", map);
		request.setAttribute("myStringArray", new String[]{"1","2","3","4"});
	%>
	<c:forEach items="${list }" var="t" varStatus="mylist" step="1">
		${t }--${mylist.index }-${mylist.current }--${mylist.count }--${mylist.first }--${mylist.last }<br/>
	</c:forEach>
	<br/>
	<c:forEach items="${map }" var="m">
		${m.key }--${m.value }<br/>
	</c:forEach>
	<br/>
	
	<c:forTokens var="sp" items="alibaba,baidu,tencent" delims=",">
		<c:out value="${sp }" /><br/>
	</c:forTokens>
	<br/>
	<%
		ArrayList<String> listp=new ArrayList();
		listp.add("dear1");
		listp.add("dear2");
		listp.add("dear3");
		listp.add("dear4");
		listp.add("dear5");
		String slp=Arrays.toString(listp.toArray()).substring(1, Arrays.toString(listp.toArray()).length()-1);
		request.setAttribute("slp", slp);
		request.setAttribute("now", new Date());
	%>
	<c:forTokens var="sp" items="${slp }" delims=",">
		<c:out value="${sp }" /><br/>
	</c:forTokens>
	<br/>
	<fmt:formatNumber value="12" type="number"/><br/>
	<fmt:formatNumber value="12" type="number" minIntegerDigits="3"/><br/>
	<fmt:formatNumber value="12" type="number" minFractionDigits="2"/><br/> 
	<fmt:formatNumber value="123456.78" pattern=".000"/><br/> 
	<fmt:formatNumber value="123456.78" pattern="#,#00.0#"/><br/>
	<fmt:formatNumber value="12" type="currency"/> <br/>
	<fmt:formatNumber value="12" type="currency" currencyCode="CNY"/><br/>
	<fmt:formatNumber value="0.12" type="percent"/><br/>
	<fmt:formatNumber value="0.125" type="percent" minFractionDigits="2"/><br/>
	<br/>
	<fmt:formatDate type="both" value="${now }" dateStyle="long" timeStyle="long" pattern="yyyy-mm-dd HH:mm:ss"  timeZone="CT"/>	<!-- hh/HH 12/24小时制	 -->	
	<br/>
	<fmt:timeZone value="GMT+1:00">
					<fmt:formatDate value="${now}" type="both" dateStyle="full"/>
				</fmt:timeZone><br/>
	<fmt:parseNumber value="12" type="number" var="currencyDemo"/>
	${currencyDemo }<br/>111
	<fmt:parseDate value="${mydate }" var="formatDate"  pattern="yyyy-mm-dd" /><br/>
	${formatDate }<br/>
	${fn:join(myStringArray,"-") }
	<br/>
	${fn:length(list) }<!--5  -->
	<br/>
	${fn:length("xiajinhui") }<!--9  --><br/>
	${fn:replace("xiajinhuix","x","d") }<br/>
	<c:forEach items='${fn:split("my,word",",") }' var="split">
		${split }<br/>
	</c:forEach>
	${fn:startsWith("xjh","x") }<br/>
	${fn:substring("xjhhahaha",2,7) }<br/>
	${fn:substringAfter("xiajinhui","j") }<br/>
	${fn:substringBefore("xiajinhui","a") }<br/>
	${fn:toLowerCase("XIAJINHUI") }<br/>
	${fn:toUpperCase("xiaJInhui") }<br/>
	${fn:trim("   xiajinhui    ") }<br/>
	
</body>  
</html>