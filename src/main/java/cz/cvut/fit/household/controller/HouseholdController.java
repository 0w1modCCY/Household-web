package cz.cvut.fit.household.controller;

import cz.cvut.fit.household.datamodel.entity.HouseHold;
import cz.cvut.fit.household.datamodel.entity.Membership;
import cz.cvut.fit.household.datamodel.entity.User;
import cz.cvut.fit.household.service.interfaces.HouseHoldService;
import cz.cvut.fit.household.service.interfaces.MembershipService;
import cz.cvut.fit.household.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.persistence.EntityManager;

@Controller
public class HouseholdController {

    private final HouseHoldService householdService;
    private final UserService userService;
    private final MembershipService membershipService;
    private final EntityManager entityManager;

    @Autowired
    public HouseholdController(HouseHoldService householdService, MembershipService membershipService, UserService userService, EntityManager entityManager) {
        this.householdService = householdService;
        this.membershipService = membershipService;
        this.userService = userService;
        this.entityManager = entityManager;
    }

    @GetMapping("/addhousehold")
    public String renderCreateHouseholdPage(Model model) {
        model.addAttribute("houseHold", new HouseHold());
        return "add-household";
    }

    @PostMapping("/addhousehold")
    public String createHousehold(Authentication authentication, Model model,  @ModelAttribute HouseHold houseHold) {
        User user = userService.findUserByUsername(authentication.getName())
                .orElseThrow(() -> new RuntimeException("Authenticated user no longer exists in the database"));

        Membership membership = new Membership();

        user.addMembership(membership);
        houseHold.addMembership(membership);

        membershipService.createMembership(membership);

        model.addAttribute("houseHolds", householdService.findAllHouseholds());
        return "welcome";
    }

    @GetMapping("/household/{id}/members")
    public String renderHouseholdMembersPage(Model model, @PathVariable Long id) {
        model.addAttribute("members", householdService.findMembershipsByHouseholdId(id));
        return "members";
    }

    @GetMapping("/household/del/{id}")
    public String leaveHousehold(@Autowired Authentication authentication, Model model, @PathVariable Long id) {
        householdService.deleteHouseholdById(id);
        model.addAttribute("houseHolds", householdService.findHouseholdsByUsername(authentication.getName()));
        return "welcome";
    }
}
