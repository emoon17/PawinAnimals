package com.pawin.likeadopt.bo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pawin.likeadopt.dao.LikeadoptDAO;
import com.pawin.likeadopt.model.AdoptView;
import com.pawin.likeadopt.model.LikeView;
import com.pawin.likeadopt.model.Likeadopt;
import com.pawin.user.bo.UserBO;
import com.pawin.user.model.User;

@Service
public class LikeadoptBO {

	@Autowired
	private LikeadoptDAO likeadoptDAO;

	@Autowired
	private UserBO userBO;

	public boolean existLike(int postId, Integer userId, String type) {
		// 비로그인 아웃
		if (userId == null) {
			return false;
		}

		// 로그인
		if (type.equals("like")) {
			likeadoptDAO.selectLikeadoptCountByPostOrUserId(postId, userId, type);
		}
		return likeadoptDAO.selectLikeadoptCountByPostOrUserId(postId, userId, type) > 0? true : false;
	}
	
	public boolean existAdopt(int postId, Integer userId, String type) {
		// 비로그인 아웃
		if (userId == null) {
			return false;
		}

		// 로그인
		if (type.equals("adopt")) {
			likeadoptDAO.selectLikeadoptCountByPostOrUserId(postId, userId, type);
		}
		return likeadoptDAO.selectLikeadoptCountByPostOrUserId(postId, userId, type) > 0? true : false;
	}

	public int getLikeadoptCountByPostId(int postId, int userId, String type) {
		// 행의 갯수 가져오기 - postBO에서 쓸려고 만듬
		return likeadoptDAO.selectLikeadoptCountByPostOrUserId(postId, userId, type);
	}

	public void deleteLikeadoptByPostIdUserId(int postId, int userId, String type) {
		likeadoptDAO.deleteLikeadoptByPostIdUserId(postId, userId, type);
	}

	public List<Likeadopt> getLikeAdoptList(int postId) {

		return likeadoptDAO.selectLikeAdoptList(postId);
	}

	public void likeadoptTogle(int postId, int userId, String loginId, String type) { // 버튼 클릭 유무에 따른 추가, 제거

		if (likeadoptDAO.selectLikeadoptCountByPostOrUserId(postId, userId, type) > 0) {
			// 있으면 제거
			likeadoptDAO.deleteLikeadoptByPostIdUserId(postId, userId, type);
		} else {
			// 없으면 추가
			likeadoptDAO.insertLikeadoptByPostIdUserId(postId, userId, loginId, type);
		}

	}

	public List<LikeView> generateLikeViewList(int postId, int userId, String type) {

		List<LikeView> likeViewList = new ArrayList<>();

		List<Likeadopt> likeadoptList = getLikeAdoptList(postId);

		for (Likeadopt likeadopt : likeadoptList) {
			LikeView likeView = new LikeView();
			
			likeView.setLikeadopt(likeadopt);
			if (likeView.getLikeadopt().getType().equals("like")) { //likeView.likeadopt.type == "adopt"
				
				//사용자
				User user = userBO.getUserById(likeadopt.getUserId());
				likeView.setUser(user);
				
				likeViewList.add(likeView);
			}
		}
		return likeViewList;

	}

	public List<AdoptView> generateAdoptViewList(int postId, int userId, String type) {

		List<AdoptView> AdoptViewList = new ArrayList<>();

		List<Likeadopt> likeadoptList = getLikeAdoptList(postId);

		for (Likeadopt likeadopt : likeadoptList) {
			AdoptView adoptView = new AdoptView();
			
			adoptView.setLikeadopt(likeadopt);
			if (adoptView.getLikeadopt().getType().equals("adopt")) {
				
				//사용자
				User user = userBO.getUserById(likeadopt.getUserId());
				adoptView.setUser(user);
				
				AdoptViewList.add(adoptView);
			}
		}
		return AdoptViewList;

	}

}
