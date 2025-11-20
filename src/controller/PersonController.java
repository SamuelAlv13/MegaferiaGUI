/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.util.ArrayList;
import model.Storage.Storage;
import model.person.Author;
import model.person.Manager;
import model.person.Narrator;

/**
 *
 * @author mayma
 */
public class PersonController {

    private static final Storage storage = Storage.getInstance();

    public static ArrayList<Object[]> getPersonTableData() {
        ArrayList<Object[]> data = new ArrayList<>();

        
        for (Author author : storage.getAllAuthors()) {
            Author cloneAuthor;
            try {
                cloneAuthor = author.clone();
            } catch (CloneNotSupportedException e) {
                continue; 
            }
            data.add(new Object[]{
                cloneAuthor.getId(),
                cloneAuthor.getFullname(),
                "Autor",
                "-",
                cloneAuthor.getBookQuantity()
            });
        }

        
        for (Manager manager : storage.getAllManagers()) {
            Manager cloneManager;
            try {
                cloneManager = manager.clone();
            } catch (CloneNotSupportedException e) {
                continue;
            }
            data.add(new Object[]{
                cloneManager.getId(),
                cloneManager.getFullname(),
                "Gerente",
                cloneManager.getPublisher() != null ? cloneManager.getPublisher().getName() : "-",
                0
            });
        }

        
        for (Narrator narrator : storage.getAllNarrators()) {
            Narrator cloneNarrator;
            try {
                cloneNarrator = narrator.clone();
            } catch (CloneNotSupportedException e) {
                continue;
            }
            data.add(new Object[]{
                cloneNarrator.getId(),
                cloneNarrator.getFullname(),
                "Narrador",
                "-",
                cloneNarrator.getBookQuantity()
            });
        }

        return data;
    }
}

