package com.example.demo.infrastructure.services;

import com.example.demo.api.dto.requests.ClassRequest;
import com.example.demo.api.dto.responses.ClassResponse;
import com.example.demo.api.dto.responses.StudentResponse;
import com.example.demo.domain.entity.ClassE;
import com.example.demo.domain.entity.Student;
import com.example.demo.domain.repository.ClassRepository;
import com.example.demo.domain.repository.StudentRepository;
import com.example.demo.infrastructure.abstrac_services.IClassService;
import com.example.demo.utils.exceptions.IdNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ClassService implements IClassService {

    @Autowired
    private final ClassRepository classRepository;

    @Override
    public ClassResponse create(ClassRequest request) {
        ClassE classE = this.requestEntity(request,new ClassE());
        classE.setActive(true);
        classE.setCreatedAt(LocalDateTime.now());
        return this.entityToResponse(this.classRepository.save(classE),0);
    }

    @Override
    public ClassResponse get(Long aLong) {
        return this.entityToResponse(this.find(aLong),1);
    }

    @Override
    public ClassResponse update(ClassRequest request, Long aLong) {
       return null;
    }

    @Override
    public void delete(Long aLong) {

    }

    @Override
    public Page<ClassResponse> getAll(int page, int size, String name,String description) {
        if (page<0)
            page=0;

        PageRequest pagination = PageRequest.of(page,size);


        return this.classRepository.findByDescriptionContainingOrNameContainingAndActive(name,description,true,pagination).map(pa->this.entityToResponse(pa,0));
    }

    public ClassE find(Long id){
        return this.classRepository.findById(id).orElseThrow(()->new IdNotFoundException("Clases"));

    }
    private StudentResponse entityToResponseStudent(Student entity){
        StudentResponse response = new StudentResponse();
        BeanUtils.copyProperties(entity,response);
        return response;
    }
    private ClassResponse entityToResponse (ClassE entity,int valid){
        ClassResponse response  = new ClassResponse();
        BeanUtils.copyProperties(entity,response);
        List<StudentResponse> student = new ArrayList<>();
       if (valid==1){
           if (entity.getStudents()!=null){
               student = entity.getStudents().stream().map(this::entityToResponseStudent).collect(Collectors.toList());

           }
       }
        response.setStudent(student);
        return response;

    }


    public ClassE requestEntity ( ClassRequest request, ClassE classe){
        BeanUtils.copyProperties(request,classe);
        return classe;
    }
}
