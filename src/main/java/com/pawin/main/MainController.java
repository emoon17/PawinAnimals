package com.pawin.main;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
	/**
	 *  메인화면 view
	 * @param model
	 * @return
	 */
	@GetMapping("/main/main_view")
	public String MainView(Model model) {
		
		model.addAttribute("veiwName", "main/main");
		return "template/layout";
	}
}
