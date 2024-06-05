package com.example.demo.infrastructure.services;

import com.example.demo.api.dto.requests.LessonRequest;
import com.example.demo.api.dto.requests.MultimediaRequest;
import com.example.demo.api.dto.responses.LessonResponse;
import com.example.demo.api.dto.responses.MultimediaResponse;
import com.example.demo.domain.entity.Lesson;
import com.example.demo.domain.entity.Multimedia;
import com.example.demo.domain.repository.LessonRepository;
import com.example.demo.domain.repository.MultimediaRepository;
import com.example.demo.infrastructure.abstrac_services.ILessonService;
import com.example.demo.utils.exceptions.IdNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class LessonService implements ILessonService {
    @Autowired
    private final LessonRepository lessonRepository;

    @Autowired
    private final MultimediaRepository multimediaRepository;


    @Override
    public LessonResponse create(LessonRequest request) {
        Lesson lesson = this.requestEntity(request,new Lesson());
        lesson.setCreatedAt(LocalDateTime.now());
        lesson.setActive(true);
        Lesson response = this.lessonRepository.save(lesson);
        request.getMultimedia().forEach(mul-> this.createMultimedia(mul,response.getId()));

        return this.entityToResponse(this.find(response.getId()));

    }
     private void createMultimedia(MultimediaRequest multimedia, Long id){
        Lesson lesson = this.find(id);
        Multimedia multimedia1 =new Multimedia();
        BeanUtils.copyProperties(multimedia,multimedia1);
        multimedia1.setLesson(lesson);
        multimedia1.setCreatedAt(LocalDateTime.now());
        multimedia1.setActive(true);
        this.multimediaRepository.save(multimedia1);
     }

    @Override
    public LessonResponse get(Long aLong) {
        return this.entityToResponse(this.find(aLong));
    }

    @Override
    public LessonResponse update(LessonRequest request, Long aLong) {
        return null;
    }

    @Override
    public void delete(Long aLong) {

    }

    @Override
    public Page<LessonResponse> getAll(int page, int size, String name, String description) {
         if (page<0)
             page=0;

        PageRequest pagination = PageRequest.of(page,size);
        return this.lessonRepository.findAll(pagination).map(this::entityToResponse);
    }

    public Lesson find(Long id){
        return this.lessonRepository.findById(id).orElseThrow(()->new IdNotFoundException("Lesion"));

    }
    private LessonResponse entityToResponse(Lesson entity){
        LessonResponse response = new LessonResponse();
        BeanUtils.copyProperties(entity,response);
        List<MultimediaResponse> multimediaResponses=new ArrayList<>();
        if (entity.getMultimedia()!=null) {
            multimediaResponses = entity.getMultimedia().stream().map(this::entityResponseMultimedia).collect(Collectors.toList());
        }
        response.setMultimedia(multimediaResponses);
        return response;
    }

    private MultimediaResponse entityResponseMultimedia(Multimedia entity){
        MultimediaResponse response = new MultimediaResponse();
        BeanUtils.copyProperties(entity,response);
        return response;
    }

    private Lesson requestEntity(LessonRequest request,Lesson lesson){
        BeanUtils.copyProperties(request,lesson);

        return lesson;
    }


    @Override
    public LessonResponse disable(Long id) {
        Lesson lesson = this.find(id);
        lesson.setActive(false);

      return this.entityToResponse(this.lessonRepository.save(lesson));
    }


}
