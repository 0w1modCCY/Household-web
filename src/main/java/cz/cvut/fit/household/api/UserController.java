package cz.cvut.fit.household.api;

import cz.cvut.fit.household.datamodel.converter.UserConverter;
import cz.cvut.fit.household.datamodel.dto.user.UserPostDTO;
import cz.cvut.fit.household.datamodel.dto.user.UserResponseDTO;
import cz.cvut.fit.household.datamodel.entity.User;
import cz.cvut.fit.household.exception.NonExistentEntityException;
import cz.cvut.fit.household.exception.UsernameAlreadyExistsException;
import cz.cvut.fit.household.service.interfaces.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Tag(name = "User API")
@RequestMapping("/users")
@RestController
public class UserController {

    private final UserService userService;

    private final UserConverter userConverter;

    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserController(UserService userService, UserConverter userConverter, BCryptPasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.userConverter = userConverter;
        this.passwordEncoder = passwordEncoder;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Create new user")
    @ApiResponse(responseCode = "201", description = "User successfully created.")
    @ApiResponse(responseCode = "422", description = "Given entity has invalid data.")
    @ApiResponse(responseCode = "409", description = "Given username already exists.")
    public UserResponseDTO createUser(@Valid @RequestBody UserPostDTO userDTO) {

        if (userService.exists(userDTO.getUserInfo().getUsername())) {
            throw new UsernameAlreadyExistsException("User with username: " + userDTO.getUserInfo().getUsername() + " can not be created as it already exists");
        }

        User user = userConverter.toModelFromPostDto(userDTO);
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));

        User createdUser = userService.createUser(user);
        return userConverter.fromModelToResponseDto(createdUser);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Retrieve all users.")
    public List<UserResponseDTO> getAllUsers() {
        return userService.findAllUsers().stream()
                .map(userConverter::fromModelToResponseDto)
                .collect(Collectors.toList());
    }

    @ResponseStatus(HttpStatus.FOUND)
    @GetMapping(value = "/{username}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Retrieve user with given id.")
    @ApiResponse(responseCode = "200", description = "User with given id returned.", content = @Content(schema = @Schema(implementation = UserPostDTO.class)))
    @ApiResponse(responseCode = "404", description = "User with given id doesn't exist.")
    public UserResponseDTO getUserByUsername(@PathVariable String username) {
        User user = userService.findUserByUsername(username)
                .orElseThrow(() -> new NonExistentEntityException("User with username: " + username + " doesn't exist"));

        return userConverter.fromModelToResponseDto(user);
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping(value = "/{username}", produces = MediaType.APPLICATION_JSON_VALUE)
    public void deleteUserByUsername(@PathVariable String username) {
        userService.deleteUserByUsername(username);
    }
}
