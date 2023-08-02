package com.homecode.quizapplication.model.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class QuestionToClientDTO {

    private String questionTitle;
    private String option1;
    private String option2;
    private String option3;
    private String option4;

}
