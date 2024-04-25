package dk.kea.dat3js.hogwarts5.students;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/prefects")
public class PrefectsController {
    PrefectsService prefectsService;

    public PrefectsController(PrefectsService prefectsService) {
        this.prefectsService = prefectsService;
    }

    // POST /prefects - modtager en student (eller et student-id) og udnævner vedkommende til prefect

    @PostMapping("/{id}")
    public ResponseEntity<StudentResponseDTO> appointPrefect(@PathVariable int id) {
      return prefectsService.appointPrefect(id);
    }


    // GET /prefects/:id - returnerer en prefect (ud fra student-id) hvis den pågældende student **er** prefect

    // GET /prefects - returnerer en liste over alle prefects i alle houses

    // GET /prefects/house/{house} - returnerer en liste over alle prefects i det house
}
