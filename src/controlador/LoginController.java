/*
    UNIVERSIDAD DE LAS FUERZAS ARMADAS ESPE
            INGENIERIA DE SOFTWARE
NOMBRE:Enrique Ortega                            PERIODO:202451
ASIGNATURA:Programacion Orientada a Objetos      NRC:1967
FECHA:31-01-2025
Tema: Clase Controlador Login controla los enentos e interacciones que tiene 
la vista Login... sirve para manejar los registros
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

public class LoginController implements Initializable {
    //Inicilizar notificaciones
    NotificacionesController notificacion = new NotificacionesController();
    

    //inicializar los componentes del formulario
    @FXML
    private TextField txtEmail;
    @FXML
    private PasswordField txtPassword;
    @FXML
    private Button btnLogin;
    @FXML
    private Hyperlink linkSignUp;

    // Constructor vacío (soluciona problemas de carga en algunos casos)
    public LoginController() {
        System.out.println("LoginController cargado correctamente.");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("Inicializando controlador...");
    }

    //agrega funcionalidad a los botones
    @FXML
    private void handleLogin() {
        String email = txtEmail.getText();
        String password = txtPassword.getText();
        try {

            if (email.equals("admin") && password.equals("admin")) {
                System.out.println("Inicio de sesión exitoso");
                notificacion.mostrarInformacion("ACCESO CONCEDIDO", "Bienvenido: " + txtEmail.getText(), "");
                FlujoController escenas = new FlujoController((Stage) btnLogin.getScene().getWindow());
                escenas.indicarDashAdmi();

            } else {
                System.out.println("Correo o contraseña incorrectos");
                notificacion.mostrarError("ERROR", "ERROR DE CREDENCIALES", "Usuario o Clave INCORRECTA"
                        + " vuelva a intentarlo");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void goToSignUp() {
        System.out.println("Redirigiendo a Registro...");

        try {
            FlujoController escenas = new FlujoController((Stage) linkSignUp.getScene().getWindow());
            escenas.indicarRegistro(); // va a registro cliente
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
