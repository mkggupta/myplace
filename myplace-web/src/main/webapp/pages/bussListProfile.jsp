<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script type="text/javascript" src="<%=request.getContextPath()%>/js/validation.js"></script>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/myplace.css" type="text/css">
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/css/pagefont.css" />
<title>bussListProfile</title>
</head>
<body>
<jsp:include page="header.jsp" flush="true" />
<table class="bussListprofile" width="100%" border="0">

	<c:if test="${not empty message}">
 	<tr>
   <td colspan="2"> <font size="4" color="red"><b>${message}</b></font> </td>
  	</tr>
 	</c:if>
	<tr>
   <td colspan="2"> &nbsp;<a href="#" onclick="goBack()"> Back </a> </td>
  	</tr>
	<tr>
   <td colspan="2"> &nbsp;</br> </td>
  	</tr>
   <c:forEach items="${respObj}" var="businessInfo">    	
  <tr> 
    <td  width="20%"><img src="${businessInfo.imgUrls[0]}" alt='photo' style="width:154px;height:108px;"/></td> 
     <td width="80%">Business Name : 
	 <c:choose>
    <c:when test="${not empty businessInfo.bussName}">
		<c:out value="${businessInfo.bussName}" />
	 </c:when>
    <c:otherwise>
      <c:out value="Not Defined" />
    </c:otherwise>
	</c:choose>
	</br>Business Address :
	 <c:choose>
    <c:when test="${not empty businessInfo.bussAddress}">
		<c:out value="${businessInfo.bussAddress}" />
	 </c:when>
    <c:otherwise>
      <c:out value="Not Defined" />
    </c:otherwise>
	</c:choose>
	</br>Business Phone :
	 <c:choose>
    <c:when test="${not empty businessInfo.bussPhone}">
		<c:out value="${businessInfo.bussPhone}" />
	 </c:when>
    <c:otherwise>
      <c:out value="Not Defined" />
    </c:otherwise>
	</c:choose>
	</br><a href="${businessInfo.detailUrl}?appType=4"> <c:out value="Get More Detail.." /> </a>
    </td></tr>
     </c:forEach>

</table>

</body>
</html>