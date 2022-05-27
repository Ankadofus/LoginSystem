package com.app.user;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {
	
	@GetMapping("/")
	public String login(Model model) {
		User user=new User();
		model.addAttribute("user", user);
		return "login";
	}
	
	@PostMapping("/userLogin")
	public String loginUser(@ModelAttribute("user") User user) {
		return "home";
	}
}
