<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
 <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">  
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<title>findon-Search</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/myplace.css" type="text/css">
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/css/pagefont.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/js/validation.js"></script>
 <script type="text/javascript" src="<%=request.getContextPath()%>/js/locvalidation.js"></script>
</head>
<body onLoad="ShowHideDiv()">
<jsp:include page="header.jsp" flush="true" />
<div style=" width:98%; margin:auto; margin-top:40px; margin-bottom:10px;"> </div>
<table class="searchCriteria" width="100%" border="0">
 
 
  <form id="search" name="search" method="post" action="<%=request.getContextPath()%>/rest/api/search/pub/getBuss" onsubmit="return validateSearchForm();">
  <tr>
	 <td colspan="2">
	     <input type="radio" name="type" id="cat" value="1"  onclick="ShowHideDiv()" <c:if test="${searchObj.type ==1}">CHECKED</c:if> >Search By Category
		 <input type="radio" name="type" id="zipcode" value="2" onclick="ShowHideDiv()" <c:if test="${searchObj.type ==2}">CHECKED</c:if> > Search By Zip
		 <input type="radio" name="type" id="loc" value="3" onclick="ShowHideDiv()" <c:if test="${searchObj.type ==3}">CHECKED</c:if> > Search By Location
		 <input type="radio" name="type" id="txt" value="4" onclick="ShowHideDiv()" <c:if test="${searchObj.type ==4}">CHECKED</c:if> > Search By Text
	 </td>
	
	</tr>
	<tr>
	 <td colspan="2">
	 &nbsp;&nbsp;
	 <div id="dvCategory" style="display: none">
		 Select Your Category:
		<select name="catId">
		 <c:forEach items="${catObj}" var="catDTO">    
			<option name="${catDTO.catId}" value="${catDTO.catId}" ${catDTO.catId ==searchObj.catId ? 'selected' : ''}> ${catDTO.catName} </option>
		 </c:forEach>
        </select>
	  </div>
	<div id="dvZip" style="display: none">
		&nbsp;&nbsp;Enter Zip :&nbsp;&nbsp;<input type="text" maxlength="10"  name="bZip" id="bZip"  value="${searchObj.zipCode}" width="100%" autocomplete="off">
	</div>
	<div id="dvText" style="display: none">
		&nbsp;&nbsp;Enter Text : &nbsp;&nbsp;<input type="text" maxlength="45"  name="text" id="text" value="${searchObj.text}" width="100%" autocomplete="off">
	 </div>	
	 </td>
	</tr>

	<tr>
	 <td colspan="2">
	 <div id="dvLocation" style="display: none">
		&nbsp;&nbsp;Enter Latitude : &nbsp;&nbsp;<input type="text" maxlength="25"  name="bLat" id="bLat" value="${searchObj.latitude}" width="100%" autocomplete="off">
		&nbsp;&nbsp;Enter Longitude : &nbsp;&nbsp;<input type="text" maxlength="25"  name="bLong" id="bLong" value="${searchObj.longitude}" width="100%" autocomplete="off"> &nbsp;&nbsp; <input type="button" value="Get My Latitude Longitude" onclick="retrieve_search_location()" >
	</div>
	 </td>
	</tr>
	<input type="hidden" name="appType" value="4"/> 
	<tr>
	<td width="20%" align="right">&nbsp;&nbsp;&nbsp;&nbsp;<input type="submit" value="Search"  id="button" ></td>
	 <td align="left">&nbsp;&nbsp;&nbsp;&nbsp;</td>
	</tr>
<tr>
	 <td colspan="2">&nbsp;&nbsp;&nbsp;<hr />
	 </td>
	 
</form>
</table>

<table class="searchResult" width="100%" border="0">

</tr>
<tr>
	 <td colspan="2">&nbsp;&nbsp;&nbsp;
	 </td>
</tr>
<c:if test="${not empty message}">
 	<tr>
  <td colspan="2" align="center"> <font size="3" color="red"><b>${message}</b> &nbsp;</br></font> </td>
  	</tr>
 </c:if>

