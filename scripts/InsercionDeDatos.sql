-- =================================================================
--  SCRIPT 2: INSERCIÓN DE DATOS INICIALES
--  Proyecto: Gestión de Citas Médicas
--  Descripción: Carga de datos de prueba para la aplicación.
-- =================================================================

USE gestion_citas;

-- ---------------------------------
-- 1. Inserción de Especialidades
-- ---------------------------------
INSERT INTO especialidades (nombre) VALUES
('Cardiología'), ('Dermatología'), ('Ginecología'), ('Pediatría'), ('Traumatología'),
('Neurología'), ('Oftalmología'), ('Otorrinolaringología'), ('Urología'), ('Psiquiatría'),
('Endocrinología'), ('Gastroenterología'), ('Neumología'), ('Oncología'), ('Reumatología'),
('Medicina Interna'), ('Cirugía General'), ('Nefrología'), ('Alergología'), ('Medicina Familiar');

-- ---------------------------------
-- 2. Inserción de Usuarios (Pacientes)
-- ---------------------------------
INSERT INTO usuarios (username, password, rol, nombres, apellidos, telefono, dni, fecha_nacimiento, direccion) VALUES 
('carlos.ramos@example.com', 'clave123', 'PACIENTE', 'Carlos Alberto', 'Ramos López', '912345678', '12345678', '1985-07-10', 'Av. Arequipa 123, Lima'),
('maria.fernandez@example.com', 'abc456', 'PACIENTE', 'María Elena', 'Fernández Quispe', '913456789', '23456789', '1990-03-25', 'Jr. Lampa 456, Lima'),
('jose.torres@example.com', 'xyz789', 'PACIENTE', 'José Manuel', 'Torres Delgado', '914567890', '34567890', '1965-12-10', 'Av. Petit Thouars 780, Lima'),
('ana.gonzales@example.com', 'claveAna', 'PACIENTE', 'Ana Lucía', 'Gonzales Ríos', '915678901', '45678901', '1975-06-30', 'Calle Los Pinos 112, Surco'),
('luis.mendoza@example.com', 'segura22', 'PACIENTE', 'Luis Enrique', 'Mendoza Chávez', '916789012', '56789012', '2000-01-18', 'Av. Grau 222, Lima'),
('roxana.diaz@example.com', 'roxana99', 'PACIENTE', 'Roxana Isabel', 'Díaz Romero', '917890123', '67890123', '1980-10-12', 'Jr. Camaná 321, Cercado de Lima'),
('jorge.soto@example.com', 'jsoto33', 'PACIENTE', 'Jorge Eduardo', 'Soto Aguilar', '918901234', '78901234', '1999-09-01', 'Av. Universitaria 999, San Miguel'),
('camila.vega@example.com', 'cami2024', 'PACIENTE', 'Camila Andrea', 'Vega Márquez', '919012345', '89012345', '2003-04-05', 'Calle Piura 144, Miraflores'),
('ricardo.paz@example.com', 'ricardo321', 'PACIENTE', 'Ricardo Alonso', 'Paz Ramos', '911123456', '90123456', '1970-08-22', 'Av. Bolívar 300, Pueblo Libre'),
('fiorella.nunez@example.com', 'f2024x', 'PACIENTE', 'Fiorella Milagros', 'Núñez Salazar', '912234567', '11223344', '1987-11-17', 'Jr. Junín 555, Lima Centro');

