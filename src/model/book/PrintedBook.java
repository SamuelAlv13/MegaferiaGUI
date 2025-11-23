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
public class PrintedBook extends Book implements Prototype<PrintedBook> {
    
    private int pages;
    private int copies;

    public PrintedBook(String title, ArrayList<Author> authors, String isbn, String genre, String format, double value, Publisher publisher, int pages, int copies) {
        super(title, authors, isbn, genre, format, value, publisher);
        this.pages = pages;
        this.copies = copies;
    }
    // Constructor COPY (usado SOLO por clone)
    public PrintedBook(PrintedBook other, ArrayList<Author> clonedAuthors) {
        super(other.title,clonedAuthors,other.isbn,other.genre,other.format,other.value,other.publisher);
        this.pages = other.pages;
        this.copies = other.copies;
    }

    public int getPages() {
        return pages;
    }

    public int getCopies() {
        return copies;
    }
    // ---- Prototype ----
    @Override
    public PrintedBook clone() throws CloneNotSupportedException {
        ArrayList<Author> clonedAuthors = new ArrayList<>(this.authors);
        return new PrintedBook(this, clonedAuthors);
    }
}
