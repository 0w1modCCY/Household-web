package cz.cvut.fit.household.datamodel.entity.resoursetypes;

import cz.cvut.fit.household.datamodel.entity.ConsumptionRecord;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 *
 *Class ResourceType is abstraction for the all types of resources,
 *which we have to record
 *
 *
 */
@NoArgsConstructor
@Getter
@Setter
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class ResourceType {
    @Id
    @NotBlank
    protected String id;

    @NotNull
    protected Double tariff;

    @NotBlank
    protected String measurement;

    @OneToMany(mappedBy = "resourceType")
    protected List<ConsumptionRecord> consumptionRecord;

    public ResourceType(String id, Double tariff, String measurement) {
        this.id = id;
        this.tariff = tariff;
        this.measurement = measurement;
    }

    @Override
    public String toString() {
        return "Resource " + this.id + " with tariff " + this.tariff + " per " + this.measurement;
    }
}
