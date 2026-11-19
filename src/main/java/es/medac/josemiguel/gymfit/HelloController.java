package es.medac.josemiguel.gymfit;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

import java.util.Optional;

public class HelloController {

    @FXML private TextField txtNombre;
    @FXML private TextField txtApellido1;
    @FXML private TextField txtApellido2;
    @FXML private DatePicker dpFechaNacimiento;
    @FXML private ToggleGroup grupoSexo;
    @FXML private ComboBox<String> cbProvincia;
    @FXML private ToggleGroup grupoCuota;
    @FXML private ComboBox<String> cbClase;
    @FXML private TextField txtIban;
    @FXML private Label lblEstado;

    // Se ejecuta al abrir la pantalla: rellena los desplegables
    @FXML
    public void initialize() {
        cbProvincia.getItems().addAll("A Coruña", "Álava", "Albacete", "Alicante", "Almería", "Asturias",
                "Ávila", "Badajoz", "Barcelona", "Burgos", "Cáceres", "Cádiz", "Cantabria", "Castellón",
                "Ceuta", "Ciudad Real", "Córdoba", "Cuenca", "Girona", "Granada", "Guadalajara", "Guipúzcoa",
                "Huelva", "Huesca", "Illes Balears", "Jaén", "La Rioja", "Las Palmas", "León", "Lleida",
                "Lugo", "Madrid", "Málaga", "Melilla", "Murcia", "Navarra", "Ourense", "Palencia",
                "Pontevedra", "Salamanca", "Santa Cruz de Tenerife", "Segovia", "Sevilla", "Soria",
                "Tarragona", "Teruel", "Toledo", "Valencia", "Valladolid", "Vizcaya", "Zamora", "Zaragoza");

        cbClase.getItems().addAll("Aquagym", "Body Pump", "Boxeo", "Crossfit", "Funcional",
                "Pilates", "Spinning", "Step", "Yoga", "Zumba");
    }

    // Botón "Dar de alta": comprueba los datos obligatorios
    @FXML
    private void darDeAlta() {
        if (txtNombre.getText().isBlank()) {
            mostrarError("Falta el nombre del socio", "Escríbelo en el campo «Nombre». Ejemplo: Laura");
            return;
        }
        if (txtApellido1.getText().isBlank()) {
            mostrarError("Falta el primer apellido", "Escríbelo en el campo «Primer apellido». Ejemplo: Gómez");
            return;
        }
        if (dpFechaNacimiento.getValue() == null) {
            mostrarError("Falta la fecha de nacimiento", "Selecciónala en el calendario o escríbela así: 14/05/2003");
            return;
        }
        if (cbProvincia.getValue() == null) {
            mostrarError("Falta la provincia", "Elígela en el desplegable «Provincia».");
            return;
        }
        if (grupoCuota.getSelectedToggle() == null) {
            mostrarError("Falta el tipo de cuota", "Marca una de las cuotas. Ejemplo: Joven");
            return;
        }
        if (cbClase.getValue() == null) {
            mostrarError("Falta la clase", "Elígela en el desplegable «Clase».");
            return;
        }

        String nombreCompleto = txtNombre.getText() + " " + txtApellido1.getText() + " " + txtApellido2.getText();

        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle("Alta completada");
        alerta.setHeaderText("Socio dado de alta correctamente");
        alerta.setContentText(nombreCompleto + " ya es socio de GymFit.");
        alerta.showAndWait();

        lblEstado.setText("Último alta: " + nombreCompleto);
        vaciarCampos();
    }

    // Botón "Limpiar formulario": pide confirmación antes de borrar
    @FXML
    private void limpiarFormulario() {
        Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
        alerta.setTitle("Limpiar formulario");
        alerta.setHeaderText("¿Quieres borrar los datos del formulario?");
        alerta.setContentText("Se perderá todo lo que has escrito.");

        ButtonType cancelar = new ButtonType("Cancelar", ButtonBar.ButtonData.CANCEL_CLOSE);
        ButtonType borrar = new ButtonType("Borrar datos", ButtonBar.ButtonData.OK_DONE);
        alerta.getButtonTypes().setAll(cancelar, borrar);

        // Cancelar es el botón por defecto, para no borrar sin querer pulsando Enter
        ((Button) alerta.getDialogPane().lookupButton(borrar)).setDefaultButton(false);
        ((Button) alerta.getDialogPane().lookupButton(cancelar)).setDefaultButton(true);

        Optional<ButtonType> respuesta = alerta.showAndWait();
        if (respuesta.isPresent() && respuesta.get() == borrar) {
            vaciarCampos();
            lblEstado.setText("Formulario limpio");
        }
    }

    // Mensaje de error: qué ha pasado y cómo solucionarlo
    private void mostrarError(String queFalla, String comoSolucionarlo) {
        Alert alerta = new Alert(Alert.AlertType.WARNING);
        alerta.setTitle("Revisa el formulario");
        alerta.setHeaderText(queFalla);
        alerta.setContentText(comoSolucionarlo);
        alerta.getButtonTypes().setAll(new ButtonType("Corregir", ButtonBar.ButtonData.OK_DONE));
        alerta.showAndWait();
    }

    private void vaciarCampos() {
        txtNombre.clear();
        txtApellido1.clear();
        txtApellido2.clear();
        dpFechaNacimiento.setValue(null);
        dpFechaNacimiento.getEditor().clear();
        grupoSexo.selectToggle(null);
        cbProvincia.getSelectionModel().clearSelection();
        grupoCuota.selectToggle(null);
        cbClase.getSelectionModel().clearSelection();
        txtIban.clear();
    }
}