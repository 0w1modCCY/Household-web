package cz.cvut.fit.household.service.interfaces;

import cz.cvut.fit.household.datamodel.entity.Membership;

import java.util.List;

public interface MembershipService {

    Membership createMembership(Membership membership);

    List<Membership> findAllMemberships();
}
