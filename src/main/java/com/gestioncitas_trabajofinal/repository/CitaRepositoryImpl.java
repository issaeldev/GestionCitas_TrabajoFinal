package com.gestioncitas_trabajofinal.repository;

import com.gestioncitas_trabajofinal.db.ConexionBD;
import com.gestioncitas_trabajofinal.model.*;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementación de la interfaz CitaRepository para interactuar con la base de datos.
 */
public class CitaRepositoryImpl implements CitaRepository {

    private Connection conexion;

    public CitaRepositoryImpl() {
        this.conexion = ConexionBD.getConecction();
    }

    @Override
    public boolean guardar(Cita cita) {
        String sql = "INSERT INTO citas (id_paciente, id_medico, fecha, motivo, estado) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, cita.getPaciente().getId());
            stmt.setInt(2, cita.getMedico().getId());
            stmt.setTimestamp(3, Timestamp.valueOf(cita.getFechaHora()));
            stmt.setString(4, cita.getMotivo());
            stmt.setString(5, cita.getEstado().name());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean existeCitaParaMedico(int idMedico, LocalDateTime fechaHora) {
        String sql = "SELECT 1 FROM citas WHERE id_medico = ? AND fecha = ?";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, idMedico);
            stmt.setTimestamp(2, Timestamp.valueOf(fechaHora));
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return true;
        }
    }

    @Override
    public List<Cita> obtenerCitasPorMedico(int idMedico) {
        List<Cita> citas = new ArrayList<>();
        String sql = """
            SELECT c.*, u.nombres, u.apellidos 
            FROM citas c
            JOIN pacientes p ON c.id_paciente = p.id
            JOIN usuarios u ON p.id = u.id
            WHERE c.id_medico = ?
            ORDER BY c.fecha
        """;
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, idMedico);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Cita cita = new Cita();
                cita.setId(rs.getInt("id"));
                Paciente paciente = new Paciente();
                paciente.setId(rs.getInt("id_paciente"));
                paciente.setNombre(rs.getString("nombres"));
                paciente.setApellido(rs.getString("apellidos"));
                cita.setPaciente(paciente);

                // Aquí no se necesita el objeto Medico completo, solo el ID
                Medico medico = new Medico();
                medico.setId(idMedico);
                cita.setMedico(medico);

                cita.setFechaHora(rs.getTimestamp("fecha").toLocalDateTime());
                cita.setMotivo(rs.getString("motivo"));
                cita.setEstado(EstadoCita.valueOf(rs.getString("estado")));

                citas.add(cita);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return citas;
    }

    @Override
    public List<Cita> obtenerCitasPorPaciente(int idPaciente) {
        List<Cita> citas = new ArrayList<>();
        String sql = """
            SELECT c.id, c.fecha, c.motivo, c.estado,
                   m.id AS id_medico, u.nombres AS nombre_medico, u.apellidos AS apellido_medico,
                   e.id AS id_especialidad, e.nombre AS nombre_especialidad
            FROM citas c
            JOIN medicos m ON c.id_medico = m.id
            JOIN usuarios u ON m.id = u.id
            JOIN especialidades e ON m.id_especialidad = e.id
            WHERE c.id_paciente = ?
            ORDER BY c.fecha
        """;
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, idPaciente);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Cita cita = new Cita();
                cita.setId(rs.getInt("id"));
                cita.setFechaHora(rs.getTimestamp("fecha").toLocalDateTime());
                cita.setMotivo(rs.getString("motivo"));
                cita.setEstado(EstadoCita.valueOf(rs.getString("estado")));

                Paciente paciente = new Paciente();
                paciente.setId(idPaciente);
                cita.setPaciente(paciente);

                Medico medico = new Medico();
                medico.setId(rs.getInt("id_medico"));
                medico.setNombre(rs.getString("nombre_medico"));
                medico.setApellido(rs.getString("apellido_medico"));
                
                Especialidad especialidad = new Especialidad();
                especialidad.setId(rs.getInt("id_especialidad"));
                especialidad.setNombre(rs.getString("nombre_especialidad"));
                medico.setEspecialidad(especialidad);

                cita.setMedico(medico);

                citas.add(cita);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return citas;
    }
    
    @Override
    public boolean existeCitaParaMedicoEnFechaHora(int idMedico, LocalDateTime fechaHora) {
        String sql = "SELECT COUNT(*) FROM citas WHERE id_medico = ? AND fecha = ? AND estado != 'CANCELADA'";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, idMedico);
            stmt.setTimestamp(2, Timestamp.valueOf(fechaHora));
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    @Override
    public Cita obtenerProximaCitaPorPaciente(int idPaciente) {
        String sql = "SELECT * FROM citas WHERE id_paciente = ? AND fecha > NOW() AND estado = 'EN_ESPERA' ORDER BY fecha ASC LIMIT 1";
        try (Connection conn = ConexionBD.getConecction();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idPaciente);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return mapearCitaDesdeResultSet(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    /**
     * Mapea un registro de la base de datos a un objeto Cita.
     * @param rs El ResultSet que contiene los datos de la cita.
     * @return Un objeto Cita.
     * @throws SQLException si ocurre un error al acceder a los datos.
     */
    private Cita mapearCitaDesdeResultSet(ResultSet rs) throws SQLException {
        Cita cita = new Cita();
        cita.setId(rs.getInt("id"));

        Timestamp timestamp = rs.getTimestamp("fecha");
        if (timestamp != null) {
            cita.setFechaHora(timestamp.toLocalDateTime());
        }

        cita.setMotivo(rs.getString("motivo"));
        cita.setEstado(EstadoCita.valueOf(rs.getString("estado")));

        int idPaciente = rs.getInt("id_paciente");
        int idMedico = rs.getInt("id_medico");

        // --- CORRECCIÓN AQUÍ ---
        // Se instancia el repositorio unificado para buscar tanto pacientes como médicos.
        UsuarioRepository usuarioRepo = new UsuarioRepositoryImpl();

        // Se busca el usuario por ID y se hace un 'cast' (conversión) al tipo correcto.
        Paciente paciente = (Paciente) usuarioRepo.obtenerPorId(idPaciente);
        Medico medico = (Medico) usuarioRepo.obtenerPorId(idMedico);

        cita.setPaciente(paciente);
        cita.setMedico(medico);

        return cita;
    }
    
    @Override
    public void actualizarEstado(int idCita, EstadoCita estado) {
        String sql = "UPDATE citas SET estado = ? WHERE id = ?";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setString(1, estado.name());
            stmt.setInt(2, idCita);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}