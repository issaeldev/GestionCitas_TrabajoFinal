package com.gestioncitas_trabajofinal.model;

import java.time.LocalTime;

/**
 * Representa un bloque de tiempo en el que un médico está disponible para atender citas.
 */
public class DisponibilidadMedica {
    private int id;
    private Medico medico;
    private DiaSemana diaSemana;
    private LocalTime horaInicio;
    private LocalTime horaFin;

    public DisponibilidadMedica() {}

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    public DiaSemana getDiaSemana() {
        return diaSemana;
    }

    public void setDiaSemana(DiaSemana diaSemana) {
        this.diaSemana = diaSemana;
    }

    public LocalTime getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(LocalTime horaInicio) {
        this.horaInicio = horaInicio;
    }

    public LocalTime getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(LocalTime horaFin) {
        this.horaFin = horaFin;
    }
}