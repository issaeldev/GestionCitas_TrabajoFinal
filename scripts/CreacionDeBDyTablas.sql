-- =================================================================
--  SCRIPT 1: ESQUEMA DE LA BASE DE DATOS
--  Proyecto: Gestión de Citas Médicas
--  Descripción: Creación de la base de datos y todas las tablas.
-- =================================================================

-- Elimina la base de datos si ya existe para una inicialización limpia
DROP DATABASE IF EXISTS gestion_citas;

-- Creación de la base de datos
CREATE DATABASE gestion_citas;
USE gestion_citas;

-- ---------------------------------
-- TABLA: usuarios
-- Almacena la información base para pacientes y médicos.
-- ---------------------------------
CREATE TABLE usuarios (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(100) UNIQUE NOT NULL COMMENT 'Correo electrónico del usuario, usado para login.',
    password VARCHAR(255) NOT NULL COMMENT 'Contraseña del usuario.',
    rol ENUM('PACIENTE', 'MEDICO') NOT NULL COMMENT 'Define el tipo de usuario.',
    nombres VARCHAR(100) NOT NULL,
    apellidos VARCHAR(100) NOT NULL,
    telefono VARCHAR(20),
    dni CHAR(8) UNIQUE NOT NULL,
    fecha_nacimiento DATE,
    direccion VARCHAR(200)
) COMMENT='Tabla central de usuarios del sistema.';

-- ---------------------------------
-- TABLA: pacientes
-- Relaciona un ID de usuario con el rol de paciente.
-- ---------------------------------
CREATE TABLE pacientes (
    id INT PRIMARY KEY COMMENT 'Referencia al ID de la tabla de usuarios.',
    FOREIGN KEY (id) REFERENCES usuarios(id) ON DELETE CASCADE
) COMMENT='Tabla que identifica a los usuarios que son pacientes.';

-- ---------------------------------
-- TABLA: especialidades
-- Catálogo de todas las especialidades médicas disponibles.
-- ---------------------------------
CREATE TABLE especialidades (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) UNIQUE NOT NULL
) COMMENT='Catálogo de especialidades médicas.';

-- ---------------------------------
-- TABLA: medicos
-- Relaciona un ID de usuario con el rol de médico y su especialidad.
-- ---------------------------------
CREATE TABLE medicos (
    id INT PRIMARY KEY COMMENT 'Referencia al ID de la tabla de usuarios.',
    id_especialidad INT NOT NULL,
    FOREIGN KEY (id) REFERENCES usuarios(id) ON DELETE CASCADE,
    FOREIGN KEY (id_especialidad) REFERENCES especialidades(id)
) COMMENT='Tabla que identifica a los usuarios que son médicos y su especialidad.';

-- ---------------------------------
-- TABLA: citas
-- Almacena toda la información de las citas agendadas.
-- ---------------------------------
CREATE TABLE citas (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_paciente INT NOT NULL,
    id_medico INT NOT NULL,
    fecha DATETIME NOT NULL COMMENT 'Fecha y hora exactas de la cita.',
    motivo VARCHAR(255) COMMENT 'Breve descripción de la razón de la consulta.',
    estado ENUM('EN_ESPERA', 'REALIZADA', 'CANCELADA', 'NO_ASISTIO') DEFAULT 'EN_ESPERA',
    FOREIGN KEY (id_paciente) REFERENCES pacientes(id) ON DELETE CASCADE,
    FOREIGN KEY (id_medico) REFERENCES medicos(id) ON DELETE CASCADE
) COMMENT='Registro de todas las citas médicas del sistema.';

-- ---------------------------------
-- TABLA: disponibilidades_medicas
-- Almacena los horarios de trabajo semanales de cada médico.
-- ---------------------------------
CREATE TABLE disponibilidades_medicas (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_medico INT NOT NULL,
    dia_semana ENUM('LUNES', 'MARTES', 'MIERCOLES', 'JUEVES', 'VIERNES', 'SABADO', 'DOMINGO') NOT NULL,
    hora_inicio TIME NOT NULL,
    hora_fin TIME NOT NULL,
    FOREIGN KEY (id_medico) REFERENCES medicos(id) ON DELETE CASCADE
) COMMENT='Horarios de disponibilidad de los médicos por día de la semana.';

-- --- Fin del Script de Esquema ---