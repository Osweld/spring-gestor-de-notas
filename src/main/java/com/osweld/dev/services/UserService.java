package com.osweld.dev.services;

import java.util.List;

import com.osweld.dev.models.entity.User;

public interface UserService {

	public User getUser(Long userId);
	public List<User> getAllUser();
	public User saveUser(User user);
	public void deleteUser(Long userId);
	public User getUserByEmail(String email);

}
