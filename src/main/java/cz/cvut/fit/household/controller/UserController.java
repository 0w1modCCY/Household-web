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
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

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

    @GetMapping("/welcome")
    public String renderWelcomePage(@Autowired Authentication authentication, Model model) {

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
