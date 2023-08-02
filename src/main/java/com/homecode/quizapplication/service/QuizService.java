package com.homecode.quizapplication.service;

import com.homecode.quizapplication.model.Question;
import com.homecode.quizapplication.model.Quiz;
import com.homecode.quizapplication.model.dto.QuestionToClientDTO;
import com.homecode.quizapplication.repository.QuizRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuizService {
    private final QuizRepository quizRepository;
    private final QuestionService questionService;

    public QuizService(QuizRepository quizRepository, QuestionService questionService) {
        this.quizRepository = quizRepository;
        this.questionService = questionService;
    }

    public ResponseEntity<String> createQuiz(String category, int numQ, String title) {

        List<Question> questions = questionService.findRandomQuestionsByCategory(category, numQ);

        Quiz quiz = Quiz.builder()
                .title(title)
                .questions(questions)
                .build();

        this.quizRepository.save(quiz);

        return new ResponseEntity<>("Success", HttpStatus.CREATED);
    }

    public ResponseEntity<List<QuestionToClientDTO>> getQuizQuestions(Integer id) {

        Optional<Quiz> quiz = quizRepository.findById(id);
        List<Question> questionsFromDB = quiz.get().getQuestions();
        List<QuestionToClientDTO> questionsDTOList = questionsFromDB
                .stream()
                .map(this::mapQuestionToDTO)
                .toList();

        return new ResponseEntity<>(questionsDTOList, HttpStatus.OK);
    }

    public QuestionToClientDTO mapQuestionToDTO(Question q) {

        return QuestionToClientDTO.builder()
                .questionTitle(q.getQuestionTitle())
                .option1(q.getOption1())
                .option2(q.getOption2())
                .option3(q.getOption3())
                .option4(q.getOption4())
                .build();
    }
}
