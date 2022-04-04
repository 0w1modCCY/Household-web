package cz.cvut.fit.household.datamodel.converter;

import cz.cvut.fit.household.datamodel.dto.user.UserPostDTO;
import cz.cvut.fit.household.datamodel.dto.user.UserResponseDTO;
import cz.cvut.fit.household.datamodel.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserConverter {
    @Mapping(target = ".", source = "userInfo")
    User toModelFromPostDto(UserPostDTO userDTO);

    @Mapping(target = "userInfo", source = ".")
    UserResponseDTO fromModelToResponseDto(User user);
}
