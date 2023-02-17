package com.pawin.post.bo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.pawin.comment.bo.CommentBO;
import com.pawin.comment.model.CommentView;
import com.pawin.likeadopt.bo.LikeadoptBO;
import com.pawin.post.dao.PostDAO;
import com.pawin.post.model.ImagePathView;
import com.pawin.post.model.Keyword;
import com.pawin.post.model.Post;
import com.pawin.post.model.PostView;
import com.pawin.user.bo.UserBO;
import com.pawin.user.model.User;

@Service
public class PostBO {

	@Autowired
	private PostDAO postDAO;

	@Autowired
	private PostImageBO postImageBO;

	@Autowired
	private UserBO userBO;

	@Autowired
	private CommentBO commentBO;
	
	@Autowired
	private LikeadoptBO likeAdoptBO;
	
	
	public List<Post> getPostList() {
		return postDAO.selectPostList();
	}

	// 글 디테일 내용
	public List<PostView> getPostByPostIdUserId(int postId, Integer userId) {

		List<PostView> postViewList = new ArrayList<>();
		// 글 목록 가져오기(post)
		List<Post> postList = getPostList();

		// 1)postview 리스트를 글 하나하나 뽑는 반복문 만들기
		for (int i = 0; i < postList.size(); i++) {

			PostView postView = new PostView();
			// 2) postview세팅
			if (postList.get(i).getId() == postId) {
				postView.setPost(postList.get(i));
				// 이미지
				List<ImagePathView> imagePathList = postImageBO.generateImagePathViewLsitByPostId(postList.get(i).getId());
				postView.setImagePathList(imagePathList);
				
				//user
				User user = userBO.getUserById(postList.get(i).getUserId());
				postView.setUser(user);
				
				// 댓글
				List<CommentView> commentViewList =commentBO.generateCommentList(postList.get(i).getId());
				postView.setCommentViewList(commentViewList);
				
				// 좋아요 입양 눌렀는지 
				postView.setFiledLikeAdopt(likeAdoptBO.existLikeadopt(postId, userId, null));
				
				// 리스트
				
				
				postViewList.add(postView);
			}
		}
		return postViewList;

	}

	// 글쓰기 insert
	public void addPost(Post post, List<MultipartFile> files, int userId) {

		// 1. 글 등록
		post.setUserId(userId);
		postDAO.insertPost(post);

		// 2 . 이미지 업로들을 내 컴퓨터에 업로드
		postImageBO.addPost(files, userId, post.getStatus(), post.getId());
	}

	// 글 목록 보여주기
	public List<PostView> generatePostList(Integer userId) {

		List<PostView> postViewList = new ArrayList<>();

		// 글 목록 가져오기(post)
		List<Post> postsList = getPostList();

		// 1)post 리스트를 글 하나하나 뽑는 반복문 만들기
		for (int i = 0; i < postsList.size(); i++) {
			// 2) postview세팅
			PostView postView = new PostView();

			// 글
			postView.setPost(postsList.get(i));
			// 이미지 파일들 첫장만 꺼내기
			List<ImagePathView> imagePathList = postImageBO.generateImagePathViewLsitByPostId(postsList.get(i).getId());
			postView.setImagePathList(imagePathList);

			// 3) postviewList에 넣기
			postViewList.add(postView);
		}

		return postViewList;
	}

	// 글 검색하기
	public List<Keyword> getKeywordListByTitleStatusAnimalsArea(String searchTitle, String searchStatus,
			String searchAnimals, String searchArea) {

		List<Keyword> keywordList = new ArrayList<>();

		postDAO.selectKeywordListByTitleStatusAnimalsArea(searchTitle, searchStatus, searchAnimals, searchArea);
		// 글 목록 가져오기(post)
		List<Post> postList = getPostList();
		for (Post post : postList) {
			Keyword keyword = new Keyword();
			// 서치 내용 가져오기
			keyword.setPost(post);
			
			if (post.getTitle().contains(searchTitle) && post.getStatus().equals(searchStatus) && post.getAnimals().equals(searchAnimals) && post.getArea().equals(searchArea) ) {

				keyword.setSearchTitle(post.getTitle());
				keyword.setSearchStatus(post.getStatus());
				keyword.setSearchAnimals(post.getAnimals());
				keyword.setSearchArea(post.getArea());
				List<ImagePathView> imagePathList = postImageBO.generateImagePathViewLsitByPostId(post.getId());
				keyword.setImagePathView(imagePathList);
				keywordList.add(keyword);
			} 
		}
		return keywordList;
	}

}
