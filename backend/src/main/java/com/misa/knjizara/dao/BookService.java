package com.misa.knjizara.dao;

import java.util.*;

import org.springframework.stereotype.Component;

import com.misa.knjizara.entity.Book;

@Component
public class BookService {
	
	static List<Book> books = new ArrayList<>();
	
	static {
		books.add(new Book(1,"A Game of Thrones",1996,8.9,"George R.R. Martin","Epic Fantasy","English"));
		books.add(new Book(2,"The Alchemist",1988,7.7,"Paulo Coelho","Adventure","Portuguese"));
		books.add(new Book(3,"The Law of Success",1925,8.8,"Napoleon Hill","Self Help","English"));
	}
	
	public List<Book> getAll(){
		return books;
	}
	
	public Optional<Book> findById(int id) {
		return books.stream().filter(book -> book.getId() == id).findFirst();
	}
	
	public Book addBook(Book newBook){
		books.add(newBook);
		return newBook;
	}

	public String deleteBook(int id){
		if (id > 0 && id < books.toArray().length){
			Book bookToRemove = findById(id).orElse(null);
			books.remove(bookToRemove);
			return "200";
		}
		return "ID out of range";
	}

	public Book switchBooks(int id, Book newBook){
		Book bookToRemove = findById(id).orElse(null);
		books.remove(bookToRemove);
		books.add(newBook);
		return newBook;
	}

	public Book anyBook(){
		Random rand = new Random();
		int n = rand.nextInt(books.toArray().length);
		return books.get(n);
	}

	public String authorOfBook(int id){
		return books.get(id).getAuthor();
	}

	public int numberOfBooks() {
		return books.toArray().length;
	}

	public Set<String> listOfAuthors(){
		Set<String> authors = new HashSet<>(Collections.emptySet());
		for(Book book : books){
			authors.add(book.getAuthor());
		}

		return authors;
	}
}
