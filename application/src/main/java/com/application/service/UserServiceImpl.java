package com.application.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.application.entity.User;
import com.application.exception.UsernameOrIdNotFoundException;
import com.application.model.ChangePasswordForm;
import com.application.repository.UserRepository;

/**
 * @author jortiz
 *
 */
@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;

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
			String encodePass = bCryptPasswordEncoder.encode((user.getPassword()));
			user.setPassword(encodePass);
			user = repository.save(user);
		}
		return user;
	}

	@Override
	public User findById(Long id) throws  UsernameOrIdNotFoundException {
		return repository.findById(id).orElseThrow(() -> new UsernameOrIdNotFoundException("Usuario no existe"));
	}

	@Override
	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
	public User updateUser(User user) throws UsernameOrIdNotFoundException {
		User userFound = findById(user.getId());
		mapUser(user, userFound);
		return repository.save(userFound);
	}

	@Override
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	public void deleteUser(Long id) throws UsernameOrIdNotFoundException {
		User userFound = findById(id);
		repository.delete(userFound);
	}

	@Override
	public User changePassword(ChangePasswordForm form) throws Exception {
		User userFound = repository.findById(form.getId())
				.orElseThrow(() -> new Exception("UsernotFound in ChangePassword -" + this.getClass().getName()));

		if (!isLoggedUserADMIN() && form.getActual().equals(userFound.getPassword())) {
			throw new Exception("Password actual Incorrecto.");
		}

		if (form.getActual().equals(form.getNuevo())) {
			throw new Exception("Password generado debe ser diferente al existente");
		}

		if (!form.getNuevo().equals(form.getConfirmar())) {
			throw new Exception("Password generado y Password de confirmación no coinciden!");
		}
		
		String encodePass = bCryptPasswordEncoder.encode((form.getNuevo()));
		userFound.setPassword(encodePass);
		return repository.save(userFound);
	}

	public boolean isLoggedUserADMIN() {
		return loggedUserHasRole("ROLE_ADMIN");
	}

	public boolean loggedUserHasRole(String role) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserDetails loggedUser = null;
		Object roles = null;
		if (principal instanceof UserDetails) {
			loggedUser = (UserDetails) principal;
			roles = loggedUser.getAuthorities().stream().filter(x -> role.equals(x.getAuthority())).findFirst()
					.orElse(null); // loggedUser = null;
		}
		return roles != null ? true : false;
	}

	/**
	 * Method clone User but not password
	 * 
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
