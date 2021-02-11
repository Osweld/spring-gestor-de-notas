package com.osweld.dev.services;

import java.util.List;

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
	public User saveUser(User user) {
		Person person = personRepository.save(user.getPerson());
		if(person == null) return null;
		user.setPassword(PasswordEncoder.encode(user.getPassword()));
		user.setPerson(person);
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

}
