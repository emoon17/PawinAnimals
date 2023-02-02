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
	
	@RequestMapping("/sign_up")
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
	
	/*
	 * @PostMapping("/sign_in") public Map<String, Object>
	 */
	
	
	
	
}
