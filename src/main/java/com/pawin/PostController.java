package com.pawin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
@RequestMapping("/post")
@Controller
public class PostController {

	@GetMapping("/look_for_family_create_view")
	public String lookFamilyDetailView(Model model) {
		
		model.addAttribute("veiwName", "post/LookfamilyCreate");
		return "template/layout";
	}
	
}
