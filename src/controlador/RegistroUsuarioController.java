/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.Stage;
import modelo.Usuario;
import modelo.UsuarioDAO;

public class RegistroUsuarioController {

    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtApellido;
    @FXML
    private TextField txtUser;
    @FXML
    private PasswordField txtPassword1;
    @FXML
    private PasswordField txtPasswordActive;
    @FXML
    private TextField txtCedula;
    @FXML
    private TextField txtEmail;
    @FXML
    private TextField txtTelefono;

    @FXML
    private ComboBox<String> rolComboBox;

    private UsuarioDAO usuarioDAO = new UsuarioDAO();
    private boolean edicion = false; // Indica si se está editando un usuario existente
    private Usuario usuarioAEditar; // Almacena el usuario a editar

    @FXML
    public void initialize() {
        // Inicializa el ComboBox con las opciones de rol
        ObservableList<String> roles = FXCollections.observableArrayList("Administrador", "Abogado", "Cliente");
        rolComboBox.setItems(roles);
    }

    @FXML
    void agregar(ActionEvent event) {
        // Validar campos
        if (txtNombre.getText().isEmpty() || txtApellido.getText().isEmpty() || txtUser.getText().isEmpty()
                || txtPassword1.getText().isEmpty() || txtPasswordActive.getText().isEmpty() || txtCedula.getText().isEmpty()
                || txtEmail.getText().isEmpty() || txtTelefono.getText().isEmpty() || rolComboBox.getValue() == null) {
            mostrarDialogoAdvertencia("Por favor, complete todos los campos.");
            return;
        }

        // Validar que las contraseñas coincidan
        if (!txtPassword1.getText().equals(txtPasswordActive.getText())) {
            mostrarDialogoAdvertencia("Las contraseñas no coinciden.");
            return;
        }

        // Crear un nuevo objeto Usuario con los datos del formulario
        Usuario nuevoUsuario = new Usuario(
                0, // La base de datos genera el ID automáticamente
                txtNombre.getText(),
                txtApellido.getText(),
                txtCedula.getText(),
                txtEmail.getText(),
                txtTelefono.getText(),
                txtUser.getText(),
                txtPassword1.getText(),
                rolComboBox.getValue()
        );
    

        // Agregar el usuario a la base de datos
        boolean resultado = usuarioDAO.agregarUsuario(nuevoUsuario);

        if (resultado) {
            mostrarDialogoInformacion("Usuario guardado correctamente.");
            cancelar(event); // Cerrar el formulario después de guardar

        } else {
            mostrarDialogoError("Error al guardar el usuario.");
        }
    }

    public void setEdicion(boolean edicion) {
        this.edicion = edicion; // Establecer si estamos en modo edición
    }

    public void setUsuarioAEditar(Usuario usuario) {
        this.usuarioAEditar = usuario; // Almacenar el usuario a editar y llenar los campos del formulario
        txtNombre.setText(usuario.getNombre());
        txtApellido.setText(usuario.getApellido());
        txtUser.setText(usuario.getUser());
        txtPassword1.setText(usuario.getPassword());
        txtCedula.setText(usuario.getCedula());
        txtEmail.setText(usuario.getEmail());
        txtTelefono.setText(usuario.getTelefono());
        rolComboBox.setValue(usuario.getRol());
    }

    @FXML
    void cancelar(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow(); // Obtener la ventana actual
        stage.close(); // Cerrar la ventana del formulario
    }

    private void mostrarDialogoAdvertencia(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Advertencia");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    private void mostrarDialogoInformacion(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Información");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    private void mostrarDialogoError(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}

