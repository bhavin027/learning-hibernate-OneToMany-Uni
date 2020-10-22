package org.bhavin.hibernate.demo;

import org.bhavin.hibernate.demo.entity.Course;
import org.bhavin.hibernate.demo.entity.Instructor;
import org.bhavin.hibernate.demo.entity.InstructorDetail;
import org.bhavin.hibernate.demo.entity.Review;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

public class GetCourseAndReviewDemo {

	public static void main(String[] args) {
		
		//create session factory
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class)
				.addAnnotatedClass(Course.class)
				.addAnnotatedClass(Review.class)
				.buildSessionFactory();
		
		//create session
		Session session = factory.getCurrentSession();
		
		//use the session object to save java object
		try {
			
			//start transaction
			session.beginTransaction();
			
			// get the course
			// print the course and reviews
			
			int theId=11;
			
			//Note: option 1
			/*
			 * Course tempCourse = session.get(Course.class, theId);
			 * 
			 * System.out.println(tempCourse);
			 * 
			 * System.out.println(tempCourse.getReviews());
			 */
			
			//Note: Option 2
			Query<Course> query = 
					session.createQuery("select c from Course c "
										+"JOIN FETCH c.reviews "
										+"where c.id=:theCourseId",
										Course.class);
			
			query.setParameter("theCourseId",theId);
			
			Course tempCourse = query.getSingleResult();
			
			System.out.println(tempCourse);
			
			//commit transaction
			session.getTransaction().commit();
			session.close();
			System.out.println("\nSession is closed\n");
			System.out.println(tempCourse.getReviews());
			
			System.out.println("Done");
			
		}
		catch(Exception exc) {
			exc.printStackTrace();
		}
		finally {
			session.close();
			factory.close();
		}
	}

}
