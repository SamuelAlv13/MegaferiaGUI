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
public class StandController {

    public static Response createStand(String id, String price) {
        try {
            long standId = Long.parseLong(id);
            double standPrice = Double.parseDouble(price);

            Stand stand = new Stand(standId, standPrice);

            boolean added = Storage.getInstance().addStand(stand);

            if (!added) {
                return new Response("El stand con ID " + id + " ya existe", Status.BAD_REQUEST);
            }

            return new Response("Stand creado correctamente", Status.CREATED, stand);

        } catch (NumberFormatException e) {
            return new Response("Formato inválido: id debe ser entero y precio numérico", Status.BAD_REQUEST);
        } catch (Exception e) {
            return new Response("Error inesperado: " + e.getMessage(), Status.INTERNAL_SERVER_ERROR);
        }
    }
}


