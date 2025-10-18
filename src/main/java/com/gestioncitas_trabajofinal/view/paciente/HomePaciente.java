package com.gestioncitas_trabajofinal.view.paciente;

import java.awt.*;
import javax.swing.*;
import javax.swing.GroupLayout;
import javax.swing.border.*;
import com.gestioncitas_trabajofinal.model.Paciente;
import com.gestioncitas_trabajofinal.view.Login;
import com.gestioncitas_trabajofinal.controller.CitaController;
import com.gestioncitas_trabajofinal.model.Cita;
import java.time.format.DateTimeFormatter;

/**
 * Representa la pantalla de inicio para un usuario de tipo Paciente.
 * Muestra un saludo de bienvenida y la información de la próxima cita agendada.
 * @author lumic
 */
public class HomePaciente extends javax.swing.JFrame 
{

    private Paciente paciente;

    /**
     * Crea una nueva instancia de HomePaciente.
     * @param paciente El objeto Paciente que ha iniciado sesión.
     */
    public HomePaciente(Paciente paciente) {
        this.paciente = paciente;
        initComponents();
        txtNombre.setText("Bienvenid@ " + paciente.getNombre());
        usernameLbl.setText(paciente.getUsername());
        cargarProximaCita();
        setLocationRelativeTo(null);
    }
    
