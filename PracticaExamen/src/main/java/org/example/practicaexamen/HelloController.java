package org.example.practicaexamen;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class HelloController implements Initializable {

    @FXML private TextField       txtNombre;
    @FXML private TextField       txtTelefono;
    @FXML private ComboBox<String> cmbParentesco;
    @FXML private ListView<Contacto> listViewContactos;
    @FXML private Label           lblMensaje;

    private final String[] PARENTESCOS = {
            "Padre", "Madre", "Hermano", "Hermana",
            "Abuelo", "Abuela", "Tío", "Tía"
    };

    private final List<Contacto>         listaContactos      = new ArrayList<>();
    private       ObservableList<Contacto> observableContactos;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cmbParentesco.getItems().addAll(PARENTESCOS);

        observableContactos = FXCollections.observableArrayList(listaContactos);
        listViewContactos.setItems(observableContactos);

        lblMensaje.setText("");
    }

    @FXML
    private void agregarContacto() {
        String nombre     = txtNombre.getText().trim();
        String telefono   = txtTelefono.getText().trim();
        String parentesco = cmbParentesco.getValue();

        if (!validarCampos(nombre, telefono, parentesco)) return;

        if (existeNombre(nombre)) {
            mostrarMensaje("Ya existe un contacto con ese nombre.", true);
            return;
        }

        listaContactos.add(new Contacto(nombre, telefono, parentesco));
        refrescarListView();
        limpiarCampos();
        mostrarMensaje("Contacto agregado correctamente.", false);
    }

    @FXML
    private void buscarContacto() {
        String nombre = txtNombre.getText().trim();

        if (nombre.isEmpty()) {
            mostrarMensaje("Escribe un nombre para buscar.", true);
            return;
        }

        Contacto encontrado = buscarPorNombre(nombre);

        if (encontrado != null) {
            txtNombre.setText(encontrado.getNombre());
            txtTelefono.setText(encontrado.getTelefono());
            cmbParentesco.setValue(encontrado.getParentesco());
            mostrarMensaje("Contacto encontrado.", false);
        } else {
            mostrarMensaje("No se encontró ningún contacto con ese nombre.", true);
        }
    }

    @FXML
    private void actualizarContacto() {
        String nombre     = txtNombre.getText().trim();
        String telefono   = txtTelefono.getText().trim();
        String parentesco = cmbParentesco.getValue();

        if (!validarCampos(nombre, telefono, parentesco)) return;

        Contacto encontrado = buscarPorNombre(nombre);

        if (encontrado != null) {
            encontrado.setTelefono(telefono);
            encontrado.setParentesco(parentesco);
            refrescarListView();
            limpiarCampos();
            mostrarMensaje("Contacto actualizado correctamente.", false);
        } else {
            mostrarMensaje("No se encontró el contacto para actualizar.", true);
        }
    }

    @FXML
    private void eliminarContacto() {
        String nombre = txtNombre.getText().trim();

        if (nombre.isEmpty()) {
            mostrarMensaje("Escribe el nombre del contacto a eliminar.", true);
            return;
        }

        Contacto encontrado = buscarPorNombre(nombre);

        if (encontrado != null) {
            listaContactos.remove(encontrado);
            refrescarListView();
            limpiarCampos();
            mostrarMensaje("Contacto eliminado correctamente.", false);
        } else {
            mostrarMensaje("No se encontró el contacto para eliminar.", true);
        }
    }

    @FXML
    private void limpiarCampos() {
        txtNombre.clear();
        txtTelefono.clear();
        cmbParentesco.setValue(null);
        listViewContactos.getSelectionModel().clearSelection();
        mostrarMensaje("", false);
    }



    private Contacto buscarPorNombre(String nombre) {
        for (Contacto c : listaContactos) {
            if (c.getNombre().equalsIgnoreCase(nombre)) return c;
        }
        return null;
    }


    private boolean existeNombre(String nombre) {
        return buscarPorNombre(nombre) != null;
    }

    private boolean validarCampos(String nombre, String telefono, String parentesco) {
        if (nombre.isEmpty()) {
            mostrarMensaje("El nombre no puede estar vacío.", true);
            return false;
        }
        if (telefono.isEmpty()) {
            mostrarMensaje("El teléfono no puede estar vacío.", true);
            return false;
        }
        if (!telefono.matches("\\d{10}")) {
            mostrarMensaje("El teléfono debe tener exactamente 10 dígitos.", true);
            return false;
        }
        if (parentesco == null || parentesco.isEmpty()) {
            mostrarMensaje("Debes seleccionar un parentesco.", true);
            return false;
        }
        return true;
    }

    private void refrescarListView() {
        observableContactos.setAll(listaContactos);
    }

    private void mostrarMensaje(String texto, boolean esError) {
        lblMensaje.setText(texto);
        lblMensaje.setStyle(esError
                ? "-fx-text-fill: #c0392b; -fx-font-weight: bold;"
                : "-fx-text-fill: #27ae60; -fx-font-weight: bold;");
    }
}