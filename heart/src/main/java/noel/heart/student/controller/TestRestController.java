package noel.heart.student.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import noel.heart.student.dto.StudentRequestDto;
import noel.heart.student.service.StudentService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
public class TestRestController {

    private final StudentService studentService;

    @PostMapping
    public void updateHeartCnt(@RequestBody StudentRequestDto dto) {
        studentService.updateHeart(dto);
    }
}
