package com.pawin.post.model;

import java.util.List;

import com.pawin.user.model.User;

// 글 한개와 매핑
public class PostView {

	
	// post 
	private Post post;
	
	// 이미지 파일들
	private List<ImagePathView> imagePathList;

	// 글쓴이
	private User user;
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Post getPost() {
		return post;
	}
	public void setPost(Post post) {
		this.post = post;
	}

	public List<ImagePathView> getImagePathList() {
		return imagePathList;
	}
	public void setImagePathList(List<ImagePathView> imagePathList) {
		this.imagePathList = imagePathList;
	}
	
	
	
}
