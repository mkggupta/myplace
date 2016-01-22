<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0">
<title>Myplace </title>

<link rel="stylesheet" href="./css/myplace.css" type="text/css">

<link type="text/css" rel="stylesheet" href="./css/pagefont.css" />

</head>

<body>
<script type="text/javascript">
		function validateForm() {
			alert("Please enter email address");
			var password = document.forms["cpForm"]["email"].value;
			if (email== null || email== "") {
				alert("Please enter email address");
				return false;
			}
			return true;
		}
	</script>
	<div style=" width:98%; margin:auto; margin-top:50px; margin-bottom:10px;"><br/> </div>
<form id="login" name="login" method="post" action="/myplace/rest/api/user/pub/register" onsubmit="return validateLoginForm()">
<table width="300" border="0">
<tbody>
	<tr>


</tr>
<tr>
<td colspan="2">&nbsp;username :<input type="text" maxlength="25"  value="" name="usrName" id="username" width="100%"></td></tr>
<tr>
<td colspan="2">&nbsp;password :<input type="password" maxlength="25" value="" name="password" id="password" width="100%"></td></tr>
<tr>
<td>&nbsp;<input type="submit" value="Sign in"  id="button" ></td>

<td>&nbsp; <a href="<%=request.getContextPath()%>/pages/forgetPassword.jsp">Forgot password?> </a></td></tr>
</tbody>
</table>
</form>

</body>
</html>
