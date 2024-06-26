package com.example.demo.api.dto.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LessonResponse {
    private Long id;
    private String title;
    private String content;
    private LocalDateTime createdAt;
    private List<MultimediaResponse> multimedia;
}
