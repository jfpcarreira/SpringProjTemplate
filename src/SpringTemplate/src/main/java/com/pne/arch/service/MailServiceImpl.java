package com.pne.arch.service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.pne.arch.model.Mail;

@Service
public class MailServiceImpl implements MailService {

	private static final Logger logger = Logger.getLogger(MailServiceImpl.class);

	@Autowired
	private JavaMailSender mailSender;
	@Autowired
	private SimpleMailMessage alertMailMessage;

	public void sendMail(Mail mail) {
		SimpleMailMessage message = new SimpleMailMessage();

		message.setFrom(mail.getFrom());
		message.setTo(mail.getTo());
		message.setSubject(mail.getSubject());
		message.setText(mail.getText());

		mailSender.send(message);
		logger.info("Mail sent from " + mail.getFrom() + " to " + mail.getTo());
	}

	public void sendMail(String from, String to, String subject, String text) {
		Mail mail = new Mail();

		mail.setFrom(from);
		mail.setTo(to);
		mail.setSubject(subject);
		mail.setText(text);
		
		this.sendMail(mail);
	}

	public void sendAlertMail(String alert) {
		SimpleMailMessage mailMessage = new SimpleMailMessage(alertMailMessage);

		mailMessage.setText(alert);

		mailSender.send(mailMessage);
	}

	public void sendHtmlMail(Mail mail) {
		MimeMessage mimeMessage = mailSender.createMimeMessage();
		MimeMessageHelper mimeHelper;
		try {
			mimeHelper = new MimeMessageHelper(mimeMessage, false, "utf-8");

			String htmlMsg = getHtmlMail();
			mimeMessage.setContent(htmlMsg, "text/html");

			mimeHelper.setFrom(mail.getFrom());
			mimeHelper.setTo(mail.getTo());
			mimeHelper.setSubject(mail.getSubject());

			mailSender.send(mimeMessage);
			logger.info("HTML mail sent from " + mail.getFrom() + " to " + mail.getTo());
		} catch (MessagingException e) {
			this.sendAlertMail(e.getMessage());
			e.printStackTrace();
		}
	}

	private String getHtmlMail() {
		StringBuffer str = new StringBuffer();
		
		str.append("<h2>Hello World!</h2><br/>");
		str.append("<h3>Isto � um teste ao mail HTML</h3><br/>");
		str.append("<table border=\"1\">");
		str.append("<tr><td>col1</td><td>col2</td><td>col3</td></tr>");
		str.append("<tr><td>linha1</td><td>linha1</td><td>linha1</td></tr>");
		str.append("<tr><td>linha2</td><td>linha2</td><td>linha2</td></tr>");
		str.append("<tr><td>linha3</td><td>linha3</td><td>linha3</td></tr>");
		str.append("</table>");
		
		return str.toString();
	}
}
