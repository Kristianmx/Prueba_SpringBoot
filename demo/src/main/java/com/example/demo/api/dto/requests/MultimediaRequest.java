package com.example.demo.api.dto.requests;

import com.example.demo.utils.enums.typeMultimedia;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MultimediaRequest {
    @Size(min = 10,message = "La url no supera el mínimo de caracteres permitidos")
    @NotBlank(message = "La url es un campo obligatorio")
    private String url;

    @NotNull(message = "El tipo de multimedia es un campo obligatorio")
    private typeMultimedia type;
    @NotNull(message = "La lesión de la multimedia es requerido")
    private Long lesson_id;
}
