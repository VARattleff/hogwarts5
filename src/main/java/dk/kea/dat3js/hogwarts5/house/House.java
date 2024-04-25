package dk.kea.dat3js.hogwarts5.house;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class House {
  @Id
  private String name;
  private String founder;
  private String color1;
  private String color2;

  public House() {}

  public House(String name, String founder, String[] colors) {
    this.name = name;
    this.founder = founder;
    this.color1 = colors[0];
    this.color2 = colors[1];
  }

  public String getName() {
    return name;
  }

  public String getFounder() {
    return founder;
  }

  public String[] getColors() {
    return new String[] {color1, color2};
  }
}
