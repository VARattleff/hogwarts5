package dk.kea.dat3js.hogwarts5.students;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment =  SpringBootTest.WebEnvironment.RANDOM_PORT)
public class StudentControllerIntegrationTest {

    @Autowired
    private WebTestClient webClient;

    @Test
    void notNull() {
        assertNotNull(webClient);
    }

    @Test
    void createStudentWithFullName() {
        webClient
                .post()
                .uri("/students")
                .bodyValue("""
                           {
                                "firstName": "Peter",
                                "middleName": "Heronimous",
                                "lastName": "Lind",
                                "house": "Gryffindor",
                                "schoolYear": 6,
                                "gender": "MAlE",
                                "isPrefect": true
                            }
                        """)
                .exchange();
    }
}

