package com.example;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.dao.AlumnoDAO;
import com.dao.AsignaturaDAO;
import com.model.Alumno;
import com.model.Asignatura;
import com.util.HibernateUtil;

public class Main {

    public static void main(String[] args) {

        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        try {
            AlumnoDAO alumnoDAO = new AlumnoDAO();
            AsignaturaDAO asignaturaDAO = new AsignaturaDAO();

            Alumno al1 = new Alumno("Joan");
            Alumno al2 = new Alumno("Carlos");
            Alumno al3 = new Alumno("Alex");

            Asignatura as1 = new Asignatura("PROG");
            Asignatura as2 = new Asignatura("ENTORNOS");

            al1.anyadirAsignatura(as1);
            al1.anyadirAsignatura(as2);
            al2.anyadirAsignatura(as1);

            alumnoDAO.insertAlumno(session, al1);
            alumnoDAO.insertAlumno(session, al2);
            alumnoDAO.insertAlumno(session, al3);

            al3.anyadirAsignatura(as2);
            alumnoDAO.updateAlumno(session, al3);

            //alumnoDAO.deleteAlumno(session, 552);

            List<Alumno> alumnos = alumnoDAO.selectAllAlumno(session);

            for (Alumno a : alumnos) {
                System.out.println(a.getNombre());
                for (Asignatura as : a.getAsignaturas()) {
                    System.out.print(as.getNombre() + " ");
                }
                System.out.println();
            }

        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }

    }
}
