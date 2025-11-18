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

        try {
            long managerId = Long.parseLong(id);

            Manager manager = new Manager(managerId, firstname, lastname);

            boolean added = Storage.getInstance().addManager(manager);

            if (!added) {
                return new Response("El gerente con ID " + id + " ya existe", Status.BAD_REQUEST);
            }

            return new Response("Gerente creado correctamente", Status.CREATED, manager);
            

        } catch (NumberFormatException e) {
            return new Response("El ID debe ser numérico", Status.BAD_REQUEST);

        } catch (Exception e) {
            return new Response("Error inesperado: " + e.getMessage(), Status.INTERNAL_SERVER_ERROR);
        }
    }
}

