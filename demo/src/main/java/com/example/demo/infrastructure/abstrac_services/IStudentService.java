package com.example.demo.infrastructure.abstrac_services;

import com.example.demo.api.dto.requests.StudentRequest;
import com.example.demo.api.dto.responses.StudentResponse;

public interface IStudentService extends  CrudService<StudentRequest, StudentResponse,Long>{
    public  StudentResponse disable(Long id);
}
