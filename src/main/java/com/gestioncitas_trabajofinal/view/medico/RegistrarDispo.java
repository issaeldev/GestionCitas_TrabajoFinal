package com.gestioncitas_trabajofinal.view.medico;

import java.awt.*;
import javax.swing.*;
import javax.swing.GroupLayout;
import com.gestioncitas_trabajofinal.controller.DisponibilidadController;
import com.gestioncitas_trabajofinal.model.DiaSemana;
import com.gestioncitas_trabajofinal.model.DisponibilidadMedica;
import com.gestioncitas_trabajofinal.model.Medico;
import java.awt.Color;
import java.awt.Component;
import java.awt.Point;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 * Ventana que permite a un médico registrar y visualizar su horario de
 * disponibilidad semanal.
 * @author lumic
 */
public class RegistrarDispo extends javax.swing.JFrame {

    private final DisponibilidadController controller;
    private final Medico medicoActual;
    private final Set<Point> celdasDisponibles = new HashSet<>();

    /**
     * Constructor de la ventana.
     * @param medico El médico que registrará su disponibilidad.
     */
    public RegistrarDispo(Medico medico) {
        this.medicoActual = medico;
        this.controller = new DisponibilidadController();
        initComponents();
        configurarTablaDisponibilidad();
        cargarDisponibilidadExistente();
        setLocationRelativeTo(null);
    }
    
    // --- MÉTODOS DE INICIALIZACIÓN Y CARGA DE DATOS ---

    /**
     * Carga y visualiza en la tabla la disponibilidad previamente registrada del médico.
     */
    private void cargarDisponibilidadExistente() {
        List<DisponibilidadMedica> existentes = controller.obtenerDisponibilidadesPorMedico(medicoActual.getId());
        for (DisponibilidadMedica d : existentes) {
            int columna = d.getDiaSemana().ordinal() + 1; // +1 porque la columna 0 es la hora
            int horaInicio = d.getHoraInicio().getHour();
            int horaFin = d.getHoraFin().getHour();
            marcarDisponibilidadEnTabla(columna, horaInicio, horaFin, false);
        }
    }
    
