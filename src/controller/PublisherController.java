/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import controller.utils.Response;
import controller.utils.Status;
import java.util.ArrayList;
import model.Publisher;
import model.Storage.Storage;
import model.person.Manager;

/**
 *
 * @author mayma
 */
public class PublisherController {
    
    private static final Storage storage = Storage.getInstance();

    public static ArrayList<Object[]> getPublisherTableData() {
        ArrayList<Object[]> data = new ArrayList<>();
        for (Publisher publisher : storage.getAllPublishers()) {
            Object[] row = new Object[]{
                publisher.getNit(),
                publisher.getName(),
                publisher.getAddress(),
                publisher.getManager() != null ? publisher.getManager().getFullname() : "",
                publisher.getStandQuantity()
            };
            data.add(row);
        }
        return data;
    }

    public static Response createPublisher(String nit, String name, String address, String managerIdString) {

        
        if (nit == null || nit.trim().isEmpty()) {
            return new Response("El NIT no puede estar vacío", Status.BAD_REQUEST);
        }

        if (!nit.matches("\\d{3}\\.\\d{3}\\.\\d{3}-\\d")) {
            return new Response("El NIT debe tener el formato XXX.XXX.XXX-X", Status.BAD_REQUEST);
        }

        if (name == null || name.trim().isEmpty()) {
            return new Response("El nombre no puede estar vacío", Status.BAD_REQUEST);
        }

        if (address == null || address.trim().isEmpty()) {
            return new Response("La dirección no puede estar vacía", Status.BAD_REQUEST);
        }

        long managerId;
        try {
            managerId = Long.parseLong(managerIdString);
        } catch (NumberFormatException e) {
            return new Response("El ID del gerente debe ser numérico", Status.BAD_REQUEST);
        }

        Storage storage = Storage.getInstance();

        
        Manager manager = storage.getManager(managerId);
        if (manager == null) {
            return new Response("El gerente seleccionado no existe", Status.NOT_FOUND);
        }

        
        if (storage.getPublisher(nit) != null) {
            return new Response("Ya existe una editorial con el NIT " + nit, Status.BAD_REQUEST);
        }

        
        Publisher publisher = new Publisher(nit, name.trim(), address.trim(), manager);

        boolean added = storage.addPublisher(publisher);
        if (!added) {
            return new Response("No se pudo registrar la editorial", Status.INTERNAL_SERVER_ERROR);
        }

        
        Publisher cloned = null;
        cloned = publisher.clone();

        return new Response("Editorial registrada correctamente", Status.CREATED, cloned);
    }
}
