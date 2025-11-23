/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import model.Publisher;
import model.book.Book;
import model.person.Author;

/**
 *
 * @author Samuel Alvarado
 */
public class BookRegistration {
    public void registerBook(Book book) {

        // Registrar en autores
        for (Author author : book.getAuthors()) {
            author.addBook(book);
        }

        // Registrar en publisher
        Publisher publisher = book.getPublisher();
        publisher.addBook(book);
    }
}
