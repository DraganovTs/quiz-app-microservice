package com.homecode.quizapplication.controller;

import com.homecode.quizapplication.model.Question;
import com.homecode.quizapplication.model.dto.QuestionAddDTO;
import com.homecode.quizapplication.service.QuestionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("question")
public class QuestionController {

    private final QuestionService questionService;

    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @GetMapping("/allQuestions")
    public List<Question> getAllQuestions(){
        return  questionService.getAllQuestions();
    }

    @GetMapping("/category/{category}")
    public List<Question> getQuestionsByCategory(@PathVariable String category){
        return  questionService.getQuestionByCategory(category);
    }

    @PostMapping("/add")
    public String addQuestion(@RequestBody QuestionAddDTO questionAddDTO){
        return  questionService.addQuestion(questionAddDTO);

    }
}
