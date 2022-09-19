package cz.cvut.fit.household.service.interfaces;

import cz.cvut.fit.household.datamodel.entity.ConsumptionRecord;
import org.springframework.stereotype.Service;

@Service
public interface ConsumptionRecordService {
    ConsumptionRecord createConsumptionRecord (ConsumptionRecord consumptionRecord, String resourceName);

    ConsumptionRecord findConsumptionRecordById (Long id);

    ConsumptionRecord updateConsumptionRecordById (Long id, ConsumptionRecord consumptionRecord);

    Boolean deleteConsumptionRecordById (Long id);

    ConsumptionRecord countRecord (Long id);

    void countBill (ConsumptionRecord consumptionRecord);

    ConsumptionRecord setUpdates(ConsumptionRecord recordToUpdate,
                                 ConsumptionRecord otherRecord);

}
