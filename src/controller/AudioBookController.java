/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import model.book.Audiobook;
import model.person.Author;
import model.book.Book;
import model.Publisher;
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

    
    public boolean addAudiobook(String title, ArrayList<Author> authors, String isbn, String genre, String format, double value, Publisher publisher, int duration, Narrator narrator) {
        
        if (storage.getBook(isbn) != null) {
            return false; 
        }

        
        Audiobook audiobook = new Audiobook(title, authors, isbn, genre, format, value, publisher, duration, narrator);

        
        boolean added = storage.addBook(audiobook);

        
        for (Author author : authors) {
            author.addBook(audiobook);
        }

        
        publisher.addBook(audiobook);

       
        narrator.addBook(audiobook);

        return added;
    }

  
    public Audiobook getAudiobook(String isbn) {
        Book book = storage.getBook(isbn);
        if (book instanceof Audiobook) {
            return (Audiobook) book;
        }
        return null;
    }

    
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
