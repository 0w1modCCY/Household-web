package cz.cvut.fit.household.datamodel.entity.resoursetypes;

import javax.persistence.Entity;

@Entity
public class Electricity extends ResourceType {
    public Electricity() {
        super("Electricity", 5.65, "kWh");
    }
}
