package com.bbit.controller;

import com.bbit.NotFoundException;
import com.bbit.model.Course;
import com.bbit.model.University;
import com.bbit.repository.CourseRepository;
import com.bbit.repository.UniversityRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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

    @PostMapping
    public University createUniversity(@RequestBody University university) {
        return universityRepository.save(university);
    }

    @DeleteMapping(value = "{id}")
    public  void deleteUniversity(@PathVariable Long id) {

//        Method One
//        universityRepository.deleteById(id);

//        Method Two
        University university = findOneById(id);
        universityRepository.delete(university);
    }

    @PatchMapping(value = "{id}")
    public University university(@PathVariable Long id, @RequestBody University university) {

        University foundUniversity = findOneById(id);
        foundUniversity.setName(university.getName());
        foundUniversity.setLocation(university.getLocation());

        return universityRepository.save(foundUniversity);
    }

    //    Getting a course by university id
    @GetMapping(value = "{universityId}/courses")
    public List<Course> findCourseByUniversityId(@PathVariable Long universityId){
        return courseRepository.findByUniversityId(universityId);
    }

    @PostMapping(value = "universities/{universityId}/courses")
    public Course createCourse(@PathVariable Long universityId, @RequestBody Course course) {
        University uni = findOneById(universityId);
        course.setUniversity(uni);
        return courseRepository.save(course);
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

