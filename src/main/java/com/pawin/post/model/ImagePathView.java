package com.pawin.post.model;

import com.pawin.user.model.User;

public class ImagePathView {

	// 사진에 대한 매핑
	
	private ImagePath imagePaths;
	
	private Post post;
	
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

	public ImagePath getImagePaths() {
		return imagePaths;
	}

	public void setImagePaths(ImagePath imagePaths) {
		this.imagePaths = imagePaths;
	}
	
	
	
}
