package cz.cvut.fit.household.repository.membership.jpa;

import cz.cvut.fit.household.datamodel.entity.Membership;
import cz.cvut.fit.household.repository.filter.MembershipFilter;

import java.util.List;

public interface MembershipCustomRepository {

    List<Membership> filterMemberships(MembershipFilter membershipFilter);
}
