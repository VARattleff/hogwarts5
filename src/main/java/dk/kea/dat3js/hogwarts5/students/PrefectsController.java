package dk.kea.dat3js.hogwarts5.students;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/prefects")
public class PrefectsController {
    PrefectsService prefectsService;

    public PrefectsController(PrefectsService prefectsService) {
        this.prefectsService = prefectsService;
    }

    // POST /prefects - modtager en student (eller et student-id) og udnævner vedkommende til prefect, hvis reglerne for prefect udnævnelse er opfyldt
    @PostMapping("/appointPrefect/{id}")
    public ResponseEntity<StudentResponseDTO> appointPrefect(@PathVariable int id) {
      return prefectsService.appointPrefect(id);
    }

    // GET /prefects/:id - returner en prefect (ud fra student-id) hvis den pågældende student er prefect
    @GetMapping("/{id}")
    public ResponseEntity<StudentResponseDTO> getPrefectById(@PathVariable int id) {
      return prefectsService.getPrefectById(id);
    }

    // GET /prefects - returnerer en liste over alle prefects i alle houses
    @GetMapping("/getAllPrefects")
    public ResponseEntity<List<StudentResponseDTO>> getAllPrefects() {
      return prefectsService.getAllPrefects();
    }

    // GET /prefects/house/{house} - returnerer en liste over alle prefects i det house
}
