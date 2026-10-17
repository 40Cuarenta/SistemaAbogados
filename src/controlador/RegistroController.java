/*
    UNIVERSIDAD DE LAS FUERZAS ARMADAS ESPE
            INGENIERIA DE SOFTWARE
NOMBRE:Enrique Ortega                            PERIODO:202451
ASIGNATURA:Programacion Orientada a Objetos      NRC:1967
FECHA:31-01-2025
Tema: Clase Controlador registro controla los enentos e interacciones que tiene 
la el registro Login... sirve para registrar a un cliente
*/
package controlador;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author enriqueo
 */
public class RegistroController implements Initializable {

    //variables
    //inicializar los componentes del formulario
    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtApellido;
    @FXML
    private TextField txtCedula;
    @FXML
    private TextField txtEmail;
    @FXML
    private TextField txtUser;
    @FXML
    private PasswordField txtPassword1;
    @FXML
    private PasswordField txtPasswordActive;
    @FXML
    private TextField txtTelefono;
    @FXML
    private Button btnRegistro;
    @FXML
    private Button btnCancelar;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    //agrega funcionalidad a los botones
    //Metodo para registrar a un cliente 
    @FXML
     private void Registro(){
         NotificacionesController notificar = new NotificacionesController();
         notificar.mostrarInformacion("REGISTRO DE CLIENTE EXITOSO", "BIENVENIDO A LEXGESTION", 
                 "Se ha registrado correctamente el usuario: "+ txtUser.getText());
         
     }
     @FXML
     private void Cancelar(){
         System.out.println("Redirigiendo a Registro...");

        try {
            FlujoController escenas = new FlujoController((Stage) btnCancelar.getScene().getWindow());
            escenas.indicarLogin(); // va a registro cliente
        } catch (Exception e) {
            e.printStackTrace();
        }
     }
}
