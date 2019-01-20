package buisnessLayer;

import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.ejb.Singleton;
import javax.mail.*;
import javax.mail.internet.*;

@Singleton
public class MailSender {

    private static String USER_NAME = "FlyAndDivaCorp@gmail.com";  // GMail user name (just the part before "@gmail.com")
    private static String PASSWORD = "MultHubPassword"; // GMail password
//    private static String RECIPIENT = "divand1999@yandex.ru";
private static String RECIPIENT = "anton99910@outlook.com";

    public void sendMail(String subject, String body, List<String> to){
        String from = USER_NAME;
        String pass = PASSWORD;
//        String[] to = { RECIPIENT }; // list of recipient email addresses


        sendFromGMail(from, pass, to, subject, body);

    }


    private static void sendFromGMail(String from, String pass, List<String> to, String subject, String body) {
        Properties props = System.getProperties();
        String host = "smtp.gmail.com";
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.user", from);
        props.put("mail.smtp.password", pass);
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");

        Session session = Session.getDefaultInstance(props);
        MimeMessage message = new MimeMessage(session);

        try {
            message.setFrom(new InternetAddress(from));
            InternetAddress[] toAddress = new InternetAddress[to.size()];

            // To get the array of addresses
            for( int i = 0; i < to.size(); i++ ) {
//                if(to.get(i) != null
                toAddress[i] = new InternetAddress(to.get(i));
            }

            for( int i = 0; i < toAddress.length; i++) {
                message.addRecipient(Message.RecipientType.TO, toAddress[i]);
            }

            message.setSubject(subject);
            message.setText(body);
////////////////////////////////////////////////////////////////////////
//            BodyPart messageBodyPart = new MimeBodyPart();
//
//            messageBodyPart.setText(body);
//
//            // Create a multipart message for attachment
//            Multipart multipart = new MimeMultipart();
//
//            // Set text message part
//            multipart.addBodyPart(messageBodyPart);
//
//            // Second part is image attachment
//            messageBodyPart = new MimeBodyPart();
//            String filename = "D:\\opThtnuiwbs.jpg";
//            DataSource source = new FileDataSource(filename);
//            messageBodyPart.setDataHandler(new DataHandler(source));
//            messageBodyPart.setFileName(filename);
//            //Trick is to add the content-id header here
//            messageBodyPart.setHeader("Content-ID", "image_id");
//            multipart.addBodyPart(messageBodyPart);
//
//            //third part for displaying image in the email body
//            messageBodyPart = new MimeBodyPart();
//            messageBodyPart.setContent("<h1 style=\"color:red;\">Attached Image</h1>" +
//                    "<img src='cid:image_id'>"+"<h3 style=\"color:red;\">Ti lox sosat</h3>", "text/html");
//            multipart.addBodyPart(messageBodyPart);
//
//            //Set the multipart message to the email message
//            message.setContent(multipart);
////////////////////////////////////////////////////////////////////////
            Transport transport = session.getTransport("smtp");
            transport.connect(host, from, pass);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
        }
        catch (AddressException ae) {
            ae.printStackTrace();
        }
        catch (MessagingException me) {
            me.printStackTrace();
        }
    }

}
