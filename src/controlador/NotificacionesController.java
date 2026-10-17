/*
    UNIVERSIDAD DE LAS FUERZAS ARMADAS ESPE
            INGENIERIA DE SOFTWARE
NOMBRE:Enrique Ortega                            PERIODO:202451
ASIGNATURA:Programacion Orientada a Objetos      NRC:1967
FECHA:31-01-2025
Tema: Clase Controlador notificacion tiene metodos para instanciar dialogos hacia
el usuario.
*/
package controlador;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextInputDialog;

import java.util.Optional;

public class NotificacionesController {

    /** 
     * Muestra un diálogo de información al usuario.
     * @param title Título del diálogo.
     * @param header Encabezado del diálogo.
     * @param content Contenido del mensaje.
     */
    public void mostrarInformacion(String title, String header, String content) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }

    /** 
     * Muestra un diálogo de advertencia al usuario.
     * @param title Título del diálogo.
     * @param header Encabezado del diálogo.
     * @param content Contenido del mensaje.
     */
    public void mostrarAdvertencia(String title, String header, String content) {
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }

    /** 
     * Muestra un diálogo de error al usuario.
     * @param title Título del diálogo.
     * @param header Encabezado del diálogo.
     * @param content Contenido del mensaje.
     */
    public void mostrarError(String title, String header, String content) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }

    /** 
     * Muestra un diálogo de entrada para que el usuario ingrese información.
     * @param title Título del diálogo.
     * @param header Encabezado del diálogo.
     * @return El texto ingresado por el usuario o null si se cancela.
     */
    public String mostrarInput(String title, String header) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle(title);
        dialog.setHeaderText(header);
        dialog.setContentText("Por favor ingresa tu respuesta:");

        Optional<String> result = dialog.showAndWait();
        return result.orElse(null); // Retorna el valor ingresado o null si se cancela
    }
}
