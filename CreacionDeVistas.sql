-- =================================================================
--  SCRIPT 3: VISTAS DE MONITOREO
--  Proyecto: Gestión de Citas Médicas
--  Descripción: Creación de vistas para simplificar consultas comunes.
-- =================================================================

USE gestion_citas;

-- ---------------------------------
-- VISTA 1: vista_citas_detalle
-- Muestra un listado completo de todas las citas con los nombres
-- del paciente y del médico, así como la especialidad.
-- ---------------------------------
CREATE OR REPLACE VIEW vista_citas_detalle AS
SELECT 
    c.id AS id_cita,
    c.fecha,
    c.estado,
    c.motivo,
    p.id AS id_paciente,
    CONCAT(up.nombres, ' ', up.apellidos) AS nombre_paciente,
    up.username AS email_paciente,
    m.id AS id_medico,
    CONCAT(um.nombres, ' ', um.apellidos) AS nombre_medico,
    e.nombre AS especialidad
FROM 
    citas c
JOIN 
    pacientes p ON c.id_paciente = p.id
JOIN 
    usuarios up ON p.id = up.id
JOIN 
    medicos m ON c.id_medico = m.id
JOIN 
    usuarios um ON m.id = um.id
JOIN 
    especialidades e ON m.id_especialidad = e.id
ORDER BY 
    c.fecha DESC;

-- ---------------------------------
-- VISTA 2: vista_proximas_citas
-- Filtra las citas para mostrar solo las que están pendientes ('EN_ESPERA').
-- Útil para ver rápidamente la agenda futura.
-- ---------------------------------
CREATE OR REPLACE VIEW vista_proximas_citas AS
SELECT *
FROM 
    vista_citas_detalle
WHERE 
    estado = 'EN_ESPERA' AND fecha >= CURDATE()
ORDER BY
    fecha ASC;

-- ---------------------------------
-- VISTA 3: vista_horarios_medicos
-- Muestra de forma clara la disponibilidad semanal de cada médico.
-- ---------------------------------
CREATE OR REPLACE VIEW vista_horarios_medicos AS
SELECT 
    CONCAT(u.nombres, ' ', u.apellidos) AS nombre_medico,
    e.nombre AS especialidad,
    d.dia_semana,
    d.hora_inicio,
    d.hora_fin
FROM 
    disponibilidades_medicas d
JOIN 
    medicos m ON d.id_medico = m.id
JOIN 
    usuarios u ON m.id = u.id
JOIN 
    especialidades e ON m.id_especialidad = e.id
ORDER BY 
    nombre_medico, FIELD(d.dia_semana, 'LUNES', 'MARTES', 'MIERCOLES', 'JUEVES', 'VIERNES', 'SABADO', 'DOMINGO');

-- ---------------------------------
-- Consultas de prueba para las vistas
-- ---------------------------------
SELECT * FROM vista_citas_detalle;
SELECT * FROM vista_proximas_citas;
SELECT * FROM vista_horarios_medicos;
SELECT * FROM usuarios;

-- --- Fin del Script de Vistas ---