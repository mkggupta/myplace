<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>	
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
 <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">  
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<title>Myplace- Contact Us</title>

<link rel="stylesheet" href="<%=request.getContextPath()%>/css/myplace.css" type="text/css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/pagefont.css" type="text/css">
<script type="text/javascript" src="<%=request.getContextPath()%>/js/validation.js"></script>


</head>

<body>
<form id="contactUs" name="contactUs" method="post" action="/myplace/rest/api/feedback/pub/savefbacks" onsubmit="return validateContactForm()" enctype="multipart/form-data">
<table width="100%" border="0">


<div style=" width:98%; margin:auto; margin-top:50px; margin-bottom:10px;"></div>
<tr>
      <td width="50%" ><font size="3" color="green"><b>&nbsp;Contact Us </b></font>
	</td>
	<td width="50%" align="center" colspan="2"><font size="3" color="green"><b>&nbsp;Suggestion/Query or Feedback </b></font>
	</td>
  </tr>
 
  
 
 <tr>
      <td width="50%"><h5>For any queries please mail us at <a href="mailto:manish.g.wnsgs@gmail.com">manish.g.wnsgs@gmail.com</a></h5></td>
	 <td width="50%" colspan="2"> <h5>We value your suggestions. So, whether you are looking for information or just trying to provide feedback, we look forward to hearing from you! Please describe your query/suggestion and send it to us. We will try and revert on any queries at the earliest. </h5></td>
  </tr>


<tr>
	<td style="width:50%;">  </td>
	 <td style="width:50%;" align="center" colspan="2"><font size="3" color="red"><b><div id="error"> </div></b></font> </td>

</tr>
 <c:if test="${not empty message}">
 	<tr>
	<td style="width:50%;"> &nbsp; </td>
  <td style="width:50%;" colspan="2"> <font size="3" color="red"><b>${message}</b> </font> </td>
  	</tr>
  </c:if>
<tr>
<td style="width:50%;">  </td>
<td style="width:13%;" align="right"><font size="2"><b> Enter Name* : </b></font></td>
<td style="width:37%"> <input type="text" maxlength="35"  value="" name="name" id="name" width="100%" placeholder="Enter Name*" autocomplete="off"></td>
</tr>
<tr>
<td style="width:50%;">  </td>
<td style="width:13%;" align="right"><font size="2"><b> Enter Contact Number* : </b></font></td>
<td style="width:37%"> <input type="text" name="contactNum" maxlength="15" value="" placeholder="Enter Contact Number*" autocomplete="off"></td>
</tr>
<tr>
<td style="width:50%;">  </td>
<td style="width:13%;" align="right"><font size="2"><b> Enter Email* : </b></font></td>
<td style="width:37%"><input type="text" maxlength="35"  name="email" id="email" width="100%" placeholder="Enter Email*" autocomplete="off"></td>
</tr>
<tr>
<td style="width:50%;"> </td>
<td style="width:13%;" align="right"><font size="2"><b> Enter Suggestion* : </b></font></td>
<td style="width:37%"> <textarea name="feedBackText" rows="4" cols="30" maxlength="4000" placeholder="Enter suggestion/query/feedback description*"  autocomplete="off"></textarea></td>
</tr>
 <input type="hidden" name="appType" value="4"/> 
 

<td style="width:50%;">  </td>
<td style="width:50%;" colspan="2" align="center">&nbsp;<input type="submit" value="Submit"  id="button"></td>
</tr>

 </table>
 </form>
</body>
</html>
