package com.spring.hibernate_demo.dao;

import org.springframework.stereotype.Repository;

import com.spring.hibernate_demo.entity.Instructor;
import com.spring.hibernate_demo.entity.InstructorDetail;
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

    @Override 
    @Transactional
    public void save(Instructor instructor) {
        entityManager.persist(instructor);
    }

    @Override 
    @Transactional 
    public void save(Course course) {
        entityManager.persist(course);
    }

    @Override 
    public Instructor findInstructorById(int id) {
        return entityManager.find(Instructor.class, id);
    }

    /**    (non-Javadoc)
     * This method deletes an Instructor entity by its ID
     * Along with its courses
     */
    @Override
    @Transactional
    public void deleteInstructorWithCoursesById(int id) {
        // Find the instructor by ID
        Instructor instructor = entityManager.find(Instructor.class, id);
        
        entityManager.remove(instructor);
    }

    /**    (non-Javadoc)
     * This method deletes only the Instructor entity
     * And does not delete the courses
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
     * It breaks the association between the InstructorDetail and its associated Instructor 
     * by setting the instructorDetail field of the Instructor to null. 
     * Finally, it removes the InstructorDetail entity from the database.
     */
    @Override 
    @Transactional
    public void deleteInstructorDetailById(int id) {
        // Find the instructor detail by ID
        InstructorDetail instructorDetail = entityManager.find(InstructorDetail.class, id);

        // Break the association with the instructor
        instructorDetail.getInstructor().setInstructorDetail(null);
        
        // Remove the instructor detail
        entityManager.remove(instructorDetail);
    }

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

    @Override 
    public Course findCourseById(int courseId) {
        return entityManager.find(Course.class, courseId);
    }

    @Override 
    @Transactional 
    public void update(Instructor instructor) {
        entityManager.merge(instructor);
    }

    @Override 
    @Transactional 
    public void update(Course course) {
        entityManager.merge(course);
    }

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

    @Override 
    @Transactional 
    public void deleteCourseById(int id) {
        // Retrieve the course
        Course c = entityManager.find(Course.class, id);

        // Delete the course
        entityManager.remove(c);
    }
}
