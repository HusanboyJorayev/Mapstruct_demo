package org.example.example_mapstruct.user;

import org.example.example_mapstruct.card.CardDto;
import org.example.example_mapstruct.card.CardMapper;
import org.example.example_mapstruct.card.CardRepository;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Set;
import java.util.StringJoiner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Mapper(componentModel = "spring", imports = {CardMapper.class, Collectors.class, Stream.class})
public interface UserMapper {


    //@Mapping(ignore = true, target = "cards")
    UserDto toDto(User user);

    User toEntity(UserDto.CreateUser dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void toUpdate(@MappingTarget User user, UserDto.UpdateUser dto);

    List<UserDto> dtoList(List<User> users);


    UserDto.UserWithCards userWithCards(UserDto dto);

    @Mapping(target = "cards", expression = "java(cardRepository.getAllByUserId(user.getId())\n" +
            "                .stream().map(cardMapper::toDto).toList())")
    UserDto.UserWithCards getUserWithCards(User user, CardMapper cardMapper, CardRepository cardRepository);


    default void test(CardMapper cardMapper, User user, CardRepository cardRepository) {
        List<CardDto> dtoList = cardRepository.getAllByUserId(user.getId())
                .stream().map(cardMapper::toDto).toList();
    }
}
