/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;
import java.util.ArrayList;
import model.interfaz.Prototype;

/**
 *
 * @author edangulo
 */
public class Stand implements Prototype<Stand> {

    private long id;
    private double price;
    private ArrayList<Publisher> publishers;

    public Stand(long id, double price) {
        this.id = id;
        this.price = price;
        this.publishers = new ArrayList<>();
    }

    public void addPublisher(Publisher publisher) {
        this.publishers.add(publisher);
    }

    public long getId() {
        return id;
    }

    public double getPrice() {
        return price;
    }

    public ArrayList<Publisher> getPublishers() {
        return new ArrayList<>(publishers);
    }

    public int getPublisherQuantity() {
        return this.publishers.size();
    }

    // Constructor copia (para clonación)
    public Stand(Stand other) {
        this.id = other.id;
        this.price = other.price;
        this.publishers = new ArrayList<>(other.publishers);
    }

    // Implementación de Prototype
    @Override
    public Stand clone() {
        return new Stand(this);
    }
}
