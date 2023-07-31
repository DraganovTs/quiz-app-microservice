package com.homecode.quizapplication.service;

import com.homecode.quizapplication.model.dto.QuestionAddDTO;
import com.homecode.quizapplication.repository.QuestionRepository;
import com.homecode.quizapplication.model.Question;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {

    private final QuestionRepository questionRepository;

    public QuestionService(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    public List<Question> getAllQuestions() {
        return questionRepository.findAll();
    }

    public void saveAll(List<Question> generatedQuestions) {
        questionRepository.saveAll(generatedQuestions);
    }

    public boolean isEmpty() {
        return questionRepository.count() == 0;
    }

    public List<Question> getQuestionByCategory(String category) {
        return questionRepository.findAllByCategory(category);
    }

    public String addQuestion(QuestionAddDTO questionAddDTO) {
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
            return "question is added";
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}
