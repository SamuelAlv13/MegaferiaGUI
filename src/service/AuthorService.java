/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

/**
 *
 * @author aleke
 */
import controller.utils.Response;
import controller.utils.Status;
import model.person.Author;
import model.person.repository.IAuthorRepository;

public class AuthorService {

    private final IAuthorRepository repository;

    public AuthorService(IAuthorRepository repository) {
        this.repository = repository;
    }

    public Response createAuthor(String id, String firstname, String lastname) {

        if (id == null || id.isEmpty()
                || firstname == null || firstname.isEmpty()
                || lastname == null || lastname.isEmpty()) {

            return new Response("Todos los campos son obligatorios", Status.BAD_REQUEST);
        }

        long authorId;
        try {
            authorId = Long.parseLong(id);
        } catch (NumberFormatException e) {
            return new Response("El ID debe ser numérico", Status.BAD_REQUEST);
        }

        if (repository.getById(authorId) != null) {
            return new Response("El autor con ID " + id + " ya existe", Status.BAD_REQUEST);
        }

        Author author = new Author(authorId, firstname, lastname);

        if (!repository.save(author)) {
            return new Response("No se pudo registrar el autor", Status.INTERNAL_SERVER_ERROR);
        }

        try {
            Author cloned = author.clone();
            return new Response("Autor creado correctamente", Status.CREATED, cloned);

        } catch (CloneNotSupportedException e) {
            return new Response("Error al clonar el autor", Status.INTERNAL_SERVER_ERROR);
        }
    }

    public Author parseComboAuthor(String comboString) {
        String[] data = comboString.split(" - ");
        long id = Long.parseLong(data[0]);

        return repository.getById(id);
    }
}
