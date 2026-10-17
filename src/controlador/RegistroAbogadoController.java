package controlador;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import modelo.Abogado;
import modelo.AbogadoDAO;
import javafx.scene.control.Button;
import javafx.fxml.Initializable; // Import this
import java.net.URL;  // Import this
import java.util.ResourceBundle; // Import this
import javafx.scene.control.Alert; // Import this
import javafx.scene.control.Alert.AlertType; // Import this

public class RegistroAbogadoController implements Initializable { // Implement Initializable

    @FXML
    private TextField txtId;
    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtApellido;
    @FXML
    private TextField txtUser;
    @FXML
    private TextField txtPassword1;
    @FXML
    private TextField txtPasswordActive;
    @FXML
    private TextField txtCedula;
    @FXML
    private TextField txtEmail;
    @FXML
    private TextField txtTelefono;
    @FXML
    private TextField txtRegistro;
    @FXML
    private TextField txtEspecialidad;
    @FXML
    private TextField txtExperiencia;
    @FXML
    private TextField txtHonorarioHora;
    @FXML
    private Button btnGuardar;

    private Abogado abogado;
    private AbogadoDAO abogadoDAO = new AbogadoDAO();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Initialization code here (if needed)
    }

    public void setAbogado(Abogado abogado) {
       try{ this.abogado = abogado;

        if (abogado != null) {
            // Cargar los datos del abogado en el formulario
            txtId.setText(String.valueOf(abogado.getId()));
            txtNombre.setText(abogado.getNombre());
            txtApellido.setText(abogado.getApellido());
            txtUser.setText(abogado.getUser());
            txtPassword1.setText(abogado.getPassword());
            txtPasswordActive.setText(abogado.getPassword());
            txtCedula.setText(abogado.getCedula());
            txtEmail.setText(abogado.getEmail());
            txtTelefono.setText(abogado.getTelefono());
            txtRegistro.setText(abogado.getRegistro());
            txtEspecialidad.setText(abogado.getEspecialidad());
            txtExperiencia.setText(String.valueOf(abogado.getExperiencia()));
            txtHonorarioHora.setText(String.valueOf(abogado.getHonorarioHora()));

            btnGuardar.setText("Actualizar");
        }}
           catch (NullPointerException e) {
               System.out.println("Error: " + e.getMessage());
           }
    }

    @FXML
    void agregar(ActionEvent event) {
        if (txtNombre.getText().isEmpty() || txtApellido.getText().isEmpty() || txtUser.getText().isEmpty() ||
            txtPassword1.getText().isEmpty() || txtPasswordActive.getText().isEmpty() || txtCedula.getText().isEmpty() ||
            txtEmail.getText().isEmpty() || txtTelefono.getText().isEmpty() || txtRegistro.getText().isEmpty() ||
            txtEspecialidad.getText().isEmpty() || txtExperiencia.getText().isEmpty() || txtHonorarioHora.getText().isEmpty()) {

            mostrarDialogoAdvertencia("Por favor, complete todos los campos.");  // Alerta en español
            return;
        }

        int id = (abogado != null) ? abogado.getId() : 0;
        String nombre = txtNombre.getText();
        String apellido = txtApellido.getText();
        String cedula = txtCedula.getText();
        String email = txtEmail.getText();
        String telefono = txtTelefono.getText();
        String user = txtUser.getText();
        String password = txtPassword1.getText();
        String registro = txtRegistro.getText();
        String especialidad = txtEspecialidad.getText();
        int experiencia = Integer.parseInt(txtExperiencia.getText());
        double honorarioHora = Double.parseDouble(txtHonorarioHora.getText());

        Abogado abogadoActualizado = new Abogado(id, nombre, apellido, cedula, email, telefono, user, password, registro, especialidad, experiencia, honorarioHora);

        boolean resultado;
        if (abogado != null) {
            resultado = abogadoDAO.actualizarAbogado(abogadoActualizado);
        } else {
            resultado = abogadoDAO.agregarAbogado(abogadoActualizado);
        }

        if (resultado) {
            mostrarDialogoInformacion("Abogado guardado correctamente."); // Alerta en español
            cancelar(event);
        } else {
            mostrarDialogoError("Error al guardar el abogado.");// Alerta en español
        }
    }

    @FXML
    void cancelar(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

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
}
