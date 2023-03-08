package com.pawin.post.bo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.pawin.comment.bo.CommentBO;
import com.pawin.comment.model.CommentView;
import com.pawin.common.FileManagerService;
import com.pawin.likeadopt.bo.LikeadoptBO;
import com.pawin.likeadopt.model.AdoptView;
import com.pawin.likeadopt.model.LikeView;
import com.pawin.post.dao.PostDAO;
import com.pawin.post.model.ImagePathView;
import com.pawin.post.model.Keyword;
import com.pawin.post.model.Post;
import com.pawin.post.model.PostView;
import com.pawin.user.bo.UserBO;
import com.pawin.user.model.User;

@Service
public class PostBO {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	private static final int POST_MAX_SIZE = 9;  // 한 페이지에 몇개씩 있게 할 것인지 상수
	
	@Autowired
	private PostDAO postDAO;

	@Autowired
	private PostImageBO postImageBO;

	@Autowired
	private UserBO userBO;

	@Autowired
	private CommentBO commentBO;
	
	@Autowired
	private LikeadoptBO likeAdoptBO;
	
	@Autowired
	private FileManagerService fileManagerService;
	
	
	public List<Post> getPostList() {
		return postDAO.selectPostList();
	}
	
	public List<Post> getAdoptPostList(){
		return postDAO.selectAdoptPostList();
	}
	
	public List<Post> getStatusPostList(){
		return postDAO.selectStatusPostList();
	}
	
	public List<Post> getAdoptPostListByPostId(int postId) {
		return postDAO.selectAdoptPostListByPostId(postId);
	}
	
	public List<Post> getPostListByPostId(int postId) {
		return postDAO.selectPostListByPostId(postId);
	}
	
	public List<Post> getPostByPostListIdUserId(int postId, int userId) {
		return postDAO.selectPostListByPostIdUserId(postId, userId);
	}
	

	// 글 디테일 내용
	public List<PostView> getPostByPostIdUserId(int postId, int userId) {

		List<PostView> postViewList = new ArrayList<>();
		// 글 목록 가져오기(post)
		List<Post> postList = getPostListByPostId(postId);

		// 1)postview 리스트를 글 하나하나 뽑는 반복문 만들기
		for (int i = 0; i < postList.size(); i++) {

			PostView postView = new PostView();
			// 2) postview세팅
			if (postList.get(i).getId() == postId) {
				postView.setPost(postList.get(i));
				// 이미지
				List<ImagePathView> imagePathList = postImageBO.generateImagePathViewLsitByPostId(postList.get(i).getId());
				postView.setImagePathList(imagePathList);
				
				//user
				User user = userBO.getUserById(postList.get(i).getUserId());
				postView.setUser(user);
				
				// 댓글
				List<CommentView> commentViewList =commentBO.generateCommentList(postList.get(i).getId());
				postView.setCommentViewList(commentViewList);
				
				
				// 좋아요 입양 눌렀는지 
				postView.setFiledLike(likeAdoptBO.existLikeAdopt(postList.get(i).getId(), userId, "like"));
				postView.setFiledAdopt(likeAdoptBO.existLikeAdopt(postList.get(i).getId(), userId, "adopt"));
				
				// 좋아요 입양 갯수
				postView.setLikeCount(likeAdoptBO.getLikeadoptCountByPostId(postList.get(i).getId(), "like"));
				postView.setAdoptCount(likeAdoptBO.getLikeadoptCountByPostId(postList.get(i).getId(), "adopt"));
				
				// 누른 사람 리스트
				List<LikeView> likeViewList = likeAdoptBO.generateLikeViewList(postId, "like");
				postView.setLikeViewList(likeViewList);
				List<AdoptView> adoptViewList = likeAdoptBO.generateAdoptViewList(postId, "adopt");
				postView.setAdoptViewList(adoptViewList);
				// 누른 사람 카운트
				
				// 리스트
				postViewList.add(postView);
			}
		}
		return postViewList;

	}

	// 글쓰기 insert
	public void addPost(Post post, List<MultipartFile> files, int userId) {

		// 1. 글 등록
		post.setUserId(userId);
		postDAO.insertPost(post);
		User user = userBO.getUserById(userId);
		// 2 . 이미지 업로들을 내 컴퓨터에 업로드
		postImageBO.addPost(files, userId, user.getLoginId(), post.getId());
	}
	
