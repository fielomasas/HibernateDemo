package com.spring.hibernate_demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.ArrayList;
import java.util.List;

@Entity 
@Table (name="course")
public class Course {
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name="id")
    private int id;

    @Column (name="title")
    private String title;

    @ManyToOne(cascade = {CascadeType.DETACH,
                            CascadeType.MERGE, 
                            CascadeType.PERSIST, 
                            CascadeType.REFRESH})
    @JoinColumn (name="instructor_id")
    private Instructor instructor;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name="course_id")
    private List<Review> review;

    @ManyToMany(fetch=FetchType.LAZY,
                cascade={CascadeType.DETACH, CascadeType.MERGE,
                            CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(name="course_student",
                joinColumns=@JoinColumn(name="course_id"),
                inverseJoinColumns=@JoinColumn(name="student_id"))
    private List<Student> students;

    /**
     * Creates an empty Course.
     */
    public Course() {
    }

    /**
     * Creates a Course with the given title.
     *
     * @param title the course title
     */
    public Course(String title) {
        this.title = title;
    }

    /**
     * Adds a review to this Course.
     *
     * @param rev the review to add
     */
    public void addReview(Review rev) {
        if (review == null) {
            review = new ArrayList<>();
        }
        review.add(rev);
    }

    /**
     * Adds a student to this Course.
     *
     * @param student the student to add
     */
    public void addStudent(Student student) {
        if(students == null) {
            students = new ArrayList<>();
        }
        students.add(student);
    }

    /**
     * Gets the Course ID.
     *
     * @return the Course ID
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the Course ID.
     *
     * @param id the Course ID
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the Course title.
     *
     * @return the Course title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the Course title.
     *
     * @param title the Course title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Gets the Instructor for this Course.
     *
     * @return the Instructor
     */
    public Instructor getInstructor() {
        return instructor;
    }

    /**
     * Sets the Instructor for this Course.
     *
     * @param instructor the Instructor
     */
    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }

    /**
     * Gets the reviews for this Course.
     *
     * @return the Course reviews
     */
    public List<Review> getReview() {
        return review;
    }

    /**
     * Sets the reviews for this Course.
     *
     * @param review the Course reviews
     */
    public void setReview(List<Review> review) {
        this.review = review;
    } 
    
    /**
     * Gets the students enrolled in this Course.
     *
     * @return the enrolled students
     */
    public List<Student> getStudents() {
        return students;
    }

    /**
     * Sets the students enrolled in this Course.
     *
     * @param students the enrolled students
     */
    public void setStudents(List<Student> students) {
        this.students = students;
    }

    /**
     * Returns a string representation of this Course.
     *
     * @return this Course as a string
     */
    @Override
    public String toString() {
        return "Course [id=" + id + 
                ", title=" + title +  
                "]";
    }
}
