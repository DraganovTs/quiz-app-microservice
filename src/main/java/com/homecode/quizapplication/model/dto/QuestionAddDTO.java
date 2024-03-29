package com.homecode.quizapplication.model.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuestionAddDTO {
    @NotEmpty
    private String questionTitle;
    @NotEmpty
    private String option1;
    @NotEmpty
    private String option2;
    @NotEmpty
    private String option3;
    @NotEmpty
    private String option4;
    @NotEmpty
    private String rightAnswer;
    @NotEmpty
    private String difficultyLevel;
    @NotEmpty
    private String category;
}
