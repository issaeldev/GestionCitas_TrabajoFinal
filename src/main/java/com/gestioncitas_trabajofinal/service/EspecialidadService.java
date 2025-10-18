package com.gestioncitas_trabajofinal.service;

import com.gestioncitas_trabajofinal.model.Especialidad;
import com.gestioncitas_trabajofinal.repository.EspecialidadRepository;
import java.util.List;

/**
 * Servicio para obtener información sobre las especialidades médicas.
 */
public class EspecialidadService {
    
    private final EspecialidadRepository especialidadRepo;

    /**
     * Constructor que inyecta la dependencia del repositorio de especialidades.
     * @param especialidadRepo La implementación del repositorio a utilizar.
     */
    public EspecialidadService(EspecialidadRepository especialidadRepo) {
        this.especialidadRepo = especialidadRepo;
    }

    /**
     * Obtiene una lista con todas las especialidades disponibles en el sistema.
     * @return Una lista de objetos Especialidad.
     */
    public List<Especialidad> obtenerTodasEspecialidades() {
        return especialidadRepo.obtenerTodas();
    }
}