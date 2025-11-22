package com.gestioncitas_trabajofinal.view.paciente;

import java.awt.*;
import javax.swing.*;
import javax.swing.GroupLayout;
import com.gestioncitas_trabajofinal.view.Login;
import com.gestioncitas_trabajofinal.controller.PacienteController;
import com.gestioncitas_trabajofinal.model.Paciente;
import java.util.Date;
import javax.swing.JOptionPane;
import com.toedter.calendar.*;

/**
 * Ventana que permite a un paciente ver y modificar sus datos personales.
 * @author lumic
 */
public class ProfilePaciente extends javax.swing.JFrame {

    private final Paciente paciente;
    private final PacienteController pacienteController;
    private boolean passwordVisible = false;
    private JButton btnTogglePassword;
    private String plainPassword; // Contraseña en texto plano

    /**
     * Constructor que inicializa la ventana con los datos del paciente.
     * @param paciente El paciente que ha iniciado sesión.
     */
    public ProfilePaciente(Paciente paciente) {
        this(paciente, null);
    }

    /**
     * Constructor que inicializa la ventana con los datos del paciente y su contraseña.
     * @param paciente El paciente que ha iniciado sesión.
     * @param plainPassword La contraseña en texto plano del usuario.
     */
    public ProfilePaciente(Paciente paciente, String plainPassword) {
        this.paciente = paciente;
        this.plainPassword = plainPassword;
        this.pacienteController = new PacienteController();
        initComponents();
        cargarDatosPaciente();
        setLocationRelativeTo(null);
    }

