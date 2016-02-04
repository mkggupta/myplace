<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
 <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">  
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<title>register jsp</title>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/regvalidation.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/validation.js"></script>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/myplace.css" type="text/css">
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/css/pagefont.css" />
</head>
<body>
<div style="width:98%; margin:auto; margin-top:50px; margin-bottom:10px;"> </div>
<form id="register" name="register" method="post" action="/myplace/rest/api/user/pub/register" onsubmit="return validateRegForm()" enctype="multipart/form-data">
<table width="100%" border="0"  align="right">

<tr>
	<td style="width:55%;">  </td>
	
	<td colspan="2" align="center" style="font-weight:bold;"> Create an account </td>
	
</tr>
<tr>
	<td colspan="3" align="right">&nbsp; &nbsp;</td>
	
</tr>
<tr>
	<td style="width:55%;">  </td>
	 <td colspan="2" align="center"><font size="3" color="red"><b><div id="error"> </div></b></font> </td>

</tr>
 <c:if test="${not empty message}">
 	<tr>
	<td style="width:55%;">  </td>
  <td colspan="2" align="center"> <font size="3" color="red"><b>${message}</b> </font> </td>
  	</tr>
  </c:if>
<tr>
<td style="width:55%;">  </td>
<td style="width:20%;" align="right" >&nbsp;First Name * : </td>
<td style="width:25%;"><input type="text" maxlength="35"  value="" name="fName" id="fName" width="100%" placeholder="Enter First Name*" autocomplete="off"></td>
</tr>
<tr>
<td style="width:55%;">  </td>
<td style="width:20%;" align="right">&nbsp;Last Name &nbsp;&nbsp;:</td>
<td style="width:25%;"><input type="text" maxlength="35"  value="" name="lName" id="lName" width="100%" placeholder="Enter Last Name" autocomplete="off"></td>
</tr>
<tr>
<td style="width:55%;">  </td>
<td style="width:20%;" align="right">&nbsp;Email *&nbsp; :</td>
<td style="width:25%;"><input type="text" maxlength="35"  name="usrName" id="username" width="100%" placeholder="Enter Email*" autocomplete="off"></td>
</tr>
<tr>
<td style="width:55%;">  </td>
<td style="width:20%;" align="right">&nbsp;Password *&nbsp;&nbsp;:</td>
<td style="width:25%;"><input type="password" maxlength="25"  name="password" id="password" width="100%" placeholder="Enter Password*" autocomplete="off"></td>
</tr>
<tr>
<td style="width:55%;">  </td>
<td style="width:20%;" align="right">&nbsp;Gender&nbsp;&nbsp;:</td>
<td style="width:25%;"><input type="radio" name="gender" id="gender" value="1" checked/>Male
			<input type="radio" name="gender" id="gender" value="2"/> Female
			<input type="radio" name="gender" id="gender" value="0"/> Not Known
</td>
</tr>
<tr>
<td style="width:55%;">  </td>

<td style="width:20%;" align="right">&nbsp;My Picture &nbsp;:</td>
<td style="width:25%;"><input type="file" name="pdata"></td>
</tr>
 <input type="hidden" name="registrationMode" value="4"/> 
 <input type="hidden" name="appType" value="4"/> 
 <tr>
 <td style="width:55%;">  </td>
<td  colspan="2"><font size="1"> &nbsp; &nbsp; &nbsp; &nbsp;By clicking on 'Create an account' above, you confirm that you accept the  
  <a href="<%=request.getContextPath()%>/pages/myplaceTerms.jsp" target="_blank">terms and conditions</a> & <a href="<%=request.getContextPath()%>/pages/myplaceTerms.jsp" target="_blank">privacy policy</a>. &nbsp;</font><br/> </td>
 </tr>
  <tr>
<td colspan="3"> </td>
 </tr>
 <tr>
<td colspan="3"> </td>
 </tr>
 <tr>
<td colspan="3"> </td>
 </tr>
 <tr>
<td style="width:55%;">  </td>
<td style="width:20%;"> &nbsp;</td>
<td style="width:25%;"  >&nbsp;<input type="submit" value="Register Me"  id="button">
&nbsp;<input type="button" value="Go Back"  id="button" onclick="goBack()" ></td>
</tr>
</table>
</form>
<table width="100%" border="0">
 <tr>
<td colspan="3">&nbsp;<br/><br/><br/> </td>
 </tr>
 <tr>
<td colspan="3">&nbsp;<br/><br/><br/>  </td>
 </tr>
 <tr>
<td colspan="3"> &nbsp;<br/><br/> </td>
 </tr>
</table>
<jsp:include page="footer.jsp" flush="true" />
</body>
</html>