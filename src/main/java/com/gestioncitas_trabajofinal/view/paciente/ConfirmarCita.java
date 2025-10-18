package com.gestioncitas_trabajofinal.view.paciente;

import java.awt.*;
import javax.swing.*;
import javax.swing.GroupLayout;
import javax.swing.border.*;
import com.gestioncitas_trabajofinal.controller.CitaController;
import com.gestioncitas_trabajofinal.model.DiaSemana;
import com.gestioncitas_trabajofinal.model.Medico;
import com.gestioncitas_trabajofinal.model.Paciente;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.Locale;
import javax.swing.JOptionPane;

/**
 * Ventana que muestra un resumen de la cita seleccionada y pide al usuario
 * su confirmación final antes de registrarla en el sistema.
 * @author lumic
 */
public class ConfirmarCita extends javax.swing.JFrame {

    // --- ATRIBUTOS ---
    private final CitaController citaController;
    private final Paciente paciente;
    private final Medico medico;
    private final DiaSemana dia;
    private final LocalTime hora;
    private final String motivo;
    private final ReservarCita ventanaAnterior;
    
    /**
     * Constructor para la ventana de confirmación de cita.
     * @param paciente El paciente que reserva la cita.
     * @param medico El médico seleccionado (con su especialidad completa).
     * @param dia El día de la semana seleccionado.
     * @param hora La hora seleccionada.
     * @param motivo El motivo de la consulta.
     * @param reservarCita La ventana anterior, para poder volver a ella.
     */
    public ConfirmarCita(Paciente paciente, Medico medico, DiaSemana dia, LocalTime hora, String motivo, ReservarCita reservarCita) {
        this.citaController = new CitaController();
        this.paciente = paciente;
        this.medico = medico;
        this.dia = dia;
        this.hora = hora;
        this.motivo = motivo;
        this.ventanaAnterior = reservarCita;
        initComponents();
        rellenarCampos();
        setLocationRelativeTo(null);
    }

    // --- MÉTODOS DE INICIALIZACIÓN Y CARGA DE DATOS ---

