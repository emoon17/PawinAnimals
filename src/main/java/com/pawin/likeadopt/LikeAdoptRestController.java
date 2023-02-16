package com.pawin.likeadopt;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpSession;

@RestController
public class LikeAdoptRestController {

	// togle api
	@GetMapping("/likeAdopt/{postId}")
	public Map<String, Object> likeAdopt(
			@PathVariable int postId,
			@RequestParam("userId") int userId,
			@RequestParam("type") String type,
			HttpSession session){
		
		// 로그인 정보, 어느 글에 눌렀는지 알아야한다.
		
		Map<String, Object> result = new HashMap<>();
		
		// session으로 필요한 정보 가져오기 : userId(로그인 정보)
		
		// insert(로그인 된 사람만)
		
		// 응답내리기
		return result;
	}
}
