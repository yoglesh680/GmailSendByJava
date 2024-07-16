package email;

import java.io.File;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class App {
	public static void main(String[] args) {

		System.out.println("preparing to send message ...");
		String message = "<!DOCTYPE html>\n" + "<html>\n" + "<body>\n" + "\n" + "<p>Hi,</p>\n" + "\n"
				+ "<p>I hope this email finds you well.</p>\n" + "\n"
				+ "<p>I am writing to apply for the Java Developer position at Orange Business Services. With 1.5 years of experience in software development, specializing in Java and the Spring framework, I am confident in my ability to contribute effectively to your team.</p>\n"
				+ "\n" + "<p>Please find my CV attached for your review.</p>\n" + "\n" + "<h3>Profile Summary:</h3>\n"
				+ "\n" + "<table border=\"1\" cellpadding=\"10\" cellspacing=\"0\">\n" + "  <tr>\n"
				+ "    <th>Detail</th>\n" + "    <th>Description</th>\n" + "  </tr>\n" + "  <tr>\n"
				+ "    <td>Total Experience</td>\n" + "    <td>1.5 years</td>\n" + "  </tr>\n" + "  <tr>\n"
				+ "    <td>Current CTC</td>\n" + "    <td>4 LPA</td>\n" + "  </tr>\n" + "  <tr>\n"
				+ "    <td>Expected CTC</td>\n" + "    <td>6 LPA</td>\n" + "  </tr>\n" + "  <tr>\n"
				+ "    <td>Notice Period</td>\n" + "    <td>Immediate</td>\n" + "  </tr>\n" + "  <tr>\n"
				+ "    <td>Current Location</td>\n" + "    <td>Delhi NCR</td>\n" + "  </tr>\n" + "  <tr>\n"
				+ "    <td>Preferred Location</td>\n" + "    <td>Open for Pan India</td>\n" + "  </tr>\n" + "</table>\n"
				+ "\n" + "<h3>Technical Expertise:</h3>\n" + "\n"
				+ "<table border=\"1\" cellpadding=\"10\" cellspacing=\"0\">\n" + "  <tr>\n" + "    <th>Area</th>\n"
				+ "    <th>Technologies</th>\n" + "  </tr>\n" + "  <tr>\n" + "    <td>Frameworks</td>\n"
				+ "    <td>Spring Boot, Spring Data, Spring Rest</td>\n" + "  </tr>\n" + "  <tr>\n"
				+ "    <td>ORM Framework</td>\n" + "    <td>Hibernate</td>\n" + "  </tr>\n" + "  <tr>\n"
				+ "    <td>Messaging Queues</td>\n" + "    <td>RabbitMQ, Kafka</td>\n" + "  </tr>\n" + "  <tr>\n"
				+ "    <td>Databases</td>\n" + "    <td>MySQL, PostgreSQL, Oracle DB, MongoDB</td>\n" + "  </tr>\n"
				+ "</table>\n" + "\n"
				+ "<p>I am a former employee of Orange Business Service, where I honed my skills in various aspects of Java development. I am confident that my expertise and passion for technology will be an asset to your organization.</p>\n"
				+ "\n" + "<p>Best regards,</p>\n" + "\n" + "<p>Yoglesh Kumari<br>\n" + "(+91 8595758339)<br>\n"
				+ "yoglesh1205@gmail.com</p>\n" + "\n" + "</body>\n" + "</html>";

		String subject = "Application for Java Developer Position - Immediate Joiner";
		String to = "to mail id";
		String from = "from mail id";

		sendEmail(message, subject, to, from);
		sendAttach(message, subject, to, from);
	}

	// this is responsible to send the message with attachment
	private static void sendAttach(String message, String subject, String to, String from) {

		// Variable for gmail
		String host = "smtp.gmail.com";

		// get the system properties
		Properties properties = System.getProperties();
		System.out.println("PROPERTIES " + properties);

		// setting important information to properties object

		// host set
		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.port", "465");
		properties.put("mail.smtp.ssl.enable", "true");
		properties.put("mail.smtp.auth", "true");

		// Step 1: to get the session object..
		Session session = Session.getInstance(properties, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("provide your gmail", "provide your password");
			}

		});

		session.setDebug(true);

		// Step 2 : compose the message [text,multi media]
		MimeMessage m = new MimeMessage(session);

		try {

			// from email
			m.setFrom(new InternetAddress(from, "your address"));
			m.setContent(message, "text/html");

			// adding recipient to message
			m.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

			// adding subject to message
			m.setSubject(subject);

			// attachement..

			// file path
			String path = "C:/Users/yogle/Downloads/Yoglesh_Java_Spring_Boot_Profile.pdf";

			MimeMultipart mimeMultipart = new MimeMultipart();
			// text
			// file

			MimeBodyPart textMime = new MimeBodyPart();

			MimeBodyPart fileMime = new MimeBodyPart();

			try {

				textMime.setText(message);

				File file = new File(path);
				fileMime.attachFile(file);

				mimeMultipart.addBodyPart(textMime);
				mimeMultipart.addBodyPart(fileMime);

			} catch (Exception e) {

				e.printStackTrace();
			}

			m.setContent(mimeMultipart);

			// send

			// Step 3 : send the message using Transport class
			Transport.send(m);

		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("Sent success...................");

	}

	// this is responsible to send email..
	private static void sendEmail(String message, String subject, String to, String from) {

		// Variable for gmail
		String host = "smtp.gmail.com";

		// get the system properties
		Properties properties = System.getProperties();
		System.out.println("PROPERTIES " + properties);

		// setting important information to properties object

		// host set
		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.port", "465");
		properties.put("mail.smtp.ssl.enable", "true");
		properties.put("mail.smtp.auth", "true");

		// Step 1: to get the session object..
		Session session = Session.getInstance(properties, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("provide your gmail", "provide your password");
			}

		});

		session.setDebug(true);

		// Step 2 : compose the message [text,multi media]
		MimeMessage m = new MimeMessage(session);

		try {

			// from email
			m.setFrom(new InternetAddress(from, "Yoglesh Kumari"));

			// adding recipient to message
			m.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

			// adding subject to message
			m.setSubject(subject);

			// adding text to message
			m.setContent(message, "text/html");

			// send

			// Step 3 : send the message using Transport class
			Transport.send(m);

			System.out.println("Sent success...................");

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
