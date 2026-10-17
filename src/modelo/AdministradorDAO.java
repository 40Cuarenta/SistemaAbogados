/*
    UNIVERSIDAD DE LAS FUERZAS ARMADAS ESPE
            INGENIERIA DE SOFTWARE
NOMBRE:Enrique Ortega                            PERIODO:202451
ASIGNATURA:Programacion Orientada a Objetos      NRC:1967
FECHA:31-01-2025
Tema: Clase AdministradorDAO donde puede hacer consultas en la base de datos
*/
package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AdministradorDAO {

    public List<Administrador> obtenerAdministradores() {
        List<Administrador> administradores = new ArrayList<>();
        String query = "SELECT u.id, u.nombre, u.apellido, u.cedula, u.email, u.telefono, u.user, u.password, " +
                       "a.nivelAcceso, a.fechaIngreso, a.estadoCuenta " +
                       "FROM Administrador a " +
                       "INNER JOIN Usuario u ON a.id = u.id " +
                       "WHERE u.rol = 'Administrador'";

        try (Connection conexion = ConexionBD.conectar();
             PreparedStatement statement = conexion.prepareStatement(query);
             ResultSet resultado = statement.executeQuery()) {

            while (resultado.next()) {
                Administrador administrador = new Administrador(
                        resultado.getInt("id"),
                        resultado.getString("nombre"),
                        resultado.getString("apellido"),
                        resultado.getString("cedula"),
                        resultado.getString("email"),
                        resultado.getString("telefono"),
                        resultado.getString("user"),
                        resultado.getString("password"),
                        resultado.getString("nivelAcceso"),
                        resultado.getObject("fechaIngreso", LocalDate.class), // Cambiado a LocalDate
                        resultado.getBoolean("estadoCuenta")
                );
                administradores.add(administrador);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return administradores;
    }
}


