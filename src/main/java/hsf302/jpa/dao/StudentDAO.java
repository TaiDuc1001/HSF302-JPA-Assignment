package hsf302.jpa.dao;

import hsf302.jpa.pojo.Student;
import hsf302.jpa.repo.StudentRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class StudentDAO implements StudentRepository {
    private static String persistenceName = "studentPU";
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory(persistenceName);

    @Override
    public void createStudent(Student student) {
        addStudent(student);
    }

    @Override
    public void removeStudent(Student student) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Student managedStudent = em.find(Student.class, student.getId());
        if (managedStudent != null) {
            em.remove(managedStudent);
        }
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public void updateStudent(Student student) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.merge(student);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public Student findStudentById(Long id) {
        EntityManager em = emf.createEntityManager();
        Student student = em.find(Student.class, id);
        em.close();
        return student;
    }

    @Override
    public void updateStudentById(Long id, Student student) {
        updateStudent(student);
    }

    public boolean addStudent(Student student) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.merge(student);
        em.getTransaction().commit();
        em.close();
        return true;
    }

    public java.util.List<Student> getAllStudents() {
        EntityManager em = emf.createEntityManager();
        java.util.List<Student> students = em.createQuery("SELECT DISTINCT s FROM Student s LEFT JOIN FETCH s.subjects", Student.class).getResultList();
        em.close();
        return students;
    }
}
