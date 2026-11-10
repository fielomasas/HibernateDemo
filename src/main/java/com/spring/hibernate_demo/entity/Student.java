package com.spring.hibernate_demo.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;

import java.util.ArrayList;
import java.util.List;

/**
 * Entity class representing a Student.
 * This class is mapped to the "student" table in the database.
 */
@Entity
@Table(name = "student")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column (name = "first_name")
    private String firstName;

    @Column (name = "last_name")
    private String lastName;

    @Column (name = "email")
    private String email;

    @ManyToMany(mappedBy="students",
        fetch=FetchType.LAZY, cascade={CascadeType.DETACH, 
                                        CascadeType.MERGE, 
                                        CascadeType.PERSIST, 
                                        CascadeType.REFRESH})
    private List<Course> courses;

    /**
     * Creates an empty Student.
     */
    public Student() {
    }

    /**
     * Creates a Student with the given details.
     *
     * @param firstName the student's first name
     * @param lastName the student's last name
     * @param email the student's email
     */
    public Student(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    /**
     * Adds a Course to this Student.
     *
     * @param course the Course to add
     */
    public void addCourse(Course course) {
        if (courses == null) {
                courses = new ArrayList<>();
        }
        courses.add(course);
        course.addStudent(this);
    }

    // Getters and Setters
    /**
     * Gets the Student ID.
     *
     * @return the ID
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the Student ID.
     *
     * @param id the ID
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the student's first name.
     *
     * @return the first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the student's first name.
     *
     * @param firstName the first name
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Gets the student's last name.
     *
     * @return the last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the student's last name.
     *
     * @param lastName the last name
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Gets the student's email.
     *
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the student's email.
     *
     * @param email the email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets the student's Courses.
     *
     * @return the list of Courses
     */
    public List<Course> getCourses() {
        return courses;
    }

    /**
     * Sets the student's Courses.
     *
     * @param courses the list of Courses
     */
    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    /**
     * Returns a string representation of this Student.
     *
     * @return this Student as a string
     */
    @Override
    public String toString() {
        return "Student [id=" + id + 
                ", firstName=" + firstName + 
                ", lastName=" + lastName + 
                ", email=" + email + "]";
    }

}
