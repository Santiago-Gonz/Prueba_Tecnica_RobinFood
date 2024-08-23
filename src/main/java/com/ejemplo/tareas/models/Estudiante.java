package com.ejemplo.tareas.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
public class Estudiante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Long id;

    @Column(nullable = false)
    @Getter
    @Setter
    private String nombre;

    @Column(unique = true, nullable = false)
    @Getter
    @Setter
    private String email;

    @OneToMany(mappedBy = "estudiante", cascade = CascadeType.ALL)
    @JsonManagedReference
    @Getter
    @Setter
    private Set<Codigo> codigosFuente;
}
