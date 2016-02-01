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
   <td> <a href="<%=request.getContextPath()%>/rest/api/about/pub/getAbt?appType=4">About Us </a>&nbsp;&nbsp; </td>
  </tr>
  
   <tr> 
	<td style="text-align:right">&nbsp;&nbsp;<br/>
    </td> </tr>  
  
</table>
</body>
</html>
