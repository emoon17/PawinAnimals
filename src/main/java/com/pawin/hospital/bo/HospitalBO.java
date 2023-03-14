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

	public List<Map<String, Object>> restParshing() throws JsonProcessingException, ParseException {

		// json 파싱하기
		String json = restAPI.seoulAPI();

		JSONParser jsonParser = new JSONParser();
		JSONObject jsonObject = (JSONObject) jsonParser.parse(json);

		JSONObject LOCALDATA = (JSONObject) jsonObject.get("LOCALDATA_020301");

		JSONArray row = (JSONArray) LOCALDATA.get("row");
		JSONObject item;

		List<Map<String, Object>> hospitalList = new ArrayList<>();
		for (int i = 0; i < row.size(); i++) {
			item = (JSONObject) row.get(i);

			Map<String, Object> map = new HashMap<String, Object>();
			String closed = (String) item.get("TRDSTATENM");
			String name = (String) item.get("BPLCNM");
			String address = (String) item.get("RDNWHLADDR");
			String X = (String) item.get("X");
			String Y = (String) item.get("Y");

			if (!closed.contains("휴업") && !closed.contains("폐업")) {

				if (!X.isEmpty() && !Y.isEmpty()) {
					map.put("name", name);

					map.put("address", address);
					map.put("X", X);
					map.put("Y", Y);

					hospitalList.add(map);
				}
			}

		}
		return hospitalList;
	}

	// 리스트 열개만 가져오기
	public List<Map<String, Object>> restParshingLimit() throws JsonProcessingException, ParseException {

		// json 파싱하기
		String json = restAPI.seoulAPI();

		JSONParser jsonParser = new JSONParser();
		JSONObject jsonObject = (JSONObject) jsonParser.parse(json);

		JSONObject LOCALDATA = (JSONObject) jsonObject.get("LOCALDATA_020301");

		JSONArray row = (JSONArray) LOCALDATA.get("row");
		JSONObject item;

		List<Map<String, Object>> hospitalList = new ArrayList<>();
		for (int i = 0; i < 12; i++) {
			item = (JSONObject) row.get(i);

			Map<String, Object> map = new HashMap<String, Object>();
			String name = (String) item.get("BPLCNM");
			String closed = (String) item.get("TRDSTATENM");
			String X = (String) item.get("X");
			String Y = (String) item.get("Y");
			String address = (String) item.get("RDNWHLADDR");
					
					if (!closed.contains("휴업") && !closed.contains("폐업")) {

						if (!X.isEmpty() && !Y.isEmpty()) {
							map.put("name", name);
							map.put("X", X);
							map.put("Y", Y);
							map.put("address", address);
							hospitalList.add(map);
						}
					}
				
			}
		return hospitalList;
	}


	public List<Map<String, String>> nameAddressList(String name, String address) {
		List<Map<String, String>> nameAddressList = new ArrayList<>();
		Map<String, String> nameAddressMap = new HashMap<>();
		nameAddressMap.put("name", name);
		nameAddressMap.put("address", address);
		nameAddressList.add(nameAddressMap);

		return nameAddressList;
	}
}
