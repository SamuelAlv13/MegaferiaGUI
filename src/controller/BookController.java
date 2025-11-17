/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import model.person.Author;
import model.book.Book;
import model.Publisher;
import java.util.ArrayList;
import model.Storage.Storage;

/**
 *
 * @author mayma
 */
public class BookController {

    private Storage storage;

    public BookController() {
        this.storage = Storage.getInstance(); 
    }

    
    public boolean addBook(String title, ArrayList<Author> authors, String isbn, String genre, String format, double value, Publisher publisher) {
        
        if (storage.getBook(isbn) != null) {
            return false; 
        }

        
        Book book = new Book(title, authors, isbn, genre, format, value, publisher) {
            
        };

        
        boolean added = storage.addBook(book);

        
        for (Author author : authors) {
            author.addBook(book);
        }

        
        publisher.addBook(book);

        return added;
    }

    
    public Book getBook(String isbn) {
        return storage.getBook(isbn);
    }

    
    public ArrayList<Book> getAllBooks() {
        return storage.getAllBooks();
    }
}
