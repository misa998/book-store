package com.misa.knjizara.dao;

import com.misa.knjizara.entity.Book;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BookServiceTest {

    private BookService bookService;

    @BeforeEach
    public void setUp(){
        bookService = new BookService();
    }

    @Test
    public void findByIdTest(){
        int expected = 2;
        Book book = bookService.findById(2).orElse(null);
        Assertions.assertEquals(expected, book.getId());
    }

    @Test
    public void findByIdTest2(){
        Book book = bookService.findById(10).orElse(null);
        Assertions.assertNull(book);
    }

    @Test
    public void addBookTest(){
        Book newBook = new Book(4, "Didibao", 2021,
                5.6, "Chao Jin", "sport", "chinese");
        int numberOfBooks = bookService.numberOfBooks();
        Book addedBook = bookService.addBook(newBook);

        Assertions.assertAll(
                () -> Assertions.assertEquals(numberOfBooks + 1, bookService.numberOfBooks()),
                () -> Assertions.assertEquals(newBook.getId(), bookService.findById(newBook.getId()).orElse(null).getId()),
                () -> Assertions.assertEquals(newBook.getName(), addedBook.getName())
        );

    }

    @Test
    public void deleteBookTest1(){
        String expected = "200";
        String actual = bookService.deleteBook(1);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void deleteBookTest2(){
        String expected = "ID out of range";
        String actual = bookService.deleteBook(0);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void switchBookTest(){
        int numberOfBooks = bookService.numberOfBooks();
        Book oldBook = bookService.findById(2).orElse(null);
        Book newBook = bookService.findById(2).orElse(null);
        newBook.setReleaseYear(1999);
        newBook.setUserScore(8.1);
        newBook.setLanguage("Spanish");
        bookService.switchBooks(oldBook.getId(), newBook);

        Assertions.assertAll(
                () -> Assertions.assertEquals(8.1, newBook.getUserScore()),
                () -> Assertions.assertEquals("Spanish", newBook.getLanguage()),
                () -> Assertions.assertEquals(1999, newBook.getReleaseYear()),
                () -> Assertions.assertEquals(numberOfBooks, bookService.numberOfBooks())
        );
    }

    @Test
    public void anyBookTest(){
        Assertions.assertNotNull(bookService.anyBook());
    }

    @Test
    public void numberOfBooks(){
        Assertions.assertTrue(bookService.numberOfBooks() > 0);
    }

    @Test
    public void listOfAuthorsTest(){
        Assertions.assertTrue(bookService.listOfAuthors().size() > 0);
    }

    @Test
    public void listOfAuthorsTest2(){
        Assertions.assertAll(
                () -> Assertions.assertTrue(bookService.listOfAuthors().contains("Paulo Coelho")),
                () -> Assertions.assertTrue(bookService.listOfAuthors().contains("Napoleon Hill"))
        );
    }
}
