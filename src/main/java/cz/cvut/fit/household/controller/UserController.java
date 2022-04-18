package cz.cvut.fit.household.controller;

import cz.cvut.fit.household.datamodel.entity.User;
import cz.cvut.fit.household.service.interfaces.HouseHoldService;
import cz.cvut.fit.household.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    private final UserService userService;
    private final HouseHoldService houseHoldService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserController(UserService userService, HouseHoldService houseHoldService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.houseHoldService = houseHoldService;
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

    @GetMapping("/welcome")
    public String renderWelcomePage(@Autowired Authentication authentication, Model model) {
        model.addAttribute("houseHolds", houseHoldService.findHouseholdsByUsername(authentication.getName()));
        return "welcome";
    }

    @PostMapping("/signup")
    public String signUp(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.createOrUpdateUser(user);
        return "login";
    }
}
