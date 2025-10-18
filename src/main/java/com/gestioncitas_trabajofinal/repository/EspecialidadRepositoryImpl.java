package com.gestioncitas_trabajofinal.repository;

import com.gestioncitas_trabajofinal.model.Especialidad;
import com.gestioncitas_trabajofinal.db.ConexionBD;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EspecialidadRepositoryImpl implements EspecialidadRepository{

    private Connection conexion;

    public EspecialidadRepositoryImpl() {
        this.conexion = ConexionBD.getConecction();
    }

    @Override
    public List<Especialidad> obtenerTodas() {
        List<Especialidad> lista = new ArrayList<>();
        String sql = "SELECT id, nombre FROM especialidades";

        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Especialidad esp = new Especialidad(
                        rs.getInt("id"),
                        rs.getString("nombre")
                );
                lista.add(esp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }
}
