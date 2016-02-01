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
<title>Search</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/myplace.css" type="text/css">
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/css/pagefont.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/js/validation.js"></script>
 
</head>
<body onLoad="ShowHideDiv()">

<div style=" width:98%; margin:auto; margin-top:40px; margin-bottom:10px;"> </div>
<table class="bussProfile" width="100%" border="0">
 
 
  <form id="search" name="search" method="post" action="/myplace/rest/api/search/pub/getBuss" onsubmit="return validateSearchForm();">
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
		&nbsp;&nbsp;Enter Zip :&nbsp;&nbsp;<input type="text" maxlength="6"  name="bZip" id="bZip"  value="${searchObj.zipCode}" width="100%">
	</div>
	<div id="dvText" style="display: none">
		&nbsp;&nbsp;Enter Text : &nbsp;&nbsp;<input type="text" maxlength="25"  name="text" id="text" value="${searchObj.text}" width="100%">
	 </div>	
	 </td>
	</tr>

	<tr>
	 <td colspan="2">
	 <div id="dvLocation" style="display: none">
		&nbsp;&nbsp;Enter Latitude : &nbsp;&nbsp;<input type="text" maxlength="25"  name="bLat" id="bLat" value="${searchObj.latitude}" width="100%">
		&nbsp;&nbsp;Enter Longitude : &nbsp;&nbsp;<input type="text" maxlength="25"  name="bLong" id="bLong" value="${searchObj.longitude}" width="100%">
	</div>
	 </td>
	</tr>
	<input type="hidden" name="appType" value="4"/> 
	<tr>
	<td width="20%" align="right">&nbsp;&nbsp;&nbsp;&nbsp;<input type="submit" value="Search"  id="button" ></td>
	 <td align="left">&nbsp;&nbsp;&nbsp;&nbsp;</td>
	</tr>

	 
</form>

<tr>
	 <td colspan="2">&nbsp;&nbsp;&nbsp;<hr />
	 </td>
</tr>
<tr>
	 <td colspan="2">&nbsp;&nbsp;&nbsp;
	 </td>
</tr>
<c:if test="${not empty message}">
 	<tr>
  <td colspan="2"> <font size="4" color="red"><b>${message}</b> &nbsp;</br></font> </td>
  	</tr>
 </c:if>
<c:forEach items="${searchRespObj}" var="bussSearchDTO">    	
  <tr> 
    <td  width="20%">
     <c:choose>
    <c:when test="${not empty bussSearchDTO.imgUrls}">
		 <img src="${bussSearchDTO.imgUrls[0]}" alt='photo' style="width:154px;height:108px;"/>
	 </c:when>
    <c:otherwise>
       <img src=" " alt='photo not found' style="width:154px;height:108px;"/>
    </c:otherwise>
	</c:choose>
   
    </td> 
     <td width="80%">Business Name : 
	 <c:choose>
    <c:when test="${not empty bussSearchDTO.bussName}">
		<c:out value="${bussSearchDTO.bussName}" />
	 </c:when>
    <c:otherwise>
      <c:out value="Not Defined" />
    </c:otherwise>
	</c:choose>
	</br>Business Address :
	 <c:choose>
    <c:when test="${not empty bussSearchDTO.bussAddress}">
		<c:out value="${bussSearchDTO.bussAddress}" />
	 </c:when>
    <c:otherwise>
      <c:out value="Not Defined" />
    </c:otherwise>
	</c:choose>
	</br>Business Phone :
	 <c:choose>
    <c:when test="${not empty bussSearchDTO.bussPhone}">
		<c:out value="${bussSearchDTO.bussPhone}" />
	 </c:when>
    <c:otherwise>
      <c:out value="Not Defined" />
    </c:otherwise>
	</c:choose>
	</br>Business Zip :
	 <c:choose>
    <c:when test="${not empty bussSearchDTO.bussZip}">
		<c:out value="${bussSearchDTO.bussZip}" />
	 </c:when>
    <c:otherwise>
      <c:out value="Not Defined" />
    </c:otherwise>
	</c:choose><hr />
    </td></tr>
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
	 <td colspan="2" style="text-align:right"><a href="javascript:document.searchMore.submit()"> Get More Results</a> &nbsp;&nbsp;
	 </td>
	 </tr>
	 </form>
	</c:if>

	<tr>
	 <td colspan="2">&nbsp;&nbsp;&nbsp;
	 </td>
	</tr>
</table>


</body>
</html>