package com.gestioncitas_trabajofinal.model;

import java.util.Date;

/**
 * Clase base que representa a un usuario del sistema.
 * Puede ser un paciente o un médico, según el valor del atributo {@code rol}.
 * Esta clase contiene la información personal común a ambos tipos de usuario.
 */
public class Usuario {
    private int id;
    private String username;
    private String password;
    private Rol rol;
    private String nombre;
    private String apellido;
    private String telefono;
    private Date fechaNacimiento;
    private String direccion;
    private String dni;

    /**
     * Constructor vacío (para uso posterior).
     */
    public Usuario() {}

    /**
     * Constructor con todos los atributos requeridos.
     *
     * @param username        Correo o nombre de usuario
     * @param password        Contraseña del usuario
     * @param rol             Rol del usuario (PACIENTE o MEDICO)
     * @param nombres         Nombre del usuario
     * @param apellidos       Apellido del usuario
     * @param telefono        Número de teléfono
     * @param dni             Documento nacional de identidad
     * @param fechaNacimiento Fecha de nacimiento
     * @param direccion       Dirección del domicilio
     */
    public Usuario(String username, String password, Rol rol, String nombres, String apellidos, String telefono,
                   String dni, Date fechaNacimiento, String direccion) {
        this.username = username;
        this.password = password;
        this.rol = rol;
        this.nombre = nombres;
        this.apellido = apellidos;
        this.telefono = telefono;
        this.dni = dni;
        this.fechaNacimiento = fechaNacimiento;
        this.direccion = direccion;
    }

    // Getters y setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }
}