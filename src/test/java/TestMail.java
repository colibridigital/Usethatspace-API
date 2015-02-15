import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import lombok.SneakyThrows;

import javax.mail.*;
import javax.mail.internet.*;
import javax.mail.Authenticator;
import java.net.URL;
import java.util.Properties;

public class TestMail {
    private static final String SMTP_HOST_NAME = "smtp.mandrillapp.com";
    private static final String SMTP_AUTH_USER = "info@subl.im";
    private static final String SMTP_AUTH_PWD  = "mheJwp4RpGmqR0T_HDVQ-g";

    @SneakyThrows
    public static void main(String args[]) {
        test();
    }

    public static void test() throws Exception{
        Properties props = new Properties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.host", SMTP_HOST_NAME);
        props.put("mail.smtp.auth", "true");
        URL url = TestMail.class.getResource("mail.html");
        String mailHTML = Resources.toString(url, Charsets.UTF_8);

        Authenticator auth = new SMTPAuthenticator();
        Session mailSession = Session.getDefaultInstance(props, auth);
        // uncomment for debugging infos to stdout
        // mailSession.setDebug(true);
        Transport transport = mailSession.getTransport();

        MimeMessage message = new MimeMessage(mailSession);
        message.setContent(mailHTML, "text/html");
        message.setFrom(new InternetAddress("info@subl.im"));
        message.addRecipient(Message.RecipientType.TO,
                new InternetAddress("james_cross91@hotmail.com"));

        transport.connect();
        transport.sendMessage(message,
                message.getRecipients(Message.RecipientType.TO));
        transport.close();
    }

    private static class SMTPAuthenticator extends javax.mail.Authenticator {
        public PasswordAuthentication getPasswordAuthentication() {
            String username = SMTP_AUTH_USER;
            String password = SMTP_AUTH_PWD;
            return new PasswordAuthentication(username, password);
        }
    }
}
