package com.gestioncitas_trabajofinal.model;

import java.util.Date;

/**
 * Representa a un paciente dentro del sistema de gestión de citas.
 * Hereda todos los atributos comunes desde la clase {@link Usuario}.
 * Actualmente no contiene campos adicionales, pero puede extenderse en el futuro.
 */
public class Paciente extends Usuario {

    /**
     * Constructor vacío.
     */
    public Paciente() {
        super();
    }

    /**
     * Constructor completo para crear una instancia de paciente con sus datos personales.
     *
     * @param username         Correo electrónico del paciente
     * @param password         Contraseña
     * @param nombres          Nombres del paciente
     * @param apellidos        Apellidos del paciente
     * @param telefono         Teléfono de contacto
     * @param dni              Documento de identidad
     * @param fecha_nacimiento Fecha de nacimiento
     * @param direccion        Dirección de residencia
     */
    public Paciente(String username, String password, String nombres, String apellidos,
                    String telefono, String dni, Date fecha_nacimiento, String direccion) {
        super(username, password, Rol.PACIENTE, nombres, apellidos, telefono, dni, fecha_nacimiento, direccion);
    }
}