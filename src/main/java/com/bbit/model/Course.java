package com.bbit.model;

import javax.persistence.*;

@Entity
@Table(name="courses")

public class Course {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="id")
    private Long id;

    @Column(name="course_name")
    private String courseName;

    @Column(name="course_description")
    private String courseDesciption;

//    Create a relationship between a course and a university
    @ManyToOne

//  Use the id to join the tables together
// When you create a course you have to provide a university_id
//    Spring will take the university obj, pick its id
    @JoinColumn(name="university_id")
    private University university;

    @Column(name = "maximum_enrollment")
    private int maximumEnrollment = 100;

    //    Constructor
    public Course(String courseName, University university) {
        this.courseName = courseName;
        this.university = university;
    }

    public int getMaximumEnrollment() {
        return maximumEnrollment;
    }

    public void setMaximumEnrollment(int maximumEnrollment) {
        this.maximumEnrollment = maximumEnrollment;
    }

//   Create a private constructor
    private Course() {
    }

//    Create your getters and setters
    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseDesciption() {
        return courseDesciption;
    }

    public void setCourseDesciption(String courseDesciption) {
        this.courseDesciption = courseDesciption;
    }

    public University getUniversity(Long universityId) {
        return university;
    }

    public void setUniversity(University university) {
        this.university = university;
    }
}
