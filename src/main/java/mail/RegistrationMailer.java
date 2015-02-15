package mail;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
public class RegistrationMailer {
    private static ExecutorService executorService = Executors.newFixedThreadPool(10);
    private static final String SMTP_HOST_NAME = "smtp.mandrillapp.com";
    private static final String SMTP_AUTH_USER = "info@subl.im";
    private static final String SMTP_AUTH_PWD  = "mheJwp4RpGmqR0T_HDVQ-g";

    @SneakyThrows
    public static void send(String emailAddress) {
        executorService.submit(sendMail(emailAddress));
    }

    private static Runnable sendMail(final String emailAddress) throws Exception{
        return new Runnable() {
            @Override
            public void run() {
                try {
                    connectAndSend(emailAddress);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (MessagingException e) {
                    e.printStackTrace();
                }
            }
        };
    }

    private static void connectAndSend(String emailAddress) throws IOException, MessagingException {
        try {
            log.info("Sending registration mail to " + emailAddress);
            Properties props = new Properties();
            props.put("mail.transport.protocol", "smtp");
            props.put("mail.smtp.host", SMTP_HOST_NAME);
            props.put("mail.smtp.auth", "true");
            URL url = Resources.getResource("mail.html");
            String mailHTML = Resources.toString(url, Charsets.UTF_8);

            Authenticator auth = new SMTPAuthenticator();
            Session mailSession = Session.getDefaultInstance(props, auth);
            // uncomment for debugging infos to stdout
            // mailSession.setDebug(true);
            Transport transport = mailSession.getTransport();

            MimeMessage message = new MimeMessage(mailSession);
            message.setContent(mailHTML, "text/html");
            message.setFrom(new InternetAddress("info@subl.im"));
            message.setSubject("Welcome to Sublim!");
            message.addRecipient(Message.RecipientType.TO,
                    new InternetAddress(emailAddress));

            transport.connect();
            transport.sendMessage(message,
                    message.getRecipients(Message.RecipientType.TO));
            transport.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    private static class SMTPAuthenticator extends javax.mail.Authenticator {
        public PasswordAuthentication getPasswordAuthentication() {
            String username = SMTP_AUTH_USER;
            String password = SMTP_AUTH_PWD;
            return new PasswordAuthentication(username, password);
        }
    }
}
