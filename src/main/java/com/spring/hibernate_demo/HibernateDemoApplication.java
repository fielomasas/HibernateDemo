package com.spring.hibernate_demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import com.spring.hibernate_demo.dao.StudentDao;
import com.spring.hibernate_demo.entity.Student;
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

	@Bean 
	CommandLineRunner commandLineRunner(StudentDao studentDao) {
		return runner -> {
			createStudent(studentDao);
			// readStudent(studentDao);
			// queryForStudents(studentDao);
			// queryForStudentsByLastName(studentDao);
			// updateStudent(studentDao);
			// deleteStudent(studentDao);
			// deleteAllStudents(studentDao);
		};
	}

	/**
	 * Creates a new student and saves it to the database.
	 * @param studentDao the StudentDao instance used for database operations
	 */
	private void createStudent(StudentDao studentDao) {
		// Create a student object
		System.out.println("Creating new student object...");
		Student student = new Student("Fiel",
										"Omas-as",
										"fiel.omas-as@example.com");
		
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
