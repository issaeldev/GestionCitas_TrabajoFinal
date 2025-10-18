package com.gestioncitas_trabajofinal.repository;

import com.gestioncitas_trabajofinal.db.ConexionBD;
import com.gestioncitas_trabajofinal.model.DiaSemana;
import com.gestioncitas_trabajofinal.model.DisponibilidadMedica;
import com.gestioncitas_trabajofinal.model.Medico;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;



public class DisponibilidadRepositoryImpl implements DisponibilidadRepository 
{
     private Connection conexion;
     
     public DisponibilidadRepositoryImpl() 
    {
        this.conexion = ConexionBD.getConecction();
    }   

    @Override
    public void guardar(DisponibilidadMedica disponibilidad) {
        String sql = "INSERT INTO disponibilidades_medicas (id_medico, dia_semana, hora_inicio, hora_fin) VALUES (?, ?, ?, ?)";
        
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, disponibilidad.getMedico().getId());
            stmt.setString(2, disponibilidad.getDiaSemana().name());
            stmt.setTime(3, Time.valueOf(disponibilidad.getHoraInicio()));
            stmt.setTime(4, Time.valueOf(disponibilidad.getHoraFin()));
            stmt.executeUpdate();
            
        } catch (SQLException e) 
        {
            e.printStackTrace();
        }
    }

    @Override
        public List<DisponibilidadMedica> obtenerPorMedico(int idMedico) 
        {
        List<DisponibilidadMedica> lista = new ArrayList<>();
        String sql = "SELECT * FROM disponibilidades_medicas WHERE id_medico = ?";

        try (PreparedStatement stmt = conexion.prepareStatement(sql)) 
        {
            stmt.setInt(1, idMedico);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                DisponibilidadMedica d = new DisponibilidadMedica();
                d.setId(rs.getInt("id"));

                // Crear el objeto Medico y asignar el ID
                Medico medico = new Medico();
                medico.setId(idMedico);
                d.setMedico(medico);

                // Día de la semana (como enum)
                d.setDiaSemana(DiaSemana.valueOf(rs.getString("dia_semana")));

                // Horas (convertidas a LocalTime)
                d.setHoraInicio(rs.getTime("hora_inicio").toLocalTime());
                d.setHoraFin(rs.getTime("hora_fin").toLocalTime());

                lista.add(d);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
        }

    @Override
    public void eliminarPorMedico(int idMedico) 
    {
        String sql = "DELETE FROM disponibilidades_medicas WHERE id_medico = ?";

        try (PreparedStatement stmt = conexion.prepareStatement(sql)) 
        {
            stmt.setInt(1, idMedico);
            stmt.executeUpdate();
        } catch (SQLException e) 
        {
            e.printStackTrace();
        }
    }
}
        