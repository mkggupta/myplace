<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/myplace.css" type="text/css">
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/css/pagefont.css" />
<title>userProfile</title>
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
   <!--  
   <tr> 
	<td  colspan="2" style="text-align:right"><a href="<%=request.getContextPath()%>/pages/register.jsp">Change my Password </a>&nbsp;&nbsp;</br></br>
	Welcome <c:out value="${respObj.contactName}"/>&nbsp;&nbsp;</br>
    </td> </tr>  
    -->
  <tr> 
    <td colspan="2" width="25%"><img src="${respObj.imgUrls[0]}" alt='photo' style="width:154px;height:108px;"/></td> 
	 </tr>  
	 <tr> <td width="5%">Name </td> 
	 <c:choose>
    <c:when test="${not empty respObj.contactName}">
    <td width="25%"><c:out value="${respObj.contactName}" /></td> 
	 </c:when>
    <c:otherwise>
       <td width="25%"><c:out value="Not Defined" /></td>
    </c:otherwise>
	</c:choose>
    </tr>
    <tr> <td width="5%">Account Status </td> 
	 <c:choose>
    <c:when test="${respObj.status==1}">
         <td width="25%"><c:out value="Verified" /></td> 
    </c:when>
	 <c:when test="${respObj.status ==2}">
         <td width="25%"><c:out value="Blocked" /></td> 
    </c:when>
    <c:otherwise>
     <td width="25%"><c:out value="Not Verified"/> 
	 
	  <c:if test="${not empty respObj.verifyAccUrl}">
		&nbsp;&nbsp;<a href="javascript:document.verifyAcc.submit()">Verify My Account</a>
	  </c:if>
		
	</td>
    </c:otherwise>
	</c:choose>
    </tr>
	 <form id="verifyAcc" name="verifyAcc" method="post" action="${respObj.verifyAccUrl}">
			 <input type="hidden" name="appType" value="4"/> 
			<input type="hidden" name="id" value="${respObj.getId()}"/> 
		</form>
    <tr> <td width="5%">Gender</td> 
	 <c:choose>
    <c:when test="${respObj.gender==1}">
         <td width="25%"><c:out value="Male" /></td> 
    </c:when>

	 <c:when test="${respObj.gender== 2}">
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
    </tr>  
       <tr> <td width="5%">About Me </td> 
	<c:choose>
    <c:when test="${not empty respObj.userDescription}">
    <td width="25%"><c:out value="${respObj.userDescription}" /></td> 
	 </c:when>
    <c:otherwise>
       <td width="25%"><c:out value="Not Defined" /></td>
    </c:otherwise>
	</c:choose>
    </tr>
    <tr> <td width="5%">Address </td> 
	<c:choose>
    <c:when test="${not empty respObj.contactAddressLine1}">
    <td width="25%"><c:out value="${respObj.contactAddressLine1}" /></td> 
	 </c:when>
    <c:otherwise>
       <td width="25%"><c:out value="Not Defined" /></td>
    </c:otherwise>
	</c:choose>
    </tr>
    <tr> <td width="5%">Zip Code </td> 
  
	<c:choose>
    <c:when test="${not empty respObj.zipcode}">
    <td width="25%"><c:out value="${respObj.zipcode}" /></td> 
	 </c:when>
    <c:otherwise>
       <td width="25%"><c:out value="Not Defined" /></td>
    </c:otherwise>
	</c:choose>
    </tr> 
    <tr> <td width="5%">Language </td> 

	<c:choose>
    <c:when test="${not empty respObj.language}">
    <td width="25%"><c:out value="${respObj.language}" /></td> 
	 </c:when>
    <c:otherwise>
       <td width="25%"><c:out value="Not Defined" /></td>
    </c:otherwise>
	</c:choose>
    </tr>   
	
    <tr> <td width="5%">Contact Number </td> 
	 <c:choose>
    <c:when test="${not empty respObj.contactNumber}">
    <td width="25%"><c:out value="${respObj.contactNumber}" /></td> 
	 </c:when>
    <c:otherwise>
       <td width="25%"><c:out value="Not Defined" /></td>
    </c:otherwise>
	</c:choose>
    </tr></br>  

	<tr> <td width="5%">Total Business </td> 
	 <c:choose>
    <c:when test="${not empty respObj.bussListUrl}">
    <td width="25%">&nbsp;<a href="${respObj.bussListUrl}?appType=4"> <c:out value="${respObj.bussCnt}" /> </a></td> 
     </c:when>
    <c:otherwise>
       <td width="25%"><c:out value="${respObj.bussCnt}" /></td>
    </c:otherwise>
	</c:choose>
    </tr>
  
    <c:if test="${not empty respObj.profileUpdateUrl}">
   <form id="editProfile" name="editProfile" method="post" action="${respObj.profileUpdateUrl}">
   <input type="hidden" name="appType" value="4"/> 
   <input type="hidden" name="id" value="${respObj.getId()}"/> 
   </form>
   <tr> 
   <td colspan="2">
   <a href="javascript:document.editProfile.submit()">Edit My Profile </a>&nbsp;&nbsp;</br></br>&nbsp;
   </td></tr>
 	</c:if>
     </c:if>
	

</table>

</body>
</html>