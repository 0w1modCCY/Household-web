package cz.cvut.fit.household.service;

import cz.cvut.fit.household.datamodel.entity.HouseHold;
import cz.cvut.fit.household.datamodel.entity.Membership;
import cz.cvut.fit.household.repository.HouseHoldRepository;
import cz.cvut.fit.household.service.interfaces.HouseHoldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class HouseHoldServiceImpl implements HouseHoldService {

    private final HouseHoldRepository houseHoldRepository;

    @Autowired
    public HouseHoldServiceImpl(HouseHoldRepository houseHoldRepository) {
        this.houseHoldRepository = houseHoldRepository;
    }

    @Override
    public HouseHold createOrUpdateHousehold(HouseHold houseHold) {
        return houseHoldRepository.save(houseHold);
    }

    @Override
    public List<HouseHold> findAllHouseholds() {
        return houseHoldRepository.findAll();
    }

    @Override
    public List<HouseHold> findHouseholdsByUsername(String username) {
        List<HouseHold> houseHolds = houseHoldRepository.findAll();
        List<HouseHold> resultHouseholds = new ArrayList<>();

        for (HouseHold houseHold : houseHolds) {
            for (Membership membership : houseHold.getMemberships()) {
                if (membership.getUser().getUsername().equals(username)) {
                    resultHouseholds.add(houseHold);
                }
            }
        }

        return resultHouseholds;
    }

    @Override
    public Optional<HouseHold> findHouseHoldById(Long id) {
        return houseHoldRepository.findById(id);
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
