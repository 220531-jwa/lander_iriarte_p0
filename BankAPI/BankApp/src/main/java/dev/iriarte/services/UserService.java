package dev.iriarte.services;

import java.util.List;

import dev.iriarte.models.User;
import dev.iriarte.repositories.UserDAO;

public class UserService {
	
	private static UserDAO userDao = new UserDAO();
	
	
	//login
	public User login(String username, String password) {
		
		User u = userDao.getUserByUsername(username);
		
		if (u.getPassword().equals(password)) {
			return u;
		}
		return null;
	}
	
	// register / create user
	public User createUser(User u) {
		User createdUser = userDao.createUser(u);
		return createdUser;
	}

	public List<User> getAllUsers() {
		return userDao.getAllUsers();
	}

	public User getUserById(int id) {
		return userDao.getUserById(id);
	}
	

}
