package com.gestioncitas_trabajofinal.repository;
import com.gestioncitas_trabajofinal.model.DisponibilidadMedica;
import java.util.List;

public interface DisponibilidadRepository 
{
    void guardar(DisponibilidadMedica disponibilidad);
    List<DisponibilidadMedica> obtenerPorMedico(int idMedico);
    void eliminarPorMedico(int idMedico); // útil si quieres reemplazar disponibilidad anterior
}

