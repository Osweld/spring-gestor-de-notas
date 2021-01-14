package com.osweld.dev.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.osweld.dev.models.entity.User;
import com.osweld.dev.models.repository.UserRepository;
@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepository userRepository;

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
	public User saveUser(User user) {
		return userRepository.save(user);
	}

	@Override
	public void deleteUser(Long userId) {
		userRepository.deleteById(userId);
		
	}

}
