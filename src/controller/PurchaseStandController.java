/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import controller.utils.Response;
import controller.utils.Status;
import java.util.ArrayList;
import model.Publisher;
import model.Stand;
import model.Storage.Storage;

/**
 *
 * @author mayma
 */
    public class PurchaseStandController {


        public static Response completePurchase(ArrayList<String> standIds, ArrayList<String> publisherIds) {

            Storage storage = Storage.getInstance();
            ArrayList<Stand> stands = new ArrayList<>();
            ArrayList<Publisher> publishers = new ArrayList<>();

            
            for (String standId : standIds) {
                if (standId == null || standId.isBlank()) continue;
                long id;
                try {
                    id = Long.parseLong(standId);
                } catch (NumberFormatException e) {
                    return new Response("ID de Stand inválido: " + standId, Status.BAD_REQUEST);
                }
                Stand stand = storage.getStand(id);
                if (stand == null) {
                    return new Response("No se encontró el Stand con ID " + id, Status.NOT_FOUND);
                }
                if (!stands.contains(stand)) {
                    stands.add(stand);
                }
            }

            
            for (String publisherData : publisherIds) {
                if (publisherData == null || publisherData.isBlank()) continue; 
                String nit = publisherData.split(" ")[1].replace("(", "").replace(")", "");
                Publisher publisher = storage.getPublisher(nit);
                if (publisher == null) {
                    return new Response("No se encontró la Editorial con NIT " + nit, Status.NOT_FOUND);
                }
                if (!publishers.contains(publisher)) {
                    publishers.add(publisher);
                }
            }

            
            for (Stand stand : stands) {
                for (Publisher publisher : publishers) {
                    stand.addPublisher(publisher);
                    publisher.addStand(stand);
                }
            }

           
            ArrayList<Stand> clonedStands = new ArrayList<>();
            for (Stand stand : stands) {
                clonedStands.add(stand.clone());
            }

            ArrayList<Publisher> clonedPublishers = new ArrayList<>();
            for (Publisher publisher : publishers) {
                clonedPublishers.add(publisher.clone());
            }

            
            StandSelectionController.clearSelection();
            PublisherSelectionController.clearSelection();

            return new Response("Compra de stands completada correctamente", Status.CREATED, new Object[]{clonedStands, clonedPublishers});
        }
    }