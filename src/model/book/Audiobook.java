/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.book;

import java.util.ArrayList;
import model.person.Author;
import model.person.Narrator;
import model.Publisher;
import model.interfaz.Prototype;

/**
 *
 * @author edangulo
 */
public class AudioBook extends Book implements Prototype<AudioBook> {

    private int duration;
    private Narrator narrator;

    // Constructor normal — libro REAL
    public AudioBook(String title, ArrayList<Author> authors, String isbn, String genre,
                     String format, double value, Publisher publisher,
                     int duration, Narrator narrator) {

        super(title, authors, isbn, genre, format, value, publisher);
        this.duration = duration;
        this.narrator = narrator;

        // Registro real
        this.narrator.addBook(this);
    }

    // Constructor COPY — NO registra en el narrador
    public AudioBook(AudioBook other, ArrayList<Author> clonedAuthors) {
        super(
            other.title,
            clonedAuthors,
            other.isbn,
            other.genre,
            other.format,
            other.value,
            other.publisher
        );

        this.duration = other.duration;
        this.narrator = other.narrator;
    }
    
    // ---- Prototype ----
    @Override
    public AudioBook clone() throws CloneNotSupportedException {
        ArrayList<Author> clonedAuthors = new ArrayList<>(this.authors);
        return new AudioBook(this, clonedAuthors);
    }

    public int getDuration() {
        return duration;
    }

    public Narrator getNarrador() {
        return narrator;
    }
}

