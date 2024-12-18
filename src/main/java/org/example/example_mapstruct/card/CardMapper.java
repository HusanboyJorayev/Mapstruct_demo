package org.example.example_mapstruct.card;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CardMapper {

    CardDto toDto(Card card);

    Card toEntity(CardDto.CreateCard dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateDto(CardDto.UpdateCard dto, @MappingTarget Card card);

    List<CardDto> dtoList(List<Card> cards);
}
