package com.pawin.comment.bo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pawin.comment.dao.CommentDAO;
import com.pawin.comment.model.Comment;
import com.pawin.comment.model.CommentView;
import com.pawin.user.bo.UserBO;
import com.pawin.user.model.User;

@Service
public class CommentBO {

	@Autowired
	private CommentDAO commentDAO;
	
	@Autowired
	private UserBO userBO;
	
	public void addCommentByUserIdPostIdComment(int userId, int postId, String content) {
		commentDAO.insertCommentByUserIdPostIdComment(userId, postId, content);
		
	}
	
	public List<Comment> getCommentListByPostId(int postId){
		return commentDAO.selectCommentListByPostId(postId);
	}
	
	// 댓글 쓰기
	public List<CommentView> generateCommentList(int postId){ // 글 번호에 해당하는 댓글(정보) 목록을 가져온다.
		
		List<CommentView> commentViewList = new ArrayList<>();
		
		// 필요한 글 리스트 가져오기
		List<Comment> commentList = getCommentListByPostId(postId);
		
		for (Comment comment : commentList) {
			CommentView commentView = new CommentView();
			
			// 댓글 내용
			commentView.setComment(comment);
			// 댓글쓴이
			User user = userBO.getUserById(comment.getUserId());
			commentView.setUser(user);
			// 리스트에 담기
			commentViewList.add(commentView);
			
		}
		
		return commentViewList;
		
	}
	
	// 댓글삭제
	public void deleteCommentListByPostId(int userId, int postId, String content){
		commentDAO.deleteCommentListByPostId(userId, postId, content);
		
	}
	
	
}
