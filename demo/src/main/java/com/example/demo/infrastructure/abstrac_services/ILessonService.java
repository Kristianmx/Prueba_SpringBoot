package com.example.demo.infrastructure.abstrac_services;

import com.example.demo.api.dto.requests.LessonRequest;
import com.example.demo.api.dto.responses.LessonResponse;

public interface ILessonService extends CrudService<LessonRequest, LessonResponse,Long>{
    public LessonResponse disable( Long id);
}
