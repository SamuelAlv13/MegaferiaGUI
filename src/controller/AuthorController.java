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

        try {
            long authorId = Long.parseLong(id);

            Author author = new Author(authorId, firstname, lastname);

            boolean added = Storage.getInstance().addAuthor(author);

            if (!added) {
                return new Response("El autor con ID " + id + " ya existe", Status.BAD_REQUEST);
            }

            return new Response("Autor creado correctamente", Status.CREATED, author);

        } catch (NumberFormatException e) {
            return new Response("El ID debe ser numérico", Status.BAD_REQUEST);

        } catch (Exception e) {
            return new Response("Error inesperado: " + e.getMessage(), Status.INTERNAL_SERVER_ERROR);
        }
    }
}

