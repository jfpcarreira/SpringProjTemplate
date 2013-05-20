package com.pne.arch.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.pne.arch.entity.Mail;
import com.pne.arch.service.MailService;

@Controller
@RequestMapping(value="/secured/mail")
@PreAuthorize("isAuthenticated()")
public class MailController {

	@Autowired
	private MailService mailService;

	@RequestMapping(value="/send", method=RequestMethod.GET)
	public ModelAndView sendMailPage(@ModelAttribute Mail mail) {

		ModelAndView modelAndView = new ModelAndView("send_mail");
		return modelAndView;
	}

	@RequestMapping(value="/sendHTML", method=RequestMethod.GET)
	public ModelAndView sendHtmlMailPage(@ModelAttribute Mail mail) {

		ModelAndView modelAndView = new ModelAndView("send_html_mail");
		return modelAndView;
	}

	@RequestMapping(value="/send/process", method=RequestMethod.POST)
	public ModelAndView sendingMail(@ModelAttribute Mail mail) {

		ModelAndView modelAndView = new ModelAndView("home");

		mailService.sendMail(mail);

		String message = "Mail was successfully sended.";
		modelAndView.addObject("message", message);

		return modelAndView;
	}

	@RequestMapping(value="/send/processHTML", method=RequestMethod.POST)
	public ModelAndView sendingHtmlMail(@ModelAttribute Mail mail) {

		ModelAndView modelAndView = new ModelAndView("home");

		mailService.sendHtmlMail(mail);

		String message = "HTML mail was successfully sended.";
		modelAndView.addObject("message", message);

		return modelAndView;
	}
}
