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
<title>bussProfile</title>
</head>
<body>
<jsp:include page="header.jsp" flush="true" />
<table class="bussProfile" width="100%" border="0">
	<c:if test="${not empty message}">
 	<tr>
  <td colspan="2"> <font size="4" color="red"><b>${message}</b> &nbsp;</font> </td>
  	</tr>
 	</c:if>
	<tr>
   <td colspan="2"> &nbsp;<a href="#" onclick="goBack()"> Back </a> </td></td>
  	</tr>
	<tr>
   <td colspan="2"></td>
  	</tr>
   <c:if test="${not empty respObj}">
    <tr> 
    <td colspan="2" width="25%"><img src="${respObj.imgUrls[0]}" alt='photo' style="width:154px;height:108px;"/></td> 
    </tr> 
    
	 <tr> <td width="5%">Business Name </td> 
	 <c:choose>
    <c:when test="${not empty respObj.bussName}">
    <td width="25%"><c:out value="${respObj.bussName}" /></td> 
	 </c:when>
	</c:choose>
    </tr>
    <tr> <td width="5%">Business Contact Name </td> 
	 <c:choose>
   <c:when test="${not empty respObj.bussContName}">
         <td width="25%"><c:out value="${respObj.bussContName}" /></td> 
    </c:when>
    <c:otherwise>
        <td width="25%"><c:out value="Not Defined" /></td>
    </c:otherwise>
	</c:choose>
    </tr>
    
	<tr> <td width="5%">Business Category </td> 
	 <c:choose>
    <c:when test="${not empty respObj.catName}">
    <td width="25%"><c:out value="${respObj.catName}" /></td> 
	 </c:when>
    <c:otherwise>
       <td width="25%"><c:out value="Not Defined" /></td>
    </c:otherwise>
	</c:choose>
    </tr> 
	
    <tr> <td width="5%">Business Address </td> 
	 <c:choose>
   <c:when test="${not empty respObj.bussAddress}">
         <td width="25%"><textarea rows="2" cols="25"><c:out value="${respObj.bussAddress}" /></textarea></td> 
    </c:when>
    <c:otherwise>
        <td width="25%"><c:out value="Not Defined" /></td>
    </c:otherwise>
	</c:choose>
    </tr>
     <tr> <td width="5%">Business Zip </td> 
	<c:choose>
    <c:when test="${not empty respObj.bussZip}">
    <td width="25%"><c:out value="${respObj.bussZip}" /></td> 
	 </c:when>
    <c:otherwise>
       <td width="25%"><c:out value="Not Defined" /></td>
    </c:otherwise>
	</c:choose>
    </tr>  
       <tr> <td width="5%">Business Phone </td> 
	<c:choose>
    <c:when test="${not empty respObj.bussPhone}">
    <td width="25%"><c:out value="${respObj.bussPhone}" /></td> 
	 </c:when>
    <c:otherwise>
       <td width="25%"><c:out value="Not Defined" /></td>
    </c:otherwise>
	</c:choose>
    </tr>
    <tr> <td width="5%">Business Email </td> 
	<c:choose>
    <c:when test="${not empty respObj.bussEmail}">
    <td width="25%"><c:out value="${respObj.bussEmail}" /></td> 
	 </c:when>
    <c:otherwise>
       <td width="25%"><c:out value="Not Defined" /></td>
    </c:otherwise>
	</c:choose>
    </tr>  
    <tr> <td width="5%">Business Web </td> 
  
	<c:choose>
    <c:when test="${not empty respObj.bussWeb}">
    <td width="25%"><c:out value="${respObj.bussWeb}" /></td> 
	 </c:when>
    <c:otherwise>
       <td width="25%"><c:out value="Not Defined" /></td>
    </c:otherwise>
	</c:choose>
    </tr>  
    <tr> <td width="5%">Business Description</td> 

	<c:choose>
    <c:when test="${not empty respObj.bussDesc}">
    <td width="25%"><textarea name="bDesc" rows="2" cols="25" maxlength="4000"><c:out value="${respObj.bussDesc}" /></textarea></td> 
	 </c:when>
    <c:otherwise>
       <td width="25%"><c:out value="Not Defined" /></td>
    </c:otherwise>
	</c:choose>
    </tr>  
	
    <tr> <td width="5%">Business Start Year </td> 
	 <c:choose>
    <c:when test="${not empty respObj.bussStartDate}">
    <td width="25%"><c:out value="${respObj.bussStartDate}" /></td> 
	 </c:when>
    <c:otherwise>
       <td width="25%"><c:out value="Not Defined" /></td>
    </c:otherwise>
	</c:choose>
	<c:if test="${not empty respObj.updateUrl}">
    </tr> 
     <form id="editBussProfile" name="editBussProfile" method="post" action="${respObj.updateUrl}">
   	<input type="hidden" name="appType" value="4"/> 
     <input type="hidden" name="userId" value="${respObj.getUserId()}"/> 
     <input type="hidden" name="bId" value="${respObj.getBussId()}"/> 
   </form>
   <tr> <td colspan="2">&nbsp;<a href="javascript:document.editBussProfile.submit()">Edit Business Profile </a></td></tr>
    <tr> <td colspan="2">&nbsp;<a href="${respObj.deleteUrl}">Delete My Business</a>&nbsp;&nbsp;</td></tr>
     </c:if>
	</c:if>
</table>

</body>
</html>