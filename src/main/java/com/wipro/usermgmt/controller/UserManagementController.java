package com.wipro.usermgmt.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wipro.usermgmt.entities.Role;
import com.wipro.usermgmt.entities.User;
import com.wipro.usermgmt.service.MiscellaneousOperations;
import com.wipro.usermgmt.service.UserManagementService;

/**
 * @author Venkat
 * 
 * Description : used to handle user management requests and response
 *
 */
@RestController
public class UserManagementController {

	private static final Logger Log = LoggerFactory.getLogger(UserManagementController.class);

	private UserManagementService usrMgmtService;

	private MiscellaneousOperations miscOperations;

	@Autowired
	public void setUsrMgmtService(UserManagementService usrMgmtService) {
		this.usrMgmtService = usrMgmtService;
	}

	@Autowired
	public void setMiscOperations(MiscellaneousOperations miscOperations) {
		this.miscOperations = miscOperations;
	}

	/**
	 * Description: This API used to create User
	 * 
	 * @param user
	 * @return
	 */
	@PostMapping("/createuser")
	public ResponseEntity<User> createUser(@RequestBody User user) {
		Log.info("createuser API invoked for username : {} ", user.getUserName());
		User createdUser = usrMgmtService.createUser(user);
		Log.info("end of createuser API for username : {} ", user.getUserName());
		return new ResponseEntity<User>(createdUser, HttpStatus.OK);
	}

	/**
	 * Description: This API used to update user
	 * 
	 * @param user
	 * @return
	 */
	@PutMapping("/updateuser")
	public ResponseEntity<User> updateUser(@RequestBody User user) {
		Log.info("updateuser API invoked for username : {} ", user.getUserName());
		User updatedUser = usrMgmtService.updateUser(user);
		Log.info("end of updateuser API for username : {} ", user.getUserName());
		return new ResponseEntity<User>(updatedUser, HttpStatus.OK);
	}

	/**
	 * Description: This API used to get all users
	 * 
	 * @return
	 */
	@GetMapping("/getusers")
	public ResponseEntity<List<User>> getUsers() {
		Log.info("getUsers API invoked.");
		List<User> allUsers = usrMgmtService.getAllUser();
		Log.info("end of getUsers API.");
		return new ResponseEntity<List<User>>(allUsers, HttpStatus.OK);
	}

	/**
	 * Description: This API used to update user
	 * 
	 * @param id
	 * @return
	 */
	@DeleteMapping("/deleteuser")
	public ResponseEntity<String> deleteUser(@RequestParam Long id) {
		Log.info("deleteUser API invoked for username : {} ", id);
		usrMgmtService.deleteUser(id);
		Log.info("end of deleteUser API for username : {} ", id);
		return new ResponseEntity<String>("Success", HttpStatus.OK);
	}

	/**
	 * Description: getuser by id
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping("/getuser")
	public ResponseEntity<User> getUserById(@RequestParam Long id) {
		Log.info("getUserById API invoked for id : {} ", id);
		User user = miscOperations.getUserById(id);
		Log.info("end of getUserById API for id : {} ", id);
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	/**
	 * Description : getAllRoles
	 * 
	 * @return
	 */
	@GetMapping("/getroles")
	public ResponseEntity<List<Role>> getAllRoles() {
		Log.info("getAllRoles API invoked.");
		List<Role> roles = miscOperations.getAllRoles();
		Log.info("end of getUserById API.");
		return new ResponseEntity<List<Role>>(roles, HttpStatus.OK);
	}

	/**
	 * Description: getRole by id
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping("/getrole")
	public ResponseEntity<Role> getRoleById(@RequestParam Long id) {
		Log.info("getRoleById API invoked for id : {} ", id);
		Role role = miscOperations.getRoleById(id);
		Log.info("end of getRoleById API for id : {} ", id);
		return new ResponseEntity<Role>(role, HttpStatus.OK);
	}

	/**
	 * Description: API used to verify the activation link
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping("/verifyactivation")
	public ResponseEntity<Boolean> verifyActivationToken(@RequestParam String code, @RequestParam Long id) {
		Log.info("verifyActivationToken API invoked for id : {}, code : {} ", id, code);
		Boolean isActivated = miscOperations.verifyActivationToken(code, id);
		Log.info("end of verifyActivationToken API for id : {}, code : {} ", id, code);
		return new ResponseEntity<Boolean>(isActivated, HttpStatus.OK);
	}
	
	/**
	 * Description: getuser by userName
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping("/getuserByName")
	public ResponseEntity<User> getUserByName(@RequestParam String userName) {
		Log.info("getUserById API invoked for userName : {} ", userName);
		User user = miscOperations.getUserByName(userName);
		Log.info("end of getUserById API for userName : {} ", userName);
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

}
