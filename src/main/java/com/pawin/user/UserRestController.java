package com.pawin.user;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pawin.common.EncrypUtils;
import com.pawin.user.bo.UserBO;
import com.pawin.user.model.User;

import jakarta.servlet.http.HttpSession;
@RequestMapping("/user")
@RestController
public class UserRestController {

	@Autowired
	private UserBO userBO;
	
	/**
	 *  아이디 중복확인 API
	 * @param loginId
	 * @return
	 */
	@PostMapping("/is_duplicated_id")
	public Map<String, Object> isDuplicatedId(
			@RequestParam("loginId") String loginId){
		
		// select
		Map<String, Object> result = new HashMap<>();
		boolean isDuplicated = userBO.existLoginId(loginId);
		
		// 코드 나누기
		if (isDuplicated) { // 중복일 떄
			result.put("code", 1);
			result.put("result", true);
		} else { // 중복이 아닐 떄
			result.put("result", false);
		}
		
		
		//응답
		return result;
	}
	
	/**
	 *  회원가입 API
	 * @param name
	 * @param loginId
	 * @param password
	 * @param phoneNumber
	 * @return
	 */
	@PostMapping("/sign_up")
	public Map<String, Object> signUp(
			@RequestParam("name") String name,
			@RequestParam("loginId") String loginId,
			@RequestParam("password") String password,
			@RequestParam("phoneNumber") String phoneNumber){
		
		// 비밀번호 해싱
		String HashedPassword = EncrypUtils.md5(password);
		
		// db insert
		userBO.addUser(name, loginId, HashedPassword, phoneNumber);
		
		// 코드 
		Map<String, Object> result = new HashMap<>();
		result.put("code", 1);
		// 응답 내리기
		return result;
	}
	

	/**
	 *  회원가입 API
	 * @param loginId
	 * @param password
	 * @param session
	 * @return
	 */
	  @PostMapping("/sign_in") 
	  public Map<String, Object> signIn(
			  	@RequestParam("loginId") String loginId,
				@RequestParam("password") String password,
				HttpSession session){
		  
		  // 비밀번호 해싱하기
		  String HashedPassword = EncrypUtils.md5(password);
		  
		  // db select
		  User user = userBO.getUserByLoginIdPassword(loginId, HashedPassword);
		  
		  //  코드 구분, 세션으로 user id, name, loginId, phone 담아놓기
		  Map<String, Object> result = new HashMap<>();
		  if (user != null) {
			  result.put("code", 1);
			  session.setAttribute("userId", user.getId());
			  session.setAttribute("name", user.getName());
			  session.setAttribute("loginId", user.getLoginId());
			  session.setAttribute("phoneNumber", user.getPhoneNumber());
		  } else {
			  result.put("errorMessage", "관리자에게 문의하세요");
		  }
		  
		  // 응답내리기
		  return result;
	  }
	 
	
	
	
	
}
