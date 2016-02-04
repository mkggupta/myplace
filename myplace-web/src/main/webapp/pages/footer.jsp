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
<title>footer</title>


</head>

<body>
<table class="footer" width="100%" border="0">
	<tr> 
   <td> &nbsp;&nbsp; </td>
  </tr>
  	<tr> 
   <td> &nbsp;&nbsp; </td>
  </tr>
   <tr> 
   <td style="text-align:center"> <a href="<%=request.getContextPath()%>/rest/api/about/pub/getAbt?appType=4" target="_blank">About Us</a>&nbsp;&nbsp;| &nbsp;&nbsp;
   <a href="<%=request.getContextPath()%>/pages/myplaceTerms.jsp" target="_blank">Legal </a>&nbsp;&nbsp;|&nbsp;&nbsp;
   <a href="<%=request.getContextPath()%>/pages/myplaceTerms.jsp" target="_blank">Terms of Service and Use </a>&nbsp;&nbsp;|&nbsp;&nbsp;
   <a href="<%=request.getContextPath()%>/pages/myplaceTerms.jsp" target="_blank">Privacy Policy </a>&nbsp;&nbsp;
   
   </td>
  </tr>
  
   <tr> 
	<td style="text-align:center">&nbsp;<font size="2">©Copyrights 2016. Myplace.com All Rights Reserved.</font><br/>
    </td> </tr>   
  
</table>
</body>
</html>
