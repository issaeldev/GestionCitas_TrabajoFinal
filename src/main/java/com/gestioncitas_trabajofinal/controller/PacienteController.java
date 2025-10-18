package com.gestioncitas_trabajofinal.controller;

import com.gestioncitas_trabajofinal.model.Paciente;
import com.gestioncitas_trabajofinal.repository.UsuarioRepositoryImpl;
import com.gestioncitas_trabajofinal.service.PacienteService;

/**
 * Controlador para gestionar las operaciones del perfil del paciente.
 */
public class PacienteController {
    
    private final PacienteService pacienteService;

    /**
     * Constructor que inicializa las dependencias del controlador.
     */
    public PacienteController() {
        this.pacienteService = new PacienteService(new UsuarioRepositoryImpl());
    }

    /**
     * Procesa la solicitud para actualizar los datos de un paciente.
     * @param paciente El objeto paciente con la información actualizada.
     * @return true si la actualización fue exitosa.
     */
    public boolean actualizarDatosPaciente(Paciente paciente) {
        return pacienteService.actualizarPaciente(paciente);
    }
}