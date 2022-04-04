package cz.cvut.fit.household.datamodel.dto.user;

import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
public class UserPostDTO {
    @Valid
    private UserInfo userInfo;

    @NotBlank(message = "Password is mandatory")
    @Size(min = 8, max = 30, message = "Password must be between {min} and {max} characters long")
    private String password;
}
