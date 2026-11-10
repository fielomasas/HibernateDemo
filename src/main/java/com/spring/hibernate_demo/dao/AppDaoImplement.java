package com.spring.hibernate_demo.dao;

import org.springframework.stereotype.Repository;

import com.spring.hibernate_demo.entity.Instructor;
import com.spring.hibernate_demo.entity.InstructorDetail;
import com.spring.hibernate_demo.entity.Student;
import com.spring.hibernate_demo.entity.Course;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

import java.util.List;

@Repository 
public class AppDaoImplement implements AppDao{
    private EntityManager entityManager;
    
    public AppDaoImplement(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    /**    (non-Javadoc)
     * This method saves an Instructor entity.
     *
     * @param instructor the Instructor entity to save
     */
    @Override 
    @Transactional
    public void save(Instructor instructor) {
        entityManager.persist(instructor);
    }

    /**    (non-Javadoc)
     * This method saves a Course entity.
     *
     * @param course the Course entity to save
     */
    @Override 
    @Transactional 
    public void save(Course course) {
        entityManager.persist(course);
    }

    /**    (non-Javadoc)
     * This method finds an Instructor entity by its ID.
     *
     * @param id the ID of the Instructor to find
     * @return the matching Instructor, or {@code null} if none exists
     */
    @Override 
    public Instructor findInstructorById(int id) {
        return entityManager.find(Instructor.class, id);
    }

    /**    (non-Javadoc)
     * This method removes an Instructor entity by its ID.
     * The Instructor mapping does not cascade removal to its courses,
     * so this method does not delete them 
     * and may fail if they still reference the Instructor.
     *
     * @param id the ID of the Instructor to remove
     */
    @Override
    @Transactional
    public void deleteInstructorWithCoursesById(int id) {
        // Find the instructor by ID
        Instructor instructor = entityManager.find(Instructor.class, id);
        
        entityManager.remove(instructor);
    }

    /**    (non-Javadoc)
     * This method removes an Instructor entity 
     * by its ID without deleting its courses.
     * It clears the association between the Instructor 
     * and each of its courses first.
     *
     * @param id the ID of the Instructor to remove
     */
    @Override
    @Transactional
    public void deleteInstructorById(int id) {
        // Find the instructor by ID
        Instructor instructor = entityManager.find(Instructor.class, id);

        // Break the associations of courses for instructor
        List<Course> courses = instructor.getCourses();
        for(Course c : courses) {
            // Remove the instructor from the courses
            c.setInstructor(null);
        }

        // Delete only the Instructor
        // Does not include the courses associated
        entityManager.remove(instructor);
    }

    /**    (non-Javadoc)
     * This method finds an InstructorDetail entity by its ID.
     *
     * @param id the ID of the InstructorDetail to find
     */
    @Override
    public InstructorDetail findInstructorDetailById(int id) {
        return entityManager.find(InstructorDetail.class, id);
    }


    /**    (non-Javadoc)
     * This method deletes an InstructorDetail entity by its ID.
     * Along with its Instructor
     */
    // @Override 
    // @Transactional
    // public void deleteInstructorDetailById(int id) {
    //     // Find the instructor detail by ID
    //     InstructorDetail instructorDetail = entityManager.find(InstructorDetail.class, id);
        
    //     // Remove the instructor detail
    //     entityManager.remove(instructorDetail);
    // }

    /**    (non-Javadoc)
     * This method deletes an InstructorDetail entity by its ID.  
     * It breaks the association between the InstructorDetail 
     * and its associated Instructor by setting the
     * instructorDetail field of the Instructor to null. 
     * Finally, it removes the InstructorDetail entity from the database.
     */
    @Override 
    @Transactional
    public void deleteInstructorDetailById(int id) {
        // Find the instructor detail by ID
        InstructorDetail instructorDetail = entityManager.find(
                                                InstructorDetail.class, id);

        // Break the association with the instructor
        instructorDetail.getInstructor().setInstructorDetail(null);
        
        // Remove the instructor detail
        entityManager.remove(instructorDetail);
    }

    /**    (non-Javadoc)
     * This method finds the Courses assigned to an Instructor.
     *
     * @param instructorId the ID of the Instructor
     * @return the matching Courses, or an empty list if there are none
     */
    @Override
    public List<Course> findCoursesByInstructorId(int instructorId) {
        // Create Query
        TypedQuery<Course> query = entityManager.createQuery(
            "FROM Course WHERE instructor.id=:data", Course.class
        );
        query.setParameter("data", instructorId);

        List<Course> courses = query.getResultList();
        return courses;
    }

    // @Override 
    // public Instructor findInstructorByJoinFetch(int id) {
    //     // Create query
    //     TypedQuery<Instructor> query = entityManager.createQuery(
    //         "SELECT i from Instructor i JOIN FETCH i.courses WHERE i.id=:data",
    //         Instructor.class);
    //     query.setParameter("data", id);

    //     Instructor ins = query.getSingleResult();
    //     return ins;
    // }

    /**    (non-Javadoc)
     * This method finds an Instructor and 
     * fetches its Courses and InstructorDetail.
     * The query returns only Instructors 
     * that have both associated records.
     *
     * @param id the ID of the Instructor
     * @return the matching Instructor with its Courses 
     * and InstructorDetail loaded
     */
    @Override 
    public Instructor findInstructorByIdJoinFetch(int id) {
        // Create query
        TypedQuery<Instructor> query = entityManager.createQuery(
            "SELECT i from Instructor i "
            + "JOIN FETCH i.courses "
            + "JOIN FETCH i.instructorDetail "
            + "WHERE i.id=:data",
            Instructor.class);
        query.setParameter("data", id);

        Instructor ins = query.getSingleResult();
        return ins;
    }

    /**    (non-Javadoc)
     * This method finds a Course entity by its ID.
     *
     * @param courseId the ID of the Course to find
     * @return the matching Course, or {@code null} if none exists
     */
    @Override 
    public Course findCourseById(int courseId) {
        return entityManager.find(Course.class, courseId);
    }

    /**    (non-Javadoc)
     * This method updates an Instructor entity.
     *
     * @param instructor the Instructor entity to update
     */
    @Override 
    @Transactional 
    public void update(Instructor instructor) {
        entityManager.merge(instructor);
    }

    /**    (non-Javadoc)
     * This method updates a Course entity.
     *
     * @param course the Course entity to update
     */
    @Override 
    @Transactional 
    public void update(Course course) {
        entityManager.merge(course);
    }

    /**    (non-Javadoc)
     * This method updates a Student entity.
     *
     * @param student the Student entity to update
     */
    @Override 
    @Transactional 
    public void update(Student student) {
        entityManager.merge(student);
    }

    /**    (non-Javadoc)
     * This method finds a Course and fetches its Reviews.
     *
     * @param id the ID of the Course
     * @return the matching Course with its Reviews loaded
     */
    @Override 
    public Course findCourseAndReviewByCourseId(int id){
        // Create query
        TypedQuery<Course> query = entityManager.createQuery(
            "SELECT c FROM Course c "
            + "JOIN FETCH c.review "
            + "WHERE c.id=:data", Course.class);
        query.setParameter("data", id);

        // Execute query
        Course c = query.getSingleResult();
        return c;
    }

    /**    (non-Javadoc)
     * This method removes a Course entity by its ID.
     *
     * @param id the ID of the Course to remove
     */
    @Override 
    @Transactional 
    public void deleteCourseById(int id) {
        // Retrieve the course
        Course c = entityManager.find(Course.class, id);

        // Delete the course
        entityManager.remove(c);
    }

    /**    (non-Javadoc)
     * This method finds a Course and fetches its Students.
     *
     * @param id the ID of the Course
     * @return the matching Course with its Students loaded
     */
    @Override 
    public Course findCourseAndStudentByCourseId(int id) {
        // Create Query
        TypedQuery<Course> query = entityManager.createQuery(
            "SELECT c FROM Course c "
            + " JOIN FETCH c.students "
            + " WHERE c.id=:data", Course.class);
        query.setParameter("data", id);
        
        // Execute Query
        Course course = query.getSingleResult();
        return course;
    }

    /**    (non-Javadoc)
     * This method finds a Student and 
     * fetches the Courses they are enrolled in.
     *
     * @param id the ID of the Student
     * @return the matching Student with their Courses loaded
     */
    @Override 
    public Student findStudentAndCourseByStudentId(int id) {
        // Create Query
        TypedQuery<Student> query = entityManager.createQuery(
            "SELECT s FROM Student s "
            + "JOIN FETCH s.courses "
            + "WHERE s.id=:data", Student.class);
        query.setParameter("data", id);

        // Execute Query
        Student student = query.getSingleResult();
        return student;
    }

    /**    (non-Javadoc)
     * This method removes a Student entity 
     * by its ID without deleting its Courses.
     * It clears the Student-to-Course associations before removal.
     *
     * @param id the ID of the Student to remove
     */
    @Override 
    @Transactional 
    public void deleteStudentById(int id) {
        // Retreive Student
        Student student = entityManager.find(Student.class, id);

        if(student != null) {
            // Get the Courses
            List<Course> courses = student.getCourses();

            // Break the associations
            for(Course c : courses) {
                c.getStudents().remove(student);
            }
        }

        // Delete only the student
        // Does not include the courses associated
        entityManager.remove(student);
    }
}
