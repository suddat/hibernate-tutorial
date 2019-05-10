package com.dev.suddat.hibernate.demo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.dev.suddat.hibernate.demo.entity.Student;

public class QueryStudentDemo {

	public static void main(String[] args) {

		// create session factory
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Student.class)
				.buildSessionFactory();

		// create session
		Session session = factory.getCurrentSession();

		try {
			// use the session object to save java object

			// start a transaction
			session.beginTransaction();

			// query student

			List<Student> theStudent = session
					.createQuery("from Student").list();

			// display the student
			displayStudent(theStudent);

			// query students lastname='Doe'
			theStudent = session.createQuery(
					"from Student s where s.lastName='Doe'")
					.list();

			// display the students
			System.out
					.println("\n\nStudents with last name Doe");
			displayStudent(theStudent);

			// query student with :lastName='doe' or firstname='Daffy'

			theStudent = session.createQuery(
					"from Student s where s.lastName='Doe' or  s.firstName='Daffy' ")
					.list();

			// display student with lastName='Doe' or firstName='Daffy'
			System.out.println(
					"\n\nstudent with lastName='Doe' or firstName='Daffy': ");
			displayStudent(theStudent);

			// query students where email LIKE %@suddat.com
			theStudent = session.createQuery(
					"from Student s where s.email LIKE '%@suddat.com' ")
					.list();
			
			//display student with email id
			displayStudent(theStudent);

			// commit the transaction
			session.getTransaction().commit();

			System.out.println("Done!!");

		} finally {
			factory.close();
		}

	}

	private static void displayStudent(
			List<Student> theStudent) {
		for (Student tempStudent : theStudent) {
			System.out.println(tempStudent);
		}
	}

}
