package com.pawin.comment;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pawin.comment.bo.CommentBO;

import jakarta.servlet.http.HttpSession;

@RestController
public class CommentRestController {

	@Autowired
	CommentBO commentBO;
	
	@PostMapping("/comment/create")
	public Map<String, Object> createComment(
			@RequestParam("postId") int postId,
			@RequestParam("content") String content, 
			HttpSession session){
		
		Map<String, Object> result = new HashMap<>();
		//session으로 필요항목 가져오기 -userId
		Integer userId = (Integer)session.getAttribute("userId");
		if (userId == null) {
			result.put("code", 500);
			result.put("errorMessage", "로그인을 다시 해주세요.");
			return result;
		}
		
		// insert
		commentBO.addCommentByUserIdPostIdComment(userId, postId, content);
		// 코드 구분하기
		result.put("code", 1);
		// 응답 내리기
		return result;
	}
}
