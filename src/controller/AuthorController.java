/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

/**
 *
 * @author aleke
 */
import controller.utils.Response;
import model.person.Author;
import model.person.repository.AuthorRepository;
import service.AuthorService;

public class AuthorController {

    private static final AuthorService service
            = new AuthorService(new AuthorRepository());

    public static Response createAuthor(String id, String firstname, String lastname) {
        return service.createAuthor(id, firstname, lastname);
    }

    public static Author getAuthorFromComboString(String comboString) {
        return service.parseComboAuthor(comboString);
    }
}
