package cz.cvut.fit.household.datamodel.entity;

import cz.cvut.fit.household.datamodel.entity.resoursetypes.ResourceType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ConsumptionRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    private LocalDate startDate;

    @NotNull
    private LocalDate endDate;

    private Double bill;

    @NotNull
    private Double consumption;

    @ManyToOne
    private ResourceType resourceType;

    @ManyToOne
    private HouseHold houseHold;
}
