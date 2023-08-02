package com.homecode.quizapplication.controller;

import com.homecode.quizapplication.model.Response;
import com.homecode.quizapplication.model.dto.QuestionToClientDTO;
import com.homecode.quizapplication.service.QuizService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("quiz")
public class QuizController {

    private final QuizService quizService;

    public QuizController(QuizService quizService) {
        this.quizService = quizService;
    }

    @PostMapping("create")
    public ResponseEntity<String> createQuiz(@RequestParam String category,
                                             @RequestParam int numQ,
                                             @RequestParam String title){
        return quizService.createQuiz(category,numQ ,title);
    }

    @GetMapping("get/{id}")
    public ResponseEntity<List<QuestionToClientDTO>> getQuizQuestions(@PathVariable Integer id){
        return quizService.getQuizQuestions(id);
    }

    @PostMapping("submit/{id}")
    public ResponseEntity<Integer> submitQuiz(@PathVariable Integer id,
                                              @RequestBody List<Response> responseList){
        return quizService.calculateResult(id,responseList);

    }
}
