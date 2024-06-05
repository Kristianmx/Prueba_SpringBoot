package com.example.demo.domain.entity;

import com.example.demo.utils.enums.typeMultimedia;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity(name = "multimedia")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Multimedia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(value = EnumType.STRING)
    private typeMultimedia type;
    @Column(columnDefinition = "TEXT",nullable = false)
    private String url;
    @Column(nullable = false)
    private LocalDateTime createdAt;
    @Column(nullable = false)
    private Boolean active;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lesson_id",referencedColumnName = "id")
    private  Lesson lesson;


}
