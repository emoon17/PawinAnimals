package com.pawin.hospital.bo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class RestAPI {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	private final String seoulUrl = "http://openapi.seoul.go.kr:8088/";

	private final String kakao_url = "	https://map.kakao.com/link/map/v2/local/search/keyword.json?";
	private final String kakao_apiKey = "fa9316195f36cf58e533d0df27dfc53b";
	// 카카오 api key를 헤더에 셋팅

	public String seoulAPI() throws JsonProcessingException {

		// 0. 결과 값을 담을 객체를 생성한다.
		HashMap<String, Object> result = new HashMap<String, Object>(); // 예외처리

		String jsonInString = "";

		try {
			// Apache HttpComponents : 각 호스트 (Ip와 Port의 조합) 당 커넥션 풀에 생성 가능한 커넥션 수
			HttpClient httpClient = HttpClientBuilder.create().setMaxConnTotal(50) // 최대 커넥션 수
					.setMaxConnPerRoute(20).setConnectionTimeToLive(5, TimeUnit.SECONDS) // keep - alive
					.build();
			// 1. 타임아웃 설정 시 이 객체를 통해 타임아웃을 제어할 수 있음
			/*
			 * HttpComponentsClientHttpRequestFactory factory = new
			 * HttpComponentsClientHttpRequestFactory(); factory.setConnectTimeout(5000); //
			 * 타임아웃 설정 5초 factory.setReadTimeout(4500); // 타임아웃 설정 5초
			 * factory.setHttpClient(httpClient);
			 */

			// 2. RestTemplate 객체 생성
			RestTemplate restTemplate = new RestTemplate();

			// 3. header설정을 위해 HttpHeader클래스를 생성한 후 HttpEntity 객체 넣어주기
			HttpHeaders header = new HttpHeaders();
			HttpEntity<String> entity = new HttpEntity<>(header);

			// 4. url와 key값
			UriComponents uri = UriComponentsBuilder
					.fromHttpUrl(seoulUrl + "4a7357466b65756e37386462676f62" + "/json/LOCALDATA_020301/1/150/").build();

			// 5. exchange() 코드로 api를 호출해 map 타입으로 전달 받는다.
			ResponseEntity<Map> responseMap = restTemplate.exchange(uri.toString(), HttpMethod.GET, entity, Map.class);
			result.put("statusCode", responseMap.getStatusCodeValue()); // http status code를 확인
			result.put("header", responseMap.getHeaders()); // 헤더 정보 확인
			result.put("body", responseMap.getBody()); // 실제 데이터 정보 확인

			// 데이터를 제대로 전달 받았는 지 확인. String형태로 파싱해줌
			ObjectMapper mapper = new ObjectMapper();
			jsonInString = mapper.writeValueAsString(responseMap.getBody());
		} catch (HttpClientErrorException | HttpServerErrorException e) {
			result.put("statusCode", e.getRawStatusCode());
			result.put("body", e.getStatusText());
			System.out.println(e.toString());
		} catch (Exception e) {
			result.put("statusCode", "999");
			result.put("body", "excpetion오류");
			System.out.println(e.toString());
		}

		return jsonInString;
	}

	public String callApi(String name, String X, String Y) throws JsonProcessingException {
		// 0. 결과 값을 담을 객체를 생성한다.
		HashMap<String, Object> result = new HashMap<String, Object>(); // 예외처리

		String jsonInString = "";

		try {
			// Apache HttpComponents : 각 호스트 (Ip와 Port의 조합) 당 커넥션 풀에 생성 가능한 커넥션 수
			HttpClient httpClient = HttpClientBuilder.create().setMaxConnTotal(50) // 최대 커넥션 수
					.setMaxConnPerRoute(20).setConnectionTimeToLive(5, TimeUnit.SECONDS) // keep - alive
					.build();
			// RestTemplate 객체 생성
			RestTemplate restTemplate = new RestTemplate();

			// header설정을 위해 HttpHeader클래스를 생성한 후 apikey 갑 세팅 후 HttpEntity 객체 넣어주기
			HttpHeaders header = new HttpHeaders();
			header.set("Authorization", "KakaoAK " + kakao_apiKey);
			HttpEntity<String> entity = new HttpEntity<>(header);

			//url
			UriComponents uri = UriComponentsBuilder
					.fromHttpUrl(kakao_url + "query=카카오프렌즈&y=37.514322572335935&x=127.06283102249932").build();

			//exchange() 코드로 api를 호출해 map 타입으로 전달 받는다.
			ResponseEntity<Map> responseMap = restTemplate.exchange(uri.toString(), HttpMethod.GET, entity, Map.class);
			result.put("statusCode", responseMap.getStatusCodeValue()); // http status code를 확인
			result.put("header", responseMap.getHeaders()); // 헤더 정보 확인
			result.put("body", responseMap.getBody()); // 실제 데이터 정보 확인

			// 데이터를 제대로 전달 받았는 지 확인. String형태로 파싱해줌
			ObjectMapper mapper = new ObjectMapper();
			jsonInString = mapper.writeValueAsString(responseMap.getBody());
		} catch (HttpClientErrorException | HttpServerErrorException e) {
			result.put("statusCode", e.getRawStatusCode());
			result.put("body", e.getStatusText());
			System.out.println(e.toString());
		} catch (Exception e) {
			result.put("statusCode", "999");
			result.put("body", "excpetion오류");
			System.out.println(e.toString());
		}

		return jsonInString;
	}

}
