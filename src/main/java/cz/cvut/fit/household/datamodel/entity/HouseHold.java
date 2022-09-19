package cz.cvut.fit.household.datamodel.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
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

    @OneToMany(mappedBy = "houseHold", cascade = CascadeType.REMOVE)
    private List<Membership> memberships = new ArrayList<>();

//    @OneToMany(mappedBy = "houseHold")
//    private List<Task> tasks;
//
//    @OneToMany(mappedBy = "houseHold")
//    private List<Location> locations;

    @OneToMany(mappedBy = "houseHold")
    private List<ConsumptionRecord> consumptionRecords;

    public void addMembership(Membership membership) {
        memberships.add(membership);
        membership.setHouseHold(this);
    }
}
