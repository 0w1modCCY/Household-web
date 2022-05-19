package cz.cvut.fit.household.service.interfaces;

import cz.cvut.fit.household.datamodel.entity.HouseHold;
import cz.cvut.fit.household.datamodel.entity.Membership;
import cz.cvut.fit.household.datamodel.entity.User;

import java.util.List;
import java.util.Optional;

/**
 * CRUD interface for households {@link HouseHold}. Also, interface has several
 * searching methods for the memberships{@link Membership}, and another households
 * using different parameters.
 *
 * @see HouseHold
 * @see Membership
 */
public interface HouseHoldService {

    /**
     * @param houseHold which has to be saved
     * @return freshly saved household
     */
    HouseHold createOrUpdateHousehold(HouseHold houseHold);

    /**
     * @return all existed households in database
     */
    List<HouseHold> findAllHouseholds();

    /**
     * Searching for the households, which has member with given username
     *
     * @param username of member{@link Membership}
     * @return list of households, which linked to given username
     * @see Membership
     */
    List<HouseHold> findHouseholdsByUsername(String username);

    /**
     * Searching for members{@link Membership} from the specific household, using id
     *
     * @param id of the household
     * @return list of members in the household, with a given id
     * @see Membership
     */
    List<Membership> findMembershipsByHouseholdId(Long id);

    /**
     * Searching for household, using id
     *
     * @param id of the household
     * @return household, if it exists by given id, otherwise null
     */
    Optional<HouseHold> findHouseHoldById(Long id);

    /**
     * Searching and deleting of the household, using id
     *
     * @param id of the household
     */
    void deleteHouseholdById(Long id);

}
