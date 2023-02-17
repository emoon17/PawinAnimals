package com.pawin.post.model;

import java.util.List;

import com.pawin.comment.model.CommentView;
import com.pawin.likeadopt.model.Likeadopt;
import com.pawin.user.model.User;

// 글 한개와 매핑
public class PostView {

	
	// post 
	private Post post;
	
	// 이미지 파일들
	private List<ImagePathView> imagePathList;

	// 글쓴이
	private User user;
	
	// 댓글
	private List<CommentView> commentViewList;
	
	// 내가 좋아요, 입양을 눌렀는지
	private boolean filedLikeAdopt;
	
	// 좋아요, 입양희망자 리스트
	private List<Likeadopt> likeAdoptLit;
	
	// 좋아요, 입양희망자 카운트
	public int likeAdoptCount;
	
	
	public boolean isFiledLikeAdopt() {
		return filedLikeAdopt;
	}
	public void setFiledLikeAdopt(boolean filedLikeAdopt) {
		this.filedLikeAdopt = filedLikeAdopt;
	}
	public List<Likeadopt> getLikeAdoptLit() {
		return likeAdoptLit;
	}
	public void setLikeAdoptLit(List<Likeadopt> likeAdoptLit) {
		this.likeAdoptLit = likeAdoptLit;
	}
	public int getLikeAdoptCount() {
		return likeAdoptCount;
	}
	public void setLikeAdoptCount(int likeAdoptCount) {
		this.likeAdoptCount = likeAdoptCount;
	}
	public List<CommentView> getCommentViewList() {
		return commentViewList;
	}
	public void setCommentViewList(List<CommentView> commentViewList) {
		this.commentViewList = commentViewList;
	}
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
