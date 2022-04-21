package cz.cvut.fit.household.service;

import cz.cvut.fit.household.datamodel.entity.Membership;
import cz.cvut.fit.household.repository.MembershipRepository;
import cz.cvut.fit.household.service.interfaces.MembershipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MembershipServiceImpl implements MembershipService {

    private final MembershipRepository membershipRepository;

    @Autowired
    public MembershipServiceImpl(MembershipRepository membershipRepository) {
        this.membershipRepository = membershipRepository;
    }

    @Override
    public Membership createMembership(Membership membership) {
        return membershipRepository.save(membership);
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
