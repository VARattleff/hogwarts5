package dk.kea.dat3js.hogwarts5.students;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment =  SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class StudentControllerIntegrationTest {

    @Autowired
    private WebTestClient webClient;

    @Test
    void notNull() {
        assertNotNull(webClient);
    }

    @Test
    void createStudentWithNameParts() {
        webClient
                .post()
                .uri("/students")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue("""
                   {
                        "firstName": "Peter",
                        "middleName": "Heronimous",
                        "lastName": "Lind",
                        "house": "Gryffindor",
                        "schoolYear": 6,
                        "gender": "MAlE",
                        "isPrefect": false
                    }
                """)
                .exchange()
                .expectStatus().isCreated()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody()
                .jsonPath("$.id").exists()
                .jsonPath("$.firstName").isEqualTo("Peter")
                .jsonPath("$.middleName").isEqualTo("Heronimous")
                .jsonPath("$.lastName").isEqualTo("Lind")
                .jsonPath("$.house").isEqualTo("Gryffindor")
                .jsonPath("$.schoolYear").isEqualTo(6)
                .jsonPath("$.gender").isEqualTo("MAlE")
                .jsonPath("$.isPrefect").isEqualTo(false);
    }

    @Test
    void createStudentWithNamePartsTestJson() {
        webClient
                .post()
                .uri("/students")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue("""
                   {
                        "firstName": "Peter",
                        "middleName": "Heronimous",
                        "lastName": "Lind",
                        "house": "Gryffindor",
                        "schoolYear": 6,
                        "gender": "MAlE",
                        "isPrefect": false
                    }
                """)
                .exchange()
                .expectStatus().isCreated()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody().json("""
                  {
                        "firstName": "Peter",
                        "middleName": "Heronimous",
                        "lastName": "Lind",
                        "house": "Gryffindor",
                        "schoolYear": 6,
                        "gender": "MAlE",
                        "isPrefect": false
                    }
                """);
    }
}
