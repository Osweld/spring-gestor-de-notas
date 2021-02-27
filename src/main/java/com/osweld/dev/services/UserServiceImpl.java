package com.osweld.dev.services;

import java.util.List;

import com.osweld.dev.models.entity.Career;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.osweld.dev.models.entity.Person;
import com.osweld.dev.models.entity.Rol;
import com.osweld.dev.models.entity.User;
import com.osweld.dev.models.repository.PersonRepository;
import com.osweld.dev.models.repository.UserRepository;
@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PersonRepository personRepository;
	
	@Autowired
	private BCryptPasswordEncoder PasswordEncoder;

	@Override
	@Transactional(readOnly = true)
	public User getUser(Long userId) {
		return userRepository.findById(userId).orElse(null);
	}

	@Override
	@Transactional(readOnly = true)
	public List<User> getAllUser() {
		return userRepository.findAll();
	}

	@Override
	@Transactional()
	public User saveUser(User user,Long careerId) {

		Person person = user.getPerson();
		person.setCareer(new Career(careerId));
		Person personDB = personRepository.save(person);

		if(personDB == null) return null;

		user.setPassword(PasswordEncoder.encode(user.getPassword()));
		user.setPerson(personDB);
		user.setRol(new Rol(1L));
		user.setActive(false);
		return userRepository.save(user);
	}

	@Override
	@Transactional()
	public void deleteUser(Long userId) {
		userRepository.deleteById(userId);
		
	}

	@Override
	@Transactional(readOnly = true)
	public User getUserByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	@Override
	@Transactional()
	public User updateUser(User user) {
		return userRepository.save(user);
	}

	@Override
	@Transactional()
	public User updatePassword(User user) {
		user.setPassword(PasswordEncoder.encode(user.getPassword()));
		return userRepository.save(user);
	}

	@Override
	@Transactional(readOnly = true)
	public User getUserByUsernameAndEmail(String username, String email) {
		return userRepository.findByUsernameAndEmail(username,email);
	}

}
