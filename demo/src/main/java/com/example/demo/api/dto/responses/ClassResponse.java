package com.example.demo.api.dto.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClassResponse {
    private Long id;
    private String name;
    private String description;
    private LocalDateTime createdAt;

}
