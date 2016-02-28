<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>	
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
 <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">  
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<title>findon-Report Issue</title>

<link rel="stylesheet" href="<%=request.getContextPath()%>/css/myplace.css" type="text/css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/pagefont.css" type="text/css">
<script type="text/javascript" src="<%=request.getContextPath()%>/js/validation.js"></script>


</head>

<body>
<form id="report" name="report" method="post" action="<%=request.getContextPath()%>/rest/api/report/pub/reportbuss" onsubmit="return validateReportForm()" enctype="multipart/form-data">
 <input type="hidden" name="appType" value="4"/> 
 <input type="hidden" name="bId" value="${businessId}"/> 
 <input type="hidden" name="userId" value="${userId}"/> 
 <input type="hidden" name="rType" value="${rType}"/> 


<table width="100%" border="0">

<div style=" width:98%; margin:auto; margin-top:50px; margin-bottom:10px;"></div>
<tr>
     
	<td  align="center" colspan="2"><font size="3" color="green"><b>&nbsp;Report Issue or Feedback </b></font>
	</td>
  </tr>
 
<tr>
	
	 <td  align="center" colspan="2"><font size="3" color="red"><b><div id="error"> </div></b></font> </td>

</tr>
 <c:if test="${not empty message}">
 	<tr>
  <td align="center" colspan="2"> <font size="2" color="green"><b>${message}</b> </font> </td>
  	</tr>
  </c:if>
 <tr>

<td style="width:48%;" align="right"><font size="2"><b> Issue Type : </b></font></td>
<td style="width:52%"> 
	<select name="rrId">
		 <c:forEach items="${respObj}" var="ReportReasonInfo">    
			<option value="${ReportReasonInfo.reasonId}"> ${ReportReasonInfo.reasonText} </option>
		 </c:forEach>
        </select>

</td>
</tr> 
  
<tr>

<td style="width:48%;" align="right"><font size="2"><b> Enter Name* : </b></font></td>
<td style="width:52%"> <input type="text" maxlength="35"  value="" name="rName" id="rName" width="100%" placeholder="Enter Name*" autocomplete="off"></td>
</tr>
<tr>

<td style="width:48%;" align="right"><font size="2"><b> Enter Contact Number* : </b></font></td>
<td style="width:52%"> <input type="text" name="rPhone" maxlength="15" value="" placeholder="Enter Contact Number*" autocomplete="off"></td>
</tr>
<tr>

<td style="width:48%;" align="right"><font size="2"><b> Enter Email : </b></font></td>
<td style="width:52%"><input type="text" maxlength="35"  name="rMail" id="rMail" width="100%" placeholder="Enter Email" autocomplete="off"></td>
</tr>
<tr>

<td style="width:48%;" align="right"><font size="2"><b> Enter Comments* : </b></font></td>
<td style="width:52%"> <textarea name="cmnt" rows="4" cols="30" maxlength="4000" placeholder="Enter Comments*"  autocomplete="off"></textarea></td>
</tr>

 
<tr>

<td style="width:50%;" colspan="2" align="center">&nbsp;<input type="submit" value="Submit" id="button"/></td>
</tr>

 </table>
 </form>
</body>
</html>
