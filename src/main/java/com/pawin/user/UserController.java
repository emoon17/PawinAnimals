package com.pawin.user;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;
@RequestMapping("/user")
@Controller
public class UserController {
	/**
	 * 로그인하기 view
	 * @param model
	 * @return
	 */
	@GetMapping("/signIn_view")
	public String signIn(Model model) {
		
		model.addAttribute("veiwName", "user/signIn");
		return "template/layout";
	}
	
	/**
	 * 회원가입 뷰
	 * @param model
	 * @return
	 */
	@GetMapping("/signUp_view")
	public String signUp(Model model) {
		
		model.addAttribute("veiwName", "user/signUp");
		return "template/layout";
	}
	
	@GetMapping("/signOut")
	public String signOut(HttpSession session) {
		
		//로그인 시 세션에 담아뒀던 정보 지우기 user id, name, loginId, phone 담아놓기
		session.removeAttribute("userId");
		session.removeAttribute("name");
		session.removeAttribute("loginId");
		session.removeAttribute("phoneNumber");
		
		return "redirect:/main/main_view";
	}
	
	
}
