/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import controller.utils.Response;
import controller.utils.Status;
import model.Stand;
import model.Storage.Storage;

/**
 *
 * @author mayma
 */

import controller.utils.Response;
import controller.utils.Status;

public class StandController {

    private Storage storage;

    public StandController() {
        this.storage = Storage.getInstance();
    }

    public Response createStand(long id, double price) {

        // VALIDACIONES
        if (id < 0 || String.valueOf(id).length() > 15) {
            return new Response("El ID del stand no es válido", Status.BAD_REQUEST);
        }

        if (price <= 0) {
            return new Response("El precio debe ser mayor que 0", Status.BAD_REQUEST);
        }

        if (storage.getStand(id) != null) {
            return new Response("El stand con ese ID ya existe", Status.BAD_REQUEST);
        }

        // CREAR OBJETO
        Stand stand = new Stand(id, price);

        // GUARDAR
        boolean added = storage.addStand(stand);

        if (!added) {
            return new Response("Error al guardar el stand", Status.INTERNAL_SERVER_ERROR);
        }

        // DEVOLVER CLON (PROTOTYPE)
        Stand cloned = stand.clone();
        return new Response("Stand creado exitosamente", Status.CREATED, cloned);

    }
}



