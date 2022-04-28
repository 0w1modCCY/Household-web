package cz.cvut.fit.household.datamodel.entity.resoursetypes;

import javax.persistence.Entity;

@Entity
public class Water extends ResourceType {
    public Water() {
        super("Water", 90.04d, "M^3");
    }
}