-- ---------------------------------
-- 3. Inserción de Usuarios (Médicos)
-- ---------------------------------
-- Cardiología (ID: 1)
INSERT INTO usuarios (username, password, rol, nombres, apellidos, telefono, dni, fecha_nacimiento, direccion) VALUES 
('andres.salazar@example.com', 'docandres1', 'MEDICO', 'Andrés Felipe', 'Salazar Huamán', '920123456', '84561234', '1980-05-14', 'Av. Brasil 150, Magdalena'),
('elena.vasquez@example.com', 'elenaDoc#', 'MEDICO', 'Elena', 'Vásquez Torres', '920111222', '87654321', '1978-11-20', 'Calle Las Begonias 123, San Isidro');
-- Dermatología (ID: 2)
INSERT INTO usuarios (username, password, rol, nombres, apellidos, telefono, dni, fecha_nacimiento, direccion) VALUES 
('lucia.montero@example.com', 'luciaDoc!', 'MEDICO', 'Lucía Fernanda', 'Montero Paredes', '921234567', '95674321', '1982-09-30', 'Jr. Los Olivos 230, San Borja'),
('javier.solano@example.com', 'javierSol', 'MEDICO', 'Javier', 'Solano Mendoza', '922333444', '12348765', '1985-03-15', 'Av. La Marina 555, San Miguel');
-- Ginecología (ID: 3)
INSERT INTO usuarios (username, password, rol, nombres, apellidos, telefono, dni, fecha_nacimiento, direccion) VALUES 
('isabel.gomez@example.com', 'isabelGine', 'MEDICO', 'Isabel', 'Gómez Luna', '925666777', '23456780', '1981-06-18', 'Av. Angamos 888, Surquillo'),
('patricia.sanchez@example.com', 'patyS', 'MEDICO', 'Patricia', 'Sánchez Rey', '926777888', '34567891', '1983-09-05', 'Calle Schell 123, Miraflores');
-- Pediatría (ID: 4)
INSERT INTO usuarios (username, password, rol, nombres, apellidos, telefono, dni, fecha_nacimiento, direccion) VALUES 
('sofia.castillo@example.com', 'sofiaKids', 'MEDICO', 'Sofía', 'Castillo Flores', '923444555', '54321678', '1988-07-22', 'Calle Los Geranios 456, Miraflores'),
('martin.rojas@example.com', 'martinPedia', 'MEDICO', 'Martín', 'Rojas Vidal', '924555666', '67891234', '1975-01-10', 'Jr. de la Unión 1122, Lima');
-- Traumatología (ID: 5)
INSERT INTO usuarios (username, password, rol, nombres, apellidos, telefono, dni, fecha_nacimiento, direccion) VALUES 
('roberto.cueva@example.com', 'robertoTrauma', 'MEDICO', 'Roberto', 'Cueva Díaz', '927888999', '45678902', '1979-04-12', 'Av. Benavides 456, Miraflores'),
('fernando.vega@example.com', 'ferVegaDoc', 'MEDICO', 'Fernando', 'Vega Soto', '928999000', '56789013', '1984-12-01', 'Jr. Ica 321, Lima');

-- ---------------------------------
-- 4. Inserción en Tablas Relacionales (pacientes y medicos)
-- ---------------------------------
-- Relacionar usuarios con rol PACIENTE a la tabla 'pacientes'
INSERT INTO pacientes (id)
SELECT id FROM usuarios WHERE rol = 'PACIENTE';

-- Relacionar usuarios con rol MEDICO a la tabla 'medicos' con su especialidad
INSERT INTO medicos (id, id_especialidad)
SELECT u.id, 
    CASE u.username
        -- Cardiología (ID: 1)
        WHEN 'andres.salazar@example.com' THEN 1
        WHEN 'elena.vasquez@example.com' THEN 1
        -- Dermatología (ID: 2)
        WHEN 'lucia.montero@example.com' THEN 2
        WHEN 'javier.solano@example.com' THEN 2
        -- Ginecología (ID: 3)
        WHEN 'isabel.gomez@example.com' THEN 3
        WHEN 'patricia.sanchez@example.com' THEN 3
        -- Pediatría (ID: 4)
        WHEN 'sofia.castillo@example.com' THEN 4
        WHEN 'martin.rojas@example.com' THEN 4
        -- Traumatología (ID: 5)
        WHEN 'roberto.cueva@example.com' THEN 5
        WHEN 'fernando.vega@example.com' THEN 5
    END
FROM usuarios u WHERE u.rol = 'MEDICO';

