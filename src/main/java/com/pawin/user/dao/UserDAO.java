package com.pawin.user.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.pawin.user.model.User;

@Repository
public interface UserDAO {

	public boolean existLoginId(String loginId);
	
	public void insertUser(
			@Param("name") String name, 
			@Param("loginId") String loginId,
			@Param("password") String password, 
			@Param("phoneNumber") String phoneNumber);
	
	public User selectUserByLoginIdPassword(
			@Param("loginId") String loginId,
			@Param("password") String password);
	
	public User insertUserById(int userId);
}
