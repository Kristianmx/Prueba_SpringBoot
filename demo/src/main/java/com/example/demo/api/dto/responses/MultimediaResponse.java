package com.example.demo.api.dto.responses;

import com.example.demo.utils.enums.typeMultimedia;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MultimediaResponse {
    private Long id;
    private typeMultimedia type;
    private String url;
    private LocalDateTime createdAt;
}
