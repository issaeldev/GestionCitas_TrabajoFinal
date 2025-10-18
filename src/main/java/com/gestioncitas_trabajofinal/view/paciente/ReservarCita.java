package com.gestioncitas_trabajofinal.view.paciente;

import java.awt.*;
import javax.swing.*;
import javax.swing.GroupLayout;
import com.gestioncitas_trabajofinal.controller.EspecialidadController;
import com.gestioncitas_trabajofinal.controller.MedicoController;
import com.gestioncitas_trabajofinal.model.DiaSemana;
import com.gestioncitas_trabajofinal.model.DisponibilidadMedica;
import com.gestioncitas_trabajofinal.model.Especialidad;
import com.gestioncitas_trabajofinal.model.Medico;
import com.gestioncitas_trabajofinal.model.Paciente;
import java.time.LocalTime;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 * Ventana que guía al paciente a través del proceso de reserva de una nueva cita,
 * permitiéndole seleccionar especialidad, médico y horario disponible.
 * @author lumic
 */
public class ReservarCita extends javax.swing.JFrame {

    // --- ATRIBUTOS ---
    private final Paciente paciente;
    private List<Especialidad> listaEspecialidades;
    private List<Medico> listaMedicosActuales;
    
    private final EspecialidadController especialidadController;
    private final MedicoController medicoController;
    
    /**
     * Constructor que inicializa la ventana de reserva de citas.
     * @param paciente El paciente que está realizando la reserva.
     */
    public ReservarCita(Paciente paciente) {
        this.paciente = paciente;
        this.especialidadController = new EspecialidadController();
        this.medicoController = new MedicoController();
        initComponents();
        cargarEspecialidades();
    }

    // --- MÉTODOS DE CARGA DE DATOS ---

    /**
     * Obtiene y carga todas las especialidades disponibles en el ComboBox.
     */
    private void cargarEspecialidades() {
        listaEspecialidades = especialidadController.obtenerTodasEspecialidades();
        comboBoxEspecialidades.removeAllItems();
        comboBoxEspecialidades.addItem("[Seleccione una especialidad]");
        for (Especialidad esp : listaEspecialidades) {
            comboBoxEspecialidades.addItem(esp.getNombre());
        }
    }

    /**
     * Carga en el ComboBox los médicos que corresponden a la especialidad seleccionada.
     * @param idEspecialidad El ID de la especialidad para filtrar los médicos.
     */
    private void cargarMedicos(int idEspecialidad) {
        listaMedicosActuales = medicoController.obtenerPorEspecialidad(idEspecialidad);
        comboBoxMedicos.removeAllItems();
        comboBoxMedicos.addItem("[Seleccione un médico]");
        for (Medico med : listaMedicosActuales) {
            comboBoxMedicos.addItem(med.getNombre() + " " + med.getApellido());
        }
    }
    
