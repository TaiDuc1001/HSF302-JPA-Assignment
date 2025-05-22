package hsf302.jpa.gui;

import hsf302.jpa.pojo.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class MainProgram {
    public static void main(String[] args){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("studentPU");
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();

        Student student = new Student();
        student.setName("John Doe");
        student.setBirthYear("1996");
        student.setEmail("john.doe@gmail.com");
        student.setPassword("password123");


        em.persist(student);
        em.getTransaction().commit();

        em.close();
        emf.close();
    }
}
