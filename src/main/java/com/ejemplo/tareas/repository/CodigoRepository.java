package com.ejemplo.tareas.repository;

import com.ejemplo.tareas.models.Codigo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CodigoRepository extends JpaRepository<Codigo, Long> {
    List<Codigo> findAllByEstudianteId(Long estudianteId);
}
