package com.gestioncitas_trabajofinal.repository;

import com.gestioncitas_trabajofinal.model.Usuario;

public interface LoginRepository 
{
    /**
     * Autentica a un usuario por sus credenciales (username y password).
     *
     * @param username Correo electrónico del usuario
     * @param password Contraseña del usuario
     * @return Un objeto Usuario (o una subclase como Paciente o Medico) si las credenciales son válidas; null si no.
     */
    Usuario autenticarUsuario(String username, String password);
}