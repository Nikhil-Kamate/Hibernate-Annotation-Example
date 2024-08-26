package com.cl.hibernateannotation.entity;

import java.util.Scanner;

public class HibernateMenu extends HibernateOperation {

	public void menu() {
		Scanner sc = new Scanner(System.in);
		int choice = 0;

		do {
			System.out.println("\nMenu:");
			System.out.println("1. Add Student");
			System.out.println("2. View All Students");
			System.out.println("3. Delete Student by ID");
			System.out.println("4. Update Student Email by Id");
			System.out.println("5. Exit");
			System.out.print("Enter your choice: ");
			choice = sc.nextInt();

			switch (choice) {
			case 1:
				addStudent();
				break;
			case 2:
				viewAllStudents();
				break;
			case 3:
				deleteStudentById(sc);
				break;
			case 4 :
				updateStudentById(sc);
				break;
			case 5:
				System.out.println("Exiting program...");
				break;
			default:
				System.out.println("Invalid choice. Please try again.");
			}
		} while (choice != 5);

		sc.close();
	}

}
