package controlador;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import modelo.Usuario;
import modelo.UsuarioDAO;

public class PaginaUsuariosController implements Initializable {

    @FXML
    private TableView<Usuario> tablaUsuarios;
    @FXML
    private TableColumn<Usuario, Integer> idC;
    @FXML
    private TableColumn<Usuario, String> userC;
    @FXML
    private TableColumn<Usuario, String> claveC;
    @FXML
    private TableColumn<Usuario, String> nombreC;
    @FXML
    private TableColumn<Usuario, String> ApellidoC;
    @FXML
    private TableColumn<Usuario, String> cedulaC;
    @FXML
    private TableColumn<Usuario, String> emailC;
    @FXML
    private TableColumn<Usuario, String> telefonoC;
    @FXML
    private TableColumn<Usuario, String> rolC;
    @FXML
    private Button btnActualizar;
    @FXML
    private Button btnAgregar;
    @FXML
    private Button btnEliminar;

    private UsuarioDAO usuarioDAO = new UsuarioDAO();
    private ObservableList<Usuario> listaUsuarios;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        configurarColumnas();
        cargarDatos();
    }

    private void configurarColumnas() {
        idC.setCellValueFactory(new PropertyValueFactory<>("id"));
        userC.setCellValueFactory(new PropertyValueFactory<>("user"));
        claveC.setCellValueFactory(new PropertyValueFactory<>("password"));
        nombreC.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        ApellidoC.setCellValueFactory(new PropertyValueFactory<>("apellido"));
        cedulaC.setCellValueFactory(new PropertyValueFactory<>("cedula"));
        emailC.setCellValueFactory(new PropertyValueFactory<>("email"));
        telefonoC.setCellValueFactory(new PropertyValueFactory<>("telefono"));
        rolC.setCellValueFactory(new PropertyValueFactory<>("rol"));
    }

    private void cargarDatos() {
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        List<Usuario> listaUsuarios = usuarioDAO.obtenerUsuarios();
        ObservableList<Usuario> observableList = FXCollections.observableArrayList(listaUsuarios);
        tablaUsuarios.setItems(observableList);
        System.out.println("Carga de usuarios correctamente");
    }

    @FXML
    void agregar(MouseEvent event) {
        // Aquí debes implementar la lógica para agregar un nuevo usuario
        try {
            // Cargar el archivo FXML del formulario
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/RegistroUsuario.fxml"));
            Parent root = loader.load();

            // Crear una nueva escena
            Scene scene = new Scene(root);

            // Crear una nueva ventana (Stage)
            Stage stage = new Stage();
            stage.setTitle("Agregar Nuevo Usuario");
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL); // Bloquea la ventana principal

            // Mostrar la ventana
            stage.showAndWait();

            // Recargar datos después de agregar
            cargarDatos();
        } catch (Exception e) {
            e.printStackTrace();
            mostrarDialogoError("Error al cargar el formulario.");
        }
    }

    @FXML
    void actualizar(MouseEvent event) {
        // Aquí debes implementar la lógica para actualizar un usuario existente
        System.out.println("Actualizar usuario");
        Usuario usuarioSeleccionado = tablaUsuarios.getSelectionModel().getSelectedItem();

        if (usuarioSeleccionado != null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/RegistroUsuario.fxml"));
                Parent root = loader.load();

                RegistroUsuarioController controller = loader.getController();
                controller.setEdicion(true); // Establecer el modo de edición
                controller.setUsuarioAEditar(usuarioSeleccionado); // Cargar los datos del usuario seleccionado

                Stage stage = new Stage();
                stage.setTitle("Editar Usuario");
                stage.setScene(new Scene(root));
                stage.initModality(Modality.APPLICATION_MODAL);

                stage.showAndWait();

                cargarDatos(); // Recargar los datos en la tabla después de la edición
            } catch (Exception e) {
                e.printStackTrace();
                mostrarDialogoError("Error al cargar el formulario de edición.");
            }
        } else {
            mostrarDialogoAdvertencia("Por favor, seleccione un usuario para editar.");
        }
    }

    @FXML
    void eliminar(MouseEvent event) {
        Usuario usuarioSeleccionado = tablaUsuarios.getSelectionModel().getSelectedItem();
        if (usuarioSeleccionado != null) {
            boolean confirmacion = mostrarDialogoConfirmacion("¿Está seguro de que desea eliminar este usuario?");
            if (confirmacion) {
                boolean eliminado = usuarioDAO.eliminarUsuario(usuarioSeleccionado.getId());
                if (eliminado) {
                    mostrarDialogoInformacion("Usuario eliminado correctamente.");
                    cargarDatos(); // Recargar datos después de eliminar
                } else {
                    mostrarDialogoError("Error al eliminar el usuario.");
                }
            }
        } else {
            mostrarDialogoAdvertencia("Por favor, seleccione un usuario para eliminar.");
        }
    }

    // Métodos para mostrar diálogos
    private void mostrarDialogoAdvertencia(String mensaje) {
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle("Advertencia");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    private void mostrarDialogoInformacion(String mensaje) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Información");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    private void mostrarDialogoError(String mensaje) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    private boolean mostrarDialogoConfirmacion(String mensaje) {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirmación");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);

        java.util.Optional<ButtonType> result = alert.showAndWait();
        return result.isPresent() && result.get() == ButtonType.OK;
    }
}
