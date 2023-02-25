package com.pawin.hospital.bo;

import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.pawin.hospital.model.Hospital;

@Service
public class HospitalBO {
	
	@Autowired
	private RestAPI restAPI;
	
//	public List<Hospital> fetch() throws ParseException {
//		RestTemplate restTemplate = new RestTemplate();
//		String jsonString = restTemplate.getForObject(uri(), String.class);
//	}
}
