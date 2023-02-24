package com.pawin.hospital;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class HospitalRestController {

	@GetMapping("hospital/list_data")
	public HashMap<String,Object> hospitalListAPI() {
		
		// 0. 결과 값을 담을 객체를 생성한다.
		HashMap<String, Object> result = new HashMap<String, Object>();
		
		//String jsonInString = "";
		
		try {
			 //Apache HttpComponents : 각 호스트 (Ip와 Port의 조합) 당 커넥션 풀에 생성 가능한 커넥션 수
			  HttpClient httpClient = HttpClientBuilder.create() 
					  								.setMaxConnTotal(50) // 최대 커넥션 수 
					  								.setMaxConnPerRoute(20)
					  								.setConnectionTimeToLive(5, TimeUnit.SECONDS) // keep - alive
					  							    .build();
			// 1. 타임아웃 설정 시 이 객체를 통해 타임아웃을 제어할 수 있음
				HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
				factory.setConnectTimeout(5000); // 타임아웃 설정 5초
				factory.setReadTimeout(5000); // 타임아웃 설정 5초
			    factory.setHttpClient(httpClient);
			
			// 2. RestTemplate 객체 생성
			RestTemplate restTemplate = new RestTemplate(factory);
			
			// 3. header설정을 위해 HttpHeader클래스를 생성한 후 HttpEntity 객체 넣어주기
			HttpHeaders header = new HttpHeaders();
			HttpEntity<String>entity = new HttpEntity<>(header);
			
			// 4. url와 key값
			String url = "http://www.localdata.go.kr/platform/rest/GR0/openDataApi?authKey=";
			UriComponents uri = UriComponentsBuilder.fromHttpUrl(url + "UiDxgnOHTAtsXEb01AnenLxgLu=XqL7j5C3NTnPBxnQ=").build();
			
			// 5. exchange() 코드로 api를 호출해 map 타입으로 전달 받는다.
			ResponseEntity<Map> response = restTemplate.exchange(uri.toString(), HttpMethod.GET, entity, Map.class);
			result.put("statusCode", response.getStatusCodeValue()); //http status code를 확인
	        result.put("header", response.getHeaders()); //헤더 정보 확인
	        result.put("body", response.getBody()); //실제 데이터 정보 확인
	        
	        // 데이터를 제대로 전달 받았는 지 확인. String형태로 파싱해줌
	    //    ObjectMapper mapper = new ObjectMapper();
	     //   jsonInString = mapper.writeValueAsString(response.getBody());
		} catch (HttpClientErrorException | HttpServerErrorException e) {
			result.put("statusCode", e.getRawStatusCode());
			result.put("body", e.getStatusText());
			System.out.println(e.toString());
		} catch(Exception e) {
			result.put("statusCode", "999");
            result.put("body"  , "excpetion오류");
            System.out.println(e.toString());
		}
		
		return result;
	}
}
