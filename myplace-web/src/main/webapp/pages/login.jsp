<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0">
<title>Myplace </title>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/regvalidation.js"></script>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/myplace.css" type="text/css">
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/css/pagefont.css" />

</head>

<body>

<div style=" width:98%; margin:auto; margin-top:40px; margin-bottom:10px;"> </div>
<table width="800" border="0">
<form id="login" name="login" method="post" action="/myplace/rest/api/user/pub/login" onsubmit="return validateLoginForm()">
<tbody>
	<tr>
<div id="error" ></div>

</tr>
<tr>
Sign In to your account 
</tr>

 <input type="hidden" name="registrationMode" value="4"/> 
  <input type="hidden" name="appType" value="4"/> 
 <c:if test="${not empty message}">
 <tr>
      <td colspan="2"> <font size="3" color="red"><b>${message}</b> &nbsp;</font> </td>
  </tr>
 </c:if>
<tr>
<td colspan="2">&nbsp;Username :<input type="text" maxlength="25"  name="usrName" id="username" placeholder="Enter User Name" width="100%"></td>
</tr>
<tr>
<td colspan="2">&nbsp;Password :<input type="password" maxlength="25"  name="password" placeholder="Enter Password" width="100%"></td>
</tr>
<tr>
<td align="center"><input type="submit" value="Sign in"  id="button" ></td>
<td>&nbsp; <a href="<%=request.getContextPath()%>/pages/forgetPassword.jsp">Can't access your account? </a></td>
</tr>
<tr>

&nbsp;
</tr>

<tr>
<td colspan="2">&nbsp;<a href="<%=request.getContextPath()%>/pages/register.jsp">Sign up for a new account </a></td>
</tr>
<tr>
<td colspan="2">&nbsp;</td>
</tr>
</tbody>
</form>
</table>
<jsp:include page="footer.jsp" flush="true" />
</body>
</html>
