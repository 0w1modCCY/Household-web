package cz.cvut.fit.household.service.interfaces;

import cz.cvut.fit.household.datamodel.entity.HouseHold;
import cz.cvut.fit.household.datamodel.entity.Membership;
import cz.cvut.fit.household.datamodel.entity.User;
import cz.cvut.fit.household.repository.filter.MembershipFilter;

import java.util.List;

public interface MembershipService {

    Membership createMembership(Membership membership, User user, HouseHold houseHold);

    List<Membership> findAllMemberships();

    List<Membership> findMembershipsByUsername(String username);

    void acceptInvitation(Long membershipId);

    void declineInvitation(Long membershipId);

    void leaveHousehold(Long id);

    List<Membership> filterMemberships(MembershipFilter membershipFilter);
}
