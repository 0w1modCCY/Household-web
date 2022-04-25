package cz.cvut.fit.household.service;

import cz.cvut.fit.household.datamodel.entity.HouseHold;
import cz.cvut.fit.household.datamodel.entity.Membership;
import cz.cvut.fit.household.datamodel.entity.User;
import cz.cvut.fit.household.datamodel.enums.MembershipStatus;
import cz.cvut.fit.household.exception.NonExistentEntityException;
import cz.cvut.fit.household.repository.membership.MembershipRepository;
import cz.cvut.fit.household.repository.filter.MembershipFilter;
import cz.cvut.fit.household.service.interfaces.MembershipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class MembershipServiceImpl implements MembershipService {

    private final MembershipRepository membershipRepository;

    @Autowired
    public MembershipServiceImpl(MembershipRepository membershipRepository) {
        this.membershipRepository = membershipRepository;
    }

    @Override
    public Membership createMembership(Membership membership, User user, HouseHold houseHold) {
        user.addMembership(membership);
        houseHold.addMembership(membership);

        return membershipRepository.save(membership);
    }

    @Override
    @Transactional
    public List<Membership> filterMemberships(MembershipFilter membershipFilter) {
        return membershipRepository.filterMemberships(membershipFilter);
    }

    @Override
    public void acceptInvitation(Long membershipId) {
        Membership membership = membershipRepository.findById(membershipId)
                .orElseThrow(() -> new NonExistentEntityException("Membership with id: " + membershipId + " doesn't exist"));

        membership.setStatus(MembershipStatus.ACTIVE);
        membershipRepository.save(membership);
    }

    @Override
    public void declineInvitation(Long membershipId) {
        Membership membership = membershipRepository.findById(membershipId)
                .orElseThrow(() -> new NonExistentEntityException("Membership with id: " + membershipId + " doesn't exist"));

        membership.setStatus(MembershipStatus.DISABLED);
        membershipRepository.save(membership);
    }

    @Override
    public List<Membership> findAllMemberships() {
        return membershipRepository.findAll();
    }

    @Override
    public List<Membership> findMembershipsByUsername(String username) {
        return membershipRepository.findMembershipsByUsername(username);
    }
}
