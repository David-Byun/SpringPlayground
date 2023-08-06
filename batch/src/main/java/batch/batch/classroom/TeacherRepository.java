package batch.batch.classroom;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepository extends JpaRepository<Student, Long> {
}
