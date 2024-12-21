package org.example.example_mapstruct.user;

import lombok.RequiredArgsConstructor;
import org.example.example_mapstruct.card.CardMapper;
import org.example.example_mapstruct.card.CardRepository;
import org.example.example_mapstruct.dto.ErrorDto;
import org.example.example_mapstruct.dto.Validations;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final CardRepository cardRepository;
    private final UserRepository userRepository;
    private final CardMapper cardMapper;
    private final UserMapper userMapper;
    private final Validations validations;


    @Override
    public ResponseEntity<?> create(UserDto.CreateUser dto) {
        List<ErrorDto> errors = this.validations.validUser(dto);
        if (errors != null && !errors.isEmpty()) {
            return ResponseEntity.badRequest().body(errors);
        }

        User user = this.userMapper.toEntity(dto);
        user.setCreatedAt(LocalDateTime.now());
        this.userRepository.save(user);
        return ResponseEntity.ok("CREATED");
    }

    @Override
    public ResponseEntity<?> get(Long id) {
        User user = this.userRepository.findById(id).orElse(null);
        if (user != null) {
            return ResponseEntity.ok(this.userMapper.toDto(user));
        }
        return ResponseEntity.ok("USER IS NOT FOUND");
    }

    @Override
    public ResponseEntity<?> getAll() {
        List<User> list = this.userRepository.findAll();
        if (!list.isEmpty()) {
            return ResponseEntity.ok(this.userMapper.dtoList(list));
        }
        return ResponseEntity.ok(new ArrayList<>());
    }

    @Override
    public ResponseEntity<?> delete(Long id) {
        User user = this.userRepository.findById(id).orElse(null);
        if (user != null) {
            this.userRepository.delete(user);
            return ResponseEntity.ok("USER DELETED");
        }
        return ResponseEntity.ok("USER IS NOT FOUND");
    }

    @Override
    public ResponseEntity<?> update(Long id, UserDto.UpdateUser dto) {
        User user = this.userRepository.findById(id).orElse(null);
        if (user != null) {
            this.userMapper.toUpdate(user, dto);
            user.setUpdatedAt(LocalDateTime.now());
            this.userRepository.save(user);
            return ResponseEntity.ok("USER UPDATED");
        }
        return ResponseEntity.ok("USER IS NOT FOUND");
    }

    @Override
    public ResponseEntity<?> userWithCards(Long userId) {
        User user = this.userRepository.findById(userId).orElse(null);
        if (user != null) {
            UserDto.UserWithCards userWithCards = this.userMapper.getUserWithCards(user, cardMapper, cardRepository);
            return ResponseEntity.ok(userWithCards);
        }
        return ResponseEntity.badRequest().body("User is not found");
    }
}
