package com.example.demo.api.dto.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
public class StudentRequest {
    @Size(max = 200,message = "El nombre excede el limite de caracteres permitidos")
    @NotBlank(message = "El nombre es un campo obligatorio")
    private String name;
    @Size(max = 150,message = "El correo excede el limite de caracteres permitidos")
    @NotBlank(message = "El correo es un campo obligatorio")
    private String email;
    @NotNull(message = "La clase de estudiante es requerido")
    private Long class_id;
}
