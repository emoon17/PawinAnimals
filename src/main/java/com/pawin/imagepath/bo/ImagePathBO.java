package com.pawin.imagepath.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.pawin.common.FileManagerService;
import com.pawin.imagepath.dao.ImagePathDAO;

@Service
public class ImagePathBO {

	@Autowired
	private ImagePathDAO imagePathDAO;
	
	@Autowired
	private FileManagerService fileManagerService;
	
	public void addPost(List<MultipartFile> files, int userId, int postId, String loginId) {
		
		// 파일 업로드 => 내 컴퓨터 서버에만 올린다. 경로로 보여지기.
		String imagePath = null;
		if (files != null) {
			// 파일이 있을 때만 업로드 -> 이미지 경로를 얻어냄
			imagePath = fileManagerService.saveFile(loginId, files);
		}
		
		imagePathDAO.insertPost(imagePath, userId, postId);
	}
}
