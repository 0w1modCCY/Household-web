package cz.cvut.fit.household.datamodel.dto.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * Simple POJO class which is used to represent basic information about User entity and doesn't
 * contain any relation information
 */
@Getter
@Setter
public class UserInfo {
    @NotBlank(message = "Username is mandatory")
    @Size(min = 5, max = 20, message = "Username must be between {min} and {max} characters long")
    private String username;

    @NotBlank(message = "Firstname is mandatory")
    @Size(max = 20, message = "First name must be at most {max} characters long")
    private String firstName;

    @NotBlank(message = "Lastname is mandatory")
    @Size(max = 20, message = "Last name must be at most {max} characters long")
    private String lastName;

    @Email(message = "Email should be valid")
    private String email;
}
