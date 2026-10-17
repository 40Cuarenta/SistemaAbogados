package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {

    public List<Cliente> obtenerClientes() {
        List<Cliente> clientes = new ArrayList<>();
        String query = "SELECT u.id, u.nombre, u.apellido, u.cedula, u.email, u.telefono, u.user, u.password, " +
                "c.numeroCaso, c.descripcionCaso, c.estadoCaso, c.fechaRegistro, c.abogadoAsignado, c.notasAdicionales " +
                "FROM Cliente c " +
                "INNER JOIN Usuario u ON c.id = u.id " +
                "WHERE u.rol = 'Cliente'";

        try (Connection conexion = ConexionBD.conectar();
             PreparedStatement statement = conexion.prepareStatement(query);
             ResultSet resultado = statement.executeQuery()) {

            while (resultado.next()) {
                // Recuperar datos del resultado
                int id = resultado.getInt("id");
                String nombre = resultado.getString("nombre");
                String apellido = resultado.getString("apellido");
                String cedula = resultado.getString("cedula");
                String email = resultado.getString("email");
                String telefono = resultado.getString("telefono");
                String user = resultado.getString("user");
                String password = resultado.getString("password");
                String numeroCaso = resultado.getString("numeroCaso");
                String descripcionCaso = resultado.getString("descripcionCaso");
                String estadoCaso = resultado.getString("estadoCaso");
                LocalDate fechaRegistro = resultado.getObject("fechaRegistro", LocalDate.class);
                int abogadoAsignadoId = resultado.getInt("abogadoAsignado"); // Asumiendo que la columna almacena el ID del abogado
                String notasAdicionales = resultado.getString("notasAdicionales");

                // Obtener el Abogado Asignado (si existe)
                Abogado abogadoAsignado = null;
                if (abogadoAsignadoId > 0) {
                    abogadoAsignado = obtenerAbogadoPorId(conexion, abogadoAsignadoId); // Usar la conexión existente
                }

                Cliente cliente = new Cliente(id, nombre, apellido, cedula, email, telefono, user, password,
                        numeroCaso, descripcionCaso, estadoCaso, fechaRegistro, abogadoAsignado, notasAdicionales);
                clientes.add(cliente);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return clientes;
    }

    // Método para obtener un Abogado por ID utilizando la conexión existente
    public Abogado obtenerAbogadoPorId(Connection conexion, int id) {
        String query = "SELECT u.id, u.nombre, u.apellido, u.cedula, u.email, u.telefono, u.user, u.password, " +
                "a.registro, a.especialidad, a.experiencia, a.honorarioHora " +
                "FROM Abogado a " +
                "INNER JOIN Usuario u ON a.id = u.id " +
                "WHERE a.id = ?";
        try (PreparedStatement statement = conexion.prepareStatement(query)) { // Usar la conexión existente
            statement.setInt(1, id);
            ResultSet resultado = statement.executeQuery();

            if (resultado.next()) {
                return new Abogado(
                    resultado.getInt("id"),
                    resultado.getString("nombre"),
                    resultado.getString("apellido"),
                    resultado.getString("cedula"),
                    resultado.getString("email"),
                    resultado.getString("telefono"),
                    resultado.getString("user"),
                    resultado.getString("password"),
                    resultado.getString("registro"),
                    resultado.getString("especialidad"),
                    resultado.getInt("experiencia"),
                    resultado.getDouble("honorarioHora")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
