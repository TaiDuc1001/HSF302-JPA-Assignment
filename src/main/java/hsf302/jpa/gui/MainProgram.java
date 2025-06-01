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
            System.out.println("==== MAIN MENU ====");
            System.out.println("1. Add data");
            System.out.println("2. Display all data");
            System.out.println("3. CRUD Student");
            System.out.println("4. CRUD Subject");
            System.out.println("0. Exit");
            System.out.print("Choose option: ");
            int option = Integer.parseInt(scanner.nextLine());
            if (option == 1) {
                // Add data submenu
                System.out.println("Add data:");
                System.out.println("1. Student");
                System.out.println("2. Subject");
                System.out.print("Choose data type: ");
                int dataType = Integer.parseInt(scanner.nextLine());
                System.out.print("How many to create? ");
                int n = Integer.parseInt(scanner.nextLine());
                if (dataType == 1) {
                    for (int i = 1; i <= n; i++) {
                        System.out.println("Adding student " + i + ":");
                        utils.addNewStudent(scanner, student_dao, subject_dao);
                    }
                } else if (dataType == 2) {
                    for (int i = 1; i <= n; i++) {
                        System.out.println("Adding subject " + i + ":");
                        utils.addNewSubject(scanner, subject_dao);
                    }
                } else {
                    System.out.println("Invalid data type!");
                }
            } else if (option == 2) {
                // Display all data
                System.out.println("\n--- Students ---");
                utils.displayAllStudents(student_dao);
                System.out.println("\n--- Subjects ---");
                utils.displayAllSubjects(subject_dao);
            } else if (option == 3) {
                // CRUD Student submenu
                System.out.println("CRUD Student:");
                System.out.println("1. Create");
                System.out.println("2. Read (by ID)");
                System.out.println("3. Update");
                System.out.println("4. Delete");
                System.out.print("Choose operation: ");
                int stuOp = Integer.parseInt(scanner.nextLine());
                if (stuOp == 1) {
                    utils.addNewStudent(scanner, student_dao, subject_dao);
                } else if (stuOp == 2) {
                    utils.readStudentById(scanner, student_dao);
                } else if (stuOp == 3) {
                    utils.updateStudent(scanner, student_dao);
                } else if (stuOp == 4) {
                    utils.deleteStudent(scanner, student_dao);
                } else {
                    System.out.println("Invalid operation!");
                }
            } else if (option == 4) {
                // CRUD Subject submenu
                System.out.println("CRUD Subject:");
                System.out.println("1. Create");
                System.out.println("2. Read (by ID)");
                System.out.println("3. Update");
                System.out.println("4. Delete");
                System.out.print("Choose operation: ");
                int subOp = Integer.parseInt(scanner.nextLine());
                if (subOp == 1) {
                    utils.addNewSubject(scanner, subject_dao);
                } else if (subOp == 2) {
                    utils.readSubjectById(scanner, subject_dao);
                } else if (subOp == 3) {
                    utils.updateSubject(scanner, subject_dao);
                } else if (subOp == 4) {
                    utils.deleteSubject(scanner, subject_dao);
                } else {
                    System.out.println("Invalid operation!");
                }
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
