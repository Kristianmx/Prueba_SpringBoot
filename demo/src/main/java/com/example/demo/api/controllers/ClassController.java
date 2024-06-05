package com.example.demo.api.controllers;

import com.example.demo.api.dto.requests.ClassRequest;
import com.example.demo.api.dto.responses.ClassResponse;
import com.example.demo.infrastructure.abstrac_services.IClassService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
@RestController
@RequestMapping("/class")
@AllArgsConstructor
public class ClassController {
    @Autowired
    private final IClassService classService;
    @Operation(summary = "Mostrar todos los registros", description = "Devuelve todos los registros paginados")
    @GetMapping
    public ResponseEntity<Page<ClassResponse>> getAll(
             @RequestParam(defaultValue = " ")String name,
            @RequestParam(defaultValue = " ")String description,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "5") int size
    ){
        return ResponseEntity.ok(this.classService.getAll(page-1,size,name,description));
    }

    @Operation(summary = "Buscar por ID", description = "Devuelve el registro con el ID proporcionado")
    @GetMapping(path = "/{id}")
    public ResponseEntity<ClassResponse> get(
            @PathVariable Long id

    ){
     return ResponseEntity.ok(this.classService.get(id));
    }

    @Operation(summary = "Crear un nuevo registro", description = "Ingrese los datos requeridos para crear un nuevo registro")
    @PostMapping
    public ResponseEntity<ClassResponse> insert(
            @Validated @RequestBody ClassRequest request
    ){
        return ResponseEntity.ok(this.classService.create(request));

    }





}
