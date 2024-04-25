package dk.kea.dat3js.hogwarts5.students;

import org.springframework.stereotype.Service;

@Service
public class PrefectsService {
    StudentRepository studentRepository;

    public PrefectsService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }


}
