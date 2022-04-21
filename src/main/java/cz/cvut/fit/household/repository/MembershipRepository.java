package cz.cvut.fit.household.repository;

import cz.cvut.fit.household.datamodel.entity.Membership;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MembershipRepository extends JpaRepository<Membership, Long> {

    @Query(value = "select m from Membership m where m.user.username like %?1%")
    List<Membership> findMembershipsByUsername(String searchTerm);
}
