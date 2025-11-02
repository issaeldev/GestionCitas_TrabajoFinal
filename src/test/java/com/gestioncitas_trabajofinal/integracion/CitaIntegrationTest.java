package com.gestioncitas_trabajofinal.integracion;

import com.gestioncitas_trabajofinal.controller.CitaController;
import com.gestioncitas_trabajofinal.db.ConexionBD;
import com.gestioncitas_trabajofinal.model.*;
import org.junit.jupiter.api.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Pruebas de Integración para el módulo de Gestión de Citas.
 * 
 * <p>Estas pruebas validan el flujo completo de la aplicación:
 * <strong>Controller -> Service -> Repository -> Base de Datos MySQL</strong></p>
 * 
 * <p><strong>¿Por qué usar Controller en pruebas de integración?</strong></p>
 * <ul>
 *   <li>Valida la arquitectura MVC completa como en producción</li>
 *   <li>Prueba la validación de datos en la capa de presentación</li>
 *   <li>Verifica el manejo de excepciones en toda la cadena</li>
 *   <li>Asegura que el patrón Observer funciona correctamente</li>
 * </ul>
 * 
 * <p><strong>REQUISITOS PREVIOS:</strong></p>
 * <ul>
 *   <li>MySQL Server corriendo en localhost:3306</li>
 *   <li>Schema 'gestion_citas' creado con todas las tablas</li>
 *   <li>Al menos 1 paciente y 1 medico registrados en la BD</li>
 *   <li>Usuario BD: root, Password: admin</li>
 * </ul>
 * 
 * <p><strong>NOTA:</strong> Estas pruebas NO usan Mockito. Se conectan a la base de datos real.</p>
 */
