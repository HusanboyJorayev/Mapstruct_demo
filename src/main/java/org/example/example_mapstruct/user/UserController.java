package org.example.example_mapstruct.user;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserService userService;



    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody @Valid UserDto.CreateUser dto) {
        return this.userService.create(dto);
    }

    @GetMapping("/get")
    public ResponseEntity<?> get(@RequestParam("id") Long id) {
        return this.userService.get(id);
    }

    @GetMapping("/getAll")
    public ResponseEntity<?> getAll() {
        return this.userService.getAll();
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> delete(@RequestParam("id") Long id) {
        return this.userService.delete(id);
    }

    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestParam("id") Long id,
                                    @RequestBody UserDto.UpdateUser dto) {
        return this.userService.update(id, dto);
    }

    @GetMapping("/userWithCards")
    public ResponseEntity<?> userWithCards(@RequestParam("userId") Long userId) {
        return this.userService.userWithCards(userId);
    }
}
