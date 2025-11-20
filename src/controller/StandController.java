/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import controller.utils.Response;
import controller.utils.Status;
import java.util.ArrayList;
import model.Stand;
import model.Storage.Storage;

/**
 *
 * @author mayma
 */
public class StandController {

    public static Response createStand(String id, String price) {

        long standId;
        double standPrice;

        try {
            standId = Long.parseLong(id);
            standPrice = Double.parseDouble(price);
        } catch (NumberFormatException e) {
            return new Response(
                    "Formato inválido: id debe ser entero y precio numérico",
                    Status.BAD_REQUEST
            );
        }

        if (standId < 0 || String.valueOf(standId).length() > 15) {
            return new Response("ID del stand no es válido", Status.BAD_REQUEST);
        }

        if (standPrice <= 0) {
            return new Response("El precio debe ser mayor a 0", Status.BAD_REQUEST);
        }

        Storage storage = Storage.getInstance();
        if (storage.getStand(standId) != null) {
            return new Response("El stand con ID " + id + " ya existe", Status.BAD_REQUEST);
        }

        Stand stand = new Stand(standId, standPrice);
        boolean added = storage.addStand(stand);

        if (!added) {
            return new Response("No se pudo guardar el stand", Status.INTERNAL_SERVER_ERROR);
        }

        Stand cloned = stand.clone();

        return new Response("Stand creado correctamente", Status.CREATED, cloned);
    }

    public static ArrayList<Object[]> getStandTableData() {
        ArrayList<Object[]> data = new ArrayList<>();

        for (Stand stand : Storage.getInstance().getAllStands()) {
            Stand clonedStand;
            clonedStand = stand.clone();

            String publishers = "";
            if (clonedStand.getPublisherQuantity() > 0) {
                publishers += clonedStand.getPublishers().get(0).getName();
                for (int i = 1; i < clonedStand.getPublisherQuantity(); i++) {
                    publishers += ", " + clonedStand.getPublishers().get(i).getName();
                }
            }

            data.add(new Object[]{
                clonedStand.getId(),
                clonedStand.getPrice(),
                clonedStand.getPublisherQuantity() > 0 ? "Si" : "No",
                publishers
            });
        }

        return data;
    }
}
