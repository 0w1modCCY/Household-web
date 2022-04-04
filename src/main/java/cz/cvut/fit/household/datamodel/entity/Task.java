package cz.cvut.fit.household.datamodel.entity;

import cz.cvut.fit.household.datamodel.enums.TaskState;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    // TODO: CONSIDER USING OFFSETDATETIME
    @NotNull
    private LocalDateTime creationDate;

    // TODO: CONSIDER USING OFFSETDATETIME
    private LocalDateTime deadline;

//    @ManyToOne
//    private Membership author;

//    @ManyToOne
//    private Membership assignee;

//    @ManyToOne
//    @NotNull
//    private HouseHold houseHold;

    private TaskState state;
}
