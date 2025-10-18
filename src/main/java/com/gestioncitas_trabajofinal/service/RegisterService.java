package com.gestioncitas_trabajofinal.service;

import com.gestioncitas_trabajofinal.model.Usuario;
import com.gestioncitas_trabajofinal.repository.UsuarioRepository;
import javax.swing.JOptionPane;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.Period;
import java.util.Date;

/**
 * Servicio encargado de la lógica de registro de nuevos usuarios.
 */
public class RegisterService {
    
    private final UsuarioRepository usuarioRepo;

    /**
     * Constructor que inyecta la dependencia del repositorio de usuarios.
     * @param usuarioRepo La implementación del repositorio a utilizar.
     */
    public RegisterService(UsuarioRepository usuarioRepo) {
        this.usuarioRepo = usuarioRepo;
    }

    /**
     * Registra un nuevo usuario si cumple con las reglas de negocio (correo y DNI únicos, mayor de edad).
     * @param usuario El usuario a registrar.
     * @return {@code true} si se registró correctamente, {@code false} si alguna regla falla.
     */
    public boolean registrarUsuario(Usuario usuario) {
        if (usuarioRepo.existeUsername(usuario.getUsername())) {
            JOptionPane.showMessageDialog(null, "El correo ingresado ya pertenece a un usuario registrado.", "Error de registro", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (usuarioRepo.existeDNI(usuario.getDni())) {
            JOptionPane.showMessageDialog(null, "El DNI ingresado ya pertenece a un usuario registrado.", "Error de registro", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (!esMayorDeEdad(usuario.getFechaNacimiento())) {
            JOptionPane.showMessageDialog(null, "Debes tener al menos 18 años para registrarte.", "Error de edad", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        usuarioRepo.guardar(usuario);
        JOptionPane.showMessageDialog(null, "Cuenta creada exitosamente.", "Registro completado", JOptionPane.INFORMATION_MESSAGE);
        return true;
    }

    /**
     * Valida si el usuario tiene al menos 18 años.
     * @param fechaNacimiento Fecha de nacimiento del usuario.
     * @return {@code true} si es mayor o igual a 18 años, {@code false} si no.
     */
    private boolean esMayorDeEdad(Date fechaNacimiento) {
        if (fechaNacimiento == null) return false;
        LocalDate nacimiento = fechaNacimiento.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate hoy = LocalDate.now();
        return Period.between(nacimiento, hoy).getYears() >= 18;
    }
}