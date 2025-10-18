package com.gestioncitas_trabajofinal.service;

import com.gestioncitas_trabajofinal.model.Medico;
import com.gestioncitas_trabajofinal.repository.UsuarioRepository;
import java.util.List;

/**
 * Servicio para gestionar la lógica de negocio relacionada con los médicos.
 */
public class MedicoService {
    
    private final UsuarioRepository usuarioRepo;

    /**
     * Constructor que inyecta la dependencia del repositorio de usuarios.
     * @param usuarioRepo La implementación del repositorio unificado a utilizar.
     */
    public MedicoService(UsuarioRepository usuarioRepo) {
        this.usuarioRepo = usuarioRepo;
    }

    /**
     * Obtiene una lista de médicos que pertenecen a una especialidad específica.
     * @param idEspecialidad El ID de la especialidad.
     * @return Una lista de objetos Medico.
     */
    public List<Medico> obtenerPorEspecialidad(int idEspecialidad) {
        return usuarioRepo.obtenerMedicosPorEspecialidad(idEspecialidad);
    }
}