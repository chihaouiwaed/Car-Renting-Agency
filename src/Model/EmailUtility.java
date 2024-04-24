package Util;

import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;

public class EmailUtility {
    public static void sendEmail(String to, String subject, String content) throws MessagingException {
        String host = "smtp.gmail.com";
        final String username = "waedpsn2@gmail.com";  // Must be replaced with your actual email
        final String password = "ygrd inbb gkmq svkn";  // Must be replaced with your actual password

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("waedpsn2@gmail.com"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject(subject);
            message.setText(content);

            Transport.send(message);
        } catch (MessagingException e) {
            System.err.println("Email could not be sent: " + e.getMessage());
            throw e;  // Re-throw the exception to handle it in the calling method
        }
    }
}
