package cz.cvut.fit.household.repository.membership;

import cz.cvut.fit.household.datamodel.entity.Membership;
import cz.cvut.fit.household.repository.AbstractRepository;
import cz.cvut.fit.household.repository.filter.MembershipFilter;

import java.util.List;

public interface AbstractMembershipRepository extends AbstractRepository<Long, Membership> {
}
