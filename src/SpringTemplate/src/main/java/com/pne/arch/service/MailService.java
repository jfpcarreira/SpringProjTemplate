package com.pne.arch.service;

import com.pne.arch.entity.Mail;

public interface MailService {

	public void sendMail(Mail mail);
	public void sendMail(String from, String to, String subject, String body);
	public void sendAlertMail(String alert);
	public void sendHtmlMail(Mail mail);

}
