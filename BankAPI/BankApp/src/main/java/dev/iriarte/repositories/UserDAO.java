package dev.iriarte.repositories;

import java.util.List;

import dev.iriarte.models.User;
import com.revature.utils.MockDB;

public class UserDAO {
	
	
	// CRUD Methods
	
	// Create
	public User createUser(User u) {
		MockDB.users.add(u);
		return u;
	}
	
	// Read
	public List<User> getAllUsers() {
		return MockDB.users;
	}
	
	public User getUserById(int id) {
		return MockDB.users.get(id-1);
	}
	
	public User getUserByUsername(String username) {
		for (User u : MockDB.users) {
			if (u.getUsername().equals(username)) {
				return u;
			}
		}
		System.out.println("User not found");
		return null;
	}
	
	//Update
	
	// Delete

}