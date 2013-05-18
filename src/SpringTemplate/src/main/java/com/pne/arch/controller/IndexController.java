package com.pne.arch.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

	@RequestMapping(value="/")
	public String empty(Model model) {
		return "home";
	}

	@RequestMapping(value="/index")
	public String index(Model model) {
		return "home";
	}

	@RequestMapping(value="/home")
	public String home(Model model) {
		return "home";
	}

}
