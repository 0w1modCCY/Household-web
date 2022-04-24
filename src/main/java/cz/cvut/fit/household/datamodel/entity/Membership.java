package cz.cvut.fit.household.datamodel.entity;

import cz.cvut.fit.household.datamodel.enums.MembershipStatus;
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

    private final LocalDate creationDate = LocalDate.now();

    @Enumerated(EnumType.STRING)
    private MembershipStatus status;

//    @OneToMany(mappedBy = "author")
//    private List<Task> createdTasks;

//    @OneToMany(mappedBy = "assignee")
//    private List<Task> assignedTasks;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private User user;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private HouseHold household;
}

