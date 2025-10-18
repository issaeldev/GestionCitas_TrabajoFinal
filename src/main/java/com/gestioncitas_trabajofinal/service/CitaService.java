package com.gestioncitas_trabajofinal.service;

import com.gestioncitas_trabajofinal.model.Cita;
import com.gestioncitas_trabajofinal.model.EstadoCita;
import com.gestioncitas_trabajofinal.model.Medico;
import com.gestioncitas_trabajofinal.model.Paciente;
import com.gestioncitas_trabajofinal.repository.CitaRepository;
import com.gestioncitas_trabajofinal.observer.Observer;
import com.gestioncitas_trabajofinal.observer.Subject;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Servicio para gestionar la lógica de negocio relacionada con las citas médicas.
 */
public class CitaService implements Subject{

    private final CitaRepository citaRepo;
    private final List<Observer> observers; // Lista para guardar los observadores

    /**
     * Constructor que inyecta la dependencia del repositorio de citas.
     * @param citaRepo La implementación del repositorio a utilizar.
     */
    public CitaService(CitaRepository citaRepo) {
        this.citaRepo = citaRepo;
        this.observers = new ArrayList<>();
    }

    /**
     * Registra una nueva cita, validando previamente que no haya conflictos de horario.
     * @param paciente  El paciente que solicita la cita.
     * @param medico    El médico con quien se agenda la cita.
     * @param fechaHora La fecha y hora exactas de la cita.
     * @param motivo    La razón o síntomas para la consulta.
     * @return {@code true} si la cita se registró con éxito, {@code false} si hay un conflicto.
     */
    public boolean registrarCita(Paciente paciente, Medico medico, LocalDateTime fechaHora, String motivo) {
        if (citaRepo.existeCitaParaMedicoEnFechaHora(medico.getId(), fechaHora)) {
            return false;
        }

        Cita cita = new Cita();
        cita.setPaciente(paciente);
        cita.setMedico(medico);
        cita.setFechaHora(fechaHora);
        cita.setMotivo(motivo);
        cita.setEstado(EstadoCita.EN_ESPERA);
        
        notifyObservers(cita);

        return citaRepo.guardar(cita);
    }
    
    /**
     * Obtiene la próxima cita pendiente de un paciente.
     * @param idPaciente El ID del paciente.
     * @return La próxima cita del paciente, o {@code null} si no tiene citas pendientes.
     */
    public Cita obtenerProximaCitaPorPaciente(int idPaciente) {
        return citaRepo.obtenerProximaCitaPorPaciente(idPaciente);
    }

    /**
     * Obtiene el historial completo de citas de un paciente.
     * @param idPaciente El ID del paciente.
     * @return Una lista con todas las citas del paciente.
     */
    public List<Cita> obtenerCitasPorPaciente(int idPaciente) {
        return citaRepo.obtenerCitasPorPaciente(idPaciente);
    }
    
    /**
     * Obtiene todas las citas agendadas para un médico específico.
     * @param idMedico El ID del médico.
     * @return Una lista con todas las citas del médico.
     */
    public List<Cita> obtenerCitasPorMedico(int idMedico) {
        return citaRepo.obtenerCitasPorMedico(idMedico);
    }
    
    /**
     * Actualiza el estado de una cita existente.
     * @param idCita El ID de la cita a actualizar.
     * @param estado El nuevo estado para la cita.
     */
    public void actualizarEstadoCita(int idCita, EstadoCita estado) {
        citaRepo.actualizarEstado(idCita, estado);
    }
    
    // --- Implementación de los métodos del patrón Subject ---

    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(Cita cita) {
        // Llama al método update() de cada observador registrado
        for (Observer observer : observers) {
            observer.update(cita);
        }
    }
}