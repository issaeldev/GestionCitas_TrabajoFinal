package com.gestioncitas_trabajofinal.controller;

import com.gestioncitas_trabajofinal.model.Medico;
import com.gestioncitas_trabajofinal.model.DisponibilidadMedica;
import com.gestioncitas_trabajofinal.repository.DisponibilidadRepositoryImpl;
import com.gestioncitas_trabajofinal.repository.UsuarioRepositoryImpl;
import com.gestioncitas_trabajofinal.service.MedicoService;
import com.gestioncitas_trabajofinal.service.DisponibilidadService;
import java.util.List;

/**
 * Controlador para operaciones que combinan información de Médicos y su Disponibilidad.
 */
public class MedicoController {

    private final MedicoService medicoService;
    private final DisponibilidadService disponibilidadService;

    /**
     * Constructor que inicializa todas las dependencias necesarias.
     */
    public MedicoController() {
        // Se crea el repositorio de usuario que será usado por MedicoService
        UsuarioRepositoryImpl usuarioRepository = new UsuarioRepositoryImpl();
        this.medicoService = new MedicoService(usuarioRepository);
        
        // Se crea el repositorio de disponibilidad que será usado por DisponibilidadService
        DisponibilidadRepositoryImpl disponibilidadRepository = new DisponibilidadRepositoryImpl();
        this.disponibilidadService = new DisponibilidadService(disponibilidadRepository);
    }

    /**
     * Obtiene una lista de médicos filtrada por su especialidad.
     * @param idEspecialidad El ID de la especialidad.
     * @return Una lista de médicos.
     */
    public List<Medico> obtenerPorEspecialidad(int idEspecialidad) {
        return medicoService.obtenerPorEspecialidad(idEspecialidad);
    }

    /**
     * Obtiene las franjas horarias disponibles para un médico.
     * @param idMedico El ID del médico.
     * @return Una lista de disponibilidades.
     */
    public List<DisponibilidadMedica> obtenerDisponibilidadesPorMedico(int idMedico) {
        return disponibilidadService.obtenerDisponibilidadesPorMedico(idMedico);
    }
}