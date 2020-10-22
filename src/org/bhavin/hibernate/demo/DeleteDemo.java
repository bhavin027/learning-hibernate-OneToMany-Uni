package org.bhavin.hibernate.demo;

import org.bhavin.hibernate.demo.entity.Instructor;
import org.bhavin.hibernate.demo.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DeleteDemo {

	public static void main(String[] args) {
		
		//create session factory
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class)
				.buildSessionFactory();
		
		//create session
		Session session = factory.getCurrentSession();
		
		//use the session object to save java object
		try {
			
			//start transaction
			session.beginTransaction();
			
			//get instructor by id
			int theId= 2;
			Instructor tempInstructor = session.get(Instructor.class, theId);
			
			System.out.println("Found Instructor: "+tempInstructor);
			
			//delete the instructor
			if(tempInstructor != null) {
				System.out.println("Deleting Instructor: "+tempInstructor);
				
				//Note this will also delete associated instructor details
				//because of Cascade.Type.ALL
				session.delete(tempInstructor);
			}
			
			//commit transaction
			session.getTransaction().commit();
			System.out.println("Done");
			
		}finally {
			factory.close();
		}
	}

}
