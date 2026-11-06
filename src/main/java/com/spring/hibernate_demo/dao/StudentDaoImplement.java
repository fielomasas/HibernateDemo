package com.spring.hibernate_demo.dao;

import org.springframework.stereotype.Repository;

import com.spring.hibernate_demo.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import java.util.List;
import jakarta.persistence.TypedQuery;

@Repository 
public class StudentDaoImplement implements StudentDao {

    // Define field for entity manager
    private EntityManager entityManager;

    // Constructor injection of entity manager
    public StudentDaoImplement(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    // Implementation for saving the student entity to the database
    @Override
    @Transactional
    public void save(Student student) {
        entityManager.persist(student);
    }

    // Implementation for finding a student entity by its ID
    @Override
    public Student findById(int id) {
        return entityManager.find(Student.class, id);
    }

    @Override 
    public List<Student> findAll() {
        // Create a query to retrieve all students from the database
        TypedQuery<Student> query = entityManager.createQuery(
            "FROM Student order by lastName", Student.class);

        // Execute the query and return the list of students
        return query.getResultList();
    }

    @Override
    public List<Student> findByLastName(String lastName) {
        // Create a query to retrieve students with the specified last name
        TypedQuery<Student> query = entityManager.createQuery(
            "FROM Student WHERE lastName=:data", Student.class);

        // Set the parameter for the last name in the query
        query.setParameter("data", lastName);

        // return the list of students with the specified last name
        return query.getResultList();
    }

    @Override
    @Transactional
    public void update(Student student) {
        entityManager.merge(student);
    }

    @Override
    @Transactional
    public void delete(int id) {
        // Retrieve the student entity based on the ID
        Student student = entityManager.find(Student.class, id);
        
        // Remove the student entity from the database
        entityManager.remove(student);
    }

    @Override
    @Transactional
    public int deleteAll() {
        // Create a query to delete all student entities from the database
        return entityManager.createQuery("DELETE FROM Student").executeUpdate();
    }

}
