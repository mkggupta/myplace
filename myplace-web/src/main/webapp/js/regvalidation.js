function validateRegForm()
	{
	var fName=document.register.fName.value;
	 fName=fName;
	var lName=document.register.lName.value;
	 lName=lName;
	var user=document.register.username.value;
	 user=user;
	var pass=document.register.password.value;
	 pass=pass;
	var gender=document.register.gender.value; 
	 
	 if(fName =="")
	{
	  document.getElementById('error').innerHTML="Please Enter First Name";
		// alert("Please Enter FirstName");
		 document.register.fName.focus();
		 return false;
	}
	if(fName.length<3 || fName.length>25)
	{
	  document.getElementById('error').innerHTML="Please Enter First Name between 3 to 25 char";
		//alert("Please Enter FirstName between 3 to 25 char");
		document.register.fName.focus();
		return false;
	}
	/*if(lName =="")
	{
	  document.getElementById('error').innerHTML="Please Enter Last Name";
		//alert("Please Enter LastName");
		document.register.lName.focus();
		return false;
	}
	if(lName.length<3 || lName.length>25)
	{
	   document.getElementById('error').innerHTML="Please Enter Last Name between 3 to 25 char";
		//alert("Please Enter LastName between 3 to 25 char");
		document.register.lName.focus();
		return false;
	}*/
	if(user == "")
	{
	  document.getElementById('error').innerHTML="Please Enter Email";
		//alert("Please Enter Email");
		document.register.username.focus();
		return false;
	}
	if(user.length<3 || user.length>25)
	{
		document.getElementById('error').innerHTML="Please Enter Email between 3 to 25 char";
		//alert("Please Enter Email between 3 to 25 char");
		document.register.username.focus();
		return false;
	}
	if (!ValidateEmail(user))
	{
		document.getElementById('error').innerHTML="Please provide a valid email address";
		//alert("Please provide a valid email address");
		document.register.username.focus();
		return false;
	}
	if(pass == "")
	{
		document.getElementById('error').innerHTML="Please Enter Password";
		//alert("Please Enter Password");
		document.register.password.focus();
		return false;
	}
	if(pass.length<3 || pass.length>25)
	{
		document.getElementById('error').innerHTML="Please Enter Password between 3 to 25 char";
		//alert("Please Enter Password between 3 to 25 char");
		document.register.password.focus();
		return false;
	}
	if(gender =="")
	{
		 document.getElementById('error').innerHTML="Please Select Gender";
		return false;
	}
}

function ValidateEmail(mail)   
	{  
	 if (/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/.test(mail))  
	  {  
		return true;
	  }  
		return false;
	}  




function validateLoginForm()
	{
	var user=document.login.username.value;
	 user=user;
	
	var pass=document.login.password.value;
	 pass=pass;
	if(user =="")
	{
		alert("Please Enter Username");
	document.login.username.focus();
	return false;
	}
	if(user.length<3 || user.length>25)
	{
	//document.getElementById('error').innerHTML="Please Enter Username between 3 to 25 char";
		alert("Please Enter Username between 3 to 25 char");
	document.login.username.focus();
	return false;
	}
	 
	if(pass =="")
	{
		alert("Please Enter Password");
		document.login.password.focus();
		return false;
	}
	if(pass.length<3 || pass.length>25)
	{
		//document.getElementById('error').innerHTML="Please Enter Password between 3 to 25 char";
		alert("Please Enter Password between 3 to 25 char");
		document.login.password.focus();
		return false;
	}
	
}
