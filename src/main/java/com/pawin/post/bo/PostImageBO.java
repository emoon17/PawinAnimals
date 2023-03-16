package com.pawin.post.bo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.pawin.common.FileManagerService;
import com.pawin.post.dao.PostImageDAO;
import com.pawin.post.model.ImagePath;
import com.pawin.post.model.ImagePathView;
import com.pawin.user.bo.UserBO;
import com.pawin.user.model.User;

@Service
public class PostImageBO {

	@Autowired
	private PostImageDAO postImageDAO;

	@Autowired
	private FileManagerService fileManagerService;

	@Autowired
	private UserBO userBO;

	public List<ImagePath> getImagePathListByPostId(int postId) {
		// 글번호에 해당 되는 id만 가져온다.
		return postImageDAO.selectImagePathListByPostId(postId);
	}

	public ImagePath getIamgePathByPostId(int postId) {
		return postImageDAO.selectIamgePathByPostId(postId);
	}

	public void addPost(List<MultipartFile> files, int userId, String loginId, int postId) {

		// 파일 업로드 => 내 컴퓨터 서버에만 올린다. 경로로 보여지기.
		String imagePath = null;
		if (files != null) {
			// 파일이 있을 때만 업로드 -> 이미지 경로를 얻어냄
			for (MultipartFile file : files) {
				imagePath = fileManagerService.saveFile(loginId, file);
				// 파일 갯수만큼 다오에 넣게 (n개)
				postImageDAO.insertImagePost(imagePath, userId, postId);
			}
		}
	}

	// 이미지 수정
	public void updateImage(List<MultipartFile> files, int userId, String loginId, int postId) {

		List<ImagePath> imagePathList = getImagePathListByPostId(postId);

		// 파일 업로드
		String imagePaths = null;
		if (files != null) {
			// 파일메니저 파일 갯수만큼 반복
			for (MultipartFile file : files) {
				imagePaths = fileManagerService.saveFile(loginId, file);
				postImageDAO.insertImagePost(imagePaths, userId, postId);
				for (ImagePath imagePath : imagePathList) {
					// 이미지 패스도 null, 기존 이미지패스도 null이 아니면
					if (imagePaths != null && imagePath.getImagePath() != null) {
						// 이미지 제거
						fileManagerService.deleteFile(imagePath.getImagePath());
						postImageDAO.deleteImage(userId, postId);
					}
				}
			}
		
			
		}

	}

	// 이미지 삭제
	public void deleteImage(List<MultipartFile> files, int userId, String loginId, int postId) {
		List<ImagePath> imagePathList = getImagePathListByPostId(postId);
		String imagePaths = null;
		if (files != null) {
			for (MultipartFile file : files) {
				imagePaths = fileManagerService.saveFile(loginId, file);
				for (ImagePath imagePath : imagePathList) {
					// 이미지 제거
					fileManagerService.deleteFile(imagePath.getImagePath());

				}
			}
		}
		postImageDAO.deleteImage(userId, postId);
	}

	public List<ImagePathView> generateImagePathViewLsitByPostId(int postId) {

		// 결과물을 담을 객체
		List<ImagePathView> imagePathViewList = new ArrayList<>();

		// 글 목록 가져오기
		List<ImagePath> imagePathList = getImagePathListByPostId(postId);
		// imagePathView 넣기
		for (ImagePath imagepath : imagePathList) {
			ImagePathView imagePathView = new ImagePathView();

			imagePathView.setImagePaths(imagepath);

			User user = userBO.getUserById(imagepath.getUserId());
			imagePathView.setUser(user);

			imagepath.setPostId(postId);

			// 결과물에 넣기
			imagePathViewList.add(imagePathView);

		}

		// 결과물 리턴
		return imagePathViewList;
	}

}
