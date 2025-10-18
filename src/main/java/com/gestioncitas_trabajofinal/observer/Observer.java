package com.gestioncitas_trabajofinal.observer;

import com.gestioncitas_trabajofinal.model.Cita;

/**
 * La interfaz Observer define el método que será llamado por el Sujeto
 * cuando ocurra un evento de interés.
 */
public interface Observer {
    
    /**
     * Este método es llamado cuando el Sujeto notifica un cambio.
     * @param cita El objeto Cita que acaba de ser creado.
     */
    void update(Cita cita);
}
