package com.altimetrik.playgound;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PlaygroundApplicationTests {

	@MockBean
	TestRestTemplate testRestTemplate;

	@Test
	public void sucess() {
		ResponseEntity<String> response= testRestTemplate.getForEntity("http://localhost:8080/wordbuilder?phrase=this%20is%20my%20input%20phrase&length=3", String.class);
	    Assertions.assertThat(response.getStatusCode()).isEqualByComparingTo(HttpStatus.OK);
	}
	
	@Test
	public void moreThanBound() {
		ResponseEntity<String> response=testRestTemplate.getForEntity("http://localhost:8080/wordbuilder?phrase=this%20is%20my%20input%20phrase&length=13", String.class);
	    Assertions.assertThat(response.getStatusCode()).isEqualByComparingTo(HttpStatus.BAD_REQUEST);
	}

}
