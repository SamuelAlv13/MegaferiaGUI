/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.interfaze;

/**
 *
 * @author Samuel Alvarado
 */
public interface Prototype<T> {
    T clone() throws CloneNotSupportedException;
}
