package com.backend.service;

import com.backend.entity.Question;
import org.springframework.stereotype.Service;

@Service
public interface QuestionService {

    void addQuestion(Question question);

}
