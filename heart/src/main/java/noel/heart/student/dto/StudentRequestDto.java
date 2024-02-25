package noel.heart.student.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import noel.heart.student.entity.Student;

@Getter
@Slf4j
@Builder
public class StudentRequestDto {
    private String name;
    private String password;

    public Student toEntity(StudentRequestDto dto) {
        return Student.builder()
                .name(dto.getName())
                .password(dto.getPassword())
                .build();
    }

    private static StudentRequestDto of(Student student) {
        return StudentRequestDto.builder()
                .name(student.getName())
                .password(student.getPassword())
                .build();
    }
}
