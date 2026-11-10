package com.spring.hibernate_demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.CascadeType;

@Entity
@Table(name="instructor_detail")
public class InstructorDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name="id")
    private int id;

    @Column (name="subject")
    private String subject;

    @Column (name="hobby")
    private String hobby;

    // @OneToOne (mappedBy = "instructorDetail", cascade = CascadeType.ALL)
    // private Instructor instructor;

    @OneToOne (mappedBy = "instructorDetail", 
                cascade = {CascadeType.DETACH, CascadeType.MERGE,
                            CascadeType.PERSIST, CascadeType.REFRESH})
    private Instructor instructor;

    /**
     * Creates an empty InstructorDetail.
     */
    public InstructorDetail() {
    }

    /**
     * Creates an InstructorDetail with a subject and hobby.
     *
     * @param subject the instructor's subject
     * @param hobby the instructor's hobby
     */
    public InstructorDetail(String subject, String hobby) {
        this.subject = subject;
        this.hobby = hobby;
    }

    /**
     * Gets the InstructorDetail ID.
     *
     * @return the ID
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the InstructorDetail ID.
     *
     * @param id the ID
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the subject.
     *
     * @return the subject
     */
    public String getSubject() {
        return subject;
    }

    /**
     * Sets the subject.
     *
     * @param subject the subject
     */
    public void setSubject(String subject) {
        this.subject = subject;
    }

    /**
     * Gets the hobby.
     *
     * @return the hobby
     */
    public String getHobby() {
        return hobby;
    }

    /**
     * Sets the hobby.
     *
     * @param hobby the hobby
     */
    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    /**
     * Gets the associated Instructor.
     *
     * @return the Instructor
     */
    public Instructor getInstructor() {
        return instructor;
    }

    /**
     * Sets the associated Instructor.
     *
     * @param instructor the Instructor
     */
    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }

    /**
     * Returns a string representation of this InstructorDetail.
     *
     * @return this InstructorDetail as a string
     */
    @Override
    public String toString() {
        return "InstructorDetail [id=" + id + 
                    ", subject=" + subject + 
                    ", hobby=" + hobby + 
                    "]";
    }
}
