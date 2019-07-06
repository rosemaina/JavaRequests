package com.bbit.repository;

        import com.bbit.model.Course;
        import com.bbit.model.University;
        import org.springframework.data.jpa.repository.JpaRepository;

        import java.util.List;

public interface CourseRepository extends JpaRepository<Course,Long> {

    //    Translate to Select * from courses where university_id = {"long" id passed}
    List<Course>findByUniversityId(Long id);
    List<Course>findAll();
}
