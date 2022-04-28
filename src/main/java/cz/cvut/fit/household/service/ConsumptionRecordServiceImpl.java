package cz.cvut.fit.household.service;

import cz.cvut.fit.household.datamodel.entity.ConsumptionRecord;
import cz.cvut.fit.household.exception.InvalidEntityException;
import cz.cvut.fit.household.exception.NonExistentEntityException;
import cz.cvut.fit.household.repository.ConsumptionRecordRepository;
import cz.cvut.fit.household.service.interfaces.ConsumptionRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class ConsumptionRecordServiceImpl implements ConsumptionRecordService {
    private ConsumptionRecordRepository consumptionRecordRepository;
    private ResourceTypeServiceImpl resourceTypeService;

    @Autowired
    public ConsumptionRecordServiceImpl(ConsumptionRecordRepository consumptionRecordRepository,
                                        ResourceTypeServiceImpl resourceTypeService) {
        this.consumptionRecordRepository = consumptionRecordRepository;
        this.resourceTypeService = resourceTypeService;
    }

    @Override
    public ConsumptionRecord createConsumptionRecord(ConsumptionRecord consumptionRecord,
                                                     String resourceName) {
        if(consumptionRecord.getId() != null)
            throw new InvalidEntityException("Record with id");
        consumptionRecord.setResourceType(resourceTypeService.createResourceType(resourceName));
        return consumptionRecordRepository.save(consumptionRecord);
    }

    @Override
    public ConsumptionRecord findConsumptionRecordById(Long id) {
        var consumptionRecord = consumptionRecordRepository.findById(id);
        if(consumptionRecord.isEmpty())
            throw new NonExistentEntityException("No record with such id");

        return consumptionRecord.get();
    }

    @Override
    public ConsumptionRecord updateConsumptionRecordById(Long id,
                                                         ConsumptionRecord consumptionRecord) {
        var recordToUpdate = consumptionRecordRepository.findById(id);

        if(recordToUpdate.isEmpty())
            throw new NonExistentEntityException("No record with such id");

        var record = setUpdates(recordToUpdate.get(), consumptionRecord);
        return consumptionRecordRepository.save(record);
    }

    @Override
    public Boolean deleteConsumptionRecordById(Long id) {
        if(consumptionRecordRepository.findById(id).isEmpty())
            throw new NonExistentEntityException("No record with such id");

        consumptionRecordRepository.deleteById(id);
        return true;
    }

    @Override
    public ConsumptionRecord countRecord(Long id) {
        var consumptionRecord = findConsumptionRecordById(id);
        countBill(consumptionRecord);
        return consumptionRecordRepository.save(consumptionRecord);
    }

    @Override
    public void countBill(ConsumptionRecord consumptionRecord) {
        consumptionRecord.setBill(consumptionRecord.getConsumption() *
                consumptionRecord.getResourceType().getTariff());
    }

    @Override
    public ConsumptionRecord setUpdates(ConsumptionRecord recordToUpdate,
                                        ConsumptionRecord otherRecord){
        recordToUpdate.setConsumption(otherRecord.getConsumption());
        recordToUpdate.setResourceType(otherRecord.getResourceType());
        recordToUpdate.setBill(otherRecord.getBill());
        return recordToUpdate;
    }
}
