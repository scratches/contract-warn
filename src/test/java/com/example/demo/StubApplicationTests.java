package com.example.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureStubRunner(ids = "com.example:stubs")
public class StubApplicationTests {

	private RestTemplate rest = new RestTemplate();

	@Value("${stubrunner.runningstubs.stubs.port}")
	int port;

	@Test
	public void contextLoads() {
		ResponseEntity<String> entity = rest.getForEntity("http://localhost:" + port + "/", String.class);
		System.err.println(entity.getBody());
	}

}
