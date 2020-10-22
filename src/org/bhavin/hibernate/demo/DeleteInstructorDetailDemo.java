package org.bhavin.hibernate.demo;

import org.bhavin.hibernate.demo.entity.Instructor;
import org.bhavin.hibernate.demo.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DeleteInstructorDetailDemo {

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
			
			//get the instructor detail object
			int theId =3;
			InstructorDetail tempInstructorDetail = session.get(InstructorDetail.class, theId);
			
			//print instructor detail
			System.out.println("Instuctor Detail: "+tempInstructorDetail);
			// print associated instructor
			System.out.println("Instructor: "+tempInstructorDetail.getInstructor());
			
			//delete the instructor detail
			System.out.println("Deleting..."+tempInstructorDetail);
			
			// remove the associated object
			// break bi directional link
			tempInstructorDetail.getInstructor().setInstructorDetail(null);
			
			session.delete(tempInstructorDetail);
			
			//commit transaction
			session.getTransaction().commit();
			System.out.println("Done");
			
		}
		catch(Exception exc) {
			exc.printStackTrace();
		}
		finally {
			//handle leakage of session
			session.close();
			factory.close();
		}
	}

}
