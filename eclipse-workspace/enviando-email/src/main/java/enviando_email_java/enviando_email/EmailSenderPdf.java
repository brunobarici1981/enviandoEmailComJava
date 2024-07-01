package enviando_email_java.enviando_email;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*; // Necessário para tratar os anexos

public class EmailSenderPdf {

	public static void sendEmail(String recipient, String subject, String content, String pdfFilePath) {
		// Configurações do servidor SMTP do Gmail
		String host = "smtp.gmail.com";
		final String username = "littleleg2003@gmail.com"; // Seu e-mail
		final String password = "v d i t t r n q f t j m f o y d"; // Sua senha (ou senha de app se estiver usando
																	// autenticação em duas etapas)

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

			// Criação de uma parte para o texto
			MimeBodyPart textBodyPart = new MimeBodyPart();
			textBodyPart.setText(content);

			// Criação de uma parte para o anexo PDF
			MimeBodyPart attachmentBodyPart = new MimeBodyPart();
			DataSource source = new FileDataSource(pdfFilePath);
			attachmentBodyPart.setDataHandler(new DataHandler(source));
			attachmentBodyPart.setFileName(source.getName());

			// Composição das partes em uma mensagem
			Multipart multipart = new MimeMultipart();
			multipart.addBodyPart(textBodyPart); // Adiciona o texto
			multipart.addBodyPart(attachmentBodyPart); // Adiciona o anexo

			// Configuração do conteúdo da mensagem
			message.setContent(multipart);

			// Envio da mensagem
			Transport.send(message);

			System.out.println("Email enviado com sucesso com o anexo!");

		} catch (MessagingException e) {
			e.printStackTrace();
			System.out.println("Erro ao enviar email: " + e.getMessage());
		}
	}

	public static void main(String[] args) {
		// Detalhes do e-mail
		try {
			String recipient = "brbaricitrin@gmail.com"; // E-mail destinatário
			String subject = "Assunto do E-mail de Teste com Anexo";
			String content = "Este é o conteúdo do e-mail de teste. Veja o anexo em PDF.";
			String pdfFilePath = "C:\\Users\\Bruno\\OneDrive\\Documents\\Curso-java-spring-pago\\12-projetos-java-web\\12 projetos java web\\4 - Ebook de configuração de ambiente Java.pdf"; // Caminho
																																																	// para
																																																	// o
																																																	// arquivo
																																																	// PDF

			// Enviando o e-mail com anexo
			sendEmail(recipient, subject, content, pdfFilePath);
		} catch (Exception e) {
			e.getStackTrace();
		}
	}
}
