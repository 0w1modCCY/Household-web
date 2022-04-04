package cz.cvut.fit.household.datamodel.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Membership {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    // TODO: CONSIDER USING OFFSETDATETIME
    @NotNull
    private LocalDate creationDate;

//    @OneToMany(mappedBy = "author")
//    private List<Task> createdTasks;

//    @OneToMany(mappedBy = "assignee")
//    private List<Task> assignedTasks;

//    @ManyToOne
//    private User user;

//    @ManyToOne
//    @NotNull
//    private HouseHold houseHold;
}

