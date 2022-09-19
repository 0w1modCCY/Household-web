package cz.cvut.fit.household.service.interfaces;

import cz.cvut.fit.household.datamodel.entity.Item;

import java.util.Optional;

public interface ItemService {
    Item createOrUpdateItem(Item item);
    void deleteItem(Long id);
    Optional<Item> findItemById(Long id);
}