    /**
     * Rellena los campos del formulario con la información actual del paciente.
     * Los campos DNI y correo electrónico se establecen como no editables.
     */
    private void cargarDatosPaciente() {
        lblUsername.setText(paciente.getUsername());
        txtNombre.setText(paciente.getNombre());
        txtApellido.setText(paciente.getApellido());
        txtTelefono.setText(paciente.getTelefono());
        txtDireccion.setText(paciente.getDireccion());
        // Si tenemos la contraseña en texto plano, mostrarla
        if (plainPassword != null && !plainPassword.isEmpty()) {
            txtPassword.setText(plainPassword);
            txtPassword.setEchoChar('•'); // Mostrar la contraseña como puntos
        } else {
            // Si no tenemos la contraseña en texto plano, dejar vacío
            txtPassword.setText("");
            txtPassword.setEchoChar('•');
        }
        dateChooserFechaNacimiento.setDate(paciente.getFechaNacimiento());
        labelDni.setText(paciente.getDni());
        labelUsername.setText(paciente.getUsername());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    // Generated using JFormDesigner Evaluation license - Issael
    private void initComponents() {
        jPanel1 = new JPanel();
        userIcon = new JLabel();
        jPanel3 = new JPanel();
        inicioBtn = new JButton();
        perfilBtn = new JButton();
        citasBtn = new JButton();
        lblUsername = new JLabel();
        jPanel4 = new JPanel();
        cerrarsesionBtn = new JButton();
        userIcon1 = new JLabel();
        jPanel2 = new JPanel();
        favicon1 = new JLabel();
        div1 = new JSeparator();
        apellidosLabel = new JLabel();
        div3 = new JSeparator();
        dniLabel = new JLabel();
        div4 = new JSeparator();
        fechaLabel = new JLabel();
        div5 = new JSeparator();
        correoLabel = new JLabel();
        div6 = new JSeparator();
        telfLabel = new JLabel();
        div7 = new JSeparator();
        contraseñaLabel = new JLabel();
        div2 = new JSeparator();
        nombreLabel1 = new JLabel();
        correoLabel1 = new JLabel();
        div8 = new JSeparator();
        btnActualizarPerfil = new JButton();
        nombreLabel9 = new JLabel();
        txtTelefono = new JTextField();
        txtPassword = new JPasswordField();
        btnTogglePassword = new JButton();
        txtNombre = new JTextField();
        txtApellido = new JTextField();
        labelDni = new JLabel();
        dateChooserFechaNacimiento = new JDateChooser();
        labelUsername = new JLabel();
        txtDireccion = new JTextField();

        //======== this ========
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        var contentPane = getContentPane();

        //======== jPanel1 ========
        {
            jPanel1.setBackground(Color.white);
            jPanel1.setBorder (new javax. swing. border. CompoundBorder( new javax .swing .border .TitledBorder (new javax. swing. border. EmptyBorder(
            0, 0, 0, 0) , "JF\u006frmDes\u0069gner \u0045valua\u0074ion", javax. swing. border. TitledBorder. CENTER, javax. swing. border. TitledBorder
            . BOTTOM, new java .awt .Font ("D\u0069alog" ,java .awt .Font .BOLD ,12 ), java. awt. Color.
            red) ,jPanel1. getBorder( )) ); jPanel1. addPropertyChangeListener (new java. beans. PropertyChangeListener( ){ @Override public void propertyChange (java .
            beans .PropertyChangeEvent e) {if ("\u0062order" .equals (e .getPropertyName () )) throw new RuntimeException( ); }} );
            jPanel1.setLayout(null);

            //---- userIcon ----
            userIcon.setHorizontalAlignment(SwingConstants.CENTER);
            userIcon.setIcon(new ImageIcon(getClass().getResource("/View/imagenes/user.png")));
            jPanel1.add(userIcon);
            userIcon.setBounds(200, 80, 90, userIcon.getPreferredSize().height);

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
                perfilBtn.setBackground(Color.white);
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

                //---- userIcon1 ----
                userIcon1.setHorizontalAlignment(SwingConstants.CENTER);
                userIcon1.setIcon(new ImageIcon(getClass().getResource("/View/imagenes/user.png")));
                jPanel3.add(userIcon1);
                userIcon1.setBounds(0, 10, 180, userIcon1.getPreferredSize().height);

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
            jPanel1.add(div1);
            div1.setBounds(310, 180, 190, div1.getPreferredSize().height);

            //---- apellidosLabel ----
            apellidosLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
            apellidosLabel.setForeground(Color.black);
            apellidosLabel.setText("APELLIDOS");
            jPanel1.add(apellidosLabel);
            apellidosLabel.setBounds(530, 130, 190, apellidosLabel.getPreferredSize().height);
            jPanel1.add(div3);
            div3.setBounds(530, 180, 190, div3.getPreferredSize().height);

            //---- dniLabel ----
            dniLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
            dniLabel.setForeground(Color.black);
            dniLabel.setText("DNI");
            jPanel1.add(dniLabel);
            dniLabel.setBounds(310, 210, 190, dniLabel.getPreferredSize().height);
            jPanel1.add(div4);
            div4.setBounds(310, 260, 190, div4.getPreferredSize().height);

            //---- fechaLabel ----
            fechaLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
            fechaLabel.setForeground(Color.black);
            fechaLabel.setText("FECHA DE NACIMIENTO");
            jPanel1.add(fechaLabel);
            fechaLabel.setBounds(530, 210, 190, fechaLabel.getPreferredSize().height);
            jPanel1.add(div5);
            div5.setBounds(530, 260, 190, div5.getPreferredSize().height);

            //---- correoLabel ----
            correoLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
            correoLabel.setForeground(Color.black);
            correoLabel.setText("CORREO ELECTRONICO");
            jPanel1.add(correoLabel);
            correoLabel.setBounds(310, 290, 190, correoLabel.getPreferredSize().height);
            jPanel1.add(div6);
            div6.setBounds(310, 340, 190, 10);

            //---- telfLabel ----
            telfLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
            telfLabel.setForeground(Color.black);
            telfLabel.setText("TELEFONO");
            jPanel1.add(telfLabel);
            telfLabel.setBounds(310, 370, 190, telfLabel.getPreferredSize().height);
            jPanel1.add(div7);
            div7.setBounds(310, 420, 190, div7.getPreferredSize().height);

            //---- contraseñaLabel ----
            contraseñaLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
            contraseñaLabel.setForeground(Color.black);
            contraseñaLabel.setText("CONTRASE\u00d1A");
            jPanel1.add(contraseñaLabel);
            contraseñaLabel.setBounds(530, 370, 190, contraseñaLabel.getPreferredSize().height);
            jPanel1.add(div2);
            div2.setBounds(530, 420, 190, div2.getPreferredSize().height);

            //---- nombreLabel1 ----
            nombreLabel1.setFont(new Font("Tahoma", Font.BOLD, 20));
            nombreLabel1.setForeground(Color.black);
            nombreLabel1.setHorizontalAlignment(SwingConstants.CENTER);
            nombreLabel1.setText("PERFIL DE PACIENTE");
            jPanel1.add(nombreLabel1);
            nombreLabel1.setBounds(310, 80, 410, nombreLabel1.getPreferredSize().height);

            //---- correoLabel1 ----
            correoLabel1.setFont(new Font("Tahoma", Font.PLAIN, 14));
            correoLabel1.setForeground(Color.black);
            correoLabel1.setText("DIRECCION");
            jPanel1.add(correoLabel1);
            correoLabel1.setBounds(530, 290, 190, correoLabel1.getPreferredSize().height);
            jPanel1.add(div8);
            div8.setBounds(530, 340, 190, 10);

            //---- btnActualizarPerfil ----
            btnActualizarPerfil.setBackground(new Color(0x006699));
            btnActualizarPerfil.setFont(new Font("Segoe UI", Font.BOLD, 14));
            btnActualizarPerfil.setForeground(Color.white);
            btnActualizarPerfil.setText("Guardar cambios");
            btnActualizarPerfil.setBorderPainted(false);
            btnActualizarPerfil.addActionListener(e -> btnActualizarPerfilActionPerformed(e));
            jPanel1.add(btnActualizarPerfil);
            btnActualizarPerfil.setBounds(310, 460, 150, 40);

            //---- nombreLabel9 ----
            nombreLabel9.setFont(new Font("Tahoma", Font.PLAIN, 14));
            nombreLabel9.setForeground(Color.black);
            nombreLabel9.setText("NOMBRES");
            jPanel1.add(nombreLabel9);
            nombreLabel9.setBounds(310, 130, 190, nombreLabel9.getPreferredSize().height);

            //---- txtTelefono ----
            txtTelefono.setBackground(Color.white);
            txtTelefono.setForeground(Color.black);
            txtTelefono.setBorder(null);
            txtTelefono.addActionListener(e -> txtTelefonoActionPerformed(e));
            jPanel1.add(txtTelefono);
            txtTelefono.setBounds(310, 400, 190, 20);

            //---- txtPassword ----
            txtPassword.setBackground(Color.white);
            txtPassword.setForeground(Color.black);
            txtPassword.setBorder(null);
            txtPassword.setEchoChar('•');
            txtPassword.addActionListener(e -> txtPasswordActionPerformed(e));
            jPanel1.add(txtPassword);
            txtPassword.setBounds(530, 400, 160, 20);

            //---- btnTogglePassword ----
            btnTogglePassword.setBackground(Color.white);
            btnTogglePassword.setText("👁");
            btnTogglePassword.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 16));
            btnTogglePassword.setBorder(null);
            btnTogglePassword.setFocusPainted(false);
            btnTogglePassword.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            btnTogglePassword.setToolTipText("Mostrar/Ocultar contraseña");
            btnTogglePassword.addActionListener(e -> btnTogglePasswordActionPerformed(e));
            jPanel1.add(btnTogglePassword);
            btnTogglePassword.setBounds(695, 398, 25, 24);

            //---- txtNombre ----
            txtNombre.setBackground(Color.white);
            txtNombre.setForeground(Color.black);
            txtNombre.setBorder(null);
            txtNombre.addActionListener(e -> txtNombreActionPerformed(e));
            jPanel1.add(txtNombre);
            txtNombre.setBounds(310, 160, 190, 20);

            //---- txtApellido ----
            txtApellido.setBackground(Color.white);
            txtApellido.setForeground(Color.black);
            txtApellido.setBorder(null);
            txtApellido.addActionListener(e -> txtApellidoActionPerformed(e));
            jPanel1.add(txtApellido);
            txtApellido.setBounds(530, 160, 190, 20);

            //---- labelDni ----
            labelDni.setFont(new Font("Segoe UI", Font.PLAIN, 14));
            labelDni.setForeground(Color.black);
            jPanel1.add(labelDni);
            labelDni.setBounds(310, 240, 190, 20);

            //---- dateChooserFechaNacimiento ----
            dateChooserFechaNacimiento.setBackground(Color.white);
            dateChooserFechaNacimiento.setForeground(Color.black);
            jPanel1.add(dateChooserFechaNacimiento);
            dateChooserFechaNacimiento.setBounds(530, 240, 190, dateChooserFechaNacimiento.getPreferredSize().height);

            //---- labelUsername ----
            labelUsername.setFont(new Font("Segoe UI", Font.PLAIN, 14));
            labelUsername.setForeground(Color.black);
            jPanel1.add(labelUsername);
            labelUsername.setBounds(310, 320, 190, 20);

            //---- txtDireccion ----
            txtDireccion.setBackground(Color.white);
            txtDireccion.setForeground(Color.black);
            txtDireccion.setBorder(null);
            txtDireccion.addActionListener(e -> txtDireccionActionPerformed(e));
            jPanel1.add(txtDireccion);
            txtDireccion.setBounds(530, 320, 190, 20);

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
     * Navega de vuelta a la pantalla de inicio del paciente.
     * @param evt El evento de acción.
     */
    private void inicioBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inicioBtnActionPerformed
        new HomePaciente(paciente).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_inicioBtnActionPerformed

    private void perfilBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_perfilBtnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_perfilBtnActionPerformed

    /**
     * Navega a la pantalla de historial de citas.
     * @param evt El evento de acción.
     */
    private void citasBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_citasBtnActionPerformed
        new MisCitas(paciente).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_citasBtnActionPerformed
    
    /**
     * Cierra la sesión del usuario y vuelve a la pantalla de Login.
     * @param evt El evento de acción.
     */
    private void cerrarsesionBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cerrarsesionBtnActionPerformed
        new Login().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_cerrarsesionBtnActionPerformed

    /**
     * Maneja el clic en el botón 'Guardar cambios'. Inicia el proceso de actualización.
     * @param evt El evento de acción.
     */
    private void btnActualizarPerfilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarPerfilActionPerformed
        actualizarDatosDelPaciente();
    }//GEN-LAST:event_btnActualizarPerfilActionPerformed

