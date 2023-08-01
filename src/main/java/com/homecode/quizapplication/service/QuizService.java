package com.homecode.quizapplication.service;

import com.homecode.quizapplication.model.Question;
import com.homecode.quizapplication.repository.QuizRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuizService {
    private final QuizRepository quizRepository;
    private final QuestionService questionService;

    public QuizService(QuizRepository quizRepository, QuestionService questionService) {
        this.quizRepository = quizRepository;
        this.questionService = questionService;
    }

    public ResponseEntity<String> createQuiz(String category, String title) {

        List<Question> questions = questionService.findRandomQuestionsByCategory(category);

        return null;
    }
}
