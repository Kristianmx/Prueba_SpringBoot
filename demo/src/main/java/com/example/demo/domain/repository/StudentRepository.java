package com.example.demo.domain.repository;

import com.example.demo.domain.entity.ClassE;
import com.example.demo.domain.entity.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository

public interface StudentRepository extends JpaRepository<Student,Long> {
    public Page<Student> findByNameContainingAndActive(String name, Boolean active , PageRequest pageable);
}
