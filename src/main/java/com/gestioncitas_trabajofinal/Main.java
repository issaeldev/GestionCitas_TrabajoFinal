package com.gestioncitas_trabajofinal;

import com.gestioncitas_trabajofinal.view.Login;
import com.gestioncitas_trabajofinal.db.ConexionBD;
import com.gestioncitas_trabajofinal.view.medico.HomeMedico;
import com.gestioncitas_trabajofinal.model.Medico;
/**
 * Clase principal que inicia la aplicación de gestión de citas.
 * Es el punto de entrada del programa y se encarga de lanzar la ventana de login,
 * además de asegurar que la conexión a la base de datos se cierre correctamente
 * al finalizar la ejecución.
 */
public class Main {

    /**
     * Método principal que se ejecuta al iniciar la aplicación.
     * @param args Argumentos de la línea de comandos (no se utilizan en este proyecto).
     */
    public static void main(String[] args) {

        // Se registra un "shutdown hook", que es un hilo que se ejecuta automáticamente
        // justo antes de que la aplicación se cierre. Es el lugar ideal para tareas
        // de limpieza, como cerrar la conexión a la base de datos.
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("INFO: Cerrando la aplicación, desconectando de la base de datos...");
            ConexionBD.closeConnection(); // Llama al método estático para cerrar la conexión única.
        }));

        // Se utiliza EventQueue.invokeLater para asegurar que la interfaz gráfica
        // de Swing se inicie y se maneje en su propio hilo (Event Dispatch Thread).
        // Esta es la forma recomendada y más segura de iniciar aplicaciones Swing.
        java.awt.EventQueue.invokeLater(() -> {
            // Crea una instancia de la ventana de Login y la hace visible.
           new Login().setVisible(true);

        });
    }
}