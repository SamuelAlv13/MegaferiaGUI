/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.person.repository;

/**
 *
 * @author aleke
 */
import model.person.Author;

public interface IAuthorRepository {

    Author getById(long id);

    boolean save(Author author);
}
