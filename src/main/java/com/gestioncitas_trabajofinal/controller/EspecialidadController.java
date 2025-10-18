package com.gestioncitas_trabajofinal.controller;

import com.gestioncitas_trabajofinal.model.Especialidad;
import com.gestioncitas_trabajofinal.repository.EspecialidadRepositoryImpl;
import com.gestioncitas_trabajofinal.service.EspecialidadService;
import java.util.List;

/**
 * Controlador para gestionar la información de las especialidades médicas.
 */
public class EspecialidadController {
    
    private final EspecialidadService especialidadService;

    /**
     * Constructor que inicializa las dependencias del controlador.
     */
    public EspecialidadController() {
        this.especialidadService = new EspecialidadService(new EspecialidadRepositoryImpl());
    }

    /**
     * Obtiene una lista de todas las especialidades disponibles.
     * @return Una lista de objetos Especialidad.
     */
    public List<Especialidad> obtenerTodasEspecialidades() {
        return especialidadService.obtenerTodasEspecialidades();
    }
}