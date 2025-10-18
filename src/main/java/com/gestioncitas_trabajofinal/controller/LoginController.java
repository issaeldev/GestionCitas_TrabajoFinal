package com.gestioncitas_trabajofinal.controller;

import com.gestioncitas_trabajofinal.repository.LoginRepositoryImpl;
import com.gestioncitas_trabajofinal.model.Usuario;

/**
 * Controlador encargado de manejar la lógica de autenticación de usuarios.
 * No utiliza un servicio intermedio por la simplicidad de la operación.
 */
public class LoginController {
    
    private final LoginRepositoryImpl loginRepo;

    /**
     * Constructor que inicializa el repositorio de login.
     */
    public LoginController() {
        this.loginRepo = new LoginRepositoryImpl();
    }

    /**
     * Valida un usuario con las credenciales ingresadas.
     * @param username Correo del usuario.
     * @param password Contraseña del usuario.
     * @return El objeto Usuario si las credenciales son correctas, o null si no coinciden.
     */
    public Usuario login(String username, String password) {
        return loginRepo.autenticarUsuario(username, password);
    }
}