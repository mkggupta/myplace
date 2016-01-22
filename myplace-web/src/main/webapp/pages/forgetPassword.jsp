<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0">
<title>Myplace </title>

<link rel="stylesheet" href="../css/myplace.css" type="text/css">
<link rel="stylesheet" href="../css/pagefont.css" type="text/css">


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
<div style=" width:98%; margin:auto;"><img src="../images/logo.jpg" /></div>

<div style=" width:98%; margin:auto; margin-top:50px; margin-bottom:10px;">Please enter your registered email address, we will send you an email to get your password<br /></div>
  
  
  <form name="cpForm"
		action="/myplace/rest/api/usrauth/forgetpasswordrequest" method="post"
		enctype="multipart/form-data" onsubmit="return validateForm();" style="margin:0px;">

  <div style=" width:98%; margin:auto; margin-top:15px; margin-bottom:10px;"> </div>
  <div style=" width:98%; float:left; margin-right:10px;"></div><div style="width:98%; float:left; margin-top:2px;"><label></label> </div>
 

  <div style=" width:98%; float:left; margin-top:15px; margin-right:10px;">Enter Registered Email</div>
  <div style="width:98%; margin-top:2px; float:left;"><label><input type="text" name="email" maxlength="35"  value="" class="textfield" /></label></div>

 <div style="width:98%; float:left; margin-top:15px; margin-right:10px;"><label>
 <input type="submit" value="SUBMIT" class="save" /></label></div>
 
</form>

</body>
</html>
