package cz.cvut.fit.household.controller;

import cz.cvut.fit.household.datamodel.entity.User;
import cz.cvut.fit.household.service.interfaces.HouseHoldService;
import cz.cvut.fit.household.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    private final UserService userService;
    private final HouseHoldService houseHoldService;

    @Autowired
    public UserController(UserService userService, HouseHoldService houseHoldService) {
        this.userService = userService;
        this.houseHoldService = houseHoldService;
    }

    @GetMapping("")
    public String renderGreetingPage() {
        return "greeting";
    }

    @GetMapping("/login")
    public String renderLoginPage() {
        return "login";
    }

    @GetMapping("/signup")
    public String renderSignUpPage() {
        return "sign-up";
    }

    @GetMapping("/profile")
    public String renderProfilePage(Model model) {
        model.addAttribute("houseHolds", houseHoldService.findAllHouseholds());
        return "profile";
    }

    @PostMapping("/signup")
    public String signUp(User user) {
        userService.createOrUpdateUser(user);
        return "greeting";
    }
}
