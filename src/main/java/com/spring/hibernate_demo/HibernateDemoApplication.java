package com.spring.hibernate_demo;

import org.aspectj.apache.bcel.generic.Instruction;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import com.spring.hibernate_demo.dao.StudentDao;
import com.spring.hibernate_demo.entity.InstructorDetail;
import com.spring.hibernate_demo.entity.Review;
import com.spring.hibernate_demo.entity.Student;
import com.spring.hibernate_demo.dao.AppDao;
import com.spring.hibernate_demo.entity.Instructor;
import com.spring.hibernate_demo.entity.InstructorDetail;
import com.spring.hibernate_demo.entity.Course;
import com.spring.hibernate_demo.dao.AppDaoImplement;
import java.util.List;

/**
 * Main application class for the Hibernate Demo application.
 * This class is responsible for bootstrapping the Spring Boot application.
 */
@SpringBootApplication
public class HibernateDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(HibernateDemoApplication.class, args);
	}

	// @Bean 
	// CommandLineRunner commandLineRunner(StudentDao studentDao) {
	// 	return runner -> {
	// 		// createStudent(studentDao);
	// 		// readStudent(studentDao);
	// 		// queryForStudents(studentDao);
	// 		// queryForStudentsByLastName(studentDao);
	// 		// updateStudent(studentDao);
	// 		// deleteStudent(studentDao);
	// 		// deleteAllStudents(studentDao);
	// 	};
	// }

	@Bean
	CommandLineRunner commandLineRunner(AppDao app) {
		return runner -> {
			// createInstructor(app);
			// findInstructor(app);
			// deleteInstructorWithCourses(app);
			// findInstructorDetail(app);
			// deleteInstructorDetail(app);
			// createInstructorWithCourses(app);
			// findInstructorWithCourses(app);
			// findCoursesForInstructor(app);
			// findInstructorWithCoursesJoinFetch(app);
			// updateInstructor(app);
			// updateCourse(app);
			// deleteInstructorOnly(app);
			// createCourseAndReviews(app);
			// retrieveCourseAndReviews(app);
			deleteCourseAndReviews(app);
		};
	}

	private void createInstructor(AppDao app) {
		// Create the instructor object
		Instructor ins = new Instructor("Jane",
												"PinaColada",
												"jane.pinacolada@example.com");
		InstructorDetail insDetails = new InstructorDetail("English",
												"Speech");
		
		// Associate the objects
		System.out.println("Saving the instructor: " + ins);
		ins.setInstructorDetail(insDetails);
		app.save(ins);
	}

	private void findInstructor(AppDao app) {
		int id = 1;
		System.out.println("Finding instructor with id: " + id);

		Instructor ins = app.findInstructorById(id);

		System.out.println("Instructor: " + ins);
		System.out.println("Details: " + ins.getInstructorDetail());
	}

	private void deleteInstructorOnly(AppDao app) {
		int id = 4;
		System.out.println("Delete instructor: " + id);

		app.deleteInstructorById(id);
		System.out.println("Deleted Successfully!");
	}

	private void deleteInstructorWithCourses(AppDao app) {
		// Delete the instructor by ID
		int id = 1;
		System.out.println("Deleting instructor with id: " + id);

		app.deleteInstructorWithCoursesById(id);
		System.out.println("Instructor deleted successfully.");
	}

	private void findInstructorDetail(AppDao app) {
		// Find the instructor detail by ID
		int id = 3;

		InstructorDetail insDetail = app.findInstructorDetailById(id);

		System.out.println("Instructor Detail: " + insDetail);
		System.out.println("Associated Instructor: " + insDetail.getInstructor());
	}

	private void deleteInstructorDetail(AppDao app) {
		// Delete the instructor detail by ID
		int id = 3;
		System.out.println("Deleting instructor detail with id: " + id);

		app.deleteInstructorDetailById(id);
		System.out.println("Deleted successfully.");
	}

	private void createInstructorWithCourses(AppDao app){
		// Create Instructor and InstructorDetail
		Instructor ins = new Instructor("Lord",
										"Voldemort",
										"lord.voldemort@example.com");
		InstructorDetail insDetails = new InstructorDetail("Dark Arts",
															"Dark Magic");
		
		// Associate the Instructor with objects
		ins.setInstructorDetail(insDetails);

		// Create Courses
		Course course1 = new Course("Defense Against the Dark Arts");
		Course course2 = new Course("Potions");

		// Associate the courses with the instructor
		ins.add(course1);
		ins.add(course2);

		// Save the instructor (and courses) to the database
		System.out.println("CourseS saved: " + ins.getCourses());
		app.save(ins);
	}

	private void findInstructorWithCourses(AppDao app) {
		int id = 4;
		System.out.println("Finding instructor id: " + id);

		Instructor ins = app.findInstructorById(id);

		System.out.println("Instructor: " + ins);
		System.out.println("Courses: " + ins.getCourses());
	}

	private void findCoursesForInstructor(AppDao app) {
		int id = 4;

		// Find the instructor
		System.out.println("Find Instructor id: " + id);
		Instructor ins = app.findInstructorById(id);

		System.out.println("Instructor: " + ins);

		// Find courses for instructor
		System.out.println("Courses from id: " + ins.getId());
		List<Course> courses = app.findCoursesByInstructorId(id);

		// Associate the objects
		ins.setCourses(courses);

		// Get the courses
		System.out.println(ins.getCourses());
	}

	private void findInstructorWithCoursesJoinFetch(AppDao app) {
		int id = 4;
		System.out.println("Find Instructor id: " + id);

		Instructor ins = app.findInstructorByIdJoinFetch(id);

		System.out.println("Instructor: " + ins);
		System.out.println("Courses: " + ins.getCourses());
	}

	private void updateInstructor(AppDao app) {
		int id = 2;
		System.out.println("Find Instructor id: " + id);

		Instructor ins = app.findInstructorById(id);

		// Change Instructor's name
		ins.setFirstName("Jane");

		// Update the data
		System.out.println("Updated!");
		app.update(ins);
	}

	private void updateCourse(AppDao app) {
		int id = 4;
		System.out.println("Course id: " + id);

		Course c = app.findCourseById(id);

		// Change Course name
		c.setTitle("How to Get Away with Murder");

		// Update the data
		System.out.println("Updated!");
		app.update(c);
	}

	private void createCourseAndReviews(AppDao app) {
		// Create a course
		Course c = new Course("How to Speedrun Spring in 1 Day");

		// Add some reviews
		c.addReview(new Review("Great speed 2x... loved it"));
		c.addReview(new Review("おもしろかったです," + 
								"with this book I became the flash"));
		c.addReview(new Review("I wanted the 0.75x speed... SCAM D:<"));

		// Save the course
		System.out.println("Course saved!");
		app.save(c);
	}

	private void retrieveCourseAndReviews(AppDao app) {
		int id = 5;
		System.out.println("Get Course and Reviews from id: " + id);

		// Get Courses and Reviews
		Course c = app.findCourseAndReviewByCourseId(id);

		// Print the Course
		System.out.println(c);

		// Print the Reviews
		System.out.println(c.getReview());
	}

	private void deleteCourseAndReviews(AppDao app) {
		int id = 5;
		System.out.println("Delete course: " + id);

		app.deleteCourseById(id);
		System.out.println("Deleted Successfully!");
	}

	// First Exercise Functions

	/**
	 * Creates a new student and saves it to the database.
	 * @param studentDao the StudentDao instance used for database operations
	 */
	private void createStudent(StudentDao studentDao) {
		// Create a student object
		System.out.println("Creating new student object...");
		Student student = new Student("Severus",
										"Snape",
										"severus.snape@example.com");
		
		// Save the student object
		System.out.println("Saving the student...");
		studentDao.save(student);

		// Display the id of the saved student
		System.out.println("Saved! Generated id: " + student.getId());
	}
	
	/**
	 * Reads a student from the database based on its ID.
	 * @param studentDao the StudentDao instance used for database operations
	 */
	private void readStudent(StudentDao studentDao) {
		// Retrieve student based on the id
		System.out.println("Finding student with id: 2");
		Student myStudent = studentDao.findById(2);

		// Display the student
		System.out.println("Found the student: " + myStudent);
	}

	/**
	 * Queries and displays all students from the database.
	 * @param studentDao the StudentDao instance used for database operations
	 */
	private void queryForStudents(StudentDao studentDao) {
		// Retrieve all students
		System.out.println("Retrieving all students...");
		List<Student> students = studentDao.findAll();

		// Display the list of students
		for(Student student : students) {
			System.out.println(student);
		}
	}

	/**
	 * Queries and displays students with a 
	 * specific last name from the database.
	 * @param studentDao the StudentDao instance used for database operations
	 */
	private void queryForStudentsByLastName(StudentDao studentDao) {
		// Retrieve students with last name "Lacuarin"
		System.out.println("Retrieving students with last name 'Lacuarin'...");
		List<Student> students = studentDao.findByLastName("Lacuarin");

		// Display the list of students
		for(Student student : students) {
			System.out.println(student);
		}
	}

	/**
	 * Updates a student's information in the database.
	 * @param studentDao the StudentDao instance used for database operations
	 */
	private void updateStudent(StudentDao studentDao) {
		// Retrieve student based on the id
		int studentId = 3;
		System.out.println("studentID: " + studentId);
		Student myStudent = studentDao.findById(studentId);

		// Update the student's information
		System.out.println("Updating student...");
		myStudent.setFirstName("Graysie");
		myStudent.setLastName("Aso");
		myStudent.setEmail("graysie.aso@example.com");

		studentDao.update(myStudent);

		// Display the updated student
		System.out.println("Updated student: " + myStudent);
	}

	/**
	 * Deletes a student from the database based on its ID.
	 * @param studentDao the StudentDao instance used for database operations
	 */
	private void deleteStudent(StudentDao studentDao) {
		int studentId = 4;
		System.out.println("Deleting id: " + studentId);

		// Delete the student
		studentDao.delete(studentId);
	}

	/**
	 * Deletes all students from the database.
	 * @param studentDao the StudentDao instance used for database operations
	 */
	private void deleteAllStudents(StudentDao studentDao) {
		System.out.println("Deleting all students...");
		int numRowsDeleted = studentDao.deleteAll();
		System.out.println("Number of rows deleted: " + numRowsDeleted);
	}
}
