package cz.cvut.fit.household.repository;

import cz.cvut.fit.household.datamodel.entity.resoursetypes.ResourceType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResourceTypeRepository extends JpaRepository<ResourceType, String> {
}
