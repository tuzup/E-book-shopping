package com.cognizant.ebook.dao;

import com.cognizant.ebook.model.User;

public interface UserDao {
	public long loginAuthentication(String email, String password);

	public void addUser(User user);

	public boolean isExistUser(String emailId);

}
