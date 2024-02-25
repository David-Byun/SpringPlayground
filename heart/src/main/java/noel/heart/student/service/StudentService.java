package noel.heart.student.service;

import lombok.RequiredArgsConstructor;
import noel.heart.student.dto.StudentRequestDto;
import noel.heart.student.entity.Student;
import noel.heart.student.repository.StudentRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;

    public void updateHeart(StudentRequestDto dto) {
        Student student = dto.toEntity(dto);
        studentRepository.updateHeartCntById(student);
    }

}
