package com.application.service;

import com.application.entity.User;

public interface UserService {

	public Iterable<User> getAllUsers();

	public User createUser(User user) throws Exception;

	public User findById(Long id) throws Exception;
	
	public User updateUser(User user) throws Exception;

}
