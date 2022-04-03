package cz.cvut.fit.household.datamodel.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class HouseHold {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    private String title;

    @OneToMany(mappedBy = "houseHold")
    @NotNull
    private List<Membership> memberships;

    @OneToMany(mappedBy = "houseHold")
    private List<Task> tasks;

    @OneToMany(mappedBy = "houseHold")
    private List<Location> locations;
}
