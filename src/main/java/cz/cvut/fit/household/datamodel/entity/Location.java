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
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @NotNull
    private HouseHold houseHold;

    @OneToMany(mappedBy = "location")
    private List<Copy> copies;

    @ManyToOne
    @JoinColumn(name = "main_id")
    private Location mainLocation;

    @OneToMany(mappedBy = "mainLocation", cascade = CascadeType.REMOVE)
    private List<Location> subLocations = new ArrayList<>();

    @NotBlank
    private String title;

    private String description;
}
