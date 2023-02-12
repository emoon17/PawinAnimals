package com.pawin.post.bo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.pawin.post.dao.PostDAO;
import com.pawin.post.model.ImagePathView;
import com.pawin.post.model.Keyword;
import com.pawin.post.model.Post;
import com.pawin.post.model.PostView;
import com.pawin.user.bo.UserBO;

@Service
public class PostBO {
	
	@Autowired
	private PostDAO postDAO;
	
	 @Autowired
	private PostImageBO postImageBO;
	 
	 @Autowired
	 private UserBO userBO;
	 
	 @Autowired
	 
	 
	 
	 public List<Post> getPostList() {
			return postDAO.selectPostList();
	 }	
	
	// 글쓰기 insert
	public void addPost(Post post, List<MultipartFile> files, int userId) {
		
		// 1. 글 등록
		post.setUserId(userId);
		postDAO.insertPost(post);
		
		
		//2 . 이미지 업로들을 내 컴퓨터에 업로드
		postImageBO.addPost(files, userId, post.getStatus(), post.getId());
	}
	
	// 글 목록 보여주기
	public List<PostView> generatePostList(Integer userId ){
		
		List<PostView> postViewList = new ArrayList<>();
		
		//글 목록 가져오기(post)
		List<Post> postsList = getPostList();
		
		// 1)post 리스트를 글 하나하나 뽑는 반복문 만들기  
		for (int i = 0; i < postsList.size(); i++) {
			//2) postview세팅
			PostView postView = new PostView();
			
			// 글
			postView.setPost(postsList.get(i));
			// 이미지 파일들 첫장만 꺼내기
			List<ImagePathView> imagePathList = postImageBO.generateImagePathViewLsitByPostId(postsList.get(i).getId());
			postView.setImagePathList(imagePathList);
			
			//3) postviewList에 넣기
			postViewList.add(postView);
		}
		
		return postViewList;
	}
	
	// 글 검색하기
	public List<Keyword> getKeywordListByTitleStatusAnimalsArea(String searchTitle, String searchStatus, String searchAnimals, String searchArea){
		List<Keyword> keywordList = new ArrayList<>();
		
		List<Post> postList = getPostList();
		
		for (int i = 0; i < postList.size(); i++) {
			Keyword keyword = new Keyword();
			
		
			
			String getTitle = postList.get(i).getTitle();
			String getStatus = postList.get(i).getStatus();
			String getAnimals = postList.get(i).getAnimals();
			String getArea = postList.get(i).getArea();
			if (getTitle == searchTitle) {
				keyword.setSearchTitle(searchTitle);
			}
			if (getStatus == searchStatus){
				keyword.setSearchStatus(searchStatus);
			}
			if (getAnimals == searchAnimals) {
				keyword.setSearchAnimals(searchAnimals);
			}
			if (getAnimals == searchArea) {
				keyword.setSearchAnimals(searchArea);
			}
			keywordList.add(keyword);
		}
		
		return keywordList;
	}
	
}
