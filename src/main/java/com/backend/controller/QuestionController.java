package com.backend.controller;

import com.backend.dto.QuestionRequest;
import com.backend.dto.Response;
import com.backend.entity.Question;
import com.backend.exceptions.BadRequestException;
import com.backend.exceptions.UserExistException;
import com.backend.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class QuestionController {

    @Autowired
    private final ModelMapper modelMapper;

    @Autowired
    private QuestionService questionService;

    @PostMapping("/question")

    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    public Response addQuestion(@AuthenticationPrincipal UserDetails userDetails,
                                                @RequestBody QuestionRequest request){
        boolean inValidRequest = validateRequest(request);

        if(inValidRequest){
            throw new BadRequestException("Bad Request.");
        }
        Question question = modelMapper.map(request, Question.class);
        try{
            questionService.addQuestion(question);
        } catch (Exception e) {
            throw new BadRequestException("Bad Request.");
        }

        return Response.builder()
                .status(200)
                .message("Question has been added sucessfully.")
                .build();
        //return ResponseEntity.ok("Question has been added sucessfully.");
    }

    private boolean validateRequest(QuestionRequest request) {
        if(request!=null){
            if(request.getSubject()==null){
                return true;
            }
            if(request.getTopic()==null){
                return true;
            }
            if(request.getQuestion()==null){
                return true;
            }
            if(request.getAnswer()==null){
                return true;
            }
        }
        return false;
    }

}
