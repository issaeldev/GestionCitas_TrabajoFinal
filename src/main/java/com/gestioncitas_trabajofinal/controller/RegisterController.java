package com.gestioncitas_trabajofinal.controller;

import com.gestioncitas_trabajofinal.model.Paciente;
import com.gestioncitas_trabajofinal.repository.UsuarioRepositoryImpl;
import com.gestioncitas_trabajofinal.service.RegisterService;
import java.util.Date;

/**
 * Controlador responsable del manejo del registro de nuevos pacientes.
 */
public class RegisterController {

    private final RegisterService service;

    /**
     * Constructor que inicializa las dependencias del controlador.
     */
    public RegisterController() {
        this.service = new RegisterService(new UsuarioRepositoryImpl());
    }

    /**
     * Registra un nuevo paciente utilizando los datos proporcionados desde la vista.
     * @param username Correo electrónico del paciente.
     * @param password Contraseña del paciente.
     * @param nombres Nombres del paciente.
     * @param apellidos Apellidos del paciente.
     * @param telefono Número de teléfono.
     * @param dni Documento nacional de identidad.
     * @param fechaNacimiento Fecha de nacimiento.
     * @param direccion Dirección de domicilio.
     * @return true si el paciente fue registrado exitosamente.
     */
    public boolean registrarPaciente(String username, String password, String nombres, String apellidos,
                                     String telefono, String dni, Date fechaNacimiento, String direccion) {
        Paciente paciente = new Paciente(username, password, nombres, apellidos, telefono, dni, fechaNacimiento, direccion);
        return service.registrarUsuario(paciente);
    }
}