package hsf302.jpa.gui;

import hsf302.jpa.pojo.Student;
import hsf302.jpa.dao.StudentDAO;
import hsf302.jpa.pojo.Subject;
import hsf302.jpa.dao.SubjectDAO;
import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;

public class MainProgram {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        StudentDAO student_dao = new StudentDAO();
        SubjectDAO subject_dao = new SubjectDAO();
        while (true) {
            System.out.println("==== MENU ====");
            System.out.println("1. Add new student");
            System.out.println("2. Add n fake subjects");
            System.out.println("3. Display all students");
            System.out.println("4. Display all subjects");
            System.out.println("0. Exit");
            System.out.print("Choose option: ");
            int option = Integer.parseInt(scanner.nextLine());
            if (option == 1) {
                utils.addNewStudent(scanner, student_dao, subject_dao);
            } else if (option == 2) {
                utils.addFakeSubjects(scanner, subject_dao);
            } else if (option == 3) {
                utils.displayAllStudents(student_dao);
            } else if (option == 4) {
                utils.displayAllSubjects(subject_dao);
            } else if (option == 0) {
                System.out.println("Exiting...");
                break;
            } else {
                System.out.println("Invalid option!");
            }
        }
        scanner.close();
    }

}
