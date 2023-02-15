package com.pawin.post;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
		return "post/LookfamilyListSearch";
		
	}
	
	@GetMapping("/look_for_family_detail_view")
	public String familyDetailView(
			@RequestParam("postId") int postId,
			Model model,
			HttpSession session) {
		
		// 세션에서 userId가져오기- 수정하기 전까진 로그인 안 된 사람도 볼 수 있음 
		Integer userId = (Integer)session.getAttribute("userId");
		
		
		// 로그인 되어있는 사람- 1. DB select -userId, postId
		List<PostView> postViewList = postBO.getPostByPostIdUserId(postId, userId);
		model.addAttribute("postView", postViewList);
		// 화면 이동
		model.addAttribute("veiwName", "post/LookFamilyDetail");
		return "template/layout";
		
		
	}
	
	
}
