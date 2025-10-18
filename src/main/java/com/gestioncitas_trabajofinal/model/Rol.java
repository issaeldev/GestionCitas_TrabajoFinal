package com.gestioncitas_trabajofinal.model;

/**
 * Enumeración que representa los roles disponibles para los usuarios del sistema.
 * Cada rol define el tipo de acceso y funcionalidad permitida en la aplicación.
 */
public enum Rol 
{
    
    /** Representa a un paciente que puede registrarse y acceder a ConfirmarCita, Homepaciente, MisCitas, ProfilePaciente, ReservarCita. */
    PACIENTE,

    /** Representa a un médico que puede acceder a sus citas agendadas, HomeMedico, ProfileMedico, RegistrarDispo. */
    MEDICO;
}
