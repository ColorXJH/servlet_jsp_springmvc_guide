<%@ attribute name="productDetail" fragment="true" %>
<%@ variable name-given="productName"%>
<%@ variable name-given="description" %>
<%@ variable name-given="price" %>

<%
	jspContext.setAttribute("productName", "hahaName");
	jspContext.setAttribute("description", "hahadescription");
	jspContext.setAttribute("price", "65");


%>
<jsp:invoke fragment="productDetail" var="invok" scope="request"></jsp:invoke>