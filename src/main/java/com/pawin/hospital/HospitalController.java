package com.pawin.hospital;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.pawin.hospital.bo.RestAPI;

@Controller
public class HospitalController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private RestAPI restAPI;
	
	@GetMapping("/hospital_list_view")
	public String hostpital(Model model) throws ParseException, JsonProcessingException {

		// json 파싱하기
		String json = restAPI.seoulAPI();

		JSONParser jsonParser = new JSONParser();
		JSONObject jsonObject = (JSONObject) jsonParser.parse(json);

		JSONObject LOCALDATA = (JSONObject) jsonObject.get("LOCALDATA_020301");

		JSONObject row = (JSONObject) LOCALDATA.get("row");
		
		JSONObject item;
		for (int i = 0; i < row.size(); i++) {
			item = (JSONObject)row.get(i);
			String name = (String)item.get("BPLCNM");
			String address = (String)item.get("RDNWHLADDR");
			String X = (String)item.get("X");
			String Y = (String)item.get("Y");
			model.addAttribute("name",name);
			model.addAttribute("address",address);
			model.addAttribute("X",X);
			model.addAttribute("Y",Y);
		}

		// logger.info(docuObject.get("keyValue").toString());
		// 뽑아오기 원하는 Key 이름을 넣어주면 그 value가 반환된다.

		model.addAttribute("veiwName", "post/hospital_list_view");
		return "template/layout";
	}

}
