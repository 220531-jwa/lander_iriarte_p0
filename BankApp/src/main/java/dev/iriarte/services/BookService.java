package dev.iriarte.services;

import dev.iriarte.models.Book;
import dev.iriarte.repositories.BookDAO;

public class BookService {

	private static BookDAO bd = new BookDAO();
	
	public void markAsRead(int userId, int bookId) {

		Book b = bd.getUserBookById(userId, bookId);
		if (b!=null) {
			bd.updateBookMarkAsRead(userId, bookId);
		}
		
	}
	

}