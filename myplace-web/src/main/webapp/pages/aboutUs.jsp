<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>	
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
 <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">  
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<title>Myplace- about us</title>

<link rel="stylesheet" href="<%=request.getContextPath()%>/css/myplace.css" type="text/css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/pagefont.css" type="text/css">


</head>

<body>
<table width="100%" border="0">


<div style=" width:98%; margin:auto; margin-top:50px; margin-bottom:10px;"></div>
<tr>
      <td colspan="2">&nbsp;
	</td>
  </tr>
  <c:if test="${not empty message}">
 <tr>
      <td colspan="2"> <font size="3" color="red"><b>${message}</b> &nbsp;</font> </td>
  </tr>
 </c:if>
  
  <c:if test="${not empty respObj}">
 <tr>
      <td colspan="2"><b>${respObj}</b> &nbsp; </td>
  </tr>
 </c:if>
 </table>
</body>
</html>
