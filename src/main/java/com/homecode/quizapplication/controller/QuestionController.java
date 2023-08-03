package com.homecode.quizapplication.controller;

import com.homecode.quizapplication.model.dto.QuestionAddDTO;
import com.homecode.quizapplication.model.view.QuestionView;
import com.homecode.quizapplication.service.QuestionService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("questions")
@Validated
public class QuestionController {

    private final QuestionService questionService;

    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @GetMapping("/allQuestions")
    public ResponseEntity<List<QuestionView>> getAllQuestions(){
        return questionService.getAllQuestions();
    }

    @GetMapping("/{id}")
    public ResponseEntity<QuestionView> getById(@PathVariable("id") Integer id){
        return questionService.getQuestionByID(id);
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<QuestionView>> getQuestionsByCategory(@PathVariable("category") String category){
        return  questionService.getQuestionByCategory(category);
    }

    @PostMapping("/create")
    public ResponseEntity<String> addQuestion(@Valid @RequestBody QuestionAddDTO questionAddDTO){
        return  questionService.addQuestion(questionAddDTO);
    }
    @PutMapping("/{id}")
    public ResponseEntity<String> updateQuestionByID(@PathVariable("id") Integer id
            ,@Valid @RequestBody QuestionAddDTO questionAddDTO){
        return questionService.updateQuestionByID(id,questionAddDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteQuestion(@PathVariable("id") Integer id){
        return questionService.deleteQuestion(id);
    }
}
