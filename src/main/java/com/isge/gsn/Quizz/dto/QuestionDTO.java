package com.isge.gsn.Quizz.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class QuestionDTO {
    private long id;
    private String content;
    private String answer;

    private List<AnswerDTO> answers ;
}
