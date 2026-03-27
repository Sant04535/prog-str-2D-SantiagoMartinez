package com.example.turnoslectura.service;

import com.example.turnoslectura.model.TurnoLectura;
import com.example.turnoslectura.repository.TurnoLecturaRepository;

import java.util.List;

public class TurnoLecturaService {
    private final TurnoLecturaRepository repository = new TurnoLecturaRepository();
    private final String[] turnos = {"Mañana", "Tarde"};

    public String[] obtenerTurnos() {
        return turnos;
    }

    public List<TurnoLectura> obtenerTodos() {
        return repository.obtenerTodos();
    }

    public TurnoLectura buscarPorNombreAlumno(String nombreAlumno) {
        if (nombreAlumno == null || nombreAlumno.trim().isEmpty()) {
            return null;
        }
        return repository.buscarPorNombreAlumno(nombreAlumno.trim());
    }

    // ✅ CÓDIGO AGREGADO
    public String agregar(String nombreAlumno, String libro, String turno) {
        if (nombreAlumno == null || nombreAlumno.trim().isEmpty()) {
            return "El nombre del alumno no puede estar vacío";
        }
        if (libro == null || libro.trim().isEmpty()) {
            return "El libro no puede estar vacío";
        }
        if (turno == null) {
            return "Debes seleccionar un turno";
        }
        if (repository.buscarPorNombreAlumno(nombreAlumno.trim()) != null) {
            return "Ya existe un registro con ese nombre";
        }
        TurnoLectura nuevo = new TurnoLectura(nombreAlumno.trim(), libro.trim(), turno);
        repository.guardar(nuevo);
        return null;
    }
    // ✅ FIN CÓDIGO AGREGADO

    // ✅ CÓDIGO AGREGADO
    public String actualizar(String nombreOriginal, String nombreNuevo, String libroNuevo, String turnoNuevo) {
        if (nombreOriginal == null || nombreOriginal.trim().isEmpty()) {
            return "No hay ningún registro seleccionado";
        }
        TurnoLectura registro = repository.buscarPorNombreAlumno(nombreOriginal);
        if (registro == null) {
            return "Registro no encontrado";
        }
        if (nombreNuevo == null || nombreNuevo.trim().isEmpty()) {
            return "El nombre del alumno no puede estar vacío";
        }
        if (libroNuevo == null || libroNuevo.trim().isEmpty()) {
            return "El libro no puede estar vacío";
        }
        if (turnoNuevo == null) {
            return "Debes seleccionar un turno";
        }
        if (!nombreNuevo.trim().equalsIgnoreCase(nombreOriginal)) {
            if (repository.buscarPorNombreAlumno(nombreNuevo.trim()) != null) {
                return "Ya existe un registro con ese nombre";
            }
        }
        registro.setNombreAlumno(nombreNuevo.trim());
        registro.setLibro(libroNuevo.trim());
        registro.setTurno(turnoNuevo);
        return null;
    }
    // ✅ FIN CÓDIGO AGREGADO

    // ✅ CÓDIGO AGREGADO
    public String eliminar(String nombreAlumno) {
        if (nombreAlumno == null || nombreAlumno.trim().isEmpty()) {
            return "El nombre del alumno no puede estar vacío";
        }
        boolean eliminado = repository.eliminarPorNombreAlumno(nombreAlumno.trim());
        if (!eliminado) {
            return "No se encontró ningún registro con ese nombre";
        }
        return null;
    }
    // ✅ FIN CÓDIGO AGREGADO
}