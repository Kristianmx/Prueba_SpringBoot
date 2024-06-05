package com.example.demo.api.controllers;

import com.example.demo.api.dto.requests.ClassRequest;
import com.example.demo.api.dto.requests.LessonRequest;
import com.example.demo.api.dto.responses.ClassResponse;
import com.example.demo.api.dto.responses.LessonResponse;
import com.example.demo.api.dto.responses.StudentResponse;
import com.example.demo.infrastructure.abstrac_services.ILessonService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/lesson")
@AllArgsConstructor
public class LessonController {
    private final ILessonService lessonService;

    @Operation(summary = "Crear un nuevo registro", description = "Ingrese los datos requeridos para crear un nuevo registro")
    @PostMapping
    public ResponseEntity<LessonResponse> insert(
            @Validated @RequestBody LessonRequest request
    ){
        return ResponseEntity.ok(this.lessonService.create(request));

    }

    @Operation(summary = "Mostrar todos los registros", description = "Devuelve todos los registros paginados")
    @GetMapping
    public ResponseEntity<Page<LessonResponse>> getAll(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "5") int size
    ){
        return ResponseEntity.ok(this.lessonService.getAll(page-1,size,"",""));
    }

    @Operation(summary = "Buscar por ID", description = "Devuelve el registro con el ID proporcionado")
    @GetMapping(path = "/{id}")
    public ResponseEntity<LessonResponse> get(
            @PathVariable Long id

    ){
        return ResponseEntity.ok(this.lessonService.get(id));
    }

    @Operation(summary = "deshabilitar  un registro", description = "Ingrese el ID del registro a deshabilitar ")
    @PatchMapping(path = "/{id}/disable")
    public ResponseEntity<LessonResponse> disable(
            @PathVariable Long id
    ){
        return ResponseEntity.ok(this.lessonService.disable(id));
    }

    @Operation(summary = "Buscar multimedia por ID", description = "Devuelve el registro con el ID proporcionado")
    @GetMapping(path = "/{id}/multimedia")
    public ResponseEntity<LessonResponse> getMUl(
            @PathVariable Long id

    ){
        return ResponseEntity.ok(this.lessonService.get(id));
    }
}
