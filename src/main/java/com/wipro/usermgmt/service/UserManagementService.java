package com.wipro.usermgmt.service;

import java.util.List;

import com.wipro.usermgmt.entities.User;


public interface UserManagementService {

	List<User> getAllUser();

	User createUser(User user);

	User updateUser(User user);

	boolean deleteUser(Long id);

}
