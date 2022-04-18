package cz.cvut.fit.household.service.interfaces;

import cz.cvut.fit.household.datamodel.entity.HouseHold;
import cz.cvut.fit.household.datamodel.entity.Membership;

import java.util.List;

public interface HouseHoldService {

    HouseHold createOrUpdateHousehold(HouseHold houseHold);

    List<HouseHold> findAllHouseholds();

    List<HouseHold> findHouseholdsByUsername(String username);

    List<Membership> findMembershipsByHouseholdId(Long id);

    void deleteHouseholdById(Long id);

}
