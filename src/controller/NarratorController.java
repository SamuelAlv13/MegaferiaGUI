/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import controller.utils.Response;
import controller.utils.Status;
import model.Storage.Storage;
import model.person.Narrator;

/**
 *
 * @author mayma
 */
public class NarratorController {
        private static final Storage storage = Storage.getInstance();

    public static Response createNarrator(String idText, String firstname, String lastname) {
        
        long id;
        
        try {
            id = Long.parseLong(idText);
        } catch (NumberFormatException e) {
            return new Response("El ID debe ser numérico.", Status.BAD_REQUEST);
        }

        if (id <= 0)
            return new Response("El ID debe ser mayor que cero.", Status.BAD_REQUEST);

        
        if (firstname == null || firstname.isBlank())
            return new Response("El nombre no puede estar vacío.", Status.BAD_REQUEST);

        if (lastname == null || lastname.isBlank())
            return new Response("El apellido no puede estar vacío.", Status.BAD_REQUEST);

        
        if (storage.getNarrator(id) != null)
            return new Response("Ya existe un narrador con ese ID.", Status.BAD_REQUEST);

        
        Narrator narrator = new Narrator(id, firstname, lastname);

        
        boolean inserted = storage.addNarrator(narrator);

        if (!inserted)
            return new Response("Error inesperado insertando narrador.", Status.INTERNAL_SERVER_ERROR);

        return new Response("Narrador creado correctamente", Status.CREATED, narrator);
    }
}
