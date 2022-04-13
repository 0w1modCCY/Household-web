package cz.cvut.fit.household.service;

import cz.cvut.fit.household.datamodel.entity.HouseHold;
import cz.cvut.fit.household.datamodel.entity.Membership;
import cz.cvut.fit.household.repository.HouseHoldRepository;
import cz.cvut.fit.household.service.interfaces.HouseHoldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HouseHoldServiceImpl implements HouseHoldService {

    private final HouseHoldRepository houseHoldRepository;

    @Autowired
    public HouseHoldServiceImpl(HouseHoldRepository houseHoldRepository) {
        this.houseHoldRepository = houseHoldRepository;
    }

    @Override
    public HouseHold createHousehold(HouseHold houseHold) {
        return houseHoldRepository.save(houseHold);
    }

    @Override
    public List<HouseHold> findAllHouseholds() {
        return houseHoldRepository.findAll();
    }

    @Override
    public List<Membership> findMembershipsByHouseholdId(Long id) {
        return houseHoldRepository.findById(id).get().getMemberships();
    }

    @Override
    public void deleteHouseholdById(Long id) {
        houseHoldRepository.deleteById(id);
    }
}
