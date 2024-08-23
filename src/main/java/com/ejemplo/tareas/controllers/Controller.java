package com.ejemplo.tareas.controllers;

import com.ejemplo.tareas.models.*;
import com.ejemplo.tareas.repository.CodigoRepository;
import com.ejemplo.tareas.repository.EstudianteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class Controller {

    @Autowired
    private EstudianteRepository estudianteRepository;

    @Autowired
    private CodigoRepository codigoRepository;

    @GetMapping("estudiantes")
    public List<Estudiante> getEstudiantes(){
        return estudianteRepository.findAll();
    }

    @GetMapping("/estudiante/{estudianteId}")
    public ResponseEntity<List<Codigo>> obtenerCodigosPorEstudiante(@PathVariable Long estudianteId) {
        List<Codigo> codigosFuente = codigoRepository.findAllByEstudianteId(estudianteId);
        return ResponseEntity.ok(codigosFuente);
    }

    @PostMapping("/estudiante/{estudianteId}")
    public ResponseEntity<Codigo> subirCodigoFuente(@PathVariable Long estudianteId, @RequestBody Codigo codigoFuente) {
        return estudianteRepository.findById(estudianteId).map(estudiante -> {
            // Asignar el estudiante al código fuente
            codigoFuente.setEstudiante(estudiante);

            // Asegurar que la calificación sea null si no está presente en la solicitud
            if (codigoFuente.getCalificacion() == null) {
                codigoFuente.setCalificacion(null);
            }

            // Guardar el nuevo código fuente
            Codigo nuevoCodigoFuente = codigoRepository.save(codigoFuente);
            return ResponseEntity.ok(nuevoCodigoFuente);
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/codigo/{codigoId}/calificar")
    public ResponseEntity<Codigo> calificarCodigo(@PathVariable Long codigoId, @RequestBody Long calificacion) {
        return codigoRepository.findById(codigoId).map(codigo -> {
            codigo.setCalificacion(calificacion);
            Codigo codigoActualizado = codigoRepository.save(codigo);
            return ResponseEntity.ok(codigoActualizado);
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/codigo/{codigoId}/calificacion")
    public ResponseEntity<Long> obtenerCalificacion(@PathVariable Long codigoId) {
        return codigoRepository.findById(codigoId).map(codigo -> {
            Long calificacion = codigo.getCalificacion();
            return ResponseEntity.ok(calificacion);
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }
}