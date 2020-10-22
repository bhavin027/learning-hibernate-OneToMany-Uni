package org.bhavin.hibernate.demo;

import org.bhavin.hibernate.demo.entity.Course;
import org.bhavin.hibernate.demo.entity.Instructor;
import org.bhavin.hibernate.demo.entity.InstructorDetail;
import org.bhavin.hibernate.demo.entity.Review;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateCourseAndReviewDemo {

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
			
			//create a course
			Course tempCourse = new Course("Guitar- Tings of Strings");
			//add some reviews
			tempCourse.addReview(new Review("Very Impressive... thank you!"));
			tempCourse.addReview(new Review("Sharp understanding of cords, great work!"));
			tempCourse.addReview(new Review("Umm boring... there is a scope of improve!"));
			//save the course and leverage cascade all
			System.out.println("Saving course..."+tempCourse);
			System.out.println(tempCourse.getReviews());
			session.save(tempCourse);
			//commit transaction
			session.getTransaction().commit();
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
