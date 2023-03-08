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
import com.pawin.post.model.Post;
import com.pawin.post.model.PostView;

import jakarta.servlet.http.HttpSession;

@RequestMapping("/post")
@Controller
public class PostController {

	@Autowired
	private PostBO postBO;

	/**
	 * 가족을 찾고습니다 insert view
	 * 
	 * @param model
	 * @return
	 */
	@GetMapping("/look_for_family_create_view")
	public String lookFamilyDetailView(Model model) {

		model.addAttribute("veiwName", "post/LookfamilyCreate");
		return "template/layout";
	}

	/**
	 * 입양 글 insert view
	 * 
	 * @param model
	 * @return
	 */
	@GetMapping("/adopt_review_view")
	public String adoptReviewView(Model model) {

		model.addAttribute("veiwName", "post/adopt/review");
		return "template/layout";
	}

	/**
	 * 무료나눔 insert view
	 * 
	 * @param model
	 * @return
	 */
	@GetMapping("/free_sharing_view")
	public String freeSharingView(Model model) {

		model.addAttribute("veiwName", "post/sharing/freeCreate");
		return "template/layout";
	}

	/**
	 * 가족을 찾고습니다 글 목록 view
	 * 
	 * @param model
	 * @return
	 */
	@GetMapping("/look_for_family_list_view")
	public String lookFamilyListView(@RequestParam(value = "prevId", required = false) Integer preveIdParam,
			@RequestParam(value = "nextId", required = false) Integer nextIdParam, Model model, HttpSession session) {

		// 세션 가져오기
		Integer userId = (Integer) session.getAttribute("userId");
		if (userId == null) {
			return "redirect:/user/signIn_view";
		}

		// 로그인 된 사람은 게시글 뿌리기
		int prevId = 0;
		int nextId = 0;
		List<Post> postsList = postBO.getPostListByUserId(userId, preveIdParam, nextIdParam);
		if (postsList.isEmpty() == false) { // 리스트가 비어 있을 때 에러 방지
			prevId = postsList.get(0).getId(); // 가져온 리스트 중 가장 앞 쪽 (큰 id)
			nextId = postsList.get(postsList.size() - 1).getId(); // 가져온 리스트 중 가장 뒤 쪽( 작은 id)

			// 이전 방향의 끝인가? 끝이면 0으로 세팅
			// postList의 0인덱스 값(prevId)과 post 테이블의 가장 큰 값이 같으면 마지막 페이지
			if (postBO.isPrevLastPage(prevId, userId)) { // 마지막 페이지 일 때
				prevId = 0;
			}

			// 다음 방향의 끝인가? 끝이면 0으로 세팅
			// postList의 마지막 인덱스 값(nextId)과 post테이블의 가장 작은 값이 같으면 마지막 페이지
			if (postBO.isNextLastPage(nextId, userId)) {
				nextId = 0;
			}
		} // -- 처음 페이지에서 이전 못 누르고 마지막 페이지에서 다음 못 누르고

		model.addAttribute("prevId", prevId); // 가져온 리스트 중 가장 앞 쪽 (큰 id)
		model.addAttribute("nextId", nextId); // 가져온 리스트 중 가장 뒤 쪽( 작은 id)

		// postView 카드 가져오기 - 댓글, 글, 사진 다 있는 거 가져오기.
		List<PostView> postList = postBO.generatePostList(userId);
		model.addAttribute("postList", postList);

		// view 응답
		model.addAttribute("veiwName", "post/LookfamilyList");
		return "template/layout";
	}

	/**
	 * 입양 후기 글 목록 view
	 * 
	 * @param model
	 * @param session
	 * @return
	 */
	@GetMapping("/adopt_review_list_view")
	public String adoptListView(Model model, HttpSession session) {

		// 세션 가져오기
		Integer userId = (Integer) session.getAttribute("userId");
		if (userId == null) {
			return "redirect:/user/signIn_view";
		}
		// 입양완료 글만 가져오기
		List<PostView> postList = postBO.generateAdoptPostList(userId);
		model.addAttribute("postList", postList);

		// view 응답
		model.addAttribute("veiwName", "post/adopt/reviewList");
		return "template/layout";
	}

	/**
	 * 무료나눔 리스트 view
	 * 
	 * @param model
	 * @param session
	 * @return
	 */
	@GetMapping("/free_sharing_list_view")
	public String freeSharingListView(Model model, HttpSession session) {

		// 세션 가져오기
		Integer userId = (Integer) session.getAttribute("userId");
		// 무료나눔 글만 가져오기
		List<PostView> postList = postBO.generateSharingPostList(userId);
		model.addAttribute("postList", postList);

		// view 응답
		model.addAttribute("veiwName", "post/sharing/freeList");
		return "template/layout";
	}

