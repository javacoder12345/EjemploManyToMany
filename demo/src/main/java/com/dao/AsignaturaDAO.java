package com.dao;

import java.util.List;

import org.hibernate.Session;

import com.model.Asignatura;

public class AsignaturaDAO {

    public void insertAsignatura(Session session, Asignatura a) {

        session.persist(a);

    }

    public void updateAsignatura(Session session, Asignatura a) {

        session.merge(a);

    }

    public void deleteAsignatura(Session session, int id) {

        Asignatura a = session.get(Asignatura.class, id);
        session.remove(a);
    }

    public List<Asignatura> selectAllAsignatura(Session session) {
        return session.createQuery("FROM Asignatura ", Asignatura.class).list();

    }

}
