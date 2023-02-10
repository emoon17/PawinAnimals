package com.pawin.post.model;

public class ImagePathView {

	// 사진에 대한 매핑
	
	private ImagePath imagePath;
	
	private Post post;

	

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
	
}
