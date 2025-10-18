package com.gestioncitas_trabajofinal.service;

import com.gestioncitas_trabajofinal.repository.DisponibilidadRepository;
import com.gestioncitas_trabajofinal.model.DisponibilidadMedica;
import java.util.List;
import javax.swing.JOptionPane;

/**
 * Servicio para gestionar la lógica de negocio de la disponibilidad de los médicos.
 */
public class DisponibilidadService {
    
    private final DisponibilidadRepository disponibilidadRepo;

    /**
     * Constructor que inyecta la dependencia del repositorio de disponibilidad.
     * @param disponibilidadRepo La implementación del repositorio a utilizar.
     */
    public DisponibilidadService(DisponibilidadRepository disponibilidadRepo) {
        this.disponibilidadRepo = disponibilidadRepo;
    }

    /**
     * Registra una nueva franja de disponibilidad para un médico.
     * Valida que el horario sea coherente y que no se solape con otros ya existentes.
     * @param disponibilidad La disponibilidad médica a registrar.
     * @return {@code true} si se registró correctamente, {@code false} si hubo un error o conflicto.
     */
    public boolean registrarDisponibilidad(DisponibilidadMedica disponibilidad) {
        if (disponibilidad.getHoraInicio().isAfter(disponibilidad.getHoraFin()) ||
            disponibilidad.getHoraInicio().equals(disponibilidad.getHoraFin())) {
            JOptionPane.showMessageDialog(null, "La hora de inicio debe ser menor a la hora de fin.");
            return false;
        }

        List<DisponibilidadMedica> existentes = disponibilidadRepo.obtenerPorMedico(disponibilidad.getMedico().getId());

        for (DisponibilidadMedica d : existentes) {
            if (d.getDiaSemana() == disponibilidad.getDiaSemana()) {
                boolean seCruza = (disponibilidad.getHoraInicio().isBefore(d.getHoraFin()) &&
                                 disponibilidad.getHoraFin().isAfter(d.getHoraInicio()));
                if (seCruza) {
                    JOptionPane.showMessageDialog(null, "Ya existe una disponibilidad registrada para el médico en ese horario.", "Conflicto de horario", JOptionPane.WARNING_MESSAGE);
                    return false;
                }
            }
        }

        try {
            disponibilidadRepo.guardar(disponibilidad);
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al registrar disponibilidad.\nDetalles: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    /**
     * Recupera todas las disponibilidades registradas para un médico específico.
     * @param idMedico ID del médico.
     * @return Una lista de las disponibilidades del médico.
     */
    public List<DisponibilidadMedica> obtenerDisponibilidadesPorMedico(int idMedico) {
        return disponibilidadRepo.obtenerPorMedico(idMedico);
    }
    
    /**
     * Elimina todas las disponibilidades de un médico.
     * Útil para cuando se quiere reemplazar el horario completo.
     * @param idMedico El ID del médico cuyas disponibilidades se eliminarán.
     */
    public void eliminarDisponibilidadesPorMedico(int idMedico) {
        disponibilidadRepo.eliminarPorMedico(idMedico); 
    }
}