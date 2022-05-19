package cz.cvut.fit.household.service.interfaces;

import cz.cvut.fit.household.datamodel.entity.HouseHold;
import cz.cvut.fit.household.datamodel.entity.Membership;
import cz.cvut.fit.household.datamodel.entity.User;
import cz.cvut.fit.household.datamodel.enums.MembershipStatus;
import cz.cvut.fit.household.exception.NonExistentEntityException;
import cz.cvut.fit.household.repository.filter.MembershipFilter;

import java.util.List;

/**
 * Interface which provides basic CRUD operations over members {@link Membership}, and
 * handling of invitation procedure
 *
 * @see Membership
 */
public interface MembershipService {

    /**
     * Creating membership of specific user {@link User}, and specific
     * household {@link HouseHold}. Method adds new membership to user and household,
     * then it saves membership in database
     *
     * @param membership which is going to be added to database
     * @param user whose will have new membership
     * @param houseHold which will has new member
     * @return freshly saved membership
     * @see User
     * @see HouseHold
     */
    Membership createMembership(Membership membership, User user, HouseHold houseHold);

    /**
     * @return all existed memberships in database
     */
    List<Membership> findAllMemberships();

    /**
     * @param username of needed memberships
     * @return list of memberships, which have matches with given username
     */
    List<Membership> findMembershipsByUsername(String username);

    /**
     * Searching for the membership, using id. Updating the status {@link MembershipStatus}
     * of it to <b>"ACTIVE"</b>. Member is accepted to another household {@link HouseHold}.
     *
     * @param membershipId is an id of needed membership
     * @throws NonExistentEntityException
     * @see MembershipStatus
     * @see HouseHold
     */
    void acceptInvitation(Long membershipId);

    /**
     * Searching for the membership, using id. Updating the status {@link MembershipStatus}
     * of it to <b>"DISABLE"</b>. Member declined household {@link HouseHold}.
     *
     * @param membershipId is an id of needed membership
     * @throws NonExistentEntityException
     * @see MembershipStatus
     * @see HouseHold
     */
    void declineInvitation(Long membershipId);

    /**
     * Searching for the membership, using id. Updating the status {@link MembershipStatus}
     * of it to <b>"DISABLE"</b>. Member left household {@link HouseHold}.
     *
     * @param id of needed membership
     * @throws NonExistentEntityException
     * @see MembershipStatus
     * @see HouseHold
     */
    void leaveHousehold(Long id);

    /**
     * Searching for the members, using any specific format
     *
     * @param membershipFilter is a format with right parameters
     * @return list of members, which matched with given filter-format
     */
    List<Membership> filterMemberships(MembershipFilter membershipFilter);
}