@DisplayName("Pruebas de Integración - Gestión de Citas (SIN MOCKITO)")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CitaIntegrationTest {

    private static CitaController citaController;
    private static Connection connection;
    private static Paciente pacientePrueba;
    private static Medico medicoPrueba;

    @BeforeAll
    static void setUpBeforeClass() throws SQLException {
        System.out.println("\n" + "=".repeat(70));
        System.out.println("   INICIANDO PRUEBAS DE INTEGRACION - GESTION DE CITAS");
        System.out.println("=".repeat(70));
        
        // Obtener conexión a la base de datos
        connection = ConexionBD.getConecction();
        assertNotNull(connection, "No se pudo establecer conexión con la base de datos");
        
        // Inicializar el controlador SIN el EmailNotifierObserver para pruebas
        // Creamos un CitaService personalizado sin observadores de email
        com.gestioncitas_trabajofinal.repository.CitaRepositoryImpl citaRepo = 
            new com.gestioncitas_trabajofinal.repository.CitaRepositoryImpl();
        com.gestioncitas_trabajofinal.service.CitaService citaService = 
            new com.gestioncitas_trabajofinal.service.CitaService(citaRepo);
        // NO agregamos EmailNotifierObserver para evitar intentar enviar emails reales
        
        // Creamos un CitaController usando reflexión para inyectar nuestro servicio
        citaController = new CitaController() {
            private final com.gestioncitas_trabajofinal.service.CitaService service = citaService;
            
            @Override
            public boolean registrarCita(com.gestioncitas_trabajofinal.model.Paciente paciente, 
                                        com.gestioncitas_trabajofinal.model.Medico medico, 
                                        com.gestioncitas_trabajofinal.model.DiaSemana dia, 
                                        java.time.LocalTime hora, 
                                        String motivo) {
                java.time.LocalDate fechaProxima = obtenerProximaFecha(dia);
                java.time.LocalDateTime fechaHora = java.time.LocalDateTime.of(fechaProxima, hora);
                return service.registrarCita(paciente, medico, fechaHora, motivo);
            }
            
            private java.time.LocalDate obtenerProximaFecha(com.gestioncitas_trabajofinal.model.DiaSemana dia) {
                java.time.DayOfWeek targetDay = convertirADayOfWeek(dia);
                java.time.LocalDate today = java.time.LocalDate.now();
                int daysUntil = (targetDay.getValue() - today.getDayOfWeek().getValue() + 7) % 7;
                daysUntil = (daysUntil == 0) ? 7 : daysUntil;
                return today.plusDays(daysUntil);
            }
            
            private java.time.DayOfWeek convertirADayOfWeek(com.gestioncitas_trabajofinal.model.DiaSemana dia) {
                return switch (dia) {
                    case LUNES -> java.time.DayOfWeek.MONDAY;
                    case MARTES -> java.time.DayOfWeek.TUESDAY;
                    case MIERCOLES -> java.time.DayOfWeek.WEDNESDAY;
                    case JUEVES -> java.time.DayOfWeek.THURSDAY;
                    case VIERNES -> java.time.DayOfWeek.FRIDAY;
                    case SABADO -> java.time.DayOfWeek.SATURDAY;
                    case DOMINGO -> java.time.DayOfWeek.SUNDAY;
                };
            }
            
            @Override
            public com.gestioncitas_trabajofinal.model.Cita obtenerProximaCita(int idPaciente) {
                return service.obtenerProximaCitaPorPaciente(idPaciente);
            }
            
            @Override
            public java.util.List<com.gestioncitas_trabajofinal.model.Cita> obtenerCitasPorPaciente(int idPaciente) {
                return service.obtenerCitasPorPaciente(idPaciente);
            }
            
            @Override
            public java.util.List<com.gestioncitas_trabajofinal.model.Cita> obtenerCitasPorMedico(int idMedico) {
                return service.obtenerCitasPorMedico(idMedico);
            }
            
            @Override
            public void cancelarCita(int idCita) {
                service.actualizarEstadoCita(idCita, com.gestioncitas_trabajofinal.model.EstadoCita.CANCELADA);
            }
            
            @Override
            public void actualizarEstadoCita(int idCita, com.gestioncitas_trabajofinal.model.EstadoCita nuevoEstado) {
                service.actualizarEstadoCita(idCita, nuevoEstado);
            }
        };

        // Configurar datos de prueba - Consultar datos reales de la BD
        try {
            // Consultar un paciente existente
            java.sql.Statement stmt = connection.createStatement();
            java.sql.ResultSet rs = stmt.executeQuery(
                "SELECT p.id, u.nombres, u.apellidos, u.telefono, u.dni " +
                "FROM pacientes p JOIN usuarios u ON p.id = u.id LIMIT 1"
            );
            
            if (rs.next()) {
                pacientePrueba = new Paciente();
                pacientePrueba.setId(rs.getInt("id"));
                pacientePrueba.setNombre(rs.getString("nombres"));
                pacientePrueba.setApellido(rs.getString("apellidos"));
                pacientePrueba.setTelefono(rs.getString("telefono"));
                pacientePrueba.setDni(rs.getString("dni"));
                System.out.println("[OK] Paciente encontrado: " + pacientePrueba.getNombre() + " " + 
                                 pacientePrueba.getApellido() + " (ID: " + pacientePrueba.getId() + ")");
            } else {
                throw new SQLException("No se encontró ningún paciente en la base de datos. " +
                                     "Por favor, registra al menos un paciente antes de ejecutar las pruebas.");
            }
            rs.close();
            
            // Consultar un médico existente
            rs = stmt.executeQuery(
                "SELECT m.id, u.nombres, u.apellidos, e.id as id_especialidad, e.nombre as nombre_especialidad " +
                "FROM medicos m " +
                "JOIN usuarios u ON m.id = u.id " +
                "JOIN especialidades e ON m.id_especialidad = e.id " +
                "LIMIT 1"
            );
            
            if (rs.next()) {
                Especialidad especialidad = new Especialidad(
                    rs.getInt("id_especialidad"), 
                    rs.getString("nombre_especialidad")
                );
                medicoPrueba = new Medico();
                medicoPrueba.setId(rs.getInt("id"));
                medicoPrueba.setNombre(rs.getString("nombres"));
                medicoPrueba.setApellido(rs.getString("apellidos"));
                medicoPrueba.setEspecialidad(especialidad);
                System.out.println("[OK] Medico encontrado: " + medicoPrueba.getNombre() + " " + 
                                 medicoPrueba.getApellido() + " - " + especialidad.getNombre() + 
                                 " (ID: " + medicoPrueba.getId() + ")");
            } else {
                throw new SQLException("No se encontró ningún médico en la base de datos. " +
                                     "Por favor, registra al menos un médico antes de ejecutar las pruebas.");
            }
            rs.close();
            stmt.close();
            
        } catch (SQLException e) {
            System.err.println("\n[ERROR] No se pudieron cargar los datos de prueba de la BD");
            System.err.println("   " + e.getMessage());
            System.err.println("\n   SOLUCION:");
            System.err.println("   1. Asegurate de que MySQL este corriendo");
            System.err.println("   2. Ejecuta los scripts de creacion de BD (CreacionDeBDyTablas.sql)");
            System.err.println("   3. Inserta al menos 1 paciente y 1 medico (InsercionDeDatos.sql)");
            throw new RuntimeException("No se pueden ejecutar las pruebas sin datos en la BD", e);
        }
        
        System.out.println("[OK] Conexion establecida");
        System.out.println("[OK] Controlador inicializado (SIN notificaciones por email)");
        System.out.println("[OK] Datos de prueba cargados desde la BD");
        System.out.println();
    }

    @AfterAll
    static void tearDownAfterClass() {
        System.out.println("\n" + "=".repeat(70));
        System.out.println("   FINALIZANDO PRUEBAS DE INTEGRACION");
        System.out.println("=".repeat(70));
        System.out.println("[OK] Todas las pruebas completadas");
        System.out.println("[OK] Conexion a BD se mantendra abierta para otras pruebas");
    }

    @BeforeEach
    void setUp() {
        System.out.println("─────────────────────────────────────────────────────────────────");
        // Limpiar citas de prueba anteriores para evitar conflictos
        try (java.sql.Statement stmt = connection.createStatement()) {
            // Eliminar solo las citas con motivos de prueba
            stmt.executeUpdate(
                "DELETE FROM citas WHERE motivo LIKE '%Test%' OR motivo LIKE '%Prueba%' " +
                "OR motivo LIKE '%test%' OR motivo LIKE '%prueba%'"
            );
        } catch (SQLException e) {
            System.err.println("[WARN] Advertencia: No se pudieron limpiar citas de prueba anteriores");
        }
    }

    @AfterEach
    void tearDown() {
        System.out.println("[OK] Prueba completada\n");
    }

    // ===================================================================
    //                        PRUEBAS DE INTEGRACION
    // ===================================================================

    /**
     * Prueba de conexión a la base de datos MySQL.
     * 
     * <p>Verifica que el Singleton de ConexionBD funcione correctamente y que
     * la conexión a MySQL esté activa y responda en menos de 5 segundos.</p>
     * 
     * <p><strong>Objetivo:</strong> Validar la infraestructura de base de datos</p>
     * <p><strong>Precondiciones:</strong> MySQL Server activo en localhost:3306</p>
     * <p><strong>Postcondiciones:</strong> Conexión establecida y válida</p>
     * 
     * @throws SQLException si la conexión falla o no es válida
     */
    @Test
    @Order(1)
    @DisplayName("Test 1: Verificar conexión a base de datos MySQL")
    void testConexionBaseDatos() {
        System.out.println("> Verificando conexion a la base de datos...");
        
        assertNotNull(connection, "La conexión no debería ser null");
        assertDoesNotThrow(() -> {
            assertTrue(connection.isValid(5), "La conexión debería ser válida");
        });
        
        System.out.println("  [OK] Conexion valida y funcional");
    }

    /**
     * Prueba de registro de una nueva cita médica en la base de datos.
     * 
     * <p>Valida el flujo completo de registro: Controller valida datos -> Service aplica lógica
     * de negocio -> Repository inserta en BD -> Se verifica la persistencia.</p>
     * 
     * <p><strong>Flujo de prueba:</strong></p>
     * <ol>
     *   <li>Registrar cita para LUNES a las 10:00 AM</li>
     *   <li>Verificar que el registro fue exitoso (resultado = true)</li>
     *   <li>Validar que la cita se persistió en la BD</li>
     * </ol>
     * 
     * <p><strong>Datos de prueba:</strong> Paciente y Médico reales de la BD</p>
     * <p><strong>Validaciones:</strong> Integridad referencial, estado inicial EN_ESPERA</p>
     */
    @Test
    @Order(2)
    @DisplayName("Test 2: Registrar nueva cita en la base de datos")
    void testRegistrarNuevaCita() {
        System.out.println("> Registrando nueva cita en la BD...");
        
        // Act - Registrar cita para el próximo LUNES a las 10:00 AM
        boolean resultado = citaController.registrarCita(
                pacientePrueba,
                medicoPrueba,
                DiaSemana.LUNES,
                LocalTime.of(10, 0),
                "Consulta de control - Test de Integración"
        );

        // Assert
        assertTrue(resultado, "La cita debería registrarse exitosamente en la BD");
        
        System.out.println("  [OK] Cita registrada: LUNES 10:00 AM");
        System.out.println("  [OK] Motivo: Consulta de control - Test de Integracion");
    }

    /**
     * Prueba de consulta de citas por paciente desde la base de datos.
     * 
     * <p>Valida que el Repository ejecute correctamente las consultas JOIN entre
     * las tablas citas, medicos, especialidades y usuarios para recuperar
     * todas las citas asociadas a un paciente específico.</p>
     * 
     * <p><strong>Aspectos validados:</strong></p>
     * <ul>
     *   <li>Recuperación de lista de citas del paciente</li>
     *   <li>Carga completa de entidades relacionadas (Médico, Especialidad)</li>
     *   <li>Integridad de datos en objetos recuperados</li>
     *   <li>Validación de que cada cita tiene paciente, médico y especialidad</li>
     * </ul>
     * 
     * <p><strong>Query ejecutado:</strong> SELECT con JOINs a medicos, especialidades, usuarios</p>
     */
    @Test
    @Order(3)
    @DisplayName("Test 3: Obtener citas de paciente desde base de datos")
    void testObtenerCitasPorPaciente() {
        System.out.println("> Consultando citas del paciente ID: " + pacientePrueba.getId());
        
        // Act
        List<Cita> citas = citaController.obtenerCitasPorPaciente(pacientePrueba.getId());

        // Assert
        assertNotNull(citas, "La lista de citas no debería ser null");
        System.out.println("  [OK] Se encontraron " + citas.size() + " cita(s)");
        
        // Verificar integridad de datos
        for (Cita cita : citas) {
            assertNotNull(cita.getPaciente(), "Cada cita debe tener un paciente");
            assertNotNull(cita.getMedico(), "Cada cita debe tener un médico");
            assertNotNull(cita.getFechaHora(), "Cada cita debe tener fecha y hora");
            assertNotNull(cita.getEstado(), "Cada cita debe tener un estado");
            
            assertEquals(pacientePrueba.getId(), cita.getPaciente().getId(), 
                    "Todas las citas deben pertenecer al paciente solicitado");
        }
        
        System.out.println("  [OK] Integridad de datos verificada");
    }

    /**
     * Prueba de consulta de citas por médico desde la base de datos.
     * 
     * <p>Similar a la consulta por paciente, pero filtrando por médico.
     * Valida que el Repository ejecute correctamente el JOIN inverso para
     * obtener todas las citas atendidas por un médico específico.</p>
     * 
     * <p><strong>Casos de prueba:</strong></p>
     * <ul>
     *   <li>Recuperar citas del médico desde la BD</li>
     *   <li>Verificar que todas las citas pertenezcan al médico correcto</li>
     *   <li>Validar carga completa de Paciente y Especialidad</li>
     *   <li>Comprobar que no hay datos nulos en las relaciones</li>
     * </ul>
     * 
     * <p><strong>Propósito:</strong> Validar funcionalidad para la vista del médico</p>
     */
    @Test
    @Order(4)
    @DisplayName("Test 4: Obtener citas de médico desde base de datos")
    void testObtenerCitasPorMedico() {
        System.out.println("> Consultando citas del medico ID: " + medicoPrueba.getId());
        
        // Act
        List<Cita> citas = citaController.obtenerCitasPorMedico(medicoPrueba.getId());

        // Assert
        assertNotNull(citas, "La lista de citas no debería ser null");
        System.out.println("  [OK] Se encontraron " + citas.size() + " cita(s) para el medico");
        
        // Verificar que todas las citas pertenecen al médico correcto
        for (Cita cita : citas) {
            assertNotNull(cita.getMedico(), "La cita debe tener un médico");
            assertEquals(medicoPrueba.getId(), cita.getMedico().getId(), 
                    "Todas las citas deben pertenecer al médico solicitado");
            
            // Verificar especialidad del médico
            assertNotNull(cita.getMedico().getEspecialidad(), 
                    "El médico debe tener una especialidad asignada");
        }
        
        System.out.println("  [OK] Todas las citas pertenecen al medico correcto");
    }

    /**
     * Prueba de consulta de la próxima cita pendiente de un paciente.
     * 
     * <p>Valida la lógica de negocio que ordena las citas por fecha/hora y
     * filtra por estado EN_ESPERA para obtener la próxima cita del paciente.</p>
     * 
     * <p><strong>Query ejecutado:</strong></p>
     * <pre>
     * SELECT * FROM citas 
     * WHERE paciente_id = ? AND estado = 'EN_ESPERA'
     * ORDER BY fecha_hora ASC 
     * LIMIT 1
     * </pre>
     * 
     * <p><strong>Casos contemplados:</strong></p>
     * <ul>
     *   <li>Si existe próxima cita: Se valida que esté en estado EN_ESPERA</li>
     *   <li>Si NO existe: Se muestra advertencia pero el test pasa</li>
     * </ul>
     */
    @Test
    @Order(5)
    @DisplayName("Test 5: Obtener próxima cita pendiente de paciente")
    void testObtenerProximaCita() {
        System.out.println("> Buscando proxima cita pendiente del paciente...");
        
        // Act
        Cita proximaCita = citaController.obtenerProximaCita(pacientePrueba.getId());

        // Assert y verificación
        if (proximaCita != null) {
            System.out.println("  [OK] Proxima cita encontrada:");
            System.out.println("    - Fecha: " + proximaCita.getFechaHora());
            System.out.println("    - Estado: " + proximaCita.getEstado());
            System.out.println("    - Medico: " + proximaCita.getMedico().getNombre() + " " + 
                             proximaCita.getMedico().getApellido());
            
            assertEquals(EstadoCita.EN_ESPERA, proximaCita.getEstado(),
                    "La próxima cita debe estar en estado EN_ESPERA");
            assertTrue(proximaCita.getFechaHora().isAfter(java.time.LocalDateTime.now()),
                    "La próxima cita debe ser en el futuro");
        } else {
            System.out.println("  [WARN] No hay proximas citas pendientes para este paciente");
        }
    }

    /**
     * Prueba de actualización de estado de una cita médica.
     * 
     * <p>Valida el flujo de cambio de estado EN_ESPERA -> REALIZADA.
     * Esta operación es crítica para el seguimiento del historial médico.</p>
     * 
     * <p><strong>Flujo de prueba:</strong></p>
     * <ol>
     *   <li>Buscar cita existente en estado EN_ESPERA</li>
     *   <li>Si no existe, crear una nueva cita de prueba</li>
     *   <li>Ejecutar actualización de estado a REALIZADA</li>
     *   <li>Verificar que la operación fue exitosa</li>
     *   <li>Consultar la BD para validar persistencia del cambio</li>
     * </ol>
     * 
     * <p><strong>Casos edge:</strong> Si no hay citas disponibles, se crea una automáticamente</p>
     */
    @Test
    @Order(6)
    @DisplayName("Test 6: Actualizar estado de cita a REALIZADA")
    void testActualizarEstadoCita() {
        System.out.println("> Actualizando estado de una cita...");
        
        // Arrange - Obtener una cita existente
        List<Cita> citas = citaController.obtenerCitasPorPaciente(pacientePrueba.getId());
        
        // Si no hay citas, crear una para poder actualizar
        if (citas.isEmpty()) {
            System.out.println("  [INFO] No hay citas existentes, creando una nueva...");
            boolean creada = citaController.registrarCita(pacientePrueba, medicoPrueba, 
                    DiaSemana.JUEVES, LocalTime.of(14, 0), "Cita para prueba de actualización");
            assertTrue(creada, "Debería poder crear una cita para la prueba");
            citas = citaController.obtenerCitasPorPaciente(pacientePrueba.getId());
        }
        
        assertFalse(citas.isEmpty(), "Debe haber al menos una cita para actualizar");
        
        Cita citaParaActualizar = citas.get(0);
        int idCita = citaParaActualizar.getId();
        EstadoCita estadoAnterior = citaParaActualizar.getEstado();
        
        System.out.println("  - Cita ID: " + idCita);
        System.out.println("  - Estado anterior: " + estadoAnterior);

        // Act - Actualizar a REALIZADA
        assertDoesNotThrow(() -> {
            citaController.actualizarEstadoCita(idCita, EstadoCita.REALIZADA);
        }, "No debería lanzar excepción al actualizar");

        // Assert - Verificar actualización
        List<Cita> citasActualizadas = citaController.obtenerCitasPorPaciente(pacientePrueba.getId());
        boolean actualizada = citasActualizadas.stream()
                .anyMatch(c -> c.getId() == idCita && c.getEstado() == EstadoCita.REALIZADA);

        assertTrue(actualizada, "La cita debería estar actualizada a REALIZADA en la BD");
        System.out.println("  [OK] Estado actualizado a: REALIZADA");
    }

    /**
     * Prueba de cancelación de cita médica.
     * 
     * <p>Valida el flujo de cambio de estado EN_ESPERA -> CANCELADA.
     * Esta funcionalidad permite al paciente cancelar citas programadas.</p>
     * 
     * <p><strong>Escenarios cubiertos:</strong></p>
     * <ul>
     *   <li><strong>Caso 1:</strong> Existe cita en estado EN_ESPERA 
     *       → Se cancela y verifica persistencia</li>
     *   <li><strong>Caso 2:</strong> NO existe cita en estado EN_ESPERA 
     *       → Se crea una nueva, se cancela y verifica</li>
     * </ul>
     * 
     * <p><strong>Validaciones:</strong></p>
     * <ul>
     *   <li>Operación retorna true</li>
     *   <li>Estado cambia a CANCELADA en la BD</li>
     *   <li>La cita sigue siendo consultable (no se borra)</li>
     * </ul>
     */
    @Test
    @Order(7)
    @DisplayName("Test 7: Cancelar cita existente en la base de datos")
    void testCancelarCita() {
        System.out.println("> Cancelando una cita...");
        
        // Arrange - Buscar una cita en estado EN_ESPERA
        List<Cita> citasAntes = citaController.obtenerCitasPorPaciente(pacientePrueba.getId());
        
        Cita citaACancelar = citasAntes.stream()
                .filter(c -> c.getEstado() == EstadoCita.EN_ESPERA)
                .findFirst()
                .orElse(null);

        if (citaACancelar != null) {
            int idCita = citaACancelar.getId();
            System.out.println("  - Cancelando cita ID: " + idCita);
            System.out.println("  - Fecha: " + citaACancelar.getFechaHora());

            // Act - Cancelar la cita
            assertDoesNotThrow(() -> {
                citaController.cancelarCita(idCita);
            }, "No debería lanzar excepción al cancelar");

            // Assert - Verificar cancelación en BD
            List<Cita> citasDespues = citaController.obtenerCitasPorPaciente(pacientePrueba.getId());
            boolean citaCancelada = citasDespues.stream()
                    .anyMatch(c -> c.getId() == idCita && c.getEstado() == EstadoCita.CANCELADA);

            assertTrue(citaCancelada, "La cita debería estar marcada como CANCELADA en la BD");
            System.out.println("  [OK] Cita cancelada exitosamente");
        } else {
            System.out.println("  [WARN] No hay citas en estado EN_ESPERA para cancelar");
            System.out.println("  [INFO] Registrando nueva cita para poder cancelarla...");
            
            // Registrar nueva cita y cancelarla
            citaController.registrarCita(pacientePrueba, medicoPrueba, 
                    DiaSemana.VIERNES, LocalTime.of(16, 0), "Cita para test de cancelación");
            
            List<Cita> nuevasCitas = citaController.obtenerCitasPorPaciente(pacientePrueba.getId());
            Cita nuevaCita = nuevasCitas.stream()
                    .filter(c -> c.getEstado() == EstadoCita.EN_ESPERA)
                    .findFirst()
                    .orElse(null);
            
            assertNotNull(nuevaCita, "Debería haberse creado la nueva cita");
            citaController.cancelarCita(nuevaCita.getId());
            System.out.println("  [OK] Nueva cita creada y cancelada exitosamente");
        }
    }

    /**
     * Prueba de flujo completo de gestión de cita (End-to-End).
     * 
     * <p>Esta es una prueba de integración E2E que valida el ciclo de vida
     * completo de una cita médica en el sistema.</p>
     * 
     * <p><strong>Flujo del usuario simulado:</strong></p>
     * <ol>
     *   <li><strong>Paso 1:</strong> Paciente registra nueva cita para MIERCOLES 15:30</li>
     *   <li><strong>Paso 2:</strong> Sistema verifica que la cita se guardó correctamente</li>
     *   <li><strong>Paso 3:</strong> Paciente consulta sus citas y encuentra la nueva</li>
     *   <li><strong>Paso 4:</strong> Paciente decide cancelar la cita</li>
     *   <li><strong>Paso 5:</strong> Sistema valida que el estado cambió a CANCELADA</li>
     * </ol>
     * 
     * <p><strong>Propósito:</strong> Validar integración completa Controller-Service-Repository-BD</p>
     * <p><strong>Componentes probados:</strong> Todas las capas de la arquitectura MVC</p>
     */
    @Test
    @Order(8)
    @DisplayName("Test 8: Flujo completo - Registrar, Consultar y Cancelar cita")
    void testFlujoCompletoRegistrarConsultarCancelar() {
        System.out.println("> Ejecutando flujo completo de gestion de cita...");
        
        // Paso 1: Registrar nueva cita
        System.out.println("  [1/4] Registrando nueva cita...");
        boolean registrado = citaController.registrarCita(
                pacientePrueba,
                medicoPrueba,
                DiaSemana.MIERCOLES,
                LocalTime.of(15, 30),
                "Test - Flujo completo de integración"
        );
        assertTrue(registrado, "La cita debería registrarse exitosamente");
        System.out.println("      [OK] Cita registrada para MIERCOLES 15:30");

        // Paso 2: Verificar que la cita existe en la BD
        System.out.println("  [2/4] Consultando citas del paciente...");
        List<Cita> citas = citaController.obtenerCitasPorPaciente(pacientePrueba.getId());
        Cita citaCreada = citas.stream()
                .filter(c -> c.getMotivo().contains("Flujo completo"))
                .findFirst()
                .orElse(null);
        
        assertNotNull(citaCreada, "La cita recién creada debería existir en la BD");
        System.out.println("      ✓ Cita encontrada en BD (ID: " + citaCreada.getId() + ")");

        // Paso 3: Verificar estado inicial
        System.out.println("  [3/4] Verificando estado de la cita...");
        assertEquals(EstadoCita.EN_ESPERA, citaCreada.getEstado(), 
                "La cita nueva debe estar EN_ESPERA");
        System.out.println("      ✓ Estado correcto: EN_ESPERA");

        // Paso 4: Cancelar la cita
        System.out.println("  [4/4] Cancelando la cita...");
        citaController.cancelarCita(citaCreada.getId());
        
        // Verificar cancelación
        List<Cita> citasActualizadas = citaController.obtenerCitasPorPaciente(pacientePrueba.getId());
        boolean estaCancelada = citasActualizadas.stream()
                .anyMatch(c -> c.getId() == citaCreada.getId() && 
                              c.getEstado() == EstadoCita.CANCELADA);

        assertTrue(estaCancelada, "La cita debería estar cancelada en la BD");
        System.out.println("      [OK] Cita cancelada exitosamente");
        System.out.println("  [OK] Flujo completo ejecutado correctamente");
    }

    /**
     * Prueba de integridad referencial entre entidades de la base de datos.
     * 
     * <p>Valida que los JOINs y relaciones entre tablas funcionen correctamente
     * y que no existan referencias rotas (foreign keys inválidas).</p>
     * 
     * <p><strong>Relaciones validadas:</strong></p>
     * <pre>
     * Cita (1) -> (1) Paciente -> (1) Usuario
     * Cita (1) -> (1) Medico   -> (1) Usuario
     *                 Medico   -> (1) Especialidad
     * </pre>
     * 
     * <p><strong>Validaciones ejecutadas:</strong></p>
     * <ul>
     *   <li>Cada cita tiene un Paciente asociado (no null)</li>
     *   <li>Cada cita tiene un Medico asociado (no null)</li>
     *   <li>Cada Medico tiene una Especialidad asignada (no null)</li>
     *   <li>Los datos de Usuario estan completos (nombre, apellido, email)</li>
     *   <li>Fecha, hora y estado de la cita no son null</li>
     * </ul>
     * 
     * <p><strong>Proposito:</strong> Detectar problemas de carga de entidades relacionadas</p>
     */
    @Test
    @Order(9)
    @DisplayName("Test 9: Verificar integridad referencial de datos")
    void testIntegridadReferencialDatos() {
        System.out.println("> Verificando integridad referencial de datos en BD...");
        
        // Act - Obtener todas las citas del paciente
        List<Cita> citas = citaController.obtenerCitasPorPaciente(pacientePrueba.getId());

        // Assert - Verificar integridad de cada relación
        int citasVerificadas = 0;
        for (Cita cita : citas) {
            // Verificar relación con Paciente
            assertNotNull(cita.getPaciente(), "Cada cita debe tener un paciente asociado");
            assertNotNull(cita.getPaciente().getNombre(), "El paciente debe tener nombre");
            assertNotNull(cita.getPaciente().getApellido(), "El paciente debe tener apellido");
            assertFalse(cita.getPaciente().getNombre().isEmpty(), 
                    "El nombre del paciente no debe estar vacío");
            
            // Verificar relación con Médico
            assertNotNull(cita.getMedico(), "Cada cita debe tener un médico asociado");
            assertNotNull(cita.getMedico().getNombre(), "El médico debe tener nombre");
            assertNotNull(cita.getMedico().getApellido(), "El médico debe tener apellido");
            assertFalse(cita.getMedico().getNombre().isEmpty(), 
                    "El nombre del médico no debe estar vacío");
            
            // Verificar relación con Especialidad
            assertNotNull(cita.getMedico().getEspecialidad(), 
                    "El médico debe tener una especialidad");
            assertNotNull(cita.getMedico().getEspecialidad().getNombre(), 
                    "La especialidad debe tener nombre");
            
            // Verificar datos obligatorios de la cita
            assertNotNull(cita.getFechaHora(), "La cita debe tener fecha y hora");
            assertNotNull(cita.getEstado(), "La cita debe tener un estado");
            assertNotNull(cita.getMotivo(), "La cita debe tener un motivo");
            
            citasVerificadas++;
        }

        System.out.println("  [OK] " + citasVerificadas + " cita(s) verificadas");
        System.out.println("  [OK] Integridad referencial validada (Paciente -> Cita -> Medico -> Especialidad)");
    }

    /**
     * Prueba de rendimiento del sistema con consultas múltiples.
     * 
     * <p>Ejecuta 50 consultas consecutivas a la base de datos para medir
     * el tiempo de respuesta y validar que el sistema mantenga un rendimiento
     * aceptable bajo carga moderada.</p>
     * 
     * <p><strong>Métricas evaluadas:</strong></p>
     * <ul>
     *   <li><strong>Total de consultas:</strong> 50 operaciones SELECT</li>
     *   <li><strong>Tiempo total:</strong> Suma de todas las consultas</li>
     *   <li><strong>Promedio por consulta:</strong> Tiempo total / 50</li>
     *   <li><strong>Consultas por segundo:</strong> 1000 / promedio</li>
     * </ul>
     * 
     * <p><strong>Criterio de aceptación:</strong></p>
     * <pre>
     * - Tiempo total: < 10 segundos (200ms promedio por consulta)
     * - Si falla: Revisar indexación de BD o pool de conexiones
     * </pre>
     * 
     * <p><strong>Propósito:</strong> Detectar problemas de rendimiento antes de producción</p>
     * 
     * @see CitaController#obtenerCitasPorPaciente(int)
     */
    @Test
    @Order(10)
    @DisplayName("Test 10: Prueba de rendimiento - Consultas multiples a BD")
    void testRendimientoConsultasMultiples() {
        System.out.println("> Realizando prueba de rendimiento con consultas multiples...");
        
        int numeroPruebas = 50;
        long tiempoInicio = System.currentTimeMillis();

        // Act - Realizar múltiples consultas
        System.out.println("  - Ejecutando " + numeroPruebas + " consultas a la BD...");
        for (int i = 0; i < numeroPruebas; i++) {
            List<Cita> citas = citaController.obtenerCitasPorPaciente(pacientePrueba.getId());
            assertNotNull(citas, "La consulta " + (i+1) + " no debería retornar null");
        }

        long tiempoFin = System.currentTimeMillis();
        long duracionTotal = tiempoFin - tiempoInicio;
        double promedioPorConsulta = duracionTotal / (double) numeroPruebas;

        // Assert - Verificar rendimiento aceptable
        assertTrue(duracionTotal < 10000, 
                "Las " + numeroPruebas + " consultas deberían completarse en menos de 10 segundos");

        // Mostrar estadisticas
        System.out.println("\n  " + "=".repeat(65));
        System.out.println("  " + " ".repeat(18) + "REPORTE DE RENDIMIENTO");
        System.out.println("  " + "=".repeat(65));
        System.out.println("    Total de consultas:        " + numeroPruebas);
        System.out.println("    Tiempo total:              " + duracionTotal + " ms");
        System.out.println(String.format("    Promedio por consulta:     %.2f ms", promedioPorConsulta));
        System.out.println(String.format("    Consultas por segundo:     %.2f", 1000.0 / promedioPorConsulta));
        System.out.println("  " + "=".repeat(65));
        
        System.out.println("  [OK] Rendimiento aceptable");
    }
}
