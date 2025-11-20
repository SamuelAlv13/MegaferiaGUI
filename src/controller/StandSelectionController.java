/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import controller.utils.Response;
import controller.utils.Status;
import java.util.ArrayList;

/**
 *
 * @author mayma
 */
public class StandSelectionController {

    private static final ArrayList<String> selectedStands = new ArrayList<>();

    public static Response addStand(String standId) {
        if (standId == null || standId.isEmpty()) {
            return new Response("ID de Stand inválido", Status.BAD_REQUEST);
        }
        if (selectedStands.contains(standId)) {
            return new Response("El Stand ya está agregado", Status.BAD_REQUEST);
        }
        selectedStands.add(standId);
        return new Response("Stand agregado correctamente", Status.CREATED, standId);
    }

    public static Response removeStand(String standId) {
        if (standId == null || standId.isEmpty()) {
            return new Response("ID de Stand inválido", Status.BAD_REQUEST);
        }
        if (!selectedStands.contains(standId)) {
            return new Response("El Stand no está en la lista", Status.BAD_REQUEST);
        }
        selectedStands.remove(standId);
        return new Response("Stand eliminado correctamente", Status.CREATED, standId);
    }

    public static ArrayList<String> getSelectedStands() {
        return new ArrayList<>(selectedStands); 
    }

    public static void clearSelection() {
        selectedStands.clear();
    }
}
