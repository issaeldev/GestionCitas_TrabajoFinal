package com.gestioncitas_trabajofinal.view.medico;

import java.awt.*;
import javax.swing.*;
import javax.swing.GroupLayout;
import com.gestioncitas_trabajofinal.view.Login;
import com.gestioncitas_trabajofinal.model.Medico;
import java.text.SimpleDateFormat;

/**
 * Ventana que muestra el perfil de un médico con sus datos personales.
 * La información mostrada es de solo lectura.
 * @author lumic
 */
public class ProfileMedico extends javax.swing.JFrame {

    private final Medico medico;

    /**
     * Constructor que inicializa la ventana y carga los datos del médico.
     * @param medico El objeto Medico cuyos datos se mostrarán.
     */
    public ProfileMedico(Medico medico) {
        this.medico = medico;
        initComponents();
        cargarDatosMedico();
        setLocationRelativeTo(null);
    }

    /**
     * Rellena los componentes de la interfaz con los datos del médico.
     */
    private void cargarDatosMedico() {
        nombreDoctortxt.setText(medico.getUsername());
        nombreTXT.setText(medico.getNombre());
        apellidoTXT.setText(medico.getApellido());
        dniTXT.setText(medico.getDni());
        usernameTXT.setText(medico.getUsername());
        direccionTXT.setText(medico.getDireccion());
        telefonoTXT.setText(medico.getTelefono());
        especialidadTXT.setText(medico.getEspecialidad().getNombre());
        
        if (medico.getFechaNacimiento() != null) {
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            nacimientoTXT.setText(formatter.format(medico.getFechaNacimiento()));
        } else {
            nacimientoTXT.setText("No registrada");
        }
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
        nombreDoctortxt = new JLabel();
        jPanel4 = new JPanel();
        cerrarsesionBtn = new JButton();
        userIcon1 = new JLabel();
        jPanel2 = new JPanel();
        favicon1 = new JLabel();
        apellidoTXT = new JLabel();
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
        nombreTXT = new JLabel();
        passwordTXT = new JLabel();
        nacimientoTXT = new JLabel();
        dniTXT = new JLabel();
        usernameTXT = new JLabel();
        telefonoTXT = new JLabel();
        correoLabel1 = new JLabel();
        div8 = new JSeparator();
        direccionTXT = new JLabel();
        nombreLabel9 = new JLabel();
        especialidadTXT = new JLabel();
        apellidosLabel1 = new JLabel();
        div9 = new JSeparator();

        //======== this ========
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        var contentPane = getContentPane();

        //======== jPanel1 ========
        {
            jPanel1.setBackground(Color.white);
            jPanel1.setBorder (new javax. swing. border. CompoundBorder( new javax .swing .border .TitledBorder (new javax. swing. border
            . EmptyBorder( 0, 0, 0, 0) , "JF\u006frmDesi\u0067ner Ev\u0061luatio\u006e", javax. swing. border. TitledBorder. CENTER, javax
            . swing. border. TitledBorder. BOTTOM, new java .awt .Font ("Dialo\u0067" ,java .awt .Font .BOLD ,
            12 ), java. awt. Color. red) ,jPanel1. getBorder( )) ); jPanel1. addPropertyChangeListener (new java. beans
            . PropertyChangeListener( ){ @Override public void propertyChange (java .beans .PropertyChangeEvent e) {if ("borde\u0072" .equals (e .
            getPropertyName () )) throw new RuntimeException( ); }} );
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

            //---- apellidoTXT ----
            apellidoTXT.setFont(new Font("Tahoma", Font.PLAIN, 14));
            apellidoTXT.setForeground(Color.black);
            apellidoTXT.setText("Apellidos");
            jPanel1.add(apellidoTXT);
            apellidoTXT.setBounds(530, 160, 190, apellidoTXT.getPreferredSize().height);
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
            nombreLabel1.setText("PERFIL DE MEDICO");
            jPanel1.add(nombreLabel1);
            nombreLabel1.setBounds(310, 80, 410, nombreLabel1.getPreferredSize().height);

            //---- nombreTXT ----
            nombreTXT.setFont(new Font("Tahoma", Font.PLAIN, 14));
            nombreTXT.setForeground(Color.black);
            nombreTXT.setText("Nombre");
            jPanel1.add(nombreTXT);
            nombreTXT.setBounds(310, 160, 190, nombreTXT.getPreferredSize().height);

            //---- passwordTXT ----
            passwordTXT.setFont(new Font("Tahoma", Font.PLAIN, 14));
            passwordTXT.setForeground(Color.black);
            passwordTXT.setText("*************");
            jPanel1.add(passwordTXT);
            passwordTXT.setBounds(530, 400, 190, passwordTXT.getPreferredSize().height);

            //---- nacimientoTXT ----
            nacimientoTXT.setFont(new Font("Tahoma", Font.PLAIN, 14));
            nacimientoTXT.setForeground(Color.black);
            nacimientoTXT.setText("DD/MM/AAAA");
            jPanel1.add(nacimientoTXT);
            nacimientoTXT.setBounds(530, 240, 190, nacimientoTXT.getPreferredSize().height);

            //---- dniTXT ----
            dniTXT.setFont(new Font("Tahoma", Font.PLAIN, 14));
            dniTXT.setForeground(Color.black);
            dniTXT.setText("DNI ");
            jPanel1.add(dniTXT);
            dniTXT.setBounds(310, 240, 190, dniTXT.getPreferredSize().height);

            //---- usernameTXT ----
            usernameTXT.setFont(new Font("Tahoma", Font.PLAIN, 14));
            usernameTXT.setForeground(Color.black);
            usernameTXT.setText("usuario@hotmail.com");
            jPanel1.add(usernameTXT);
            usernameTXT.setBounds(310, 320, 190, usernameTXT.getPreferredSize().height);

            //---- telefonoTXT ----
            telefonoTXT.setFont(new Font("Tahoma", Font.PLAIN, 14));
            telefonoTXT.setForeground(Color.black);
            telefonoTXT.setText("N\u00famero de telefono");
            jPanel1.add(telefonoTXT);
            telefonoTXT.setBounds(310, 400, 190, telefonoTXT.getPreferredSize().height);

            //---- correoLabel1 ----
            correoLabel1.setFont(new Font("Tahoma", Font.PLAIN, 14));
            correoLabel1.setForeground(Color.black);
            correoLabel1.setText("DIRECCION");
            jPanel1.add(correoLabel1);
            correoLabel1.setBounds(530, 290, 190, correoLabel1.getPreferredSize().height);
            jPanel1.add(div8);
            div8.setBounds(530, 340, 190, 10);

            //---- direccionTXT ----
            direccionTXT.setFont(new Font("Tahoma", Font.PLAIN, 14));
            direccionTXT.setForeground(Color.black);
            direccionTXT.setText("Direcci\u00f3n");
            jPanel1.add(direccionTXT);
            direccionTXT.setBounds(530, 320, 190, direccionTXT.getPreferredSize().height);

            //---- nombreLabel9 ----
            nombreLabel9.setFont(new Font("Tahoma", Font.PLAIN, 14));
            nombreLabel9.setForeground(Color.black);
            nombreLabel9.setText("NOMBRES");
            jPanel1.add(nombreLabel9);
            nombreLabel9.setBounds(310, 130, 190, nombreLabel9.getPreferredSize().height);

            //---- especialidadTXT ----
            especialidadTXT.setFont(new Font("Tahoma", Font.PLAIN, 14));
            especialidadTXT.setForeground(Color.black);
            especialidadTXT.setText("Especialidad");
            jPanel1.add(especialidadTXT);
            especialidadTXT.setBounds(310, 480, 190, especialidadTXT.getPreferredSize().height);

            //---- apellidosLabel1 ----
            apellidosLabel1.setFont(new Font("Tahoma", Font.PLAIN, 14));
            apellidosLabel1.setForeground(Color.black);
            apellidosLabel1.setText("ESPECIALIDAD");
            jPanel1.add(apellidosLabel1);
            apellidosLabel1.setBounds(310, 450, 190, apellidosLabel1.getPreferredSize().height);
            jPanel1.add(div9);
            div9.setBounds(310, 500, 190, div9.getPreferredSize().height);

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
     * Navega de vuelta a la pantalla de inicio del médico.
     * @param evt El evento de acción.
     */
    private void inicioBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inicioBtnActionPerformed
        new HomeMedico(medico).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_inicioBtnActionPerformed

    private void perfilBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_perfilBtnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_perfilBtnActionPerformed

    /**
     * Cierra la sesión del médico y vuelve a la pantalla de Login.
     * @param evt El evento de acción.
     */
    private void cerrarsesionBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cerrarsesionBtnActionPerformed
        new Login().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_cerrarsesionBtnActionPerformed



    // Variables declaration - do not modify//GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - Issael
    private JPanel jPanel1;
    private JLabel userIcon;
    private JPanel jPanel3;
    private JButton inicioBtn;
    private JButton perfilBtn;
    private JLabel nombreDoctortxt;
    private JPanel jPanel4;
    private JButton cerrarsesionBtn;
    private JLabel userIcon1;
    private JPanel jPanel2;
    private JLabel favicon1;
    private JLabel apellidoTXT;
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
    private JLabel nombreTXT;
    private JLabel passwordTXT;
    private JLabel nacimientoTXT;
    private JLabel dniTXT;
    private JLabel usernameTXT;
    private JLabel telefonoTXT;
    private JLabel correoLabel1;
    private JSeparator div8;
    private JLabel direccionTXT;
    private JLabel nombreLabel9;
    private JLabel especialidadTXT;
    private JLabel apellidosLabel1;
    private JSeparator div9;
    // End of variables declaration//GEN-END:variables
}
