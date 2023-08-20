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
	 
	 public Boolean sendEmail(String to, String subject, String content) {
		 
		 
		 MimeMessage message = javaMailSender.createMimeMessage();
		 
		 try {
			 MimeMessageHelper helper = new MimeMessageHelper(message, true);
			 helper.setFrom(emailfrom);
			 helper.setTo(to);
	         helper.setSubject(subject);
	         helper.setText(content, true);
	         javaMailSender.send(message);
			 javaMailSender.send(message);
	        
	     } catch (Exception e) {
	    	 System.out.println(e.getMessage());
	    	 return false;
	     }
		 return true;
	 }
	 
	 public Boolean sendOTPEmail(String username, String toemail, int[] otp) {
		 
		 
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
		 		+ "            max-width: 600px;\n"
		 		+ "            margin: 0 auto;\n"
		 		+ "            padding: 20px;\n"
		 		+ "            background-color: #e1d9d9;\n"
		 		+ "            border-radius: 5px;\n"
		 		+ "            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);\n"
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
		 		+ "                <p class=\"lead\">Thank you for using BuySellConnect! Here is your one-time password (OTP) to complete your verification:</p>\n"
		 		+ "                \n"
		 		+ "                <p class=\"lead\">OTP Code: " + String.valueOf(otp[0]) + String.valueOf(otp[1]) + String.valueOf(otp[2]) + String.valueOf(otp[3]) + "</p>\n"
		 		+ "                \n"
		 		+ "                <p class=\"lead\">Please use this OTP code to verify your account.</p>\n"
		 		+ "                \n"
		 		+ "                <p class=\"lead pb-2 mb-2\">If you did not request this OTP, please ignore this email. Your account security is important to us.</p>\n"
		 		+ "                \n"
		 		+ "                <p>Thank you,</p>\n"
		 		+ "                <p>The BuySellConnect Team</p>\n"
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
