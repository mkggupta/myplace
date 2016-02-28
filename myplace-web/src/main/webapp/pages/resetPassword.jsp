<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
 <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">  
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<title>findon-Reset Password</title>

<link rel="stylesheet" href="<%=request.getContextPath()%>/css/myplace.css" type="text/css">
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/css/pagefont.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/js/validation.js"></script>
</head>

<body>

 <c:if test="${not empty message}">
 <div style=" width:98%; margin:auto; margin-top:50px; margin-bottom:10px;">
 <font size="3"><b>${message}</b></font><br /></div>
 </c:if>

<div style=" width:98%; margin:auto; margin-top:50px; margin-bottom:10px;"><font size="3"><b>Please fill in your new password to reset your password</b></font><br /></div>
  
  
  <form name="cpForm"
		action="<%=request.getContextPath()%>/rest/api/usrauth/resetPassword" method="post"
		enctype="multipart/form-data" onsubmit="return validateResetPassForm();" style="margin:0px;">

  <div style=" width:98%; margin:auto; margin-top:15px; margin-bottom:10px;">
  <div style=" width:98%; float:left; margin-right:10px;"></div><div style="width:98%; float:left; margin-top:2px;"><label>
   <input type="hidden" name="usrName" value="${usrName}" />
 	<input type="hidden" name="forgetPasswordId" value="${forgetPasswordId}" />
 	 <input type="hidden" name="id" value="${userId}" />
 	 <input type="hidden" name="verificationCode" value="${verificationCode}" />

  <div style=" width:98%; float:left; margin-top:15px; margin-right:10px;"><font size="2"><b>NEW PASSWORD* </b></font></div>
  <div style="width:98%; margin-top:2px; float:left;"><label><input type="password" name="password" placeholder="Enter New Password*" class="textfield" maxlength="50" /></label>
  </div>
  <div style=" width:98%; float:left; margin-top:15px; margin-right:10px;"><font size="2"><b>PLEASE RE-ENTER YOUR PASSWORD*</b></font></div><div style="width:98%; margin-top:2px; float:left;"><label><input type="password" name="confPassword" placeholder="Re-Enter New Password*" class="textfield" maxlength="50" /></label></div>
 
 <div style="width:98%; float:left; margin-top:15px; margin-right:10px;"><label>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input type="submit" value="SAVE"  /></label></div>
  </div>
</form>

</body>
</html>
