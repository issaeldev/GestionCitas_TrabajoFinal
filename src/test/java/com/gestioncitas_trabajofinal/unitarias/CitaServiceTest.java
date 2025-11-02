package com.gestioncitas_trabajofinal.unitarias;

import com.gestioncitas_trabajofinal.model.*;
import com.gestioncitas_trabajofinal.observer.Observer;
import com.gestioncitas_trabajofinal.repository.CitaRepository;
import com.gestioncitas_trabajofinal.service.CitaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Pruebas Unitarias con Mockito para CitaService.
 * 
 * <p>Estas pruebas validan la lógica de negocio del servicio de forma aislada,
 * utilizando mocks del repositorio en lugar de conexiones reales a la base de datos.</p>
 * 
 * <p><strong>¿Por qué usar Mockito en pruebas unitarias?</strong></p>
 * <ul>
 *   <li>Aísla la lógica de negocio sin depender de la BD</li>
 *   <li>Pruebas más rápidas (sin I/O de base de datos)</li>
 *   <li>Permite simular escenarios difíciles de reproducir</li>
 *   <li>Valida que el servicio llame correctamente al repositorio</li>
 * </ul>
 * 
 * <p><strong>Componentes mockeados:</strong></p>
 * <ul>
 *   <li><strong>CitaRepository:</strong> Mock para simular operaciones de BD</li>
 *   <li><strong>Observer:</strong> Mock para verificar notificaciones</li>
 * </ul>
 * 
 * <p><strong>Patrón utilizado:</strong> Arrange-Act-Assert (AAA)</p>
 * 
 * <p><strong>NOTA:</strong> Estos tests NO requieren base de datos activa.</p>
 * 
 * @author Sistema de Gestión de Citas
 * @version 1.0
 * @since 2025-11-02
 * @see CitaService
 * @see CitaRepository
 */
@DisplayName("Pruebas Unitarias con Mockito - CitaService")
class CitaServiceTest {

    private CitaRepository citaRepositoryMock;
    private Observer observerMock;
    private CitaService citaService;
    private Paciente pacientePrueba;
    private Medico medicoPrueba;

    @BeforeEach
    void setUp() {
        // Crear mocks manualmente (compatible con Java 17)
        citaRepositoryMock = mock(CitaRepository.class);
        observerMock = mock(Observer.class);

        // Crear el servicio con el repositorio mockeado
        citaService = new CitaService(citaRepositoryMock);

        // Registrar el observador mock
        citaService.addObserver(observerMock);

        // Setup de datos de prueba
        Especialidad especialidadPrueba = new Especialidad(1, "Cardiología");
        medicoPrueba = new Medico("juanperez", "secreto", "Dr. Juan", "Pérez",
                "984878378", "39972711", new Date(),
                "Javier Prado", especialidadPrueba);
        medicoPrueba.setId(1);

        pacientePrueba = new Paciente();
        pacientePrueba.setId(1);
        pacientePrueba.setNombre("Pedro");
        pacientePrueba.setApellido("García");
    }

    /**
     * Prueba de registro exitoso de una cita médica.
     * 
     * <p>Valida que el servicio registre correctamente una cita cuando
     * el médico está disponible en el horario solicitado.</p>
     * 
     * <p><strong>Escenario:</strong> Médico libre, sin conflictos de horario</p>
     * <p><strong>Flujo:</strong></p>
     * <ol>
     *   <li>Verificar que no existe cita en ese horario → false</li>
     *   <li>Guardar la nueva cita → true</li>
     *   <li>Notificar a los observadores (patrón Observer)</li>
     * </ol>
     * 
     * <p><strong>Validaciones:</strong></p>
     * <ul>
     *   <li>El método retorna true (éxito)</li>
     *   <li>Se verificó la disponibilidad del médico (1 vez)</li>
     *   <li>Se guardó la cita (1 vez)</li>
     *   <li>Se notificó al observador (1 vez)</li>
     * </ul>
     */
    @Test
    @DisplayName("Test 1: Registrar cita exitosamente")
    void testRegistrarCitaExitoso() {
        // Arrange - Configurar datos de prueba y comportamiento del mock
        LocalDateTime fechaHora = LocalDateTime.now().plusDays(5);

        when(citaRepositoryMock.existeCitaParaMedicoEnFechaHora(medicoPrueba.getId(), fechaHora))
                .thenReturn(false);
        when(citaRepositoryMock.guardar(any(Cita.class)))
                .thenReturn(true);

        // Act - Ejecutar el registro de la cita
        boolean resultado = citaService.registrarCita(pacientePrueba, medicoPrueba, fechaHora, "Dolor de pecho");

        // Assert - Verificar resultado y comportamiento
        assertTrue(resultado, "La cita debería registrarse exitosamente");

        verify(citaRepositoryMock, times(1))
                .existeCitaParaMedicoEnFechaHora(medicoPrueba.getId(), fechaHora);
        verify(citaRepositoryMock, times(1))
                .guardar(any(Cita.class));
        verify(observerMock, times(1))
                .update(any(Cita.class));
    }

