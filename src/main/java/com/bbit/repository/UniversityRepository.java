package com.bbit.repository;

import com.bbit.model.Course;
import com.bbit.model.University;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


// happens for every single table -- should have the id as well
public interface UniversityRepository extends JpaRepository<University, Long> {

    // select * from universities
    List<University> findAll();

}
