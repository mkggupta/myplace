<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

 <%@page import="com.myplace.dto.UserInfo"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/myplace.css" type="text/css">
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/css/pagefont.css" />
<title>bussProfile</title>
</head>
<body>
<table class="userprofile" width="100%" border="0">

	<c:if test="${not empty message}">
 	<tr>
  <td colspan="2"> <font size="4" color="red"><b>${message}</b> &nbsp;</br></font> </td>
  	</tr>
 	</c:if>
   <c:if test="${not empty respObj}">
     
    <td colspan="2" width="25%"><img src="${respObj.imgUrls[0]}" alt='photo' style="width:154px;height:108px;"/></td> 
    </tr></br>  
	 <tr> <td width="5%">Bussiness Name </td> 
	 <c:choose>
    <c:when test="${not empty respObj.bussName}">
    <td width="25%"><c:out value="${respObj.bussName}" /></td> 
	 </c:when>
	</c:choose>
    </tr></br>
    
	<tr> <td width="5%">Business Category </td> 
	 <c:choose>
    <c:when test="${not empty respObj.catName}">
    <td width="25%"><c:out value="${respObj.catName}" /></td> 
	 </c:when>
    <c:otherwise>
       <td width="25%"><c:out value="Not Defined" /></td>
    </c:otherwise>
	</c:choose>
    </tr></br>  
	
    <tr> <td width="5%">Bussiness Address </td> 
	 <c:choose>
   <c:when test="${not empty businessInfo.bussAddress}">
         <td width="25%"><c:out value="${businessInfo.bussAddress}" /></td> 
    </c:when>
    <c:otherwise>
        <td width="25%"><c:out value="Not Defined" /></td>
    </c:otherwise>
	</c:choose>
    </tr></br>
     <tr> <td width="5%">Bussiness Zip </td> 
	<c:choose>
    <c:when test="${not empty respObj.bussZip}">
    <td width="25%"><c:out value="${respObj.bussZip}" /></td> 
	 </c:when>
    <c:otherwise>
       <td width="25%"><c:out value="Not Defined" /></td>
    </c:otherwise>
	</c:choose>
    </tr></br>   
       <tr> <td width="5%">Bussiness Phone </td> 
	<c:choose>
    <c:when test="${not empty respObj.bussPhone}">
    <td width="25%"><c:out value="${respObj.bussPhone}" /></td> 
	 </c:when>
    <c:otherwise>
       <td width="25%"><c:out value="Not Defined" /></td>
    </c:otherwise>
	</c:choose>
    </tr></br> 
    <tr> <td width="5%">Business Email </td> 
	<c:choose>
    <c:when test="${not empty respObj.bussEmail}">
    <td width="25%"><c:out value="${respObj.bussEmail}" /></td> 
	 </c:when>
    <c:otherwise>
       <td width="25%"><c:out value="Not Defined" /></td>
    </c:otherwise>
	</c:choose>
    </tr></br>   
    <tr> <td width="5%">Business Web </td> 
  
	<c:choose>
    <c:when test="${not empty respObj.bussWeb}">
    <td width="25%"><c:out value="${respObj.bussWeb}" /></td> 
	 </c:when>
    <c:otherwise>
       <td width="25%"><c:out value="Not Defined" /></td>
    </c:otherwise>
	</c:choose>
    </tr></br>   
    <tr> <td width="5%">Business Description</td> 

	<c:choose>
    <c:when test="${not empty respObj.bussDesc}">
    <td width="25%"><c:out value="${respObj.bussDesc}" /></td> 
	 </c:when>
    <c:otherwise>
       <td width="25%"><c:out value="Not Defined" /></td>
    </c:otherwise>
	</c:choose>
    </tr></br>   
	
    <tr> <td width="5%">Business Start Year </td> 
	 <c:choose>
    <c:when test="${not empty respObj.bussStartDate}">
    <td width="25%"><c:out value="${respObj.bussStartDate}" /></td> 
	 </c:when>
    <c:otherwise>
       <td width="25%"><c:out value="Not Defined" /></td>
    </c:otherwise>
	</c:choose>
    </tr></br>  
   <tr> <td colspan="2">&nbsp;<a href="${respObj.updateUrl}">Edit Business Profile </a>&nbsp;&nbsp;</br></br></td></tr>
    <tr> <td colspan="2">&nbsp;<a href="${respObj.deleteUrl}">Delete My Business</a>&nbsp;&nbsp;</br></br></td></tr>
     </c:if>
	
</table>

</body>
</html>