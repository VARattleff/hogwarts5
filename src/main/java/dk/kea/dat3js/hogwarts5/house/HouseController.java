package dk.kea.dat3js.hogwarts5.house;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/houses")
public class HouseController {

  private final HouseService houseService;

  public HouseController(HouseService houseService) {
    this.houseService = houseService;
  }

  // get all houses
  @GetMapping
  public List<House> getAllHouses() {
    return houseService.findAll();
  }

  // get house by id
  @GetMapping("/{id}")
  public ResponseEntity<House> getHouse(@PathVariable String id) {
    return ResponseEntity.of(houseService.findById(id));
  }

}
