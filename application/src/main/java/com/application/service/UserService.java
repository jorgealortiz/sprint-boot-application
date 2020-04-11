package com.application.service;

import com.application.entity.User;

public interface UserService {

	Iterable<User> getAllUsers();

	User createUser(User user) throws Exception;

}
