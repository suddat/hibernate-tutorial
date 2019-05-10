package com.dev.suddat.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.dev.suddat.hibernate.demo.entity.Student;

public class ReadStudentDemo {

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
			
			// create a student object
			System.out.println("Creating new student object...");
			Student tempStudent = new Student("Daffy", "Duck",
					"duck@daffy.com");
			
			// start a transaction
			session.beginTransaction();
			
			//save the student object
			System.out.println("Saving the student");
			System.out.println(tempStudent);
			session.save(tempStudent);
			
			// commit the transaction
			session.getTransaction().commit();
			
			//My NEW Code
			
			//find out the students id : primary key
			
			System.out.println("Saved Student. Generated id: "+ tempStudent.getId());
			
			//now get a new session and start transaction
			session = factory.getCurrentSession();
			
			session.beginTransaction();
			
			//retrieve student based on primary key.
			System.out.println("\nGetting student with id: "+tempStudent.getId());
			
			Student myStudent = session.get(Student.class, tempStudent.getId());
			
			System.out.println("Get complete: "+myStudent);
			
			//commit the transaction.
			session.getTransaction().commit();			
			
			System.out.println("Done!!");
			
			
		} finally {
			factory.close();
		}

	}

}
