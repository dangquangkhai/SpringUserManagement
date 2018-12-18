package com.usermanagement.Provider;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.usermanagement.Model.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	
	@Query("select u from User u where u.ID = :#{#ID}")
	User getUserById(@Param("ID") int ID);
	
	@Query("select u from User u where u.Email = :#{#Email}")
	User getUserByEmail(@Param("Email") String Email);
	
	
	
}
