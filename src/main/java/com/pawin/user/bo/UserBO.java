package com.pawin.user.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pawin.user.dao.UserDAO;
import com.pawin.user.model.User;

@Service
public class UserBO {

	@Autowired
	private UserDAO userDAO;

	public boolean existLoginId(String loginId) {
		return userDAO.existLoginId(loginId);
	}

	public void addUser(String name, String loginId, String password, String phoneNumber) {
		userDAO.insertUser(name, loginId, password, phoneNumber);
	}

	public User getUserByLoginIdPassword(String loginId, String password) {

		return userDAO.selectUserByLoginIdPassword(loginId, password);
	}

}
