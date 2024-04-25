package dk.kea.dat3js.hogwarts5.students;

import dk.kea.dat3js.hogwarts5.errorhandling.exception.NotFoundException;
import dk.kea.dat3js.hogwarts5.errorhandling.exception.ValidationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PrefectsService {
    StudentRepository studentRepository;
    StudentService studentService;

    public PrefectsService(StudentRepository studentRepository, StudentService studentService) {
        this.studentRepository = studentRepository;
        this.studentService = studentService;
    }


    public Boolean appointPrefectValidationCheck(int id){
        Student student = studentRepository.findById(id).orElseThrow(() -> new NotFoundException("Student with id " + id + " not found"));

        List<Student> prefectsInSameHouse = studentRepository.findAll().stream()
                .filter(s -> s.getHouse().equals(student.getHouse()) && s.getPrefect())
                .toList();

        if (prefectsInSameHouse.size() >= 2) {
            return false;
        }

        if (prefectsInSameHouse.size() == 1) {
            if (prefectsInSameHouse.getFirst().getGender().equals(student.getGender())) {
                return false;
            }
        }

        return student.getSchoolYear() >= 5;
    }

    public ResponseEntity<StudentResponseDTO> appointPrefect(int id) {
        Optional<Student> studentOptional = studentRepository.findById(id);
        if(studentOptional.isPresent()) {
            Student student = studentOptional.orElseThrow(() -> new NotFoundException("Student with id " + id + " not found"));
            if(appointPrefectValidationCheck(id)){
                student.setPrefect(true);
                studentRepository.save(student);
                return ResponseEntity.ok().body(studentService.toDTO(student));
            } else {
                throw new ValidationException("Student with id " + id + " could not be appointed as prefect");
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    public ResponseEntity<StudentResponseDTO> getPrefectById(int id) {
        Student student = studentRepository.findById(id).orElseThrow(() -> new NotFoundException("Student with id " + id + " not found"));
        if (!student.getPrefect()) {
            throw new ValidationException("Student with id " + id + " is not a prefect");
        }
        return ResponseEntity.ok().body(studentService.toDTO(student));
    }

    public ResponseEntity<List<StudentResponseDTO>> getAllPrefects() {
        List<Student> prefects = studentRepository.findAll().stream()
                .filter(Student::getPrefect)
                .collect(Collectors.toList());

        if (prefects.isEmpty()) {
            throw new ValidationException("No prefects found");
        }

        return ResponseEntity.ok().body(studentService.toDTOList(prefects));
    }

    public ResponseEntity<List<StudentResponseDTO>> getPrefectsByHouse(String house) {
        List<Student> prefects = studentRepository.findAll().stream()
                .filter(s -> s.getHouse().getName().equals(house) && s.getPrefect())
                .collect(Collectors.toList());

        if (prefects.isEmpty()) {
            throw new ValidationException("No prefects found in house " + house);
        }

        return ResponseEntity.ok().body(studentService.toDTOList(prefects));
    }
}
