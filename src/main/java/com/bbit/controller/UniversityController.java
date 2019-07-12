package com.bbit.controller;

import com.bbit.NotFoundException;
import com.bbit.model.Course;
import com.bbit.model.University;
import com.bbit.repository.CourseRepository;
import com.bbit.repository.UniversityRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "universities")

public class UniversityController {

    private final UniversityRepository universityRepository;
    private final CourseRepository courseRepository;

//    Constructor Initialization
    public UniversityController(UniversityRepository universityRepository, CourseRepository courseRepository) {
        this.universityRepository = universityRepository;
        this.courseRepository = courseRepository;
    }

    @GetMapping
    private List<University> getAllUniversities() {
        return universityRepository.findAll();
    }

//    localHost:8080/universities/1
    @GetMapping(value = "{id}")
    private University findOneById(@PathVariable Long id) {
        University university = universityRepository.findById(id).orElseThrow(
                ()-> new NotFoundException("No university with ID:" +id+ "is found")
                );

//        university.setName("New Name");
        universityRepository.save(university);
        return university;


//        Optional<University> university = universityRepository.findById(id);
//
//        if (university.isPresent()) {
//            return  university.get();
//        } else {
//            return null;
//        }
    }

//    CREATE A UNIVERSITY
    @PostMapping
    public University createUniversity(@RequestBody University university) {
        return universityRepository.save(university);
    }

//    DELETE A UNIVERSITY
    @DeleteMapping(value = "{id}")
    public  void deleteUniversity(@PathVariable Long id) {

//        Method One
//        universityRepository.deleteById(id);

//        Method Two
        University university = findOneById(id);
        universityRepository.delete(university);
    }

//    EDIT UNIVERSITY DETAILS
    @PatchMapping(value = "{id}")
    public University university(@PathVariable Long id, @RequestBody University university) {

        University foundUniversity = findOneById(id);
        foundUniversity.setName(university.getName());
        foundUniversity.setLocation(university.getLocation());

        return universityRepository.save(foundUniversity);
    }

    //    GET ALL COURSES FOR ONE UNIVERSITY
    @GetMapping(value = "{universityId}/courses")
    public List<Course> findCourseByUniversityId(@PathVariable Long universityId){
        return courseRepository.findByUniversityId(universityId);
    }

//    CREATE A COURSE
    @PostMapping(value = "{id}/courses")
    public Course createCourse(@PathVariable Long id, @RequestBody Course course) {
        University uni = findOneById(id);
        course.setUniversity(uni);
        return courseRepository.save(course);
    }

    //    DELETE A COURSE
    @DeleteMapping(value = "{universityId}/courses/{id}")
    public void deleteCourse(@PathVariable Long id) {
        courseRepository.deleteById(id);
    }

//    @PatchMapping(value = "{id}")
//    public Course course(@PathVariable Long id, @RequestBody Course course) {
//
//        Course foundCourse = findOneById(id);
//        foundUniversity.setName(university.getName());
//        foundUniversity.setLocation(university.getLocation());
//
//        return universityRepository.save(foundUniversity);
//    }
}

