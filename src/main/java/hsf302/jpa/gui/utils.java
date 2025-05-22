package hsf302.jpa.gui;

import hsf302.jpa.pojo.Student;
import hsf302.jpa.dao.StudentDAO;
import hsf302.jpa.pojo.Subject;
import hsf302.jpa.dao.SubjectDAO;
import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;

public class utils {
    public static void addNewStudent(Scanner scanner, StudentDAO student_dao, SubjectDAO subject_dao) {
        Student student = new Student();
        System.out.print("Enter name: ");
        student.setName(scanner.nextLine());
        System.out.print("Enter birth year: ");
        student.setBirthYear(scanner.nextLine());
        System.out.print("Enter email: ");
        student.setEmail(scanner.nextLine());
        System.out.print("Enter password: ");
        student.setPassword(scanner.nextLine());

        // List all available subjects
        List<Subject> allSubjects = subject_dao.getAllSubjects();
        if (allSubjects.isEmpty()) {
            System.out.println("No subjects available. Please add subjects first.");
        } else {
            System.out.println("Available subjects:");
            for (int i = 0; i < allSubjects.size(); i++) {
                System.out.println((i+1) + ". " + allSubjects.get(i).getName() + " (" + allSubjects.get(i).getCode() + ")");
            }
            System.out.print("Enter subject numbers separated by comma (e.g. 1,3): ");
            String[] subjectIndexes = scanner.nextLine().split(",");
            List<Subject> selectedSubjects = new ArrayList<>();
            for (String idxStr : subjectIndexes) {
                try {
                    int idx = Integer.parseInt(idxStr.trim()) - 1;
                    if (idx >= 0 && idx < allSubjects.size()) {
                        selectedSubjects.add(allSubjects.get(idx));
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input: " + idxStr);
                }
            }
            student.setSubjects(selectedSubjects);
        }
        student_dao.addStudent(student);
        System.out.println("Student added successfully!");
    }

    public static void addFakeSubjects(Scanner scanner, SubjectDAO subject_dao) {
        System.out.print("Enter number of fake subjects to add: ");
        int n = Integer.parseInt(scanner.nextLine());
        for (int i = 1; i <= n; i++) {
            Subject subject = new Subject();
            subject.setName("Subject" + i);
            subject.setCode("CODE" + String.format("%03d", i));
            subject_dao.addSubject(subject);
        }
        System.out.println(n + " fake subjects added successfully!");
    }

    public static void displayAllStudents(StudentDAO student_dao) {
        List<Student> students = student_dao.getAllStudents();
        if (students.isEmpty()) {
            System.out.println("No students found.");
        } else {
            System.out.println("| ID  | Name                 | BirthYear | Email                        | Subjects");
            System.out.println("--------------------------------------------------------------------------------------");
            for (Student s : students) {
                StringBuilder subjectNames = new StringBuilder();
                for (Subject subj : s.getSubjects()) {
                    if (subjectNames.length() > 0) subjectNames.append(", ");
                    subjectNames.append(subj.getName());
                }
                System.out.printf("| %-3d | %-20s | %-9s | %-28s | %s\n",
                    s.getId(), s.getName(), s.getBirthYear(), s.getEmail(), subjectNames.toString());
            }
        }
    }

    public static void displayAllSubjects(SubjectDAO subject_dao) {
        List<Subject> subjects = subject_dao.getAllSubjects();
        if (subjects.isEmpty()) {
            System.out.println("No subjects found.");
        } else {
            System.out.println("| ID  | Name                 | Code      |");
            System.out.println("------------------------------------------");
            for (Subject subj : subjects) {
                System.out.printf("| %-3d | %-20s | %-9s |\n", subj.getId(), subj.getName(), subj.getCode());
            }
        }
    }
}
