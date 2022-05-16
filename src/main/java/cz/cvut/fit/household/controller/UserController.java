package cz.cvut.fit.household.controller;

import cz.cvut.fit.household.datamodel.entity.Membership;
import cz.cvut.fit.household.datamodel.entity.User;
import cz.cvut.fit.household.datamodel.enums.MembershipStatus;
import cz.cvut.fit.household.repository.filter.MembershipFilter;
import cz.cvut.fit.household.service.interfaces.MembershipService;
import cz.cvut.fit.household.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class UserController {

    private final UserService userService;
    private final MembershipService membershipService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserController(UserService userService, MembershipService membershipService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.membershipService = membershipService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/login")
    public String renderLoginPage() {
        return "login";
    }

    @GetMapping("/signup")
    public String renderSignUpPage(Model model) {
        model.addAttribute("user", new User());
        return "sign-up";
    }

    @PostMapping("/signup")
    public String signUp(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.createOrUpdateUser(user);
        return "login";
    }

    @PostMapping("/{householdId}/users/search")
    public String searchForUser(@PathVariable Long householdId, @RequestParam String searchTerm, Model model) {

        var users = userService.findUsersBySearchTerm(searchTerm);

        model.addAttribute("users", users);
        model.addAttribute("householdId", householdId);

        return "invite-user";
    }

    @GetMapping("/welcome")
    public String renderWelcomePage(Authentication authentication, Model model) {

        User user = userService.findUserByUsername(authentication.getName())
                .orElseThrow(() -> new RuntimeException());

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
