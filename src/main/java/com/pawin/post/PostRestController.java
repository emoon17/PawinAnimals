package com.pawin.post;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.pawin.post.bo.PostBO;
import com.pawin.post.bo.PostImageBO;

import jakarta.servlet.http.HttpSession;

@RequestMapping("/post")
@RestController
public class PostRestController {

	@Autowired
	private PostBO postBO;

	@Autowired
	private PostImageBO postImageBO;
	
	
	@PostMapping("/post_create")
	public Map<String, Object> create(
			@RequestParam(value="writeTitle", required=false) String writeTitle,
			@RequestParam(value="writeArea", required=false) String content,
			@RequestPart(value="files", required=false) List<MultipartFile> files, 
			@RequestParam(value="animals", required=false) String animals,
			@RequestParam(value="status", required=false) String status,
		    @RequestParam(value="area", required=false) String area,
			HttpSession session
			) {

		/*
		 * // 세션으로 필요한 정보 가져오기 userId, loginId, postId int userId = (int)
		 * session.getAttribute("userId"); String loginId = (String)
		 * session.getAttribute("loginId"); int postId = (int)
		 * session.getAttribute("postId");
		 * 
		 * // db insert int rowCount = postBO.addPost(title, content, files, animals,
		 * status, area, userId, loginId, postId); postImageBO.addPost(files, userId,
		 * postId, loginId);
		 */
		// code 구분
		Map<String, Object> result = new HashMap<>();
		/*
		 * if (rowCount > 0) { result.put("code", 1); session.setAttribute("title",
		 * title); session.setAttribute("animals", animals);
		 * session.setAttribute("status", status); session.setAttribute("area", area);
		 * session.setAttribute("userId", userId); } else { result.put("errorMessage",
		 * "관리자에게 문의하여 주세요."); }
		 */
		// 응답하기
		return result;
	}

}
