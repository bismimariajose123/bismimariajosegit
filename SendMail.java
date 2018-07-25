package com.churchmanagementsystem.SendMail;

import java.util.Properties;
 

import javax.mail.*;
import javax.mail.internet.*;

 

public class SendMail {
	

	public void sendPlainTextEmail(String tBSubject, String tBTo, String tBFrom, String tBBody)  throws AddressException,
    MessagingException{
		
		 // Recipient's email ID needs to be mentioned.
	     // String to = "extern_jose.bismi@allianz.com";

	      // Sender's email ID needs to be mentioned
	    //  String from = "extern_jose.bismi@allianz.com";

	      // Assuming you are sending email from localhost
	      String host = "tmu-econ.mail.allianz";

	      // Get system properties
	      Properties properties = System.getProperties();

	      // Setup mail server
	      properties.setProperty("mail.smtp.host", host);

	      // Get the default Session object.
	      Session session = Session.getDefaultInstance(properties);

	      try {
	         // Create a default MimeMessage object.
	         MimeMessage message = new MimeMessage(session);

	         // Set From: header field of the header.
	         message.setFrom(new InternetAddress(tBFrom));

	         // Set To: header field of the header.
	         message.addRecipient(Message.RecipientType.TO, new InternetAddress(tBTo));

	         // Set Subject: header field
	         message.setSubject(tBSubject);

	         // Now set the actual message
	         //message.setText("This is actual message");#
	         message.setContent(tBBody, "text/html");


	         // Send message
	         Transport.send(message);
	         System.out.println("Sent message successfully....");
	      } catch (MessagingException mex) {
	         mex.printStackTrace();
	      }

		
	}
}
