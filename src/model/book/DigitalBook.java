/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.book;

import java.util.ArrayList;
import model.person.Author;
import model.Publisher;
import model.interfaz.Prototype;

/**
 *
 * @author edangulo
 */
public class DigitalBook extends Book implements Prototype<DigitalBook> {
    
    private boolean hasHyperlink;
    private String hyperlink;

    // Constructor normal SIN hyperlink
    public DigitalBook(String title, ArrayList<Author> authors, String isbn, 
                       String genre, String format, double value, 
                       Publisher publisher) {

        super(title, authors, isbn, genre, format, value, publisher);
        this.hasHyperlink = false;
        this.hyperlink = null;
    }

    // Constructor normal CON hyperlink
    public DigitalBook(String title, ArrayList<Author> authors, String isbn, 
                       String genre, String format, double value, 
                       Publisher publisher, String hyperlink) {

        super(title, authors, isbn, genre, format, value, publisher);
        this.hasHyperlink = true;
        this.hyperlink = hyperlink;
    }

    // ------ COPY CONSTRUCTOR (usado SOLO para clone) ------
    public DigitalBook(DigitalBook other, ArrayList<Author> clonedAuthors) {
        super(
            other.title,
            clonedAuthors,
            other.isbn,
            other.genre,
            other.format,
            other.value,
            other.publisher
        );

        this.hasHyperlink = other.hasHyperlink;
        this.hyperlink = other.hyperlink;
    }

    public boolean hasHyperlink() {
        return hasHyperlink;
    }

    public String getHyperlink() {
        return hyperlink;
    }

    // ------ PROTOTYPE ------
    @Override
    public DigitalBook clone() throws CloneNotSupportedException {
        ArrayList<Author> clonedAuthors = new ArrayList<>(this.authors);
        return new DigitalBook(this, clonedAuthors);
    }
}
