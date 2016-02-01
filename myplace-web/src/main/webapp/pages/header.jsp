<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0">
<title>header</title>


</head>

<body>
<table class="header" width="100%" border="0">
	<c:if test="${(not empty respObj)}">
   <tr> 
    <form id="logout" name="logout" method="post" action="/myplace/rest/api/user/pvt/logout">
   	<input type="hidden" name="appType" value="4"/> 
     <input type="hidden" name="userId" value="<%=session.getAttribute("userId")%>"/> 
    
   </form>
   
    <form id="changePass" name="changePass" method="post" action="/myplace/rest/api/usr/pvt/loadchangepassword">
   	<input type="hidden" name="appType" value="4"/> 
     <input type="hidden" name="id" value="<%=session.getAttribute("userId")%>"/> 
   </form>
   <td  style="text-align:right"><a href="javascript:document.changePass.submit()">Change My Password </a>&nbsp;&nbsp;
   <a href="javascript:document.logout.submit()">LogOut </a>&nbsp;&nbsp;</br></br> </td>
  </tr>
  
   <tr> 
	<td style="text-align:right">Welcome <%=session.getAttribute("userName")%>&nbsp;&nbsp;</br>
    </td> </tr>  
  </c:if>
  
  <tr> 
	<td style="text-align:left">&nbsp;&nbsp;<a href="<%=request.getContextPath()%>/rest/api/search/pub/getsearch">search it </a>
    </td> 
    </tr> 
  
</table>
</body>
</html>
