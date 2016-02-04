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
<title>userProfile</title>
</head>
<body>
<jsp:include page="header.jsp" flush="true" />
<table class="userprofile" width="80%" border="0" align="center">


<c:if test="${not empty message}">
 	<tr>
   <td colspan="2" align="center"> <font size="4" color="red"><b>${message}</b> &nbsp;</br></font> </td>
  	</tr>
 	</c:if>

   <c:if test="${not empty respObj}">
   <tr> 
    <td colspan="2" align="center" style="font-weight:bold;">User Profile</td> 
	 </tr>  
  <tr> 
    <td colspan="2" align="center"><img src="${respObj.imgUrls[0]}" alt='photo' style="width:154px;height:108px;"/></td> 
	 </tr>  
	  <tr> 
   <td colspan="2" align="center">
   <a href="javascript:document.editProfile.submit()">Edit My Profile </a>&nbsp;&nbsp;</br>&nbsp;
   </td></tr>

	 <tr> <td style="width:50%;" align="right">Name :&nbsp;</td> 
	 <c:choose>
    <c:when test="${not empty respObj.contactName}">
    <td style="width:50%;" ><c:out value="${respObj.contactName}" /></td> 
	 </c:when>
    <c:otherwise>
       <td style="width:50%;"><c:out value="Not Defined" /></td>
    </c:otherwise>
	</c:choose>
    </tr>
    <tr> <td style="width:50%;" align="right">Account Status :&nbsp; </td> 
	 <c:choose>
    <c:when test="${respObj.status==1}">
         <td style="width:50%;"><c:out value="Verified" /></td> 
    </c:when>
	 <c:when test="${respObj.status ==2}">
         <td style="width:50%;"><c:out value="Blocked" /></td> 
    </c:when>
    <c:otherwise>
     <td style="width:50%;"><c:out value="Not Verified"/> 
	 
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
    <tr> <td style="width:50%;" align="right">Gender :&nbsp;</td> 
	 <c:choose>
    <c:when test="${respObj.gender==1}">
         <td style="width:50%;"><c:out value="Male" /></td> 
    </c:when>

	 <c:when test="${respObj.gender== 2}">
         <td style="width:50%;"><c:out value="Female" /></td> 
    </c:when>
    <c:otherwise>
        <td style="width:50%;"><c:out value="Not Defined" /></td>
    </c:otherwise>
	</c:choose>
    </tr></br>
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
       <tr> <td style="width:50%;" align="right">About Me :&nbsp;</td> 
	<c:choose>
    <c:when test="${not empty respObj.userDescription}">
    <td style="width:50%;"><c:out value="${respObj.userDescription}" /></td> 
	 </c:when>
    <c:otherwise>
       <td style="width:50%;"><c:out value="Not Defined" /></td>
    </c:otherwise>
	</c:choose>
    </tr>
    <tr> <td style="width:50%;" align="right">Address :&nbsp;</td> 
	<c:choose>
    <c:when test="${not empty respObj.contactAddressLine1}">
    <td style="width:50%;"><c:out value="${respObj.contactAddressLine1}" /></td> 
	 </c:when>
    <c:otherwise>
       <td style="width:50%;"><c:out value="Not Defined" /></td>
    </c:otherwise>
	</c:choose>
    </tr>
    <tr> <td style="width:50%;" align="right">Zip Code :&nbsp;</td> 
  
	<c:choose>
    <c:when test="${not empty respObj.zipcode}">
    <td style="width:50%;"><c:out value="${respObj.zipcode}" /></td> 
	 </c:when>
    <c:otherwise>
       <td style="width:50%;"><c:out value="Not Defined" /></td>
    </c:otherwise>
	</c:choose>
    </tr> 
    <tr> <td style="width:50%;" align="right">Language :&nbsp;</td> 

	<c:choose>
    <c:when test="${not empty respObj.language}">
    <td style="width:50%;"><c:out value="${respObj.language}" /></td> 
	 </c:when>
    <c:otherwise>
       <td style="width:50%;"><c:out value="Not Defined" /></td>
    </c:otherwise>
	</c:choose>
    </tr>   
	
    <tr> <td style="width:50%;" align="right">Contact Number :&nbsp;</td> 
	 <c:choose>
    <c:when test="${not empty respObj.contactNumber}">
    <td style="width:50%;"><c:out value="${respObj.contactNumber}" /></td> 
	 </c:when>
    <c:otherwise>
       <td style="width:50%;"><c:out value="Not Defined" /></td>
    </c:otherwise>
	</c:choose>
    </tr></br>  

	<tr> <td style="width:50%;" align="right">Total Business :&nbsp;</td> 
	 <c:choose>
    <c:when test="${not empty respObj.bussListUrl}">
    <td style="width:50%;">&nbsp;<a href="${respObj.bussListUrl}?appType=4"> <c:out value="${respObj.bussCnt}" /> </a></td> 
     </c:when>
    <c:otherwise>
       <td style="width:50%;"><c:out value="${respObj.bussCnt}" /></td>
    </c:otherwise>
	</c:choose>
    </tr>
  <tr> 
   <td colspan="2" align="center">&nbsp;
	</td></tr>
    <c:if test="${not empty respObj.profileUpdateUrl}">
   <form id="editProfile" name="editProfile" method="post" action="${respObj.profileUpdateUrl}">
   <input type="hidden" name="appType" value="4"/> 
   <input type="hidden" name="id" value="${respObj.getId()}"/> 
   </form>

 	</c:if>
     </c:if>
	

</table>
<jsp:include page="footer.jsp" flush="true" />
</body>
</html>