	// 글 update
	public void updatePost(int postId, String title, String content,
			String status, String animals,String area,
			List<MultipartFile> files, int userId) {
		// post 글 하나 가져오기.
		List<Post> postList = getPostListByPostId(postId);
		for (Post post : postList) {
			if (post == null) {
				logger.warn("=========[update post] 수정할 메모가 존재하지 않습니다. postId:{}, userId:{}", postId, userId);
				return;
			}
			
		}
		postDAO.updatePost(postId, title, content, status, animals, area);
		User user = userBO.getUserById(userId);
		postImageBO.updateImage(files, userId, user.getLoginId(), postId);
		
		
	}
	
	 // 글 delete
	public int deletePostByPostIdUserId(int postId, int userId) {
		
		List<Post> postList = getPostListByPostId(postId);
		for (Post post : postList) {
			if (post == null) {
				logger.warn("[글 삭제] post is null. postId:{}, userId{}", postId, userId);
				return 0;
			}
			User user = userBO.getUserById(userId);
			// 이미지 삭제
			postImageBO.deleteImage(null, userId, user.getLoginId(), postId);
			// 댓글 삭제
			commentBO.deleteCommentListByPostId(userId, postId, null);
			// 좋아요 삭제
			likeAdoptBO.deleteLikeAdopt(postId, userId);
			
		}
		return postDAO.deletePostByPostIdUserId(postId, userId);
	}

	
	

	// 글 목록 보여주기
	public List<PostView> generatePostList(Integer userId) {

		List<PostView> postViewList = new ArrayList<>();

		// 글 목록 가져오기(post)
		List<Post> postsList = getPostList();

		// 1)post 리스트를 글 하나하나 뽑는 반복문 만들기
		for (int i = 0; i < postsList.size(); i++) {
			// 2) postview세팅
			PostView postView = new PostView();
			if (!postsList.get(i).getStatus().contains("입양완료")
					&& !postsList.get(i).getStatus().contains("장난감") && !postsList.get(i).getStatus().contains("옷")
					&& !postsList.get(i).getStatus().contains("기타")) {
				
				// 글
				postView.setPost(postsList.get(i));
				// 이미지 파일들 첫장만 꺼내기
				List<ImagePathView> imagePathList = postImageBO.generateImagePathViewLsitByPostId(postsList.get(i).getId());
				postView.setImagePathList(imagePathList);
				
				// user
				User user = userBO.getUserById(userId);
				postView.setUser(user);
				
				// 3) postviewList에 넣기
				postViewList.add(postView);
			}
		}

		return postViewList;
	}
	
	// 입양 글 목록 보여주기
		public List<PostView> generateAdoptPostList(Integer userId) {

			List<PostView> postViewList = new ArrayList<>();

			// 글 목록 가져오기(post)
			List<Post> postsList = getAdoptPostList();

			// 1)post 리스트를 글 하나하나 뽑는 반복문 만들기
			for (int i = 0; i < postsList.size(); i++) {
				// 2) postview세팅
				PostView postView = new PostView();
				
				// 글
				postView.setPost(postsList.get(i));
				// 이미지 파일들 첫장만 꺼내기
				List<ImagePathView> imagePathList = postImageBO.generateImagePathViewLsitByPostId(postsList.get(i).getId());
				postView.setImagePathList(imagePathList);
				
				// user
				User user = userBO.getUserById(userId);
				postView.setUser(user);
				
				// 3) postviewList에 넣기
				postViewList.add(postView);
			}

			return postViewList;
		}
	
	// 무료나눔 글 보여주기
		public List<PostView> generateSharingPostList(Integer userId) {

			List<PostView> postViewList = new ArrayList<>();

			// 글 목록 가져오기(post)
			List<Post> postsList = getStatusPostList();

			// 1)poㄲst 리스트를 글 하나하나 뽑는 반복문 만들기
			for (int i = 0; i < postsList.size(); i++) {
				// 2) postview세팅
				PostView postView = new PostView();
					
					// 글
					postView.setPost(postsList.get(i));
					// 이미지 파일들 첫장만 꺼내기
					List<ImagePathView> imagePathList = postImageBO.generateImagePathViewLsitByPostId(postsList.get(i).getId());
					postView.setImagePathList(imagePathList);
					
					// user
					User user = userBO.getUserById(userId);
					postView.setUser(user);
					
					// 3) postviewList에 넣기
					postViewList.add(postView);
					
				}
			

			return postViewList;
		}

