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

/**
 *
 * Membership is a connection bridge between household and users, it stores basic information about current status of user in the household.
 * New instance of membership is created whenever new user joins Household {@link HouseHold}. Every user can have multiple
 * memberships (one for every household where he is a member).
 * Membership has three possible states: ACTIVE, PENDING and DISABLED.
 * ACTIVE - you are current member of the household.
 * PENDING - you are invited to the household, but you did not accept or decline invitation yet.
 * DISABLED - you either declined invitation or left household.
 *
 * @see HouseHold
 * @see Task
 * @see MembershipStatus
 */
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

