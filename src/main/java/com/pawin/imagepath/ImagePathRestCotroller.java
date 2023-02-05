package com.pawin.imagepath;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.pawin.imagepath.bo.ImagePathBO;
import com.pawin.imagepath.model.Imagepath;

import jakarta.servlet.http.HttpSession;
@RestController
public class ImagePathRestCotroller {
	
	@Autowired
	private ImagePathBO imagePathBO;
	
	@PostMapping("/imagePath/upload_image")
	public String imagePath(
			@RequestPart(value="file",required = false) List<MultipartFile> files,
			HttpSession session){
		Imagepath imagepath = new Imagepath();
		
		// 응답
		return "uploaded";
		
	}

}