    /**
     * Prueba de validación de citas duplicadas en el mismo horario.
     * 
     * <p>Verifica que el servicio rechace el registro de una cita cuando
     * el médico ya tiene otra cita programada en el mismo horario.</p>
     * 
     * <p><strong>Escenario:</strong> Médico ocupado, conflicto de horario</p>
     * <p><strong>Regla de negocio:</strong> Un médico solo puede atender una cita a la vez</p>
     * 
     * <p><strong>Flujo esperado:</strong></p>
     * <ol>
     *   <li>Verificar disponibilidad → Ya existe cita (true)</li>
     *   <li>NO guardar la cita</li>
     *   <li>NO notificar a observadores</li>
     *   <li>Retornar false (rechazo)</li>
     * </ol>
     * 
     * <p><strong>Validaciones:</strong></p>
     * <ul>
     *   <li>El método retorna false (rechazo)</li>
     *   <li>Se verificó la existencia (1 vez)</li>
     *   <li>NO se llamó a guardar (never())</li>
     *   <li>NO se notificó al observador (never())</li>
     * </ul>
     */
    @Test
    @DisplayName("Test 2: No permitir cita duplicada en mismo horario")
    void testRegistrarCitaDuplicada() {
        // Arrange - Simular que ya existe una cita en ese horario
        LocalDateTime fechaHora = LocalDateTime.now().plusDays(3);

        when(citaRepositoryMock.existeCitaParaMedicoEnFechaHora(medicoPrueba.getId(), fechaHora))
                .thenReturn(true);

        // Act - Intentar registrar cita en horario ocupado
        boolean resultado = citaService.registrarCita(pacientePrueba, medicoPrueba, fechaHora, "Segunda consulta");

        // Assert - Verificar rechazo y que no se guardó ni notificó
        assertFalse(resultado, "No debería permitir cita duplicada en el mismo horario");

        verify(citaRepositoryMock, times(1))
                .existeCitaParaMedicoEnFechaHora(medicoPrueba.getId(), fechaHora);
        verify(citaRepositoryMock, never())
                .guardar(any(Cita.class));
        verify(observerMock, never())
                .update(any(Cita.class));
    }

    /**
     * Prueba de consulta de citas por paciente.
     * 
     * <p>Valida que el servicio delegue correctamente al repositorio
     * la consulta de todas las citas de un paciente específico.</p>
     * 
     * <p><strong>Datos de prueba:</strong> 3 citas del mismo paciente</p>
     * <p><strong>Objetivo:</strong> Verificar que el servicio actúa como capa intermedia</p>
     * 
     * <p><strong>Validaciones:</strong></p>
     * <ul>
     *   <li>La lista retornada no es null</li>
     *   <li>Contiene exactamente 3 citas</li>
     *   <li>Se llamó al repositorio exactamente 1 vez</li>
     * </ul>
     */
    @Test
    @DisplayName("Test 3: Obtener citas por paciente")
    void testObtenerCitasPorPaciente() {
        // Arrange - Crear 3 citas de prueba
        Cita cita1 = crearCita(1, pacientePrueba, medicoPrueba, LocalDateTime.now().plusDays(1), "Consulta 1");
        Cita cita2 = crearCita(2, pacientePrueba, medicoPrueba, LocalDateTime.now().plusDays(2), "Consulta 2");
        Cita cita3 = crearCita(3, pacientePrueba, medicoPrueba, LocalDateTime.now().plusDays(3), "Consulta 3");

        List<Cita> citasEsperadas = Arrays.asList(cita1, cita2, cita3);

        when(citaRepositoryMock.obtenerCitasPorPaciente(1))
                .thenReturn(citasEsperadas);

        // Act - Consultar citas del paciente
        List<Cita> resultado = citaService.obtenerCitasPorPaciente(1);

        // Assert - Verificar lista y delegación al repositorio
        assertNotNull(resultado, "La lista no debería ser null");
        assertEquals(3, resultado.size(), "Debería haber 3 citas");

        verify(citaRepositoryMock, times(1))
                .obtenerCitasPorPaciente(1);
    }

