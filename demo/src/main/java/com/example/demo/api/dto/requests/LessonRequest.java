package com.example.demo.api.dto.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class LessonRequest {
    @Size(max = 200,message = "El titulo excede el limite de caracteres permitidos")
    @NotBlank(message = "El titulo es un campo obligatorio")
    private String title;
    @Size(min = 10,message = "El contenido no supera el mínimo de caracteres permitidos")
    @NotBlank(message = "El contenido es un campo obligatorio")
    private String content;
}
