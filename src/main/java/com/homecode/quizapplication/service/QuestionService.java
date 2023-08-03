package com.homecode.quizapplication.service;

import com.homecode.quizapplication.exception.ResourceNotFoundException;
import com.homecode.quizapplication.model.dto.QuestionAddDTO;
import com.homecode.quizapplication.model.view.QuestionView;
import com.homecode.quizapplication.repository.QuestionRepository;
import com.homecode.quizapplication.model.Question;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuestionService {

    private final QuestionRepository questionRepository;

    public QuestionService(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    //Init methods
    public void saveAll(List<Question> generatedQuestions) {
        questionRepository.saveAll(generatedQuestions);
    }

    public boolean isEmpty() {
        return questionRepository.count() == 0;
    }

    //Production methods
    public ResponseEntity<List<QuestionView>> getAllQuestions() {
        try {
            return new ResponseEntity<>(questionRepository.findAll()
                    .stream().map(this::mapQuestionToView)
                    .collect(Collectors.toList()), HttpStatus.OK);
        } catch (Exception e) {
            throw new ResourceNotFoundException("Questions Not Found!");
        }
    }

    public ResponseEntity<QuestionView> getQuestionByID(Integer id) {

        QuestionView questionView = questionRepository.findById(id)
                .map(this::mapQuestionToView)
                .orElseThrow(() -> new ResourceNotFoundException("Question with ID :" + id + " Not Found!"));

        return ResponseEntity.ok().body(questionView);
    }

    public ResponseEntity<List<QuestionView>> getQuestionByCategory(String category) {
        try {
            return new ResponseEntity<>(questionRepository.findAllByCategory(category)
                    .stream()
                    .map(this::mapQuestionToView)
                    .collect(Collectors.toList()), HttpStatus.OK);
        } catch (Exception e) {
            throw new ResourceNotFoundException("Questions Not Found!");
        }
    }

    public ResponseEntity<String> addQuestion(QuestionAddDTO questionAddDTO) {

        Question questionToAdd = mapQuestionToAddToQuestion(questionAddDTO);
        try {
            questionRepository.save(questionToAdd);
            return new ResponseEntity<>("question is added", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("question is not added", HttpStatus.NOT_ACCEPTABLE);
        }
    }

    public ResponseEntity<String> updateQuestionByID(Integer id, QuestionAddDTO questionAddDTO) {

        Question qn = questionRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Question with ID :" + id + " Not Found!"));

        Question questionToSave = mapQuestionToAddToQuestion(questionAddDTO);
        questionToSave.setId(qn.getId());
        questionRepository.save(questionToSave);
        return ResponseEntity.ok().body("question is updated");
    }
    public ResponseEntity<String> deleteQuestion(Integer id) {

        try {
        Question question = questionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Question with ID :" + id + " Not Found!"));

        questionRepository.deleteById(question.getId());

        return ResponseEntity.ok().body("Question deleted with success!");
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Question is in many quiz cant be deleted at this time");
        }
    }

    public List<Question> findRandomQuestionsByCategory(String category, int numQ) {
        return questionRepository.findByCategoryAndNumQ(category, numQ);
    }


    private Question mapQuestionToAddToQuestion(QuestionAddDTO q) {
        return Question.builder()
                .questionTitle(q.getQuestionTitle())
                .rightAnswer(q.getRightAnswer())
                .difficultyLevel(q.getDifficultyLevel())
                .category(q.getCategory())
                .option1(q.getOption1())
                .option2(q.getOption2())
                .option3(q.getOption3())
                .option4(q.getOption4())
                .build();
    }

    private QuestionView mapQuestionToView(Question q) {
        return QuestionView.builder()
                .id(q.getId())
                .questionTitle(q.getQuestionTitle())
                .category(q.getCategory())
                .difficultyLevel(q.getDifficultyLevel())
                .rightAnswer(q.getRightAnswer())
                .option1(q.getOption1())
                .option2(q.getOption2())
                .option3(q.getOption3())
                .option4(q.getOption4())
                .build();

    }



}