    /**
     * Prueba de consulta de citas por médico.
     * 
     * <p>Valida que el servicio recupere correctamente todas las citas
     * asignadas a un médico específico, independientemente del paciente.</p>
     * 
     * <p><strong>Datos de prueba:</strong> 2 citas del mismo médico con diferentes pacientes</p>
     * <p><strong>Caso de uso:</strong> Vista de agenda del médico</p>
     * 
     * <p><strong>Validaciones:</strong></p>
     * <ul>
     *   <li>La lista retornada no es null</li>
     *   <li>Contiene exactamente 2 citas</li>
     *   <li>Se delegó correctamente al repositorio (1 llamada)</li>
     * </ul>
     */
    @Test
    @DisplayName("Test 4: Obtener citas por médico")
    void testObtenerCitasPorMedico() {
        // Arrange - Crear 2 citas del mismo médico con diferentes pacientes
        Paciente otroPaciente = new Paciente();
        otroPaciente.setId(2);
        otroPaciente.setNombre("María");
        otroPaciente.setApellido("López");

        Cita cita1 = crearCita(1, pacientePrueba, medicoPrueba, LocalDateTime.now().plusDays(1), "Consulta 1");
        Cita cita2 = crearCita(2, otroPaciente, medicoPrueba, LocalDateTime.now().plusDays(2), "Consulta 2");

        List<Cita> citasEsperadas = Arrays.asList(cita1, cita2);

        when(citaRepositoryMock.obtenerCitasPorMedico(1))
                .thenReturn(citasEsperadas);

        // Act - Consultar citas del médico
        List<Cita> resultado = citaService.obtenerCitasPorMedico(1);

        // Assert - Verificar lista y delegación
        assertNotNull(resultado, "La lista no debería ser null");
        assertEquals(2, resultado.size(), "El médico debería tener 2 citas");

        verify(citaRepositoryMock, times(1))
                .obtenerCitasPorMedico(1);
    }

    /**
     * Prueba de consulta de la próxima cita pendiente de un paciente.
     * 
     * <p>Valida que el servicio recupere correctamente la cita más cercana
     * en estado EN_ESPERA para un paciente específico.</p>
     * 
     * <p><strong>Caso de uso:</strong> Mostrar próxima cita al paciente en dashboard</p>
     * <p><strong>Regla:</strong> Solo citas futuras en estado EN_ESPERA</p>
     * 
     * <p><strong>Validaciones:</strong></p>
     * <ul>
     *   <li>Retorna una cita (no null)</li>
     *   <li>La cita está en estado EN_ESPERA</li>
     *   <li>El motivo es correcto</li>
     *   <li>Se delegó al repositorio (1 llamada)</li>
     * </ul>
     */
    @Test
    @DisplayName("Test 5: Obtener próxima cita por paciente")
    void testObtenerProximaCitaPorPaciente() {
        // Arrange - Crear cita próxima (mañana)
        LocalDateTime fechaCercana = LocalDateTime.now().plusDays(1);
        Cita citaProxima = crearCita(1, pacientePrueba, medicoPrueba, fechaCercana, "Consulta cercana");

        when(citaRepositoryMock.obtenerProximaCitaPorPaciente(1))
                .thenReturn(citaProxima);

        // Act - Consultar próxima cita
        Cita resultado = citaService.obtenerProximaCitaPorPaciente(1);

        // Assert - Verificar datos de la cita y delegación
        assertNotNull(resultado, "Debería encontrar una próxima cita");
        assertEquals(EstadoCita.EN_ESPERA, resultado.getEstado());
        assertEquals("Consulta cercana", resultado.getMotivo());

        verify(citaRepositoryMock, times(1))
                .obtenerProximaCitaPorPaciente(1);
    }

