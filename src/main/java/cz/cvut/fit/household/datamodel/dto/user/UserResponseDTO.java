package cz.cvut.fit.household.datamodel.dto.user;

import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;

@Getter
@Setter
public class UserResponseDTO {
    @Valid
    private UserInfo userInfo;
}
