/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import core.Author;
import core.Book;
import core.Publisher;
import java.util.ArrayList;
import model.Storage.Storage;

/**
 *
 * @author mayma
 */
public class BookController {

    private Storage storage;

    public BookController() {
        this.storage = Storage.getInstance(); // singleton storage
    }

    // Add a new book and register it with authors and publisher
    public boolean addBook(String title, ArrayList<Author> authors, String isbn, String genre, String format, double value, Publisher publisher) {
        // Check if book already exists
        if (storage.getBook(isbn) != null) {
            return false; // book already in storage
        }

        // Create the book
        Book book = new Book(title, authors, isbn, genre, format, value, publisher) {
            // Since Book is abstract, we provide an anonymous subclass
        };

        // Add book to storage
        boolean added = storage.addBook(book);

        // Register book with authors
        for (Author author : authors) {
            author.addBook(book);
        }

        // Register book with publisher
        publisher.addBook(book);

        return added;
    }

    // Get a book by ISBN
    public Book getBook(String isbn) {
        return storage.getBook(isbn);
    }

    // List all books
    public ArrayList<Book> getAllBooks() {
        return storage.getAllBooks();
    }
}
