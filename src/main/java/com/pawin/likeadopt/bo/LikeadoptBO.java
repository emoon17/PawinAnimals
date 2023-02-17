package com.pawin.likeadopt.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pawin.likeadopt.dao.LikeadoptDAO;
import com.pawin.likeadopt.model.Likeadopt;

@Service
public class LikeadoptBO {

	@Autowired
	private LikeadoptDAO likeadoptDAO;
	
	public boolean existLikeadopt(int postId, Integer userId, String type) {
		// 비로그인 아웃
		if (userId == null) {
			return false;
		}
		
		// 로그인
		return likeadoptDAO.selectLikeadoptCountByPostOrUserId(postId, userId, type) > 0? true : false;
	}

	public List<Likeadot> getLikeAdoptList(){
		
	}
	
	public int getLikeadoptCountByPostId(int postId) {
		// 행의 갯수 가져오기 - postBO에서 쓸려고 만듬
		return likeadoptDAO.selectLikeadoptCountByPostOrUserId(postId, null, null);
	}
	
	public void deleteLikeadoptByPostIdUserId(int postId, int userId, String type) {
		likeadoptDAO.deleteLikeadoptByPostIdUserId(postId, userId, type);
	}
	
	public void likeadoptTogle(int postId, int userId, String loginId, String type) { // 버튼 클릭 유무에 따른 추가, 제거
		
		List<Likeadopt> likeAdoptList = 
		
		
		if (likeadoptDAO.selectLikeadoptCountByPostOrUserId(postId, userId, type) > 0 ) {
			//있으면 제거
			likeadoptDAO.deleteLikeadoptByPostIdUserId(postId, userId, type);
		} else {
			//없으면 추가
			likeadoptDAO.insertLikeadoptByPostIdUserId(postId, userId, loginId, type);
		}
	}
}
