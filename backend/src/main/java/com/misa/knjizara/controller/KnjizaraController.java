package com.misa.knjizara.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.misa.knjizara.dao.BookService;
import com.misa.knjizara.entity.Book;

@RestController
public class KnjizaraController {

	@Autowired
	private BookService bookService;
	
	@RequestMapping(method=RequestMethod.GET, path="/books")
	public List<Book> showAll() {
		return bookService.getAll();
	}
	
	@RequestMapping(method=RequestMethod.GET, path="/books/{id}")
	public Book showById(@PathVariable int id) {
		return bookService.findById(id);
	}
	
	@RequestMapping(method=RequestMethod.POST, path="/books")
	public Book addNew(@RequestBody Book book) {
		return bookService.addBook(book);
	}
	
	@RequestMapping(method=RequestMethod.DELETE, path="/books/{id}")
	public String deleteBook(@PathVariable int id) {
		return bookService.deleteBook(id);
	}
	
	@PutMapping("/books/{id}")
	public Book switchBooks(@PathVariable int id, @RequestBody Book newBook){
		return bookService.switchBooks(id, newBook);
	}
	
	@GetMapping("/books/random")
	public Book returnAnyBook(){
		return bookService.anyBook();
	}
	
	@GetMapping("/books/author/{id}")
	public String bookAuthor(@PathVariable int id){
		return bookService.authorOfBook(id);
	}
	
	@GetMapping("/books/count")
	public int bookCount(){
		return bookService.numberOfBooks();
	}
	
	@GetMapping("/authors")
	public String allAuthors(){
		return bookService.listOfAuthors();
	}
}
