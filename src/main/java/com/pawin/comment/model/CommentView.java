package com.pawin.comment.model;

import com.pawin.user.model.User;

public class CommentView {

	private Comment comment;
	
	private User user;

	public Comment getComment() {
		return comment;
	}

	public void setComment(Comment comment) {
		this.comment = comment;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
}
