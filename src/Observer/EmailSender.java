package Observer;


import jakarta.mail.*;
import jakarta.mail.internet.*;
import java.util.Properties;

public class EmailSender {

    public static void enviarCorreo(String destinatario, String asunto, String cuerpo) {
        final String remitente = "bruno.figueroa@usil.pe";
        final String password = "T3csup5629.";

        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.office365.com"); // Servidor de Outlook
        props.put("mail.smtp.port", "587");                // Puerto TLS
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");    // Requiere TLS

        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(remitente, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(remitente));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(destinatario));
            message.setSubject(asunto);
            message.setText(cuerpo);

            Transport.send(message);
            System.out.println("Correo enviado con éxito.");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}