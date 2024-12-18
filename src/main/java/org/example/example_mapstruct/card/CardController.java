package org.example.example_mapstruct.card;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/card")
@RequiredArgsConstructor
public class CardController {
    private final CardService cardService;


    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody CardDto.CreateCard dto) {
        return this.cardService.create(dto);
    }

    @GetMapping("/get")
    public ResponseEntity<?> get(@RequestParam("id") Long id) {
        return this.cardService.get(id);
    }

    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody CardDto.UpdateCard dto,
                                    @RequestParam("id") Long id) {
        return this.cardService.update(dto, id);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> delete(@RequestParam("id") Long id) {
        return this.cardService.delete(id);
    }

    @GetMapping("/getAll")
    public ResponseEntity<?> getAll() {
        return this.cardService.getAll();
    }

    @GetMapping("/getAllByUserId")
    public ResponseEntity<?> getAllByUserId(@RequestParam("userId") Long userId) {
        return this.cardService.getAllByUserId(userId);
    }
}
