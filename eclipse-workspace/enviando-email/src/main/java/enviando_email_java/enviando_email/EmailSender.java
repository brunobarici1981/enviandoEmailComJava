package enviando_email_java.enviando_email;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

public class EmailSender {

    public static void sendEmail(String recipient, String subject, String content) {
        // Configurações do servidor SMTP do Gmail
        String host = "smtp.gmail.com";
        final String username = "littleleg2003@gmail.com"; // Seu e-mail
        final String password = "v d i t t r n q f t j m f o y d"; // Sua senha (ou senha de app se estiver usando autenticação em duas etapas)

        // Configurações de propriedades do JavaMail
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", "587");

        // Criação da sessão com autenticação
        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            // Criação da mensagem
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipient));
            message.setSubject(subject);
            message.setText(content);

            // Envio da mensagem
            Transport.send(message);

            System.out.println("Email enviado com sucesso!");

        } catch (MessagingException e) {
            e.printStackTrace();
            System.out.println("Erro ao enviar email: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        // Detalhes do e-mail
        String recipient = "brbaricitrin@gmail.com"; // E-mail destinatário
        String subject = "Assunto do E-mail de Teste";
        String content = "Este é o conteúdo do e-mail de teste.";

        // Enviando o e-mail
        sendEmail(recipient, subject, content);
    }
}
