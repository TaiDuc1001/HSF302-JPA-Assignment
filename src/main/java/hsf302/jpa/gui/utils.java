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
        String name;
        do {
            System.out.print("Enter name (max 50 chars): ");
            name = scanner.nextLine();
            if (name.length() > 50) System.out.println("Name too long. Try again.");
        } while (name.length() > 50 || name.isEmpty());
        student.setName(name);
        String birthYear;
        do {
            System.out.print("Enter birth year (4 digits): ");
            birthYear = scanner.nextLine();
            if (birthYear.length() != 4) System.out.println("Birth year must be 4 digits.");
        } while (birthYear.length() != 4);
        student.setBirthYear(birthYear);
        String email;
        do {
            System.out.print("Enter email (max 50 chars): ");
            email = scanner.nextLine();
            if (email.length() > 50) System.out.println("Email too long. Try again.");
        } while (email.length() > 50 || email.isEmpty());
        student.setEmail(email);
        String password;
        do {
            System.out.print("Enter password (max 50 chars): ");
            password = scanner.nextLine();
            if (password.length() > 50) System.out.println("Password too long. Try again.");
        } while (password.length() > 50 || password.isEmpty());
        student.setPassword(password);

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

    public static void updateStudent(Scanner scanner, StudentDAO student_dao) {
        System.out.print("Enter student ID to update: ");
        Long id = Long.parseLong(scanner.nextLine());
        Student student = student_dao.findStudentById(id);
        if (student == null) {
            System.out.println("Student not found.");
            return;
        }
        System.out.println("Leave field empty to keep current value.");
        String name;
        do {
            System.out.print("Enter new name (current: " + student.getName() + ", max 50 chars): ");
            name = scanner.nextLine();
            if (!name.isEmpty() && name.length() > 50) System.out.println("Name too long. Try again.");
        } while (!name.isEmpty() && name.length() > 50);
        if (!name.isEmpty()) student.setName(name);
        String birthYear;
        do {
            System.out.print("Enter new birth year (current: " + student.getBirthYear() + ", 4 digits): ");
            birthYear = scanner.nextLine();
            if (!birthYear.isEmpty() && birthYear.length() != 4) System.out.println("Birth year must be 4 digits.");
        } while (!birthYear.isEmpty() && birthYear.length() != 4);
        if (!birthYear.isEmpty()) student.setBirthYear(birthYear);
        String email;
        do {
            System.out.print("Enter new email (current: " + student.getEmail() + ", max 50 chars): ");
            email = scanner.nextLine();
            if (!email.isEmpty() && email.length() > 50) System.out.println("Email too long. Try again.");
        } while (!email.isEmpty() && email.length() > 50);
        if (!email.isEmpty()) student.setEmail(email);
        String password;
        do {
            System.out.print("Enter new password (current: hidden, max 50 chars): ");
            password = scanner.nextLine();
            if (!password.isEmpty() && password.length() > 50) System.out.println("Password too long. Try again.");
        } while (!password.isEmpty() && password.length() > 50);
        if (!password.isEmpty()) student.setPassword(password);
        student_dao.updateStudent(student);
        System.out.println("Student updated successfully!");
    }

    public static void deleteStudent(Scanner scanner, StudentDAO student_dao) {
        System.out.print("Enter student ID to delete: ");
        Long id = Long.parseLong(scanner.nextLine());
        Student student = student_dao.findStudentById(id);
        if (student == null) {
            System.out.println("Student not found.");
            return;
        }
        student_dao.removeStudent(student);
        System.out.println("Student deleted successfully!");
    }

    public static void addNewSubject(Scanner scanner, SubjectDAO subject_dao) {
        Subject subject = new Subject();
        while (true) {
            System.out.print("Enter subject name (max 50 chars): ");
            String name = scanner.nextLine();
            if (name.length() > 50) {
                System.out.println("Name too long. Try again.");
                continue;
            }
            subject.setName(name);
            break;
        }
        while (true) {
            System.out.print("Enter subject code (max 20 chars): ");
            String code = scanner.nextLine();
            if (code.length() > 20) {
                System.out.println("Code too long. Try again.");
                continue;
            }
            subject.setCode(code);
            break;
        }
        subject_dao.addSubject(subject);
        System.out.println("Subject added successfully!");
    }

    public static void updateSubject(Scanner scanner, SubjectDAO subject_dao) {
        System.out.print("Enter subject ID to update: ");
        Long id = Long.parseLong(scanner.nextLine());
        Subject subject = subject_dao.findSubject(id);
        if (subject == null) {
            System.out.println("Subject not found.");
            return;
        }
        System.out.println("Leave field empty to keep current value.");
        while (true) {
            System.out.print("Enter new name (current: " + subject.getName() + ", max 50 chars): ");
            String name = scanner.nextLine();
            if (!name.isEmpty() && name.length() > 50) {
                System.out.println("Name too long. Try again.");
                continue;
            }
            if (!name.isEmpty()) subject.setName(name);
            break;
        }
        while (true) {
            System.out.print("Enter new code (current: " + subject.getCode() + ", max 20 chars): ");
            String code = scanner.nextLine();
            if (!code.isEmpty() && code.length() > 20) {
                System.out.println("Code too long. Try again.");
                continue;
            }
            if (!code.isEmpty()) subject.setCode(code);
            break;
        }
        subject_dao.updateSubject(subject);
        System.out.println("Subject updated successfully!");
    }

    public static void deleteSubject(Scanner scanner, SubjectDAO subject_dao) {
        Long id = null;
        while (true) {
            System.out.print("Enter subject ID to delete: ");
            String input = scanner.nextLine();
            try {
                id = Long.parseLong(input);
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid ID. Please enter a numeric subject ID.");
            }
        }
        Subject subject = subject_dao.findSubject(id);
        if (subject == null) {
            System.out.println("Subject not found.");
            return;
        }
        subject_dao.deleteSubject(id);
        System.out.println("Subject deleted successfully!");
    }

    public static void readStudentById(Scanner scanner, StudentDAO student_dao) {
        Long id = null;
        while (true) {
            System.out.print("Enter student ID to view: ");
            String input = scanner.nextLine();
            try {
                id = Long.parseLong(input);
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid ID. Please enter a numeric student ID.");
            }
        }
        Student student = student_dao.findStudentById(id);
        if (student == null) {
            System.out.println("Student not found.");
            return;
        }
        System.out.println("--- Student Info ---");
        System.out.println("ID: " + student.getId());
        System.out.println("Name: " + student.getName());
        System.out.println("Birth Year: " + student.getBirthYear());
        System.out.println("Email: " + student.getEmail());
        System.out.println("Subjects: ");
        if (student.getSubjects() == null || student.getSubjects().isEmpty()) {
            System.out.println("  None");
        } else {
            for (Subject subj : student.getSubjects()) {
                System.out.println("  - " + subj.getName() + " (" + subj.getCode() + ")");
            }
        }
    }

    public static void readSubjectById(Scanner scanner, SubjectDAO subject_dao) {
        Long id = null;
        while (true) {
            System.out.print("Enter subject ID to view: ");
            String input = scanner.nextLine();
            try {
                id = Long.parseLong(input);
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid ID. Please enter a numeric subject ID.");
            }
        }
        Subject subject = subject_dao.findSubject(id);
        if (subject == null) {
            System.out.println("Subject not found.");
            return;
        }
        System.out.println("--- Subject Info ---");
        System.out.println("ID: " + subject.getId());
        System.out.println("Name: " + subject.getName());
        System.out.println("Code: " + subject.getCode());
    }
}
