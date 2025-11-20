/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import controller.utils.Response;
import controller.utils.Status;
import model.Storage.Storage;
import model.person.Author;

/**
 *
 * @author mayma
 */
public class AuthorController {

    public static Response createAuthor(String id, String firstname, String lastname) {

      
        if (id == null || id.isEmpty() || firstname == null || firstname.isEmpty() || lastname == null || lastname.isEmpty()) {
            return new Response("Todos los campos son obligatorios", Status.BAD_REQUEST);
        }

        long authorId;
        try {
            authorId = Long.parseLong(id);
        } catch (NumberFormatException e) {
            return new Response("El ID debe ser numérico", Status.BAD_REQUEST);
        }

        
        Storage storage = Storage.getInstance();
        if (storage.getAuthor(authorId) != null) {
            return new Response("El autor con ID " + id + " ya existe", Status.BAD_REQUEST);
        }

        
        Author author = new Author(authorId, firstname, lastname);
        boolean added = storage.addAuthor(author);
        if (!added) {
            return new Response("No se pudo registrar el autor", Status.INTERNAL_SERVER_ERROR);
        }

        
        Author cloned;
        try {
            cloned = author.clone();
        } catch (CloneNotSupportedException e) {
            return new Response("Error al clonar el autor", Status.INTERNAL_SERVER_ERROR);
        }

        return new Response("Autor creado correctamente", Status.CREATED, cloned);
    }

    public static Author getAuthorFromComboString(String comboString) {
        String[] data = comboString.split(" - ");
        long id = Long.parseLong(data[0]);

        return Storage.getInstance().getAuthor(id);
    }
}


