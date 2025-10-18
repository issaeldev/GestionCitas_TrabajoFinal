package com.gestioncitas_trabajofinal.repository;

import com.gestioncitas_trabajofinal.model.Cita;
import com.gestioncitas_trabajofinal.model.EstadoCita;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Interfaz para las operaciones de persistencia de datos de la entidad Cita.
 */
public interface CitaRepository {
    /**
     * Guarda una nueva cita en la base de datos.
     *
     * @param cita La cita a guardar.
     * @return true si se guardó correctamente, false en caso contrario.
     */
    boolean guardar(Cita cita);

    /**
     * Verifica si ya existe una cita para un médico en una fecha y hora específicas.
     *
     * @param idMedico   ID del médico a verificar.
     * @param fechaHora  Fecha y hora de la posible cita.
     * @return true si ya existe una cita, false si no.
     */
    boolean existeCitaParaMedico(int idMedico, LocalDateTime fechaHora);

    /**
     * Obtiene todas las citas agendadas para un médico específico.
     *
     * @param idMedico ID del médico.
     * @return Una lista de citas.
     */
    List<Cita> obtenerCitasPorMedico(int idMedico);

    /**
     * Obtiene todas las citas de un paciente específico.
     *
     * @param idPaciente ID del paciente.
     * @return Una lista de citas.
     */
    List<Cita> obtenerCitasPorPaciente(int idPaciente);

    /**
     * Verifica si un médico tiene una cita activa (no cancelada) en una fecha y hora.
     *
     * @param idMedico   ID del médico.
     * @param fechaHora  Fecha y hora a consultar.
     * @return true si existe una cita activa, false si no.
     */
    boolean existeCitaParaMedicoEnFechaHora(int idMedico, LocalDateTime fechaHora);
    
    /**
     * Obtiene la próxima cita pendiente de un paciente.
     *
     * @param idPaciente ID del paciente.
     * @return La próxima cita o null si no hay.
     */
    Cita obtenerProximaCitaPorPaciente(int idPaciente);

    /**
     * Actualiza el estado de una cita (ej. EN_ESPERA, REALIZADA, CANCELADA).
     *
     * @param idCita ID de la cita a actualizar.
     * @param estado El nuevo estado de la cita.
     */
    void actualizarEstado(int idCita, EstadoCita estado);
}