package com.spring.hibernate_demo;

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

	/**
	 * Starts the Spring Boot application.
	 *
	 * @param args command-line arguments
	 */
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

	/**
	 * Creates the command-line runner for the application.
	 *
	 * @param app the DAO used for application operations
	 * @return the configured command-line runner
	 */
	@Bean
	CommandLineRunner commandLineRunner(AppDao app) {
		return runner -> {
			// createInstructor(app);
			// findInstructor(app);
			deleteInstructorWithCourses(app);
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
			// deleteCourseAndReviews(app);
			// createCoursesAndsStudents(app);
			// findCourseAndStudents(app);
			// findStudentAndCourses(app);
			// addMoreCoursesForStudent(app);
			// deleteCourseById(app);
			deleteStudentOnlyById(app);
		};
	}

	/**
	 * Creates and saves an Instructor with its details.
	 *
	 * @param app the DAO used for database operations
	 */
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

	/**
	 * Finds and displays an Instructor.
	 *
	 * @param app the DAO used for database operations
	 */
	private void findInstructor(AppDao app) {
		int id = 2;
		System.out.println("Finding instructor with id: " + id);

		Instructor ins = app.findInstructorById(id);

		System.out.println("Instructor: " + ins);
		System.out.println("Details: " + ins.getInstructorDetail());
	}

	/**
	 * Deletes an Instructor without deleting its Courses.
	 *
	 * @param app the DAO used for database operations
	 */
	private void deleteInstructorOnly(AppDao app) {
		int id = 4;
		System.out.println("Delete instructor: " + id);

		app.deleteInstructorById(id);
		System.out.println("Deleted Successfully!");
	}

	/**
	 * Requests deletion of an Instructor 
	 * using the DAO's course-aware delete method.
	 *
	 * @param app the DAO used for database operations
	 */
	private void deleteInstructorWithCourses(AppDao app) {
		// Delete the instructor by ID
		int id = 21;
		System.out.println("Deleting instructor with id: " + id);

		app.deleteInstructorWithCoursesById(id);
		System.out.println("Instructor deleted successfully.");
	}

	/**
	 * Finds and displays InstructorDetail and its Instructor.
	 *
	 * @param app the DAO used for database operations
	 */
	private void findInstructorDetail(AppDao app) {
		// Find the instructor detail by ID
		int id = 21;

		InstructorDetail insDetail = app.findInstructorDetailById(id);

		System.out.println("Instructor Detail: " + insDetail);
		System.out.println("Associated Instructor: " 
			+ insDetail.getInstructor());
	}

	/**
	 * Deletes InstructorDetail by its ID.
	 *
	 * @param app the DAO used for database operations
	 */
	private void deleteInstructorDetail(AppDao app) {
		// Delete the instructor detail by ID
		int id = 3;
		System.out.println("Deleting instructor detail with id: " + id);

		app.deleteInstructorDetailById(id);
		System.out.println("Deleted successfully.");
	}

	/**
	 * Creates and saves an Instructor with Courses.
	 *
	 * @param app the DAO used for database operations
	 */
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

	/**
	 * Finds and displays an Instructor and its Courses.
	 *
	 * @param app the DAO used for database operations
	 */
	private void findInstructorWithCourses(AppDao app) {
		int id = 4;
		System.out.println("Finding instructor id: " + id);

		Instructor ins = app.findInstructorById(id);

		System.out.println("Instructor: " + ins);
		System.out.println("Courses: " + ins.getCourses());
	}

	/**
	 * Finds and displays the Courses assigned to an Instructor.
	 *
	 * @param app the DAO used for database operations
	 */
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

	/**
	 * Finds and displays an Instructor and its fetched Courses.
	 *
	 * @param app the DAO used for database operations
	 */
	private void findInstructorWithCoursesJoinFetch(AppDao app) {
		int id = 4;
		System.out.println("Find Instructor id: " + id);

		Instructor ins = app.findInstructorByIdJoinFetch(id);

		System.out.println("Instructor: " + ins);
		System.out.println("Courses: " + ins.getCourses());
	}

	/**
	 * Updates an Instructor's name.
	 *
	 * @param app the DAO used for database operations
	 */
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

	/**
	 * Updates a Course's title.
	 *
	 * @param app the DAO used for database operations
	 */
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

	/**
	 * Creates a Course with Reviews and saves it.
	 *
	 * @param app the DAO used for database operations
	 */
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

	/**
	 * Finds and displays a Course and its Reviews.
	 *
	 * @param app the DAO used for database operations
	 */
	private void retrieveCourseAndReviews(AppDao app) {
		int id = 21;
		System.out.println("Get Course and Reviews from id: " + id);

		// Get Courses and Reviews
		Course c = app.findCourseAndReviewByCourseId(id);

		// Print the Course
		System.out.println(c);

		// Print the Reviews
		System.out.println(c.getReview());
	}

	/**
	 * Deletes a Course and its Reviews.
	 *
	 * @param app the DAO used for database operations
	 */
	private void deleteCourseAndReviews(AppDao app) {
		int id = 5;
		System.out.println("Delete course: " + id);

		app.deleteCourseById(id);
		System.out.println("Deleted Successfully!");
	}

	/**
	 * Creates a Course with Students and saves it.
	 *
	 * @param app the DAO used for database operations
	 */
	private void createCoursesAndsStudents(AppDao app) {
		// Create the course
		Course course =  new Course("How to Be A FullStack in Half A Day");

		// Create the students
		Student student1 = new Student("Light", 
										"Yagami", 
										"light.yagami@example.com");
		Student student2 = new Student("Leslie", 
										"Andrews", 
										"leslie.andrews@example.com");

		// Add student to the course
		course.addStudent(student1);
		course.addStudent(student2);

		// Save the course and associated students
		app.save(course);
		System.out.println("Successfully Enrolled!");
	}

	/**
	 * Finds and displays a Course and its Students.
	 *
	 * @param app the DAO used for database operations
	 */
	private void findCourseAndStudents(AppDao app) {
		int id = 22;
		Course course = app.findCourseAndStudentByCourseId(id);

		System.out.println("Course: " + course);
		System.out.println("Students: " + course.getStudents());
	}

	/**
	 * Finds and displays a Student and their Courses.
	 *
	 * @param app the DAO used for database operations
	 */
	private void findStudentAndCourses(AppDao app) {
		int id = 41;
		Student student = app.findStudentAndCourseByStudentId(id);

		System.out.println("Student: " + student);
		System.out.println("Courses: " + student.getCourses());
	}

	/**
	 * Adds Courses to a Student and updates the Student.
	 *
	 * @param app the DAO used for database operations
	 */
	private void addMoreCoursesForStudent(AppDao app) {
		int id = 42;
		Student student = app.findStudentAndCourseByStudentId(id);

		Course course1 = new Course("How to Test without Running Application");
		Course course2 = new Course("How to Develop without Computer");
		Course course3 = new Course("How to Be a Legit Developer");

		student.addCourse(course1);
		student.addCourse(course2);
		student.addCourse(course3);

		System.out.println("Sucessfully enrolled to new courses!");
		app.update(student);
	}

	/**
	 * Deletes a Course by its ID.
	 *
	 * @param app the DAO used for database operations
	 */
	private void deleteCourseById(AppDao app) {
		int id = 1;
		System.out.println("Delete course: " + id);

		app.deleteCourseById(id);
		System.out.println("Deleted Successfully");
	} 

	/**
	 * Deletes a Student by its ID.
	 *
	 * @param app the DAO used for database operations
	 */
	private void deleteStudentOnlyById(AppDao app) {
		int id = 21;
		System.out.println("Delete Student: " + id);

		app.deleteStudentById(id);
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
