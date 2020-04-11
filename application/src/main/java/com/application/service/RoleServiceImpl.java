package com.application.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.application.entity.Role;
import com.application.repository.RoleRepository;

@Service
public class RoleServiceImpl implements RoleService{
	
	@Autowired
	private RoleRepository repository;

	@Override
	public Iterable<Role> getAllRoles() {
		return repository.findAll();
	}

}
