package com.misa.knjizara.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Component;

import com.misa.knjizara.entity.Book;

@Component
public class BookService {
	
	static List<Book> books = new ArrayList<>();
	
	static {
		books.add(new Book(0,"A Game of Thrones",1996,8.9,"George R.R. Martin","Epic Fantasy","English"));
		books.add(new Book(1,"The Alchemist",1988,7.7,"Paulo Coelho","Adventure","Portuguese"));
		books.add(new Book(2,"The Law of Success",1925,8.8,"Napoleon Hill","Self Help","English"));
	}
	
	public List<Book> getAll(){
		return books;
	}
	
	public Book findById(int id) {
		return books.get(id);
	}
	
	public Book addBook(Book newBook){
		books.add(newBook);
		return newBook;
	}
	public String deleteBook(int id){
		if (id>=0 && id<books.toArray().length){
			books.remove(id);
			return "OK";
		}
		return "ID out of range";
	}
	public Book switchBooks(int id, Book newBook){
		books.remove(id);
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
	public String listOfAuthors(){
		String names = "";
		for (int i=0; i<books.toArray().length;i++){
			names = names + " " + books.get(i).getAuthor() + " ";
		}
		return names;
	}
}
