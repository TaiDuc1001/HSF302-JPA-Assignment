package hsf302.jpa.dao;

import hsf302.jpa.pojo.Subject;
import hsf302.jpa.repo.SubjectRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.List;

public class SubjectDAO implements SubjectRepository {
    private static String persistenceName = "studentPU";
    @Override
    public void addSubject(Subject subject) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(persistenceName);
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(subject);
        em.getTransaction().commit();
        em.close();
        emf.close();
    }
    @Override
    public Subject findSubject(Long id) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(persistenceName);
        EntityManager em = emf.createEntityManager();
        Subject subject = em.find(Subject.class, id);
        em.close();
        emf.close();
        return subject;
    }
    @Override
    public void updateSubject(Subject subject) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(persistenceName);
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.merge(subject);
        em.getTransaction().commit();
        em.close();
        emf.close();
    }
    @Override
    public void deleteSubject(Long id) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(persistenceName);
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Subject subject = em.find(Subject.class, id);
        if (subject != null) {
            em.remove(subject);
        }
        em.getTransaction().commit();
        em.close();
        emf.close();
    }
    @Override
    public List<Subject> getAllSubjects() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(persistenceName);
        EntityManager em = emf.createEntityManager();
        List<Subject> subjects = em.createQuery("SELECT s FROM Subject s", Subject.class).getResultList();
        System.out.println("DEBUG: in getAllSubjects() method");
        for (Subject subject : subjects) {
            System.out.println("DEBUG: Subject ID: " + subject.getId() + ", Name: " + subject.getName() + ", Code: " + subject.getCode());
        }
        em.close();
        emf.close();
        return subjects;
    }
}
