package hsf302.jpa.repo;

import hsf302.jpa.pojo.Subject;
import java.util.List;

public interface SubjectRepository {
    void addSubject(Subject subject);
    Subject findSubject(Long id);
    void updateSubject(Subject subject);
    void deleteSubject(Long id);
    List<Subject> getAllSubjects();
}
