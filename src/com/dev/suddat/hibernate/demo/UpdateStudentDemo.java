package com.dev.suddat.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.dev.suddat.hibernate.demo.entity.Student;

public class UpdateStudentDemo {

	public static void main(String[] args) {

		// create session factory
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Student.class)
				.buildSessionFactory();

		// create session
		Session session = factory.getCurrentSession();

		try {
			int studentId = 1;

			// start a transaction
			session = factory.getCurrentSession();
			session.beginTransaction();

			// retrieve student based on primary key.
			System.out.println(
					"\nGetting student with id: " + studentId);

			Student myStudent = session.get(Student.class,
					studentId);

			System.out.println("Updating Student: " + myStudent);

			myStudent.setFirstName("Scooby");

			// commit the transaction.
			session.getTransaction().commit();

			// NEW CODE
			session = factory.getCurrentSession();
			session.beginTransaction();

			// update email for all students
			System.out.println("update email for all students");

			session.createQuery(
					"update Student set email='dev.suddat@gmail.com'")
					.executeUpdate();

			session.getTransaction().commit();

			System.out.println("Done!!");

		} finally {
			factory.close();
		}

	}

}
