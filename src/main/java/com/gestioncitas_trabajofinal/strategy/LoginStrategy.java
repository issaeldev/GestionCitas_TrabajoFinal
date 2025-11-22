package com.gestioncitas_trabajofinal.strategy;
import com.gestioncitas_trabajofinal.model.Usuario;

public interface LoginStrategy<T extends Usuario>
{
    /**
     * Método para el inicio de sesión para un usuario específico (diferentes variaciones de un algoritmo).
     *
     * Este método se encarga de realizar la autenticación o el proceso necesario
     * para que un usuario de tipo {@code T} inicie sesión en el sistema.
     *
     * @param usuario El usuario que está intentando iniciar sesión. Este parámetro
     *                debe ser de tipo {@code T}, que es un tipo que extiende la clase {@link Usuario}.
     * @param plainPassword La contraseña en texto plano del usuario.
     */
    void login(T usuario, String plainPassword);
}
    