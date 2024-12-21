package org.example.example_mapstruct.user;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.example_mapstruct.card.CardDto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class UserDto {
    private Long id;
    private String firstname;
    private String lastname;
    private String email;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;


    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @Data
    public static class CreateUser {
        @NotBlank(message = "firstname cannot be null or empty")
        private String firstname;
        private String lastname;
        private String email;
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @Data
    public static class UpdateUser {
        private String firstname;
        private String lastname;
        private String email;
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @Data
    public static class UserWithCards {
        private Long id;
        private String firstname;
        private String lastname;
        private String email;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;
        private List<CardDto> cards = new ArrayList<>();
    }
}
