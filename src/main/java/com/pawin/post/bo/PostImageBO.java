package com.pawin.post.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.pawin.common.FileManagerService;
import com.pawin.post.dao.PostImageDAO;
@Service
public class PostImageBO {

	@Autowired
	private PostImageDAO postImageDAO;
	
	@Autowired
	private FileManagerService fileManagerService;
	
	public void addPost(List<MultipartFile> files, int userId, String loginId, int postId) {
		
		// 파일 업로드 => 내 컴퓨터 서버에만 올린다. 경로로 보여지기.
		String imagePath = null;
		if (files != null) {
			// 파일이 있을 때만 업로드 -> 이미지 경로를 얻어냄
			
			// 파일메니저 파일 갯수만큼 반복 
			
			for (MultipartFile file : files) {
				imagePath = fileManagerService.saveFile(loginId, file);
				// 파일 갯수만큼 다오에 넣게 (n개)
				postImageDAO.insertImagePost(imagePath, userId, postId);
			}
		}
	}
	
	//public List<ImagePath> getImagePathById
}
