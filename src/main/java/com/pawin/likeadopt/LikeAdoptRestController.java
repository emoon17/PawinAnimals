package com.pawin.likeadopt;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pawin.likeadopt.bo.LikeadoptBO;

import jakarta.servlet.http.HttpSession;

@RestController
public class LikeAdoptRestController {

	
	@Autowired
	private LikeadoptBO likeadoptBO;
	
	
	// togle api
	@GetMapping("/likeAdopt/{postId}")
	public Map<String, Object> likeAdopt(
			@PathVariable int postId,
			@RequestParam("type") String type,
			HttpSession session){
		
		// 로그인 정보, 어느 글에 눌렀는지 알아야한다.
		Map<String, Object> result = new HashMap<>();
		
		// session으로 필요한 정보 가져오기 : userId(로그인 정보) , loginId(로그인 아이디)
		Integer userId = (Integer)session.getAttribute("userId");
		if (userId == null) {
			result.put("code", 500);
			result.put("errorMessage", "로그인을 다시 해주세요.");
			return result;
		}
		String loginId = (String)session.getAttribute("loginId");
		// insert(로그인 된 사람만)
		likeadoptBO.likeadoptTogle(postId, postId, loginId, type);
		result.put("code", 1);
		// 응답내리기
		return result;
	}
}