	// 글 검색하기
	public List<Keyword> getKeywordListByTitleStatusAnimalsArea(String searchTitle, String searchStatus,
			String searchAnimals, String searchArea) {

		List<Keyword> keywordList = new ArrayList<>();

		postDAO.selectKeywordListByTitleStatusAnimalsArea(searchTitle, searchStatus, searchAnimals, searchArea);
		// 글 목록 가져오기(post)
		List<Post> postList = getPostList();
		for (Post post : postList) {
			Keyword keyword = new Keyword();
			// 서치 내용 가져오기
			keyword.setPost(post);
			
			if (post.getTitle().contains(searchTitle) && post.getStatus().equals(searchStatus) && post.getAnimals().equals(searchAnimals) && post.getArea().equals(searchArea) ) {

				keyword.setSearchTitle(post.getTitle());
				keyword.setSearchStatus(post.getStatus());
				keyword.setSearchAnimals(post.getAnimals());
				keyword.setSearchArea(post.getArea());
				List<ImagePathView> imagePathList = postImageBO.generateImagePathViewLsitByPostId(post.getId());
				keyword.setImagePathView(imagePathList);
				keywordList.add(keyword);
			} 
		}
		return keywordList;
	}
	
	// 글 검색하기
		public List<Keyword> getKeywordAdoptListByTitleStatusAnimals(String searchTitle,
				String searchAnimals, String searchStatus) {

			List<Keyword> keywordList = new ArrayList<>();

			postDAO.selectKeywordAdoptListByTitleStatusAnimals(searchTitle, searchAnimals, searchStatus);
			// 글 목록 가져오기(post)
			List<Post> postList = getPostList();
			for (Post post : postList) {
				Keyword keyword = new Keyword();
				// 서치 내용 가져오기
				keyword.setPost(post);
				
				if (post.getTitle().contains(searchTitle) && post.getStatus().equals(searchStatus) && post.getAnimals().equals(searchAnimals)) {

					keyword.setSearchTitle(post.getTitle());
					keyword.setSearchAnimals(post.getAnimals());
					keyword.setSearchStatus(post.getStatus());
					List<ImagePathView> imagePathList = postImageBO.generateImagePathViewLsitByPostId(post.getId());
					keyword.setImagePathView(imagePathList);
					keywordList.add(keyword);
				} 
			}
			return keywordList;
		}
	
		//페이징
		public List<Post> getPostListByUserId(int userId, Integer prevId, Integer nextId){//서비스를 만들 때는 객체로 해야된다.
			// 게시글 번호 :  10 9 8 | 7 6 5| 4 3 2| 1
			// 만약 4 3 2 페이지에 있을 때 
			// 1) 이전 : 정방향ASC 4보다 큰 3개 (5 6 7) = > 뿌려질 땐 7 6 5 => List reverse
			// 2) 다음 :  2보다 작은 3개 DESC 
			// 3) 첫 페이지 (이전, 다음 없음) : DESC 3개
			String direction = null; // 방향
			Integer standardId = null; // 기준 postId
			
			if (prevId != null) { // 여기에 오면 이전
				direction = "prev";
				standardId = prevId;
				
				List<Post> postList = postDAO.selectPostListByUserId(userId, direction, standardId, POST_MAX_SIZE);
				Collections.reverse(postList); // 리스트를 뒤집는 메소드 (바꿔놓고 저장까지 해줌:void)
				return postList;
			} else if (nextId != null) { // 다음
				direction = "next";
				standardId = nextId;
			} 
			// 첫 페이지 일 때 ( 페이징 안 함)  standardId, direction == null
			// 다음일 때 standardId, directionId 채워져서 넘어감
			return postDAO.selectPostListByUserId(userId, direction, standardId, POST_MAX_SIZE);
			
		}
		
		public boolean isPrevLastPage(int prevId, int userId) {
			int maxPostId = postDAO.selectPostIdByUserIdSort(userId, "DESC");
			return maxPostId == prevId ? true : false;
		}
		
		public boolean isNextLastPage(int nextId, int userId) {
			int minPostId = postDAO.selectPostIdByUserIdSort(userId, "ASC");
			return minPostId == nextId ? true : false;
		}

}