    /**
     * Carga y muestra los detalles de la próxima cita del paciente.
     * Si no hay citas próximas, muestra un mensaje indicándolo.
     */
    private void cargarProximaCita() 
    {
        CitaController citaController = new CitaController();
        Cita proximaCita = citaController.obtenerProximaCita(paciente.getId());

        if (proximaCita != null) {
            String nombreMedico = proximaCita.getMedico().getNombre() + " " + proximaCita.getMedico().getApellido();
            String especialidad = proximaCita.getMedico().getEspecialidad().getNombre();

            lblMedico.setText(nombreMedico);
            lblEspecialidad.setText(especialidad);
            
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
            String fechaHora = proximaCita.getFechaHora().format(formatter);
            lblFecha.setText(fechaHora);
        } else {
            lblEspecialidad.setText("No se han registrado citas,");
            lblMedico.setText("por favor");
            lblFecha.setText("reserve una cita.");
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    // Generated using JFormDesigner Evaluation license - Issael
    private void initComponents() {
        jPanel1 = new JPanel();
        txtNombre = new JLabel();
        jPanel2 = new JPanel();
        favicon1 = new JLabel();
        jPanel3 = new JPanel();
        inicioBtn = new JButton();
        perfilBtn = new JButton();
        citasBtn = new JButton();
        userIcon = new JLabel();
        usernameLbl = new JLabel();
        jPanel4 = new JPanel();
        cerrarsesionBtn = new JButton();
        jPanel5 = new JPanel();
        titleCita = new JLabel();
        especialidadLbl = new JLabel();
        medicoLbl = new JLabel();
        fechaLbl = new JLabel();
        jLabel1 = new JLabel();
        lblMedico = new JLabel();
        lblFecha = new JLabel();
        lblEspecialidad = new JLabel();
        reservarCitaBtn = new JButton();
        favicon = new JLabel();

        //======== this ========
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        var contentPane = getContentPane();

        //======== jPanel1 ========
        {
            jPanel1.setBackground(Color.white);
            jPanel1.setBorder ( new javax . swing. border .CompoundBorder ( new javax . swing. border .TitledBorder ( new
            javax . swing. border .EmptyBorder ( 0, 0 ,0 , 0) ,  "JF\u006frmDes\u0069gner \u0045valua\u0074ion" , javax
            . swing .border . TitledBorder. CENTER ,javax . swing. border .TitledBorder . BOTTOM, new java
            . awt .Font ( "D\u0069alog", java .awt . Font. BOLD ,12 ) ,java . awt
            . Color .red ) ,jPanel1. getBorder () ) ); jPanel1. addPropertyChangeListener( new java. beans .
            PropertyChangeListener ( ){ @Override public void propertyChange (java . beans. PropertyChangeEvent e) { if( "\u0062order" .
            equals ( e. getPropertyName () ) )throw new RuntimeException( ) ;} } );
            jPanel1.setLayout(null);

            //---- txtNombre ----
            txtNombre.setFont(new Font("Tahoma", Font.BOLD, 20));
            txtNombre.setForeground(Color.black);
            txtNombre.setText("BIENVENIDO(A) [NOMBRE]");
            jPanel1.add(txtNombre);
            txtNombre.setBounds(new Rectangle(new Point(210, 90), txtNombre.getPreferredSize()));

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

            //======== jPanel3 ========
            {
                jPanel3.setBackground(new Color(0x83b3e3));
                jPanel3.setLayout(null);

                //---- inicioBtn ----
                inicioBtn.setBackground(Color.white);
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
                citasBtn.setBackground(new Color(0x83b3e3));
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

                //---- usernameLbl ----
                usernameLbl.setFont(new Font("Tahoma", Font.BOLD, 14));
                usernameLbl.setForeground(new Color(0x003366));
                usernameLbl.setHorizontalAlignment(SwingConstants.CENTER);
                usernameLbl.setText("Nombre de usuario");
                jPanel3.add(usernameLbl);
                usernameLbl.setBounds(0, 110, 180, 20);

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

            //======== jPanel5 ========
            {
                jPanel5.setBackground(Color.white);
                jPanel5.setBorder(new LineBorder(new Color(0x003366), 2));
                jPanel5.setLayout(null);

                //---- titleCita ----
                titleCita.setFont(new Font("Tahoma", Font.BOLD, 14));
                titleCita.setForeground(Color.black);
                titleCita.setHorizontalAlignment(SwingConstants.CENTER);
                titleCita.setText("TU PR\u00d3XIMA CITA");
                jPanel5.add(titleCita);
                titleCita.setBounds(0, 10, 430, 20);

                //---- especialidadLbl ----
                especialidadLbl.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
                especialidadLbl.setForeground(Color.black);
                especialidadLbl.setText("Especialidad: ");
                jPanel5.add(especialidadLbl);
                especialidadLbl.setBounds(40, 50, 110, especialidadLbl.getPreferredSize().height);

                //---- medicoLbl ----
                medicoLbl.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
                medicoLbl.setForeground(Color.black);
                medicoLbl.setText("M\u00e9dico: ");
                jPanel5.add(medicoLbl);
                medicoLbl.setBounds(40, 70, 110, medicoLbl.getPreferredSize().height);

                //---- fechaLbl ----
                fechaLbl.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
                fechaLbl.setForeground(Color.black);
                fechaLbl.setText("Fecha: ");
                jPanel5.add(fechaLbl);
                fechaLbl.setBounds(40, 90, 110, fechaLbl.getPreferredSize().height);
                jPanel5.add(jLabel1);
                jLabel1.setBounds(new Rectangle(new Point(160, 50), jLabel1.getPreferredSize()));

                //---- lblMedico ----
                lblMedico.setFont(new Font("Segoe UI", Font.PLAIN, 14));
                lblMedico.setForeground(Color.black);
                lblMedico.setText("por favor");
                jPanel5.add(lblMedico);
                lblMedico.setBounds(160, 70, 240, lblMedico.getPreferredSize().height);

                //---- lblFecha ----
                lblFecha.setFont(new Font("Segoe UI", Font.PLAIN, 14));
                lblFecha.setForeground(Color.black);
                lblFecha.setText("reserve una cita.");
                jPanel5.add(lblFecha);
                lblFecha.setBounds(160, 90, 250, lblFecha.getPreferredSize().height);

                //---- lblEspecialidad ----
                lblEspecialidad.setFont(new Font("Segoe UI", Font.PLAIN, 14));
                lblEspecialidad.setForeground(Color.black);
                lblEspecialidad.setText("No se han registrado citas,");
                jPanel5.add(lblEspecialidad);
                lblEspecialidad.setBounds(160, 50, 240, lblEspecialidad.getPreferredSize().height);

                {
                    // compute preferred size
                    Dimension preferredSize = new Dimension();
                    for(int i = 0; i < jPanel5.getComponentCount(); i++) {
                        Rectangle bounds = jPanel5.getComponent(i).getBounds();
                        preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                        preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                    }
                    Insets insets = jPanel5.getInsets();
                    preferredSize.width += insets.right;
                    preferredSize.height += insets.bottom;
                    jPanel5.setMinimumSize(preferredSize);
                    jPanel5.setPreferredSize(preferredSize);
                }
            }
            jPanel1.add(jPanel5);
            jPanel5.setBounds(250, 150, 430, 140);

            //---- reservarCitaBtn ----
            reservarCitaBtn.setBackground(new Color(0x006699));
            reservarCitaBtn.setFont(new Font("Tahoma", Font.BOLD, 14));
            reservarCitaBtn.setForeground(Color.white);
            reservarCitaBtn.setText("Reservar una cita");
            reservarCitaBtn.setBorderPainted(false);
            reservarCitaBtn.addActionListener(e -> reservarCitaBtnActionPerformed(e));
            jPanel1.add(reservarCitaBtn);
            reservarCitaBtn.setBounds(380, 320, 170, 40);

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

        //---- favicon ----
        favicon.setFont(new Font("Tahoma", Font.BOLD, 18));
        favicon.setForeground(Color.black);
        favicon.setIcon(new ImageIcon(getClass().getResource("/View/imagenes/favicon.png")));
        favicon.setText("LOGO");
    }// </editor-fold>//GEN-END:initComponents

    private void perfilBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_perfilBtnActionPerformed
        new ProfilePaciente(paciente).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_perfilBtnActionPerformed

    private void cerrarsesionBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cerrarsesionBtnActionPerformed
        new Login().setVisible(true);
        this.dispose();
        
    }//GEN-LAST:event_cerrarsesionBtnActionPerformed

    private void citasBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_citasBtnActionPerformed
        new MisCitas(paciente).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_citasBtnActionPerformed

    private void inicioBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inicioBtnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_inicioBtnActionPerformed

    private void reservarCitaBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reservarCitaBtnActionPerformed
        new ReservarCita(paciente).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_reservarCitaBtnActionPerformed


    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - Issael
    private JPanel jPanel1;
    private JLabel txtNombre;
    private JPanel jPanel2;
    private JLabel favicon1;
    private JPanel jPanel3;
    private JButton inicioBtn;
    private JButton perfilBtn;
    private JButton citasBtn;
    private JLabel userIcon;
    private JLabel usernameLbl;
    private JPanel jPanel4;
    private JButton cerrarsesionBtn;
    private JPanel jPanel5;
    private JLabel titleCita;
    private JLabel especialidadLbl;
    private JLabel medicoLbl;
    private JLabel fechaLbl;
    private JLabel jLabel1;
    private JLabel lblMedico;
    private JLabel lblFecha;
    private JLabel lblEspecialidad;
    private JButton reservarCitaBtn;
    private JLabel favicon;
    // End of variables declaration//GEN-END:variables
}
