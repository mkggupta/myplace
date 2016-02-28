<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
 <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">  
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<script type="text/javascript" src="<%=request.getContextPath()%>/js/validation.js"></script>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/myplace.css" type="text/css">
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/css/pagefont.css" />
<title>findon-Bussiness List</title>
</head>
<body>
<jsp:include page="header.jsp" flush="true" />
<table class="bussListprofile" width="100%" border="0">

	<c:if test="${not empty message}">
 	<tr>
   <td colspan="2"> <font size="2" color="red"><b>${message}</b></font> </td>
  	</tr>
 	</c:if>
	<tr>
   <td colspan="2"> &nbsp; </td>
  	</tr>
	<tr>
   <td colspan="2"> &nbsp; </td>
  	</tr>
   <c:forEach items="${respObj}" var="businessInfo">    	
  <tr> 
    <td  style="width:15%;"><img src="${businessInfo.imgUrls[0]}" alt='photo' style="width:174px;height:118px;"/></td> 
     <td style="width:85%;"> <table class="bussShortDesc" width="100%" border="0">  <tr> 
    <td  style="width:15%;" align="right">Business Name :&nbsp; </td>
	<td  style="width:85%;">
	 <c:choose>
    <c:when test="${not empty businessInfo.bussName}">
		<c:out value="${businessInfo.bussName}" />
	 </c:when>
    <c:otherwise>
      <c:out value="Not Defined" />
    </c:otherwise>
	</c:choose>
	</td>
	</tr>
	<tr> 
    <td  style="width:15%;" align="right">Business Category :&nbsp;</td>
	<td  style="width:85%;">
	 <c:choose>
    <c:when test="${not empty businessInfo.catName}">
		<c:out value="${businessInfo.catName}" />
	 </c:when>
    <c:otherwise>
      <c:out value="Not Defined" />
    </c:otherwise>
	</c:choose>
	</td>
	</tr>
	 <tr> 
    <td  style="width:15%;" align="right">Business Address :&nbsp;</td>
	<td  style="width:85%;">

	 <c:choose>
    <c:when test="${not empty businessInfo.bussAddress}">
		<c:out value="${businessInfo.bussAddress}" />
	 </c:when>
    <c:otherwise>
      <c:out value="Not Defined" />
    </c:otherwise>
	</c:choose>
	</td>
	</tr>
	 <tr> 
    <td  style="width:15%;" align="right">Business Phone :&nbsp;</td>
	<td  style="width:85%;">
	 <c:choose>
    <c:when test="${not empty businessInfo.bussPhone}">
		<c:out value="${businessInfo.bussPhone}" />
	 </c:when>
    <c:otherwise>
      <c:out value="Not Defined" />
    </c:otherwise>
	</c:choose>
	</td>
	</tr>
	
	 <tr> 
    <td  colspan="2">&nbsp;&nbsp;&nbsp;&nbsp;<a href="${businessInfo.detailUrl}?appType=4"> <c:out value="Get More Detail.." /> </a>
   <hr /> 
   </td>
	</tr>
</table>
   </td></tr>
     </c:forEach>

</table>
<jsp:include page="footer.jsp" flush="true" />
</body>
</html>