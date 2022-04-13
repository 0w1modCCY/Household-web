package cz.cvut.fit.household.repository;

import cz.cvut.fit.household.datamodel.entity.HouseHold;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HouseHoldRepository extends JpaRepository<HouseHold, Long> {
}
