function goBack() {
    //window.history.back();
    window.history.go(-1);
}

function validateChangePassForm() {
	
	var oldPassword = document.forms["cpForm"]["oldPassword"].value;
		oldPassword=oldPassword;
	if (oldPassword == null || oldPassword == "") {
		alert("Please enter your old password");
		document.cpForm.oldPassword.focus();
		return false;
	}
	var password = document.forms["cpForm"]["password"].value;
	password=password;
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
	
	if (oldPassword == password) {
		alert("Old Password and New Password could not be same.");
		return false;
	}
	
	var confPassword = document.forms["cpForm"]["confPassword"].value;
	confPassword=confPassword;
	if (confPassword == null || confPassword == "") {
		alert("Please re-enter your New Password");
		document.cpForm.confPassword.focus();
		return false;
	}
	if (confPassword != password) {
		alert("New Password and Confirm Password are not matching.");
		return false;
	}
	
}


function validateResetPassForm() {
	var password = document.forms["cpForm"]["password"].value;
	password=password;
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
	confPassword=confPassword;
	if (confPassword == null || confPassword == "") {
		alert("Please re-enter your new password");
		document.cpForm.confPassword.focus();
		return false;
	}
	if (confPassword != password) {
		alert("New Password and Confirm Password is not matching.");
		return false;
	}
	
}

function validateForgetForm() {

	var email = document.forms["cpForm"]["email"].value;
	email=email;
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
	
	
}

function ValidateEmail(mail)   
{  
 if (/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/.test(mail))  
  {  
	return true;
  }  
	return false;
}  

function validateUserEditForm()
{
	var a = document.editform.contactNum.value;
	if(a=="")
	{
	alert("Please Enter Contact Number");
	document.editform.contactNum.focus();
	return false;
	}
	if(isNaN(a))
	{
	alert("Enter Valid Numeric Number without +,-,space etc.");
	document.editform.contactNum.focus();
	return false;
	}
	if((a.length < 5) || (a.length > 15))
	{
	alert("Your Contact Number must be 5 to 15 digits");
	document.editform.contactNum.select();
	return false;
	}

	var zip = document.editform.zipcode.value;
	if(zip!= null && zip!="")
	{
		if(isNaN(zip))
		{
			alert("Enter Valid Numeric Zip Code without +,-,space etc.");
			document.editform.zipcode.focus();
			return false;
		}
		if((zip.length < 5) || (zip.length > 11))
		{
			alert("Your Zip Code must be between 5 to 10 digits");
			document.editform.zipcode.select();
			return false;
		}
	}

}

