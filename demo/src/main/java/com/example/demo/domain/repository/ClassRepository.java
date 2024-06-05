package com.example.demo.domain.repository;

import com.example.demo.domain.entity.ClassE;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClassRepository extends JpaRepository<ClassE,Long> {
}
