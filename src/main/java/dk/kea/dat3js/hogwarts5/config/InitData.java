package dk.kea.dat3js.hogwarts5.config;

import dk.kea.dat3js.hogwarts5.house.HouseRepository;
import dk.kea.dat3js.hogwarts5.students.Student;
import dk.kea.dat3js.hogwarts5.students.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import dk.kea.dat3js.hogwarts5.house.House;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class InitData implements CommandLineRunner {

  private final HouseRepository houseRepository;
  private final StudentRepository studentRepository;

  public InitData(HouseRepository houseRepository, StudentRepository studentRepository) {
    this.houseRepository = houseRepository;
    this.studentRepository = studentRepository;
  }

  @Override
  public void run(String... args) throws Exception {
    createHouses();
    createStudents();
  }

  private void createStudents() {
    // To avoid creating and re-creating the same students, we first get all those that already exist
    Set<Student> existingStudents = new HashSet<>();
    existingStudents.addAll(studentRepository.findAll());

    Student harry = new Student("Harry", "James", "Potter", gryffindor, 5);
    Student hermione = new Student("Hermione", "Jean", "Granger", gryffindor, 5);
    Student ron = new Student("Ron", "Bilius", "Weasley", gryffindor, 5);
    Student neville = new Student("Neville", "Frank", "Longbottom", gryffindor, 5);
    Student ginny = new Student("Ginny", "Molly", "Weasley", gryffindor, 5);
    Student fred = new Student("Fred", "Gideon", "Weasley", gryffindor, 5);
    Student george = new Student("George", "Fabian", "Weasley", gryffindor, 5);
    Student percy = new Student("Percy", "Ignatius", "Weasley", gryffindor, 5);

    Student draco = new Student("Draco", "", "Malfoy", slytherin, 5);
    Student cedric = new Student("Cedric", "", "Diggory", hufflepuff, 6);
    Student luna = new Student("Luna", "", "Lovegood", ravenclaw, 4);

    existingStudents.addAll(List.of(harry, hermione, ron, neville, ginny, fred, george, percy, draco, cedric, luna));
    studentRepository.saveAll(existingStudents);
  }

  private House gryffindor;
  private House slytherin;
  private House hufflepuff;
  private House ravenclaw;


  private void createHouses() {
      gryffindor = new House("Gryffindor", "Godric Gryffindor", new String[] {"red", "gold"});
      slytherin = new House("Slytherin", "Salazar Slytherin", new String[] {"green", "silver"});
      hufflepuff = new House("Hufflepuff", "Helga Hufflepuff", new String[] {"yellow", "black"});
      ravenclaw = new House("Ravenclaw", "Rowena Ravenclaw", new String[] {"blue", "bronze"});

     houseRepository.save(gryffindor);
      houseRepository.save(slytherin);
      houseRepository.save(hufflepuff);
      houseRepository.save(ravenclaw);


  }
}