    /**
     * Prueba de actualización de estado de una cita.
     * 
     * <p>Valida que el servicio delegue correctamente al repositorio
     * la actualización del estado de una cita.</p>
     * 
     * <p><strong>Transición probada:</strong> Cualquier estado → REALIZADA</p>
     * <p><strong>Operación:</strong> Método void (no retorna valor)</p>
     * 
     * <p><strong>Validaciones:</strong></p>
     * <ul>
     *   <li>No lanza excepciones</li>
     *   <li>Se llamó al repositorio con los parámetros correctos</li>
     *   <li>Se llamó exactamente 1 vez</li>
     * </ul>
     */
    @Test
    @DisplayName("Test 6: Actualizar estado de cita")
    void testActualizarEstadoCita() {
        // Arrange - Preparar ID de cita y nuevo estado
        int idCita = 1;
        EstadoCita nuevoEstado = EstadoCita.REALIZADA;

        doNothing().when(citaRepositoryMock)
                .actualizarEstado(idCita, nuevoEstado);

        // Act - Actualizar estado de la cita
        citaService.actualizarEstadoCita(idCita, nuevoEstado);

        // Assert - Verificar delegación al repositorio
        verify(citaRepositoryMock, times(1))
                .actualizarEstado(idCita, nuevoEstado);
    }

    /**
     * Prueba de manejo de lista vacía al consultar citas.
     * 
     * <p>Valida que el servicio maneje correctamente el caso donde
     * un paciente no tiene ninguna cita registrada.</p>
     * 
     * <p><strong>Escenario:</strong> Paciente sin citas (paciente nuevo o sin historial)</p>
     * <p><strong>Comportamiento esperado:</strong> Lista vacía, no null</p>
     * 
     * <p><strong>Validaciones:</strong></p>
     * <ul>
     *   <li>La lista no es null (evita NullPointerException)</li>
     *   <li>La lista está vacía (size == 0)</li>
     *   <li>Se consultó correctamente al repositorio</li>
     * </ul>
     */
    @Test
    @DisplayName("Test 7: Verificar que el servicio maneja lista vacía")
    void testObtenerCitasPorPacienteListaVacia() {
        // Arrange - Simular paciente sin citas
        when(citaRepositoryMock.obtenerCitasPorPaciente(999))
                .thenReturn(Collections.emptyList());

        // Act - Consultar citas de paciente sin historial
        List<Cita> resultado = citaService.obtenerCitasPorPaciente(999);

        // Assert - Verificar lista vacía pero no null
        assertNotNull(resultado, "La lista no debería ser null");
        assertTrue(resultado.isEmpty(), "La lista debería estar vacía");

        verify(citaRepositoryMock, times(1))
                .obtenerCitasPorPaciente(999);
    }

    /**
     * Prueba de cancelación de cita (cambio a estado CANCELADA).
     * 
     * <p>Valida que el servicio pueda cambiar el estado de una cita
     * a CANCELADA correctamente.</p>
     * 
     * <p><strong>Transición:</strong> EN_ESPERA → CANCELADA</p>
     * <p><strong>Caso de uso:</strong> Paciente cancela su cita programada</p>
     * 
     * <p><strong>Validaciones:</strong></p>
     * <ul>
     *   <li>La operación se ejecuta sin errores</li>
     *   <li>Se delegó al repositorio con el estado correcto</li>
     * </ul>
     */
    @Test
    @DisplayName("Test 8: Cancelar cita cambia su estado")
    void testCancelarCita() {
        // Arrange - Preparar cita a cancelar
        int idCita = 1;
        EstadoCita estadoCancelado = EstadoCita.CANCELADA;

        doNothing().when(citaRepositoryMock)
                .actualizarEstado(idCita, estadoCancelado);

        // Act - Cancelar la cita
        citaService.actualizarEstadoCita(idCita, estadoCancelado);

        // Assert - Verificar actualización
        verify(citaRepositoryMock, times(1))
                .actualizarEstado(idCita, estadoCancelado);
    }

