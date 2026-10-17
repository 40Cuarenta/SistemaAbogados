/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controlador;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import modelo.Cliente;
import modelo.ClienteDAO;

import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;
import modelo.Abogado;

public class PaginaClienteController implements Initializable {

    @FXML
    private TableView<Cliente> tablaClientes;

    @FXML
    private TableColumn<Cliente, Integer> idC;

    @FXML
    private TableColumn<Cliente, String> numeroCasoC;

    @FXML
    private TableColumn<Cliente, String> descripcionCasoC;

    @FXML
    private TableColumn<Cliente, String> estadoCasoC;

    @FXML
    private TableColumn<Cliente, LocalDate> fechaRegistroC;

    @FXML
    private TableColumn<Cliente, Integer> abogadoAsignadoC;

    @FXML
    private TableColumn<Cliente, String> notasAdicionalesC;


    private ClienteDAO clienteDAO = new ClienteDAO();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        configurarColumnas();
        cargarDatos();
    }

    private void configurarColumnas() {
        idC.setCellValueFactory(new PropertyValueFactory<>("id"));
        numeroCasoC.setCellValueFactory(new PropertyValueFactory<>("numeroCaso"));
        descripcionCasoC.setCellValueFactory(new PropertyValueFactory<>("descripcionCaso"));
        estadoCasoC.setCellValueFactory(new PropertyValueFactory<>("estadoCaso"));
        fechaRegistroC.setCellValueFactory(new PropertyValueFactory<>("fechaRegistro"));
        abogadoAsignadoC.setCellValueFactory(new PropertyValueFactory<>("abogadoAsignadoId"));
        notasAdicionalesC.setCellValueFactory(new PropertyValueFactory<>("notasAdicionales"));
    }

    private void cargarDatos() {
        List<Cliente> listaClientes = clienteDAO.obtenerClientes();
        ObservableList<Cliente> observableList = FXCollections.observableArrayList(listaClientes);
        tablaClientes.setItems(observableList);
    }
}

