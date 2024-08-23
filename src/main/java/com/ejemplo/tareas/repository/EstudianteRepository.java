package com.ejemplo.tareas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ejemplo.tareas.models.Estudiante;

public interface EstudianteRepository extends JpaRepository<Estudiante, Long> {
}