    /**
     * Prueba de rechazo de cita cuando el médico está ocupado.
     * 
     * <p>Valida la misma regla de negocio que el Test 2, pero desde
     * otra perspectiva para asegurar la consistencia.</p>
     * 
     * <p><strong>Regla de negocio:</strong> Un médico solo puede atender una cita por horario</p>
     * <p><strong>Escenario:</strong> Médico con agenda ocupada</p>
     * 
     * <p><strong>Validaciones:</strong></p>
     * <ul>
     *   <li>Registro rechazado (retorna false)</li>
     *   <li>Se verificó disponibilidad (1 vez)</li>
     *   <li>NO se guardó la cita (never)</li>
     * </ul>
     */
    @Test
    @DisplayName("Test 9: No permite cita si médico ya tiene cita activa")
    void testNoPemiteCitaConMedicoOcupado() {
        // Arrange - Simular médico ocupado en el horario solicitado
        LocalDateTime fechaHora = LocalDateTime.now().plusDays(2);

        when(citaRepositoryMock.existeCitaParaMedicoEnFechaHora(medicoPrueba.getId(), fechaHora))
                .thenReturn(true);

        // Act - Intentar registrar cita en horario ocupado
        boolean resultado = citaService.registrarCita(pacientePrueba, medicoPrueba, fechaHora, "Segunda cita");

        // Assert - Verificar rechazo
        assertFalse(resultado, "No debería permitir otra cita en el mismo horario");

        verify(citaRepositoryMock, times(1))
                .existeCitaParaMedicoEnFechaHora(medicoPrueba.getId(), fechaHora);
        verify(citaRepositoryMock, never())
                .guardar(any(Cita.class));
    }

    /**
     * Prueba del patrón Observer con múltiples observadores.
     * 
     * <p>Valida que el servicio notifique correctamente a todos los
     * observadores registrados cuando se registra una nueva cita.</p>
     * 
     * <p><strong>Patrón:</strong> Observer (Publicador-Suscriptor)</p>
     * <p><strong>Caso de uso:</strong> Notificar por email y SMS simultáneamente</p>
     * 
     * <p><strong>Validaciones:</strong></p>
     * <ul>
     *   <li>Ambos observadores son notificados (1 vez cada uno)</li>
     *   <li>La notificación ocurre después del registro exitoso</li>
     * </ul>
     */
    @Test
    @DisplayName("Test 10: Verificar múltiples registros de observadores")
    void testMultiplesObservadores() {
        // Arrange - Registrar segundo observador
        Observer observador2 = mock(Observer.class);
        citaService.addObserver(observador2);

        LocalDateTime fechaHora = LocalDateTime.now().plusDays(5);

        when(citaRepositoryMock.existeCitaParaMedicoEnFechaHora(medicoPrueba.getId(), fechaHora))
                .thenReturn(false);
        when(citaRepositoryMock.guardar(any(Cita.class)))
                .thenReturn(true);

        // Act - Registrar cita
        citaService.registrarCita(pacientePrueba, medicoPrueba, fechaHora, "Consulta");

        // Assert - Verificar que ambos observadores fueron notificados
        verify(observerMock, times(1))
                .update(any(Cita.class));
        verify(observador2, times(1))
                .update(any(Cita.class));
    }

    /**
     * Prueba de remoción de observadores del patrón Observer.
     * 
     * <p>Valida que después de remover un observador, este ya no
     * reciba notificaciones de eventos futuros.</p>
     * 
     * <p><strong>Operación:</strong> removeObserver()</p>
     * <p><strong>Comportamiento esperado:</strong> Observador removido no es notificado</p>
     * 
     * <p><strong>Validaciones:</strong></p>
     * <ul>
     *   <li>El observador removido NO recibe notificaciones (never)</li>
     *   <li>El registro de cita funciona normalmente</li>
     * </ul>
     */
    @Test
    @DisplayName("Test 11: Remover observador funciona correctamente")
    void testRemoverObservador() {
        // Arrange - Remover el observador antes de la operación
        citaService.removeObserver(observerMock);

        LocalDateTime fechaHora = LocalDateTime.now().plusDays(5);

        when(citaRepositoryMock.existeCitaParaMedicoEnFechaHora(medicoPrueba.getId(), fechaHora))
                .thenReturn(false);
        when(citaRepositoryMock.guardar(any(Cita.class)))
                .thenReturn(true);

        // Act - Registrar cita después de remover observador
        citaService.registrarCita(pacientePrueba, medicoPrueba, fechaHora, "Consulta");

        // Assert - Verificar que el observador removido NO fue notificado
        verify(observerMock, never())
                .update(any(Cita.class));
    }

