package com.gestioncitas_trabajofinal.model;

import java.util.Date;

/**
 * Representa a un médico dentro del sistema.
 * Hereda atributos de {@link Usuario} y añade su especialidad.
 */
public class Medico extends Usuario {

    /** Especialidad médica del doctor. */
    private Especialidad especialidad;

    /**
     * Constructor vacío.
     */
    public Medico() {
        super();
    }

    /**
     * Constructor completo para crear un médico con todos sus datos.
     *
     * @param username         Correo electrónico del médico
     * @param password         Contraseña
     * @param nombres          Nombres del médico
     * @param apellidos        Apellidos del médico
     * @param telefono         Teléfono de contacto
     * @param dni              Documento nacional de identidad
     * @param fecha_nacimiento Fecha de nacimiento
     * @param direccion        Dirección de residencia
     * @param especialidad     Especialidad médica del doctor
     */
    public Medico(String username, String password, String nombres, String apellidos, String telefono,
                  String dni, Date fecha_nacimiento, String direccion, Especialidad especialidad) {
        super(username, password, Rol.MEDICO, nombres, apellidos, telefono, dni, fecha_nacimiento, direccion);
        this.especialidad = especialidad;
    }

    /**
     * Obtiene la especialidad médica del doctor.
     * @return La especialidad asignada.
     */
    public Especialidad getEspecialidad() {
        return especialidad;
    }

    /**
     * Asigna una especialidad médica al doctor.
     * @param especialidad La especialidad a establecer.
     */
    public void setEspecialidad(Especialidad especialidad) {
        this.especialidad = especialidad;
    }
}