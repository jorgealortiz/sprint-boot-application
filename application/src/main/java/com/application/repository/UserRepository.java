/**
 * 
 */
package com.application.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.application.entity.User;

/**
 * @author jortiz
 *
 */

public interface UserRepository extends JpaRepository<User, Long>{

	public Optional<User> findByUsername(String username);

	public Optional<User> findByEmail(String email);
	
}
