package cz.cvut.fit.household.controller;

import cz.cvut.fit.household.datamodel.entity.HouseHold;
import cz.cvut.fit.household.datamodel.entity.Membership;
import cz.cvut.fit.household.datamodel.entity.User;
import cz.cvut.fit.household.datamodel.enums.MembershipStatus;
import cz.cvut.fit.household.exception.MembershipAlreadyExistsException;
import cz.cvut.fit.household.repository.filter.MembershipFilter;
import cz.cvut.fit.household.service.interfaces.HouseHoldService;
import cz.cvut.fit.household.service.interfaces.MembershipService;
import cz.cvut.fit.household.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Controller which manages all requests related to households.
 */
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

    /**
     * Render create household page.
     *
     * @param model model
     * @return add-household page.
     */
    @GetMapping("/households/add")
    public String renderCreateHouseholdPage(Model model) {
        model.addAttribute("houseHold", new HouseHold());
        return "add-household";
    }

    /**
     * Create new household.
     *
     * @param authentication
     * @param model
     * @param houseHold
     * @return
     */
    @PostMapping("/households/add")
    public String createHousehold(Authentication authentication, Model model, @ModelAttribute HouseHold houseHold) {

        User user = userService.findUserByUsername(authentication.getName())
                .orElseThrow(() -> new RuntimeException("Authenticated user no longer exists in the database"));

        Membership membership = new Membership();
        membership.setStatus(MembershipStatus.ACTIVE);

        membershipService.createMembership(membership, user, houseHold);;


        List<Membership> pendingMemberships =  user.getMemberships()
                .stream().filter(mem -> mem.getStatus().equals(MembershipStatus.PENDING))
                .collect(Collectors.toList());

        List<Membership> activeMemberships =  user.getMemberships()
                .stream().filter(mem -> mem.getStatus().equals(MembershipStatus.ACTIVE))
                .collect(Collectors.toList());

        model.addAttribute("pendingHouseholds", pendingMemberships);
        model.addAttribute("activeHouseholds", activeMemberships);
        return "welcome";
    }

    /**
     * Render page with all members of the household.
     *
     * @param model model
     * @param id id
     * @return members page
     */
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

    /**
     * Render invite user page.
     *
     * @param id id
     * @param model model
     * @return invite user page
     */
    @GetMapping("/household/{id}/invite")
    public String renderInviteUserPage(@PathVariable Long id, Model model) {
        model.addAttribute("householdId", id);

        return "invite-user";
    }

    /**
     * Invite user.
     *
     * @param householdId
     * @param username
     * @param model
     * @return invite user page
     */
    @PostMapping("/household/{householdId}/invite")
    public String inviteUser(@PathVariable(name = "householdId") Long householdId, @RequestParam String username, Model model) {
        User user = userService.findUserByUsername(username)
                .orElseThrow(() -> new RuntimeException("User with given username does not exist"));

        HouseHold houseHold = householdService.findHouseHoldById(householdId)
                .orElseThrow(() -> new RuntimeException("Household with given id does not exist"));

        try {
            houseHold.getMemberships()
                    .forEach(membership -> {
                        if (membership.getUser().getUsername().equals(username)) {
                            throw new MembershipAlreadyExistsException("User with username: " + username + " is already a member of household with id: " + householdId);
                        }
                    });
        } catch (MembershipAlreadyExistsException e) {
            return "invite-user";
        }

        Membership membership = new Membership();
        membership.setStatus(MembershipStatus.PENDING);

        membershipService.createMembership(membership, user, houseHold);
        return "invite-user";
    }

    @GetMapping("household/{householdId}/invitation/{membershipId}/accept")
    public String acceptInvitation(Authentication authentication, @PathVariable Long householdId, @PathVariable Long membershipId, Model model) {
        User user = userService.findUserByUsername(authentication.getName())
                .orElseThrow(() -> new RuntimeException());

        membershipService.acceptInvitation(membershipId);

        List<Membership> pendingMemberships =  user.getMemberships()
                .stream().filter(membership -> membership.getStatus().equals(MembershipStatus.PENDING))
                .collect(Collectors.toList());

        List<Membership> activeMemberships =  user.getMemberships()
                .stream().filter(membership -> membership.getStatus().equals(MembershipStatus.ACTIVE))
                .collect(Collectors.toList());

        model.addAttribute("pendingHouseholds", pendingMemberships);
        model.addAttribute("activeHouseholds", activeMemberships);
        return "welcome";
    }


    @GetMapping("household/{householdId}/invitation/{membershipId}/decline")
    public String declineInvitation(Authentication authentication, @PathVariable Long householdId, @PathVariable Long membershipId, Model model) {
        User user = userService.findUserByUsername(authentication.getName())
                .orElseThrow(() -> new RuntimeException());

        membershipService.declineInvitation(membershipId);

        List<Membership> pendingMemberships =  user.getMemberships()
                .stream().filter(membership -> membership.getStatus().equals(MembershipStatus.PENDING))
                .collect(Collectors.toList());

        List<Membership> activeMemberships =  user.getMemberships()
                .stream().filter(membership -> membership.getStatus().equals(MembershipStatus.ACTIVE))
                .collect(Collectors.toList());

        model.addAttribute("pendingHouseholds", pendingMemberships);
        model.addAttribute("activeHouseholds", activeMemberships);
        return "welcome";
    }

    @GetMapping("/household/{id}/delete")
    public String leaveHousehold(Authentication authentication, @PathVariable Long id, Model model) {

        User user = userService.findUserByUsername(authentication.getName())
                .orElseThrow(() -> new RuntimeException());

        membershipService.leaveHousehold(id);

        List<Membership> pendingMemberships =  user.getMemberships()
                .stream().filter(membership -> membership.getStatus().equals(MembershipStatus.PENDING))
                .collect(Collectors.toList());

        List<Membership> activeMemberships =  user.getMemberships()
                .stream().filter(membership -> membership.getStatus().equals(MembershipStatus.ACTIVE))
                .collect(Collectors.toList());

        model.addAttribute("pendingHouseholds", pendingMemberships);
        model.addAttribute("activeHouseholds", activeMemberships);
        return "welcome";
    }
}
