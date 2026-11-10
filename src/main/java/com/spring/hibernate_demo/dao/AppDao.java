package com.spring.hibernate_demo.dao;

import com.spring.hibernate_demo.entity.Instructor;
import com.spring.hibernate_demo.entity.InstructorDetail;
import com.spring.hibernate_demo.entity.Course;
import com.spring.hibernate_demo.entity.Student;

import java.util.List;

public interface AppDao {
    void save(Instructor instructor);
    Instructor findInstructorById(int id);
    void deleteInstructorById(int id);
    void deleteInstructorWithCoursesById(int id);
    InstructorDetail findInstructorDetailById(int id);
    void deleteInstructorDetailById(int id);
    List<Course> findCoursesByInstructorId(int instructorId);
    Instructor findInstructorByIdJoinFetch(int id);
    void update(Instructor instructor);
    Course findCourseById(int courseId);
    void update(Course course);
    void save(Course course);
    Course findCourseAndReviewByCourseId(int id);
    void deleteCourseById(int id);
    Course findCourseAndStudentByCourseId(int id);
    Student findStudentAndCourseByStudentId(int id);
    void update(Student student);
    void deleteStudentById(int id);
}
