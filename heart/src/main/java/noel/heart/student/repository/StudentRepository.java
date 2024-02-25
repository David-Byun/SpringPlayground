package noel.heart.student.repository;

import noel.heart.student.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    @Modifying
    @Query(value = "update Student s set s.heartCnt = s.student.heartCnt + :heartCnt where s.student.student_id = :id", nativeQuery = true)
    int updateHeartCntById(Student student);

}
