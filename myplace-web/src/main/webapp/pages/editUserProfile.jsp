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
<title>editUserProfile</title>
<script>
function goBack() {
    window.history.go(-1);
}
</script>
</head>
<body>


<table class="userprofile" width="100%" border="0">

	<c:if test="${not empty message}">
 	<tr>
   	<td colspan="2"> <font size="4" color="red"><b>${message}</b> &nbsp;</br></font> </td>
  	</tr>
 	</c:if>

   <c:if test="${not empty respObj}">
  
	 <form name="editform" method="post" action="${respObj.profileUpdateUrl}">
  	<tr><td colspan=2 style="font-weight:bold;" align="center">Edit Profile</td></tr>	
  	
  	 
  <tr> 
    <td colspan="2" width="25%"><img src="${respObj.imgUrls[0]}" alt='photo' style="width:154px;height:108px;"/></td> 
    </tr></br>  
    <tr> <td width="5%">Change My Picture </td> 
	
    <td width="25%"> <input type="file" name="pdata"> </td> 
	
    </tr></br> 
	 <tr> <td width="5%">Name </td> 
	 <c:choose>
    <c:when test="${not empty respObj.contactName}">
    <td width="25%"><input type="text" name="cName" value="${respObj.contactName}" /></td> 
	 </c:when>
    <c:otherwise>
       <td width="25%"><c:out value="Not Defined" /></td>
    </c:otherwise>
	</c:choose>
    </tr></br>
    
     <tr> <td width="5%">About Me </td> 
	<c:choose>
    <c:when test="${not empty respObj.userDescription}">
    <td width="25%"> <input type="text" name="udesc" value="${respObj.userDescription}"> </td> 
	 </c:when>
    <c:otherwise>
       <td width="25%"> <input type="text" name="udesc" value=""> </td> 
    </c:otherwise>
	</c:choose>
    </tr></br> 
    <tr> <td width="5%">Gender</td> 
	 <c:choose>
    <c:when test="${respObj.gender} == 1">
         <td width="25%"><c:out value="Male" /></td> 
    </c:when>
	 <c:when test="${respObj.gender} == 2">
         <td width="25%"><c:out value="Female" /></td> 
    </c:when>
    <c:otherwise>
        <td width="25%"><c:out value="Not Defined" /></td>
    </c:otherwise>
	</c:choose>
    </tr></br>
     <tr> <td width="5%">Email </td> 
	<c:choose>
    <c:when test="${not empty respObj.userName}">
    <td width="25%"><c:out value="${respObj.userName}" /></td> 
	 </c:when>
    <c:otherwise>
       <td width="25%"><c:out value="Not Defined" /></td>
    </c:otherwise>
	</c:choose>
    </tr></br>   
    <tr> <td width="5%">Address </td> 
	<c:choose>
    <c:when test="${not empty respObj.contactAddressLine1}">
    <td width="25%"> <input type="text" name="cntctAddLn1" value="${respObj.contactAddressLine1}"> </td> 
	 </c:when>
    <c:otherwise>
     <td width="25%"> <input type="text" name="cntctAddLn1" value=""> </td> 
    </c:otherwise>
	</c:choose>
    </tr></br>   
    <tr> <td width="5%">Zip Code </td> 
  
	<c:choose>
    <c:when test="${not empty respObj.zipcode}">
    <td width="25%"><input type="text" name="zipcode" value="${respObj.zipcode}" /></td> 
	 </c:when>
    <c:otherwise>
       <td width="25%"><c:out value="Not Defined" /></td>
    </c:otherwise>
	</c:choose>
    </tr></br>   
    <tr> <td width="5%">Language </td> 

	<c:choose>
    <c:when test="${not empty respObj.language}">
     <td width="25%"> <input type="text" name="lang" value="${respObj.language}"> </td> 
	 </c:when>
    <c:otherwise>   
        <td width="25%"> <input type="text" name="lang" value=""> </td> 
    </c:otherwise>
	</c:choose>
    </tr></br>   
	
    <tr> <td width="5%">Contact Number </td> 
	 <c:choose>
    <c:when test="${not empty respObj.contactNumber}">
     <td width="25%"> <input type="text" name="contactNum" value="${respObj.contactNumber}"> </td> 
	 </c:when>
    <c:otherwise>
       <td width="25%"> <input type="text" name="contactNum" value=""> </td> 
    </c:otherwise>
	</c:choose>
    </tr></br>  
       <input type="hidden" name="appType" value="4"/> 
   		<input type="hidden" name="id" value="${respObj.getId()}"/> 
     
     <tr>
		<td colspan="2">&nbsp;<input type="submit" value="UPDATE"  id="button">
		&nbsp;&nbsp; <input name="Reset" type="reset" class="button" value="RESET" /> 
		&nbsp;&nbsp;<input type="submit" value="Cancle"  id="button" onclick="goBack()" ></td>
     </tr>

	 </form>
     </c:if></br></br>
</table>

</body>
</html>