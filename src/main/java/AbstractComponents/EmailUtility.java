package AbstractComponents;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

import java.io.File;
import java.util.Properties;

public class EmailUtility {

    public static void sendEmail(String senderEmail, String senderPassword, String receiverEmail, String reportsDirectory, String subject) {
        // Setup mail server properties
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com"); // for Gmail
        props.put("mail.smtp.port", "587"); // for TLS

        // Get the Session object
        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(senderEmail, senderPassword);
            }
        });

        try {
            // Create a default MimeMessage object
            MimeMessage message = new MimeMessage(session);

            // Set From: header field of the header
            message.setFrom(new InternetAddress(senderEmail));

            // Set To: header field of the header
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(receiverEmail));

            // Set Subject: header field
            message.setSubject(subject);

            // Create a multipart message
            Multipart multipart = new MimeMultipart();

            // Text content
            BodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setText("The automated test suite has completed successfully.");
            multipart.addBodyPart(messageBodyPart);

            // Find the most recent HTML report file in the directory
            File mostRecentFile = getMostRecentHTMLReport(reportsDirectory);
            if (mostRecentFile != null) {
                // Attachment part
                messageBodyPart = new MimeBodyPart();
                DataSource source = new FileDataSource(mostRecentFile);
                messageBodyPart.setDataHandler(new DataHandler(source));
                messageBodyPart.setFileName(mostRecentFile.getName());
                multipart.addBodyPart(messageBodyPart);
            } else {
                System.out.println("No HTML report files found in the specified directory.");
                return; // Exit the method if no files are found
            }

            // Set the multipart message as the email content
            message.setContent(multipart);

            // Send message
            Transport.send(message);

            System.out.println("Email sent successfully.");

        } catch (MessagingException e) {
            e.printStackTrace();
            System.out.println("Failed to send email: " + e.getMessage());
        }
    }

    // Method to get the most recent HTML report file from the directory
    private static File getMostRecentHTMLReport(String directoryPath) {
        File folder = new File(directoryPath);
        File[] files = folder.listFiles((dir, name) -> name.toLowerCase().endsWith(".html"));

        if (files == null || files.length == 0) {
            return null;
        }

        File mostRecentFile = files[0];
        long lastModifiedTime = mostRecentFile.lastModified();

        for (File file : files) {
            if (file.lastModified() > lastModifiedTime) {
                mostRecentFile = file;
                lastModifiedTime = file.lastModified();
            }
        }

        return mostRecentFile;
    }
}
