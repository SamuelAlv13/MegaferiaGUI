/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.person;

import model.interfaze.Prototype;
import model.interfaze.Add_Book;
import model.book.AudioBook;
import java.util.ArrayList;

/**
 *
 * @author edangulo
 */
public class Narrator extends Person implements Add_Book<AudioBook>, Prototype<Narrator> {
    
    private ArrayList<AudioBook> books;

    public Narrator(long id, String firstname, String lastname) {
        super(id, firstname, lastname);
        this.books = new ArrayList<>();
    }
    
    public int getBookQuantity() {
        return this.books.size();
    }
    
    // ---- AddBook method----
    @Override
    public void addBook(AudioBook book) {
        this.books.add(book);
    }
    
    // ---- Prototype ----
    @Override
    public Narrator clone() throws CloneNotSupportedException {
        return new Narrator(this.id, this.firstname, this.lastname);
    }
}
