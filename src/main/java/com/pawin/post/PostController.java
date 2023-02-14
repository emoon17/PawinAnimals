package com.pawin.post;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.pawin.post.bo.PostBO;
import com.pawin.post.model.Keyword;
import com.pawin.post.model.PostView;

import jakarta.servlet.http.HttpSession;
@RequestMapping("/post")
@Controller
public class PostController {

	@Autowired
	private PostBO postBO;
	
	/**
	 *  가족을 찾고습니다 글쓰기 view
	 * @param model
	 * @return
	 */
	@GetMapping("/look_for_family_create_view")
	public String lookFamilyDetailView(Model model) {
		
		model.addAttribute("veiwName", "post/LookfamilyCreate");
		return "template/layout";
	}
	
	/**
	 *  가족을 찾고습니다 글 목록 view
	 * @param model
	 * @return
	 */
	@GetMapping("/look_for_family_list_view")
	public String lookFamilyListView(Model model, HttpSession session) {
		
		// 세션 가져오기
		Integer userId = (Integer)session.getAttribute("userId");
		// postView 카드 가져오기 - 댓글, 글, 사진 다 있는 거 가져오기.
		List<PostView> postList = postBO.generatePostList(userId);
		model.addAttribute("postList", postList);
		
		// view 응답
		model.addAttribute("veiwName", "post/LookfamilyList");
		return "template/layout";
	}
	
	@GetMapping("/search_list_view")
	public String searchList(
			@RequestParam(value="searchTitle", required=false) String searchTitle,
			@RequestParam("searchStatus") String searchStatus,
			@RequestParam("searchAnimals") String searchAnimals,
			@RequestParam("searchArea") String searchArea,
			Model model){
		
		
		//select
		List<Keyword> keywordList = postBO.getKeywordListByTitleStatusAnimalsArea(searchTitle, searchStatus, searchAnimals, searchArea);
		
		// 응답
		model.addAttribute("keywordList", keywordList);
		model.addAttribute("veiwName", "post/LookfamilyListSearch");
		return "template/layout";
		
	}
	
	
}
