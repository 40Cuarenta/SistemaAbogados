/*
    UNIVERSIDAD DE LAS FUERZAS ARMADAS ESPE
            INGENIERIA DE SOFTWARE
NOMBRE:Enrique Ortega                            PERIODO:202451
ASIGNATURA:Programacion Orientada a Objetos      NRC:1967
FECHA:31-01-2025
Tema: Clase Controlador Flujo controla la instanciacion de ventanas
 */
package controlador;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class FlujoController {
    private Stage stage;

    public FlujoController(Stage stage) {
        this.stage = stage;
    }

    public void indicarLogin() throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/vista/Login.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("SISTEMA JUDICIAL LEXGESTION");
        stage.show();
    }

    public void indicarRegistro() throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/vista/Registro.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("REGISTRO DE CLIENTE DE LEXGESTION");
        stage.show();
    }
    public void indicarDashAdmi() throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/vista/DashAdmin.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("ADMINISTRADOR DEL SISTEMA JUDICIAL LEXGESTION");
        stage.show();
    }
}

