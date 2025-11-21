package com.gestioncitas_trabajofinal.repository;

import com.gestioncitas_trabajofinal.db.ConexionBD;
import com.gestioncitas_trabajofinal.model.*;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginRepositoryImpl implements LoginRepository {

    private final Connection conexion;

    /**
     * Establece la conexión con la base de datos al crear el repositorio.
     */
    public LoginRepositoryImpl() {
        this.conexion = ConexionBD.getConecction();
    }

    /**
     * Autentica al usuario por su correo y contraseña.
     *
     * @param username Correo electrónico ingresado
     * @param password Contraseña ingresada
     * @return El objeto Usuario (Paciente o Medico) si es válido; null si las credenciales no coinciden.
     */
    @Override
    public Usuario autenticarUsuario(String username, String password) {
        String sql = "SELECT * FROM usuarios WHERE username = ?";

        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setString(1, username);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String storedHashedPassword = rs.getString("password");
                Rol rol = Rol.valueOf(rs.getString("rol"));

                boolean passwordValida = false;

                // ✅ Verificación dual: BCrypt o texto plano
                if (storedHashedPassword != null) {
                    if (storedHashedPassword.startsWith("$2a$")
                            || storedHashedPassword.startsWith("$2b$")
                            || storedHashedPassword.startsWith("$2y$")) {
                        // Contraseña hasheada con BCrypt
                        passwordValida = BCrypt.checkpw(password, storedHashedPassword);
                    } else {
                        // Contraseña almacenada en texto plano (modo prueba)
                        passwordValida = password.equals(storedHashedPassword);
                    }
                }

                if (passwordValida) {
                    // Retornar el tipo de usuario según su rol
                    return switch (rol) {
                        case MEDICO -> construirMedicoDesdeBase(rs);
                        case PACIENTE -> construirPacienteDesdeBase(rs);
                        default -> null;
                    };
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null; // No autenticado
    }

    /**
     * Construye un objeto Medico a partir del ResultSet de la tabla usuarios.
     * También consulta la especialidad correspondiente desde la tabla medicos.
     */
    private Medico construirMedicoDesdeBase(ResultSet rs) throws SQLException {
        Medico medico = new Medico();

        medico.setId(rs.getInt("id"));
        medico.setUsername(rs.getString("username"));
        medico.setPassword(rs.getString("password"));
        medico.setRol(Rol.MEDICO);
        medico.setNombre(rs.getString("nombres"));
        medico.setApellido(rs.getString("apellidos"));
        medico.setTelefono(rs.getString("telefono"));
        medico.setDni(rs.getString("dni"));
        medico.setFechaNacimiento(rs.getDate("fecha_nacimiento"));
        medico.setDireccion(rs.getString("direccion"));

        // Consulta la especialidad del médico
        String sqlEspecialidad = """
            SELECT e.id AS id_especialidad, e.nombre AS nombre_especialidad 
            FROM medicos m 
            JOIN especialidades e ON m.id_especialidad = e.id 
            WHERE m.id = ?
        """;
        try (PreparedStatement stmtEsp = conexion.prepareStatement(sqlEspecialidad)) {
            stmtEsp.setInt(1, medico.getId());
            ResultSet rsEsp = stmtEsp.executeQuery();

            if (rsEsp.next()) {
                int idEspecialidad = rsEsp.getInt("id_especialidad");
                String nombreEspecialidad = rsEsp.getString("nombre_especialidad");
                medico.setEspecialidad(new Especialidad(idEspecialidad, nombreEspecialidad));
            }
        }

        return medico;
    }

    /**
     * Construye un objeto Paciente a partir del ResultSet de la tabla usuarios.
     */
    private Paciente construirPacienteDesdeBase(ResultSet rs) throws SQLException {
        Paciente paciente = new Paciente();

        paciente.setId(rs.getInt("id"));
        paciente.setUsername(rs.getString("username"));
        paciente.setPassword(rs.getString("password"));
        paciente.setRol(Rol.PACIENTE);
        paciente.setNombre(rs.getString("nombres"));
        paciente.setApellido(rs.getString("apellidos"));
        paciente.setTelefono(rs.getString("telefono"));
        paciente.setDni(rs.getString("dni"));
        paciente.setFechaNacimiento(rs.getDate("fecha_nacimiento"));
        paciente.setDireccion(rs.getString("direccion"));

        return paciente;
    }
}
