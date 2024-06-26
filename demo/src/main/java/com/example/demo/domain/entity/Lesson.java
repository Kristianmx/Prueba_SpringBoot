package com.example.demo.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity(name = "lesson")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Lesson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 200,nullable = false)
    private String title;
    @Column(columnDefinition = "TEXT",nullable = false)
    private String content;
    @Column(nullable = false)
    private LocalDateTime createdAt;
    @Column(nullable = false)
    private Boolean active;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="class_lesson_id",referencedColumnName = "id")
    private ClassE classe;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(
            mappedBy = "lesson",
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL,
            orphanRemoval = false
    )
    private List<Multimedia> multimedia;
}
