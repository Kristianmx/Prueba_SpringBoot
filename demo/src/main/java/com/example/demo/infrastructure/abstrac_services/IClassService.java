package com.example.demo.infrastructure.abstrac_services;

import com.example.demo.api.dto.requests.ClassRequest;
import com.example.demo.api.dto.responses.ClassResponse;

public interface IClassService extends CrudService<ClassRequest, ClassResponse,Long>{
}
