package dev.iriarte;
import java.util.List;

import com.revature.models.User;
import com.revature.services.UserService;

import io.javalin.Javalin;

public class BankApp {
	
	private static UserService userService;
	
	public static void main(String[] args) {
		userService = new UserService();
		
		Javalin app = Javalin.create();
		
		app.start(8081);
		
		// Javalin provides us with a Context Class (ctx) that represents information 
		// about BOTH the Http Request AND Http Response Objects
		// we'll be using methods from the context class to handle our incoming http requests
		// and to send our http resonses
		
		app.get("/users", ctx -> {
			ctx.status(200);
			List<User> users = userService.getAllUsers();
			ctx.json(users); // marshalling
		});
		
		app.get("/users/{id}", ctx -> {
			
			int id = Integer.parseInt(ctx.pathParam("id"));
			
			User u = userService.getUserById(id);
			ctx.status(200);
			ctx.json(u);
		});
		
		
		app.post("/users", ctx -> {
			ctx.status(201);
			User userFromRequestBody = ctx.bodyAsClass(User.class);
			User u = userService.createUser(userFromRequestBody); // unmarshalling
			ctx.json(u);
			
		});
		
		
		
		
		
		
		app.get("/test", ctx -> {
			ctx.status(200);
			ctx.result("Test successful!");
		});
		
		app.get("/{name}", ctx -> {
			ctx.status(200);
			String name = ctx.pathParam("name");
			ctx.result("Hello, " + name);
		});
		
	
		
	}

}