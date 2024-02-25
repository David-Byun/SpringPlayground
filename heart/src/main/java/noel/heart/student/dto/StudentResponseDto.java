package noel.heart.student.dto;

import lombok.Builder;
import lombok.Getter;
import noel.heart.student.entity.Student;

@Builder
@Getter
public class StudentResponseDto {

    private String name;
    private String password;

    public static StudentResponseDto of(Student student) {
        return StudentResponseDto.builder()
                .name(student.getName())
                .password(student.getPassword())
                .build();
    }
}
