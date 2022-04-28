package cz.cvut.fit.household.datamodel.entity.resoursetypes;

import javax.persistence.Entity;

@Entity
public class Gas extends ResourceType {
    public Gas() {
        super("Gas", 21.6, "L");
    }
}
