package cz.cvut.fit.household.service;

import cz.cvut.fit.household.datamodel.entity.Category;
import cz.cvut.fit.household.datamodel.entity.Copy;
import cz.cvut.fit.household.repository.CopyRepository;
import cz.cvut.fit.household.service.interfaces.CopyService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CopyServiceImpl implements CopyService {

    private final CopyRepository copyRepository;

    public CopyServiceImpl(CopyRepository copyRepository) {
        this.copyRepository = copyRepository;
    }

    @Override
    public Copy createOrUpdateCopy(Copy copy) {
        return copyRepository.save(copy);
    }

    @Override
    public void deleteCopy(Long id) {
        copyRepository.deleteById(id);
    }

    @Override
    public Optional<Copy> findCopyById(Long id) {
        return copyRepository.findById(id);
    }
}
