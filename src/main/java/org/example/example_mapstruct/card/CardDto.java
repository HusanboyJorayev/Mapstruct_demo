package org.example.example_mapstruct.card;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CardDto {
    private Long id;
    private String cardNumber;
    private String name;
    private String expiryDate;
    private Long userId;

    private String cvv;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;


    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class CreateCard {
        private String cardNumber;
        private String name;
        private String expiryDate;
        private Long userId;
        private String cvv;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class UpdateCard {
        private String cardNumber;
        private String name;
        private String expiryDate;
        private Long userId;
        private String cvv;
    }
}
