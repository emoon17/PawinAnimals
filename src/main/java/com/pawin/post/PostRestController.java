package com.pawin.post;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.pawin.post.bo.PostBO;
import com.pawin.post.bo.PostImageBO;
import com.pawin.post.model.Post;

import jakarta.servlet.http.HttpSession;

@RequestMapping("/post")
@RestController
public class PostRestController {

	@Autowired
	private PostBO postBO;

	@Autowired
	private PostImageBO postImageBO;

	/**
	 * 글쓰기 API
	 * 
	 * @param post
	 * @param files
	 * @param session
	 * @return
	 */
	@PostMapping("/post_create")
	public Map<String, Object> create(@ModelAttribute Post post, @RequestPart("files") List<MultipartFile> files,
			HttpSession session) {

		int userId = (int) session.getAttribute("userId");

		// db insert
		postBO.addPost(post, files, userId);

		// code 구분
		Map<String, Object> result = new HashMap<>();

		if (post != null) {
			result.put("code", 1);
		} else {
			result.put("errorMessage", "관리자에게 문의하여 주세요.");
		}

		// 응답하기
		return result;
	}
	/**
	 *  글 수정하기 API
	 * @param postId
	 * @param title
	 * @param content
	 * @param status
	 * @param animals
	 * @param area
	 * @param files
	 * @param session
	 * @return
	 */
	@PutMapping("/post_update")
	public Map<String, Object> postUpdate(@RequestParam("postId") int postId, @RequestParam("title") String title,
			@RequestParam("content") String content, @RequestParam("status") String status,
			@RequestParam("animals") String animals, @RequestParam(value="area", required=false) String area,
			@RequestPart("files") List<MultipartFile> files, HttpSession session) {

		// 필요한 세션 - userId, loginId
		int userId = (int) session.getAttribute("userId");

		// db update
		postBO.updatePost(postId, title, content, status, animals, area, files, userId);
		// 코드 나누기
		Map<String, Object> result = new HashMap<>();

		result.put("code", 1);

		// 응답하기
		return result;
	}

	/**
	 * 게시글 삭제하기 API
	 * @param postId
	 * @param session
	 * @return
	 */
	@DeleteMapping("/post_delete")
	public Map<String, Object> postDelete(
			@RequestParam("postId") int postId,
			HttpSession session) {
		
		// 필요한 세션 - userId, loginId
		int userId = (int) session.getAttribute("userId");
		
		//db delete
		int row = postBO.deletePostByPostIdUserId(postId, userId);
		// 코드 나누기
		Map<String, Object> result = new HashMap<>();
		if (row > 0) {
			result.put("code", 1);
		} else {
			result.put("errorMessage", "잠시후 다시 시도 부탁드립니다.");
		}
		// 응답 보내기
		return result;
	}

}
