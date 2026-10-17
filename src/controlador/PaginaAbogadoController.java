package controlador;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import modelo.Abogado;
import modelo.AbogadoDAO;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class PaginaAbogadoController implements Initializable {

    @FXML
    private TableView<Abogado> tablaAbogados;

    @FXML
    private TableColumn<Abogado, Integer> idC;

    @FXML
    private TableColumn<Abogado, String> registroC;

    @FXML
    private TableColumn<Abogado, String> especialidadC;

    @FXML
    private TableColumn<Abogado, Integer> experienciaC;

    @FXML
    private TableColumn<Abogado, Double> honorarioHoraC;

    private AbogadoDAO abogadoDAO = new AbogadoDAO();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        configurarColumnas();
        cargarDatos();
    }

    private void configurarColumnas() {
        idC.setCellValueFactory(new PropertyValueFactory<>("id"));
        registroC.setCellValueFactory(new PropertyValueFactory<>("registro"));
        especialidadC.setCellValueFactory(new PropertyValueFactory<>("especialidad"));
        experienciaC.setCellValueFactory(new PropertyValueFactory<>("experiencia"));
        honorarioHoraC.setCellValueFactory(new PropertyValueFactory<>("honorarioHora"));
    }

    private void cargarDatos() {
        List<Abogado> listaAbogados = abogadoDAO.obtenerAbogados();
        ObservableList<Abogado> observableList = FXCollections.observableArrayList(listaAbogados);
        tablaAbogados.setItems(observableList);
    }

    @FXML
    void agregarAbogado(MouseEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/RegistroAbogado.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle("Agregar Abogado");
            stage.setScene(new Scene(root));
            stage.initModality(Modality.APPLICATION_MODAL);

            stage.showAndWait();
            cargarDatos();
        } catch (IOException e) {
            mostrarDialogoError("Error al cargar el formulario de agregar abogado: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    void editarAbogado(MouseEvent event) {
        Abogado abogadoSeleccionado = tablaAbogados.getSelectionModel().getSelectedItem();

        if (abogadoSeleccionado == null) {
            mostrarDialogoAdvertencia("Por favor, seleccione un abogado para editar.");
            return;
        }

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/RegistroAbogado.fxml"));
            Parent root = loader.load();

            RegistroAbogadoController controller = loader.getController();
            controller.setAbogado(abogadoSeleccionado);

            Stage stage = new Stage();
            stage.setTitle("Editar Abogado");
            stage.setScene(new Scene(root));
            stage.initModality(Modality.APPLICATION_MODAL);

            stage.showAndWait();
            cargarDatos();
        } catch (IOException e) {
            mostrarDialogoError("Error al cargar el formulario de edición: " + e.getMessage());
            e.printStackTrace();
        }
    }


    @FXML
    void eliminarAbogado(MouseEvent event) {
        Abogado abogadoSeleccionado = tablaAbogados.getSelectionModel().getSelectedItem();

        if (abogadoSeleccionado == null) {
            mostrarDialogoAdvertencia("Por favor, seleccione un abogado para eliminar.");
            return;
        }

        if (mostrarDialogoConfirmacion("¿Está seguro de que desea eliminar este abogado?")) {
            boolean resultado = abogadoDAO.eliminarAbogado(abogadoSeleccionado.getId());

            if (resultado) {
                mostrarDialogoInformacion("Abogado eliminado correctamente.");
                cargarDatos();
            } else {
                mostrarDialogoError("Error al eliminar al abogado.");
            }
        }
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

    private boolean mostrarDialogoConfirmacion(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmación");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);

        Optional<ButtonType> result = alert.showAndWait();
        return result.isPresent() && result.get() == ButtonType.OK;
    }
}
