package com.pawin.post.model;

import java.util.List;

import com.pawin.comment.model.CommentView;
import com.pawin.likeadopt.model.AdoptView;
import com.pawin.likeadopt.model.LikeAdoptView;
import com.pawin.likeadopt.model.LikeView;
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
	private boolean filedLike;
	private boolean filedAdopt;
	
	// 좋아요, 입양희망자 리스트
	private List<LikeView> likeViewList;
	private List<AdoptView> adoptViewList;
	
	// 좋아요, 입양희망자 카운트
	private int likeAdoptCount;
	
	


	public boolean isFiledLike() {
		return filedLike;
	}
	public void setFiledLike(boolean filedLike) {
		this.filedLike = filedLike;
	}
	public boolean isFiledAdopt() {
		return filedAdopt;
	}
	public void setFiledAdopt(boolean filedAdopt) {
		this.filedAdopt = filedAdopt;
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
	public List<LikeView> getLikeViewList() {
		return likeViewList;
	}
	public void setLikeViewList(List<LikeView> likeViewList) {
		this.likeViewList = likeViewList;
	}
	public List<AdoptView> getAdoptViewList() {
		return adoptViewList;
	}
	public void setAdoptViewList(List<AdoptView> adoptViewList) {
		this.adoptViewList = adoptViewList;
	}
	
	
	
	
}
