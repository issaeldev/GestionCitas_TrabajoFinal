package com.gestioncitas_trabajofinal.service;

import com.gestioncitas_trabajofinal.model.Paciente;
import com.gestioncitas_trabajofinal.repository.UsuarioRepository;

/**
 * Servicio para la lógica de negocio relacionada con los pacientes.
 */
public class PacienteService {
    
    private final UsuarioRepository usuarioRepo;

    /**
     * Constructor que inyecta la dependencia del repositorio de usuarios.
     * @param usuarioRepo La implementación del repositorio unificado a utilizar.
     */
    public PacienteService(UsuarioRepository usuarioRepo) {
        this.usuarioRepo = usuarioRepo;
    }

    /**
     * Delega la actualización de los datos de un paciente al repositorio.
     * @param paciente El paciente con los datos a actualizar.
     * @return {@code true} si la actualización fue exitosa, {@code false} en caso contrario.
     */
    public boolean actualizarPaciente(Paciente paciente) {
        return usuarioRepo.actualizar(paciente);
    }
}