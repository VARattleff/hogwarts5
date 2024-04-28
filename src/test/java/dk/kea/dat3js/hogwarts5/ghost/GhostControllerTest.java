package dk.kea.dat3js.hogwarts5.ghost;

import dk.kea.dat3js.hogwarts5.house.House;
import dk.kea.dat3js.hogwarts5.house.HouseRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.mockito.Mockito.when;

@WebMvcTest(GhostController.class)
@ComponentScan(basePackageClasses = GhostController.class)
class GhostControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private HouseRepository houseRepository;

    @BeforeEach
    void setUp() {
        when(houseRepository.findById("Gryffindor")).thenReturn(Optional.of(new House(1, "Gryffindor")));
        when(houseRepository.findById("Ravenclaw")).thenReturn(Optional.of(new House(2, "Ravenclaw")));
        when(houseRepository.findById("Hufflepuff")).thenReturn(Optional.of(new House(3, "Hufflepuff")));
        when(houseRepository.findById("Slytherin")).thenReturn(Optional.of(new House(4, "Slytherin")));
    }

    @Test
    void getAllGhost() throws Exception {
        mockMvc.perform(get("/ghosts"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(4)))
                .andExpect(jsonPath("$[*].name").value(containsInAnyOrder(
                        "Nearly Headless Nick",
                        "The Grey Lady",
                        "The Fat Friar",
                        "The Bloody Baron"
                )));
    }

    @Test
    void getGhost() throws Exception {
        mockMvc.perform(get("/ghosts/Nearly Headless Nick"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Nearly Headless Nick"))
                .andExpect(jsonPath("$.realName").value("Sir Nicholas de Mimsy-Porpington"))
                .andExpect(jsonPath("$.house").value("Gryffindor"));
    }
}