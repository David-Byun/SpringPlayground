package batch.batch.classroom;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ClassInformationRepository extends JpaRepository<Student, Long> {
}
