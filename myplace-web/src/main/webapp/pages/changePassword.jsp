<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
 <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">  
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<title>findon-change password</title>

<link rel="stylesheet" href="<%=request.getContextPath()%>/css/myplace.css" type="text/css">
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/css/pagefont.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/js/validation.js"></script>
</head>

<body>
<jsp:include page="header.jsp" />
<table width="75%" border="0">
<tr>
      <td colspan="2" align="center"> &nbsp; </td>
  </tr>
  <tr>
      <td colspan="2" align="center"> &nbsp; </td>
  </tr>
	<tr>
      <td colspan="2" align="center">Please fill in your new password to change your current password <br/>
  </td>
  </tr>
  <c:if test="${not empty message}">
 <tr>
      <td colspan="2" align="center"> <font size="2" color="red"><b>${message}</b> &nbsp; </td>
  </tr>
 </c:if>

  <form name="cpForm"
		action="<%=request.getContextPath()%>/rest/api/usr/pvt/changepassword" method="post"
		enctype="multipart/form-data" onsubmit="return validateChangePassForm();" style="margin:0px;">
 		
  <c:if test="${not empty userId}">
  	<input type="hidden" name="id" value="${userId}" />
   </c:if>
   
  <input type="hidden" name="appType" value="4"/> 
  <tr>
 <td width="50%" align="right"> Old Password*:&nbsp; </td>
 <td ><input type="password" name="oldPassword" maxlength="50" placeholder="Old Password*" autocomplete="off" /></td>
</tr>
 <tr>
 <td width="50%" align="right"> New Password*:&nbsp; </td>
 <td><input type="password" name="password" maxlength="50" placeholder="New Password*" autocomplete="off"/></td>
 </tr>

 <tr>
 <td width="50%" align="right"> Please Re-enter Your Password*:&nbsp; </td>
 <td ><input type="password" name="confPassword" maxlength="50" placeholder="Re-enter Password*" autocomplete="off"/></td>
 </tr>
<tr>
      <td colspan="2" align="center"> &nbsp; </td>
  </tr>
  <tr>
	<td width="50%" align="right">&nbsp; </td>
      <td> <input type="submit" value="SUBMIT" /> &nbsp;<input type="button" value="CANCLE"  id="button" onclick="goBack()" > </td></td>
  </tr>
  

</form>
</table>
</table>
<table height="70%" border="0" align="right">
<tr>
<td colspan="2">&nbsp;<br/><br/><br/><br/></td>
</tr>
<tr>
<td colspan="2">&nbsp;<br/><br/><br/><br/></td>
</tr>
<tr>
<td colspan="2">&nbsp;<br/><br/><br/><br/></td>
</tr>
</table>
<jsp:include page="footer.jsp" flush="true" />
</body>
</html>
