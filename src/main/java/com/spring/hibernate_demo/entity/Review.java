package com.spring.hibernate_demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity 
@Table (name="review")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="comments")
    private String comments;

    /**
     * Gets the Review ID.
     *
     * @return the ID
     */
    public int getId() {
        return id;
    }

    /**
     * Returns a string representation of this Review.
     *
     * @return this Review as a string
     */
    @Override
    public String toString() {
        return "Review [id=" 
                + id + ", comments=" 
                + comments + "]";
    }

    /**
     * Sets the Review ID.
     *
     * @param id the ID
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the review comments.
     *
     * @return the comments
     */
    public String getComments() {
        return comments;
    }

    /**
     * Sets the review comments.
     *
     * @param comments the comments
     */
    public void setComments(String comments) {
        this.comments = comments;
    }

    /**
     * Creates an empty Review.
     */
    public Review() {
    }

    /**
     * Creates a Review with the given comments.
     *
     * @param comments the review comments
     */
    public Review(String comments) {
        this.comments = comments;
    }
}
