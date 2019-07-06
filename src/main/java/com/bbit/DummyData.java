package com.bbit;

import com.bbit.model.Course;
import com.bbit.model.University;
import com.bbit.repository.CourseRepository;
import com.bbit.repository.UniversityRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class DummyData implements CommandLineRunner {

    private  final UniversityRepository universityRepository;
    private final CourseRepository courseRepository;

    public DummyData(UniversityRepository universityRepository, CourseRepository courseRepository) {
        this.universityRepository = universityRepository;
        this.courseRepository = courseRepository;
    }

    @Override
    public void run(String... args) throws Exception {

//        Saving an array as a list
        University jkuat = new University("JKUAT","Juja");
        University strath = new University("Strathmore","Ole Sangale");

        universityRepository.saveAll(Arrays.asList(jkuat, strath));

//        OR THIS IMPLEMENTATION
//        universityRepository.saveAll(Arrays.asList(
//                new University("Strathmore", "LOe"),
//                new University("JKUAT", "Juja ")
//        ));

//        Saving one item to the DataBase
        University uon = new University("UON", "Nairobi");
        universityRepository.save(uon);

        University moi = new University("MOI University", "Eldoret");
        universityRepository.save(moi);


        Course course1 = new Course("API", moi);
        courseRepository.save(course1);

        Course course2 = new Course( "Automate", uon);
        courseRepository.save(course2);
        Course course3 = new Course( "Automate", uon);
        courseRepository.save(course3);
        Course course4 = new Course( "Automate", uon);
        courseRepository.save(course4);

    }
}
