package dk.kea.dat3js.hogwarts5.students;

import dk.kea.dat3js.hogwarts5.errorhandling.exception.NotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
public class StudentServiceTest {

    @Autowired
    private StudentService studentService;

    @Autowired
    private StudentRepository studentRepository;

    @Test
    void appointPrefectValidationCheck() {
        Student student = studentRepository.findById(1)
                .orElseThrow(() -> new NotFoundException("Student with id " + 1 + " not found"));

        Boolean canBeAppointed = studentService.appointPrefectValidationCheck(student.getId());

        assertTrue(canBeAppointed);

        Student otherStudent = studentRepository.findById(3)
                .orElseThrow(() -> new NotFoundException("Student with id " + 3 + " not found"));
        otherStudent.setPrefect(true);
        studentRepository.save(otherStudent);

        canBeAppointed = studentService.appointPrefectValidationCheck(student.getId());

        assertFalse(canBeAppointed);
    }
}