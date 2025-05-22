package hsf302.jpa.dao;

import hsf302.jpa.pojo.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class StudentDAO {
    private static String persistenceName = "studentPU";
    public boolean addStudent(Student student) {
        // Create an instance of EntityManagerFactory
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(persistenceName);
        EntityManager em = emf.createEntityManager();

        // Start a transaction
        em.getTransaction().begin();

        // Persist the student object
        em.persist(student);

        // Commit the transaction
        em.getTransaction().commit();

        // Close the EntityManager and EntityManagerFactory
        em.close();
        emf.close();

        return true;
    }
}