<c:forEach items="${searchRespObj}" var="bussSearchDTO">    	
  <tr> 
    <td  style="width:15%;">
     <c:choose>
    <c:when test="${not empty bussSearchDTO.imgUrls}">
		 <img src="${bussSearchDTO.imgUrls[0]}" alt='photo' style="width:174px;height:118px;"/>
	 </c:when>
    <c:otherwise>
       <img src=" " alt='photo not found' style="width:174px;height:118px;"/>
    </c:otherwise>
	</c:choose>
   
    </td> 
     <td style="width:85%;"> <table class="bussShortDesc" width="100%" border="0">  
	 <tr> 
    <td  style="width:15%;" align="right">Business Name :&nbsp; </td>
	<td  style="width:85%;"> 
	 <c:choose>
    <c:when test="${not empty bussSearchDTO.bussName}">
		<c:out value="${bussSearchDTO.bussName}" />
	 </c:when>
    <c:otherwise>
      <c:out value="Not Defined" />
    </c:otherwise>
	</c:choose>
	</td>
	</tr>
	<tr> 
    <td  style="width:15%;" align="right">Business Address :&nbsp;</td>
	<td  style="width:85%;">
	 <c:choose>
    <c:when test="${not empty bussSearchDTO.bussAddress}">
		<c:out value="${bussSearchDTO.bussAddress}" />
	 </c:when>
    <c:otherwise>
      <c:out value="Not Defined" />
    </c:otherwise>
	</c:choose>
	</td>
	</tr>
	 <tr> 
    <td  style="width:15%;" align="right">Business Phone :&nbsp;</td>
	<td  style="width:85%;">
	 <c:choose>
    <c:when test="${not empty bussSearchDTO.bussPhone}">
		<c:out value="${bussSearchDTO.bussPhone}" />
	 </c:when>
    <c:otherwise>
      <c:out value="Not Defined" />
    </c:otherwise>
	</c:choose>
	</td>
	</tr>
	 <tr> 
    <td  style="width:15%;" align="right">Business Zip :&nbsp;</td>
	<td  style="width:85%;">
	 <c:choose>
    <c:when test="${not empty bussSearchDTO.bussZip}">
		<c:out value="${bussSearchDTO.bussZip}" />
	 </c:when>
    <c:otherwise>
      <c:out value="Not Defined" />
    </c:otherwise>
	</c:choose>
	 </td>
	</tr>
	 <tr> 
    <td  colspan="2">&nbsp;&nbsp;&nbsp;&nbsp;<a href="${bussSearchDTO.detailUrl}?appType=4"> <c:out value="Get More Detail.." /> </a>
   </td>
	</tr>
</table>

    </td></tr>
    <tr>
	 <td colspan="2"> <hr />
	 </td>
	 </tr>
     </c:forEach>
	<tr>
	 <td colspan="2">&nbsp;&nbsp;&nbsp;
	 </td>
	</tr>
	
   
	<c:if test="${not empty searchObj.nextUrl}">
	<form id="searchMore" name="searchMore" method="post" action="${searchObj.nextUrl}">
	  <input type="hidden" name="appType" value="4"/> 
	  <input type="hidden" name="type" value="${searchObj.type}"/> 
	  <input type="hidden" name="catId" value="${searchObj.catId}"/> 
	  <input type="hidden" name="bZip" value="${searchObj.zipCode}"/> 
	  <input type="hidden" name="text" value="${searchObj.text}"/> 
	  <input type="hidden" name="bLong" value="${searchObj.longitude}"/> 
	  <input type="hidden" name="bLat" value="${searchObj.latitude}"/> 
 	
	<tr>
	 <td colspan="2" style="text-align:center"><a href="javascript:document.searchMore.submit()"> Get More Results</a> &nbsp;&nbsp;
	 </td>
	 </tr>
	 </form>
	</c:if>

	<tr>
	 <td colspan="2">&nbsp;&nbsp;&nbsp;
	 </td>
	</tr>
</table>
<c:if test="${empty searchRespObj}">
<table height="100%" border="0" align="right">
<tr>
<td >&nbsp;<br/><br/>&nbsp;<br/><br/></td>
</tr>
<tr>
<td >&nbsp;<br/><br/>&nbsp;<br/><br/></td>
</tr>
<tr>
<td >&nbsp;<br/><br/>&nbsp;<br/><br/></td>
</tr>
</table>
 </c:if>

<jsp:include page="footer.jsp" flush="true" />

</body>
</html>