    /**
     * Rellena los campos de la interfaz con la información de la cita a confirmar.
     * Formatea las fechas y horas para una mejor presentación al usuario.
     */
    private void rellenarCampos() {
        // Datos del Paciente
        nombreCompletotxt.setText(paciente.getNombre() + " " + paciente.getApellido());
        dnitxt.setText(paciente.getDni());
        usernametxt.setText(paciente.getUsername());
        
        // Datos de la Cita
        mediconombretxt.setText("Dr(a). " + medico.getNombre() + " " + medico.getApellido());
        especialidadtxt.setText(medico.getEspecialidad().getNombre());
        
        LocalDate fechaProxima = obtenerProximaFecha(dia);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd 'de' MMMM 'de' yyyy", new Locale("es", "ES"));
        String diaSemanaTexto = fechaProxima.getDayOfWeek().getDisplayName(TextStyle.FULL, new Locale("es", "ES"));
        
        diatxt.setText(diaSemanaTexto.substring(0, 1).toUpperCase() + diaSemanaTexto.substring(1) + ", " + fechaProxima.format(formatter));
        horatxt.setText(hora.format(DateTimeFormatter.ofPattern("HH:mm 'hrs.'")));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    // Generated using JFormDesigner Evaluation license - Issael
    private void initComponents() {
        jPanel1 = new JPanel();
        jPanel2 = new JPanel();
        favicon1 = new JLabel();
        editarCitabtn = new JButton();
        reservarCitaBtn = new JButton();
        jLabel6 = new JLabel();
        volverBtn = new JButton();
        jPanel3 = new JPanel();
        jLabel1 = new JLabel();
        jLabel3 = new JLabel();
        jLabel5 = new JLabel();
        jLabel7 = new JLabel();
        jLabel8 = new JLabel();
        jLabel9 = new JLabel();
        jSeparator1 = new JSeparator();
        jLabel10 = new JLabel();
        jLabel11 = new JLabel();
        usernametxt = new JLabel();
        nombreCompletotxt = new JLabel();
        dnitxt = new JLabel();
        horatxt = new JLabel();
        mediconombretxt = new JLabel();
        especialidadtxt = new JLabel();
        diatxt = new JLabel();

        //======== this ========
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        var contentPane = getContentPane();

        //======== jPanel1 ========
        {
            jPanel1.setBackground(Color.white);
            jPanel1.setBorder (new javax. swing. border. CompoundBorder( new javax .swing .border .TitledBorder (new javax. swing
            . border. EmptyBorder( 0, 0, 0, 0) , "JF\u006frmD\u0065sig\u006eer \u0045val\u0075ati\u006fn", javax. swing. border. TitledBorder
            . CENTER, javax. swing. border. TitledBorder. BOTTOM, new java .awt .Font ("Dia\u006cog" ,java .
            awt .Font .BOLD ,12 ), java. awt. Color. red) ,jPanel1. getBorder( )) )
            ; jPanel1. addPropertyChangeListener (new java. beans. PropertyChangeListener( ){ @Override public void propertyChange (java .beans .PropertyChangeEvent e
            ) {if ("\u0062ord\u0065r" .equals (e .getPropertyName () )) throw new RuntimeException( ); }} )
            ;
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

            //---- editarCitabtn ----
            editarCitabtn.setBackground(new Color(0xcc0000));
            editarCitabtn.setFont(new Font("Segoe UI", Font.BOLD, 14));
            editarCitabtn.setForeground(Color.white);
            editarCitabtn.setText("Editar");
            editarCitabtn.setBorderPainted(false);
            editarCitabtn.addActionListener(e -> editarCitabtnActionPerformed(e));
            jPanel1.add(editarCitabtn);
            editarCitabtn.setBounds(210, 490, 140, 40);

            //---- reservarCitaBtn ----
            reservarCitaBtn.setBackground(new Color(0x006699));
            reservarCitaBtn.setFont(new Font("Segoe UI", Font.BOLD, 14));
            reservarCitaBtn.setForeground(Color.white);
            reservarCitaBtn.setText("Confirmar cita");
            reservarCitaBtn.setBorderPainted(false);
            reservarCitaBtn.addActionListener(e -> reservarCitaBtnActionPerformed(e));
            jPanel1.add(reservarCitaBtn);
            reservarCitaBtn.setBounds(400, 490, 140, 40);

            //---- jLabel6 ----
            jLabel6.setFont(new Font("Segoe UI", Font.BOLD, 20));
            jLabel6.setForeground(Color.black);
            jLabel6.setHorizontalAlignment(SwingConstants.CENTER);
            jLabel6.setText("Resumen de cita");
            jPanel1.add(jLabel6);
            jLabel6.setBounds(0, 100, 750, jLabel6.getPreferredSize().height);

            //---- volverBtn ----
            volverBtn.setBackground(new Color(0x006699));
            volverBtn.setFont(new Font("Segoe UI", Font.BOLD, 14));
            volverBtn.setForeground(Color.white);
            volverBtn.setText("< Volver al Incio");
            volverBtn.setBorderPainted(false);
            volverBtn.addActionListener(e -> volverBtnActionPerformed(e));
            jPanel1.add(volverBtn);
            volverBtn.setBounds(20, 60, 180, 30);

            //======== jPanel3 ========
            {
                jPanel3.setBackground(Color.white);
                jPanel3.setBorder(new LineBorder(new Color(0x0066cc), 2, true));
                jPanel3.setLayout(null);

                //---- jLabel1 ----
                jLabel1.setFont(new Font("Tahoma", Font.BOLD, 15));
                jLabel1.setForeground(Color.black);
                jLabel1.setText("Paciente:");
                jPanel3.add(jLabel1);
                jLabel1.setBounds(20, 30, 150, jLabel1.getPreferredSize().height);

                //---- jLabel3 ----
                jLabel3.setFont(new Font("Tahoma", Font.BOLD, 15));
                jLabel3.setForeground(Color.black);
                jLabel3.setHorizontalAlignment(SwingConstants.CENTER);
                jLabel3.setText("Detalles de la cita");
                jPanel3.add(jLabel3);
                jLabel3.setBounds(20, 140, 390, jLabel3.getPreferredSize().height);

                //---- jLabel5 ----
                jLabel5.setFont(new Font("Tahoma", Font.BOLD, 15));
                jLabel5.setForeground(Color.black);
                jLabel5.setText("DNI:");
                jPanel3.add(jLabel5);
                jLabel5.setBounds(20, 60, 180, jLabel5.getPreferredSize().height);

                //---- jLabel7 ----
                jLabel7.setFont(new Font("Tahoma", Font.BOLD, 15));
                jLabel7.setForeground(Color.black);
                jLabel7.setText("Correo electr\u00f3nico:");
                jPanel3.add(jLabel7);
                jLabel7.setBounds(20, 90, 173, jLabel7.getPreferredSize().height);

                //---- jLabel8 ----
                jLabel8.setFont(new Font("Tahoma", Font.BOLD, 15));
                jLabel8.setForeground(Color.black);
                jLabel8.setText("Especialidad:");
                jPanel3.add(jLabel8);
                jLabel8.setBounds(20, 200, 130, jLabel8.getPreferredSize().height);

                //---- jLabel9 ----
                jLabel9.setFont(new Font("Tahoma", Font.BOLD, 15));
                jLabel9.setForeground(Color.black);
                jLabel9.setText("M\u00e9dico:");
                jPanel3.add(jLabel9);
                jLabel9.setBounds(20, 170, 100, jLabel9.getPreferredSize().height);
                jPanel3.add(jSeparator1);
                jSeparator1.setBounds(20, 120, 390, 10);

                //---- jLabel10 ----
                jLabel10.setFont(new Font("Tahoma", Font.BOLD, 15));
                jLabel10.setForeground(Color.black);
                jLabel10.setText("Fecha:");
                jPanel3.add(jLabel10);
                jLabel10.setBounds(20, 230, 70, jLabel10.getPreferredSize().height);

                //---- jLabel11 ----
                jLabel11.setFont(new Font("Tahoma", Font.BOLD, 15));
                jLabel11.setForeground(Color.black);
                jLabel11.setText("Hora:");
                jPanel3.add(jLabel11);
                jLabel11.setBounds(20, 260, 70, jLabel11.getPreferredSize().height);

                //---- usernametxt ----
                usernametxt.setFont(new Font("Segoe UI", Font.PLAIN, 14));
                usernametxt.setForeground(Color.black);
                usernametxt.setText("Default");
                jPanel3.add(usernametxt);
                usernametxt.setBounds(170, 90, 240, usernametxt.getPreferredSize().height);

                //---- nombreCompletotxt ----
                nombreCompletotxt.setFont(new Font("Segoe UI", Font.PLAIN, 14));
                nombreCompletotxt.setForeground(Color.black);
                nombreCompletotxt.setText("Default");
                jPanel3.add(nombreCompletotxt);
                nombreCompletotxt.setBounds(170, 30, 250, nombreCompletotxt.getPreferredSize().height);

                //---- dnitxt ----
                dnitxt.setFont(new Font("Segoe UI", Font.PLAIN, 14));
                dnitxt.setForeground(Color.black);
                dnitxt.setText("Default");
                jPanel3.add(dnitxt);
                dnitxt.setBounds(170, 60, 250, dnitxt.getPreferredSize().height);

                //---- horatxt ----
                horatxt.setFont(new Font("Segoe UI", Font.PLAIN, 14));
                horatxt.setForeground(Color.black);
                horatxt.setText("Default");
                jPanel3.add(horatxt);
                horatxt.setBounds(140, 260, 270, horatxt.getPreferredSize().height);

                //---- mediconombretxt ----
                mediconombretxt.setFont(new Font("Segoe UI", Font.PLAIN, 14));
                mediconombretxt.setForeground(Color.black);
                mediconombretxt.setText("Default");
                jPanel3.add(mediconombretxt);
                mediconombretxt.setBounds(140, 170, 270, mediconombretxt.getPreferredSize().height);

                //---- especialidadtxt ----
                especialidadtxt.setFont(new Font("Segoe UI", Font.PLAIN, 14));
                especialidadtxt.setForeground(Color.black);
                especialidadtxt.setText("Default");
                jPanel3.add(especialidadtxt);
                especialidadtxt.setBounds(140, 200, 270, especialidadtxt.getPreferredSize().height);

                //---- diatxt ----
                diatxt.setFont(new Font("Segoe UI", Font.PLAIN, 14));
                diatxt.setForeground(Color.black);
                diatxt.setText("Default");
                jPanel3.add(diatxt);
                diatxt.setBounds(140, 230, 270, diatxt.getPreferredSize().height);

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
            jPanel3.setBounds(160, 150, 430, 310);

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
     * Maneja el clic en 'Confirmar cita'. Intenta registrar la cita y
     * navega a la pantalla de inicio del paciente si tiene éxito.
     * @param evt El evento de acción.
     */
    private void reservarCitaBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reservarCitaBtnActionPerformed
        boolean exito = citaController.registrarCita(paciente, medico, dia, hora, motivo);

        if (exito) {
            JOptionPane.showMessageDialog(this, "¡Cita registrada exitosamente!", "Confirmación", JOptionPane.INFORMATION_MESSAGE);
            new HomePaciente(paciente).setVisible(true);
            ventanaAnterior.dispose();
            this.dispose();
        } else {
            JOptionPane.showMessageDialog(this, "El horario seleccionado ya no está disponible. Por favor, intente con otro.", "Error de reserva", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_reservarCitaBtnActionPerformed

    /**
     * Vuelve a la pantalla de inicio del paciente, cancelando el proceso de reserva.
     * @param evt El evento de acción.
     */
    private void volverBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_volverBtnActionPerformed
        new HomePaciente(paciente).setVisible(true);
        ventanaAnterior.dispose();
        this.dispose();
    }//GEN-LAST:event_volverBtnActionPerformed

    /**
     * Vuelve a la pantalla anterior (ReservarCita) para modificar la selección.
     * @param evt El evento de acción.
     */
    private void editarCitabtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editarCitabtnActionPerformed
        ventanaAnterior.setVisible(true);
        this.dispose();

    }//GEN-LAST:event_editarCitabtnActionPerformed

    // --- MÉTODOS PRIVADOS DE AYUDA ---

    /**
     * Calcula la fecha exacta de la cita basándose en el día de la semana seleccionado.
     * @param dia El día de la semana (LUNES, MARTES, etc.).
     * @return La próxima fecha que corresponde a ese día de la semana.
     */
    private LocalDate obtenerProximaFecha(DiaSemana dia) {
        DayOfWeek targetDay = convertirADayOfWeek(dia);
        LocalDate today = LocalDate.now();
        int daysUntil = (targetDay.getValue() - today.getDayOfWeek().getValue() + 7) % 7;
        daysUntil = (daysUntil == 0) ? 7 : daysUntil;
        return today.plusDays(daysUntil);
    }

    /**
     * Convierte el enum personalizado DiaSemana al enum estándar de Java DayOfWeek.
     * @param dia El enum personalizado.
     * @return El enum estándar DayOfWeek.
     */
    private DayOfWeek convertirADayOfWeek(DiaSemana dia) {
        return switch (dia) {
            case LUNES -> DayOfWeek.MONDAY;
            case MARTES -> DayOfWeek.TUESDAY;
            case MIERCOLES -> DayOfWeek.WEDNESDAY;
            case JUEVES -> DayOfWeek.THURSDAY;
            case VIERNES -> DayOfWeek.FRIDAY;
            case SABADO -> DayOfWeek.SATURDAY;
            case DOMINGO -> DayOfWeek.SUNDAY;
        };
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - Issael
    private JPanel jPanel1;
    private JPanel jPanel2;
    private JLabel favicon1;
    private JButton editarCitabtn;
    private JButton reservarCitaBtn;
    private JLabel jLabel6;
    private JButton volverBtn;
    private JPanel jPanel3;
    private JLabel jLabel1;
    private JLabel jLabel3;
    private JLabel jLabel5;
    private JLabel jLabel7;
    private JLabel jLabel8;
    private JLabel jLabel9;
    private JSeparator jSeparator1;
    private JLabel jLabel10;
    private JLabel jLabel11;
    private JLabel usernametxt;
    private JLabel nombreCompletotxt;
    private JLabel dnitxt;
    private JLabel horatxt;
    private JLabel mediconombretxt;
    private JLabel especialidadtxt;
    private JLabel diatxt;
    // End of variables declaration//GEN-END:variables
}