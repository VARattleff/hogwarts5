package dk.kea.dat3js.hogwarts5.ghost;

import dk.kea.dat3js.hogwarts5.errorhandling.exception.NotFoundException;
import dk.kea.dat3js.hogwarts5.house.HouseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/ghosts")
public class GhostController {

    HouseService houseService;
    private List<Ghost> ghost = new ArrayList<>();

    public GhostController(HouseService houseService) {
        this.houseService = houseService;
          initGhost();
    }

    private void initGhost(){
        ghost.add(new Ghost(1, "Nearly Headless Nick", "Sir Nicholas de Mimsy-Porpington", houseService.findById("Gryffindor").orElseThrow(NoSuchElementException::new)));
        ghost.add(new Ghost(2, "The Grey Lady", "Helena Ravenclaw", houseService.findById("Ravenclaw").orElseThrow(NoSuchElementException::new)));
        ghost.add(new Ghost(3, "The Fat Friar", "Unknown", houseService.findById("Hufflepuff").orElseThrow(NoSuchElementException::new)));
        ghost.add(new Ghost(4, "The Bloody Baron", "Unknown", houseService.findById("Slytherin").orElseThrow(NoSuchElementException::new)));
        }

    @GetMapping
    public List<Ghost> getAllGhost(){
        initGhost();
        return ghost;
    }

    @GetMapping("/{name}")
    public ResponseEntity<Ghost> getGhost(@PathVariable String name){
        initGhost();
        return ResponseEntity.of(ghost.stream().filter(g -> g.getName().equals(name)).findFirst());
    }

}