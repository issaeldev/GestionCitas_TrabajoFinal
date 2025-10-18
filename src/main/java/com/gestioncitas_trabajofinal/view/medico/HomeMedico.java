package com.gestioncitas_trabajofinal.view.medico;

import java.awt.*;
import javax.swing.*;
import javax.swing.GroupLayout;
import com.gestioncitas_trabajofinal.view.Login;
import com.gestioncitas_trabajofinal.controller.CitaController;
import com.gestioncitas_trabajofinal.controller.DisponibilidadController;
import com.gestioncitas_trabajofinal.model.Cita;
import com.gestioncitas_trabajofinal.model.DiaSemana;
import com.gestioncitas_trabajofinal.model.DisponibilidadMedica;
import com.gestioncitas_trabajofinal.model.Medico;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.table.DefaultTableModel;

/**
 * Pantalla principal para el médico. Muestra un resumen de su disponibilidad
 * y las citas agendadas para el día actual.
 * @author lumic
 */
public class HomeMedico extends javax.swing.JFrame {
    
    private final Medico medico;
    private final DisponibilidadController disponibilidadController;
    private final CitaController citaController;
    
    /**
     * Constructor de la ventana principal del médico.
     * @param medico El objeto Medico que ha iniciado sesión.
     */
    public HomeMedico(Medico medico) {
        this.medico = medico;
        this.disponibilidadController = new DisponibilidadController();
        this.citaController = new CitaController();
        initComponents();
        configurarBienvenida();
        cargarDisponibilidadHoy();
        cargarCitasDeHoy();
        setLocationRelativeTo(null);
    }

    // --- MÉTODOS DE INICIALIZACIÓN Y CARGA DE DATOS ---

    /**
     * Configura los mensajes de bienvenida en la interfaz.
     */
    private void configurarBienvenida() {
        txtTitle.setText("Bienvenido Dr(a). " + medico.getNombre());
        nombreDoctortxt.setText(medico.getUsername());
    }
    
    /**
     * Carga y muestra la disponibilidad horaria del médico para el día actual.
     */
    private void cargarDisponibilidadHoy() {
        DiaSemana diaActual = convertirADiaSemana(LocalDate.now().getDayOfWeek());
        List<DisponibilidadMedica> disponibilidadesHoy = disponibilidadController
            .obtenerDisponibilidadesPorMedico(medico.getId())
            .stream()
            .filter(d -> d.getDiaSemana() == diaActual)
            .collect(Collectors.toList());

        if (disponibilidadesHoy.isEmpty()) {
            disponibilidadLbl.setText("Hoy no tiene disponibilidad registrada.");
        } else {
            StringBuilder sb = new StringBuilder("<html>Disponibilidad de hoy:<br>");
            for (DisponibilidadMedica d : disponibilidadesHoy) {
                sb.append("- ").append(d.getHoraInicio()).append(" a ").append(d.getHoraFin()).append("<br>");
            }
            sb.append("</html>");
            disponibilidadLbl.setText(sb.toString());
        }
    }

