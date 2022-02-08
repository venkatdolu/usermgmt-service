package com.wipro.usermgmt.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.wipro.usermgmt.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {

	@Query("SELECT u FROM User u WHERE u.email = ?1")
	public User findByEmail(String email);

	public void deleteByUserName(String userId);

	@Query("SELECT u FROM User u WHERE u.userName = ?1")
	public User findByUserName(String userName);

	@Query("SELECT u FROM User u WHERE u.password = ?1 AND u.id = ?2")
	public User findByVerificationCode(String code, Long id);
	

}