    private void txtTelefonoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTelefonoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTelefonoActionPerformed

    private void txtPasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPasswordActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPasswordActionPerformed

    private void txtNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombreActionPerformed

    private void txtApellidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtApellidoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtApellidoActionPerformed

    private void txtDireccionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDireccionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDireccionActionPerformed

    /**
     * Alterna entre mostrar y ocultar la contraseña.
     * @param evt El evento de acción.
     */
    private void btnTogglePasswordActionPerformed(java.awt.event.ActionEvent evt) {
        if (passwordVisible) {
            // Ocultar contraseña
            txtPassword.setEchoChar('•');
            btnTogglePassword.setText("👁");
            passwordVisible = false;
        } else {
            // Mostrar contraseña
            txtPassword.setEchoChar((char) 0);
            btnTogglePassword.setText("👁‍🗨");
            passwordVisible = true;
        }
    }

    // ------------------- MÉTODOS PRIVADOS DE AYUDA -------------------

    /**
     * Recopila los datos del formulario, los valida y, si son correctos,
     * actualiza la información del paciente en la base de datos.
     */
    private void actualizarDatosDelPaciente() {
        String nombre = txtNombre.getText().trim();
        String apellido = txtApellido.getText().trim();
        String direccion = txtDireccion.getText().trim();
        String telefono = txtTelefono.getText().trim();
        String password = new String(txtPassword.getPassword()).trim();
        Date fechaNacimiento = dateChooserFechaNacimiento.getDate();

        // Validación simple de campos no vacíos (excepto contraseña que es opcional)
        if (nombre.isEmpty() || apellido.isEmpty() || direccion.isEmpty() || telefono.isEmpty() || fechaNacimiento == null) {
            JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios (excepto contraseña).", "Error de validación", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Actualiza el objeto paciente con los nuevos datos
        paciente.setNombre(nombre);
        paciente.setApellido(apellido);
        paciente.setDireccion(direccion);
        paciente.setTelefono(telefono);

        // Solo actualizar la contraseña si el usuario ingresó una nueva
        if (!password.isEmpty()) {
            paciente.setPassword(password);
            plainPassword = password; // Actualizar la contraseña en memoria
        }

        paciente.setFechaNacimiento(fechaNacimiento);

        // Llama al controlador para persistir los cambios
        boolean actualizado = pacienteController.actualizarDatosPaciente(paciente);

        if (actualizado) {
            JOptionPane.showMessageDialog(this, "Datos actualizados correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            // Mostrar la contraseña actualizada (oculta con puntos)
            if (!password.isEmpty()) {
                txtPassword.setText(plainPassword);
                txtPassword.setEchoChar('•');
            }
        } else {
            JOptionPane.showMessageDialog(this, "Error al actualizar los datos.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - Issael
    private JPanel jPanel1;
    private JLabel userIcon;
    private JPanel jPanel3;
    private JButton inicioBtn;
    private JButton perfilBtn;
    private JButton citasBtn;
    private JLabel lblUsername;
    private JPanel jPanel4;
    private JButton cerrarsesionBtn;
    private JLabel userIcon1;
    private JPanel jPanel2;
    private JLabel favicon1;
    private JSeparator div1;
    private JLabel apellidosLabel;
    private JSeparator div3;
    private JLabel dniLabel;
    private JSeparator div4;
    private JLabel fechaLabel;
    private JSeparator div5;
    private JLabel correoLabel;
    private JSeparator div6;
    private JLabel telfLabel;
    private JSeparator div7;
    private JLabel contraseñaLabel;
    private JSeparator div2;
    private JLabel nombreLabel1;
    private JLabel correoLabel1;
    private JSeparator div8;
    private JButton btnActualizarPerfil;
    private JLabel nombreLabel9;
    private JTextField txtTelefono;
    private JPasswordField txtPassword;
    private JTextField txtNombre;
    private JTextField txtApellido;
    private JLabel labelDni;
    private JDateChooser dateChooserFechaNacimiento;
    private JLabel labelUsername;
    private JTextField txtDireccion;
    // End of variables declaration//GEN-END:variables
}
