package com.dev.suddat.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.dev.suddat.hibernate.demo.entity.Instructor;
import com.dev.suddat.hibernate.demo.entity.InstructorDetail;
import com.dev.suddat.hibernate.demo.entity.Student;

public class DeleteDemo {

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

			// start a transaction
			session.beginTransaction();

			// get instructor by primary key/id
			int theId = 1;
			Instructor tempInstructor = session
					.get(Instructor.class, theId);
			
			//delete record
			if(tempInstructor != null) {
				
				System.out.println("Deleting: "+ tempInstructor);
				
				//Note: will ALSo delete instructor row as well
				//because of CascadeType.ALL
				//
				session.delete(tempInstructor);
			}
			
			//commit transaction.
			session.getTransaction().commit();

			System.out.println("Done!!");

		} finally {
			factory.close();
		}

	}

}
