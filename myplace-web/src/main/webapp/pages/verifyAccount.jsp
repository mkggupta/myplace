<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0">
<title>Myplace </title>

<link rel="stylesheet" href="<%=request.getContextPath()%>/css/myplace.css" type="text/css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/pagefont.css" type="text/css">
<script type="text/javascript" src="<%=request.getContextPath()%>/js/validation.js"></script>

</head>

<body>
<table width="750" border="0">

<div style=" width:98%; margin:auto; margin-top:50px; margin-bottom:10px;">
<tr>
      <td colspan="2">Please enter your registered email address, we will send you an email to verify your Account<br /></div>
	</td>
  </tr>
  <c:if test="${not empty message}">
 <tr>
      <td colspan="2"> <font size="3" color="red"><b>${message}</b> &nbsp;</font> </td>
  </tr>
 </c:if>
  
  <form name="cpForm"
		action="/myplace/rest/api/usrauth/pvt/verifyaccountrequest" method="post"
		enctype="multipart/form-data" onsubmit="return validateForgetForm();" style="margin:0px;">
  <input type="hidden" name="appType" value="4"/> 
  <div style=" width:98%; margin:auto; margin-top:15px; margin-bottom:10px;"> </div>
  <div style=" width:98%; float:left; margin-right:10px;"></div><div style="width:98%; float:left; margin-top:2px;"><label></label> </div>
 

  <div style=" width:98%; float:left; margin-top:15px; margin-right:10px;">
  <tr>
      <td colspan="2">Enter Registered Email</div>
	  </td>
  </tr>
   <tr>
      <td colspan="2">
  <div style="width:98%; margin-top:2px; float:left;"><label>
  <input type="text" name="email" maxlength="75" class="textfield" /></label></div></td>
  </tr>
 <tr>
  <td align="center" style="width:50%;"> <input type="submit" value="SUBMIT" id="button"/>
 &nbsp;&nbsp;<a href="#" onclick="goBack()"> Back </a>  </td>
  <td> </td>
  </tr>
 
</form>
</table>
</body>
</html>