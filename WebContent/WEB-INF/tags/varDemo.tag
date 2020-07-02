<%@tag import="com.sun.xml.internal.bind.v2.runtime.unmarshaller.Scope"%>
<%@tag import="java.text.DateFormat"%>
<%@ tag pageEncoding="utf-8" %>
<%@tag import="java.util.Date"%>
<%@ variable name-given="shortDate" %>
<%-- <%@ variable name-given="longDate" %>	
<%@ variable name-given="medium" %> --%>
<%
Date now=new Date(System.currentTimeMillis());
DateFormat longFormat=DateFormat.getDateInstance(DateFormat.LONG);
DateFormat shortFormat=DateFormat.getDateInstance(DateFormat.SHORT);
jspContext.setAttribute("longDate", longFormat.format(now),PageContext.REQUEST_SCOPE);//效果如同jstl的c:set标签，这里提供了REQUEST_SCOPE，可以跨页面
jspContext.setAttribute("shortDate", shortFormat.format(now));//默认PAGE_SCOPE，变量不能跨页面传递到jsp页面，使用variable指令就可以将其带过去再自己的标签内显示
%>
<jsp:doBody></jsp:doBody>