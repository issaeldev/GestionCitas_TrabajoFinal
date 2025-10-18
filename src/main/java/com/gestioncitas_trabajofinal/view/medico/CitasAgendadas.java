package com.gestioncitas_trabajofinal.view.medico;

import java.awt.*;
import javax.swing.*;
import javax.swing.GroupLayout;
import com.gestioncitas_trabajofinal.controller.CitaController;
import com.gestioncitas_trabajofinal.model.Cita;
import com.gestioncitas_trabajofinal.model.EstadoCita;
import com.gestioncitas_trabajofinal.model.Medico;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 * Ventana que muestra el listado completo de citas agendadas para un médico.
 * Permite al médico cambiar el estado de las citas (e.g., a REALIZADA o NO_ASISTIO).
 * @author lumic
 */
public class CitasAgendadas extends javax.swing.JFrame {

    private final Medico medico;
    private final CitaController citaController;
    private List<Cita> listaDeCitas;

    /**
     * Constructor que inicializa la ventana y carga las citas del médico.
     * @param medico El médico que ha iniciado sesión.
     */
    public CitasAgendadas(Medico medico) {
        this.medico = medico;
        this.citaController = new CitaController();
        initComponents();
        cargarCitasAgendadas();
        setLocationRelativeTo(null);
    }

