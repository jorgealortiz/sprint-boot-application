/**
 * 
 */
package com.application.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.application.entity.Role;

/**
 * @author jortiz
 *
 */

public interface RoleRepository extends JpaRepository<Role, Long>{

}