    /**
     * Prueba de validación de argumentos con ArgumentCaptor/Matcher.
     * 
     * <p>Valida que la cita guardada en el repositorio tenga todos los
     * datos correctos: paciente, médico, fecha/hora, motivo y estado inicial.</p>
     * 
     * <p><strong>Técnica:</strong> ArgumentCaptor con argThat()</p>
     * <p><strong>Objetivo:</strong> Verificar que el objeto Cita se construye correctamente</p>
     * 
     * <p><strong>Validaciones:</strong></p>
     * <ul>
     *   <li>Paciente correcto en la cita</li>
     *   <li>Médico correcto en la cita</li>
     *   <li>Fecha/hora correcta</li>
     *   <li>Motivo correcto</li>
     *   <li>Estado inicial es EN_ESPERA</li>
     * </ul>
     */
    @Test
    @DisplayName("Test 12: Verificar argumentos específicos en guardar")
    void testVerificarArgumentosGuardar() {
        // Arrange - Preparar datos específicos de la cita
        LocalDateTime fechaHora = LocalDateTime.now().plusDays(5);
        String motivo = "Control de presión";

        when(citaRepositoryMock.existeCitaParaMedicoEnFechaHora(medicoPrueba.getId(), fechaHora))
                .thenReturn(false);
        when(citaRepositoryMock.guardar(any(Cita.class)))
                .thenReturn(true);

        // Act - Registrar cita con datos específicos
        citaService.registrarCita(pacientePrueba, medicoPrueba, fechaHora, motivo);

        // Assert - Verificar que la cita tiene todos los datos correctos
        verify(citaRepositoryMock).guardar(argThat(cita ->
                cita.getPaciente().equals(pacientePrueba) &&
                        cita.getMedico().equals(medicoPrueba) &&
                        cita.getFechaHora().equals(fechaHora) &&
                        cita.getMotivo().equals(motivo) &&
                        cita.getEstado() == EstadoCita.EN_ESPERA
        ));
    }

    /**
     * Prueba de manejo de null al consultar próxima cita.
     * 
     * <p>Valida que el servicio maneje correctamente el caso donde
     * un paciente no tiene próximas citas pendientes.</p>
     * 
     * <p><strong>Escenario:</strong> Paciente sin citas futuras o todas canceladas/realizadas</p>
     * <p><strong>Comportamiento esperado:</strong> Retorna null (no hay próxima cita)</p>
     * 
     * <p><strong>Validaciones:</strong></p>
     * <ul>
     *   <li>Retorna null (no lanza excepción)</li>
     *   <li>Se consultó correctamente al repositorio</li>
     * </ul>
     */
    @Test
    @DisplayName("Test 13: Próxima cita retorna null si no hay citas")
    void testProximaCitaRetornaNullSiNoHayCitas() {
        // Arrange - Simular que no hay próximas citas
        when(citaRepositoryMock.obtenerProximaCitaPorPaciente(1))
                .thenReturn(null);

        // Act - Consultar próxima cita
        Cita resultado = citaService.obtenerProximaCitaPorPaciente(1);

        // Assert - Verificar que retorna null sin errores
        assertNull(resultado, "Debería retornar null si no hay próximas citas");

        verify(citaRepositoryMock, times(1))
                .obtenerProximaCitaPorPaciente(1);
    }

    // ==================== METODOS AUXILIARES ====================

    /**
     * Método auxiliar para crear una cita de prueba.
     * 
     * <p>Construye un objeto Cita con todos los datos necesarios
     * para las pruebas unitarias.</p>
     * 
     * @param id Identificador único de la cita
     * @param paciente Paciente asociado a la cita
     * @param medico Médico que atenderá la cita
     * @param fechaHora Fecha y hora de la cita
     * @param motivo Motivo de la consulta
     * @return Objeto Cita configurado con estado inicial EN_ESPERA
     */
    private Cita crearCita(int id, Paciente paciente, Medico medico, LocalDateTime fechaHora, String motivo) {
        Cita cita = new Cita();
        cita.setId(id);
        cita.setPaciente(paciente);
        cita.setMedico(medico);
        cita.setFechaHora(fechaHora);
        cita.setMotivo(motivo);
        cita.setEstado(EstadoCita.EN_ESPERA);
        return cita;
    }
}
