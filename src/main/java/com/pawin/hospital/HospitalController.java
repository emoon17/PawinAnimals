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
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.pawin.hospital.bo.HospitalBO;
import com.pawin.hospital.bo.TransCoord;


@Controller
public class HospitalController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private TransCoord transCoord;
	
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
	
	/**
	 * 병원 상세화면 view
	 * @param model
	 * @param name
	 * @param X
	 * @param Y
	 * @param address
	 * @return
	 */
	@GetMapping("/hospital_list_detail_view")
	public String hostpitalListDetailView(Model model, 
			@RequestParam("name") String name,
			@RequestParam("coordinateX") String X,
			@RequestParam("coordinateY" ) String Y,
			@RequestParam("address") String address) {

		//JSP file [/WEB-INF/jsp/.jsp] not found 라고 뜸.
		List<Map<Object, Object>> transCoordList = transCoord.transform(X, Y, name, address);
		model.addAttribute("transCoordList", transCoordList);
		model.addAttribute("veiwName", "post/hospital/detail");
		return "template/layout";
	}
	
	@GetMapping("/hospital_list_search_view")
	public String hospitalListSearchView(@RequestParam("searchAddress") String searchAddress, Model model) throws JsonProcessingException, ParseException {
		
		
		List<Map<String, Object>> hospitalList = hospitalBO.searchParshing(searchAddress);
		
		model.addAttribute("hospitalList", hospitalList);
		return "post/hospital/searchList";
	}

}
