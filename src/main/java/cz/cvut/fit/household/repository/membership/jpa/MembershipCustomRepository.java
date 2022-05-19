package cz.cvut.fit.household.repository.membership.jpa;

import cz.cvut.fit.household.datamodel.entity.Membership;
import cz.cvut.fit.household.repository.filter.MembershipFilter;

import java.util.List;

/**
 * Contains a filter method for the searching of the members in the household
 */
public interface MembershipCustomRepository {

    /**
     * Searching for specific member in database, using filter class
     *
     * @param membershipFilter is special format for searching a matches in the household
     * @return list of memberships which are matched with filter parameter
     */
    List<Membership> filterMemberships(MembershipFilter membershipFilter);
}
