package com.dev.suddat.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.dev.suddat.hibernate.demo.entity.Student;

public class PrimaryKeyDemo {

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

			// create 3 student objects
			System.out
					.println("Creating 3 new student object...");
			Student tempStudent1 = new Student("John", "Doe",
					"doe@john.com");
			Student tempStudent2 = new Student("Mary", "Public",
					"public@mary.com");
			Student tempStudent3 = new Student("Bonita",
					"Applebum", "apple@bonita.com");

			// start a transaction
			session.beginTransaction();

			// save the student object
			System.out.println("Saving the student");
			session.save(tempStudent1);
			session.save(tempStudent2);
			session.save(tempStudent3);

			// commit the transaction
			session.getTransaction().commit();

			System.out.println("Done!!");

		} finally {
			factory.close();
		}
	}
}