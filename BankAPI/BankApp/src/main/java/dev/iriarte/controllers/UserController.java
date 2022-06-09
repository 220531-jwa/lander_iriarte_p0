package dev.iriarte.controllers;

import java.util.List;

import dev.iriarte.models.User;
import dev.iriarte.services.UserService;

import io.javalin.http.Context;

public class UserController {
	
	private static UserService us = new UserService();

	public static void getAllUsers(Context ctx) {
		ctx.status(200);
		List<User> users = us.getAllUsers();
		ctx.json(users);
	}
	
	public static void createNewUser(Context ctx) {
		ctx.status(201);
		User userFromRequestBody = ctx.bodyAsClass(User.class);
		User u = us.createUser(userFromRequestBody); // unmarshalling
		ctx.json(u);
	}
	
	public static void getUserById(Context ctx) {
		int id = Integer.parseInt(ctx.pathParam("id"));
		User u = us.getUserById(id);
		ctx.status(200);
		ctx.json(u);
	}
	
}
