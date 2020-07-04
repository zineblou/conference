package com.gpch.login.controller;

import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.gpch.login.repository.UserRepository;

@Controller
public class acceuilController {
	
	

	@RequestMapping(value="/acceuil",method = RequestMethod.GET)
	public String getAcceuil()
	{
		System.out.println("acceuil 1");
		
		return "acceuil";
	}
	
	@RequestMapping(value="/test")
	public String getFooter()
	{
		return "/test";
	}
}
