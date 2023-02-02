package com.pawin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
@RequestMapping("/user")
@Controller
public class UserController {
	@GetMapping("/signIn_view")
	public String signIn(Model model) {
		
		model.addAttribute("veiwName", "user/signIn");
		return "template/layout";
	}
	
	@GetMapping("/signUp_view")
	public String signUp(Model model) {
		
		model.addAttribute("veiwName", "user/signUp");
		return "template/layout";
	}
}
