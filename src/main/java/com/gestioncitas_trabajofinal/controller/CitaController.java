package com.gestioncitas_trabajofinal.controller;

import com.gestioncitas_trabajofinal.model.*;
import com.gestioncitas_trabajofinal.model.*;
import com.gestioncitas_trabajofinal.repository.CitaRepositoryImpl;
import com.gestioncitas_trabajofinal.service.CitaService;
import com.gestioncitas_trabajofinal.observer.EmailNotifierObserver;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

/**
 * Controlador para gestionar las operaciones relacionadas con las Citas.
 * Actúa como intermediario entre la vista y el servicio de citas.
 */
public class CitaController {

    private final CitaService citaService;

    /**
     * Constructor que inicializa el controlador y sus dependencias.
     * Crea las implementaciones de repositorio y servicio necesarias.
     */
    public CitaController() {
        this.citaService = new CitaService(new CitaRepositoryImpl());
        this.citaService.addObserver(new EmailNotifierObserver());
    }

    /**
     * Procesa la solicitud para registrar una nueva cita.
     * @param paciente El paciente de la cita.
     * @param medico El médico para la cita.
     * @param dia El día de la semana seleccionado.
     * @param hora La hora seleccionada.
     * @param motivo El motivo de la consulta.
     * @return true si la cita fue registrada exitosamente.
     */
    public boolean registrarCita(Paciente paciente, Medico medico, DiaSemana dia, LocalTime hora, String motivo) {
        LocalDate fechaProxima = obtenerProximaFecha(dia);
        LocalDateTime fechaHora = LocalDateTime.of(fechaProxima, hora);
        return citaService.registrarCita(paciente, medico, fechaHora, motivo);
    }
    
    /**
     * Obtiene la próxima cita pendiente para un paciente.
     * @param idPaciente El ID del paciente.
     * @return La próxima cita o null si no existe.
     */
    public Cita obtenerProximaCita(int idPaciente) {
        return citaService.obtenerProximaCitaPorPaciente(idPaciente);
    }
    
    /**
     * Obtiene el historial de citas de un paciente.
     * @param idPaciente El ID del paciente.
     * @return Una lista de todas las citas del paciente.
     */
    public List<Cita> obtenerCitasPorPaciente(int idPaciente) {
        return citaService.obtenerCitasPorPaciente(idPaciente);
    }
    
    /**
     * Obtiene todas las citas agendadas para un médico.
     * @param idMedico El ID del médico.
     * @return Una lista de todas las citas del médico.
     */
    public List<Cita> obtenerCitasPorMedico(int idMedico) {
        return citaService.obtenerCitasPorMedico(idMedico);
    }
    
    /**
     * Cancela una cita específica.
     * @param idCita El ID de la cita a cancelar.
     */
    public void cancelarCita(int idCita) {
        citaService.actualizarEstadoCita(idCita, EstadoCita.CANCELADA);
    }
    
    /**
     * Actualiza el estado de una cita.
     * @param idCita El ID de la cita a actualizar.
     * @param nuevoEstado El nuevo estado (REALIZADA, NO_ASISTIO, etc.).
     */
    public void actualizarEstadoCita(int idCita, EstadoCita nuevoEstado) {
        citaService.actualizarEstadoCita(idCita, nuevoEstado);
    }

    private LocalDate obtenerProximaFecha(DiaSemana dia) {
        DayOfWeek targetDay = convertirADayOfWeek(dia);
        LocalDate today = LocalDate.now();
        int daysUntil = (targetDay.getValue() - today.getDayOfWeek().getValue() + 7) % 7;
        daysUntil = (daysUntil == 0) ? 7 : daysUntil;
        return today.plusDays(daysUntil);
    }

    private DayOfWeek convertirADayOfWeek(DiaSemana dia) {
        return switch (dia) {
            case LUNES -> DayOfWeek.MONDAY;
            case MARTES -> DayOfWeek.TUESDAY;
            case MIERCOLES -> DayOfWeek.WEDNESDAY;
            case JUEVES -> DayOfWeek.THURSDAY;
            case VIERNES -> DayOfWeek.FRIDAY;
            case SABADO -> DayOfWeek.SATURDAY;
            case DOMINGO -> DayOfWeek.SUNDAY;
        };
    }
}