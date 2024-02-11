package com.alibou.reactive;

import com.alibou.reactive.student.Student;
import com.alibou.reactive.student.StudentService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ReactiveDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReactiveDemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(StudentService service) {
		return args -> {
			for (int i = 0; i < 100; i++) {
				service.save(Student.builder()
						.firstname("Ali " + i).lastname("Bouali " + i).age(i).build()).subscribe();
			}
		};
	}
}