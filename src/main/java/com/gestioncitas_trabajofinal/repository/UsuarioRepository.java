package com.gestioncitas_trabajofinal.repository;

import com.gestioncitas_trabajofinal.model.Medico;
import com.gestioncitas_trabajofinal.model.Usuario;
import java.util.List;

/**
 * Interfaz de repositorio UNIFICADA para operaciones CRUD relacionadas con Usuario y sus subclases.
 * Define los métodos necesarios para guardar, buscar, actualizar y validar usuarios.
 */
public interface UsuarioRepository {

    /**
     * Guarda un nuevo usuario (Paciente o Medico) en la base de datos.
     * @param usuario El usuario a registrar.
     */
    void guardar(Usuario usuario);

    /**
     * Actualiza los datos de un usuario existente en la base de datos.
     * @param usuario El usuario con los datos modificados.
     * @return true si la actualización fue exitosa, false en caso contrario.
     */
    boolean actualizar(Usuario usuario);
    
    /**
     * Busca un usuario por su ID y devuelve la instancia correcta (Paciente o Medico).
     * @param id El ID del usuario a buscar.
     * @return Un objeto Usuario (o subclase) si se encuentra, de lo contrario null.
     */
    Usuario obtenerPorId(int id);
    
    /**
     * Obtiene una lista de médicos que pertenecen a una especialidad específica.
     * @param idEspecialidad El ID de la especialidad.
     * @return Una lista de objetos Medico.
     */
    List<Medico> obtenerMedicosPorEspecialidad(int idEspecialidad);

    /**
     * Verifica si ya existe un usuario con el mismo correo electrónico.
     * @param username El correo electrónico a verificar.
     * @return true si el usuario ya existe, false en caso contrario.
     */
    boolean existeUsername(String username);

    /**
     * Verifica si ya existe un usuario con el mismo DNI.
     * @param dni El DNI a verificar.
     * @return true si el DNI ya está registrado, false en caso contrario.
     */
    boolean existeDNI(String dni);
}