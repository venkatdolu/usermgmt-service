package com.wipro.usermgmt.service.impl;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.wipro.usermgmt.entities.User;
import com.wipro.usermgmt.exceptions.UserMgmtException;
import com.wipro.usermgmt.repository.UserRepository;
import com.wipro.usermgmt.service.UserManagementService;

@Service
public class UserManagementServiceImpl implements UserManagementService {

	private static final Logger Log = LoggerFactory.getLogger(UserManagementServiceImpl.class);

	private UserRepository userRepository;

	@Autowired
	public void setUserRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
	}


	/**
	 * Description: function used to get users from db
	 * 
	 * @return
	 */
	@Override
	public List<User> getAllUser() {
		Log.info("getAllUser API invoked.");
		List<User> allUsers = userRepository.findAll();
		Log.info("end of getAllUser API.");
		return allUsers;
	}

	/**
	 * Description: function used to create user in db
	 * 
	 * @param user
	 * @return User
	 */
	@Override
	public User createUser(User user) {
		Log.info("createUser method invoked for username : {} ", user.getUserName());
		User createdUser = userRepository.save(user);
		Log.info("end of createUser method for username : {} ", user.getUserName());
		return createdUser;
	}

	/**
	 * Description: function used to update user in db
	 * 
	 * @param user
	 * @return
	 */
	@Override
	public User updateUser(User user) {
		Log.info("updateUser method invoked for username : {} ", user.getUserName());
		Optional<User> optionalUser = userRepository.findById(user.getId());
		User existingUser = null;
		if (optionalUser.isPresent()) {
			Log.info("existing user is present for the username: {}", user.getUserName());
			existingUser = optionalUser.get();
			existingUser.setPassword(user.getPassword());
			userRepository.save(existingUser);
		} else {
			throw new UserMgmtException("user does not exist in the system. please register the user.");
		}
		Log.info("end of updateUser method for username : {} ", user.getUserName());
		return existingUser;
	}

	/**
	 * Description: function used to delete user in db
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public boolean deleteUser(Long id) {
		Log.info("deleteUser method invoked for username : {} ", id);
		try {
			userRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new UserMgmtException("requested user id does not exist in the system");
		}
		Log.info("end of deleteUser method for username : {} ", id);
		return true;
	}

	/**
	 * Description: used to verify activtion link
	 */
	@Override
	public Boolean verifyActivationToken(String code, Long id) {
		Log.info("verifyActivationToken method invoked for userid : {} ", id);
		User user = null;
		try {
			user = userRepository.findByVerificationCode(code, id);
			if (user == null || user.isStatus()) {
				Log.info("user does not exist for the given code and id : {} ", id);
				return false;
			} else {
				user.setStatus(true);
				userRepository.save(user);
				Log.info("end of verifyActivationToken method for userid : {} ", id);
				return true;
			}
		} catch (EmptyResultDataAccessException e) {
			throw new UserMgmtException("requested user id does not exist in the system");
		}

	}

	/**
	 * Description: to get existance User
	 */
	@Override
	public User getExistanceUser(String field, String value) {
		Log.info("getExistanceUser method invoked for field : {}, value: {} ", field, value);
		User existUser = null;
		if (Objects.nonNull(value)) {
			try {
				if (field.equalsIgnoreCase("email")) {
					existUser = userRepository.findByEmail(value);
				} else if (field.equalsIgnoreCase("username")) {
					existUser = userRepository.findByUserName(value);
				} else if (field.equalsIgnoreCase("id") || !(value.isEmpty())) {
					Long id = Long.parseLong(value);
					Optional<User> user = userRepository.findById(id);
					if (user.isPresent()) {
						existUser = user.get();
					}
				}
			} catch (EmptyResultDataAccessException e) {
				throw new UserMgmtException("requested user id does not exist in the system");
			}
		}

		Log.info("getExistanceUser method invoked for field : {}, value: {} ", field, value);

		return existUser;
	}

}
   