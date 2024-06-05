package com.example.demo.api.controllers;

import com.example.demo.api.dto.requests.StudentRequest;
import com.example.demo.api.dto.responses.StudentResponse;
import com.example.demo.infrastructure.abstrac_services.IStudentService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/student")
@AllArgsConstructor
public class StudentController {
    @Autowired
    private final IStudentService studentService;
    @Operation(summary = "Mostrar todos los registros", description = "Devuelve todos los registros paginados")
    @GetMapping
    public ResponseEntity<Page<StudentResponse>> getAll(
            @RequestParam(defaultValue = " ")String name,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "5") int size
    ){
        return ResponseEntity.ok(this.studentService.getAll(page-1,size,name,""));
    }

    @Operation(summary = "Buscar por ID", description = "Devuelve el registro con el ID proporcionado")
    @GetMapping(path = "/{id}")
    public ResponseEntity<StudentResponse> get(
            @PathVariable Long id
    ){
     return ResponseEntity.ok(this.studentService.get(id));
    }

    @Operation(summary = "Crear un nuevo registro", description = "Ingrese los datos requeridos para crear un nuevo registro")
    @PostMapping
    public ResponseEntity<StudentResponse> insert(
            @Validated @RequestBody StudentRequest request
    ){
        return ResponseEntity.ok(this.studentService.create(request));
    }

    @Operation(summary = "Actualizar un registro existente", description = "Ingrese el ID del registro a actualizar y los nuevos datos")
    @PutMapping (path = "/{id}")
    public ResponseEntity<StudentResponse> update(
            @Validated @RequestBody StudentRequest request,
            @PathVariable Long id
    ){
        return ResponseEntity.ok(this.studentService.update(request,id));
    }

    @Operation(summary = "deshabilitar  un registro", description = "Ingrese el ID del registro a deshabilitar ")
    @PatchMapping(path = "/{id}/disable")
    public ResponseEntity<StudentResponse> delete(
            @PathVariable Long id
    ){
    return ResponseEntity.ok(this.studentService.disable(id));
    }




}
