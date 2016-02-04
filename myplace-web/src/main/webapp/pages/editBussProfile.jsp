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
<title>editBussProfile</title>
</head>

<body>
<jsp:include page="header.jsp" flush="true" />
<table class="editBussProfile" width="80%" border="0" align="center">

	<c:if test="${not empty message}">
 	<tr>
  <td colspan="2" align="center"> <font size="3" color="red"><b>${message}</b> &nbsp;</font> </td>
  	</tr>
 	</c:if>
	<tr>
   <td colspan="2"> &nbsp; </td>
  	</tr>
	<tr>
   <td colspan="2"></td> 
  	</tr>
   <c:if test="${not empty respObj}">
   
   <form id="editBussProfile" name="editBussProfile" method="post" action="${respObj.updateUrl}" onsubmit="return validateBussEditForm()" enctype="multipart/form-data" >
   
   <tr><td colspan=2 style="font-weight:bold;" align="center">Edit Business Profile</td></tr>	
   
   <tr>
    <td colspan="2" align="center"><img src="${respObj.imgUrls[0]}" alt='photo' style="width:154px;height:108px;"/></td> 
    </tr> 
    
    <tr> <td style="width:50%;" align="right">Change Business Picture :&nbsp;</td> 
	
    <td style="width:50%;"> <input type="file" name="data"> </td> 
	
    </tr>
	 <tr> <td style="width:50%;" align="right">Business Name* :&nbsp;</td> 
	 <c:choose>
    <c:when test="${not empty respObj.bussName}">
    <td style="width:50%;"><input type="text" name="bName" maxlength="55" value="${respObj.bussName}" /></td> 
	 </c:when>
	</c:choose>
    </tr>
    <tr> <td style="width:50%;" align="right">Business Contact Name* :&nbsp;</td> 
	 <c:choose>
    <c:when test="${not empty respObj.bussContName}">
    <td style="width:50%;"><input type="text" name="bContName" maxlength="35" value="${respObj.bussContName}" /></td> 
	 </c:when>
	  <c:otherwise>
        <td style="width:50%;"><input type="text" name="bContName" maxlength="35" value="" placeholder="Enter Contact Name" autocomplete="off"/></td> 
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
	
    <tr> <td style="width:50%;" align="right">Business Address* :&nbsp;</td> 
	 <c:choose>
   <c:when test="${not empty respObj.bussAddress}">
         <td style="width:50%;"><textarea name="bAddress" rows="2" cols="25" maxlength="300"><c:out value="${respObj.bussAddress}" /> </textarea></td> 
    </c:when>
    <c:otherwise>
       <td style="width:50%;"><textarea name="bAddress" rows="2" cols="25" maxlength="300" placeholder="Enter Business Address*" autocomplete="off"></textarea></td> 
    </c:otherwise>
	</c:choose>
    </tr>
     <tr> <td style="width:50%;" align="right">Business Zip* :&nbsp;</td> 
	<c:choose>
    <c:when test="${not empty respObj.bussZip}">
    <td style="width:50%;"><input type="text" name="bZip" maxlength="15" value="${respObj.bussZip}" /></td> 
	 </c:when>
    <c:otherwise>
       <td style="width:50%;"><input type="text" name="bZip" maxlength="15" value="" placeholder="Enter Zip Code*"  autocomplete="off"/></td>
    </c:otherwise>
	</c:choose>
    </tr>  
       <tr> <td style="width:50%;" align="right">Business Phone* :&nbsp;</td> 
	<c:choose>
    <c:when test="${not empty respObj.bussPhone}">
    <td style="width:50%;"><input type="text" name="bPhone" maxlength="65" value="${respObj.bussPhone}" /></td> 
	 </c:when>
    <c:otherwise>
       <td style="width:50%;"><input type="text" name="bPhone" maxlength="65" value="" placeholder="Enter Business Phone*" autocomplete="off"/></td>
    </c:otherwise>
	</c:choose>
    </tr>
    <tr> <td style="width:50%;" align="right">Business Email :&nbsp;</td> 
	<c:choose>
    <c:when test="${not empty respObj.bussEmail}">
    <td style="width:50%;"><input type="text" name="bEmail"  maxlength="40" value="${respObj.bussEmail}" /></td> 
	 </c:when>
    <c:otherwise>
       <td style="width:50%;"><input type="text" name="bEmail"  maxlength="40" value=""  placeholder="Enter Business Email"  autocomplete="off"/></td>
    </c:otherwise>
	</c:choose>
    </tr>  
    <tr> <td style="width:50%;" align="right">Business Web :&nbsp;</td> 
  
	<c:choose>
    <c:when test="${not empty respObj.bussWeb}">
    <td style="width:50%;"><input type="text" name="bWeb" maxlength="100" value="${respObj.bussWeb}" /></td> 
	 </c:when>
    <c:otherwise>
       <td style="width:50%;"><input type="text" name="bWeb" maxlength="100" value="" placeholder="e.g. http://google.com"  autocomplete="off"/></td>
    </c:otherwise>
	</c:choose>
    </tr>  
    <tr> <td style="width:50%;" align="right">Business Description* :&nbsp;</td> 

	<c:choose>
    <c:when test="${not empty respObj.bussDesc}">
    <td style="width:50%;"><textarea name="bDesc" rows="2" cols="25" maxlength="4000"><c:out value="${respObj.bussDesc}" /></textarea></td> 
	 </c:when>
    <c:otherwise>
       <td style="width:50%;"><textarea name="bDesc" rows="2" cols="25" maxlength="4000" placeholder="Enter Business Description*"  autocomplete="off"></textarea></td>
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
     
   	<input type="hidden" name="appType" value="4"/> 
     <input type="hidden" name="userId" value="${respObj.getUserId()}"/> 
     <input type="hidden" name="bId" value="${respObj.getBussId()}"/> 
   <tr>
		<td colspan="2">&nbsp;</td>
     </tr>
    <tr>
	<td style="width:50%;">&nbsp;</td>
		<td >&nbsp;<input type="submit" value="UPDATE"  id="button">
		&nbsp;&nbsp; <input name="Reset" type="Reset" class="button" value="RESET" /> 
		&nbsp;&nbsp;<input type="button" value="CANCLE"  id="button" onclick="goBack()" ></td>
     </tr>
      <tr>
		<td colspan="2">&nbsp;</td>
     </tr>
     </form>
     </c:if>
	
</table>
<jsp:include page="footer.jsp" flush="true" />
</body>
</html>