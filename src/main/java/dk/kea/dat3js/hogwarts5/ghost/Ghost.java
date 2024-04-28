package dk.kea.dat3js.hogwarts5.ghost;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import dk.kea.dat3js.hogwarts5.house.House;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Ghost {
    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    private String realName;
    @ManyToOne
    private House house;
}
