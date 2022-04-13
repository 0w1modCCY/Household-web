package cz.cvut.fit.household.controller;

import cz.cvut.fit.household.datamodel.converter.UserConverter;
import cz.cvut.fit.household.datamodel.dto.user.UserPostDTO;
import cz.cvut.fit.household.datamodel.entity.User;
import cz.cvut.fit.household.service.interfaces.HouseHoldService;
import cz.cvut.fit.household.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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
    public String showMainView() {
        return "main";
    }

    @GetMapping("/profile")
    public String showProfileView(Model model) {
        model.addAttribute("houseHolds", houseHoldService.findAllHouseholds());
        return "profile";
    }

    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

    @GetMapping("/signup")
    public String showSignUpForm(User user) {
        return "add-user";
    }

    @PostMapping("/adduser")
    public String addUser(User user, BindingResult result, Model model) {

        userService.createUser(user);
        return "main";
    }
}
