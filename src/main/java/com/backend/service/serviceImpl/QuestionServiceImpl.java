package com.backend.service.serviceImpl;

import com.backend.entity.Question;
import com.backend.repositories.QuestionRepository;
import com.backend.service.QuestionService;
import com.backend.utils.UserUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private UserUtility userUtility;

    @Override
    public void addQuestion(Question question) {



        if(question!=null){
            question.setUser(userUtility.getLoggedInUser());
           Question q = questionRepository.save(question);
            System.out.println();
        }
    }
}
