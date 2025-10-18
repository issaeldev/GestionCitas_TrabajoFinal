package com.gestioncitas_trabajofinal.repository;

import com.gestioncitas_trabajofinal.db.ConexionBD;
import com.gestioncitas_trabajofinal.model.*;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementación de la interfaz UNIFICADA UsuarioRepository.
 * Se encarga de la persistencia de usuarios, pacientes y médicos.
 */
public class UsuarioRepositoryImpl implements UsuarioRepository {

    private Connection conexion;

    public UsuarioRepositoryImpl() {
        this.conexion = ConexionBD.getConecction();
    }
    
    // --- MÉTODOS DE ESCRITURA (GUARDAR Y ACTUALIZAR) ---

    @Override
    public void guardar(Usuario usuario) {
        // La lógica de guardar se mantiene igual, ya estaba bien centralizada.
        try {
            String sql = "INSERT INTO usuarios (username, password, rol, nombres, apellidos, telefono, dni, fecha_nacimiento, direccion) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = conexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            // Contraseña encriptada
            String hashedPassword = BCrypt.hashpw(usuario.getPassword(), BCrypt.gensalt());

            stmt.setString(1, usuario.getUsername());
            stmt.setString(2, hashedPassword);
            stmt.setString(3, usuario.getRol().name());
            stmt.setString(4, usuario.getNombre());
            stmt.setString(5, usuario.getApellido());
            stmt.setString(6, usuario.getTelefono());
            stmt.setString(7, usuario.getDni());
            stmt.setDate(8, new java.sql.Date(usuario.getFechaNacimiento().getTime()));
            stmt.setString(9, usuario.getDireccion());
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                int userId = rs.getInt(1);
                if (usuario.getRol() == Rol.PACIENTE) {
                    guardarEnTablaEspecifica(userId, "pacientes", null);
                } else if (usuario instanceof Medico medico) {
                    guardarEnTablaEspecifica(userId, "medicos", medico.getEspecialidad().getId());
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    private void guardarEnTablaEspecifica(int userId, String tabla, Integer idEspecialidad) throws SQLException {
        String sql;
        if (tabla.equals("medicos")) {
            sql = "INSERT INTO medicos (id, id_especialidad) VALUES (?, ?)";
        } else {
            sql = "INSERT INTO pacientes (id) VALUES (?)";
        }
        
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            if (idEspecialidad != null) {
                stmt.setInt(2, idEspecialidad);
            }
            stmt.executeUpdate();
        }
    }

    @Override
    public boolean actualizar(Usuario usuario) {
        // Usamos la lógica completa que estaba en `actualizarPaciente`
        String sql = "UPDATE usuarios SET nombres = ?, apellidos = ?, direccion = ?, telefono = ?, fecha_nacimiento = ?, password = ? WHERE id = ?";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            // Contraseña encriptada
            String hashedPassword = BCrypt.hashpw(usuario.getPassword(), BCrypt.gensalt());

            stmt.setString(1, usuario.getNombre());
            stmt.setString(2, usuario.getApellido());
            stmt.setString(3, usuario.getDireccion());
            stmt.setString(4, usuario.getTelefono());
            stmt.setDate(5, new java.sql.Date(usuario.getFechaNacimiento().getTime()));
            stmt.setString(6, hashedPassword);
            stmt.setInt(7, usuario.getId());
            int filasAfectadas = stmt.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    // --- MÉTODOS DE LECTURA (OBTENER Y VERIFICAR) ---

    @Override
    public Usuario obtenerPorId(int id) {
        // Esta es la implementación del método repetido, ahora centralizado.
        // Similar a la lógica de LoginRepositoryImpl, determina el tipo de usuario por su ROL.
        String sql = "SELECT * FROM usuarios WHERE id = ?";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Rol rol = Rol.valueOf(rs.getString("rol"));
                if (rol == Rol.MEDICO) {
                    return construirMedicoDesdeResultSet(rs);
                } else {
                    return construirPacienteDesdeResultSet(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Medico> obtenerMedicosPorEspecialidad(int idEspecialidad) {
        // Lógica movida desde MedicoRepositoryImpl
        List<Medico> lista = new ArrayList<>();
        String sql = "SELECT u.id, u.nombres, u.apellidos, u.username FROM usuarios u JOIN medicos m ON u.id = m.id WHERE m.id_especialidad = ?";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, idEspecialidad);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Medico m = new Medico();
                m.setId(rs.getInt("id"));
                m.setNombre(rs.getString("nombres"));
                m.setApellido(rs.getString("apellidos"));
                m.setUsername(rs.getString("username"));
                m.setEspecialidad(new Especialidad(idEspecialidad));
                lista.add(m);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
    
    @Override
    public boolean existeUsername(String username) {
        // Se mantiene igual
        String sql = "SELECT 1 FROM usuarios WHERE username = ?";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean existeDNI(String dni) {
        // Se mantiene igual
        String sql = "SELECT 1 FROM usuarios WHERE dni = ?";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setString(1, dni);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    // --- Métodos de ayuda para construir objetos ---

    private Paciente construirPacienteDesdeResultSet(ResultSet rs) throws SQLException {
        Paciente paciente = new Paciente();
        // Mapeo común de Usuario
        mapearUsuarioBase(paciente, rs);
        return paciente;
    }
    
    private Medico construirMedicoDesdeResultSet(ResultSet rs) throws SQLException {
        Medico medico = new Medico();
        // Mapeo común de Usuario
        mapearUsuarioBase(medico, rs);
        // Mapeo específico de Medico (su especialidad)
        String sqlEspecialidad = "SELECT e.id, e.nombre FROM especialidades e JOIN medicos m ON e.id = m.id_especialidad WHERE m.id = ?";
        try (PreparedStatement stmt = conexion.prepareStatement(sqlEspecialidad)) {
            stmt.setInt(1, medico.getId());
            ResultSet rsEsp = stmt.executeQuery();
            if (rsEsp.next()) {
                medico.setEspecialidad(new Especialidad(rsEsp.getInt("id"), rsEsp.getString("nombre")));
            }
        }
        return medico;
    }
    
    private void mapearUsuarioBase(Usuario usuario, ResultSet rs) throws SQLException {
        usuario.setId(rs.getInt("id"));
        usuario.setUsername(rs.getString("username"));
        usuario.setPassword(rs.getString("password"));
        usuario.setRol(Rol.valueOf(rs.getString("rol")));
        usuario.setNombre(rs.getString("nombres"));
        usuario.setApellido(rs.getString("apellidos"));
        usuario.setTelefono(rs.getString("telefono"));
        usuario.setDni(rs.getString("dni"));
        usuario.setFechaNacimiento(rs.getDate("fecha_nacimiento"));
        usuario.setDireccion(rs.getString("direccion"));
    }
}