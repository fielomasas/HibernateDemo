package com.spring.hibernate_demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.CascadeType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.FetchType;

import java.util.List;
import java.util.ArrayList;

@Entity
@Table(name="instructor")
public class Instructor {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column (name="id")
    private int id;

    @Column (name="first_name")
    private String firstName;

    @Column (name="last_name")
    private String lastName;

    @Column (name="email")
    private String email;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="instructor_detail_id")
    private InstructorDetail instructorDetail;

    @OneToMany (mappedBy = "instructor",
                fetch = FetchType.LAZY, 
                cascade = {CascadeType.DETACH, 
                            CascadeType.MERGE, 
                            CascadeType.PERSIST, 
                            CascadeType.REFRESH})
    private List<Course> courses;

    /**
     * Creates an empty Instructor.
     */
    public Instructor() {
    }

    /**
     * Creates an Instructor with the given details.
     *
     * @param firstName the instructor's first name
     * @param lastName the instructor's last name
     * @param email the instructor's email
     */
    public Instructor(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    /**
     * Adds a Course to this Instructor.
     *
     * @param tcourse the Course to add
     */
    public void add(Course tcourse) {
        // Initialize the courses list if it's null
        if (this.courses == null) {
            this.courses = new ArrayList<>();
        }

        // Add the course to the list
        this.courses.add(tcourse);

        // Set the instructor of the course to this instructor
        // 'this' refers to the current instance of the Instructor class
        tcourse.setInstructor(this);

    }

    /**
     * Gets the Instructor ID.
     *
     * @return the ID
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the Instructor ID.
     *
     * @param id the ID
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the instructor's first name.
     *
     * @return the first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the instructor's first name.
     *
     * @param firstName the first name
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Gets the instructor's last name.
     *
     * @return the last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the instructor's last name.
     *
     * @param lastName the last name
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Gets the instructor's email.
     *
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the instructor's email.
     *
     * @param email the email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets the InstructorDetail.
     *
     * @return the instructor detail
     */
    public InstructorDetail getInstructorDetail() {
        return instructorDetail;
    }

    /**
     * Sets the InstructorDetail.
     *
     * @param instructorDetail the instructor detail
     */
    public void setInstructorDetail(InstructorDetail instructorDetail) {
        this.instructorDetail = instructorDetail;
    }

    /**
     * Gets the instructor's Courses.
     *
     * @return the list of Courses
     */
    public List<Course> getCourses() {
        return courses;
    }

    /**
     * Sets the instructor's Courses.
     *
     * @param courses the list of Courses
     */
    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    /**
     * Returns a string representation of this Instructor.
     *
     * @return this Instructor as a string
     */
    @Override
    public String toString() {
        return "Instructor [id=" + id + 
                    ", firstName=" + firstName + 
                    ", lastName=" + lastName + 
                    ", email=" + email + 
                    "]";
    }
}
