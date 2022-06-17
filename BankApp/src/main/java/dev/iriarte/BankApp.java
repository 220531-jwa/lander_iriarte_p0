package dev.iriarte;
import static io.javalin.apibuilder.ApiBuilder.delete;
import static io.javalin.apibuilder.ApiBuilder.get;
import static io.javalin.apibuilder.ApiBuilder.patch;
import static io.javalin.apibuilder.ApiBuilder.path;
import static io.javalin.apibuilder.ApiBuilder.post;
import static io.javalin.apibuilder.ApiBuilder.put;

import java.util.List;

import dev.iriarte.controllers.BookController;
import dev.iriarte.controllers.UserController;
import dev.iriarte.services.UserService;
import dev.iriarte.models.Book;
import dev.iriarte.repositories.BookDAO;
import dev.iriarte.repositories.UserDAO;

import io.javalin.Javalin;

public class BankApp {
	
	public static void main(String[] args) {
		BookDAO bd = new BookDAO();
		UserController uc = new UserController(new UserService(new UserDAO()));
		Javalin app = Javalin.create();
		
		app.start(8080);
		
		
		app.routes(() -> {
			path("/users", () -> { // http://localhost:8080/users
				get(uc::getAllUsers);
				post(uc::createNewUser);
				path("/{id}", () -> { // http://localhost:8080/users/1
					get(uc::getUserById);
					delete(uc::deleteUser);
					put(uc::updateUser); 
					patch(uc::updatePassword);
					path("/accounts", () -> { //http://localhost:8080/users/1/accounts
						get(ctx -> {
							int id = Integer.parseInt(ctx.pathParam("id"));
							List<Book> books = bd.getBooksByUserId(id);
							ctx.status(200);
							ctx.json(books);
						});
						path("/{bookId}", () -> { //http://localhost:8080/users/10/books/5?someQueryParamKey=someValue?anotherQParam=helloagain
							patch(BookController::markBookAsRead);
						});
						
					});
				});
			});
		});
		
		
	
		
		// Exception Mapping - best practice would be to use a more specific (or even custom) exception like a UserNotFoundException
		app.exception(Exception.class, (e, ctx) -> {
		    ctx.status(404);
		    ctx.result("client not found");
		});
		// Error Mapping
		//app.error(404, ctx -> { ctx.result("Hahahah you typed in the url wrong");});
		
		// Test Endpoints that won't be in the final application 
		app.get("/test", ctx -> {
			ctx.status(200);
			String name = ctx.queryParam("name");
			ctx.result("Test successful! Hello " + name);
		});
		
//		app.get("/{name}", ctx -> {
//			ctx.status(200);
//			String name = ctx.pathParam("name");
//			ctx.result("Hello, " + name);
//		});
		
	
		app.get("/bodystring",ctx -> {
			
			String body = ctx.body();
			System.out.println("Body: " + body);
			String[] split = body.split(":");
			 
			 for (String s : split) {
				 System.out.println(s);
			 }
			
		});
		
	}


}