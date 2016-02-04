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
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/myplace.css" type="text/css">
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/css/pagefont.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/js/validation.js"></script>
<title>editUserProfile</title>

</head>
<body>

<jsp:include page="header.jsp" flush="true" />
<table class="userprofile" width="80%" border="0" align="center">

	<c:if test="${not empty message}">
 	<tr>
   	<td colspan="2" align="center"> <font size="3" color="red"><b>${message}</b> &nbsp;</br></font> </td>
  	</tr>
 	</c:if>

   <c:if test="${not empty respObj}">
  
<form name="editform" method="post" action="${respObj.profileUpdateUrl}" onsubmit="return validateUserEditForm()" enctype="multipart/form-data">
  	<tr><td colspan=2 style="font-weight:bold;" align="center">Edit Profile </td></tr>	
  	
  	 
  <tr> 
    <td colspan="2" align="center"><img src="${respObj.imgUrls[0]}" alt='photo' style="width:154px;height:108px;"/> </td> 
    </tr></br>  
    <tr> <td style="width:50%;" align="right">Change My Picture :&nbsp;</td> 
	
    <td style="width:50%;"> <input type="file" name="pdata"> </td> 
	
    </tr> 
	 <tr> <td style="width:50%;" align="right">Name :&nbsp;</td> 
	 <c:choose>
    <c:when test="${not empty respObj.contactName}">
    <td style="width:50%;"><input type="text" name="cName" maxlength="35" value="${respObj.contactName}" /></td> 
	 </c:when>
    <c:otherwise>
       <td style="width:50%;"><input type="text" name="cName" maxlength="35" value="" placeholder="Enter Name" autocomplete="off" /></td>
    </c:otherwise>
	</c:choose>
    </tr>
    
     <tr> <td style="width:50%;" align="right">About Me :&nbsp;</td> 
	<c:choose>
    <c:when test="${not empty respObj.userDescription}">
    <td style="width:50%;"> <input type="text" name="udesc" maxlength="100" value="${respObj.userDescription}"> </td> 
	 </c:when>
    <c:otherwise>
       <td style="width:50%;"> <input type="text" name="udesc" maxlength="100" value="" placeholder="Enter About Me" autocomplete="off"> </td> 
    </c:otherwise>
	</c:choose>
    </tr> 
    <tr> <td style="width:50%;" align="right">Gender :&nbsp;</td>  
	 <c:choose>
    <c:when test="${respObj.gender == 1}">
         <td style="width:50%;"><c:out value="Male" /></td> 
    </c:when>
	 <c:when test="${respObj.gender== 2}">
         <td style="width:50%;"><c:out value="Female" /></td> 
    </c:when>
    <c:otherwise>
        <td style="width:50%;"><c:out value="Not Defined" /></td>
    </c:otherwise>
	</c:choose>
    </tr>
     <tr> <td style="width:50%;" align="right">Email :&nbsp;</td> 
	<c:choose>
    <c:when test="${not empty respObj.userName}">
    <td style="width:50%;"><c:out value="${respObj.userName}" /></td> 
	 </c:when>
    <c:otherwise>
       <td style="width:50%;"><c:out value="Not Defined" /></td>
    </c:otherwise>
	</c:choose>
    </tr>   
    <tr> <td style="width:50%;" align="right">Address :&nbsp;</td> 
	<c:choose>
    <c:when test="${not empty respObj.contactAddressLine1}">
    <td style="width:50%;"> <input type="text" name="cntctAddLn1" maxlength="100" value="${respObj.contactAddressLine1}"> </td> 
	 </c:when>
    <c:otherwise>
     <td style="width:50%;"> <input type="text" name="cntctAddLn1" maxlength="100" value="" placeholder="Enter Address" autocomplete="off"> </td> 
    </c:otherwise>
	</c:choose>
    </tr>   
    <tr> <td style="width:50%;" align="right">Zip Code :&nbsp;</td> 
  
	<c:choose>
    <c:when test="${not empty respObj.zipcode}">
    <td style="width:50%;"><input type="text" name="zipcode" maxlength="10" value="${respObj.zipcode}" /></td> 
	 </c:when>
    <c:otherwise>
       <td style="width:50%;"><input type="text" name="zipcode" maxlength="10" value="" placeholder="Enter Zip Code" autocomplete="off"/></td>
    </c:otherwise>
	</c:choose>
    </tr>   
    <tr> <td style="width:50%;" align="right">Language :&nbsp;</td> 

	<c:choose>
    <c:when test="${not empty respObj.language}">
     <td style="width:50%;"> <input type="text" name="lang" value="${respObj.language}"> </td> 
	 </c:when>
    <c:otherwise>   
        <td style="width:50%;"> <input type="text" name="lang" maxlength="20" value="" placeholder="Enter Language" autocomplete="off"> </td> 
    </c:otherwise>
	</c:choose>
    </tr>   
	
    <tr> <td style="width:50%;" align="right">Contact Number * :&nbsp;</td> 
	 <c:choose>
    <c:when test="${not empty respObj.contactNumber}">
     <td style="width:50%;"> <input type="text" name="contactNum" maxlength="15" value="${respObj.contactNumber}"> </td> 
	 </c:when>
    <c:otherwise>
       <td style="width:50%;"> <input type="text" name="contactNum" maxlength="15" value="" placeholder="Enter Contact Number*" autocomplete="off"> </td> 
    </c:otherwise>
	</c:choose>
    </tr>  
       <input type="hidden" name="appType" value="4"/> 
   		<input type="hidden" name="id" value="${respObj.getId()}"/> 
	<tr>
		<td colspan="2">&nbsp;</td>
     </tr>
     
     <tr>
	 <td style="width:50%;"> &nbsp;</td>
		<td >&nbsp;<input type="submit" value="UPDATE"  id="button">
		&nbsp;&nbsp; <input name="Reset" type="reset" class="button" value="RESET" /> 
		&nbsp;&nbsp;<input type="button" value="CANCLE"  id="button" onclick="goBack()" ></td>
     </tr>

	 </form>
     </c:if>
</table>
<jsp:include page="footer.jsp" flush="true" />
</body>
</html>