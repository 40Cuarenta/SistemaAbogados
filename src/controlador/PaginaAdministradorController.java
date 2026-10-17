/*
    UNIVERSIDAD DE LAS FUERZAS ARMADAS ESPE
            INGENIERIA DE SOFTWARE
NOMBRE:Enrique Ortega                            PERIODO:202451
ASIGNATURA:Programacion Orientada a Objetos      NRC:1967
FECHA:31-01-2025
Tema: Clase PaginaAdministradorController donde se controla los eventos y acciones
de los botones.

*/
package controlador;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import modelo.Administrador;
import modelo.AdministradorDAO;

import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

public class PaginaAdministradorController implements Initializable {

    @FXML
    private TableView<Administrador> tablaAdministradores;

    @FXML
    private TableColumn<Administrador, Integer> idC;

    @FXML
    private TableColumn<Administrador, String> nivelAccesoC;

    @FXML
    private TableColumn<Administrador, LocalDate> fechaIngresoC; // Cambiado a LocalDate

    @FXML
    private TableColumn<Administrador, Boolean> estadoCuentaC;
    
    private AdministradorDAO administradorDAO = new AdministradorDAO();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        configurarColumnas();
        cargarDatos();
    }

    private void configurarColumnas() {
        idC.setCellValueFactory(new PropertyValueFactory<>("id"));
        nivelAccesoC.setCellValueFactory(new PropertyValueFactory<>("nivelAcceso"));
        fechaIngresoC.setCellValueFactory(new PropertyValueFactory<>("fechaIngreso"));
        estadoCuentaC.setCellValueFactory(new PropertyValueFactory<>("estadoCuenta"));
    }

    private void cargarDatos() {
        List<Administrador> listaAdministradores = administradorDAO.obtenerAdministradores();
        ObservableList<Administrador> observableList = FXCollections.observableArrayList(listaAdministradores);
        tablaAdministradores.setItems(observableList);
    }
}


