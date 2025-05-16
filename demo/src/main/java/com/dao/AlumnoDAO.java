package com.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.model.Alumno;

public class AlumnoDAO {

    public void insertAlumno(Session session, Alumno a) {
        Transaction transaction = null;

        session.persist(a);

    }

    public void updateAlumno(Session session, Alumno a) {

        session.merge(a);

    }

    public void deleteAlumno(Session session, int id) {
        Alumno a = session.get(Alumno.class, id);

        session.remove(a);

    }

    public List<Alumno> selectAllAlumno(Session session) {
        return session.createQuery(" FROM Alumno ", Alumno.class).list();

    }

}
