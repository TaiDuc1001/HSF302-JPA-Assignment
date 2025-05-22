package hsf302.jpa.repo;

import hsf302.jpa.pojo.Student;

public interface StudentRepository {
    public void createStudent(Student student);
    public void removeStudent(Student student);
    public void updateStudent(Student student);
    public void findStudentById(Long id);
    public void updateStudentById(Long id, Student student);
}
