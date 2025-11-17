/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import core.Audiobook;
import core.Author;
import core.Book;
import core.Publisher;
import java.util.ArrayList;
import model.Storage.Storage;

/**
 *
 * @author mayma
 */
public class AudioBookController {

    private Storage storage;

    public AudioBookController() {
        this.storage = Storage.getInstance();
    }

    // Add a new audiobook
    public boolean addAudiobook(String title, ArrayList<Author> authors, String isbn, String genre, String format, double value, Publisher publisher, int duration, Narrator narrator) {
        // Check if book already exists
        if (storage.getBook(isbn) != null) {
            return false; // ISBN already used
        }

        // Create audiobook
        Audiobook audiobook = new Audiobook(title, authors, isbn, genre, format, value, publisher, duration, narrator);

        // Add audiobook to storage
        boolean added = storage.addBook(audiobook);

        // Register audiobook with authors
        for (Author author : authors) {
            author.addBook(audiobook);
        }

        // Register audiobook with publisher
        publisher.addBook(audiobook);

        // Register audiobook with narrator
        narrator.addBook(audiobook);

        return added;
    }

    // Get audiobook by ISBN
    public Audiobook getAudiobook(String isbn) {
        Book book = storage.getBook(isbn);
        if (book instanceof Audiobook) {
            return (Audiobook) book;
        }
        return null;
    }

    // List all audiobooks
    public ArrayList<Audiobook> getAllAudiobooks() {
        ArrayList<Audiobook> audiobooks = new ArrayList<>();
        for (Book b : storage.getAllBooks()) {
            if (b instanceof Audiobook) {
                audiobooks.add((Audiobook) b);
            }
        }
        return audiobooks;
    }
}
