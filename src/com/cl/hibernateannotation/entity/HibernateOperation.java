package com.cl.hibernateannotation.entity;

import java.util.List;
import java.util.Scanner;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class HibernateOperation {

	protected void addStudent() {
		Transaction transaction = null;
		Session dbSession = null;
		Scanner sc = new Scanner(System.in);

		try {
			dbSession = HibernateUtil.getSessionFactory().openSession();
			Student st = new Student();

			System.out.println("enter the student details");

			System.out.println("enter the student name = ");
			st.setStudentName(sc.next());

			System.out.println("enter the student email = ");
			st.setStudentEmail(sc.next());

			transaction = dbSession.beginTransaction();
			dbSession.save(st);

			transaction.commit();

			System.out.println("Student added successfully!");

		} catch (Exception e) {
			e.printStackTrace();
			if (transaction != null) {
				transaction.rollback();
			}
		} finally {
			if (dbSession != null) {
				dbSession.close();
			}
		}
	}
	
	 //list of the student
	 protected static void viewAllStudents() {
	        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
	            List<Student> stu = session.createQuery("from Student", Student.class).list();
	            stu.stream().forEach(System.out::println);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	 
	//delete the student data based on id
	    protected static void deleteStudentById(Scanner sc) {
	        System.out.print("Enter Student ID to delete: ");
	        int id = sc.nextInt();

	        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
	            Transaction tx = session.beginTransaction();
	            Query<Student> deleteQuery = session.createQuery("delete from Student where id = :id");
	            deleteQuery.setParameter("id", id);
	            int rowCount = deleteQuery.executeUpdate();

	            if (rowCount != 0) {
	                System.out.println("Row deleted: " + rowCount);
	            } else {
	                System.out.println("No row found with ID: " + id);
	            }

	            tx.commit();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	    
	    protected static void updateStudentById(Scanner sc) {
	        System.out.println("Enter Student Id : ");
	        int id = sc.nextInt();
	        
	        System.out.println("Enter new Student Email: ");
	        String email = sc.next();  // Assuming the email does not contain spaces

	        try {
	            Session session = HibernateUtil.getSessionFactory().openSession(); 
	            Transaction tx = session.beginTransaction();

	            String hql = "update Student set studentEmail = :email where id = :id";
	            Query query = session.createQuery(hql);
	            query.setParameter("id", id);
	            query.setParameter("email", email);

	            int rowCount = query.executeUpdate();
	            System.out.println("Rows affected: " + rowCount);

	            tx.commit();
	        } catch (Exception e) {
	            e.printStackTrace();
	        } 
	    }


}
