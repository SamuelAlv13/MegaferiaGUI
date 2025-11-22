/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.person.repository;

/**
 *
 * @author aleke
 */
import model.Storage.Storage;
import model.person.Author;

public class AuthorRepository implements IAuthorRepository {

    private final Storage storage = Storage.getInstance();

    @Override
    public Author getById(long id) {
        return storage.getAuthor(id);
    }

    @Override
    public boolean save(Author author) {
        return storage.addAuthor(author);
    }
}
