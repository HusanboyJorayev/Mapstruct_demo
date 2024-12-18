package org.example.example_mapstruct.user;


import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public interface UserService {
    ResponseEntity<?> create(UserDto.CreateUser dto);

    ResponseEntity<?> get(Long id);

    ResponseEntity<?> getAll();

    ResponseEntity<?> delete(Long id);

    ResponseEntity<?> update(Long id, UserDto.UpdateUser dto);

    ResponseEntity<?>userWithCards(Long userId);
}
