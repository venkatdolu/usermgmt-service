package com.wipro.usermgmt.service;

import java.util.List;

import com.wipro.usermgmt.entities.Role;
import com.wipro.usermgmt.entities.User;

public interface MiscellaneousOperations {

	User getUserById(Long id);

	List<Role> getAllRoles();

	Role getRoleById(Long id);

	Boolean verifyActivationToken(String code, Long id);

	User getUserByName(String name);

}
