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
<title>findon-Bussiness Profile</title>
</head>
<body>
<jsp:include page="header.jsp" flush="true" />
<table class="bussProfile" width="80%" border="0" align="center">
	<c:if test="${not empty message}">
 	<tr>
  <td colspan="2" align="center"> <font size="2" color="green"><b>${message}</b> &nbsp;</font> </td>
  	</tr>
 	</c:if>
	<tr>
   <td colspan="2" align="right"> &nbsp;&nbsp;</td>
  	</tr>
	<tr>
   <td colspan="2"></td>
  	</tr>
   <c:if test="${not empty respObj}">
    <tr> 
    <td colspan="2" align="center"><img src="${respObj.imgUrls[0]}" alt='photo' style="width:154px;height:108px;"/></td> 
    </tr> 
	<c:if test="${not empty respObj.updateUrl}">
    <tr> <td colspan="2" align="center">&nbsp;<a href="javascript:document.editBussProfile.submit()">Edit Business Profile </a><br/><br/></td></tr>
	</c:if>
	 <tr> <td style="width:50%;" align="right">Business Name :&nbsp;</td> 
	 <c:choose>
    <c:when test="${not empty respObj.bussName}">
    <td style="width:50%;"><c:out value="${respObj.bussName}" /></td> 
	 </c:when>
	</c:choose>
    </tr>
    <tr> <td style="width:50%;" align="right">Business Contact Name :&nbsp;</td> 
	 <c:choose>
   <c:when test="${not empty respObj.bussContName}">
         <td style="width:50%;"><c:out value="${respObj.bussContName}" /></td> 
    </c:when>
    <c:otherwise>
        <td style="width:50%;"><c:out value="Not Defined" /></td>
    </c:otherwise>
	</c:choose>
    </tr>
    
	<tr> <td style="width:50%;" align="right">Business Category :&nbsp;</td> 
	 <c:choose>
    <c:when test="${not empty respObj.catName}">
    <td style="width:50%;"><c:out value="${respObj.catName}" /></td> 
	 </c:when>
    <c:otherwise>
       <td style="width:50%;"><c:out value="Not Defined" /></td>
    </c:otherwise>
	</c:choose>
    </tr> 
	
    <tr> <td style="width:50%;" align="right">Business Address :&nbsp;</td> 
	 <c:choose>
   <c:when test="${not empty respObj.bussAddress}">
         <td style="width:50%;"><textarea rows="2" cols="25" readonly><c:out value="${respObj.bussAddress}" /></textarea></td> 
    </c:when>
    <c:otherwise>
        <td style="width:50%;"><c:out value="Not Defined" /></td>
    </c:otherwise>
	</c:choose>
    </tr>
     <tr> <td style="width:50%;" align="right">Business Zip :&nbsp;</td> 
	<c:choose>
    <c:when test="${not empty respObj.bussZip}">
    <td style="width:50%;"><c:out value="${respObj.bussZip}" /></td> 
	 </c:when>
    <c:otherwise>
       <td style="width:50%;"><c:out value="Not Defined" /></td>
    </c:otherwise>
	</c:choose>
    </tr>  
       <tr> <td style="width:50%;" align="right">Business Phone :&nbsp;</td> 
	<c:choose>
    <c:when test="${not empty respObj.bussPhone}">
    <td style="width:50%;"><c:out value="${respObj.bussPhone}" /></td> 
	 </c:when>
    <c:otherwise>
       <td style="width:50%;"><c:out value="Not Defined" /></td>
    </c:otherwise>
	</c:choose>
    </tr>
    <tr> <td style="width:50%;" align="right">Business Email :&nbsp;</td> 
	<c:choose>
    <c:when test="${not empty respObj.bussEmail}">
    <td style="width:50%;"><c:out value="${respObj.bussEmail}" /></td> 
	 </c:when>
    <c:otherwise>
       <td style="width:50%;"><c:out value="Not Defined" /></td>
    </c:otherwise>
	</c:choose>
    </tr>  
    <tr> <td style="width:50%;" align="right">Business Web :&nbsp;</td> 
  
	<c:choose>
    <c:when test="${not empty respObj.bussWeb}">
    <td style="width:50%;"><c:out value="${respObj.bussWeb}" /></td> 
	 </c:when>
    <c:otherwise>
       <td style="width:50%;"><c:out value="Not Defined" /></td>
    </c:otherwise>
	</c:choose>
    </tr>  
    <tr> <td style="width:50%;" align="right">Business Description :&nbsp;</td> 

	<c:choose>
    <c:when test="${not empty respObj.bussDesc}">
    <td style="width:50%;"><textarea name="bDesc" rows="2" cols="25" maxlength="4000" readonly><c:out value="${respObj.bussDesc}" /></textarea></td> 
	 </c:when>
    <c:otherwise>
       <td style="width:50%;"><c:out value="Not Defined" /></td>
    </c:otherwise>
	</c:choose>
    </tr>  
	
    <tr> <td style="width:50%;" align="right">Business Start Year :&nbsp;</td> 
	 <c:choose>
    <c:when test="${not empty respObj.bussStartDate}">
    <td style="width:50%;"><c:out value="${respObj.bussStartDate}" /></td> 
	 </c:when>
    <c:otherwise>
       <td style="width:50%;"><c:out value="Not Defined" /></td>
    </c:otherwise>
	</c:choose>
	 </tr> 
	<c:if test="${not empty respObj.updateUrl}">
   
     <form id="editBussProfile" name="editBussProfile" method="post" action="${respObj.updateUrl}">
   	<input type="hidden" name="appType" value="4"/> 
     <input type="hidden" name="userId" value="${respObj.getUserId()}"/> 
     <input type="hidden" name="bId" value="${respObj.getBussId()}"/> 
   </form>
     </c:if>
	</c:if>
	 <tr> <td colspan="2" >&nbsp;&nbsp;&nbsp;</td></tr> 
	<tr> <td colspan="2" align="center">&nbsp;&nbsp;
	<c:if test="${not empty respObj.reportUrl}">
	 <c:choose>
    <c:when test="${not empty sessionScope.userId}">
		<input type="button" value="Report This"  id="button" onclick="reportpopup('${respObj.reportUrl}${sessionScope.userId}')" >
	</c:when>
    <c:otherwise>
		<input type="button" value="Report This"  id="button" onclick="reportpopup('${respObj.reportUrl}0')" >
	</c:otherwise>
	</c:choose>
	 </c:if>
	&nbsp;<input type="button" value="Go Back"  id="button" onclick="goBack()" >&nbsp;&nbsp;</td></tr> 
</table>
<jsp:include page="footer.jsp" flush="true" />
</body>
</html>