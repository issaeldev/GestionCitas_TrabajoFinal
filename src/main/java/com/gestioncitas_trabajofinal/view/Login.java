package com.gestioncitas_trabajofinal.view;

import java.awt.*;
import javax.swing.*;
import javax.swing.GroupLayout;
import com.gestioncitas_trabajofinal.controller.LoginController;
import com.gestioncitas_trabajofinal.model.Medico;
import com.gestioncitas_trabajofinal.model.Paciente;
import com.gestioncitas_trabajofinal.model.Usuario;
import com.gestioncitas_trabajofinal.strategy.MedicoLoginStrategy;
import com.gestioncitas_trabajofinal.strategy.PacienteLoginStrategy;
import com.gestioncitas_trabajofinal.service.LoginContext;

import javax.swing.JOptionPane;

/**
 * Ventana principal de inicio de sesión de la aplicación.
 * Permite a los usuarios (pacientes y médicos) autenticarse para acceder al sistema.
 * * @author lumic
 */
public class Login extends javax.swing.JFrame {
    
    private final LoginController loginController;

    /**
     * Constructor que inicializa los componentes de la ventana y las dependencias.
     */
    public Login() {
        initComponents();
        this.loginController = new LoginController();
        setLocationRelativeTo(null);
    }
    

    /**
     * Este método es llamado desde el constructor para inicializar el form.
     * ADVERTENCIA: No modificar este código. El contenido de este método es siempre
     * regenerado por el Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    // Generated using JFormDesigner Evaluation license - unknown
    private void initComponents() {
        jPanel1 = new JPanel();
        logo = new JLabel();
        logoName = new JLabel();
        favicon = new JLabel();
        title = new JLabel();
        userLabel = new JLabel();
        userTxt = new JTextField();
        div1 = new JSeparator();
        passLabel = new JLabel();
        passTxt = new JPasswordField();
        div2 = new JSeparator();
        ingresarBtn = new JButton();
        registrarseBtn = new JButton();
        background = new JLabel();

        //======== this ========
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        var contentPane = getContentPane();

        //======== jPanel1 ========
        {
            jPanel1.setBorder (new javax. swing. border. CompoundBorder( new javax .swing .border .TitledBorder (new javax. swing. border. EmptyBorder(
            0, 0, 0, 0) , "JF\u006frmDes\u0069gner \u0045valua\u0074ion", javax. swing. border. TitledBorder. CENTER, javax. swing. border. TitledBorder
            . BOTTOM, new java .awt .Font ("D\u0069alog" ,java .awt .Font .BOLD ,12 ), java. awt. Color.
            red) ,jPanel1. getBorder( )) ); jPanel1. addPropertyChangeListener (new java. beans. PropertyChangeListener( ){ @Override public void propertyChange (java .
            beans .PropertyChangeEvent e) {if ("\u0062order" .equals (e .getPropertyName () )) throw new RuntimeException( ); }} );
            jPanel1.setLayout(null);

            //---- logo ----
            logo.setHorizontalAlignment(SwingConstants.CENTER);
            logo.setIcon(new ImageIcon(getClass().getResource("/View/imagenes/logo.png")));
            jPanel1.add(logo);
            logo.setBounds(0, 110, 280, 150);

            //---- logoName ----
            logoName.setHorizontalAlignment(SwingConstants.CENTER);
            logoName.setText("CitaSmart");
            jPanel1.add(logoName);
            logoName.setBounds(0, 250, 280, logoName.getPreferredSize().height);

            //---- favicon ----
            favicon.setIcon(new ImageIcon(getClass().getResource("/View/imagenes/favicon.png")));
            favicon.setText("CitaSmart");
            jPanel1.add(favicon);
            favicon.setBounds(310, 30, 130, favicon.getPreferredSize().height);

            //---- title ----
            title.setHorizontalAlignment(SwingConstants.CENTER);
            title.setText("INICIAR SESI\u00d3N");
            jPanel1.add(title);
            title.setBounds(280, 150, 470, title.getPreferredSize().height);

            //---- userLabel ----
            userLabel.setText("CORREO ELECTRONICO");
            jPanel1.add(userLabel);
            userLabel.setBounds(350, 210, 190, userLabel.getPreferredSize().height);

            //---- userTxt ----
            userTxt.setBorder(null);
            userTxt.addActionListener(e -> userTxtActionPerformed(e));
            jPanel1.add(userTxt);
            userTxt.setBounds(350, 240, 320, 30);
            jPanel1.add(div1);
            div1.setBounds(350, 270, 320, div1.getPreferredSize().height);

            //---- passLabel ----
            passLabel.setText("CONTRASE\u00d1A");
            jPanel1.add(passLabel);
            passLabel.setBounds(350, 300, 190, passLabel.getPreferredSize().height);

            //---- passTxt ----
            passTxt.setBorder(null);
            jPanel1.add(passTxt);
            passTxt.setBounds(350, 330, 320, 30);
            jPanel1.add(div2);
            div2.setBounds(350, 360, 320, div2.getPreferredSize().height);

            //---- ingresarBtn ----
            ingresarBtn.setText("INGRESAR");
            ingresarBtn.setBorderPainted(false);
            ingresarBtn.addActionListener(e -> ingresarBtnActionPerformed(e));
            jPanel1.add(ingresarBtn);
            ingresarBtn.setBounds(460, 390, 100, 30);

            //---- registrarseBtn ----
            registrarseBtn.setText("REGISTRARSE");
            registrarseBtn.setBorder(null);
            registrarseBtn.setBorderPainted(false);
            registrarseBtn.addActionListener(e -> registrarseBtnActionPerformed(e));
            jPanel1.add(registrarseBtn);
            registrarseBtn.setBounds(460, 430, 100, 20);

            //---- background ----
            background.setIcon(new ImageIcon(getClass().getResource("/View/imagenes/background2.jpg")));
            jPanel1.add(background);
            background.setBounds(0, 0, 280, 570);

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

    
    private void userTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_userTxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_userTxtActionPerformed

    /**
     * Maneja el evento de clic en el botón 'Registrarse'.
     * Abre la ventana de registro y cierra la ventana actual.
     * @param evt El evento de acción.
     */
    private void registrarseBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registrarseBtnActionPerformed
        new Register().setVisible(true);
        this.dispose();         
    }//GEN-LAST:event_registrarseBtnActionPerformed
    
    /**
     * Maneja el evento de clic en el botón 'Ingresar'.
     * Intenta autenticar al usuario y lo redirige según su rol.
     * @param evt El evento de acción.
     */
    private void ingresarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ingresarBtnActionPerformed
        realizarLogin();
    }//GEN-LAST:event_ingresarBtnActionPerformed

    // ------------------- MÉTODOS PRIVADOS DE AYUDA -------------------

    /**
     * Contiene la lógica para autenticar al usuario y redirigirlo.
     */
    private void realizarLogin() {
        String correo = userTxt.getText().trim();
        String password = new String(passTxt.getPassword()).trim();

        if (correo.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, ingrese correo y contraseña.", "Campos vacíos", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Usuario usuario = loginController.login(correo, password);

        if (usuario != null) {
            redirigirUsuario(usuario);
            this.dispose(); // Cierra la ventana de login
        } else {
            JOptionPane.showMessageDialog(this, "Credenciales incorrectas. Por favor, intente de nuevo.", "Error de autenticación", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Redirige al usuario a su panel correspondiente basado en su rol.
     * @param usuario El usuario autenticado.
     */
    private void redirigirUsuario(Usuario usuario) {
        switch (usuario.getRol()) {
            case PACIENTE -> {
                if (usuario instanceof Paciente paciente) {
                    // Creamos un LoginContext específico para Paciente
                    LoginContext<Paciente> pacienteContext = new LoginContext<>();
                    pacienteContext.setStrategy(new PacienteLoginStrategy());
                    pacienteContext.ejecutarLogin(paciente);
                }
            }
            case MEDICO -> {
                if (usuario instanceof Medico medico) {
                    // Creamos un LoginContext específico para Medico
                    LoginContext<Medico> medicoContext = new LoginContext<>();
                    medicoContext.setStrategy(new MedicoLoginStrategy());
                    medicoContext.ejecutarLogin(medico);
                }
            }
            default -> JOptionPane.showMessageDialog(this, "Rol de usuario no reconocido.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - unknown
    private JPanel jPanel1;
    private JLabel logo;
    private JLabel logoName;
    private JLabel favicon;
    private JLabel title;
    private JLabel userLabel;
    private JTextField userTxt;
    private JSeparator div1;
    private JLabel passLabel;
    private JPasswordField passTxt;
    private JSeparator div2;
    private JButton ingresarBtn;
    private JButton registrarseBtn;
    private JLabel background;
    // End of variables declaration//GEN-END:variables
}
