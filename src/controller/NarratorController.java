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

        
        if (idText == null || idText.isEmpty() ||
            firstname == null || firstname.isEmpty() ||
            lastname == null || lastname.isEmpty()) {
            return new Response("Todos los campos son obligatorios", Status.BAD_REQUEST);
        }

        long id;
        try {
            id = Long.parseLong(idText);
        } catch (NumberFormatException e) {
            return new Response("El ID debe ser numérico", Status.BAD_REQUEST);
        }

        if (id < 0 || String.valueOf(id).length() > 15) {
            return new Response("El ID debe ser >= 0 y tener máximo 15 dígitos", Status.BAD_REQUEST);
        }

        
        if (storage.getNarrator(id) != null) {
            return new Response("Ya existe un narrador con ese ID", Status.BAD_REQUEST);
        }

        
        Narrator narrator = new Narrator(id, firstname.trim(), lastname.trim());
        boolean inserted = storage.addNarrator(narrator);
        if (!inserted) {
            return new Response("No se pudo registrar el narrador", Status.INTERNAL_SERVER_ERROR);
        }

        
        Narrator cloned;
        try {
            cloned = narrator.clone();
        } catch (CloneNotSupportedException e) {
            return new Response("Error al clonar el narrador", Status.INTERNAL_SERVER_ERROR);
        }

        return new Response("Narrador creado correctamente", Status.CREATED, cloned);
    }
}