-- ---------------------------------
-- 5. Inserción de Disponibilidad de Médicos
-- ---------------------------------
-- Dr. Andrés Salazar (Cardiología)
INSERT INTO disponibilidades_medicas (id_medico, dia_semana, hora_inicio, hora_fin) VALUES
((SELECT id FROM usuarios WHERE username = 'andres.salazar@example.com'), 'LUNES', '08:00:00', '12:00:00'),
((SELECT id FROM usuarios WHERE username = 'andres.salazar@example.com'), 'MIERCOLES', '14:00:00', '18:00:00');
-- Dra. Elena Vásquez (Cardiología)
INSERT INTO disponibilidades_medicas (id_medico, dia_semana, hora_inicio, hora_fin) VALUES
((SELECT id FROM usuarios WHERE username = 'elena.vasquez@example.com'), 'MARTES', '09:00:00', '13:00:00'),
((SELECT id FROM usuarios WHERE username = 'elena.vasquez@example.com'), 'JUEVES', '09:00:00', '13:00:00');
-- Dra. Lucía Montero (Dermatología)
INSERT INTO disponibilidades_medicas (id_medico, dia_semana, hora_inicio, hora_fin) VALUES
((SELECT id FROM usuarios WHERE username = 'lucia.montero@example.com'), 'LUNES', '10:00:00', '14:00:00'),
((SELECT id FROM usuarios WHERE username = 'lucia.montero@example.com'), 'VIERNES', '10:00:00', '14:00:00');
-- Dr. Javier Solano (Dermatología)
INSERT INTO disponibilidades_medicas (id_medico, dia_semana, hora_inicio, hora_fin) VALUES
((SELECT id FROM usuarios WHERE username = 'javier.solano@example.com'), 'MIERCOLES', '08:00:00', '12:00:00'),
((SELECT id FROM usuarios WHERE username = 'javier.solano@example.com'), 'SABADO', '09:00:00', '13:00:00');
-- Dra. Sofía Castillo (Pediatría)
INSERT INTO disponibilidades_medicas (id_medico, dia_semana, hora_inicio, hora_fin) VALUES
((SELECT id FROM usuarios WHERE username = 'sofia.castillo@example.com'), 'MARTES', '14:00:00', '19:00:00'),
((SELECT id FROM usuarios WHERE username = 'sofia.castillo@example.com'), 'JUEVES', '14:00:00', '19:00:00');
-- Dr. Martín Rojas (Pediatría)
INSERT INTO disponibilidades_medicas (id_medico, dia_semana, hora_inicio, hora_fin) VALUES
((SELECT id FROM usuarios WHERE username = 'martin.rojas@example.com'), 'LUNES', '09:00:00', '13:00:00'),
((SELECT id FROM usuarios WHERE username = 'martin.rojas@example.com'), 'MIERCOLES', '09:00:00', '13:00:00');
-- Dra. Isabel Gómez (Ginecología)
INSERT INTO disponibilidades_medicas (id_medico, dia_semana, hora_inicio, hora_fin) VALUES
((SELECT id FROM usuarios WHERE username = 'isabel.gomez@example.com'), 'LUNES', '15:00:00', '19:00:00'),
((SELECT id FROM usuarios WHERE username = 'isabel.gomez@example.com'), 'JUEVES', '08:00:00', '12:00:00');
-- Dra. Patricia Sánchez (Ginecología)
INSERT INTO disponibilidades_medicas (id_medico, dia_semana, hora_inicio, hora_fin) VALUES
((SELECT id FROM usuarios WHERE username = 'patricia.sanchez@example.com'), 'MARTES', '10:00:00', '15:00:00'),
((SELECT id FROM usuarios WHERE username = 'patricia.sanchez@example.com'), 'VIERNES', '14:00:00', '18:00:00');
-- Dr. Roberto Cueva (Traumatología)
INSERT INTO disponibilidades_medicas (id_medico, dia_semana, hora_inicio, hora_fin) VALUES
((SELECT id FROM usuarios WHERE username = 'roberto.cueva@example.com'), 'LUNES', '08:00:00', '13:00:00'),
((SELECT id FROM usuarios WHERE username = 'roberto.cueva@example.com'), 'JUEVES', '15:00:00', '20:00:00');
-- Dr. Fernando Vega (Traumatología)
INSERT INTO disponibilidades_medicas (id_medico, dia_semana, hora_inicio, hora_fin) VALUES
((SELECT id FROM usuarios WHERE username = 'fernando.vega@example.com'), 'MIERCOLES', '10:00:00', '14:00:00'),
((SELECT id FROM usuarios WHERE username = 'fernando.vega@example.com'), 'VIERNES', '09:00:00', '13:00:00');

-- --- Fin del Script de Inserción ---