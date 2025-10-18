package com.gestioncitas_trabajofinal.controller;

import com.gestioncitas_trabajofinal.model.DisponibilidadMedica;
import com.gestioncitas_trabajofinal.repository.DisponibilidadRepositoryImpl;
import com.gestioncitas_trabajofinal.service.DisponibilidadService;
import java.util.List;

/**
 * Controlador para manejar la lógica de la disponibilidad horaria de los médicos.
 */
public class DisponibilidadController {

    private final DisponibilidadService disponibilidadService;

    /**
     * Constructor que inicializa las dependencias del controlador.
     */
    public DisponibilidadController() {
        this.disponibilidadService = new DisponibilidadService(new DisponibilidadRepositoryImpl());
    }

    /**
     * Registra una nueva franja de disponibilidad para un médico.
     * @param disponibilidad El objeto de disponibilidad a guardar.
     * @return true si se registró correctamente.
     */
    public boolean registrarDisponibilidad(DisponibilidadMedica disponibilidad) {
        return disponibilidadService.registrarDisponibilidad(disponibilidad);
    }

    /**
     * Obtiene todas las disponibilidades de un médico específico.
     * @param idMedico El ID del médico.
     * @return Una lista con las disponibilidades del médico.
     */
    public List<DisponibilidadMedica> obtenerDisponibilidadesPorMedico(int idMedico) {
        return disponibilidadService.obtenerDisponibilidadesPorMedico(idMedico);
    }
    
    /**
     * Elimina todas las disponibilidades existentes para un médico.
     * @param idMedico El ID del médico.
     */
    public void eliminarDisponibilidadesPorMedico(int idMedico) {
        disponibilidadService.eliminarDisponibilidadesPorMedico(idMedico);
    }
}