    /**
     * Obtiene todas las citas del médico y las muestra en la tabla.
     */
    private void cargarCitasAgendadas() {
        this.listaDeCitas = citaController.obtenerCitasPorMedico(medico.getId());
        DefaultTableModel modelo = (DefaultTableModel) tablaCitasAgendadas.getModel();
        modelo.setRowCount(0);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

        for (Cita cita : listaDeCitas) {
            String fechaHora = cita.getFechaHora().format(formatter);
            String paciente = cita.getPaciente().getNombre() + " " + cita.getPaciente().getApellido();
            String estado = cita.getEstado().toString();

            modelo.addRow(new Object[]{fechaHora, paciente, estado});
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    // Generated using JFormDesigner Evaluation license - Issael
    private void initComponents() {
        jPanel1 = new JPanel();
        jPanel2 = new JPanel();
        favicon1 = new JLabel();
        jLabel2 = new JLabel();
        volverBtn = new JButton();
        jScrollPane2 = new JScrollPane();
        tablaCitasAgendadas = new JTable();
        btnActualizarEstado = new JButton();

        //======== this ========
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        var contentPane = getContentPane();

        //======== jPanel1 ========
        {
            jPanel1.setBackground(Color.white);
            jPanel1.setBorder ( new javax . swing. border .CompoundBorder ( new javax . swing. border .TitledBorder (
            new javax . swing. border .EmptyBorder ( 0, 0 ,0 , 0) ,  "JFor\u006dDesi\u0067ner \u0045valu\u0061tion"
            , javax. swing .border . TitledBorder. CENTER ,javax . swing. border .TitledBorder . BOTTOM
            , new java. awt .Font ( "Dia\u006cog", java .awt . Font. BOLD ,12 )
            ,java . awt. Color .red ) ,jPanel1. getBorder () ) ); jPanel1. addPropertyChangeListener(
            new java. beans .PropertyChangeListener ( ){ @Override public void propertyChange (java . beans. PropertyChangeEvent e
            ) { if( "bord\u0065r" .equals ( e. getPropertyName () ) )throw new RuntimeException( )
            ;} } );
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

            //---- jLabel2 ----
            jLabel2.setFont(new Font("Segoe UI", Font.BOLD, 20));
            jLabel2.setForeground(Color.black);
            jLabel2.setHorizontalAlignment(SwingConstants.CENTER);
            jLabel2.setText("CITAS AGENDADAS");
            jPanel1.add(jLabel2);
            jLabel2.setBounds(0, 100, 750, 30);

            //---- volverBtn ----
            volverBtn.setBackground(new Color(0x006699));
            volverBtn.setFont(new Font("Segoe UI", Font.BOLD, 14));
            volverBtn.setForeground(Color.white);
            volverBtn.setText("< Volver");
            volverBtn.setBorderPainted(false);
            volverBtn.addActionListener(e -> volverBtnActionPerformed(e));
            jPanel1.add(volverBtn);
            volverBtn.setBounds(20, 60, 130, 30);

            //======== jScrollPane2 ========
            {
                jScrollPane2.setBackground(Color.white);
                jScrollPane2.setForeground(Color.black);

                //---- tablaCitasAgendadas ----
                tablaCitasAgendadas.setBackground(Color.white);
                tablaCitasAgendadas.setForeground(Color.black);
                tablaCitasAgendadas.setModel(new DefaultTableModel(
                    new Object[][] {
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null},
                    },
                    new String[] {
                        "Fecha", "Hora", "Nombre del paciente", "Estado"
                    }
                ) {
                    boolean[] columnEditable = new boolean[] {
                        false, false, false, false
                    };
                    @Override
                    public boolean isCellEditable(int rowIndex, int columnIndex) {
                        return columnEditable[columnIndex];
                    }
                });
                jScrollPane2.setViewportView(tablaCitasAgendadas);
            }
            jPanel1.add(jScrollPane2);
            jScrollPane2.setBounds(40, 150, 670, 280);

            //---- btnActualizarEstado ----
            btnActualizarEstado.setBackground(new Color(0x006699));
            btnActualizarEstado.setFont(new Font("Segoe UI", Font.BOLD, 14));
            btnActualizarEstado.setForeground(Color.white);
            btnActualizarEstado.setText("Actualizar Estado");
            btnActualizarEstado.setToolTipText("");
            btnActualizarEstado.setBorderPainted(false);
            btnActualizarEstado.addActionListener(e -> btnActualizarEstadoActionPerformed(e));
            jPanel1.add(btnActualizarEstado);
            btnActualizarEstado.setBounds(292, 460, 190, 40);

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
     * Navega de vuelta a la pantalla de inicio del médico.
     * @param evt El evento de acción.
     */
    private void volverBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_volverBtnActionPerformed
       new HomeMedico(medico).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_volverBtnActionPerformed

    /**
     * Maneja el clic en el botón 'Actualizar Estado'.
     * @param evt El evento de acción.
     */
    private void btnActualizarEstadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarEstadoActionPerformed
         actualizarEstadoCitaSeleccionada();
    }//GEN-LAST:event_btnActualizarEstadoActionPerformed

/**
     * Permite al médico cambiar el estado de una cita seleccionada.
     * Muestra un diálogo de opciones para elegir el nuevo estado.
     */
    private void actualizarEstadoCitaSeleccionada() {
        int fila = tablaCitasAgendadas.getSelectedRow();

        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Por favor, seleccione una cita de la tabla.", "Ninguna cita seleccionada", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Cita citaSeleccionada = listaDeCitas.get(fila);

        // Opciones para el diálogo
        Object[] opciones = { EstadoCita.REALIZADA, EstadoCita.NO_ASISTIO };
        EstadoCita nuevoEstado = (EstadoCita) JOptionPane.showInputDialog(
            this,
            "Seleccione el nuevo estado para la cita:",
            "Actualizar Estado de Cita",
            JOptionPane.QUESTION_MESSAGE,
            null,
            opciones,
            opciones[0]
        );

        // Si el usuario seleccionó una opción (no cerró el diálogo)
        if (nuevoEstado != null) {
            citaController.actualizarEstadoCita(citaSeleccionada.getId(), nuevoEstado);
            JOptionPane.showMessageDialog(this, "Estado actualizado con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            cargarCitasAgendadas(); // Refresca la tabla para mostrar el cambio
        }
    }
    


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - Issael
    private JPanel jPanel1;
    private JPanel jPanel2;
    private JLabel favicon1;
    private JLabel jLabel2;
    private JButton volverBtn;
    private JScrollPane jScrollPane2;
    private JTable tablaCitasAgendadas;
    private JButton btnActualizarEstado;
    // End of variables declaration//GEN-END:variables
}
