<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
 <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">  
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<title>header</title>


</head>

<body>
<table class="header" width="100%" border="0">
 <%
		Object sUserName=session.getAttribute("userName");
		if (sUserName!=null){
			
			String username=sUserName.toString();
			request.setAttribute("username", username);
		}
  %>

  <c:choose>
    <c:when test="${not empty username}">
   <tr> 
    <form id="logout" name="logout" method="post" action="<%=request.getContextPath()%>/rest/api/user/pvt/logout">
   	<input type="hidden" name="appType" value="4"/> 
     <input type="hidden" name="userId" value="<%=session.getAttribute("userId")%>"/> 
    
   </form>
   
    <form id="changePass" name="changePass" method="post" action="<%=request.getContextPath()%>/rest/api/usr/pvt/loadchangepassword">
   	<input type="hidden" name="appType" value="4"/> 
     <input type="hidden" name="id" value="<%=session.getAttribute("userId")%>"/> 
   </form>
   <td  style="text-align:right">
   <a href="<%=request.getContextPath()%>/rest/api/usr/pvt/profile/<%=session.getAttribute("userId")%>?appType=4" >My Profile </a>&nbsp;&nbsp;&nbsp;&nbsp;
   <a href="<%=request.getContextPath()%>/rest/api/search/pub/getsearch">Search Business Now </a>&nbsp;&nbsp;&nbsp;&nbsp;
   <a href="javascript:document.changePass.submit()">Change My Password </a>&nbsp;&nbsp;
   <a href="javascript:document.logout.submit()">LogOut </a>&nbsp;&nbsp;</br></br> </td>
  </tr>
 
   <tr> 

    	<td clospan="2" style="text-align:right">Welcome ${username}&nbsp;&nbsp;</br>
	
    </td> </tr>  
  </c:when>
    <c:otherwise>
	  <tr> 
	<td clospan="2" style="text-align:right">&nbsp;&nbsp;</br>
    </td> </tr> 
   <tr> 
	<td clospan="2" style="text-align:right">&nbsp;<a href="<%=request.getContextPath()%>/pages/login.jsp">Go To Home </a>&nbsp;</br>
    </td> </tr>  
 </c:otherwise>
	</c:choose>
  
</table>
</body>
</html>