	/**
	 * 가족을 찾습니다 목록 서치 view
	 * 
	 * @param searchTitle
	 * @param searchStatus
	 * @param searchAnimals
	 * @param searchArea
	 * @param model
	 * @return
	 */
	@GetMapping("/search_list_view")
	public String searchList(@RequestParam(value = "searchTitle", required = false) String searchTitle,
			@RequestParam("searchStatus") String searchStatus, @RequestParam("searchAnimals") String searchAnimals,
			@RequestParam("searchArea") String searchArea, Model model) {

		// select
		List<Keyword> keywordList = postBO.getKeywordListByTitleStatusAnimalsArea(searchTitle, searchStatus,
				searchAnimals, searchArea);

		// 응답
		model.addAttribute("keywordList", keywordList);
		return "post/LookfamilyListSearch";

	}

	/**
	 * 입양후기 search view
	 * 
	 * @param searchTitle
	 * @param searchAnimals
	 * @param searchStatus
	 * @param model
	 * @return
	 */
	@GetMapping("/adopt_search_list_view")
	public String adoptSearchList(@RequestParam(value = "searchTitle", required = false) String searchTitle,
			@RequestParam("searchAnimals") String searchAnimals, @RequestParam("searchStatus") String searchStatus,
			Model model) {

		// select
		List<Keyword> keywordList = postBO.getKeywordAdoptListByTitleStatusAnimals(searchTitle, searchAnimals,
				searchStatus);

		// 응답
		model.addAttribute("keywordList", keywordList);
		return "post/adopt/reviewSearch";

	}

	/**
	 * 가족을 찾습니다 상세화면 view
	 * 
	 * @param postId
	 * @param model
	 * @param session
	 * @return
	 */
	@GetMapping("/look_for_family_detail_view")
	public String familyDetailView(@RequestParam("postId") int postId, Model model, HttpSession session) {

		// 세션에서 userId가져오기
		Integer userId = (Integer) session.getAttribute("userId");
		if (userId == null) {
			return "redirect:/user/signIn_view";
		}
		// 1. DB select -userId, postId
		List<PostView> postViewList = postBO.getPostByPostIdUserId(postId, userId);
		model.addAttribute("postView", postViewList);
		// 화면 이동
		model.addAttribute("veiwName", "post/LookFamilyDetail");
		return "template/layout";

	}

	/**
	 * 무료나눔 상세화면 view
	 * 
	 * @param postId
	 * @param model
	 * @param session
	 * @return
	 */
	@GetMapping("/free_sharing_detail_view")
	public String adoptReviewDetailView(@RequestParam("postId") int postId, Model model, HttpSession session) {

		// 세션에서 userId가져오기
		Integer userId = (Integer) session.getAttribute("userId");
		if (userId == null) {
			return "redirect:/user/signIn_view";
		}
		// 1. DB select -userId, postId
		List<PostView> postViewList = postBO.getPostByPostIdUserId(postId, userId);
		model.addAttribute("postView", postViewList);
		// 화면 이동
		model.addAttribute("veiwName", "post/sharing/freeDetail");
		return "template/layout";
	}

	/**
	 * 게시글 수정하기 view
	 * 
	 * @param postId
	 * @param session
	 * @param model
	 * @return
	 */
	@GetMapping("/update_view")
	public String updateView(@RequestParam("postId") int postId, HttpSession session, Model model) {

		// 세션에서 userId가져오기
		Integer userId = (Integer) session.getAttribute("userId");
		// 필요한 post 가져오기
		List<PostView> postViewList = postBO.getPostByPostIdUserId(postId, userId);
		model.addAttribute("postView", postViewList);

		model.addAttribute("veiwName", "post/updatePost");
		return "template/layout";

	}

	@GetMapping("/review_update_view")
	public String reviewUpdateView(@RequestParam("postId") int postId, HttpSession session, Model model) {

		// 세션에서 userId가져오기
		Integer userId = (Integer) session.getAttribute("userId");
		// 필요한 post 가져오기
		List<PostView> postViewList = postBO.getPostByPostIdUserId(postId, userId);
		model.addAttribute("postView", postViewList);

		model.addAttribute("veiwName", "post/adopt/reviewUpdatePost");
		return "template/layout";

	}

	/**
	 * 무료나눔 수정하기 view
	 * 
	 * @param postId
	 * @param session
	 * @param model
	 * @return
	 */
	@GetMapping("/free_update_view")
	public String freeUpdateView(@RequestParam("postId") int postId, HttpSession session, Model model) {

		// 세션에서 userId가져오기
		Integer userId = (Integer) session.getAttribute("userId");
		// 필요한 post 가져오기
		List<PostView> postViewList = postBO.getPostByPostIdUserId(postId, userId);
		model.addAttribute("postView", postViewList);

		model.addAttribute("veiwName", "post/sharing/freeUpdatePost");
		return "template/layout";

	}

	/**
	 * 동물 병원 리스트 view
	 * 
	 * @param model
	 * @return
	 */
	@GetMapping("/hospital_list_view")
	public String hospitalListView(Model model) {

		model.addAttribute("veiwName", "post/hospital/list");
		return "template/layout";
	}

}
