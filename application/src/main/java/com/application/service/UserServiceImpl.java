package com.application.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.application.entity.User;
import com.application.repository.UserRepository;

/**
 * @author jortiz
 *
 */
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository repository;

	@Override
	public Iterable<User> getAllUsers() {
		return repository.findAll();
	}

	private boolean checkUsernameAvailable(User user) throws Exception {
		Optional<User> userFound = repository.findByUsername(user.getUsername());
		if (userFound.isPresent()) {
			throw new Exception("Username no esta disponible");
		}
		return true;
	}

	private boolean checkEmailAvailable(User user) throws Exception {
		Optional<User> userFound = repository.findByEmail(user.getEmail());
		if (userFound.isPresent()) {
			throw new Exception("Ya existe un usuario Regitsrado");
		}
		return true;
	}

	private boolean checkPasswordvalid(User user) throws Exception {
		if (!user.getPassword().equals(user.getConfirmPassword())) {
			throw new Exception("La Confirmacion de Password no Coincide.");
		}
		return true;
	}

	@Override
	public User createUser(User user) throws Exception {
		if (checkUsernameAvailable(user) && checkEmailAvailable(user) && checkPasswordvalid(user)) {
			user = repository.save(user);
		}
		return user;
	}

	@Override
	public User findById(Long id) throws Exception {
		return repository.findById(id).orElseThrow(() -> new Exception("Usuario no existe"));
	}

	@Override
	public User updateUser(User user) throws Exception {
		User userFound = findById(user.getId());
		mapUser(user, userFound);
		return repository.save(userFound);
	}
	
	/**
	 * Method clone User but not password
	 * @param from
	 * @param to
	 */
	protected void mapUser(User from, User to) {
		to.setUsername(from.getUsername());
		to.setFirstName(from.getFirstName());
		to.setLastName(from.getLastName());
		to.setEmail(from.getEmail());
		to.setRoles(from.getRoles());
	}

}
