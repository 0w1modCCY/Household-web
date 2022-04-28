package cz.cvut.fit.household.service.interfaces;

import cz.cvut.fit.household.datamodel.entity.resoursetypes.ResourceType;
import org.springframework.stereotype.Service;

@Service
public interface ResourceTypeService {
    ResourceType createResourceType(String id);

    ResourceType updateResourceById(String id, ResourceType resourceType);

    ResourceType findResourceById(String id);

    Boolean deleteResourceById(String id);

    ResourceType extractResource (String id);
}