    /**
     * Muestra en la tabla los horarios disponibles para el médico seleccionado.
     * @param medico El médico del cual se mostrarán los horarios.
     */
    private void cargarHorarios(Medico medico) {
        List<DisponibilidadMedica> disponibilidades = medicoController.obtenerDisponibilidadesPorMedico(medico.getId());
        DefaultTableModel modelo = (DefaultTableModel) tablaFechaDispo.getModel(); 
        modelo.setRowCount(0); // Limpia la tabla antes de llenarla

        for (DisponibilidadMedica dispo : disponibilidades) {
            LocalTime horaActual = dispo.getHoraInicio();
            LocalTime horaFin = dispo.getHoraFin();
            // Itera de hora en hora y añade una fila por cada hora disponible
            while (horaActual.isBefore(horaFin)) {
                modelo.addRow(new Object[]{dispo.getDiaSemana().toString(), horaActual.toString()});
                horaActual = horaActual.plusHours(1);
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    // Generated using JFormDesigner Evaluation license - Issael
    private void initComponents() {
        jPanel1 = new JPanel();
        jPanel2 = new JPanel();
        favicon1 = new JLabel();
        comboBoxMedicos = new JComboBox<>();
        jLabel1 = new JLabel();
        jLabel2 = new JLabel();
        comboBoxEspecialidades = new JComboBox<>();
        jLabel3 = new JLabel();
        siguienteBtn = new JButton();
        jScrollPane1 = new JScrollPane();
        tablaFechaDispo = new JTable();
        jLabel6 = new JLabel();
        volverBtn = new JButton();
        jLabel4 = new JLabel();
        jLabel5 = new JLabel();
        jScrollPane3 = new JScrollPane();
        txtArea = new JTextArea();

        //======== this ========
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        var contentPane = getContentPane();

        //======== jPanel1 ========
        {
            jPanel1.setBackground(Color.white);
            jPanel1.setBorder(new javax.swing.border.CompoundBorder(new javax.swing.border.TitledBorder(new javax.swing.border
            .EmptyBorder(0,0,0,0), "JFor\u006dDesi\u0067ner \u0045valu\u0061tion",javax.swing.border.TitledBorder.CENTER,javax
            .swing.border.TitledBorder.BOTTOM,new java.awt.Font("Dia\u006cog",java.awt.Font.BOLD,
            12),java.awt.Color.red),jPanel1. getBorder()));jPanel1. addPropertyChangeListener(new java.beans
            .PropertyChangeListener(){@Override public void propertyChange(java.beans.PropertyChangeEvent e){if("bord\u0065r".equals(e.
            getPropertyName()))throw new RuntimeException();}});
            jPanel1.setLayout(null);

            //======== jPanel2 ========
            {
                jPanel2.setBackground(new Color(0x2d79c6));
                jPanel2.setForeground(Color.white);
                jPanel2.setLayout(null);

                //---- favicon1 ----
                favicon1.setFont(new Font("Tahoma", Font.BOLD, 18));
                favicon1.setForeground(Color.white);
                favicon1.setIcon(new ImageIcon(getClass().getResource("/View/imagenes/favicon.png")));
                favicon1.setText("CitaSmart");
                jPanel2.add(favicon1);
                favicon1.setBounds(20, 10, 130, favicon1.getPreferredSize().height);

                {
                    // compute preferred size
                    Dimension preferredSize = new Dimension();
                    for(int i = 0; i < jPanel2.getComponentCount(); i++) {
                        Rectangle bounds = jPanel2.getComponent(i).getBounds();
                        preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                        preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                    }
                    Insets insets = jPanel2.getInsets();
                    preferredSize.width += insets.right;
                    preferredSize.height += insets.bottom;
                    jPanel2.setMinimumSize(preferredSize);
                    jPanel2.setPreferredSize(preferredSize);
                }
            }
            jPanel1.add(jPanel2);
            jPanel2.setBounds(0, 0, 750, 50);

            //---- comboBoxMedicos ----
            comboBoxMedicos.setBackground(Color.white);
            comboBoxMedicos.setFont(new Font("Segoe UI", Font.PLAIN, 14));
            comboBoxMedicos.setForeground(Color.black);
            comboBoxMedicos.setModel(new DefaultComboBoxModel<>(new String[] {
                "[Selecciona una opci\u00f3n]",
                "Medico 1",
                "Medico 2",
                "Medico 3",
                "Medico 4",
                "Medico 5"
            }));
            comboBoxMedicos.addActionListener(e -> comboBoxMedicosActionPerformed(e));
            jPanel1.add(comboBoxMedicos);
            comboBoxMedicos.setBounds(70, 310, 240, 30);

            //---- jLabel1 ----
            jLabel1.setFont(new Font("Segoe UI", Font.BOLD, 14));
            jLabel1.setForeground(Color.black);
            jLabel1.setText("Seleccionar m\u00e9dico");
            jPanel1.add(jLabel1);
            jLabel1.setBounds(70, 280, 180, jLabel1.getPreferredSize().height);

            //---- jLabel2 ----
            jLabel2.setFont(new Font("Segoe UI", Font.BOLD, 20));
            jLabel2.setForeground(Color.black);
            jLabel2.setHorizontalAlignment(SwingConstants.CENTER);
            jLabel2.setText("RESERVAR CITA MEDICA");
            jPanel1.add(jLabel2);
            jLabel2.setBounds(0, 100, 750, 30);

            //---- comboBoxEspecialidades ----
            comboBoxEspecialidades.setBackground(Color.white);
            comboBoxEspecialidades.setFont(new Font("Segoe UI", Font.PLAIN, 14));
            comboBoxEspecialidades.setForeground(Color.black);
            comboBoxEspecialidades.setModel(new DefaultComboBoxModel<>(new String[] {
                "[Selecciona una opci\u00f3n]"
            }));
            comboBoxEspecialidades.addActionListener(e -> comboBoxEspecialidadesActionPerformed(e));
            jPanel1.add(comboBoxEspecialidades);
            comboBoxEspecialidades.setBounds(70, 220, 240, 30);

            //---- jLabel3 ----
            jLabel3.setFont(new Font("Segoe UI", Font.BOLD, 14));
            jLabel3.setForeground(Color.black);
            jLabel3.setText("Especialidad");
            jPanel1.add(jLabel3);
            jLabel3.setBounds(70, 190, 180, jLabel3.getPreferredSize().height);

            //---- siguienteBtn ----
            siguienteBtn.setBackground(new Color(0x006699));
            siguienteBtn.setFont(new Font("Segoe UI", Font.BOLD, 14));
            siguienteBtn.setForeground(Color.white);
            siguienteBtn.setText("Siguiente ->");
            siguienteBtn.setBorderPainted(false);
            siguienteBtn.addActionListener(e -> siguienteBtnActionPerformed(e));
            jPanel1.add(siguienteBtn);
            siguienteBtn.setBounds(500, 450, 140, 40);

            //======== jScrollPane1 ========
            {

                //---- tablaFechaDispo ----
                tablaFechaDispo.setBackground(Color.white);
                tablaFechaDispo.setFont(new Font("Segoe UI", Font.PLAIN, 14));
                tablaFechaDispo.setForeground(Color.black);
                tablaFechaDispo.setModel(new DefaultTableModel(
                    new Object[][] {
                        {null, null},
                        {null, null},
                        {null, null},
                        {null, null},
                        {null, null},
                        {null, null},
                    },
                    new String[] {
                        "D\u00eda", "Hora"
                    }
                ) {
                    boolean[] columnEditable = new boolean[] {
                        false, false
                    };
                    @Override
                    public boolean isCellEditable(int rowIndex, int columnIndex) {
                        return columnEditable[columnIndex];
                    }
                });
                jScrollPane1.setViewportView(tablaFechaDispo);
            }
            jPanel1.add(jScrollPane1);
            jScrollPane1.setBounds(370, 200, 300, 150);

            //---- jLabel6 ----
            jLabel6.setFont(new Font("Segoe UI", Font.BOLD, 14));
            jLabel6.setForeground(Color.black);
            jLabel6.setHorizontalAlignment(SwingConstants.CENTER);
            jLabel6.setText("Fechas disponibles");
            jPanel1.add(jLabel6);
            jLabel6.setBounds(370, 170, 300, jLabel6.getPreferredSize().height);

            //---- volverBtn ----
            volverBtn.setBackground(new Color(0x006699));
            volverBtn.setFont(new Font("Segoe UI", Font.BOLD, 14));
            volverBtn.setForeground(Color.white);
            volverBtn.setText("< Volver");
            volverBtn.setBorderPainted(false);
            volverBtn.addActionListener(e -> volverBtnActionPerformed(e));
            jPanel1.add(volverBtn);
            volverBtn.setBounds(20, 60, 130, 30);
            jPanel1.add(jLabel4);
            jLabel4.setBounds(new Rectangle(new Point(370, 350), jLabel4.getPreferredSize()));

            //---- jLabel5 ----
            jLabel5.setFont(new Font("Segoe UI", Font.PLAIN, 18));
            jLabel5.setForeground(Color.black);
            jLabel5.setText("Motivo de la Cita");
            jPanel1.add(jLabel5);
            jLabel5.setBounds(140, 400, 150, 30);

            //======== jScrollPane3 ========
            {

                //---- txtArea ----
                txtArea.setBackground(Color.white);
                txtArea.setColumns(20);
                txtArea.setFont(new Font("Segoe UI", Font.PLAIN, 14));
                txtArea.setForeground(Color.black);
                txtArea.setRows(5);
                txtArea.setToolTipText("Ingrese una descripci\u00f3n corta sobre sus s\u00edntomas");
                jScrollPane3.setViewportView(txtArea);
            }
            jPanel1.add(jScrollPane3);
            jScrollPane3.setBounds(70, 440, 300, 60);

            {
                // compute preferred size
                Dimension preferredSize = new Dimension();
                for(int i = 0; i < jPanel1.getComponentCount(); i++) {
                    Rectangle bounds = jPanel1.getComponent(i).getBounds();
                    preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                    preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                }
                Insets insets = jPanel1.getInsets();
                preferredSize.width += insets.right;
                preferredSize.height += insets.bottom;
                jPanel1.setMinimumSize(preferredSize);
                jPanel1.setPreferredSize(preferredSize);
            }
        }

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addComponent(jPanel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addComponent(jPanel1, GroupLayout.DEFAULT_SIZE, 570, Short.MAX_VALUE)
        );
        pack();
        setLocationRelativeTo(getOwner());
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Valida los datos seleccionados y navega a la pantalla de confirmación.
     * @param evt El evento de acción.
     */
    private void siguienteBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_siguienteBtnActionPerformed
        int especialidadIndex = comboBoxEspecialidades.getSelectedIndex();
        int medicoIndex = comboBoxMedicos.getSelectedIndex();
        int horarioFila = tablaFechaDispo.getSelectedRow();

        // Valida que todos los campos necesarios estén seleccionados
        if (especialidadIndex <= 0 || medicoIndex <= 0 || horarioFila == -1) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar una especialidad, un médico y un horario.", "Información incompleta", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String motivo = txtArea.getText().trim();
        if (motivo.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, ingrese un motivo para la cita.", "Campo requerido", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        // --- INICIO DE LA CORRECCIÓN ---
        // 1. Obtiene el objeto Medico (cuya especialidad puede estar incompleta)
        Medico medicoSeleccionado = listaMedicosActuales.get(medicoIndex - 1);
        
        // 2. Obtiene el objeto Especialidad COMPLETO (con ID y Nombre) del primer ComboBox
        Especialidad especialidadCompleta = listaEspecialidades.get(especialidadIndex - 1);
        
        // 3. Asigna la especialidad completa al médico para asegurar que no falten datos
        medicoSeleccionado.setEspecialidad(especialidadCompleta);
        // --- FIN DE LA CORRECCIÓN ---

        String diaStr = tablaFechaDispo.getValueAt(horarioFila, 0).toString();
        String horaStr = tablaFechaDispo.getValueAt(horarioFila, 1).toString();
        
        // Pasa a la siguiente ventana con el objeto Medico ya completo
        new ConfirmarCita(paciente, medicoSeleccionado, DiaSemana.valueOf(diaStr), LocalTime.parse(horaStr), motivo, this).setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_siguienteBtnActionPerformed

    /**
     * Vuelve a la pantalla de inicio del paciente.
     * @param evt El evento de acción.
     */
    private void volverBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_volverBtnActionPerformed
       new HomePaciente(paciente).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_volverBtnActionPerformed

    /**
     * Maneja el cambio de selección en el ComboBox de especialidades.
     * Carga los médicos correspondientes y limpia las selecciones posteriores.
     * @param evt El evento de acción.
     */
    private void comboBoxEspecialidadesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBoxEspecialidadesActionPerformed
        int index = comboBoxEspecialidades.getSelectedIndex();
        // El índice 0 es el texto "[Seleccione...]"
        if (index > 0) {
            Especialidad especialidadSeleccionada = listaEspecialidades.get(index - 1);
            cargarMedicos(especialidadSeleccionada.getId());
        } else {
            comboBoxMedicos.removeAllItems();
            comboBoxMedicos.addItem("[Seleccione una especialidad primero]");
            ((DefaultTableModel) tablaFechaDispo.getModel()).setRowCount(0); // Limpia la tabla de horarios
        }
    }//GEN-LAST:event_comboBoxEspecialidadesActionPerformed

    /**
     * Maneja el cambio de selección en el ComboBox de médicos.
     * Carga los horarios disponibles para el médico seleccionado.
     * @param evt El evento de acción.
     */
    private void comboBoxMedicosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBoxMedicosActionPerformed
        int index = comboBoxMedicos.getSelectedIndex();
        if (index > 0) {
            Medico medicoSeleccionado = listaMedicosActuales.get(index - 1);
            cargarHorarios(medicoSeleccionado);
        } else {
            // Limpia la tabla si se deselecciona el médico
            ((DefaultTableModel) tablaFechaDispo.getModel()).setRowCount(0);
        }
    }//GEN-LAST:event_comboBoxMedicosActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - Issael
    private JPanel jPanel1;
    private JPanel jPanel2;
    private JLabel favicon1;
    private JComboBox<String> comboBoxMedicos;
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JComboBox<String> comboBoxEspecialidades;
    private JLabel jLabel3;
    private JButton siguienteBtn;
    private JScrollPane jScrollPane1;
    private JTable tablaFechaDispo;
    private JLabel jLabel6;
    private JButton volverBtn;
    private JLabel jLabel4;
    private JLabel jLabel5;
    private JScrollPane jScrollPane3;
    private JTextArea txtArea;
    // End of variables declaration//GEN-END:variables
}
