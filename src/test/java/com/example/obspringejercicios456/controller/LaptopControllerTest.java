package com.example.obspringejercicios456.controller;

import com.example.obspringejercicios456.model.Laptop;
import io.swagger.models.Response;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import springfox.documentation.annotations.ApiIgnore;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class LaptopControllerTest {

    private TestRestTemplate testRestTemplate;
    @Autowired
    private RestTemplateBuilder restTemplateBuilder;
    @LocalServerPort
    private int port;

    @BeforeEach
    void setUp() {
        restTemplateBuilder = restTemplateBuilder.rootUri("http://localhost:" + port);
        testRestTemplate = new TestRestTemplate(restTemplateBuilder);
    }

    @Test
    void getAll() {
        ResponseEntity<Laptop[]> response = testRestTemplate.getForEntity("/api/laptops", Laptop[].class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, Arrays.stream(response.getBody()).count());
    }

    @Test
    void findOneById() {
        ResponseEntity<Laptop> response = testRestTemplate.getForEntity("/api/laptops/1", Laptop.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void findOneByIdFail() {
        ResponseEntity<Laptop> response = testRestTemplate.getForEntity("/api/laptops/4", Laptop.class);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void create() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        String jsonToSend = """
                {
                    "marca":"lenovo",
                    "peso": 2.4
                }
                """;

        HttpEntity<String> request = new HttpEntity<>(jsonToSend,headers);

        ResponseEntity<Laptop> response = testRestTemplate.exchange("/api/laptops", HttpMethod.POST, request, Laptop.class);
        Laptop laptopFromRequest = response.getBody();

        assertEquals("lenovo", laptopFromRequest.getMarca());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void update() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        String jsonToSend = """
                {
                    "id": 1,
                    "marca":"huawei",
                    "peso": 1.4
                }
                """;

        HttpEntity<String> request = new HttpEntity<>(jsonToSend,headers);
        ResponseEntity<Laptop> response = testRestTemplate.exchange("/api/laptops/", HttpMethod.PUT, request, Laptop.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("huawei", response.getBody().getMarca());
    }



    @Test
    void deleteOne() {
        testRestTemplate.delete("/api/laptops/1");
        ResponseEntity<Laptop> foundById = testRestTemplate.getForEntity("/api/laptops/1", Laptop.class);
        assertEquals(404, foundById.getStatusCodeValue());
    }

    @Test
    void deleteAll() {
        testRestTemplate.delete("/api/laptops");
        ResponseEntity<Laptop[]> response = testRestTemplate.getForEntity("/api/laptops", Laptop[].class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}