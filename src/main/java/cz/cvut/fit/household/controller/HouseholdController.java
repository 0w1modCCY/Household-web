package cz.cvut.fit.household.controller;

import cz.cvut.fit.household.datamodel.entity.HouseHold;
import cz.cvut.fit.household.datamodel.entity.Membership;
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

import java.time.LocalDate;
import java.util.List;

@Controller
public class HouseholdController {

    private final HouseHoldService householdService;
    private final MembershipService membershipService;
    private final UserService userService;

    @Autowired
    public HouseholdController(HouseHoldService householdService, MembershipService membershipService, UserService userService) {
        this.householdService = householdService;
        this.membershipService = membershipService;
        this.userService = userService;
    }

    @GetMapping("/addhousehold")
    public String showAddHousholdView(Model model) {
        model.addAttribute("houseHold", new HouseHold());
        return "add-household";
    }

    @PostMapping("/addhousehold")
    public String createHousehold(Authentication authentication, Model mode, @ModelAttribute HouseHold houseHold) {

        Membership membership = new Membership();
        membership.setUser(userService.findUserByUsername(authentication.getName()).get());
        membership.setHouseHold(houseHold);
        membership.setCreationDate(LocalDate.now());

        houseHold.setMemberships(List.of(membership));
        HouseHold createdHousehold = householdService.createHousehold(houseHold);

        membership.setHouseHold(houseHold);
        membershipService.createMembership(membership);

        return "profile";
    }

    @GetMapping("/household/{id}/members")
    public String getHouseholdMembers(Model model, @PathVariable Long id) {
        model.addAttribute("members", householdService.findMembershipsByHouseholdId(id));
        return "members";
    }

    @GetMapping("/household/del/{id}")
    public String leaveHousehold(Model model, @PathVariable Long id) {
        householdService.deleteHouseholdById(id);

        model.addAttribute("houseHolds", householdService.findAllHouseholds());
        return "profile";
    }
}
