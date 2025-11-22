package com.gestioncitas_trabajofinal.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Gestiona la conexión a la base de datos utilizando el patrón de diseño Singleton.
 * Esto asegura que solo exista una única instancia de la conexión en toda la aplicación,
 * mejorando el rendimiento y la gestión de recursos.
 */
public class ConexionBD {

    private static final String URL = "jdbc:mysql://localhost:3306/gestion_citas";
    private static final String USER = "root";
    private static final String PASSWORD = "admin"; // Varia por la BD
    
    // 1. Variable estática y privada para guardar la única instancia de la conexión.
    private static Connection connection = null;

    /**
     * Constructor privado para evitar que se creen instancias de esta clase desde fuera.
     * Esto es fundamental para el patrón Singleton.
     */
    private ConexionBD() {
    }

    /**
     * Devuelve la única instancia de la conexión a la base de datos.
     * Si la conexión no ha sido creada o está cerrada, la inicializa.
     * Si ya existe, simplemente la devuelve.
     * * @return La instancia única de la conexión (Connection).
     */
    public static Connection getConecction() {
        // 2. Comprueba si la conexión es nula o si se ha cerrado.
        try {
            if (connection == null || connection.isClosed()) {
                try {
                    // 3. Si no existe, la crea una sola vez.
                    connection = DriverManager.getConnection(URL, USER, PASSWORD);
                    System.out.println("INFO: Conexión a la base de datos establecida exitosamente.");
                } catch (SQLException e) {
                    System.err.println("ERROR: No se pudo conectar a la base de datos.");
                    e.printStackTrace();
                    throw new RuntimeException("Error al conectar a la base de datos", e);
                }
            }
        } catch (SQLException e) {
            System.err.println("ERROR: Error al comprobar el estado de la conexión.");
            e.printStackTrace();
        }
        // 4. Devuelve la instancia existente o la recién creada.
        return connection;
    }
    
    /**
     * Cierra la conexión a la base de datos si está abierta.
     * Es crucial llamar a este método cuando la aplicación se va a cerrar
     * para liberar los recursos de la base de datos correctamente.
     */
    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                connection = null; // Se pone a null para permitir una posible reconexión.
                System.out.println("INFO: Conexión a la base de datos cerrada.");
            } catch (SQLException e) {
                System.err.println("ERROR: Error al cerrar la conexión.");
                e.printStackTrace();
            }
        }
    }
}