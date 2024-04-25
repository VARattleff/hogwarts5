package dk.kea.dat3js.hogwarts5.students;

import dk.kea.dat3js.hogwarts5.errorhandling.exception.NotFoundException;
import dk.kea.dat3js.hogwarts5.house.HouseService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
  private final StudentRepository studentRepository;
  private final HouseService houseService;

  public StudentService(StudentRepository studentRepository, HouseService houseService) {
    this.studentRepository = studentRepository;
    this.houseService = houseService;
  }

  public List<StudentResponseDTO> findAll() {
    return studentRepository.findAll().stream().map(this::toDTO).toList();
  }

  public Optional<StudentResponseDTO> findById(int id) {
    return studentRepository.findById(id).map(this::toDTO);
  }

  public StudentResponseDTO save(StudentRequestDTO student) {
    return toDTO(studentRepository.save(fromDTO(student)));
  }

  public Optional<StudentResponseDTO> updateIfExists(int id, StudentRequestDTO student) {
    if (studentRepository.existsById(id)) {
      Student existingStudent = fromDTO(student);
      existingStudent.setId(id);
      return Optional.of(toDTO(studentRepository.save(existingStudent)));
    } else {
      throw new NotFoundException("Student with id " + id + " not found");
    }
  }

  public Optional<StudentResponseDTO> partialUpdate(int id, StudentRequestDTO student) {
    Optional<Student> existingStudent = studentRepository.findById(id);
    if(existingStudent.isPresent()) {
      Student studentToUpdate = existingStudent.get();
      if(student.firstName() != null) {
        studentToUpdate.setFirstName(student.firstName());
      }
      if(student.middleName() != null) {
        studentToUpdate.setMiddleName(student.middleName());
      }
      if(student.lastName() != null) {
        studentToUpdate.setLastName(student.lastName());
      }
      if(student.house() != null) {
        studentToUpdate.setHouse(houseService.findById(student.house()).orElseThrow());
      }
      if(student.schoolYear() != null) {
        studentToUpdate.setSchoolYear(student.schoolYear());
      }
      if(student.gender() != null) {
        studentToUpdate.setGender(student.gender());
      }
        if(student.isPrefect() != null) {
            studentToUpdate.setPrefect(student.isPrefect());
        }
      return Optional.of(toDTO(studentRepository.save(studentToUpdate)));
    } else {
      throw new NotFoundException("Student with id " + id + " not found");
    }
  }

  public Optional<StudentResponseDTO> deleteById(int id) {
    Optional<StudentResponseDTO> existingStudent = studentRepository.findById(id).map(this::toDTO);
    if(existingStudent.isPresent()) {
      studentRepository.deleteById(id);
      return existingStudent;
    } else {
      throw new NotFoundException("Student with id " + id + " not found");
    }
  }

  private StudentResponseDTO toDTO(Student studentEntity) {
      return new StudentResponseDTO(
        studentEntity.getId(),
        studentEntity.getFirstName(),
        studentEntity.getMiddleName(),
        studentEntity.getLastName(),
        studentEntity.getHouse().getName(),
        studentEntity.getSchoolYear(),
        studentEntity.getGender(),
        studentEntity.getPrefect()
    );
  }

  private Student fromDTO(StudentRequestDTO studentDTO) {
    return new Student(
            studentDTO.firstName(),
            studentDTO.middleName(),
            studentDTO.lastName(),
            houseService.findById(studentDTO.house()).orElseThrow(() -> new NotFoundException("House with id " + studentDTO.house() + " not found")),
            studentDTO.schoolYear(),
            studentDTO.gender(),
            studentDTO.isPrefect()
    );
  }
}
