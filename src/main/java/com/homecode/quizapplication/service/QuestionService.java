package com.homecode.quizapplication.service;

import com.homecode.quizapplication.model.dto.QuestionAddDTO;
import com.homecode.quizapplication.repository.QuestionRepository;
import com.homecode.quizapplication.model.Question;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {

    private final QuestionRepository questionRepository;

    public QuestionService(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    public ResponseEntity<List<Question>> getAllQuestions() {
        try {
            return new ResponseEntity<>(questionRepository.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
        }
    }

    public void saveAll(List<Question> generatedQuestions) {
        questionRepository.saveAll(generatedQuestions);
    }

    public boolean isEmpty() {
        return questionRepository.count() == 0;
    }

    public ResponseEntity<List<Question>> getQuestionByCategory(String category) {
        try {
            return new ResponseEntity<>(questionRepository.findAllByCategory(category), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<String> addQuestion(QuestionAddDTO questionAddDTO) {

        Question questionToAdd = Question.builder()
                .questionTitle(questionAddDTO.getQuestionTitle())
                .rightAnswer(questionAddDTO.getRightAnswer())
                .difficultyLevel(questionAddDTO.getDifficultyLevel())
                .category(questionAddDTO.getCategory())
                .option1(questionAddDTO.getOption1())
                .option2(questionAddDTO.getOption2())
                .option3(questionAddDTO.getOption3())
                .option4(questionAddDTO.getOption4())
                .build();
        try {
            questionRepository.save(questionToAdd);
            return new ResponseEntity<>("question is added",HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("question is not added",HttpStatus.NOT_ACCEPTABLE);
        }
    }

    public List<Question> findRandomQuestionsByCategory(String category) {
        return null;
    }
}