    /**
     * Carga en la tabla las citas programadas para el día de hoy.
     */
    private void cargarCitasDeHoy() {
        List<Cita> citasDelDia = citaController.obtenerCitasPorMedico(medico.getId())
            .stream()
            .filter(cita -> cita.getFechaHora().toLocalDate().equals(LocalDate.now()))
            .collect(Collectors.toList());

        DefaultTableModel modelo = (DefaultTableModel) tablaCitasHoy.getModel();
        modelo.setRowCount(0);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm 'hrs.'");
        for (Cita cita : citasDelDia) {
            String hora = cita.getFechaHora().format(formatter);
            String nombrePaciente = cita.getPaciente().getNombre() + " " + cita.getPaciente().getApellido();
            String estado = cita.getEstado().toString();
            modelo.addRow(new Object[]{hora, nombrePaciente, estado});
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    // Generated using JFormDesigner Evaluation license - Issael
    private void initComponents() {
        jPanel1 = new JPanel();
        disponibilidadLbl = new JLabel();
        jPanel2 = new JPanel();
        favicon1 = new JLabel();
        jPanel3 = new JPanel();
        inicioBtn = new JButton();
        perfilBtn = new JButton();
        userIcon = new JLabel();
        nombreDoctortxt = new JLabel();
        jPanel4 = new JPanel();
        cerrarsesionBtn = new JButton();
        registrarDispoBtn = new JButton();
        verCitasBtn = new JButton();
        jScrollPane1 = new JScrollPane();
        tablaCitasHoy = new JTable();
        txtTitle = new JLabel();
        title2 = new JLabel();
        title3 = new JLabel();

        //======== this ========
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        var contentPane = getContentPane();

        //======== jPanel1 ========
        {
            jPanel1.setBackground(Color.white);
            jPanel1.setBorder ( new javax . swing. border .CompoundBorder ( new javax . swing. border .TitledBorder ( new javax .
            swing. border .EmptyBorder ( 0, 0 ,0 , 0) ,  "JF\u006frm\u0044es\u0069gn\u0065r \u0045va\u006cua\u0074io\u006e" , javax. swing .border
            . TitledBorder. CENTER ,javax . swing. border .TitledBorder . BOTTOM, new java. awt .Font ( "D\u0069al\u006fg"
            , java .awt . Font. BOLD ,12 ) ,java . awt. Color .red ) ,jPanel1. getBorder
            () ) ); jPanel1. addPropertyChangeListener( new java. beans .PropertyChangeListener ( ){ @Override public void propertyChange (java
            . beans. PropertyChangeEvent e) { if( "\u0062or\u0064er" .equals ( e. getPropertyName () ) )throw new RuntimeException
            ( ) ;} } );
            jPanel1.setLayout(null);

            //---- disponibilidadLbl ----
            disponibilidadLbl.setFont(new Font("Tahoma", Font.BOLD, 16));
            disponibilidadLbl.setForeground(new Color(0x000099));
            disponibilidadLbl.setText("9:00 AM - 17:00 PM");
            jPanel1.add(disponibilidadLbl);
            disponibilidadLbl.setBounds(410, 130, 280, 60);

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

                //---- userIcon ----
                userIcon.setHorizontalAlignment(SwingConstants.CENTER);
                userIcon.setIcon(new ImageIcon(getClass().getResource("/View/imagenes/user.png")));
                jPanel3.add(userIcon);
                userIcon.setBounds(0, 10, 180, userIcon.getPreferredSize().height);

                //---- nombreDoctortxt ----
                nombreDoctortxt.setFont(new Font("Tahoma", Font.BOLD, 14));
                nombreDoctortxt.setForeground(new Color(0x003366));
                nombreDoctortxt.setHorizontalAlignment(SwingConstants.CENTER);
                nombreDoctortxt.setText("Nombre de usuario");
                jPanel3.add(nombreDoctortxt);
                nombreDoctortxt.setBounds(0, 110, 180, 20);

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

            //---- registrarDispoBtn ----
            registrarDispoBtn.setBackground(new Color(0x006699));
            registrarDispoBtn.setFont(new Font("Segoe UI", Font.BOLD, 14));
            registrarDispoBtn.setForeground(Color.white);
            registrarDispoBtn.setText("Registrar disponibilidad");
            registrarDispoBtn.setBorderPainted(false);
            registrarDispoBtn.addActionListener(e -> registrarDispoBtnActionPerformed(e));
            jPanel1.add(registrarDispoBtn);
            registrarDispoBtn.setBounds(210, 190, 200, 40);

            //---- verCitasBtn ----
            verCitasBtn.setBackground(new Color(0x006699));
            verCitasBtn.setFont(new Font("Segoe UI", Font.BOLD, 14));
            verCitasBtn.setForeground(Color.white);
            verCitasBtn.setText("Ver todas las citas");
            verCitasBtn.setBorderPainted(false);
            verCitasBtn.addActionListener(e -> verCitasBtnActionPerformed(e));
            jPanel1.add(verCitasBtn);
            verCitasBtn.setBounds(210, 420, 200, 40);

            //======== jScrollPane1 ========
            {

                //---- tablaCitasHoy ----
                tablaCitasHoy.setBackground(Color.white);
                tablaCitasHoy.setForeground(Color.black);
                tablaCitasHoy.setModel(new DefaultTableModel(
                    new Object[][] {
                        {null, null, null},
                        {null, null, null},
                        {null, null, null},
                        {null, null, null},
                    },
                    new String[] {
                        "Hora", "Nombre de paciente", "Estado"
                    }
                ) {
                    boolean[] columnEditable = new boolean[] {
                        false, false, false
                    };
                    @Override
                    public boolean isCellEditable(int rowIndex, int columnIndex) {
                        return columnEditable[columnIndex];
                    }
                });
                jScrollPane1.setViewportView(tablaCitasHoy);
            }
            jPanel1.add(jScrollPane1);
            jScrollPane1.setBounds(210, 290, 490, 110);

            //---- txtTitle ----
            txtTitle.setFont(new Font("Tahoma", Font.BOLD, 20));
            txtTitle.setForeground(Color.black);
            txtTitle.setText("BIENVENIDO(A) DR. [NOMBRE]");
            jPanel1.add(txtTitle);
            txtTitle.setBounds(new Rectangle(new Point(210, 90), txtTitle.getPreferredSize()));

            //---- title2 ----
            title2.setFont(new Font("Tahoma", Font.BOLD, 16));
            title2.setForeground(Color.black);
            title2.setText("Tus citas hoy");
            jPanel1.add(title2);
            title2.setBounds(new Rectangle(new Point(210, 260), title2.getPreferredSize()));

            //---- title3 ----
            title3.setFont(new Font("Tahoma", Font.BOLD, 16));
            title3.setForeground(Color.black);
            title3.setText("Tu disponibilidad hoy:");
            jPanel1.add(title3);
            title3.setBounds(210, 150, title3.getPreferredSize().width, 20);

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

    // --- MANEJADORES DE EVENTOS ---
    
    private void inicioBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inicioBtnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_inicioBtnActionPerformed

    /**
     * Navega a la pantalla de perfil del médico.
     * @param evt El evento de acción.
     */
    private void perfilBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_perfilBtnActionPerformed
        new ProfileMedico(medico).setVisible(true);
        this.dispose();
        
    }//GEN-LAST:event_perfilBtnActionPerformed

        /**
     * Cierra la sesión del médico y vuelve a la pantalla de Login.
     * @param evt El evento de acción.
     */
    private void cerrarsesionBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cerrarsesionBtnActionPerformed
        new Login().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_cerrarsesionBtnActionPerformed

        /**
     * Navega a la pantalla que muestra todas las citas agendadas.
     * @param evt El evento de acción.
     */
    private void verCitasBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_verCitasBtnActionPerformed
        new CitasAgendadas(medico).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_verCitasBtnActionPerformed

        /**
     * Navega a la pantalla para registrar o actualizar la disponibilidad horaria.
     * @param evt El evento de acción.
     */
    private void registrarDispoBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registrarDispoBtnActionPerformed
        new RegistrarDispo(medico).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_registrarDispoBtnActionPerformed
    
        // --- MÉTODOS PRIVADOS DE AYUDA ---
    
     /**
     * Convierte el enum estándar de Java DayOfWeek a nuestro enum personalizado DiaSemana.
     * @param dayOfWeek El día de la semana de Java Time.
     * @return El correspondiente enum DiaSemana.
     */
    private DiaSemana convertirADiaSemana(DayOfWeek dayOfWeek) {
        return switch (dayOfWeek) {
            case MONDAY -> DiaSemana.LUNES;
            case TUESDAY -> DiaSemana.MARTES;
            case WEDNESDAY -> DiaSemana.MIERCOLES;
            case THURSDAY -> DiaSemana.JUEVES;
            case FRIDAY -> DiaSemana.VIERNES;
            case SATURDAY -> DiaSemana.SABADO;
            case SUNDAY -> DiaSemana.DOMINGO;
        };
    }

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - Issael
    private JPanel jPanel1;
    private JLabel disponibilidadLbl;
    private JPanel jPanel2;
    private JLabel favicon1;
    private JPanel jPanel3;
    private JButton inicioBtn;
    private JButton perfilBtn;
    private JLabel userIcon;
    private JLabel nombreDoctortxt;
    private JPanel jPanel4;
    private JButton cerrarsesionBtn;
    private JButton registrarDispoBtn;
    private JButton verCitasBtn;
    private JScrollPane jScrollPane1;
    private JTable tablaCitasHoy;
    private JLabel txtTitle;
    private JLabel title2;
    private JLabel title3;
    // End of variables declaration//GEN-END:variables
}
