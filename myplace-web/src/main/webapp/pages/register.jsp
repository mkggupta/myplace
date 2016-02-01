<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>register jsp</title>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/regvalidation.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/validation.js"></script>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/myplace.css" type="text/css">
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/css/pagefont.css" />
</head>
<body>
<div style="width:98%; margin:auto; margin-top:50px; margin-bottom:10px;"> </div>
<form id="register" name="register" method="post" action="/myplace/rest/api/user/pub/register" onsubmit="return validateRegForm()" enctype="multipart/form-data">
<table width="600" border="0">

<tr>
	<td colspan="2" align="right"> Sign Up </td>
	<td style="width:50%;"> &nbsp; </td>
	
</tr>
<tr>
	<td colspan="3" align="right">&nbsp; &nbsp;</td>
	
</tr>
<tr>
	 <td colspan="3" align="center"><font size="3" color="red"><b><div id="error"> </div></b></font> </td>

</tr>
 <c:if test="${not empty message}">
 	<tr>
  <td colspan="3"> <font size="3" color="red"><b>${message}</b> </font> </td>
  	</tr>
  </c:if>
<tr>
<td style="width:30%;" align="right" >&nbsp;First Name * : </td>
<td colspan="2" style="width:70%;"><input type="text" maxlength="35"  value="" name="fName" id="fName" width="100%"></td>
</tr>
<tr>
<td style="width:30%;" align="right">&nbsp;Last Name &nbsp;&nbsp;:</td>
<td colspan="2" style="width:70%;"><input type="text" maxlength="35"  value="" name="lName" id="lName" width="100%"></td>
</tr>
<tr>
<td style="width:30%;" align="right">&nbsp;Email *&nbsp; :</td><td colspan="2" style="width:70%;"><input type="text" maxlength="35"  name="usrName" id="username" width="100%"></td>
</tr>
<tr>
<td style="width:30%;" align="right">&nbsp;Password *&nbsp;&nbsp;:</td><td colspan="2" style="width:70%;"><input type="password" maxlength="25"  name="password" id="password" width="100%"></td>
</tr>
<tr>
<td style="width:30%;" align="right">&nbsp;Gender&nbsp;&nbsp;:</td>
<td colspan="2" style="width:70%;"><input type="radio" name="gender" id="gender" value="1" checked/>Male
			<input type="radio" name="gender" id="gender" value="2"/> Female
			<input type="radio" name="gender" id="gender" value="0"/> Not Known
</td>
</tr>
<tr>
<td style="width:30%;" align="right">&nbsp;My Picture &nbsp;:</td>
<td colspan="2" style="width:70%;"><input type="file" name="pdata"></td>
</tr>
 <input type="hidden" name="registrationMode" value="4"/> 
 <input type="hidden" name="appType" value="4"/> 
 <tr>
  <td colspan="3">  &nbsp;</br> </td>
  </tr>
 <tr>
<td style="width:40%;" align="right" colspan="2">&nbsp;<input type="submit" value="Register Me"  id="button">
&nbsp;<button onclick="goBack()">Go Back</button> </td>
<td> &nbsp;</td>
</tr>

</table>
</form>

</body>
</html>