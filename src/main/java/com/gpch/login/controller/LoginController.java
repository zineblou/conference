package com.gpch.login.controller;

import java.util.Optional;


import javax.validation.Valid;

import com.gpch.login.model.User;
import com.gpch.login.repository.MyUserDetailsService;
import com.gpch.login.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class LoginController {

	@Autowired
	private MyUserDetailsService userService;
    @RequestMapping(value={"/", "/login"}, method = RequestMethod.GET)
    public ModelAndView login(){
    	
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        return modelAndView;
    }

    @RequestMapping(value="/profile",method=RequestMethod.GET)
    public ModelAndView profile()
    {
    	ModelAndView modelAndView = new ModelAndView();
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    	UserDetails user = userService.loadUserByUsername(auth.getName());
    	modelAndView.addObject(user);
    	modelAndView.setViewName("/profile");
    	return modelAndView;
    }
    

    @RequestMapping(value="/registration", method = RequestMethod.GET)
    public ModelAndView registration(){
        ModelAndView modelAndView = new ModelAndView();
        User user = new User();
        modelAndView.addObject("user", user);
        modelAndView.setViewName("registration");
        return modelAndView;
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public ModelAndView createNewUser(@Valid User user, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        System.out.println("User "+user.getEmail());
        UserDetails userExists = userService.loadUserByUsername(user.getEmail());       
        if (userExists != null) {
            bindingResult
                    .rejectValue("email", "error.user",
                            "il existe deja un utilisateur avec le meme email");
        }
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("registration");
        } else {
        	userService.saveUser(user);         
            modelAndView.addObject("successMessage", "utilisateur ajoute");
            modelAndView.addObject("user", new User());
            modelAndView.setViewName("registration");

        }
        return modelAndView;
    }
    /*
    @RequestMapping(value="/admin/home", method = RequestMethod.GET)
    public ModelAndView home(){
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        modelAndView.addObject("userName", "Welcome " + user.getName() + " " + user.getLastName() + " (" + user.getEmail() + ")");
        modelAndView.addObject("adminMessage","Content Available Only for Users with Admin Role");
        modelAndView.setViewName("admin/home");
        return modelAndView;
    }
	*/
    
    //acceuil athensallestication
    @RequestMapping(value="/acc")
    public ModelAndView home(Model model)
    { 	
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    	UserDetails user = userService.loadUserByUsername(auth.getName()); 
    	ModelAndView modelAndView = new ModelAndView();
    	modelAndView.addObject("user", user);
    	
    	System.out.println("user name "+user.getUsername()); 
    	modelAndView.setViewName("acceuilAth");
        return modelAndView;    	
    }
    


}
