package com.example.turnoslectura.controller;

import com.example.turnoslectura.model.TurnoLectura;
import com.example.turnoslectura.service.TurnoLecturaService;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.util.List;

public class MainController {

    @FXML
    private TextField txtNombreAlumno;
    @FXML
    private TextField txtLibro;
    @FXML
    private ComboBox<String> cbTurno;
    @FXML
    private ListView<String> lvRegistros;

    private final TurnoLecturaService service = new TurnoLecturaService();
    private String nombreOriginal;

    @FXML
    public void initialize() {
        cargarTurnos();
        actualizarLista();
        lvRegistros.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                cargarSeleccion(newValue);
            }
        });
    }

    private void cargarTurnos() {
        String[] turnos = service.obtenerTurnos();
        for (int i = 0; i < turnos.length; i++) {
            cbTurno.getItems().add(turnos[i]);
        }
    }

    // ✅ CÓDIGO AGREGADO
    @FXML
    public void agregar() {
        String nombre = txtNombreAlumno.getText();
        String libro = txtLibro.getText();
        String turno = cbTurno.getValue();

        String error = service.agregar(nombre, libro, turno);

        if (error != null) {
            mostrarMensaje("Error", error, Alert.AlertType.ERROR);
            return;
        }
        actualizarLista();
        limpiar();
    }
    // ✅ FIN CÓDIGO AGREGADO

    @FXML
    public void buscar() {
        TurnoLectura registro = service.buscarPorNombreAlumno(txtNombreAlumno.getText());
        if (registro == null) {
            mostrarMensaje("Aviso", "Registro no encontrado", Alert.AlertType.WARNING);
            return;
        }
        txtNombreAlumno.setText(registro.getNombreAlumno());
        txtLibro.setText(registro.getLibro());
        cbTurno.setValue(registro.getTurno());
        nombreOriginal = registro.getNombreAlumno();
    }

    // ✅ CÓDIGO AGREGADO
    @FXML
    public void actualizar() {
        if (nombreOriginal == null) {
            mostrarMensaje("Aviso", "Primero busca o selecciona un registro", Alert.AlertType.WARNING);
            return;
        }
        String nombreNuevo = txtNombreAlumno.getText();
        String libroNuevo = txtLibro.getText();
        String turnoNuevo = cbTurno.getValue();

        String error = service.actualizar(nombreOriginal, nombreNuevo, libroNuevo, turnoNuevo);

        if (error != null) {
            mostrarMensaje("Error", error, Alert.AlertType.ERROR);
            return;
        }
        actualizarLista();
        limpiar();
    }
    // ✅ FIN CÓDIGO AGREGADO

    // ✅ CÓDIGO AGREGADO
    @FXML
    public void eliminar() {
        String nombre = txtNombreAlumno.getText();

        String error = service.eliminar(nombre);

        if (error != null) {
            mostrarMensaje("Error", error, Alert.AlertType.ERROR);
            return;
        }
        actualizarLista();
        limpiar();
    }
    // ✅ FIN CÓDIGO AGREGADO

    @FXML
    public void limpiar() {
        txtNombreAlumno.clear();
        txtLibro.clear();
        cbTurno.setValue(null);
        lvRegistros.getSelectionModel().clearSelection();
        nombreOriginal = null;
    }

    private void actualizarLista() {
        lvRegistros.getItems().clear();
        List<TurnoLectura> registros = service.obtenerTodos();
        for (int i = 0; i < registros.size(); i++) {
            lvRegistros.getItems().add(registros.get(i).toString());
        }
    }

    private void cargarSeleccion(String textoSeleccionado) {
        List<TurnoLectura> registros = service.obtenerTodos();
        for (int i = 0; i < registros.size(); i++) {
            TurnoLectura actual = registros.get(i);
            if (actual.toString().equals(textoSeleccionado)) {
                txtNombreAlumno.setText(actual.getNombreAlumno());
                txtLibro.setText(actual.getLibro());
                cbTurno.setValue(actual.getTurno());
                nombreOriginal = actual.getNombreAlumno();
                break;
            }
        }
    }

    private void mostrarMensaje(String titulo, String mensaje, Alert.AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}