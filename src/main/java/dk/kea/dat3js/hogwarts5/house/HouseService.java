package dk.kea.dat3js.hogwarts5.house;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HouseService {
    private final HouseRepository houseRepository;

    public HouseService(HouseRepository houseRepository) {
        this.houseRepository = houseRepository;
    }

    public List<House> findAll() {
        return houseRepository.findAll();
    }

    public Optional<House> findById(String name) {
        return houseRepository.findById(name);
    }
}
