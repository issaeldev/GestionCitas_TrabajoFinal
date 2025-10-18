package com.gestioncitas_trabajofinal.view.paciente;

import java.awt.*;
import javax.swing.*;
import javax.swing.GroupLayout;
import com.gestioncitas_trabajofinal.view.Login;
import com.gestioncitas_trabajofinal.controller.CitaController;
import com.gestioncitas_trabajofinal.model.Cita;
import com.gestioncitas_trabajofinal.model.EstadoCita;
import com.gestioncitas_trabajofinal.model.Paciente;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 * Ventana que muestra el historial de citas de un paciente.
 * Permite al paciente ver todas sus citas (pasadas y futuras) y cancelar
 * aquellas que aún están pendientes.
 * * @author lumic
 */
public class MisCitas extends javax.swing.JFrame {

    private final Paciente paciente;
    private final CitaController citaController;
    private List<Cita> listaDeCitas; // Almacenar la lista para acceder al ID

    /**
     * Constructor que inicializa la ventana y carga el historial de citas.
     * @param paciente El paciente que ha iniciado sesión.
     */
    public MisCitas(Paciente paciente) {
        this.paciente = paciente;
        this.citaController = new CitaController();
        initComponents();
        lblUsername.setText(paciente.getUsername());
        cargarCitasDelPaciente();
        setLocationRelativeTo(null);
    }
    
