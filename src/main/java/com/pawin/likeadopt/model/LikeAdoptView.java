package com.pawin.likeadopt.model;

import com.pawin.user.model.User;

public class LikeAdoptView {

	private Likeadopt likeadopt;
	
	
	private User user;
	
	private boolean filedLikeAdopt;


	public boolean isFiledLikeAdopt() {
		return filedLikeAdopt;
	}

	public void setFiledLikeAdopt(boolean filedLikeAdopt) {
		this.filedLikeAdopt = filedLikeAdopt;
	}

	public Likeadopt getLikeadopt() {
		return likeadopt;
	}

	public void setLikeadopt(Likeadopt likeadopt) {
		this.likeadopt = likeadopt;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
}
