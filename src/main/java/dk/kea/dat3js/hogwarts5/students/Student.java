package dk.kea.dat3js.hogwarts5.students;

import dk.kea.dat3js.hogwarts5.house.House;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class Student {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  private String firstName;
  private String middleName;
  private String lastName;
  @ManyToOne
  private House house;
  private Integer schoolYear; // 1-7

  //---------------------------------------
  // add gender and isPrefect

  private Gender gender;
  private Boolean isPrefect;

  public Gender getGender() {
    return gender;
  }

  public void setGender(Gender gender) {
    this.gender = gender;
  }

  public Boolean getPrefect() {
    return isPrefect;
  }

  public void setPrefect(Boolean prefect) {
    isPrefect = prefect;
  }

  //---------------------------------------

  public Student() {
  }

  public Student(String firstName, String lastName, House house, int schoolYear, Gender gender, Boolean isPrefect) {
    this(firstName, null, lastName, house, schoolYear, gender, isPrefect);
  }

  public Student(String firstName, String middleName, String lastName, House house, int schoolYear, Gender gender, Boolean isPrefect) {
    this.firstName = firstName;
    this.middleName = middleName;
    this.lastName = lastName;
    this.house = house;
    this.schoolYear = schoolYear;
    this.gender = gender;
    this.isPrefect = isPrefect;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getMiddleName() {
    return middleName;
  }

  public void setMiddleName(String middleName) {
    this.middleName = middleName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public House getHouse() {
    return house;
  }

  public void setHouse(House house) {
    this.house = house;
  }

  public Integer getSchoolYear() {
    return schoolYear;
  }

  public void setSchoolYear(Integer schoolYear) {
    this.schoolYear = schoolYear;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Student student = (Student) o;
    return Objects.equals(getFirstName(), student.getFirstName()) &&
            Objects.equals(getMiddleName(), student.getMiddleName()) &&
            Objects.equals(getLastName(), student.getLastName()) &&
            Objects.equals(getHouse().getName(), student.getHouse().getName()) &&
            Objects.equals(getGender(), student.getGender()) &&
            Objects.equals(getPrefect(), student.getPrefect());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getFirstName(), getMiddleName(), getLastName(), getHouse().getName(), getGender(), getPrefect());
  }
}
