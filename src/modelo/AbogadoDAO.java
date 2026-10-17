/*
    UNIVERSIDAD DE LAS FUERZAS ARMADAS ESPE
            INGENIERIA DE SOFTWARE
NOMBRE:Enrique Ortega                            PERIODO:202451
ASIGNATURA:Programacion Orientada a Objetos      NRC:1967
FECHA:31-01-2025
Tema: Clase Abogado donde se realizara la solicitud hacia la tabla dentro
de la base de datos.

 */
package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AbogadoDAO {

    public List<Abogado> obtenerAbogados() {
        List<Abogado> abogados = new ArrayList<>();
        String query = "SELECT * FROM Abogado a INNER JOIN Usuario u ON a.id = u.id WHERE u.rol = 'Abogado'";

        try (Connection conexion = ConexionBD.conectar(); PreparedStatement statement = conexion.prepareStatement(query); ResultSet resultado = statement.executeQuery()) {

            while (resultado.next()) {
                Abogado abogado = new Abogado(
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
                abogados.add(abogado);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return abogados;
    }

    //METODO PARA ASIGNAR ABOGADO POR ID
    // Método para obtener un Abogado por ID
    public Abogado obtenerAbogadoPorId(int id) {
        String query = "SELECT u.id, u.nombre, u.apellido, u.cedula, u.email, u.telefono, u.user, u.password, "
                + "a.registro, a.especialidad, a.experiencia, a.honorarioHora "
                + "FROM Abogado a "
                + "INNER JOIN Usuario u ON a.id = u.id "
                + "WHERE a.id = ?";
        try (Connection conexion = ConexionBD.conectar(); PreparedStatement statement = conexion.prepareStatement(query)) {
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

    public boolean agregarAbogado(Abogado abogado) {
        String query = "INSERT INTO Abogado (registro, especialidad, experiencia, honorarioHora, id) VALUES (?, ?, ?, ?, ?)";
        String queryUsuario = "INSERT INTO Usuario (nombre, apellido, cedula, email, telefono, user, password, rol) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conexion = ConexionBD.conectar(); PreparedStatement statementAbogado = conexion.prepareStatement(query); PreparedStatement statementUsuario = conexion.prepareStatement(queryUsuario)) {

            // Configurar los parámetros del statement para la tabla Usuario
            statementUsuario.setString(1, abogado.getNombre());
            statementUsuario.setString(2, abogado.getApellido());
            statementUsuario.setString(3, abogado.getCedula());
            statementUsuario.setString(4, abogado.getEmail());
            statementUsuario.setString(5, abogado.getTelefono());
            statementUsuario.setString(6, abogado.getUser());
            statementUsuario.setString(7, abogado.getPassword());
            statementUsuario.setString(8, "Abogado"); // Establecer el rol como Abogado

            // Ejecutar la inserción en la tabla Usuario
            int filasAfectadasUsuario = statementUsuario.executeUpdate();

            // Obtener el ID del usuario recién insertado
            int idUsuario = obtenerUltimoIdUsuario(conexion);

            // Configurar los parámetros del statement para la tabla Abogado
            statementAbogado.setString(1, abogado.getRegistro());
            statementAbogado.setString(2, abogado.getEspecialidad());
            statementAbogado.setInt(3, abogado.getExperiencia());
            statementAbogado.setDouble(4, abogado.getHonorarioHora());
            statementAbogado.setInt(5, idUsuario); // Usar el ID del usuario recién insertado

            // Ejecutar la inserción en la tabla Abogado
            int filasAfectadasAbogado = statementAbogado.executeUpdate();

            // Verificar si ambas inserciones fueron exitosas
            return filasAfectadasUsuario > 0 && filasAfectadasAbogado > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    private int obtenerUltimoIdUsuario(Connection conexion) throws SQLException {
        String query = "SELECT MAX(id) FROM Usuario";
        try (PreparedStatement statement = conexion.prepareStatement(query); ResultSet resultado = statement.executeQuery()) {
            if (resultado.next()) {
                return resultado.getInt(1);
            }
        }
        return 0; // O manejar el caso en que no hay usuarios
    }

    //Metodo para eliminar abogado
    public boolean eliminarAbogado(int idAbogado) {
        String queryAbogado = "DELETE FROM Abogado WHERE id = ?";
        String queryUsuario = "DELETE FROM Usuario WHERE id = ?";

        try (Connection conexion = ConexionBD.conectar(); PreparedStatement statementAbogado = conexion.prepareStatement(queryAbogado); PreparedStatement statementUsuario = conexion.prepareStatement(queryUsuario)) {

            // Desactivar el auto-commit para asegurar la atomicidad
            conexion.setAutoCommit(false);

            // Eliminar primero de la tabla Abogado
            statementAbogado.setInt(1, idAbogado);
            int filasAfectadasAbogado = statementAbogado.executeUpdate();

            // Eliminar luego de la tabla Usuario
            statementUsuario.setInt(1, idAbogado);
            int filasAfectadasUsuario = statementUsuario.executeUpdate();

            // Si ambas eliminaciones fueron exitosas, confirmar la transacción
            if (filasAfectadasAbogado > 0 && filasAfectadasUsuario > 0) {
                conexion.commit();
                return true;
            } else {
                // Si alguna eliminación falló, deshacer la transacción
                conexion.rollback();
                return false;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean actualizarAbogado(Abogado abogado) {
        String queryAbogado = "UPDATE Abogado SET registro = ?, especialidad = ?, experiencia = ?, honorarioHora = ? WHERE id = ?";
        String queryUsuario = "UPDATE Usuario SET nombre = ?, apellido = ?, cedula = ?, email = ?, telefono = ?, user = ?, password = ? WHERE id = ?";

        try (Connection conexion = ConexionBD.conectar(); PreparedStatement statementAbogado = conexion.prepareStatement(queryAbogado); PreparedStatement statementUsuario = conexion.prepareStatement(queryUsuario)) {

            // Configurar los parámetros del statement para la tabla Usuario
            statementUsuario.setString(1, abogado.getNombre());
            statementUsuario.setString(2, abogado.getApellido());
            statementUsuario.setString(3, abogado.getCedula());
            statementUsuario.setString(4, abogado.getEmail());
            statementUsuario.setString(5, abogado.getTelefono());
            statementUsuario.setString(6, abogado.getUser());
            statementUsuario.setString(7, abogado.getPassword());
            statementUsuario.setInt(8, abogado.getId());

            // Configurar los parámetros del statement para la tabla Abogado
            statementAbogado.setString(1, abogado.getRegistro());
            statementAbogado.setString(2, abogado.getEspecialidad());
            statementAbogado.setInt(3, abogado.getExperiencia());
            statementAbogado.setDouble(4, abogado.getHonorarioHora());
            statementAbogado.setInt(5, abogado.getId());

            // Ejecutar la actualización en la tabla Usuario
            int filasAfectadasUsuario = statementUsuario.executeUpdate();

            // Ejecutar la actualización en la tabla Abogado
            int filasAfectadasAbogado = statementAbogado.executeUpdate();

            // Verificar si ambas actualizaciones fueron exitosas
            return filasAfectadasUsuario > 0 && filasAfectadasAbogado > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}
