package com.ejemplo.tareas.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
public class Codigo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Long id;

    @Column(nullable = true)
    @Getter
    @Setter
    private Long calificacion;

    @Getter
    @Setter
    private String codigoFuente;

    @ManyToOne
    @JoinColumn(name = "estudiante_id")
    @JsonBackReference
    @Getter
    @Setter
    private Estudiante estudiante;
}
