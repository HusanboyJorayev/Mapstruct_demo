package org.example.example_mapstruct.card;

import lombok.RequiredArgsConstructor;
import org.example.example_mapstruct.dto.ErrorDto;
import org.example.example_mapstruct.dto.Validations;
import org.example.example_mapstruct.user.User;
import org.example.example_mapstruct.user.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CardServiceImpl implements CardService {
    private final CardRepository cardRepository;
    private final UserRepository userRepository;
    private final Validations validations;
    private final CardMapper cardMapper;


    @Override
    public ResponseEntity<?> create(CardDto.CreateCard dto) {
        List<ErrorDto> errors = this.validations.validCard(dto);
        if (errors != null && !errors.isEmpty()) {
            return ResponseEntity.badRequest().body(errors);
        }

        User user = this.userRepository.findById(dto.getUserId()).orElse(null);
        if (user != null) {
            Card card = this.cardMapper.toEntity(dto);
            card.setCreatedAt(LocalDateTime.now());
            this.cardRepository.save(card);
            return ResponseEntity.ok("Card created successfully");
        }
        return ResponseEntity.badRequest().body("User not found");
    }

    @Override
    public ResponseEntity<?> get(Long id) {
        Card card = this.cardRepository.findById(id).orElse(null);
        if (card != null) {
            return ResponseEntity.ok(this.cardMapper.toDto(card));
        }
        return ResponseEntity.badRequest().body("Card not found");
    }

    @Override
    public ResponseEntity<?> update(CardDto.UpdateCard dto, Long id) {
        User user = this.userRepository.findById(dto.getUserId()).orElse(null);
        if (user != null) {
            Card card = this.cardRepository.findById(id).orElse(null);
            if (card != null) {
                this.cardMapper.updateDto(dto, card);
                card.setUpdatedAt(LocalDateTime.now());
                this.cardRepository.save(card);
                return ResponseEntity.ok("Card updated successfully");

            }
            return ResponseEntity.badRequest().body("Card not found");
        }
        return ResponseEntity.badRequest().body("User not found");
    }

    @Override
    public ResponseEntity<?> delete(Long id) {
        Card card = this.cardRepository.findById(id).orElse(null);
        if (card != null) {
            this.cardRepository.delete(card);
            return ResponseEntity.badRequest().body("Card is deleted successfully");
        }
        return ResponseEntity.badRequest().body("Card not found");
    }

    @Override
    public ResponseEntity<?> getAll() {
        List<Card> list = this.cardRepository.findAll();
        if (!list.isEmpty()) {
            return ResponseEntity.ok(this.cardMapper.dtoList(list));
        }
        return ResponseEntity.ok(new ArrayList<>());
    }

    @Override
    public ResponseEntity<?> getAllByUserId(Long userId) {
        User user = this.userRepository.findById(userId).orElse(null);
        if (user != null) {
            List<Card> list = this.cardRepository.getAllByUserId(userId);
            if (list != null && !list.isEmpty()) {
                return ResponseEntity.ok(this.cardMapper.dtoList(list));
            }
            return ResponseEntity.ok(this.cardMapper.dtoList(new ArrayList<>()));
        }
        return ResponseEntity.badRequest().body("User not found");
    }
}
