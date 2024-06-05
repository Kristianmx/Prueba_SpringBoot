package com.example.demo.api.dto.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClassRequest {
    @Size(max = 200,message = "El nombre excede el limite de caracteres permitidos")
    @NotBlank(message = "El nombre es un campo obligatorio")
    private String name;
    @Size(min = 10,message = "La descripción no supera el mínimo de caracteres permitidos")
    @NotBlank(message = "El descripción es un campo obligatorio")
    private String description;


}
