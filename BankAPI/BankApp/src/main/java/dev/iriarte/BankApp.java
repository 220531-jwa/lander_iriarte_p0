package dev.iriarte;
import dev.iriarte.controllers.UserController;
import java.util.List;

import dev.iriarte.models.User;
import dev.iriarte.services.UserService;

import io.javalin.Javalin;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import static io.javalin.apibuilder.ApiBuilder.*;

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
		
		// lambdas - introduced functional programming to Java
		// (parameter) -> {// implementation}
		
		app.routes(() -> {
			path("/users", () -> {
				get(UserController::getAllUsers);
			});
		});
		
		app.get("/users", UserController::getAllUsers);
		
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
			String name = ctx.queryParam("name");
			ctx.result("Test successful! Hello " + name);
		});
		
		app.get("/{name}", ctx -> {
			ctx.status(200);
			String name = ctx.pathParam("name");
			ctx.result("Hello, " + name);
		});
		
	
		
	}


}