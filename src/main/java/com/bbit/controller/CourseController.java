package com.bbit.controller;

import com.bbit.BadRequestException;
import com.bbit.NotFoundException;
import com.bbit.model.Course;
import com.bbit.model.University;
import com.bbit.repository.CourseRepository;
import com.bbit.repository.UniversityRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "courses")
public class CourseController {

    private final CourseRepository courseRepository;

    public CourseController(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }


    @GetMapping(value = "{id}/courses")
    public List<Course> findCourseByUniversityId(@PathVariable Long id) {
       return courseRepository.findByUniversityId(id);
    }

    @GetMapping(value = "{id}/add/{enrollement}")
    private Course add(@PathVariable Long id, @PathVariable int someValue) {
        Course course = courseRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Course not found"));

        int newEnrollmentCount = course.getMaximumEnrollment() + someValue;
//
//        if(newEnrollmentCount>7) {
//            throw new BadRequestException("Cannot enroll new students");
////        }

        course.setMaximumEnrollment(newEnrollmentCount);
        courseRepository.save(course);
        return course;

    }

    @PatchMapping(value = "{universityId}/courses/{id}")
    public void  course(@PathVariable Long id, @PathVariable Long universityId, @RequestBody Course course) {

//        Course foundCourse = findCourseByUniversityId(id);
//        foundCourse.setCourseName(course.getCourseName());
//        foundCourse.setCourseDesciption(course.getCourseDesciption());
//
//        return courseRepository.save(foundCourse);
    }

//    @GetMapping(value = "{id}")
//    private Course findOneById(@PathVariable Long id) {
//        Course course = courseRepository.findById(id).orElseThrow(
//                ()-> new NotFoundException("The Course with ID:" +id+ "cannot be found")
//        );
//
//        course.setCourseName("BBIT- Test Course Name");
//        course.setCourseDesciption("This is the description of the test name");
//        course.setUniversity("UON");
//        course.setMaximumEnrollment(30);
//        university.setName("New University Name");
//        universityRepository.save(university);
//        return university;
//
//
////        Optional<University> university = universityRepository.findById(id);
////
////        if (university.isPresent()) {
////            return  university.get();
////        } else {
////            return null;
////        }
//    }

//    @PatchMapping(value = "{id}")
//    public Course course(@PathVariable Long id, @RequestBody Course course) {
//
//        Course findC
//        Course foundCourse = findCourseByUniversityId(id);
//        foundCourse
//        foundCourse.
//
//        return universityRepository.save(foundUniversity);
//    }
}




//TO-DO
//    fetch the course by id
//    add the cvlaue passed to the maximum enrollment method
//    check if the new maximnum is exceeded by  7(after adding)
//     if yes throw an exception { throw a bad requiest}
//    if no add the value to the maximum enrollemnt and save

//    do another methoid call it "addToEnrollment()"
//        @GetMapping(value = "university/{id}/courses/{courseId}/add/{some value}")
//

