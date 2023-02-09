package com.gl.examples;

import java.sql.SQLException;

import java.util.Scanner;

import com.gl.examples.dto.Student;

import com.gl.examples.service.StudentService;

import com.gl.examples.serviceImpl.StudentServiceImpl;

public class AppMain {

	public static void main(String[] args) throws SQLException {
		int choice;
		String Continue;

		StudentService dao = new StudentServiceImpl();
		Student student;

		do {
			System.out.println("<-------Menu------>");
			System.out.println("1. Add student");
			System.out.println("2. Get student by ID");
			System.out.println("3. Update student");
			System.out.println("4. Delete student by id");
			System.out.println("5. Get all student");
			System.out.println(" Enter the choice : ");
			Scanner sc = new Scanner(System.in);
			choice = sc.nextInt();

			switch (choice) {
			case 1:
				System.out.println("Enter Student Details: ");
				System.out.println("Enter student ID: ");
				int id2 = sc.nextInt();
				System.out.println("Enter student Name: ");
				String name = sc.next();
				System.out.println("Enter address: ");
				String address = sc.next();
				System.out.println("Enter email: ");
				String email = sc.next();
				
				Student e1 = new Student(id2, name, address, email);
				student = dao.addStudent(e1);
				break;

			case 2:
				System.out.println("Enter the student id: ");
				String contactId = sc.next();
				student = dao.getStudentById(contactId);
				break;

			case 3:
				System.out.println("Enter the studentId to change record and update ");
				String studentId = sc.next();
				System.out.println("Enter the new name: ");
				String name1 = sc.next();
				System.out.println("Enter the new address: ");
				String email1 = sc.next();
				System.out.println("Enter the new email: ");
				String address1 = sc.next();
				
				student = dao.updateStudent(studentId, name1, email1, address1);
				break;

			case 4:
				System.out.println("Enter the studentId to delete record");
				String id4 = sc.next();
				student = dao.deleteStudent(id4);
				break;

			case 5:
				System.out.println("All student Data");

				dao.getAllStudent();

				break;

			default:
				System.out.println("Wrong input..!");
				break;
			}
			System.out.println("   Enter y to continue..!");
			Continue = sc.next();
		} while (Continue.endsWith("y"));
	}

}