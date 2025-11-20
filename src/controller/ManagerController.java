/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import controller.utils.Response;
import controller.utils.Status;
import model.Storage.Storage;
import model.person.Manager;

/**
 *
 * @author mayma
 */
public class ManagerController {

    public static Response createManager(String id, String firstname, String lastname) {

       
        if (id == null || id.isEmpty() || firstname == null || firstname.isEmpty() || lastname == null || lastname.isEmpty()) {
            return new Response("Todos los campos son obligatorios", Status.BAD_REQUEST);
        }

        long managerId;
        try {
            managerId = Long.parseLong(id);
        } catch (NumberFormatException e) {
            return new Response("El ID debe ser numérico", Status.BAD_REQUEST);
        }

        if (managerId < 0 || String.valueOf(managerId).length() > 15) {
            return new Response("El ID debe ser >= 0 y tener máximo 15 dígitos", Status.BAD_REQUEST);
        }

        Storage storage = Storage.getInstance();

       
        if (storage.getManager(managerId) != null) {
            return new Response("El gerente con ID " + id + " ya existe", Status.BAD_REQUEST);
        }

       
        Manager manager = new Manager(managerId, firstname.trim(), lastname.trim());
        boolean added = storage.addManager(manager);
        if (!added) {
            return new Response("No se pudo registrar el gerente", Status.INTERNAL_SERVER_ERROR);
        }

        
        Manager cloned;
        try {
            cloned = manager.clone();
        } catch (CloneNotSupportedException e) {
            return new Response("Error al clonar el gerente", Status.INTERNAL_SERVER_ERROR);
        }

        return new Response("Gerente creado correctamente", Status.CREATED, cloned);
    }
}

