<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>register jsp</title>
<script type="text/javascript" src="../js/regvalidation.js"></script>
<script type="text/javascript" src="../js/validation.js"></script>
<link rel="stylesheet" href="../css/myplace.css" type="text/css">
<link type="text/css" rel="stylesheet" href="../css/pagefont.css" />
</head>
<body>
<div style=" width:98%; margin:auto; margin-top:50px; margin-bottom:10px;"> </div>
<form id="register" name="register" method="post" action="/myplace/rest/api/user/pub/register" onsubmit="return validateRegForm()">
<table width="300" border="0">
<tbody>
<tr>
	Sign up
<div style=" width:98%; margin:auto; margin-top:50px; margin-bottom:10px;"> </div>
</tr>
<tr>
	<div id="error"> </div>
<div style=" width:98%; margin:auto; margin-top:50px; margin-bottom:10px;"> </div>
</tr>
 <c:if test="${not empty message}">
 	<tr>
  <td colspan="2"> <font size="4" color="red"><b>${message}</b> &nbsp;</br></font> </td>
  	</tr>
  </c:if>
<tr>
<td colspan="2">&nbsp;FirstName :<input type="text" maxlength="25"  value="" name="fName" id="fName" width="100%"></td></tr>
<tr>
<tr>
<td colspan="2">&nbsp;LastName :<input type="text" maxlength="25"  value="" name="lName" id="lName" width="100%"></td></tr>
<tr>
<tr>
<td colspan="2">&nbsp;Email&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; :<input type="text" maxlength="25"  name="usrName" id="username" width="100%"></td></tr>
<tr>
<td colspan="2">&nbsp;Password&nbsp;&nbsp;:<input type="password" maxlength="25"  name="password" id="password" width="100%"></td></tr>
<tr>
 <input type="hidden" name="registrationMode" value="4"/> 
 <input type="hidden" name="appType" value="4"/> 
<td>&nbsp;<input type="submit" value="Register Me"  id="button" ></td>
<td>&nbsp; <button onclick="goBack()">Go Back</button> </td></tr>
</tbody>
</table>
</form>

</body>
</html>