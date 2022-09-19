package cz.cvut.fit.household.service;

import cz.cvut.fit.household.datamodel.entity.Copy;
import cz.cvut.fit.household.datamodel.entity.Item;
import cz.cvut.fit.household.exception.NonExistentEntityException;
import cz.cvut.fit.household.repository.ItemRepository;
import cz.cvut.fit.household.service.interfaces.ItemService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;

    public ItemServiceImpl(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public Item createOrUpdateItem(Item item) {
        return itemRepository.save(item);
    }

    @Override
    public void deleteItem(Long id) {
        itemRepository.deleteById(id);
    }

    @Override
    public Optional<Item> findItemById(Long id) {
        return itemRepository.findById(id);
    }

    public void addCopy(Copy copy, Long itemId) {
        itemRepository.findById(itemId).orElseThrow(() -> new NonExistentEntityException("item doesn't exist")).addCopy(copy);
    }

    public void removeCopy(Copy copy, Long itemId) {
        itemRepository.findById(itemId).orElseThrow(() -> new NonExistentEntityException("item doesn't exist")).removeCopy(copy);
    }
}
