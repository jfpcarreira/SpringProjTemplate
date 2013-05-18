package com.pne.arch.config;

import java.util.Properties;

import javax.annotation.Resource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import com.pne.arch.constant.PropertiesConstant;

@Configuration
@PropertySource("classpath:mail.properties")
public class MailConfig {

	@Resource
	private Environment env;

	@Bean
	public JavaMailSenderImpl mailSender() {
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();

		mailSender.setHost(env.getRequiredProperty(PropertiesConstant.MAIL_HOST));
		mailSender.setPort(Integer.parseInt(env.getRequiredProperty(PropertiesConstant.MAIL_PORT)));
		mailSender.setUsername(env.getRequiredProperty(PropertiesConstant.MAIL_USERNAME));
		mailSender.setPassword(env.getRequiredProperty(PropertiesConstant.MAIL_PASSWORD));
		mailSender.setJavaMailProperties(mailProperties());

		return mailSender;
	}

	private Properties mailProperties() {
		Properties properties = new Properties();
		// JavaMail properties
		properties.put(PropertiesConstant.MAIL_TRANSP_PROTOCOL, env.getRequiredProperty(PropertiesConstant.MAIL_TRANSP_PROTOCOL));
		properties.put(PropertiesConstant.MAIL_SMTP_AUTH, env.getRequiredProperty(PropertiesConstant.MAIL_SMTP_AUTH));
		properties.put(PropertiesConstant.MAIL_STMP_STARTTLS, env.getRequiredProperty(PropertiesConstant.MAIL_STMP_STARTTLS));
		return properties;	
	}

	@Bean
	public SimpleMailMessage alertMailMessage() {
		SimpleMailMessage alertMail = new SimpleMailMessage();

		alertMail.setFrom(env.getRequiredProperty(PropertiesConstant.MAIL_FROM_DEFAULT));
		alertMail.setTo(env.getRequiredProperty(PropertiesConstant.MAIL_TO_DEFAULT));
		alertMail.setSubject("Alert - Ocorreu uma excepção. É favor investigar.");

		return alertMail;
	}

}
