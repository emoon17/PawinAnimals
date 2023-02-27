package com.pawin.hospital.bo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;

@Service
public class HospitalBO {

	@Autowired
	private RestAPI restAPI;
	
	public List<Map<String, Object>> restParshing() throws JsonProcessingException, ParseException{
		
		// json 파싱하기
		String json = restAPI.seoulAPI();

		JSONParser jsonParser = new JSONParser();
		JSONObject jsonObject = (JSONObject) jsonParser.parse(json);

		JSONObject LOCALDATA = (JSONObject) jsonObject.get("LOCALDATA_020301");

		JSONArray row = (JSONArray) LOCALDATA.get("row");
		JSONObject item;

		List<Map<String, Object>> hospitalList = new ArrayList<>();
		for (int i = 0; i < row.size(); i++) {
			item = (JSONObject)row.get(i);
			
			Map<String, Object> map = new HashMap<String, Object>();
			String closed = (String)item.get("TRDSTATENM");
			
			if (!closed.contains("휴업") && !closed.contains("폐업") ) {
				
				String name = (String)item.get("BPLCNM");
				map.put("name", name);
				String address = (String)item.get("RDNWHLADDR");
				map.put("address", address);
				String X = (String)item.get("X");
				map.put("X", X);
				String Y = (String)item.get("Y");
				map.put("Y", Y);
				
				hospitalList.add(map);
			}
			
		}
		return hospitalList;
	}
}
