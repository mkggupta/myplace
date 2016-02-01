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
<script type="text/javascript" src="<%=request.getContextPath()%>/js/validation.js"></script>
<title>editUserProfile</title>

</head>
<body>

<jsp:include page="header.jsp" flush="true" />
<table class="userprofile" width="100%" border="0">

	<c:if test="${not empty message}">
 	<tr>
   	<td colspan="2"> <font size="4" color="red"><b>${message}</b> &nbsp;</br></font> </td>
  	</tr>
 	</c:if>

   <c:if test="${not empty respObj}">
  
<form name="editform" method="post" action="${respObj.profileUpdateUrl}" onsubmit="return validateUserEditForm()" enctype="multipart/form-data">
  	<tr><td colspan=2 style="font-weight:bold;" align="center">Edit Profile</td></tr>	
  	
  	 
  <tr> 
    <td colspan="2" width="25%"><img src="${respObj.imgUrls[0]}" alt='photo' style="width:154px;height:108px;"/></td> 
    </tr></br>  
    <tr> <td width="5%">Change My Picture </td> 
	
    <td width="25%"> <input type="file" name="pdata"> </td> 
	
    </tr> 
	 <tr> <td width="5%">Name </td> 
	 <c:choose>
    <c:when test="${not empty respObj.contactName}">
    <td width="25%"><input type="text" name="cName" maxlength="35" value="${respObj.contactName}" /></td> 
	 </c:when>
    <c:otherwise>
       <td width="25%"><input type="text" name="cName" maxlength="35" value="" /></td>
    </c:otherwise>
	</c:choose>
    </tr>
    
     <tr> <td width="5%">About Me </td> 
	<c:choose>
    <c:when test="${not empty respObj.userDescription}">
    <td width="25%"> <input type="text" name="udesc" maxlength="100" value="${respObj.userDescription}"> </td> 
	 </c:when>
    <c:otherwise>
       <td width="25%"> <input type="text" name="udesc" maxlength="100" value=""> </td> 
    </c:otherwise>
	</c:choose>
    </tr> 
    <tr> <td width="5%">Gender</td> 
	 <c:choose>
    <c:when test="${respObj.gender == 1}">
         <td width="25%"><c:out value="Male" /></td> 
    </c:when>
	 <c:when test="${respObj.gender== 2}">
         <td width="25%"><c:out value="Female" /></td> 
    </c:when>
    <c:otherwise>
        <td width="25%"><c:out value="Not Defined" /></td>
    </c:otherwise>
	</c:choose>
    </tr>
     <tr> <td width="5%">Email </td> 
	<c:choose>
    <c:when test="${not empty respObj.userName}">
    <td width="25%"><c:out value="${respObj.userName}" /></td> 
	 </c:when>
    <c:otherwise>
       <td width="25%"><c:out value="Not Defined" /></td>
    </c:otherwise>
	</c:choose>
    </tr>   
    <tr> <td width="5%">Address </td> 
	<c:choose>
    <c:when test="${not empty respObj.contactAddressLine1}">
    <td width="25%"> <input type="text" name="cntctAddLn1" maxlength="100" value="${respObj.contactAddressLine1}"> </td> 
	 </c:when>
    <c:otherwise>
     <td width="25%"> <input type="text" name="cntctAddLn1" maxlength="100" value=""> </td> 
    </c:otherwise>
	</c:choose>
    </tr>   
    <tr> <td width="5%">Zip Code </td> 
  
	<c:choose>
    <c:when test="${not empty respObj.zipcode}">
    <td width="25%"><input type="text" name="zipcode" maxlength="10" value="${respObj.zipcode}" /></td> 
	 </c:when>
    <c:otherwise>
       <td width="25%"><input type="text" name="zipcode" maxlength="10" value="" /></td>
    </c:otherwise>
	</c:choose>
    </tr>   
    <tr> <td width="5%">Language </td> 

	<c:choose>
    <c:when test="${not empty respObj.language}">
     <td width="25%"> <input type="text" name="lang" value="${respObj.language}"> </td> 
	 </c:when>
    <c:otherwise>   
        <td width="25%"> <input type="text" name="lang" maxlength="20" value=""> </td> 
    </c:otherwise>
	</c:choose>
    </tr>   
	
    <tr> <td width="5%">Contact Number * </td> 
	 <c:choose>
    <c:when test="${not empty respObj.contactNumber}">
     <td width="25%"> <input type="text" name="contactNum" maxlength="15" value="${respObj.contactNumber}"> </td> 
	 </c:when>
    <c:otherwise>
       <td width="25%"> <input type="text" name="contactNum" maxlength="15" value=""> </td> 
    </c:otherwise>
	</c:choose>
    </tr>  
       <input type="hidden" name="appType" value="4"/> 
   		<input type="hidden" name="id" value="${respObj.getId()}"/> 
	<tr>
		<td colspan="2">&nbsp;</td>
     </tr>
     
     <tr>
		<td colspan="2">&nbsp;<input type="submit" value="UPDATE"  id="button">
		&nbsp;&nbsp; <input name="Reset" type="reset" class="button" value="RESET" /> 
		&nbsp;&nbsp;<input type="button" value="Cancle"  id="button" onclick="goBack()" ></td>
     </tr>

	 </form>
     </c:if>
</table>

</body>
</html>