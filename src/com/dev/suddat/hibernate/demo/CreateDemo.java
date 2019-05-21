package com.dev.suddat.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.dev.suddat.hibernate.demo.entity.Instructor;
import com.dev.suddat.hibernate.demo.entity.InstructorDetail;
import com.dev.suddat.hibernate.demo.entity.Student;

public class CreateDemo {

	public static void main(String[] args) {

		// create session factory
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class)
				.buildSessionFactory();

		// create session
		Session session = factory.getCurrentSession();

		try {
			// create objects
			/*Instructor tempInstructor = new Instructor("Chad",
					"Darby", "chad@darby.com");

			InstructorDetail tempInstructorDetail = new InstructorDetail(
					"youtube.com.chagDarby",
					"love to bakchodi1");*/
			
			Instructor tempInstructor = new Instructor("Ashwini",
					"Bhasme", "ashu@bhasme.com");

			InstructorDetail tempInstructorDetail = new InstructorDetail(
					"youtube.com.ashuBhasme",
					"love to play guitar");

			// associate objects
			tempInstructor
					.setInstructorDetail(tempInstructorDetail);

			// start a transaction
			session.beginTransaction();

			// save the instructor
			//
			// Note : this will also save the detail object
			// because of cascadetype.ALL
			//
			System.out.println(
					"Saving inistructor:" + tempInstructor);
			session.save(tempInstructor);

			// commit the transaction
			session.getTransaction().commit();

			System.out.println("Done!!");

		} finally {
			factory.close();
		}

	}

}
