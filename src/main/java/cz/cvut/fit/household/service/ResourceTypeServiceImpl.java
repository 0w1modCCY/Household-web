package cz.cvut.fit.household.service;

import cz.cvut.fit.household.datamodel.entity.resoursetypes.Electricity;
import cz.cvut.fit.household.datamodel.entity.resoursetypes.Gas;
import cz.cvut.fit.household.datamodel.entity.resoursetypes.ResourceType;
import cz.cvut.fit.household.datamodel.entity.resoursetypes.Water;
import cz.cvut.fit.household.exception.NonExistentEntityException;
import cz.cvut.fit.household.repository.ResourceTypeRepository;
import cz.cvut.fit.household.service.interfaces.ResourceTypeService;
import org.springframework.stereotype.Service;

@Service
public class ResourceTypeServiceImpl implements ResourceTypeService {

    private ResourceTypeRepository resourceTypeRepository;

    @Override
    public ResourceType createResourceType(String id) {
        var resource = resourceTypeRepository.findById(id);
        return resource.orElseGet(() -> resourceTypeRepository.save(extractResource(id)));
    }

    @Override
    public ResourceType updateResourceById(String id, ResourceType resourceType) {
        if(resourceTypeRepository.findById(id).isEmpty())
            throw new NonExistentEntityException("No resourceType with such id " + id);

        var resourceToUpdate = resourceTypeRepository.findById(id).get();

        resourceToUpdate.setMeasurement(resourceType.getMeasurement());
        resourceToUpdate.setTariff(resourceType.getTariff());

        return resourceTypeRepository.save(resourceToUpdate);
    }

    @Override
    public ResourceType findResourceById(String id) {
        if(resourceTypeRepository.findById(id).isEmpty())
            throw new NonExistentEntityException("No unit found with such id " + id);

        return resourceTypeRepository.findById(id).get();
    }

    @Override
    public Boolean deleteResourceById(String id) {
        if(resourceTypeRepository.findById(id).isEmpty())
            throw new NonExistentEntityException("No unit found with such id " + id);

        resourceTypeRepository.deleteById(id); return true;
    }

    @Override
    public ResourceType extractResource (String id){
        switch (id){
            case "Water":
                return new Water();
            case "Gas":
                return new Gas();
            case "Electricity":
                return new Electricity();
        }
        return null;
    }
}

