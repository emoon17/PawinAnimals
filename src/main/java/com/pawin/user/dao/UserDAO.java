package com.pawin.user.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDAO {

	public boolean existLoginId(String loginId);
	
	public void insertUser(
			@Param("name") String name, 
			@Param("loginId") String loginId,
			@Param("password") String password, 
			@Param("phoneNumber") String phoneNumber);
}