function validateURL(value) {
    return /^(https?|ftp):\/\/(((([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:)*@)?(((\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5])\.(\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5])\.(\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5])\.(\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5]))|((([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.)+(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.?)(:\d*)?)(\/((([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:|@)+(\/(([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:|@)*)*)?)?(\?((([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:|@)|[\uE000-\uF8FF]|\/|\?)*)?(\#((([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:|@)|\/|\?)*)?$/i.test(value);
}

function validateBussEditForm()
{
	var bussName = document.editBussProfile.bName.value;
	var bussContName = document.editBussProfile.bContName.value;
	var bussAddress = document.editBussProfile.bAddress.value;
	var bussZip = document.editBussProfile.bZip.value;
	var bussPhone = document.editBussProfile.bPhone.value;
	var bussEmail= document.editBussProfile.bEmail.value;
	var bussWeb = document.editBussProfile.bWeb.value;
	var bussDesc = document.editBussProfile.bDesc.value;
	bussName = bussName;
	bussContName=bussContName;
	bussAddress=bussAddress;
	bussZip=bussZip;
	bussPhone=bussPhone;
	bussEmail=bussEmail;
	bussWeb=bussWeb;
	bussDesc=bussDesc;

	var letters = /^[0-9./;#,+-]*$/;  
	

	if(bussPhone=="")
	{
		alert("Please Enter Business Contact Number");
		document.editBussProfile.bPhone.focus();
		return false;
	}

	if(!bussPhone.match(letters))  
	{  
		alert('Please input numeric characters only, although you can use +-,;#');  
		document.editBussProfile.bPhone.focus();
		return false;  
	}  
	
	
	if(bussPhone.length<6)
	{
		alert("Business Contact Number could not be so short");
		document.editBussProfile.bPhone.focus();
		return false;
	}

	if(bussName=="")
	{
		alert("Please Enter Business Name");
		document.editBussProfile.bName.focus();
		return false;
	}
	
	if(bussName.length<3)
	{
		alert("Business Name could not be so short");
		document.editBussProfile.bName.focus();
		return false;
	}
	
	if(bussContName=="")
	{
		alert("Please Enter Business Contact Person Name");
		document.editBussProfile.bContName.focus();
		return false;
	}
	
	if(bussContName.length<3)
	{
		alert("Business Contact Person Name could not be so short");
		document.editBussProfile.bContName.focus();
		return false;
	}
	
	if(bussAddress=="")
	{
		alert("Please Enter Business Address");
		document.editBussProfile.bAddress.focus();
		return false;
	}
	
	if(bussAddress.length<10)
	{
		alert("Business Address could not be so short");
		document.editBussProfile.bAddress.focus();
		return false;
	}
	
	if(bussZip=="")
	{
		alert("Please Enter Business Zip Code");
		document.editBussProfile.bZip.focus();
		return false;
	}
	if(isNaN(bussZip))
	{
		alert("Enter valid Numeric Zip Code without +,-,space etc.");
		document.editBussProfile.bZip.focus();
		return false;
	}
	if((bussZip.length < 5) || (bussZip.length > 11))
	{
		alert("Your Zip Code must be between 5 to 10 digits");
		document.editBussProfile.bZip.focus();
		return false;
	}
	if(bussEmail!= null && bussEmail!="")
	{
		if (!ValidateEmail(bussEmail))
		{
			alert("Please provide a valid email address");
			document.editBussProfile.bEmail.select();
			return false;
		}
	}
	if(bussWeb!= null && bussWeb!="")
	{
		if (!validateURL(bussWeb))
		{
			alert("Please provide a valid web url like http://www.findon.biz");
			document.editBussProfile.bWeb.select();
			return false;
		}
	}
	if(bussDesc=="")
	{
		alert("Please Enter Business Description");
		document.editBussProfile.bDesc.focus();
		return false;
	}
	
	if(bussDesc.length<20)
	{
		alert("Business Description could not be so short");
		document.editBussProfile.bDesc.focus();
		return false;
	}
	
}

function ShowHideDiv() {
        var category = document.getElementById("cat");
        var dvCategory = document.getElementById("dvCategory");
        dvCategory.style.display = category.checked ? "block" : "none";

		var zipcode = document.getElementById("zipcode");
        var dvZip = document.getElementById("dvZip");
        dvZip.style.display = zipcode.checked ? "block" : "none";
	
		 var loc = document.getElementById("loc");
        var dvLocation = document.getElementById("dvLocation");
        dvLocation.style.display = loc.checked ? "block" : "none";
		
		 var txt = document.getElementById("txt");
        var dvText = document.getElementById("dvText");
        dvText.style.display = txt.checked ? "block" : "none";
	
    }


function validateSearchForm() {
	
	if(document.getElementById("zipcode").checked)
	{
		var	bussZip=document.search.bZip.value;
		bussZip=bussZip;
		if(bussZip=="")
		{
			alert("Please Enter Business Zip Code");
			document.search.bZip.focus();
			return false;
		}
		if(isNaN(bussZip))
		{
			alert("Enter Valid Numeric Zip Code without +,-,space etc.");
			document.search.bZip.focus();
			return false;
		}
		if((bussZip.length < 5) || (bussZip.length > 11))
		{
			alert("Your Zip Code must be between 5 to 10 digits");
			document.search.bZip.focus();
			return false;
		}

	}

	if(document.getElementById("loc").checked)
	{
		var	bussLat=document.search.bLat.value;
		var	bussLong=document.search.bLong.value;
		bussLat=bussLat;
		bussLong=bussLong;
		if(bussLat=="")
		{
			alert("Please Enter Business Latitude");
			document.search.bLat.focus();
			return false;
		}
		if(isNaN(bussLat))
		{
			alert("Enter Valid Business Latitude");
			document.search.bLat.focus();
			return false;
		}
		
		if(bussLong=="")
		{
			alert("Please Enter Business Longitude");
			document.search.bLong.focus();
			return false;
		}
		if(isNaN(bussLong))
		{
			alert("Enter Valid Business Longitude");
			document.search.bLong.focus();
			return false;
		}

	}


	if(document.getElementById("txt").checked)
	{
		var	bussText=document.search.text.value;
	
		if(bussText=="")
		{
			alert("Please Enter Some Text with atleast 3 characters in length");
			document.search.text.focus();
			return false;
		}
		
		if((bussText.length < 3) || (bussText.length > 21))
		{
			alert("Your Search Text must be between 3 to 20 digits");
			document.search.text.focus();
			return false;
		}

	}
	
    
}



function validateContactForm()
{
	var name = document.contactUs.name.value;
	var contactNum = document.contactUs.contactNum.value;
	var email= document.contactUs.email.value;
	var feedBackText = document.contactUs.feedBackText.value;
	
	var letters = /^[0-9./;#,+-]*$/;  
	if(name=="")
	{
		alert("Please Enter Your Name");
		document.contactUs.name.focus();
		return false;
	}
	
	if(name.length<3)
	{
		alert("Name could not be so short");
		document.contactUs.name.focus();
		return false;
	}

	if(contactNum=="")
	{
		alert("Please Enter Contact Number");
		document.contactUs.contactNum.focus();
		return false;
	}
	if(!contactNum.match(letters))  
	{  
		alert('Please input numeric characters only, although you can use +-,;#');  
		document.contactUs.contactNum.focus();
		return false;  
	}  

	if(contactNum.length<6)
	{
		alert("Contact Number could not be so short");
		document.contactUs.contactNum.focus();
		return false;
	}
	if(email=="")
	{
		alert("Please Enter email address");
		document.contactUs.email.focus();
		return false;
	}
	if(email!= null && email!="")
	{
		if (!ValidateEmail(email))
		{
			alert("Please provide a valid email address");
			document.contactUs.email.select();
			return false;
		}
	}
	
	if(feedBackText=="")
	{
		alert("Please Enter Description");
		document.contactUs.feedBackText.focus();
		return false;
	}
	
	if(feedBackText.length<20)
	{
		alert("Description could not be so short");
		document.contactUs.feedBackText.focus();
		return false;
	}
	
}


function reportpopup(urlToOpen)
{
	var window_width = screen.availWidth/2;
	var window_height = screen.availHeight/2;
	var window_left = (screen.availWidth/2)-(window_width/2);
	var window_top = (screen.availHeight/2)-(window_height/2);
	var winParms = "Status=yes" + ",resizable=yes" + ",height="+window_height+",width="+window_width + ",left="+window_left+",top="+window_top;
	var newwindow = window.open(urlToOpen,'_blank',winParms);
	newwindow.focus()
}


function validateReportForm()
{
	var name = document.report.rName.value;
	var contactNum = document.report.rPhone.value;
	var email= document.report.rMail.value;
	var cmntText = document.report.cmnt.value;
	
	var letters = /^[0-9./;#,+-]*$/;  
	if(name=="")
	{
		alert("Please Enter Your Name");
		document.report.rName.focus();
		return false;
	}
	
	if(name.length<3)
	{
		alert("Name could not be so short");
		document.report.rName.focus();
		return false;
	}

	if(contactNum=="")
	{
		alert("Please Enter Contact Number");
		document.report.rPhone.focus();
		return false;
	}
	if(!contactNum.match(letters))  
	{  
		alert('Please input numeric characters only, although you can use +-,;#');  
		document.report.rPhone.focus();
		return false;  
	}  

	if(contactNum.length<6)
	{
		alert("Contact Number could not be so short");
		document.report.rPhone.focus();
		return false;
	}
	
	if(email!= null && email!="")
	{
		if (!ValidateEmail(email))
		{
			alert("Please provide a valid email address");
			document.report.rMail.select();
			return false;
		}
	}
	
	if(cmntText=="")
	{
		alert("Please Enter Comment");
		document.report.cmnt.focus();
		return false;
	}
	
	if(cmntText.length<20)
	{
		alert("Comment could not be so short");
		document.report.cmnt.focus();
		return false;
	}	
}