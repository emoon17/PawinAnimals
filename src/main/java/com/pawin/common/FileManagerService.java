package com.pawin.common;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component  // 일반적인 스트링 빈. Autowired로 가져와서 쓸 수 있음.
public class FileManagerService {
										
	private Logger logger =  LoggerFactory.getLogger(this.getClass());
	
										// 집 C:\JAVA_EUNHYE\7.pawinAnimals\Pawin\workspace\image/
	// 실제 이미지가 저장될 경로(내컴퓨터 서버) // 학원 : D:\jungeunhye\7.Pawin Animals\workspace\image
	public static final String FILE_UPLOAD_PATH = "C:\\JAVA_EUNHYE\\7.pawinAnimals\\Pawin\\workspace\\image/";
	
	//실제 업로드하는 메소드
	//input : MultipartFile, loginId
	//output : imagePath
	public String saveFile(String loginId, MultipartFile file) {
		// loginId를 BO로 가져온 이유 : 사람마다 파일을 만드려고.
		// 파일 디렉토리 예) aaaa_16205468768/sun.png (중복이 생기면 안됌)
		String directoryName = loginId + "_" + System.currentTimeMillis() + "/"; //aaaa_16205468768/sun.png
		String filePath = FILE_UPLOAD_PATH + directoryName;  //D:\\jungeunhye\\6.Spring_project\\memo\\workspac
		
		File directory = new File(filePath); // 파일을 만들어 낼 준비(명시)
		if (directory.mkdir() == false) {
			// 폴더 만드는데에 실패했으면 이미지패스는 null이된다.
			return null;
		}
		
		// 파일 업로드 : byte 단위로 업로드 된다.
		try {
			byte[] bytes = ((MultipartFile) file).getBytes();
			Path path = Paths.get(filePath + ((MultipartFile) file).getOriginalFilename()); // OriginalFilename:사용자가 올린 파일명
			// 한글은 안 올라감. 한글 올리고 싶을 땐 확장자를 분해하고 이름은""스트링으로감싸서 내가 만들어야한다.
			Files.write(path, bytes); // 진짜로 업로드 하는 순간
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		
		// 파일 업로드를 성공했으면 이미지url path를 리턴한다 (아직 매핑 안한 상태라 경로는 안 만듬 - 1번까지 한 상태)
		// 주소가 이렇게 될 거라고 예상하는 것.
		// http://localhost/images/aaaa_16205468768/sun.png
		return "/images/" + directoryName + ((MultipartFile) file).getOriginalFilename();
	}
	
	
	
	// 이미지 제거 하는 메소드
		public void deleteFile(String imagePath) { // imagePath: /images/aaaa_16205468768/sun.png
			// 폴더에 있는 이미지를 찾아서 path로 만들어야한다.
			//         \\imges/    imagePath에 있는 겹치는 /images/  구문 제거  
			Path path = Paths.get(FILE_UPLOAD_PATH +  imagePath.replace("/images/", ""));
			if (Files.exists(path)) {
				// 이미지 삭제
				try {
					Files.delete(path);
				} catch (IOException e) {
					logger.error("[이미지삭제] 이미지 삭제 실패. imagePath:{}", imagePath);
				}
				
				// 디렉토리(폴더) 삭제
				path = path.getParent(); // 이미지의 폴더로 올라간다.
				if (Files.exists(path)) {
					try {
						Files.delete(path);
					} catch (IOException e) {
						logger.error("[이미지 디렉토리0 삭제] 디렉토리 삭제 실패. imagePath:{}", imagePath);
					}
				}
			}
	
		}



		
}


