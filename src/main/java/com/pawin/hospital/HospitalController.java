package com.pawin.hospital;

import java.util.List;
import java.util.Map;

import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.pawin.hospital.bo.HospitalBO;
import com.pawin.hospital.bo.RestAPI;


@Controller
public class HospitalController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private RestAPI restAPI;
	
	@Autowired
	private HospitalBO hospitalBO;
	
	/**
	 *  동물병원 목록 view
	 * @param model
	 * @return
	 * @throws JsonProcessingException
	 * @throws ParseException
	 */
	@GetMapping("/hospital_list_view")
	public String hostpitalListView(Model model) throws JsonProcessingException, ParseException{

		
		List<Map<String, Object>> hospitalList = hospitalBO.restParshing();
		
		model.addAttribute("hospitalList", hospitalList);
		model.addAttribute("veiwName", "post/hospital/list");
		return "template/layout";
	}
	
	@GetMapping("/hospital_list_detail_view")
	public String hostpitalListDetailView(Model model, String name, String X, String Y) {
		
		//JSP file [/WEB-INF/jsp/.jsp] not found 라고 뜸.
		model.addAttribute("veiwName", "post/hospital/detail");
		return "template/layout";
	}
}
