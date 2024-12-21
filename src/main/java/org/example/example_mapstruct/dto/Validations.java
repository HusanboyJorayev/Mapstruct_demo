package org.example.example_mapstruct.dto;

import lombok.RequiredArgsConstructor;
import org.example.example_mapstruct.card.CardDto;
import org.example.example_mapstruct.user.UserDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class Validations {


    public List<ErrorDto> validUser(UserDto.CreateUser user) {
        List<ErrorDto> errors = new ArrayList<>();
        if (user.getFirstname() == null || user.getFirstname().trim().isEmpty()) {
            errors.add(new ErrorDto("firstname", "firstname cannot be null or empty"));
        }
        return errors;
    }

    public List<ErrorDto> validCard(CardDto.CreateCard dto) {
        List<ErrorDto> errors = new ArrayList<>();
        if (dto.getCardNumber() == null || dto.getCardNumber().trim().isEmpty()) {
            errors.add(new ErrorDto("cardNumber", "cardNumber cannot be null or empty"));
        }
        if (dto.getExpiryDate() == null || dto.getExpiryDate().trim().isEmpty()) {
            errors.add(new ErrorDto("expiryDate", "expiryDate cannot be null or empty"));
        }
        if (dto.getUserId() == null) {
            errors.add(new ErrorDto("userId", "userId cannot be null or empty"));
        }
        return errors;
    }
}
