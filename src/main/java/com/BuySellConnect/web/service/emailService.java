package com.BuySellConnect.web.service;

import javax.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class emailService {
	
	 @Autowired
	 private JavaMailSender javaMailSender;
	 
	 @Value("${emailFrom}")
	 private String emailfrom;
	 
	 //send email
	 public Boolean sendEmail(String to, String subject, String content) {
		 
		 
		 MimeMessage message = javaMailSender.createMimeMessage();
		 
		 try {
			 MimeMessageHelper helper = new MimeMessageHelper(message, true);
			 helper.setFrom(emailfrom);
			 helper.setTo(to);
	         helper.setSubject(subject);
	         helper.setText(content, true);
	         javaMailSender.send(message);
	        
	     } catch (Exception e) {
	    	 System.out.println(e.getMessage());
	    	 return false;
	     }
		 return true;
	 }
	 
	 //send signup otp email
	 public Boolean sendSignupOTPEmail(String username, String toemail, int[] otp) {
		 
		 
		 String subject = "Your One-Time Password for BuySellConnect";
		 
		 String content = "<!doctype html>\n"
		 		+ "<html lang=\"en\">\n"
		 		+ "  <head>\n"
		 		+ "    <meta charset=\"utf-8\">\n"
		 		+ "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n"
		 		+ "    \n"
		 		+ "    <!--bootstrap css-->\n"
		 		+ "    <link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css\" rel=\"stylesheet\" \n"
		 		+ "    integrity=\"sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9\" crossorigin=\"anonymous\">\n"
		 		+ "    \n"
		 		+ "    <style>\n"
		 		+ "        body {\n"
		 		+ "            font-family: Arial, sans-serif;\n"
		 		+ "            background-color: #cdc6c6;\n"
		 		+ "        }\n"
		 		+ "        .container {\n"
		 		+ "            max-width: 700px;\n"
		 		+ "            margin: 0 auto;\n"
		 		+ "            padding: 1rem;\n"
		 		+ "            background-color: #e1d9d9;\n"
		 		+ "            border-radius: 1rem;\n"
		 		+ "            box-shadow: 0 0.5rem 1rem rgba(0, 0, 0, 0.1);\n"
		 		+ "        }\n"
		 		+ "        h3{\n"
		 		+ "            color: blue;\n"
		 		+ "        }\n"
		 		+ "    </style>\n"
		 		+ "    \n"
		 		+ "  </head>\n"
		 		+ "  <body>\n"
		 		+ "    <main>\n"
		 		+ "        <div class=\"px-4 py-5 my-5 container\">\n"
		 		+ "            \n"
		 		+ "            <div class=\"col-lg-8 mx-auto\">\n"
		 		+ "\n"
		 		+ "                <h3 class=\"display-6\">Welcome to BuySellConnect</h3>\n"
		 		+ "                <p class=\"lead mb-4 pt-2\">Hello "+ username + ",</p>\n"
		 		+ "\n"
		 		+ "                <p class=\"lead\">Here is your one-time password (OTP) to complete your signup verification:</p>\n"
		 		+ "                \n"
		 		+ "                <p class=\"fw-semibold\">OTP Code: " + String.valueOf(otp[0]) + String.valueOf(otp[1]) + String.valueOf(otp[2]) + String.valueOf(otp[3]) + "</p>\n"
		 		+ "                \n"
		 		+ "                <p class=\"lead\">Please use this OTP code to verify your account.</p>\n"
		 		+ "                \n"
		 		+ "                <p class=\"lead pb-2 mb-2\">If you did not request this OTP, please ignore this email. Your account security is important to us.</p>\n"
		 		+ "                \n"
		 		+ "                <p>Best regards,<br>The BuySellConnect Team</p>\n"
		 		+ "                \n"
		 		+ "              <div class=\"d-grid gap-2 d-sm-flex justify-content-sm-center\">\n"
		 		+ "              </div>\n"
		 		+ "            </div>\n"
		 		+ "        </div>\n"
		 		+ "    </main>\n"
		 		+ "\n"
		 		+ "    <!--bootstrap js-->\n"
		 		+ "    <script src=\"https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js\" \n"
		 		+ "    integrity=\"sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm\" crossorigin=\"anonymous\"></script>\n"
		 		+ "\n"
		 		+ "  </body>\n"
		 		+ "</html>";
		 
		 return sendEmail(toemail,subject,content);
	 }
	 
	 //send forgot password otp email
	 public Boolean sendForgotPasswordOTPEmail(String username, String toemail, int[] otp) {
		 
		 
		 String subject = "Your One-Time Password for BuySellConnect";
		 
		 String content = "<!doctype html>\n"
		 		+ "<html lang=\"en\">\n"
		 		+ "  <head>\n"
		 		+ "    <meta charset=\"utf-8\">\n"
		 		+ "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n"
		 		+ "    \n"
		 		+ "    <!--bootstrap css-->\n"
		 		+ "    <link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css\" rel=\"stylesheet\" \n"
		 		+ "    integrity=\"sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9\" crossorigin=\"anonymous\">\n"
		 		+ "    \n"
		 		+ "    <style>\n"
		 		+ "        body {\n"
		 		+ "            font-family: Arial, sans-serif;\n"
		 		+ "            background-color: #cdc6c6;\n"
		 		+ "        }\n"
		 		+ "        .container {\n"
		 		+ "            max-width: 700px;\n"
		 		+ "            margin: 0 auto;\n"
		 		+ "            padding: 0.5rem;\n"
		 		+ "            background-color: #e1d9d9;\n"
		 		+ "            border-radius: 1rem;\n"
		 		+ "            box-shadow: 0 0.5rem 1rem rgba(0, 0, 0, 0.1);\n"
		 		+ "        }\n"
		 		+ "        h3{\n"
		 		+ "            color: blue;\n"
		 		+ "        }\n"
		 		+ "    </style>\n"
		 		+ "    \n"
		 		+ "  </head>\n"
		 		+ "  <body>\n"
		 		+ "    <main>\n"
		 		+ "        <div class=\"px-4 py-5 my-5 container\">\n"
		 		+ "            \n"
		 		+ "            <div class=\"col-lg-8 mx-auto\">\n"
		 		+ "\n"
		 		+ "                <h3 class=\"display-6\">Greetings from BuySellConnect</h3>\n"
		 		+ "                <p class=\"lead mb-4 pt-2\">Dear " + username + ",</p>\n"
		 		+ "\n"
		 		+ "                <p class=\"lead\">We have received a request to reset your password on BuySellConnect.</p>\n"
		 		+ "                \n"
		 		+ "                <p class=\"fw-semibold\">Your One-Time Password (OTP) is: " + String.valueOf(otp[0]) + String.valueOf(otp[1]) + String.valueOf(otp[2]) + String.valueOf(otp[3]) + "</p>\n"
		 		+ "                \n"
		 		+ "                <p class=\"lead\">Please use this OTP to verify your identity and complete the password reset process.</p>\n"
		 		+ "                \n"
		 		+ "                <p class=\"lead\">If you didn't request this password reset, you can ignore this email.</p>\n"
		 		+ "                \n"
		 		+ "                <p class=\"lead pb-2 mb-2\">Thank you for using BuySellConnect.</p>\n"
		 		+ "\n"
		 		+ "                <p>Best regards,<br>The BuySellConnect Team</p>\n"
		 		+ "                \n"
		 		+ "              <div class=\"d-grid gap-2 d-sm-flex justify-content-sm-center\">\n"
		 		+ "              </div>\n"
		 		+ "            </div>\n"
		 		+ "        </div>\n"
		 		+ "    </main>\n"
		 		+ "\n"
		 		+ "    <!--bootstrap js-->\n"
		 		+ "    <script src=\"https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js\" \n"
		 		+ "    integrity=\"sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm\" crossorigin=\"anonymous\"></script>\n"
		 		+ "\n"
		 		+ "  </body>\n"
		 		+ "</html>";
		 
		 return sendEmail(toemail,subject,content);
	 }
	 
	 //send new password email
	 public Boolean sendNewPasswordEmail(String username, String toemail, String newpassword) {
		 
		 String subject = "Your New Password for BuySellConnect Account";
		 
		 String content = "<!doctype html>\n"
		 		+ "<html lang=\"en\">\n"
		 		+ "  <head>\n"
		 		+ "    <meta charset=\"utf-8\">\n"
		 		+ "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n"
		 		+ "    \n"
		 		+ "    <!--bootstrap css-->\n"
		 		+ "    <link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css\" rel=\"stylesheet\" \n"
		 		+ "    integrity=\"sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9\" crossorigin=\"anonymous\">\n"
		 		+ "    \n"
		 		+ "    <style>\n"
		 		+ "        body {\n"
		 		+ "            font-family: Arial, sans-serif;\n"
		 		+ "            background-color: #cdc6c6;\n"
		 		+ "        }\n"
		 		+ "        .container {\n"
		 		+ "            max-width: 700px;\n"
		 		+ "            margin: 0 auto;\n"
		 		+ "            padding: 0.5rem;\n"
		 		+ "            background-color: #e1d9d9;\n"
		 		+ "            border-radius: 1rem;\n"
		 		+ "            box-shadow: 0 0.5rem 1rem rgba(0, 0, 0, 0.1);\n"
		 		+ "        }\n"
		 		+ "        h3{\n"
		 		+ "            color: blue;\n"
		 		+ "        }\n"
		 		+ "    </style>\n"
		 		+ "    \n"
		 		+ "  </head>\n"
		 		+ "  <body>\n"
		 		+ "    <main>\n"
		 		+ "        <div class=\"px-4 py-5 my-5 container\">\n"
		 		+ "            \n"
		 		+ "            <div class=\"col-lg-8 mx-auto\">\n"
		 		+ "\n"
		 		+ "                <h3 class=\"display-6\">Greetings from BuySellConnect</h3>\n"
		 		+ "                <p class=\"lead mb-4 pt-2\">Dear " + username + ",</p>\n"
		 		+ "\n"
		 		+ "                <p class=\"lead\">We've received a request to reset your BuySellConnect account password. As requested, we've generated a new password for you:</p>\n"
		 		+ "                \n"
		 		+ "                <p class=\"fw-semibold\">Username: " + username + "<br>New Password: " + newpassword + "</p>\n"
		 		+ "                \n"
		 		+ "                <p class=\"lead\">Please use this new password to log in to your BuySellConnect account. Once you log in, we recommend \n"
		 		+ "                    changing your password to something more memorable.</p>\n"
		 		+ "                \n"
		 		+ "                <p class=\"lead\">If you did not request a password reset, \n"
		 		+ "                    please contact our team immediately at this email.</p>\n"
		 		+ "                \n"
		 		+ "                <p class=\"lead pb-2 mb-2\">Thank you for using BuySellConnect.</p>\n"
		 		+ "\n"
		 		+ "                <p>Best regards,<br>The BuySellConnect Team</p>\n"
		 		+ "                \n"
		 		+ "              <div class=\"d-grid gap-2 d-sm-flex justify-content-sm-center\">\n"
		 		+ "              </div>\n"
		 		+ "            </div>\n"
		 		+ "        </div>\n"
		 		+ "    </main>\n"
		 		+ "\n"
		 		+ "    <!--bootstrap js-->\n"
		 		+ "    <script src=\"https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js\" \n"
		 		+ "    integrity=\"sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm\" crossorigin=\"anonymous\"></script>\n"
		 		+ "\n"
		 		+ "  </body>\n"
		 		+ "</html>";
		 
		 return sendEmail(toemail,subject,content);
	 }
	 
	 //send Signup successful mail
	 public Boolean sendAccountCreationEmail(String username, String toemail) {
			 
		 String subject = "Your Account Has Been Created!";
			 
		 String content = "<!doctype html>\n"
		 		+ "<html lang=\"en\">\n"
		 		+ "  <head>\n"
		 		+ "    <meta charset=\"utf-8\">\n"
		 		+ "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n"
		 		+ "    \n"
		 		+ "    <!--bootstrap css-->\n"
		 		+ "    <link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css\" rel=\"stylesheet\" \n"
		 		+ "    integrity=\"sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9\" crossorigin=\"anonymous\">\n"
		 		+ "    \n"
		 		+ "    <style>\n"
		 		+ "        body {\n"
		 		+ "            font-family: Arial, sans-serif;\n"
		 		+ "            background-color: #cdc6c6;\n"
		 		+ "        }\n"
		 		+ "        .container {\n"
		 		+ "            max-width: 700px;\n"
		 		+ "            margin: 0 auto;\n"
		 		+ "            padding: 0.5rem;\n"
		 		+ "            background-color: #e1d9d9;\n"
		 		+ "            border-radius: 1rem;\n"
		 		+ "            box-shadow: 0 0.5rem 1rem rgba(0, 0, 0, 0.1);\n"
		 		+ "        }\n"
		 		+ "        h3{\n"
		 		+ "            color: blue;\n"
		 		+ "        }\n"
		 		+ "    </style>\n"
		 		+ "    \n"
		 		+ "  </head>\n"
		 		+ "  <body>\n"
		 		+ "    <main>\n"
		 		+ "        <div class=\"px-4 py-5 my-5 container\">\n"
		 		+ "            \n"
		 		+ "            <div class=\"col-lg-8 mx-auto\">\n"
		 		+ "\n"
		 		+ "                <h3 class=\"display-6\">Greetings from BuySellConnect</h3>\n"
		 		+ "                <p class=\"lead mb-4 pt-2\">Dear " + username + ",</p>\n"
		 		+ "\n"
		 		+ "                <p class=\"lead\">Congratulations! Your account has been successfully created on BuySellConnect.</p>\n"
		 		+ "                \n"
		 		+ "                <p class=\"lead\">You can now explore our platform, connect with others, and start buying and selling.</p>\n"
		 		+ "                \n"
		 		+ "                <p class=\"lead\">If you have any questions or need assistance, our support team is here to help.</p>\n"
		 		+ "                \n"
		 		+ "                <p class=\"lead pb-2 mb-2\">Thank you for joining the BuySellConnect community!</p>\n"
		 		+ "\n"
		 		+ "                <p>Best regards,<br>The BuySellConnect Team</p>\n"
		 		+ "                \n"
		 		+ "              <div class=\"d-grid gap-2 d-sm-flex justify-content-sm-center\">\n"
		 		+ "              </div>\n"
		 		+ "            </div>\n"
		 		+ "        </div>\n"
		 		+ "    </main>\n"
		 		+ "\n"
		 		+ "    <!--bootstrap js-->\n"
		 		+ "    <script src=\"https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js\" \n"
		 		+ "    integrity=\"sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm\" crossorigin=\"anonymous\"></script>\n"
		 		+ "\n"
		 		+ "  </body>\n"
		 		+ "</html>";
			 
		 return sendEmail(toemail,subject,content);
	 }
	 
	// password change email
	public Boolean sendPasswordChangeEmail(String username, String toemail) {
				 
		String subject = "Your BuySellConnect Password Change Was Successful";
				 
		String content = "<!doctype html>\n"
			 		+ "<html lang=\"en\">\n"
			 		+ "  <head>\n"
			 		+ "    <meta charset=\"utf-8\">\n"
			 		+ "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n"
			 		+ "    \n"
			 		+ "    <!--bootstrap css-->\n"
			 		+ "    <link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css\" rel=\"stylesheet\" \n"
			 		+ "    integrity=\"sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9\" crossorigin=\"anonymous\">\n"
			 		+ "    \n"
			 		+ "    <style>\n"
			 		+ "        body {\n"
			 		+ "            font-family: Arial, sans-serif;\n"
			 		+ "            background-color: #cdc6c6;\n"
			 		+ "        }\n"
			 		+ "        .container {\n"
			 		+ "            max-width: 700px;\n"
			 		+ "            margin: 0 auto;\n"
			 		+ "            padding: 0.5rem;\n"
			 		+ "            background-color: #e1d9d9;\n"
			 		+ "            border-radius: 1rem;\n"
			 		+ "            box-shadow: 0 0.5rem 1rem rgba(0, 0, 0, 0.1);\n"
			 		+ "        }\n"
			 		+ "        h3{\n"
			 		+ "            color: blue;\n"
			 		+ "        }\n"
			 		+ "    </style>\n"
			 		+ "    \n"
			 		+ "  </head>\n"
			 		+ "  <body>\n"
			 		+ "    <main>\n"
			 		+ "        <div class=\"px-4 py-5 my-5 container\">\n"
			 		+ "            \n"
			 		+ "            <div class=\"col-lg-8 mx-auto\">\n"
			 		+ "\n"
			 		+ "                <h3 class=\"display-6\">Greetings from BuySellConnect</h3>\n"
			 		+ "                <p class=\"lead mb-4 pt-2\">Dear " + username + ",</p>\n"
			 		+ "\n"
			 		+ "                <p class=\"lead\">We are writing to inform you that your password has been successfully changed on BuySellConnect.</p>\n"
			 		+ "                \n"
			 		+ "                <p class=\"lead\">If you have made this change, you can disregard this email. If you did not initiate this action, please contact our support team immediately.</p>\n"
			 		+ "                \n"
			 		+ "                <p class=\"lead pb-2 mb-2\">Thank you for using BuySellConnect!</p>\n"
			 		+ "\n"
			 		+ "                <p>Best regards,<br>The BuySellConnect Team</p>\n"
			 		+ "                \n"
			 		+ "              <div class=\"d-grid gap-2 d-sm-flex justify-content-sm-center\">\n"
			 		+ "              </div>\n"
			 		+ "            </div>\n"
			 		+ "        </div>\n"
			 		+ "    </main>\n"
			 		+ "\n"
			 		+ "    <!--bootstrap js-->\n"
			 		+ "    <script src=\"https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js\" \n"
			 		+ "    integrity=\"sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm\" crossorigin=\"anonymous\"></script>\n"
			 		+ "\n"
			 		+ "  </body>\n"
			 		+ "</html>";
				 
		return sendEmail(toemail,subject,content);
	}
	
	// send account deleted
	public Boolean sendAccountDeletedEmail(String username, String toemail) {
					 
		String subject = "Account Deletion Confirmation";
					 
		String content = "<!doctype html>\n"
				+ "<html lang=\"en\">\n"
				+ "  <head>\n"
				+ "    <meta charset=\"utf-8\">\n"
				+ "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n"
				+ "    \n"
				+ "    <!--bootstrap css-->\n"
				+ "    <link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css\" rel=\"stylesheet\" \n"
				+ "    integrity=\"sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9\" crossorigin=\"anonymous\">\n"
				+ "    \n"
				+ "    <style>\n"
				+ "        body {\n"
				+ "            font-family: Arial, sans-serif;\n"
				+ "            background-color: #cdc6c6;\n"
				+ "        }\n"
				+ "        .container {\n"
				+ "            max-width: 700px;\n"
				+ "            margin: 0 auto;\n"
				+ "            padding: 0.5rem;\n"
				+ "            background-color: #e1d9d9;\n"
				+ "            border-radius: 1rem;\n"
				+ "            box-shadow: 0 0.5rem 1rem rgba(0, 0, 0, 0.1);\n"
				+ "        }\n"
				+ "        h3{\n"
				+ "            color: blue;\n"
				+ "        }\n"
				+ "    </style>\n"
				+ "    \n"
				+ "  </head>\n"
				+ "  <body>\n"
				+ "    <main>\n"
				+ "        <div class=\"px-4 py-5 my-5 container\">\n"
				+ "            \n"
				+ "            <div class=\"col-lg-8 mx-auto\">\n"
				+ "\n"
				+ "                <h3 class=\"display-6\">Greetings from BuySellConnect</h3>\n"
				+ "                <p class=\"lead mb-4 pt-2\">Dear " + username + ",</p>\n"
				+ "\n"
				+ "                <p class=\"lead\">We wanted to inform you that your request for account deletion has been received and is being processed. Your account and associated data will be permanently deleted.</p>\n"
				+ "                \n"
				+ "                <p class=\"lead\">If you did not initiate this action, please contact our support team immediately.</p>\n"
				+ "                \n"
				+ "                <p class=\"lead pb-2 mb-2\">Thank you for being a part of BuySellConnect.</p>\n"
				+ "\n"
				+ "                <p>Best regards,<br>The BuySellConnect Team</p>\n"
				+ "                \n"
				+ "              <div class=\"d-grid gap-2 d-sm-flex justify-content-sm-center\">\n"
				+ "              </div>\n"
				+ "            </div>\n"
				+ "        </div>\n"
				+ "    </main>\n"
				+ "\n"
				+ "    <!--bootstrap js-->\n"
				+ "    <script src=\"https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js\" \n"
				+ "    integrity=\"sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm\" crossorigin=\"anonymous\"></script>\n"
				+ "\n"
				+ "  </body>\n"
				+ "</html>";
					 
		return sendEmail(toemail,subject,content);
	}

}
