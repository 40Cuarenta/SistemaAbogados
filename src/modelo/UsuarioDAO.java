/*
    UNIVERSIDAD DE LAS FUERZAS ARMADAS ESPE
            INGENIERIA DE SOFTWARE
NOMBRE:Enrique Ortega                            PERIODO:202451
ASIGNATURA:Programacion Orientada a Objetos      NRC:1967
FECHA:31-01-2025
Tema: Clase UsuarioDAO Una clase que obtiene los datos de la base de datos:
 */
package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {

    public List<Usuario> obtenerUsuarios() {
        List<Usuario> usuarios = new ArrayList<>();
        String query = "SELECT * FROM Usuario";

        try (Connection conexion = ConexionBD.conectar(); PreparedStatement statement = conexion.prepareStatement(query); ResultSet resultado = statement.executeQuery()) {

            while (resultado.next()) {
                Usuario usuario = new Usuario(
                        resultado.getInt("id"),
                        resultado.getString("nombre"),
                        resultado.getString("apellido"),
                        resultado.getString("cedula"),
                        resultado.getString("email"),
                        resultado.getString("telefono"),
                        resultado.getString("user"),
                        resultado.getString("password"),
                        resultado.getString("rol")
                );
                usuarios.add(usuario);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return usuarios;
    }

    public boolean agregarUsuario(Usuario usuario) {
        String query = "INSERT INTO Usuario (nombre, apellido, cedula, email, telefono, user, password, rol) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conexion = ConexionBD.conectar(); PreparedStatement statement = conexion.prepareStatement(query)) {
            statement.setString(1, usuario.getNombre());
            statement.setString(2, usuario.getApellido());
            statement.setString(3, usuario.getCedula());
            statement.setString(4, usuario.getEmail());
            statement.setString(5, usuario.getTelefono());
            statement.setString(6, usuario.getUser());
            statement.setString(7, usuario.getPassword());
            statement.setString(8, usuario.getRol());

            int filasAfectadas = statement.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Método para actualizar un usuario
    public boolean actualizarUsuario(Usuario usuario) {
        String query = "UPDATE Usuario SET nombre=?, apellido=?, cedula=?, email=?, telefono=?, user=?, password=?, rol=? WHERE id=?";
        try (Connection conexion = ConexionBD.conectar(); PreparedStatement statement = conexion.prepareStatement(query)) {
            statement.setString(1, usuario.getNombre());
            statement.setString(2, usuario.getApellido());
            statement.setString(3, usuario.getCedula());
            statement.setString(4, usuario.getEmail());
            statement.setString(5, usuario.getTelefono());
            statement.setString(6, usuario.getUser());
            statement.setString(7, usuario.getPassword());
            statement.setString(8, usuario.getRol());
            statement.setInt(9, usuario.getId()); // Importante: Establecer el ID para la cláusula WHERE

            int filasAfectadas = statement.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Método para eliminar un usuario
    public boolean eliminarUsuario(int id) {
        String query = "DELETE FROM Usuario WHERE id=?";
        try (Connection conexion = ConexionBD.conectar(); PreparedStatement statement = conexion.prepareStatement(query)) {
            statement.setInt(1, id);

            int filasAfectadas = statement.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
