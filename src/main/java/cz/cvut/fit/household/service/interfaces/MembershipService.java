package cz.cvut.fit.household.service.interfaces;

import cz.cvut.fit.household.datamodel.entity.HouseHold;
import cz.cvut.fit.household.datamodel.entity.Membership;
import cz.cvut.fit.household.datamodel.entity.User;
import cz.cvut.fit.household.datamodel.enums.MembershipStatus;
import cz.cvut.fit.household.exception.NonExistentEntityException;
import cz.cvut.fit.household.repository.filter.MembershipFilter;

import java.util.List;

public interface MembershipService {

    /**
     * Creating membership of specific user {@link User}, and specific
     * household {@link HouseHold}. Method adds new membership to user and household,
     * then it saves membership in database
     *
     * @param membership membership which is going to be added to database
     * @param user author of the membership
     * @param houseHold houseHold
     * @return created membership
     * @see User
     * @see HouseHold
     */
    Membership createMembership(Membership membership, User user, HouseHold houseHold);

    /**
     * Retrieve all existing memberships.
     *
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
     * of it to <b>"ACTIVE"</b>.
     *
     * @param membershipId is an id of needed membership
     * @throws NonExistentEntityException
     * @see MembershipStatus
     * @see HouseHold
     */
    void acceptInvitation(Long membershipId);

    /**
     * Searching for the membership, using id. Updating the status {@link MembershipStatus}
     * of it to <b>"DISABLE"</b>.
     *
     * @param membershipId is an id of needed membership
     * @throws NonExistentEntityException
     * @see MembershipStatus
     * @see HouseHold
     */
    void declineInvitation(Long membershipId);

    /**
     * Searching for the membership, using id. Updating the status {@link MembershipStatus}
     * of it to <b>"DISABLE"</b>.
     *
     * @param id of needed membership
     * @throws NonExistentEntityException
     * @see MembershipStatus
     * @see HouseHold
     */
    void leaveHousehold(Long id);

    /**
     * Searching for the members matching any parameters you want.
     *
     * @param membershipFilter is a format with right parameters
     * @return list of members, which matched with given filter-format
     */
    List<Membership> filterMemberships(MembershipFilter membershipFilter);
}
