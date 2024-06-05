package com.example.demo.api.dto.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LessonRequest {
    @Size(max = 200,message = "El titulo excede el limite de caracteres permitidos")
    @NotBlank(message = "El titulo es un campo obligatorio")
    private String title;
    @Size(min = 10,message = "El contenido no supera el mínimo de caracteres permitidos")
    @NotBlank(message = "El contenido es un campo obligatorio")
    private String content;

    @NotNull(message = "La clase es requerida")
    private Long classe;

    @NotNull(message = "la multimedia es requerida")
    private List<MultimediaRequest> multimedia;
}
