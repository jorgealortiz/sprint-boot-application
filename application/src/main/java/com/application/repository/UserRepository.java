/**
 * 
 */
package com.application.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.application.entity.User;

/**
 * @author jortiz
 *
 */

public interface UserRepository extends JpaRepository<User, Long>{

}
