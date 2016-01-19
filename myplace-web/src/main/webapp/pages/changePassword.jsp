<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0">
<title>Myplace</title>

<link rel="stylesheet" href="../css/myplace.css" type="text/css">

<style>
.textfield {width:35%; height:30px; line-height:30px;}
.save {font-weight:bold; background-color:#e89431; width:35%; height:32px; color:#FFFFFF;}
@media only screen and (max-width: 640px) { .textfield {width:100%; height:30px; line-height:30px;}
.save {font-weight:bold; background-color:#e89431; width:100%; height:32px; color:#FFFFFF;}
}

@media only screen and (min-width:320px)  { .textfield {width:100%; height:30px; line-height:30px;} 
.save {font-weight:bold; background-color:#e89431; width:100%; height:32px; color:#FFFFFF;} }
@media only screen and (min-width:768px)  { .textfield {width:80%; height:30px; line-height:30px;}
.save {font-weight:bold; background-color:#e89431; width:80%; height:32px; color:#FFFFFF;} }
@media only screen and (min-width:1024px) { .textfield {width:35%; height:30px; line-height:30px;}
.save {font-weight:bold; background-color:#e89431; width:35%; height:32px; color:#FFFFFF;} }
@media only screen and (min-width:1900px) { .textfield {width:35%; height:30px; line-height:30px;}
.save {font-weight:bold; background-color:#e89431; width:35%; height:32px; color:#FFFFFF;} }


</style>

</head>

<body>
<script type="text/javascript">
		function validateForm() {
			var password = document.forms["cpForm"]["password"].value;
			if (password == null || password == "") {
				alert("Please enter your new password");
				return false;
			}
			var confPassword = document.forms["cpForm"]["confPassword"].value;
			if (confPassword == null || confPassword == "") {
				alert("Please re-enter your new password");
				return false;
			}
			if (confPassword != password) {
				alert("Passwords do not match.");
				return false;
			}
			return true;
		}
	</script>
	<div style=" width:98%; margin:auto; margin-top:50px; margin-bottom:10px;"><br />
<div style=" width:98%; margin:auto;"><img src="../images/logo.jpg" /></div>

<div style=" width:98%; margin:auto; margin-top:50px; margin-bottom:10px;">Please fill in your new password to reset your password<br /></div>
  
  
  <form name="cpForm"
		action="/myplace/rest/api/usrauth/resetPassword" method="post"
		enctype="multipart/form-data" onsubmit="return validateForm();" style="margin:0px;">

  <div style=" width:98%; margin:auto; margin-top:15px; margin-bottom:10px;">
  <div style=" width:98%; float:left; margin-right:10px;"></div><div style="width:98%; float:left; margin-top:2px;"><label>
   <input type="hidden" name="usrName" value=<%=request.getParameter("usrName")%> />
 	<input type="hidden" name="forgetPasswordId" value=<%=request.getParameter("forgetPasswordId")%> />
 	 <input type="hidden" name="id" value=<%=request.getParameter("userId")%> />
 	 <input type="hidden" name="verificationCode" value=<%=request.getParameter("verificationCode")%> />

  <div style=" width:98%; float:left; margin-top:15px; margin-right:10px;">NEW PASSWORD</div><div style="width:98%; margin-top:2px; float:left;"><label><input type="password" name="password" class="textfield" /></label></div>
  <div style=" width:98%; float:left; margin-top:15px; margin-right:10px;">PLEASE RE-ENTER YOUR PASSWORD</div><div style="width:98%; margin-top:2px; float:left;"><label><input type="password" name="confPassword" class="textfield" /></label></div>
 
 <div style="width:98%; float:left; margin-top:15px; margin-right:10px;"><label>
 <input type="submit" value="SAVE" class="save" /></label></div>
  </div>
</form>

</body>
</html>
