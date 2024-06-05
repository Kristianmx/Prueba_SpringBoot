package com.example.demo.domain.repository;

import com.example.demo.domain.entity.ClassE;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassRepository extends JpaRepository<ClassE,Long> {

    public Page<ClassE> findByDescriptionContainingOrNameContainingAndActive(String name, String description, Boolean active , PageRequest pageable);

}
