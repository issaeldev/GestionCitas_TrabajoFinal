package com.gestioncitas_trabajofinal.observer;

import com.gestioncitas_trabajofinal.model.Cita;

/**
 * La interfaz Subject define los métodos que el Sujeto debe implementar
 * para gestionar a sus observadores.
 */
public interface Subject {
    
    /**
     * Añade un nuevo observador a la lista de notificaciones.
     * @param observer El observador a registrar.
     */
    void addObserver(Observer observer);

    /**
     * Elimina un observador de la lista de notificaciones.
     * @param observer El observador a eliminar.
     */
    void removeObserver(Observer observer);

    /**
     * Notifica a todos los observadores registrados sobre un evento.
     * @param cita La cita recién creada que se pasará a los observadores.
     */
    void notifyObservers(Cita cita);
}
