package com.gestioncitas_trabajofinal.service;

import com.gestioncitas_trabajofinal.strategy.LoginStrategy;
import com.gestioncitas_trabajofinal.model.Usuario;

/**
 * Contexto que utiliza una estrategia de inicio de sesión para redirigir al usuario
 * a la ventana correspondiente según su rol (Paciente o Médico).
 * @param <T> El tipo de usuario (Paciente o Medico).
 */
public class LoginContext<T extends Usuario> {
    
    private LoginStrategy<T> strategy;

    /**
     * Establece la estrategia de inicio de sesión a utilizar.
     * @param strategy La estrategia concreta (PacienteLoginStrategy o MedicoLoginStrategy).
     */
    public void setStrategy(LoginStrategy<T> strategy) {
        this.strategy = strategy;
    }

    /**
     * Ejecuta la estrategia de inicio de sesión para el usuario proporcionado.
     * @param usuario El usuario que ha iniciado sesión.
     * @param plainPassword La contraseña en texto plano del usuario.
     */
    public void ejecutarLogin(T usuario, String plainPassword) {
        if (strategy != null) {
            strategy.login(usuario, plainPassword);
        } else {
            System.out.println("Estrategia no definida.");
        }
    }
}