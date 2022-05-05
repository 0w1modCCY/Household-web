package cz.cvut.fit.household.repository.household.jpa;

import cz.cvut.fit.household.datamodel.entity.HouseHold;
import cz.cvut.fit.household.repository.household.AbstractHouseHoldRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HouseHoldRepository extends JpaRepository<HouseHold, Long>, AbstractHouseHoldRepository {
}
