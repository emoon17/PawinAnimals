package com.pawin.post.model;

import com.pawin.user.model.User;

// 글 한개와 매핑
public class PostView {

	
	// post 
	private Post post;
	
	// 이미지 파일들
	private ImagePath imagePath;
	// 글쓴이
	private User user;
	// 댓글들 리스트
	
	
	// 좋아요들 - likeview만들어서 하기
	
	// 좋아요 갯수
	
	// adop 리스트
	
	// adop 갯수 -adopview 만들어서 하기
	
	
	
	public Post getPost() {
		return post;
	}
	public void setPost(Post post) {
		this.post = post;
	}
	public ImagePath getImagePath() {
		return imagePath;
	}
	public void setImagePath(ImagePath imagePath) {
		this.imagePath = imagePath;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
}
