<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
 <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">  
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<title>findon-Verify Account </title>

<link rel="stylesheet" href="<%=request.getContextPath()%>/css/myplace.css" type="text/css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/pagefont.css" type="text/css">
<script type="text/javascript" src="<%=request.getContextPath()%>/js/validation.js"></script>

</head>

<body>
<jsp:include page="header.jsp" flush="true" />
<table width="100%" border="0">

<div style=" width:98%; margin:auto; margin-top:50px; margin-bottom:10px;">
<tr>
      <td colspan="2">Please enter your registered email address, we will send you an email to verify your Account<br /></div>
	</td>
  </tr>
  <c:if test="${not empty message}">
 <tr>
      <td colspan="2"> <font size="2" color="green"><b>${message}</b> &nbsp;</font> </td>
  </tr>
 </c:if>
  
  <form name="cpForm"
		action="<%=request.getContextPath()%>/rest/api/usrauth/pvt/verifyaccountrequest" method="post"
		enctype="multipart/form-data" onsubmit="return validateForgetForm();" style="margin:0px;">
  <input type="hidden" name="appType" value="4"/> 
  <div style="width:98%; margin:auto; margin-top:15px; margin-bottom:10px;"> </div>
  <div style="width:98%; float:left; margin-right:10px;"></div><div style="width:98%; float:left; margin-top:2px;"><label></label> </div>
 

  <div style="width:98%; float:left; margin-top:15px; margin-right:10px;">
  <tr>
      <td colspan="2">Enter Registered Email</div>
	  </td>
  </tr>
   <tr>
      <td style="width:50%;">
  <div style="width:98%; margin-top:2px; float:left;"><label>
  <input type="text" name="email" maxlength="75" class="textfield" autocomplete="off"/></label></div></td>
  <td style="width:50%;"> </td>
  </tr>
 <tr>
  <td style="width:50%;"> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="submit" value="SUBMIT" id="button"/>
 &nbsp;&nbsp;<input type="button" value="Go Back"  id="button" onclick="goBack()" ></td>
  <td> </td>
  </tr>
 
</form>
</table>
<table height="100%" border="0" align="right">
<tr>
<td colspan="2">&nbsp;&nbsp;&nbsp;&nbsp;<br/><br/>&nbsp;<br/></td>
</tr>
<tr>
<td colspan="2">&nbsp;&nbsp;&nbsp;&nbsp;<br/><br/>&nbsp;<br/></td>
</tr>
<tr>
<td colspan="2">&nbsp;&nbsp;&nbsp;&nbsp;<br/><br/>&nbsp;<br/></td>
</tr>
</table>
<jsp:include page="footer.jsp" flush="true" />
</body>
</html>
