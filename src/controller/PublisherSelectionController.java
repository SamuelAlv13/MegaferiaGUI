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
    public class PublisherSelectionController {

        private static final ArrayList<String> selectedEditorials = new ArrayList<>();

        public static Response addEditorial(String editorialId) {
            if (editorialId == null || editorialId.isEmpty()) {
                return new Response("Editorial inválida", Status.BAD_REQUEST);
            }
            if (selectedEditorials.contains(editorialId)) {
                return new Response("La editorial ya está agregada", Status.BAD_REQUEST);
            }
            selectedEditorials.add(editorialId);
            return new Response("Editorial agregada correctamente", Status.CREATED, editorialId);
        }

        public static Response removeEditorial(String editorialId) {
            if (editorialId == null || editorialId.isEmpty()) {
                return new Response("Editorial inválida", Status.BAD_REQUEST);
            }
            if (!selectedEditorials.contains(editorialId)) {
                return new Response("La editorial no está en la lista", Status.BAD_REQUEST);
            }
            selectedEditorials.remove(editorialId);
            return new Response("Editorial eliminada correctamente", Status.CREATED, editorialId);
        }

        public static ArrayList<String> getSelectedEditorials() {
            return new ArrayList<>(selectedEditorials);
        }

        
        public static void clearSelection() {
            selectedEditorials.clear();
        }
    }