    /**
     * Obtiene las citas del paciente desde el controlador y las muestra en la tabla.
     */
    private void cargarCitasDelPaciente() {
        this.listaDeCitas = citaController.obtenerCitasPorPaciente(paciente.getId());
        
        DefaultTableModel modelo = (DefaultTableModel) tablaMisCitas.getModel();
        modelo.setRowCount(0); // Limpiar tabla antes de cargar nuevos datos

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

        for (Cita cita : listaDeCitas) {
            String fechaFormateada = cita.getFechaHora().format(formatter);
            String especialidad = cita.getMedico().getEspecialidad().getNombre();
            String medico = cita.getMedico().getNombre() + " " + cita.getMedico().getApellido();
            String estado = cita.getEstado().toString();
            
            modelo.addRow(new Object[]{fechaFormateada, especialidad, medico, estado});
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    // Generated using JFormDesigner Evaluation license - Issael
    private void initComponents() {
        jPanel1 = new JPanel();
        jPanel3 = new JPanel();
        inicioBtn = new JButton();
        perfilBtn = new JButton();
        citasBtn = new JButton();
        userIcon = new JLabel();
        lblUsername = new JLabel();
        jPanel4 = new JPanel();
        cerrarsesionBtn = new JButton();
        jPanel2 = new JPanel();
        favicon1 = new JLabel();
        jScrollPane1 = new JScrollPane();
        tablaMisCitas = new JTable();
        jLabel2 = new JLabel();
        cancelarCitabtn = new JButton();

        //======== this ========
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        var contentPane = getContentPane();

        //======== jPanel1 ========
        {
            jPanel1.setBackground(Color.white);
            jPanel1.setBorder (new javax. swing. border. CompoundBorder( new javax .swing .border .TitledBorder (new javax. swing. border. EmptyBorder
            ( 0, 0, 0, 0) , "JF\u006frmD\u0065sig\u006eer \u0045val\u0075ati\u006fn", javax. swing. border. TitledBorder. CENTER, javax. swing. border
            . TitledBorder. BOTTOM, new java .awt .Font ("Dia\u006cog" ,java .awt .Font .BOLD ,12 ), java. awt
            . Color. red) ,jPanel1. getBorder( )) ); jPanel1. addPropertyChangeListener (new java. beans. PropertyChangeListener( ){ @Override public void
            propertyChange (java .beans .PropertyChangeEvent e) {if ("\u0062ord\u0065r" .equals (e .getPropertyName () )) throw new RuntimeException( )
            ; }} );
            jPanel1.setLayout(null);

            //======== jPanel3 ========
            {
                jPanel3.setBackground(new Color(0x83b3e3));
                jPanel3.setLayout(null);

                //---- inicioBtn ----
                inicioBtn.setBackground(new Color(0x83b3e3));
                inicioBtn.setFont(new Font("Tahoma", Font.BOLD, 14));
                inicioBtn.setForeground(new Color(0x003366));
                inicioBtn.setText("INICIO");
                inicioBtn.setBorder(null);
                inicioBtn.addActionListener(e -> inicioBtnActionPerformed(e));
                jPanel3.add(inicioBtn);
                inicioBtn.setBounds(0, 170, 180, 28);

                //---- perfilBtn ----
                perfilBtn.setBackground(new Color(0x83b3e3));
                perfilBtn.setFont(new Font("Tahoma", Font.BOLD, 14));
                perfilBtn.setForeground(new Color(0x003366));
                perfilBtn.setText("MI PERFIL");
                perfilBtn.setBorder(null);
                perfilBtn.addActionListener(e -> perfilBtnActionPerformed(e));
                jPanel3.add(perfilBtn);
                perfilBtn.setBounds(0, 200, 180, 28);

                //---- citasBtn ----
                citasBtn.setBackground(Color.white);
                citasBtn.setFont(new Font("Tahoma", Font.BOLD, 14));
                citasBtn.setForeground(new Color(0x003366));
                citasBtn.setText("MIS CITAS");
                citasBtn.setBorder(null);
                citasBtn.addActionListener(e -> citasBtnActionPerformed(e));
                jPanel3.add(citasBtn);
                citasBtn.setBounds(0, 230, 180, 28);

                //---- userIcon ----
                userIcon.setHorizontalAlignment(SwingConstants.CENTER);
                userIcon.setIcon(new ImageIcon(getClass().getResource("/View/imagenes/user.png")));
                jPanel3.add(userIcon);
                userIcon.setBounds(0, 10, 180, userIcon.getPreferredSize().height);

                //---- lblUsername ----
                lblUsername.setFont(new Font("Tahoma", Font.BOLD, 14));
                lblUsername.setForeground(new Color(0x003366));
                lblUsername.setHorizontalAlignment(SwingConstants.CENTER);
                lblUsername.setText("Nombre de usuario");
                jPanel3.add(lblUsername);
                lblUsername.setBounds(0, 110, 180, 20);

                //======== jPanel4 ========
                {
                    jPanel4.setBackground(new Color(0xc3e0f2));
                    jPanel4.setLayout(null);

                    //---- cerrarsesionBtn ----
                    cerrarsesionBtn.setBackground(new Color(0xc3e0f2));
                    cerrarsesionBtn.setFont(new Font("Tahoma", Font.BOLD, 14));
                    cerrarsesionBtn.setForeground(new Color(0x003366));
                    cerrarsesionBtn.setText("CERRAR SESION");
                    cerrarsesionBtn.setBorder(null);
                    cerrarsesionBtn.addActionListener(e -> cerrarsesionBtnActionPerformed(e));
                    jPanel4.add(cerrarsesionBtn);
                    cerrarsesionBtn.setBounds(0, 10, 180, 28);

                    {
                        // compute preferred size
                        Dimension preferredSize = new Dimension();
                        for(int i = 0; i < jPanel4.getComponentCount(); i++) {
                            Rectangle bounds = jPanel4.getComponent(i).getBounds();
                            preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                            preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                        }
                        Insets insets = jPanel4.getInsets();
                        preferredSize.width += insets.right;
                        preferredSize.height += insets.bottom;
                        jPanel4.setMinimumSize(preferredSize);
                        jPanel4.setPreferredSize(preferredSize);
                    }
                }
                jPanel3.add(jPanel4);
                jPanel4.setBounds(0, 520, 180, 50);

                {
                    // compute preferred size
                    Dimension preferredSize = new Dimension();
                    for(int i = 0; i < jPanel3.getComponentCount(); i++) {
                        Rectangle bounds = jPanel3.getComponent(i).getBounds();
                        preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                        preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                    }
                    Insets insets = jPanel3.getInsets();
                    preferredSize.width += insets.right;
                    preferredSize.height += insets.bottom;
                    jPanel3.setMinimumSize(preferredSize);
                    jPanel3.setPreferredSize(preferredSize);
                }
            }
            jPanel1.add(jPanel3);
            jPanel3.setBounds(0, 0, 180, 570);

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
            jPanel2.setBounds(180, 0, 570, 50);

            //======== jScrollPane1 ========
            {
                jScrollPane1.setBackground(Color.white);
                jScrollPane1.setForeground(Color.white);

                //---- tablaMisCitas ----
                tablaMisCitas.setBackground(Color.white);
                tablaMisCitas.setForeground(Color.black);
                tablaMisCitas.setModel(new DefaultTableModel(
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
                    },
                    new String[] {
                        "Fecha", "Especialidad", "M\u00e9dico", "Estado"
                    }
                ));
                jScrollPane1.setViewportView(tablaMisCitas);
            }
            jPanel1.add(jScrollPane1);
            jScrollPane1.setBounds(210, 140, 510, 230);

            //---- jLabel2 ----
            jLabel2.setFont(new Font("Segoe UI", Font.BOLD, 20));
            jLabel2.setForeground(Color.black);
            jLabel2.setHorizontalAlignment(SwingConstants.CENTER);
            jLabel2.setText("MIS CITAS");
            jPanel1.add(jLabel2);
            jLabel2.setBounds(210, 100, 510, 30);

            //---- cancelarCitabtn ----
            cancelarCitabtn.setBackground(new Color(0x006699));
            cancelarCitabtn.setFont(new Font("Segoe UI", Font.BOLD, 14));
            cancelarCitabtn.setForeground(Color.white);
            cancelarCitabtn.setText("Cancelar cita");
            cancelarCitabtn.setBorderPainted(false);
            cancelarCitabtn.addActionListener(e -> cancelarCitabtnActionPerformed(e));
            jPanel1.add(cancelarCitabtn);
            cancelarCitabtn.setBounds(390, 410, 150, 40);

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
                .addComponent(jPanel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pack();
        setLocationRelativeTo(getOwner());
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Navega a la pantalla de inicio del paciente.
     * @param evt El evento de acción.
     */
    private void inicioBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inicioBtnActionPerformed
        new HomePaciente(paciente).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_inicioBtnActionPerformed

    /**
     * Navega al perfil del paciente.
     * @param evt El evento de acción.
     */
    private void perfilBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_perfilBtnActionPerformed
        new ProfilePaciente(paciente).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_perfilBtnActionPerformed

    private void citasBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_citasBtnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_citasBtnActionPerformed

    /**
     * Cierra la sesión y vuelve a la pantalla de Login.
     * @param evt El evento de acción.
     */
    private void cerrarsesionBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cerrarsesionBtnActionPerformed
        new Login().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_cerrarsesionBtnActionPerformed

    /**
     * Maneja el clic en el botón 'Cancelar cita'.
     * @param evt El evento de acción.
     */
    private void cancelarCitabtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelarCitabtnActionPerformed
        cancelarCitaSeleccionada();
    }//GEN-LAST:event_cancelarCitabtnActionPerformed

     /**
     * Lógica para cancelar una cita seleccionada en la tabla.
     * Verifica que se haya seleccionado una fila, confirma la acción y
     * llama al controlador para realizar la cancelación.
     */
    private void cancelarCitaSeleccionada() {
        int filaSeleccionada = tablaMisCitas.getSelectedRow();

        if (filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(this, "Por favor, seleccione una cita de la tabla para cancelar.", "Ninguna cita seleccionada", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Cita citaSeleccionada = listaDeCitas.get(filaSeleccionada);
        
        // Solo se pueden cancelar citas que están en espera
        if (citaSeleccionada.getEstado() != EstadoCita.EN_ESPERA) {
            JOptionPane.showMessageDialog(this, "Solo se pueden cancelar las citas que están 'EN ESPERA'.", "Acción no permitida", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int confirmacion = JOptionPane.showConfirmDialog(this, "¿Está seguro de que desea cancelar esta cita?", "Confirmar cancelación", JOptionPane.YES_NO_OPTION);
        
        if (confirmacion == JOptionPane.YES_OPTION) {
            citaController.cancelarCita(citaSeleccionada.getId());
            JOptionPane.showMessageDialog(this, "Cita cancelada correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            cargarCitasDelPaciente(); // Refresca la tabla
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - Issael
    private JPanel jPanel1;
    private JPanel jPanel3;
    private JButton inicioBtn;
    private JButton perfilBtn;
    private JButton citasBtn;
    private JLabel userIcon;
    private JLabel lblUsername;
    private JPanel jPanel4;
    private JButton cerrarsesionBtn;
    private JPanel jPanel2;
    private JLabel favicon1;
    private JScrollPane jScrollPane1;
    private JTable tablaMisCitas;
    private JLabel jLabel2;
    private JButton cancelarCitabtn;
    // End of variables declaration//GEN-END:variables
}
