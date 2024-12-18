package org.example.example_mapstruct.card;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public interface CardService {
    ResponseEntity<?> create(CardDto.CreateCard dto);

    ResponseEntity<?> get(Long id);

    ResponseEntity<?> update(CardDto.UpdateCard dto, Long id);

    ResponseEntity<?> delete(Long id);

    ResponseEntity<?> getAll();

    ResponseEntity<?> getAllByUserId(Long userId);
}

