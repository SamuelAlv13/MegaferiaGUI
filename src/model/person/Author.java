/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.person;

import model.interfaze.Prototype;
import model.interfaze.Add_Book;
import model.book.Book;
import java.util.ArrayList;
import model.Publisher;

/**
 *
 * @author edangulo
 */
public class Author extends Person implements Add_Book<Book>, Prototype<Author> {

    private ArrayList<Book> books;

    public Author(long id, String firstname, String lastname) {
        super(id, firstname, lastname);
        this.books = new ArrayList<>();
    }

    public ArrayList<Book> getBooks() {
        return books;
    }

    public int getBookQuantity() {
        return this.books.size();
    }

    // ---- AddBook method ----
    @Override
    public void addBook(Book book) {
        this.books.add(book);
    }

    public int getPublisherQuantity() {
        ArrayList<Publisher> publishers = new ArrayList<>();
        for (Book book : this.books) {
            if (!publishers.contains(book.getPublisher())) {
                publishers.add(book.getPublisher());
            }
        }
        return publishers.size();
    }

    // ---- Prototype ----
    @Override
    public Author clone() throws CloneNotSupportedException {
        return new Author(this.id, this.firstname, this.lastname);
    }
}