    /**
     * Configura un renderizador de celdas personalizado para pintar el fondo de la
     * tabla según la disponibilidad.
     */
    private void configurarTablaDisponibilidad() {
        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                if (column > 0) { // No pintar la columna de la hora
                    c.setBackground(celdasDisponibles.contains(new Point(row, column)) ? Color.GREEN : Color.WHITE);
                }
                return c;
            }
        };

        for (int col = 1; col < tablaSemana.getColumnCount(); col++) {
            tablaSemana.getColumnModel().getColumn(col).setCellRenderer(renderer);
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
        btnGuardarDispo = new JButton();
        jLabel6 = new JLabel();
        regresarHomebtn = new JButton();
        jLabel1 = new JLabel();
        jLabel3 = new JLabel();
        jLabel4 = new JLabel();
        jLabel5 = new JLabel();
        jLabel7 = new JLabel();
        jLabel8 = new JLabel();
        jLabel9 = new JLabel();
        comboBoxLunes_fin = new JComboBox<>();
        comboBoxLunes_inicio = new JComboBox<>();
        comboBoxMartes_inicio = new JComboBox<>();
        comboBoxMartes_fin = new JComboBox<>();
        comboBoxJueves_fin = new JComboBox<>();
        comboBoxJueves_inicio = new JComboBox<>();
        comboBoxMiercoles_inicio = new JComboBox<>();
        comboBoxMiercoles_fin = new JComboBox<>();
        comboBoxDomingo_fin = new JComboBox<>();
        comboBoxDomingo_inicio = new JComboBox<>();
        comboBoxSabado_inicio = new JComboBox<>();
        comboBoxViernes_inicio = new JComboBox<>();
        comboBoxViernes_fin = new JComboBox<>();
        comboBoxSabado_fin = new JComboBox<>();
        btnAgregarMartes = new JButton();
        btnAgregarLunes = new JButton();
        btnAgregarMiercoles = new JButton();
        btnAgregarJueves = new JButton();
        btnAgregarDomingo = new JButton();
        btnAgregarSabado = new JButton();
        btnAgregarViernes = new JButton();
        jScrollPane1 = new JScrollPane();
        tablaSemana = new JTable();
        btnLimpiarTabla = new JButton();

        //======== this ========
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        var contentPane = getContentPane();

        //======== jPanel1 ========
        {
            jPanel1.setBackground(Color.white);
            jPanel1.setBorder (new javax. swing. border. CompoundBorder( new javax .swing .border .TitledBorder (new javax. swing.
            border. EmptyBorder( 0, 0, 0, 0) , "JFor\u006dDesi\u0067ner \u0045valu\u0061tion", javax. swing. border. TitledBorder. CENTER
            , javax. swing. border. TitledBorder. BOTTOM, new java .awt .Font ("Dia\u006cog" ,java .awt .Font
            .BOLD ,12 ), java. awt. Color. red) ,jPanel1. getBorder( )) ); jPanel1. addPropertyChangeListener (
            new java. beans. PropertyChangeListener( ){ @Override public void propertyChange (java .beans .PropertyChangeEvent e) {if ("bord\u0065r"
            .equals (e .getPropertyName () )) throw new RuntimeException( ); }} );
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
            jLabel2.setText("REGISTRAR DISPONIBILIDAD");
            jPanel1.add(jLabel2);
            jLabel2.setBounds(0, 100, 750, 30);

            //---- btnGuardarDispo ----
            btnGuardarDispo.setBackground(new Color(0x006699));
            btnGuardarDispo.setFont(new Font("Segoe UI", Font.BOLD, 14));
            btnGuardarDispo.setForeground(Color.white);
            btnGuardarDispo.setText("Guardar disponibilidad");
            btnGuardarDispo.setBorderPainted(false);
            btnGuardarDispo.addActionListener(e -> btnGuardarDispoActionPerformed(e));
            jPanel1.add(btnGuardarDispo);
            btnGuardarDispo.setBounds(270, 470, 200, 40);

            //---- jLabel6 ----
            jLabel6.setFont(new Font("Segoe UI", Font.BOLD, 14));
            jLabel6.setForeground(Color.black);
            jLabel6.setText("Configura tus horarios disponibles");
            jPanel1.add(jLabel6);
            jLabel6.setBounds(60, 160, 240, jLabel6.getPreferredSize().height);

            //---- regresarHomebtn ----
            regresarHomebtn.setBackground(new Color(0x006699));
            regresarHomebtn.setFont(new Font("Segoe UI", Font.BOLD, 14));
            regresarHomebtn.setForeground(Color.white);
            regresarHomebtn.setText("< Volver");
            regresarHomebtn.setBorderPainted(false);
            regresarHomebtn.addActionListener(e -> regresarHomebtnActionPerformed(e));
            jPanel1.add(regresarHomebtn);
            regresarHomebtn.setBounds(20, 60, 130, 30);

            //---- jLabel1 ----
            jLabel1.setFont(new Font("Tahoma", Font.PLAIN, 14));
            jLabel1.setForeground(Color.black);
            jLabel1.setText("Domingo");
            jPanel1.add(jLabel1);
            jLabel1.setBounds(390, 280, 60, jLabel1.getPreferredSize().height);

            //---- jLabel3 ----
            jLabel3.setFont(new Font("Tahoma", Font.PLAIN, 14));
            jLabel3.setForeground(Color.black);
            jLabel3.setText("Lunes");
            jPanel1.add(jLabel3);
            jLabel3.setBounds(60, 200, 60, 20);

            //---- jLabel4 ----
            jLabel4.setFont(new Font("Tahoma", Font.PLAIN, 14));
            jLabel4.setForeground(Color.black);
            jLabel4.setText("Martes");
            jPanel1.add(jLabel4);
            jLabel4.setBounds(60, 240, 60, jLabel4.getPreferredSize().height);

            //---- jLabel5 ----
            jLabel5.setFont(new Font("Tahoma", Font.PLAIN, 14));
            jLabel5.setForeground(Color.black);
            jLabel5.setText("Mi\u00e9rcoles");
            jPanel1.add(jLabel5);
            jLabel5.setBounds(60, 280, 60, jLabel5.getPreferredSize().height);

            //---- jLabel7 ----
            jLabel7.setFont(new Font("Tahoma", Font.PLAIN, 14));
            jLabel7.setForeground(Color.black);
            jLabel7.setText("Jueves");
            jPanel1.add(jLabel7);
            jLabel7.setBounds(60, 320, 60, jLabel7.getPreferredSize().height);

            //---- jLabel8 ----
            jLabel8.setFont(new Font("Tahoma", Font.PLAIN, 14));
            jLabel8.setForeground(Color.black);
            jLabel8.setText("Viernes");
            jPanel1.add(jLabel8);
            jLabel8.setBounds(390, 200, 60, jLabel8.getPreferredSize().height);

            //---- jLabel9 ----
            jLabel9.setFont(new Font("Tahoma", Font.PLAIN, 14));
            jLabel9.setForeground(Color.black);
            jLabel9.setText("S\u00e1bado");
            jPanel1.add(jLabel9);
            jLabel9.setBounds(390, 240, 60, jLabel9.getPreferredSize().height);

            //---- comboBoxLunes_fin ----
            comboBoxLunes_fin.setBackground(Color.white);
            comboBoxLunes_fin.setForeground(Color.black);
            comboBoxLunes_fin.setModel(new DefaultComboBoxModel<>(new String[] {
                "6:00",
                "7:00",
                "8:00",
                "9:00",
                "10:00",
                "11:00",
                "12:00",
                "13:00",
                "14:00",
                "15:00",
                "16:00",
                "17:00",
                "18:00",
                "19:00",
                "20:00"
            }));
            jPanel1.add(comboBoxLunes_fin);
            comboBoxLunes_fin.setBounds(220, 200, 70, comboBoxLunes_fin.getPreferredSize().height);

            //---- comboBoxLunes_inicio ----
            comboBoxLunes_inicio.setBackground(Color.white);
            comboBoxLunes_inicio.setForeground(Color.black);
            comboBoxLunes_inicio.setModel(new DefaultComboBoxModel<>(new String[] {
                "6:00",
                "7:00",
                "8:00",
                "9:00",
                "10:00",
                "11:00",
                "12:00",
                "13:00",
                "14:00",
                "15:00",
                "16:00",
                "17:00",
                "18:00",
                "19:00",
                "20:00"
            }));
            comboBoxLunes_inicio.addActionListener(e -> comboBoxLunes_inicioActionPerformed(e));
            jPanel1.add(comboBoxLunes_inicio);
            comboBoxLunes_inicio.setBounds(130, 200, 70, comboBoxLunes_inicio.getPreferredSize().height);

            //---- comboBoxMartes_inicio ----
            comboBoxMartes_inicio.setBackground(Color.white);
            comboBoxMartes_inicio.setForeground(Color.black);
            comboBoxMartes_inicio.setModel(new DefaultComboBoxModel<>(new String[] {
                "6:00",
                "7:00",
                "8:00",
                "9:00",
                "10:00",
                "11:00",
                "12:00",
                "13:00",
                "14:00",
                "15:00",
                "16:00",
                "17:00",
                "18:00",
                "19:00",
                "20:00"
            }));
            jPanel1.add(comboBoxMartes_inicio);
            comboBoxMartes_inicio.setBounds(130, 240, 70, comboBoxMartes_inicio.getPreferredSize().height);

            //---- comboBoxMartes_fin ----
            comboBoxMartes_fin.setBackground(Color.white);
            comboBoxMartes_fin.setForeground(Color.black);
            comboBoxMartes_fin.setModel(new DefaultComboBoxModel<>(new String[] {
                "6:00",
                "7:00",
                "8:00",
                "9:00",
                "10:00",
                "11:00",
                "12:00",
                "13:00",
                "14:00",
                "15:00",
                "16:00",
                "17:00",
                "18:00",
                "19:00",
                "20:00"
            }));
            jPanel1.add(comboBoxMartes_fin);
            comboBoxMartes_fin.setBounds(220, 240, 70, comboBoxMartes_fin.getPreferredSize().height);

            //---- comboBoxJueves_fin ----
            comboBoxJueves_fin.setBackground(Color.white);
            comboBoxJueves_fin.setForeground(Color.black);
            comboBoxJueves_fin.setModel(new DefaultComboBoxModel<>(new String[] {
                "6:00",
                "7:00",
                "8:00",
                "9:00",
                "10:00",
                "11:00",
                "12:00",
                "13:00",
                "14:00",
                "15:00",
                "16:00",
                "17:00",
                "18:00",
                "19:00",
                "20:00"
            }));
            comboBoxJueves_fin.addActionListener(e -> comboBoxJueves_finActionPerformed(e));
            jPanel1.add(comboBoxJueves_fin);
            comboBoxJueves_fin.setBounds(220, 320, 70, comboBoxJueves_fin.getPreferredSize().height);

            //---- comboBoxJueves_inicio ----
            comboBoxJueves_inicio.setBackground(Color.white);
            comboBoxJueves_inicio.setForeground(Color.black);
            comboBoxJueves_inicio.setModel(new DefaultComboBoxModel<>(new String[] {
                "6:00",
                "7:00",
                "8:00",
                "9:00",
                "10:00",
                "11:00",
                "12:00",
                "13:00",
                "14:00",
                "15:00",
                "16:00",
                "17:00",
                "18:00",
                "19:00",
                "20:00"
            }));
            jPanel1.add(comboBoxJueves_inicio);
            comboBoxJueves_inicio.setBounds(130, 320, 70, comboBoxJueves_inicio.getPreferredSize().height);

            //---- comboBoxMiercoles_inicio ----
            comboBoxMiercoles_inicio.setBackground(Color.white);
            comboBoxMiercoles_inicio.setForeground(Color.black);
            comboBoxMiercoles_inicio.setModel(new DefaultComboBoxModel<>(new String[] {
                "6:00",
                "7:00",
                "8:00",
                "9:00",
                "10:00",
                "11:00",
                "12:00",
                "13:00",
                "14:00",
                "15:00",
                "16:00",
                "17:00",
                "18:00",
                "19:00",
                "20:00"
            }));
            jPanel1.add(comboBoxMiercoles_inicio);
            comboBoxMiercoles_inicio.setBounds(130, 280, 70, comboBoxMiercoles_inicio.getPreferredSize().height);

            //---- comboBoxMiercoles_fin ----
            comboBoxMiercoles_fin.setBackground(Color.white);
            comboBoxMiercoles_fin.setForeground(Color.black);
            comboBoxMiercoles_fin.setModel(new DefaultComboBoxModel<>(new String[] {
                "6:00",
                "7:00",
                "8:00",
                "9:00",
                "10:00",
                "11:00",
                "12:00",
                "13:00",
                "14:00",
                "15:00",
                "16:00",
                "17:00",
                "18:00",
                "19:00",
                "20:00"
            }));
            jPanel1.add(comboBoxMiercoles_fin);
            comboBoxMiercoles_fin.setBounds(220, 280, 70, comboBoxMiercoles_fin.getPreferredSize().height);

            //---- comboBoxDomingo_fin ----
            comboBoxDomingo_fin.setBackground(Color.white);
            comboBoxDomingo_fin.setForeground(Color.black);
            comboBoxDomingo_fin.setModel(new DefaultComboBoxModel<>(new String[] {
                "6:00",
                "7:00",
                "8:00",
                "9:00",
                "10:00",
                "11:00",
                "12:00",
                "13:00",
                "14:00",
                "15:00",
                "16:00",
                "17:00",
                "18:00",
                "19:00",
                "20:00"
            }));
            comboBoxDomingo_fin.addActionListener(e -> comboBoxDomingo_finActionPerformed(e));
            jPanel1.add(comboBoxDomingo_fin);
            comboBoxDomingo_fin.setBounds(550, 280, 70, comboBoxDomingo_fin.getPreferredSize().height);

            //---- comboBoxDomingo_inicio ----
            comboBoxDomingo_inicio.setBackground(Color.white);
            comboBoxDomingo_inicio.setForeground(Color.black);
            comboBoxDomingo_inicio.setModel(new DefaultComboBoxModel<>(new String[] {
                "6:00",
                "7:00",
                "8:00",
                "9:00",
                "10:00",
                "11:00",
                "12:00",
                "13:00",
                "14:00",
                "15:00",
                "16:00",
                "17:00",
                "18:00",
                "19:00",
                "20:00"
            }));
            jPanel1.add(comboBoxDomingo_inicio);
            comboBoxDomingo_inicio.setBounds(460, 280, 70, comboBoxDomingo_inicio.getPreferredSize().height);

            //---- comboBoxSabado_inicio ----
            comboBoxSabado_inicio.setBackground(Color.white);
            comboBoxSabado_inicio.setForeground(Color.black);
            comboBoxSabado_inicio.setModel(new DefaultComboBoxModel<>(new String[] {
                "6:00",
                "7:00",
                "8:00",
                "9:00",
                "10:00",
                "11:00",
                "12:00",
                "13:00",
                "14:00",
                "15:00",
                "16:00",
                "17:00",
                "18:00",
                "19:00",
                "20:00"
            }));
            jPanel1.add(comboBoxSabado_inicio);
            comboBoxSabado_inicio.setBounds(460, 240, 70, comboBoxSabado_inicio.getPreferredSize().height);

            //---- comboBoxViernes_inicio ----
            comboBoxViernes_inicio.setBackground(Color.white);
            comboBoxViernes_inicio.setForeground(Color.black);
            comboBoxViernes_inicio.setModel(new DefaultComboBoxModel<>(new String[] {
                "6:00",
                "7:00",
                "8:00",
                "9:00",
                "10:00",
                "11:00",
                "12:00",
                "13:00",
                "14:00",
                "15:00",
                "16:00",
                "17:00",
                "18:00",
                "19:00",
                "20:00"
            }));
            jPanel1.add(comboBoxViernes_inicio);
            comboBoxViernes_inicio.setBounds(460, 200, 70, comboBoxViernes_inicio.getPreferredSize().height);

            //---- comboBoxViernes_fin ----
            comboBoxViernes_fin.setBackground(Color.white);
            comboBoxViernes_fin.setForeground(Color.black);
            comboBoxViernes_fin.setModel(new DefaultComboBoxModel<>(new String[] {
                "6:00",
                "7:00",
                "8:00",
                "9:00",
                "10:00",
                "11:00",
                "12:00",
                "13:00",
                "14:00",
                "15:00",
                "16:00",
                "17:00",
                "18:00",
                "19:00",
                "20:00"
            }));
            jPanel1.add(comboBoxViernes_fin);
            comboBoxViernes_fin.setBounds(550, 200, 70, comboBoxViernes_fin.getPreferredSize().height);

            //---- comboBoxSabado_fin ----
            comboBoxSabado_fin.setBackground(Color.white);
            comboBoxSabado_fin.setForeground(Color.black);
            comboBoxSabado_fin.setModel(new DefaultComboBoxModel<>(new String[] {
                "6:00",
                "7:00",
                "8:00",
                "9:00",
                "10:00",
                "11:00",
                "12:00",
                "13:00",
                "14:00",
                "15:00",
                "16:00",
                "17:00",
                "18:00",
                "19:00",
                "20:00"
            }));
            jPanel1.add(comboBoxSabado_fin);
            comboBoxSabado_fin.setBounds(550, 240, 70, comboBoxSabado_fin.getPreferredSize().height);

            //---- btnAgregarMartes ----
            btnAgregarMartes.setBackground(new Color(0x006699));
            btnAgregarMartes.setFont(new Font("Tahoma", Font.BOLD, 12));
            btnAgregarMartes.setForeground(Color.white);
            btnAgregarMartes.setText("+");
            btnAgregarMartes.setToolTipText("");
            btnAgregarMartes.setBorder(null);
            btnAgregarMartes.setBorderPainted(false);
            btnAgregarMartes.addActionListener(e -> btnAgregarMartesActionPerformed(e));
            jPanel1.add(btnAgregarMartes);
            btnAgregarMartes.setBounds(310, 240, 30, 20);

            //---- btnAgregarLunes ----
            btnAgregarLunes.setBackground(new Color(0x006699));
            btnAgregarLunes.setFont(new Font("Tahoma", Font.BOLD, 12));
            btnAgregarLunes.setForeground(Color.white);
            btnAgregarLunes.setText("+");
            btnAgregarLunes.setToolTipText("");
            btnAgregarLunes.setBorder(null);
            btnAgregarLunes.setBorderPainted(false);
            btnAgregarLunes.addActionListener(e -> btnAgregarLunesActionPerformed(e));
            jPanel1.add(btnAgregarLunes);
            btnAgregarLunes.setBounds(310, 200, 30, 20);

            //---- btnAgregarMiercoles ----
            btnAgregarMiercoles.setBackground(new Color(0x006699));
            btnAgregarMiercoles.setFont(new Font("Tahoma", Font.BOLD, 12));
            btnAgregarMiercoles.setForeground(Color.white);
            btnAgregarMiercoles.setText("+");
            btnAgregarMiercoles.setToolTipText("");
            btnAgregarMiercoles.setBorder(null);
            btnAgregarMiercoles.setBorderPainted(false);
            btnAgregarMiercoles.addActionListener(e -> btnAgregarMiercolesActionPerformed(e));
            jPanel1.add(btnAgregarMiercoles);
            btnAgregarMiercoles.setBounds(310, 280, 30, 20);

            //---- btnAgregarJueves ----
            btnAgregarJueves.setBackground(new Color(0x006699));
            btnAgregarJueves.setFont(new Font("Tahoma", Font.BOLD, 12));
            btnAgregarJueves.setForeground(Color.white);
            btnAgregarJueves.setText("+");
            btnAgregarJueves.setToolTipText("");
            btnAgregarJueves.setBorder(null);
            btnAgregarJueves.setBorderPainted(false);
            btnAgregarJueves.addActionListener(e -> btnAgregarJuevesActionPerformed(e));
            jPanel1.add(btnAgregarJueves);
            btnAgregarJueves.setBounds(310, 320, 30, 20);

            //---- btnAgregarDomingo ----
            btnAgregarDomingo.setBackground(new Color(0x006699));
            btnAgregarDomingo.setFont(new Font("Tahoma", Font.BOLD, 12));
            btnAgregarDomingo.setForeground(Color.white);
            btnAgregarDomingo.setText("+");
            btnAgregarDomingo.setToolTipText("");
            btnAgregarDomingo.setBorder(null);
            btnAgregarDomingo.setBorderPainted(false);
            btnAgregarDomingo.addActionListener(e -> btnAgregarDomingoActionPerformed(e));
            jPanel1.add(btnAgregarDomingo);
            btnAgregarDomingo.setBounds(640, 280, 30, 20);

            //---- btnAgregarSabado ----
            btnAgregarSabado.setBackground(new Color(0x006699));
            btnAgregarSabado.setFont(new Font("Tahoma", Font.BOLD, 12));
            btnAgregarSabado.setForeground(Color.white);
            btnAgregarSabado.setText("+");
            btnAgregarSabado.setToolTipText("");
            btnAgregarSabado.setBorder(null);
            btnAgregarSabado.setBorderPainted(false);
            btnAgregarSabado.addActionListener(e -> btnAgregarSabadoActionPerformed(e));
            jPanel1.add(btnAgregarSabado);
            btnAgregarSabado.setBounds(640, 240, 30, 20);

            //---- btnAgregarViernes ----
            btnAgregarViernes.setBackground(new Color(0x006699));
            btnAgregarViernes.setFont(new Font("Tahoma", Font.BOLD, 12));
            btnAgregarViernes.setForeground(Color.white);
            btnAgregarViernes.setText("+");
            btnAgregarViernes.setToolTipText("");
            btnAgregarViernes.setBorder(null);
            btnAgregarViernes.setBorderPainted(false);
            btnAgregarViernes.addActionListener(e -> btnAgregarViernesActionPerformed(e));
            jPanel1.add(btnAgregarViernes);
            btnAgregarViernes.setBounds(640, 200, 30, 20);

            //======== jScrollPane1 ========
            {

                //---- tablaSemana ----
                tablaSemana.setBackground(Color.white);
                tablaSemana.setFont(new Font("Tahoma", Font.PLAIN, 14));
                tablaSemana.setForeground(Color.black);
                tablaSemana.setModel(new DefaultTableModel(
                    new Object[][] {
                        {"6:00", null, null, null, null, null, null, null},
                        {"7:00", null, null, null, null, null, null, null},
                        {"8:00", null, null, null, null, null, null, null},
                        {"9:00", null, null, null, null, null, null, null},
                        {"10:00", null, null, null, null, null, null, null},
                        {"11:00", null, null, null, null, null, null, null},
                        {"12:00", null, null, null, null, null, null, null},
                        {"13:00", null, null, null, null, null, null, null},
                        {"14:00", null, null, null, null, null, null, null},
                        {"15:00", null, null, null, null, null, null, null},
                        {"16:00", null, null, null, null, null, null, null},
                        {"17:00", null, null, null, null, null, null, null},
                        {"18:00", null, null, null, null, null, null, null},
                        {"19:00", null, null, null, null, null, null, null},
                    },
                    new String[] {
                        "Hora", "LUNES", "MARTES", "MIERCOLES", "JUEVES", "VIERNES", "SABADO", "DOMINGO"
                    }
                ));
                tablaSemana.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                jScrollPane1.setViewportView(tablaSemana);
            }
            jPanel1.add(jScrollPane1);
            jScrollPane1.setBounds(60, 350, 620, 110);

            //---- btnLimpiarTabla ----
            btnLimpiarTabla.setBackground(new Color(0x006699));
            btnLimpiarTabla.setFont(new Font("Segoe UI", Font.BOLD, 14));
            btnLimpiarTabla.setForeground(Color.white);
            btnLimpiarTabla.setText("Limpiar Horario");
            btnLimpiarTabla.setToolTipText("");
            btnLimpiarTabla.setBorderPainted(false);
            btnLimpiarTabla.addActionListener(e -> btnLimpiarTablaActionPerformed(e));
            jPanel1.add(btnLimpiarTabla);
            btnLimpiarTabla.setBounds(60, 470, 140, 40);

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

    // --- MANEJADORES DE EVENTOS ---
    
    private void btnGuardarDispoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarDispoActionPerformed
        guardarDisponibilidad();
    }//GEN-LAST:event_btnGuardarDispoActionPerformed

    private void regresarHomebtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_regresarHomebtnActionPerformed
        limpiarTablaCompleta();
        new HomeMedico(medicoActual).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_regresarHomebtnActionPerformed

    private void comboBoxJueves_finActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBoxJueves_finActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboBoxJueves_finActionPerformed

    private void comboBoxDomingo_finActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBoxDomingo_finActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboBoxDomingo_finActionPerformed

    private void btnAgregarLunesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarLunesActionPerformed
        agregarDisponibilidadParaDia(DiaSemana.LUNES, comboBoxLunes_inicio, comboBoxLunes_fin);
    }//GEN-LAST:event_btnAgregarLunesActionPerformed

    private void comboBoxLunes_inicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBoxLunes_inicioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboBoxLunes_inicioActionPerformed

    private void btnAgregarMartesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarMartesActionPerformed
        agregarDisponibilidadParaDia(DiaSemana.MARTES, comboBoxMartes_inicio, comboBoxMartes_fin);
    }//GEN-LAST:event_btnAgregarMartesActionPerformed

    private void btnAgregarMiercolesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarMiercolesActionPerformed
        agregarDisponibilidadParaDia(DiaSemana.MIERCOLES, comboBoxMiercoles_inicio, comboBoxMiercoles_fin);
    }//GEN-LAST:event_btnAgregarMiercolesActionPerformed

    private void btnAgregarJuevesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarJuevesActionPerformed
        agregarDisponibilidadParaDia(DiaSemana.JUEVES, comboBoxJueves_inicio, comboBoxJueves_fin);
    }//GEN-LAST:event_btnAgregarJuevesActionPerformed

    private void btnAgregarViernesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarViernesActionPerformed
        agregarDisponibilidadParaDia(DiaSemana.VIERNES, comboBoxViernes_inicio, comboBoxViernes_fin);
    }//GEN-LAST:event_btnAgregarViernesActionPerformed

    private void btnAgregarSabadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarSabadoActionPerformed
        agregarDisponibilidadParaDia(DiaSemana.SABADO, comboBoxSabado_inicio, comboBoxSabado_fin);
    }//GEN-LAST:event_btnAgregarSabadoActionPerformed

    private void btnAgregarDomingoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarDomingoActionPerformed
        agregarDisponibilidadParaDia(DiaSemana.DOMINGO, comboBoxDomingo_inicio, comboBoxDomingo_fin);
    }//GEN-LAST:event_btnAgregarDomingoActionPerformed

    private void btnLimpiarTablaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarTablaActionPerformed
        limpiarTablaCompleta();
    }//GEN-LAST:event_btnLimpiarTablaActionPerformed

    // --- MÉTODOS PRIVADOS DE AYUDA ---

    /**
     * Guarda la disponibilidad configurada en la tabla en la base de datos.
     * Primero borra la disponibilidad anterior y luego guarda la nueva.
     */
    private void guardarDisponibilidad() {
        controller.eliminarDisponibilidadesPorMedico(medicoActual.getId());

        Map<Integer, List<Integer>> horasPorDia = new HashMap<>();
        for (Point p : celdasDisponibles) {
            horasPorDia.computeIfAbsent(p.y, k -> new ArrayList<>()).add(p.x + 6);
        }

        boolean exitoTotal = true;
        for (Map.Entry<Integer, List<Integer>> entry : horasPorDia.entrySet()) {
            List<Integer> horas = entry.getValue();
            horas.sort(Integer::compareTo);

            if (horas.isEmpty()) continue;

            int inicioBloque = horas.get(0);
            for (int i = 1; i <= horas.size(); i++) {
                if (i == horas.size() || !horas.get(i).equals(horas.get(i - 1) + 1)) {
                    int finBloque = horas.get(i - 1) + 1;
                    
                    DisponibilidadMedica disp = new DisponibilidadMedica();
                    disp.setMedico(medicoActual);
                    disp.setDiaSemana(convertirColumnaADiaSemana(entry.getKey()));
                    disp.setHoraInicio(LocalTime.of(inicioBloque, 0));
                    disp.setHoraFin(LocalTime.of(finBloque, 0));
                    
                    if (!controller.registrarDisponibilidad(disp)) {
                        exitoTotal = false;
                        break;
                    }
                    if (i < horas.size()) {
                        inicioBloque = horas.get(i);
                    }
                }
            }
            if (!exitoTotal) break;
        }

        if (exitoTotal) {
            JOptionPane.showMessageDialog(this, "Disponibilidad guardada correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Ocurrió un error al guardar la disponibilidad.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /**
     * Método centralizado para añadir un bloque de disponibilidad.
     * @param dia El día de la semana.
     * @param comboInicio El JComboBox de la hora de inicio.
     * @param comboFin El JComboBox de la hora de fin.
     */
    private void agregarDisponibilidadParaDia(DiaSemana dia, JComboBox<String> comboInicio, JComboBox<String> comboFin) {
        int columna = dia.ordinal() + 1;
        int horaInicio = comboInicio.getSelectedIndex() + 6;
        int horaFin = comboFin.getSelectedIndex() + 6;

        if (horaInicio >= horaFin) {
            JOptionPane.showMessageDialog(this, "La hora de inicio debe ser menor que la hora de fin.", "Error de horario", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        marcarDisponibilidadEnTabla(columna, horaInicio, horaFin, true);
    }
    
    /**
     * Pinta las celdas de la tabla para un rango horario en un día específico.
     * @param columnaDia La columna que representa el día.
     * @param horaInicio La hora de inicio (formato 24h).
     * @param horaFin La hora de fin (formato 24h).
     * @param limpiarPrevio Si es true, limpia las celdas de ese día antes de pintar.
     */
    private void marcarDisponibilidadEnTabla(int columnaDia, int horaInicio, int horaFin, boolean limpiarPrevio) {
        if (limpiarPrevio) {
            celdasDisponibles.removeIf(p -> p.y == columnaDia);
        }

        for (int hora = horaInicio; hora < horaFin; hora++) {
            int fila = hora - 6; // Convertir hora a índice de fila
            if (fila >= 0 && fila < tablaSemana.getRowCount()) {
                celdasDisponibles.add(new Point(fila, columnaDia));
            }
        }
        tablaSemana.repaint(); // Redibuja la tabla para mostrar los colores
    }

    /**
     * Limpia por completo la tabla y resetea los ComboBox.
     */
    private void limpiarTablaCompleta() {
        celdasDisponibles.clear();
        ((DefaultTableModel) tablaSemana.getModel()).fireTableDataChanged();
        tablaSemana.repaint();
    }
    
    /**
     * Convierte el índice de una columna de la tabla al enum DiaSemana.
     * @param columna El índice de la columna (1 para Lunes, 2 para Martes, etc.).
     * @return El DiaSemana correspondiente.
     */
    private DiaSemana convertirColumnaADiaSemana(int columna) {
        return DiaSemana.values()[columna - 1];
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - Issael
    private JPanel jPanel1;
    private JPanel jPanel2;
    private JLabel favicon1;
    private JLabel jLabel2;
    private JButton btnGuardarDispo;
    private JLabel jLabel6;
    private JButton regresarHomebtn;
    private JLabel jLabel1;
    private JLabel jLabel3;
    private JLabel jLabel4;
    private JLabel jLabel5;
    private JLabel jLabel7;
    private JLabel jLabel8;
    private JLabel jLabel9;
    private JComboBox<String> comboBoxLunes_fin;
    private JComboBox<String> comboBoxLunes_inicio;
    private JComboBox<String> comboBoxMartes_inicio;
    private JComboBox<String> comboBoxMartes_fin;
    private JComboBox<String> comboBoxJueves_fin;
    private JComboBox<String> comboBoxJueves_inicio;
    private JComboBox<String> comboBoxMiercoles_inicio;
    private JComboBox<String> comboBoxMiercoles_fin;
    private JComboBox<String> comboBoxDomingo_fin;
    private JComboBox<String> comboBoxDomingo_inicio;
    private JComboBox<String> comboBoxSabado_inicio;
    private JComboBox<String> comboBoxViernes_inicio;
    private JComboBox<String> comboBoxViernes_fin;
    private JComboBox<String> comboBoxSabado_fin;
    private JButton btnAgregarMartes;
    private JButton btnAgregarLunes;
    private JButton btnAgregarMiercoles;
    private JButton btnAgregarJueves;
    private JButton btnAgregarDomingo;
    private JButton btnAgregarSabado;
    private JButton btnAgregarViernes;
    private JScrollPane jScrollPane1;
    private JTable tablaSemana;
    private JButton btnLimpiarTabla;
    // End of variables declaration//GEN-END:variables
}
