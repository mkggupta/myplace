/**
 * 
 */
package com.myplace.common.util;

import java.io.StringWriter;
import java.util.Properties;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.myplace.service.mail.exception.MailServiceFailedException;



/**
 * @author Manish
 * 
 */
public class EmailUtil {

	static Logger logger = LoggerFactory.getLogger(EmailUtil.class);

	private static String getEmailVerificationText(String firstName, String lastName, long emailVerificationId, String emailAddress, String verificationCode) {
		String verificationUrl = MyPlaceUtil.getServerBaseUrl() + "usrauth/verifyemail/" + emailVerificationId + "/" + emailAddress + "/" + verificationCode;
		Properties props = new Properties();
		props.put("resource.loader", "class");
		props.put("class.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
		VelocityEngine ve = new VelocityEngine();
		try {
			ve.init(props);
			Template t = ve.getTemplate("templates/verificationEmail.vm");
			VelocityContext context = new VelocityContext();
			context.put("fname", firstName);
			if(lastName != null && lastName.length()>0){
			context.put("lname", lastName);
			}else{
				context.put("lname", "");
			}
			context.put("email", emailAddress);
			context.put("verificationUrl", verificationUrl);
			StringWriter writer = new StringWriter();
			t.merge(context, writer);
			return writer.toString();
		} catch (Exception e) {
			logger.error("getEmailVerificationText : Exception in fetching mail template " + e.getMessage(), e);
			if(lastName == null){
				lastName="";
			}
			return "Dear "
					+ firstName
					+ " "
					+ lastName
					+ ",\n \n\n You've entered "
					+ emailAddress
					+ " as the contact email address for your Myplace userID. To complete the process, we just need to verify that this email address belongs to you. Simply click the link below to verify your registration.\n "
					+ verificationUrl
					+ " \n\n\n\n  Wondering why you got this email? \n \n It's sent when someone registers with Myplace. \n \n If you didn't do this, don't worry. Your email address cannot be used as a contact address for anywhere without your verification.\n\n Thanks,\n\n Myplace Customer Support";

		}

	}

	private static String getResetPasswordEmailText(String firstName, String lastName, long forgetPasswordId, long userId, String emailAddress,
			String verificationCode) {

		String resetPasswordUrl = MyPlaceUtil.getApplicationUrl() + "/pages/changePassword.jsp?forgetPasswordId=" + forgetPasswordId + "&userId=" + userId
				+ "&usrName=" + emailAddress + "&verificationCode=" + verificationCode;
		Properties props = new Properties();
		props.put("resource.loader", "class");
		props.put("class.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
		VelocityEngine ve = new VelocityEngine();
		try {
			ve.init(props);
			Template t = ve.getTemplate("templates/forgetPasswordEmail.vm");
			VelocityContext context = new VelocityContext();
			context.put("fname", firstName);
			if(lastName != null && lastName.length()>0){
			context.put("lname", lastName);
			}else{
				context.put("lname", "");
			}
			context.put("email", emailAddress);
			context.put("resetPasswordUrl", resetPasswordUrl);
			StringWriter writer = new StringWriter();
			t.merge(context, writer);
			return writer.toString();

		} catch (Exception e) {
			logger.error("getResetPasswordEmailText : Exception in fetching mail template " + e.getMessage(), e);
			return "\n Forgot your password, " + firstName + " " + lastName
					+ ",\n We received a request to reset the password for your Myplace account  " + emailAddress
					+ "\n To reset your password, click on the link below (or copy and paste the URL into your browser): \n " + resetPasswordUrl
					+ " \n\n Thanks, \n \n Myplace Customer Support";

		}

	}

	public static void sendEmailVerificationEmail(String firstName, String lastName, long emailVerificationId, String emailAddress, String verificationCode)
			throws MailServiceFailedException {

		String emailText = getEmailVerificationText(firstName, lastName, emailVerificationId, emailAddress, verificationCode);
		MyPlaceProperties MyPlaceProperties = com.myplace.common.util.MyPlaceProperties.getInstance();
		SendEmail.sendEmail(emailAddress, MyPlaceProperties.getProperty(MyPlacePropertyKeys.EMAIL_SENDER),
				MyPlaceProperties.getProperty(MyPlacePropertyKeys.EMAIL_VERIFICATION_SUBJECT), emailText,
				MyPlaceProperties.getProperty(MyPlacePropertyKeys.SMTP_SERVER), MyPlaceProperties.getProperty(MyPlacePropertyKeys.SMTP_SERVER_PORT),
				MyPlaceProperties.getProperty(MyPlacePropertyKeys.SMTP_USER),MyPlaceProperties.getProperty(MyPlacePropertyKeys.SMTP_PASSWORD));
	}

	public static void sendResetPasswordEmail(String firstName, String lastName, long forgetPasswordId, long userId, String emailAddress,
			String verificationCode) throws MailServiceFailedException {

		String emailText = getResetPasswordEmailText(firstName, lastName, forgetPasswordId, userId, emailAddress, verificationCode);

		MyPlaceProperties MyPlaceProperties = com.myplace.common.util.MyPlaceProperties.getInstance();

		SendEmail.sendEmail(emailAddress, MyPlaceProperties.getProperty(MyPlacePropertyKeys.EMAIL_SENDER),
				MyPlaceProperties.getProperty(MyPlacePropertyKeys.EMAIL_CHANGE_PASSWORD_SUBJECT), emailText,
				MyPlaceProperties.getProperty(MyPlacePropertyKeys.SMTP_SERVER),  
				MyPlaceProperties.getProperty(MyPlacePropertyKeys.SMTP_SERVER_PORT),
				MyPlaceProperties.getProperty(MyPlacePropertyKeys.SMTP_USER),
				MyPlaceProperties.getProperty(MyPlacePropertyKeys.SMTP_PASSWORD));
	}
}
