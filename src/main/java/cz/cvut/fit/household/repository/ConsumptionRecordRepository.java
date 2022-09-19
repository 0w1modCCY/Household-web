package cz.cvut.fit.household.repository;

import cz.cvut.fit.household.datamodel.entity.ConsumptionRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConsumptionRecordRepository extends JpaRepository<ConsumptionRecord, Long> {
}
