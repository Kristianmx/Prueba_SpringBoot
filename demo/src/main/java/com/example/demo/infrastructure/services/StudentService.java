package com.example.demo.infrastructure.services;

import com.example.demo.api.dto.requests.StudentRequest;
import com.example.demo.api.dto.responses.ClassResponse;
import com.example.demo.api.dto.responses.StudentResponse;
import com.example.demo.domain.entity.ClassE;
import com.example.demo.domain.entity.Student;
import com.example.demo.domain.repository.ClassRepository;
import com.example.demo.domain.repository.StudentRepository;
import com.example.demo.infrastructure.abstrac_services.IStudentService;
import com.example.demo.utils.exceptions.IdNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Service
@AllArgsConstructor
public class StudentService implements IStudentService {
    @Autowired
    private final StudentRepository studentRepository;
    @Autowired
    private final ClassRepository classRepository;
    @Override
    public StudentResponse create(StudentRequest request) {
        Student student=this.requestToEntity(request,new Student());
        ClassE classE = this.classRepository.findById(request.getClass_id()).orElseThrow(()->new IdNotFoundException("clase"));
          student.setClasse(classE);
          student.setActive(true);
          student.setCreatedAt(LocalDateTime.now());
        return this.entityResponse(this.studentRepository.save(student));
    }

    @Override
    public StudentResponse get(Long aLong) {
        return this.entityResponse(this.find(aLong));
    }

    @Override
    public StudentResponse update(StudentRequest request, Long aLong) {
        Student student = this.find(aLong);
        Student studentUpdate = this.requestToEntity(request,student);
        ClassE classE = this.classRepository.findById(request.getClass_id()).orElseThrow(()->new IdNotFoundException("clase"));
        classE.setStudents(new ArrayList<>());
        student.setClasse(classE);
        studentUpdate.setClasse(classE);
        return this.entityResponse(this.studentRepository.save(studentUpdate));
    }

    @Override
    public void delete(Long aLong) {

    }

    @Override
    public Page<StudentResponse> getAll(int page, int size, String name, String description) {
        if (page<0)
            page=0;

        PageRequest pagination = PageRequest.of(page,size);
        return this.studentRepository.findByNameContainingAndActive(name,true,pagination).map(this::entityResponse);
    }

    public Student find(Long id){
        return this.studentRepository.findById(id).orElseThrow(()->new IdNotFoundException("estudiante"));
    }

    public StudentResponse entityResponse(Student entity){
        StudentResponse response = new StudentResponse();
        BeanUtils.copyProperties(entity,response);
        ClassResponse classResponse = new ClassResponse();
        BeanUtils.copyProperties(entity.getClasse(),classResponse);
        response.setClasse(classResponse);
        return response;

    }
    @Override
    public  StudentResponse disable(Long id){
        Student student = this.find(id);
        student.setActive(false);

        return this.entityResponse(this.studentRepository.save(student));
    }

    public Student requestToEntity ( StudentRequest request,Student student){
        BeanUtils.copyProperties(request,student);
        return student;
    }
}
