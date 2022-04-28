package cz.cvut.fit.household.controller;

import cz.cvut.fit.household.datamodel.entity.HouseHold;
import cz.cvut.fit.household.datamodel.entity.Membership;
import cz.cvut.fit.household.datamodel.entity.User;
import cz.cvut.fit.household.datamodel.enums.MembershipStatus;
import cz.cvut.fit.household.repository.filter.MembershipFilter;
import cz.cvut.fit.household.service.interfaces.HouseHoldService;
import cz.cvut.fit.household.service.interfaces.MembershipService;
import cz.cvut.fit.household.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class HouseholdController {

    private final HouseHoldService householdService;
    private final UserService userService;
    private final MembershipService membershipService;

    @Autowired
    public HouseholdController(HouseHoldService householdService, MembershipService membershipService, UserService userService) {
        this.householdService = householdService;
        this.membershipService = membershipService;
        this.userService = userService;
    }

    @GetMapping("/households/add")
    public String renderCreateHouseholdPage(Model model) {
        model.addAttribute("houseHold", new HouseHold());
        return "add-household";
    }

    @PostMapping("/households/add")
    public String createHousehold(Authentication authentication, Model model,  @ModelAttribute HouseHold houseHold) {
        User user = userService.findUserByUsername(authentication.getName())
                .orElseThrow(() -> new RuntimeException("Authenticated user no longer exists in the database"));

        Membership membership = new Membership();
        membership.setStatus(MembershipStatus.ACTIVE);

        membershipService.createMembership(membership, user, houseHold);;

        MembershipFilter pendingHouseholds = MembershipFilter.builder()
                .username(authentication.getName())
                .status(MembershipStatus.PENDING)
                .build();

        MembershipFilter activeHouseholds = MembershipFilter.builder()
                .username(authentication.getName())
                .status(MembershipStatus.ACTIVE)
                .build();

        model.addAttribute("pendingHouseholds", membershipService.filterMemberships(pendingHouseholds));
        model.addAttribute("activeHouseholds", membershipService.filterMemberships(activeHouseholds));
        return "welcome";
    }

    @GetMapping("/household/{id}/members")
    public String renderHouseholdMembersPage(Model model, @PathVariable Long id) {

        model.addAttribute("householdId", id);

        MembershipFilter pendingMember = MembershipFilter.builder()
                .householdId(id)
                .status(MembershipStatus.PENDING)
                .build();

        MembershipFilter activeMember = MembershipFilter.builder()
                .householdId(id)
                .status(MembershipStatus.ACTIVE)
                .build();

        model.addAttribute("pendingMembers", membershipService.filterMemberships(pendingMember));
        model.addAttribute("activeMembers", membershipService.filterMemberships(activeMember));
        return "members";
    }

    @GetMapping("/household/{id}/invite")
    public String renderInviteUserPage(@PathVariable Long id, Model model) {

        model.addAttribute("users", userService.findAllUsers());
        model.addAttribute("householdId", id);

        return "invite-user";
    }

    @PostMapping("/household/{householdId}/invite")
    public String inviteUser(@PathVariable(name = "householdId") Long householdId, @RequestParam String username, Model model) {
        User user = userService.findUserByUsername(username)
                .orElseThrow(() -> new RuntimeException("User with given username does not exist"));

        HouseHold houseHold = householdService.findHouseHoldById(householdId)
                .orElseThrow(() -> new RuntimeException("Household with given id does not exist"));

        Membership membership = new Membership();
        membership.setStatus(MembershipStatus.PENDING);

        membershipService.createMembership(membership, user, houseHold);
        model.addAttribute("users", userService.findAllUsers());
        return "invite-user";
    }

    @GetMapping("household/{householdId}/invitation/{membershipId}/accept")
    public String acceptInvitation(Authentication authentication, @PathVariable Long householdId, @PathVariable Long membershipId, Model model) {
        membershipService.acceptInvitation(membershipId);

        MembershipFilter pendingHouseholds = MembershipFilter.builder()
                .username(authentication.getName())
                .status(MembershipStatus.PENDING)
                .build();

        MembershipFilter activeHouseholds = MembershipFilter.builder()
                .username(authentication.getName())
                .status(MembershipStatus.ACTIVE)
                .build();

        model.addAttribute("pendingHouseholds", membershipService.filterMemberships(pendingHouseholds));
        model.addAttribute("activeHouseholds", membershipService.filterMemberships(activeHouseholds));
        return "welcome";
    }


    @GetMapping("household/{householdId}/invitation/{membershipId}/decline")
    public String declineInvitation(Authentication authentication, @PathVariable Long householdId, @PathVariable Long membershipId, Model model) {
        membershipService.declineInvitation(membershipId);

        MembershipFilter pendingHouseholds = MembershipFilter.builder()
                .username(authentication.getName())
                .status(MembershipStatus.PENDING)
                .build();

        MembershipFilter activeHouseholds = MembershipFilter.builder()
                .username(authentication.getName())
                .status(MembershipStatus.ACTIVE)
                .build();

        model.addAttribute("pendingHouseholds", membershipService.filterMemberships(pendingHouseholds));
        model.addAttribute("activeHouseholds", membershipService.filterMemberships(activeHouseholds));
        return "welcome";
    }

    @GetMapping("/household/{id}/delete")
    public String leaveHousehold(Authentication authentication, @PathVariable Long id, Model model) {

        householdService.deleteHouseholdById(id);

        MembershipFilter pendingHouseholds = MembershipFilter.builder()
                .username(authentication.getName())
                .status(MembershipStatus.PENDING)
                .build();

        MembershipFilter activeHouseholds = MembershipFilter.builder()
                .username(authentication.getName())
                .status(MembershipStatus.ACTIVE)
                .build();

        model.addAttribute("pendingHouseholds", membershipService.filterMemberships(pendingHouseholds));
        model.addAttribute("activeHouseholds", membershipService.filterMemberships(activeHouseholds));
        return "welcome";
    }
}
