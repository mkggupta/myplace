function goBack() {
    window.history.back();
}

function validateChangePassForm() {
	var password = document.forms["cpForm"]["password"].value;
	password=password.trim();
	if (password == null || password == "") {
		alert("Please enter your new password");
		document.cpForm.password.focus();
		return false;
	}
	if(password.length<3 || password.length>25)
	{
		alert("Please Enter Password between 3 to 25 char");
		document.cpForm.password.focus();
		return false;
	}
	
	var confPassword = document.forms["cpForm"]["confPassword"].value;
	confPassword=confPassword.trim();
	if (confPassword == null || confPassword == "") {
		alert("Please re-enter your new password");
		document.cpForm.confPassword.focus();
		return false;
	}
	if (confPassword != password) {
		alert("Passwords do not match.");
		return false;
	}
	return true;
}


function validateForgetForm() {

	var email = document.forms["cpForm"]["email"].value;
	email=email.trim();
	if (email== null || email== "") {
		alert("Please enter email address");
		document.cpForm.email.focus();
		return false;
	}
	if (!ValidateEmail(email))
	{
//		/document.getElementById('error').innerHTML="Please provide a valid email address";
		alert("Please provide a valid email address");
		document.cpForm.email.focus();
		return false;
	}
	
	return true;
}

function ValidateEmail(mail)   
{  
 if (/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/.test(mail))  
  {  
	return true;
  }  
	return false;
}  