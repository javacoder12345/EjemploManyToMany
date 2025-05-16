package com.model;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name = "alumno")
public class Alumno {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;
    @Column(name = "nombre")
    private String nombre;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
    @JoinTable(name = "matriculas", joinColumns = @JoinColumn(name = "idalumno"), inverseJoinColumns = @JoinColumn(name = "idasignatura"))
    private Set<Asignatura> asignaturas = new HashSet<>();

    public Alumno() {
    }

    public Alumno(String nombre) {
        this.nombre = nombre;
    }

    public void anyadirAsignatura(Asignatura as) {
        asignaturas.add(as);
        as.getAlumnos().add(this);
    }

    public void quitarAsignatura(Asignatura as) {
        asignaturas.remove(as);
        as.getAlumnos().remove(this);
    }

}
