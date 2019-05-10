package com.dev.suddat.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.dev.suddat.hibernate.demo.entity.Student;

public class DeleteStudentDemo {

	public static void main(String[] args) {

		// create session factory
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Student.class)
				.buildSessionFactory();

		// create session
		Session session = factory.getCurrentSession();

		try {
			int studentId = 2;

			// start a transaction
			session = factory.getCurrentSession();
			session.beginTransaction();

			// retrieve student based on primary key.
			System.out.println(
					"\nGetting student with id: " + studentId);

			Student myStudent = session.get(Student.class,
					studentId);

			// delete the student
			//System.out.println("Deleting student: " + myStudent);
			//session.delete(myStudent);

			session.createQuery(
					"delete from Student where id=2")
					.executeUpdate();

			// commit the transaction.
			session.getTransaction().commit();

			System.out.println("Done!!");

		} finally {
			factory.close();
		}

	}

}
