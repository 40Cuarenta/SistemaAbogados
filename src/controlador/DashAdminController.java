/*
    UNIVERSIDAD DE LAS FUERZAS ARMADAS ESPE
            INGENIERÍA DE SOFTWARE
NOMBRE: Enrique Ortega                            PERIODO: 202451
ASIGNATURA: Programación Orientada a Objetos      NRC: 1967
FECHA: 31-01-2025
Tema: Clase Controlador DashAdminController 
      - Formulario para administrador.
      - Permite agregar un nuevo administrador y abogados a la firma.
      - Realiza consultas en MySQL.
 */
package controlador;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

public class DashAdminController {

    @FXML
    private AnchorPane ap;

    @FXML
    private BorderPane bp;

    @FXML
    private Button btnAbogado;

    @FXML
    private Button btnAdministrador;

    @FXML
    private Button btnCasos;

    @FXML
    private Button btnCerrar;

    @FXML
    private Button btnClientes;

    @FXML
    private Button btnOpciones;

    @FXML
    private Button btnPrincipal;

    @FXML
    private Button btnUsuario;

    @FXML
    void cerrarSesion(MouseEvent event) {
        
    }

    @FXML
    void paginaAbogado(MouseEvent event) {
        CargarPagina("PaginaAbogado");
    }

    @FXML
    void paginaAdmin(MouseEvent event) {
        CargarPagina("PaginaAdministrador");
    }

    @FXML
    void paginaCasos(MouseEvent event) {
        //CargarPagina("PaginaCasos");
        
    }

    @FXML
    void paginaClientes(MouseEvent event) {
        CargarPagina("PaginaCliente");
    }

    @FXML
    void paginaOpciones(MouseEvent event) {
        CargarPagina("PaginaOpciones");
    }

    @FXML
    void paginaPrincipal(MouseEvent event) {
        bp.setCenter(ap); // Muestra el panel principal
    }

    @FXML
    void paginaUsuario(MouseEvent event) {
        CargarPagina("PaginaUsuarios");
    }
    
    // Método para cargar páginas en el BorderPane
    private void CargarPagina(String pagina) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/" + pagina + ".fxml"));
            Parent root = loader.load();
            if (root != null) {
                bp.setCenter(root);
            } else {
                Logger.getLogger(DashAdminController.class.getName()).log(Level.SEVERE, 
                    "No se pudo cargar la página: " + pagina + ".fxml (El archivo no existe o está mal escrito).");
            }
        } catch (IOException ex) {
            Logger.getLogger(DashAdminController.class.getName()).log(Level.SEVERE, 
                "Error al cargar la página: " + pagina + ".fxml. Verifica la ruta y el nombre del archivo.", ex);
        }
    }
}
