package com.example.demo;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.cloud.contract.wiremock.WireMockConfigurationCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.extension.responsetemplating.ResponseTemplateTransformer;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureWireMock(port = 0)
public class DemoApplicationTests {
	
	private RestTemplate rest = new RestTemplate();
	
	@Autowired
	private WireMockServer wiremock;

	@Test
	public void contextLoads() {
		wiremock.stubFor(get(urlEqualTo("/resource")).willReturn(aResponse().withTransformers("response-template")
				.withHeader("Content-Type", "text/plain").withBody("Hello World {{request.requestLine.port}}!")));
		ResponseEntity<String> entity = rest.getForEntity("http://localhost:" + wiremock.port() + "/resource", String.class);
		System.err.println(entity.getBody());
	}
	
	@Configuration
	static class TestConfiguration {
		@Bean
		public WireMockConfigurationCustomizer wireMockConfigurationCustomizer() {
			return config -> config.extensions(new ResponseTemplateTransformer(false));
		}
	}

}
