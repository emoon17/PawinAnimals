package com.pawin.hospital.bo;

import org.apache.tomcat.util.json.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Service;

@Service
public class HospitalBO {

	@Autowired
	private RestAPI restAPI;

}
