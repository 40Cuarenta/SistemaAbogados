/*
    UNIVERSIDAD DE LAS FUERZAS ARMADAS ESPE
            INGENIERIA DE SOFTWARE
NOMBRE: Enrique Ortega                            PERIODO: 202451
ASIGNATURA: Programación Orientada a Objetos      NRC: 1967
FECHA: 31-01-2025
Tema: Clase ConexionBD que sirve para conectar a la base de datos
incluye:
      - Metodos para conectar y desconectar base de datos
      - Advertencias
 */
package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {
    // Atributos de conexión
    private static final String URL = "jdbc:mysql://localhost:3306/SistemaJudicial?serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = "170702Enrique";
    
    // Variable para mantener la conexión
    private static Connection conn = null;

    // Método para conectar a la base de datos
    public static Connection conectar() {
        try {
            if (conn == null || conn.isClosed()) {
                conn = DriverManager.getConnection(URL, USER, PASSWORD);
                System.out.println("Conexión exitosa a MySQL.");
            }
        } catch (SQLException e) {
            System.out.println("Error al conectar: " + e.getMessage());
        }
        return conn;
    }
    // Método para cerrar la conexión
    public static void cerrarConexion() {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
                System.out.println("Conexión cerrada.");
            }
        } catch (SQLException e) {
            System.out.println("Error al cerrar la conexión: " + e.getMessage());
        }
    }
}

