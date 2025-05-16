package com.model;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@Table(name = "asignatura")
public class Asignatura {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;
    @Column(name = "nombre")
    private String nombre;

    @ManyToMany(mappedBy = "asignaturas", fetch = FetchType.EAGER)
    private Set<Alumno> alumnos = new HashSet<>();

    public Asignatura(String nombre) {
        this.nombre = nombre;
    }

    public void anyadirAlumno(Alumno al) {
        alumnos.add(al);
        al.getAsignaturas().add(this);
    }

    public void quitarAlumno(Alumno al) {
        alumnos.remove(al);
        al.getAsignaturas().remove(this);
    }

}
