package com.pawin.post.model;

import java.util.List;

public class Keyword {
	
	private Post post;
	private List<ImagePathView> imagePathView;
	private String searchTitle;
	private String searchStatus;
	private String searchAnimals;
	private String searchArea;
	
	
	public List<ImagePathView> getImagePathView() {
		return imagePathView;
	}
	public void setImagePathView(List<ImagePathView> imagePathView) {
		this.imagePathView = imagePathView;
	}
	public Post getPost() {
		return post;
	}
	public void setPost(Post post) {
		this.post = post;
	}
	public String getSearchTitle() {
		return searchTitle;
	}
	public void setSearchTitle(String searchTitle) {
		this.searchTitle = searchTitle;
	}
	public String getSearchStatus() {
		return searchStatus;
	}
	public void setSearchStatus(String searchStatus) {
		this.searchStatus = searchStatus;
	}
	public String getSearchAnimals() {
		return searchAnimals;
	}
	public void setSearchAnimals(String searchAnimals) {
		this.searchAnimals = searchAnimals;
	}
	public String getSearchArea() {
		return searchArea;
	}
	public void setSearchArea(String searchArea) {
		this.searchArea = searchArea;
	}
	
	

}
