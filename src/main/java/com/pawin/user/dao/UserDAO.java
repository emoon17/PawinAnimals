package com.pawin.user.dao;

import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

@Repository
public interface UserDAO {

	public boolean existLoginId(String loginId);
	
	public void insertUser(
			@RequestParam("name") String name, 
			@RequestParam("loginId") String loginId,
			@RequestParam("password") String password, 
			@RequestParam("phoneNumber") String phoneNumber);
}
