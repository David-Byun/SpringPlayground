package jwt.redis;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Collections;

@SpringBootTest
class RedisApplicationTests {

	@Autowired
	WebClient webClient;

	//GET : employees
	public Flux<Employee> findAll() {
		return webClient.get()
				.uri("/employees")
				.retrieve()
				.bodyToFlux(Employee.class);
	}

	//GET : /employees/{id} Request single employee by id as Mono
	public Mono<Employee> findById(Integer id) {
		return webClient.get()
				.uri("/employees/" + id)
				.retrieve()
				.bodyToMono(Employee.class);
	}

	public Mono<Employee> create(Employee employee) {
		
	}

	WebClient client = WebClient.builder()
			.baseUrl("http://localhost:8080")
			.defaultCookie("cookieKey", "cookieValue")
			.defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
			// Map객체 하나만 저장
			.defaultUriVariables(Collections.singletonMap("url", "http://localhost:8080"))
			.build();




	@Test
	void contextLoads() {
	}

}
