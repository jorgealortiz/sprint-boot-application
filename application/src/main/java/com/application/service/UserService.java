package com.application.service;

import com.application.entity.User;
import com.application.model.ChangePasswordForm;

public interface UserService {

	public Iterable<User> getAllUsers();

	public User createUser(User user) throws Exception;

	public User findById(Long id) throws Exception;
	
	public User updateUser(User user) throws Exception;

    public void deleteUser(Long id) throws Exception;
    
    public User changePassword(ChangePasswordForm form) throws Exception;

}
