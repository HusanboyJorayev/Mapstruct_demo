package org.example.example_mapstruct.user;

import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto toDto(User user);

    User toEntity(UserDto.CreateUser dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void toUpdate(@MappingTarget User user, UserDto.UpdateUser dto);

    List<UserDto> dtoList(List<User> users);

    UserDto.UserWithCards userWithCards(UserDto dto);
}
