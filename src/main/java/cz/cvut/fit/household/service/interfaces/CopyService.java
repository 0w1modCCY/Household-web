package cz.cvut.fit.household.service.interfaces;

import cz.cvut.fit.household.datamodel.entity.Copy;
import cz.cvut.fit.household.datamodel.entity.Item;

import java.util.Optional;

public interface CopyService {
    Copy createOrUpdateCopy(Copy copy);
    void deleteCopy(Long id);
    Optional<Copy> findCopyById(Long id);
}
