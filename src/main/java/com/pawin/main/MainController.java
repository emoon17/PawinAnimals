package com.pawin.main;

import java.util.List;
import java.util.Map;

import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.pawin.hospital.bo.HospitalBO;
import com.pawin.hospital.model.Hospital;
import com.pawin.post.bo.PostBO;
import com.pawin.post.model.Post;

@Controller
public class MainController {
	
	@Autowired
	private PostBO postBO;
	
	@Autowired
	private HospitalBO hospitalBO;
	
	/**
	 *  메인화면 view
	 * @param model
	 * @return
	 * @throws ParseException 
	 * @throws JsonProcessingException 
	 */
	@GetMapping("/main/main_view")
	public String MainView( Model model) throws JsonProcessingException, ParseException {
		
		// 가족을 찾습니다 10개
		List<Post> postList = postBO.getFamilyPostListDesc();
		model.addAttribute("postList", postList);
		
		// 병원 10개
		List<Map<String, Object>>  hospitalList = hospitalBO.restParshingLimit();
		model.addAttribute("hospitalList", hospitalList);
		
		model.addAttribute("veiwName", "main/main");
		return "template/layout";
	}
	
	
}
